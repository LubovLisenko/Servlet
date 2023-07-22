package org.example;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZoneOffset;

@WebFilter(value = "/time/*")
public class TimezoneValidateFilter extends HttpFilter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doFilter(HttpServletRequest req,
                            HttpServletResponse res,
                            FilterChain chain) throws IOException, ServletException {

        String timezone = req.getParameter("timezone");

       if (timezone.contains("timezone")) {
            chain.doFilter(req, res);
        } else {
           res.setStatus(400);
            res.getWriter().write("Invalid timezone");
            res.getWriter().close();
        }
        super.doFilter(req, res, chain);
    }
}
