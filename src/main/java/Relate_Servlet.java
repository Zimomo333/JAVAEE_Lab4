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
import java.io.IOException;

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
@WebServlet(urlPatterns = {"/Relate_Servlet"})
public class Relate_Servlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        String username=(String)session.getAttribute("username");
        String relate=request.getParameter("relate");

        LocalUser localUser=em.find(LocalUser.class,username);
        DgutUser dgutUser=em.find(DgutUser.class,relate);
        if(dgutUser!=null) {
            localUser.setdgutUser(dgutUser);
            dgutUser.setlocalUser(localUser);

            tx.begin();
            em.persist(localUser);
            em.persist(dgutUser);
            tx.commit();

            session.setAttribute("relate", relate);
            response.sendRedirect("index.jsp");
        }
        else {
            response.sendRedirect("index.jsp?error=yes");
        }
    }
}
