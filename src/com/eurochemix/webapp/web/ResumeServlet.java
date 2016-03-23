package com.eurochemix.webapp.web;

import com.eurochemix.webapp.XmlFileStorage;
import com.eurochemix.webapp.model.Resume;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ilya on 15.03.2016.
 */
public class ResumeServlet extends HttpServlet {
    public static XmlFileStorage storage = new XmlFileStorage("D:\\Java\\projects\\MyTestApp\\file_storage");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        /*response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Writer w = response.getWriter();
        String name = request.getParameter("name");
        w.write("Матвейка, привет!   - "+name);
        w.close();*/

        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("list");
                return;
            case "create":
                r = Resume.EMPTY;
                break;
            case "view":
            case "edit":
                r = storage.load(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + "is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(("view".equals(action) ? "WEB-INF/jsp/view.jsp" : "WEB-INF/jsp/edit.jsp")).forward(request, response);
    }

}
