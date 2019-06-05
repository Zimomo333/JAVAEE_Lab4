import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 梁梓豪
 * Created by 梁梓豪 on 2019-04-18.
 */
@WebFilter(filterName = "TokenFilter")
public class TokenFilter extends HttpFilter {
    public void destroy() {
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String postString_token = req.getParameter("token");

        // 如果回调有携带token可以放行。否则重定向到404错误提示页面
        if (postString_token == null) {
            res.sendRedirect("error.jsp");
        } else {
            chain.doFilter(req, res);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
