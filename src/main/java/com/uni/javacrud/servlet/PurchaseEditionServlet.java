package com.uni.javacrud.servlet;

import com.uni.javacrud.beans.Edition;
import com.uni.javacrud.beans.Subscription;
import com.uni.javacrud.beans.User;
import com.uni.javacrud.dao.EditionDao;
import com.uni.javacrud.dao.SubscriptionDao;
import com.uni.javacrud.dao.UserDao;
import com.uni.javacrud.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "PurchaseEditionServlet", value = "/purchase")
public class PurchaseEditionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        User user = MyUtils.getLoginedUser(request.getSession());
        EditionDao editionDao = new EditionDao(conn);
        UserDao userDao = new UserDao(conn);
        SubscriptionDao subscriptionDao = new SubscriptionDao(conn);

        String idStr = (String) request.getParameter("id");

        int id = 0;
        try {
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String errorString = null;

        try {
            Edition edition = editionDao.findEditionById(id);
            if(edition.getPrice() <= user.getMoney()) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

                LocalDate date_start = LocalDate.now();
                //String date_startStr = dateFormat.format(date_start);
                LocalDate date_end = LocalDate.now().plusDays( 30 );
                //String date_endStr = dateFormat.format(date_end);

                Subscription subscription = new Subscription(edition.getName(),  edition.getPrice(), date_start.toString(), date_end.toString(), id, user.getId());
                subscriptionDao.insertSubscription(subscription);
                userDao.addMoney(user.getId(), user.getMoney(), -edition.getPrice());
            }
            else {
                errorString = "Not enough money!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        if (errorString != null) {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/userEditionsView.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/userEditions");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
