import Bean.UserBean;
import Entity.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


/**
 * @author 梁梓豪
 * Created by 梁梓豪 on 2019-04-18.
 */
@WebServlet(urlPatterns = {"/dgut"})
public class Login_Servlet extends HttpServlet {

    private static final EntityManagerFactory entityManagerFactory;
    private static final String PERSISTENCE_UNIT_NAME = "MyJPADemoPU";

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //接收回调的token，并和appid，appsecret，userip一起打包成Json字符串jsonToken
        String postString_token = req.getParameter("token");
        TokenDTO tokenDTO = new TokenDTO(postString_token, "javaee", "b3b52e43ccfd", "10.0.2.2");
        Jsonb jsonb = JsonbBuilder.create();
        String jsonToken = jsonb.toJson(tokenDTO);

        //把打包好的Json字符串转换成StringEntity放到HttpPost请求中，发送请求到https://cas.dgut.edu.cn/ssoapi/v2/checkToken，获取access_token和openid
        HttpPost httpPost = new HttpPost("https://cas.dgut.edu.cn/ssoapi/v2/checkToken");
        httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
        StringEntity stringEntity = new StringEntity(jsonToken, "UTF-8");
        httpPost.setEntity(stringEntity);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse resp_access_token = httpclient.execute(httpPost);

        //接收包含access_token，openid的Entity回调，并转换成Json字符串jsonUser_info
        HttpEntity entity = resp_access_token.getEntity();
        String jsonAccess_token = EntityUtils.toString(entity, "UTF-8");
        Access_TokenDTO access_tokenDTO = jsonb.fromJson(jsonAccess_token, Access_TokenDTO.class);
        String jsonUser_info = jsonb.toJson(access_tokenDTO);

        //把包含access_token，openid的Json字符串转换成StringEntity放到HttpPost请求中，发送请求到https://cas.dgut.edu.cn/oauth/getUserInfo，获取用户信息
        httpPost = new HttpPost("https://cas.dgut.edu.cn/oauth/getUserInfo");
        httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
        stringEntity = new StringEntity(jsonUser_info, "UTF-8");
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse resp_user_info = httpclient.execute(httpPost);

        //接收包含用户信息的Entity回调，并转换成Json
        entity = resp_user_info.getEntity();
        String user_info = EntityUtils.toString(entity, "UTF-8");
        UserDTO userDTO = jsonb.fromJson(user_info, UserDTO.class);

        //从Json中获取用户信息，并存到session中
        String username = userDTO.getusername();
        String name = userDTO.getname();
        String grouptype = userDTO.getgroup();

        HttpSession session = req.getSession();

        UserBean userBean = new UserBean();
        userBean.setusername(username);
        //userBean.setpassword1("123456");
        //userBean.setname(name);
        //userBean.setgrouptype(grouptype);

        EntityManager em = getEntityManager();

        EntityTransaction tx = em.getTransaction();

        if(!userBean.check_register_dgut()){
            userBean.add_dgut();
            session.setAttribute("head", "unset.jpg");
        }
        else{
            DgutUser dgutUser= em.find(DgutUser.class,username);
            //查关联表，有则读关联账号邮箱，设置session
            if(dgutUser.getlocalUser()!=null) {
                LocalUser localUser = dgutUser.getlocalUser();
                session.setAttribute("email", localUser.getemail());
                session.setAttribute("relate",localUser.getusername());
            }
            session.setAttribute("head",dgutUser.gethead());
        }
        session.setAttribute("username", username);
        session.setAttribute("name", name);
        session.setAttribute("grouptype", grouptype);
        session.setAttribute("login","dgut");

        //重定向首页
        resp.sendRedirect("index.jsp");

        /*  传javabean，有bug
        req.setAttribute("userBean", userBean);
        try {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }catch (ServletException e){}
        */
    }
}
