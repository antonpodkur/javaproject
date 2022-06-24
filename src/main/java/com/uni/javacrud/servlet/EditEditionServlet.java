package com.uni.javacrud.servlet;

import com.uni.javacrud.beans.Edition;
import com.uni.javacrud.dao.EditionDao;
import com.uni.javacrud.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "EditEditionServlet", value = "/editEdition")
public class EditEditionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        EditionDao editionDao = new EditionDao(conn);

        String idStr = (String) request.getParameter("id");

        Edition edition = null;

        String errorString = null;
        int id = 0;

        try {
            id = Integer.parseInt(idStr);
            edition = editionDao.findEditionById(id);
        } catch (Exception e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        if (errorString != null && edition == null) {
            response.sendRedirect(request.getServletPath() + "/editionList");
            return;
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("edition", edition);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editEditionView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        EditionDao editionDao = new EditionDao(conn);
        String idStr = (String) request.getParameter("id");
        String name = (String) request.getParameter("name");
        String priceStr = (String) request.getParameter("price");
        String topic_idStr = (String) request.getParameter("topic_id");
        float price = 0;
        int topic_id = 0;
        int id = 0;
        try {
            price = Float.parseFloat(priceStr);
            topic_id = Integer.parseInt(topic_idStr);
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
        }
        Edition edition = new Edition(id, name, price, topic_id);

        String errorString = null;

        try {
            editionDao.updateEdition(edition);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("edition", edition);

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editEditionView.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/editionList");
        }
    }
}
