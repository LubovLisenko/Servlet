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

        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss"
        ));

        String[] timeZoneSplit = timezone.split(" ");
         String utc = timeZoneSplit[0];
        int hour = Integer.parseInt(timeZoneSplit[1]);

        if (hour == 2){
           resp.getWriter().write(currentTime + " UTC + 2");

       } else resp.getWriter().write(LocalDateTime.now()
                .plusHours(hour - 2)
                .format(DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss")) + " " + utc + "+" + hour);
        resp.getWriter().close();
        super.doGet(req, resp);
    }


    }





