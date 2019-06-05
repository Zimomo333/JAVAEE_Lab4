
import Entity.DgutUser;
import Entity.LocalUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

/**
 * @author Zimomo
 * Created by Zimomo on 2019-05-23.
 *
 * @see MultipartConfig
 * 使用注解@MultipartConfig 将一个 Servlet 标识为支持文件上传。
 * Servlet3 将 multipart/form-data 的 POST 请求封装成 Part，通过 Part 对文件进行上传。
 * Servlet3 没有提供直接获取文件名的方法,需要从请求头中解析出来。
 *
 * @see Part
 * {@link HttpServletRequest} 提供了两个方法用于从请求中解析出上传的文件：
 * {@link HttpServletRequest#getPart(String)}
 * {@link HttpServletRequest#getParts()}
 * 前者用于获取请求中给定 name 的文件，后者用于获取所有的文件。
 * 每一个文件用一个 javax.servlet.http.Part 对象来表示。
 * 该接口提供了处理文件的简易方法，比如 write()、delete() 等。
 * 至此，结合 HttpServletRequest 和 Part 来保存上传的文件变得非常简单。
 *
 * 本Servlet演示上传单个文件时的处理过程。
 *
 * 详情请查看javaee-web-api-8.0-javadoc的api文档
 * 下载地址：https://jcp.org/aboutJava/communityprocess/final/jsr366/index.html
 */
@WebServlet(urlPatterns = {"/Upload_Servlet"})
@MultipartConfig(location = "C:\\Users\\Zimomo\\Documents\\Visual Studio 2017\\JAVA EE\\Lab4\\src\\main\\webapp\\images\\head_photos\\")// location改为本地的绝对路径
public class Upload_Servlet extends HttpServlet {

    private static final EntityManagerFactory entityManagerFactory;
    private static final String PERSISTENCE_UNIT_NAME = "MyJPADemoPU";

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = getEntityManager();

        EntityTransaction tx = em.getTransaction();

        DgutUser dgutUser1;
        LocalUser localUser1;

        HttpSession session = request.getSession();
        String username=(String)session.getAttribute("username");
        String head=(String)session.getAttribute("head");

        Random r=new Random();

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        if(!head.equals("unset.jpg")){
            File file = new File("C:\\Users\\Zimomo\\Documents\\Visual Studio 2017\\JAVA EE\\Lab4\\src\\main\\webapp\\images\\head_photos\\"+head);
            file.delete();
        }

        // 获取请求项，封装成Part对象
        // request.getPart方法的传参为前端页面from表单的input文件组件的name值
        Part part = request.getPart("file");
        head=r.nextInt(9999999)+".jpg";

        part.write(head);

        localUser1=em.find(LocalUser.class,username);
        if(localUser1!=null){
            localUser1.sethead(head);
            tx.begin();
            em.persist(localUser1);
            tx.commit();
            if(localUser1.getdgutUser()!=null) {
                dgutUser1=localUser1.getdgutUser();
                dgutUser1.sethead(head);
                tx.begin();
                em.persist(dgutUser1);
                tx.commit();
            }
        }
        else{
            dgutUser1=em.find(DgutUser.class,username);
            dgutUser1.sethead(head);
            tx.begin();
            em.persist(dgutUser1);
            tx.commit();
            if(dgutUser1.getlocalUser()!=null) {
                LocalUser localUser_temp=dgutUser1.getlocalUser();
                localUser_temp.sethead(head);
                tx.begin();
                em.persist(localUser_temp);
                tx.commit();
            }
        }

        session.setAttribute("head", head);
        //重定向首页
        response.sendRedirect("index.jsp");
    }
}
