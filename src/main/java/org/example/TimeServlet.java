package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;



@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String timezone = req.getParameter("timezone");


        if (req.getParameterMap().containsKey("timezone")){
             req.getParameter("timezone");
            String[] timeZoneSplit = timezone.split(" ");
            String utc = timeZoneSplit[0];
            int hour = Integer.parseInt(timeZoneSplit[1]);
            resp.getWriter().write(LocalDateTime.now()
                    .plusHours(hour - 3)
                    .format(DateTimeFormatter.ofPattern(
                            "yyyy-MM-dd HH:mm:ss")) + " " + utc + "+" + hour);

        } else {

        resp.getWriter().write(String.valueOf(LocalDateTime.now().minusHours(3)
                .format(DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss"))) + " UTC ");
    }

        resp.getWriter().close();
            super.doGet(req, resp);
        }

    }





