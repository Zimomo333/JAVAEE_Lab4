import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 梁梓豪
 * Created by 梁梓豪 on 2019-04-18.
 */
@WebFilter(filterName = "SessionFilter")
public class SessionFilter extends HttpFilter {
    public void destroy() {
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String path = req.getRequestURI();

        // 如果包含login或register证明是登陆或注册就放行,包含dgut证明是回调，包含bootstrap、css、images证明是模板资源申请，
        if (path.contains("console") ||path.contains("login.html") || path.contains("login_action") || path.contains("dgut")|| path.contains("bootstrap") || path.contains("jQuery") || path.contains("register")|| path.contains("css")|| path.contains("images") || path.contains("error.jsp")) {
            chain.doFilter(req, res); // 放行
            return;
        }

        HttpSession session = req.getSession();
        // 如果session中存在user证明用户登录放行，否则认为未登陆重定向到login.html
        if (session == null||session.getAttribute("username")==null) {
            res.sendRedirect("login.html");
        } else {
            chain.doFilter(req, res);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
