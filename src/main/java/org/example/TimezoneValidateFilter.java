package org.example;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DateTimeException;
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
        if (timezone == null || isValidTimezone(timezone)) {
            chain.doFilter(req, res);
        } else {
            res.setContentType("text/html; charset=utf-8");
            res.getWriter().write("ERROR 400: Invalid timezone");
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().close();
        }
    }

        private boolean isValidTimezone(String timezone) {
            try {
                ZoneId.of(timezone.replaceAll(" ", "+"));
                return true;
            } catch (DateTimeException e) {
                return false;
            }
        }
}
