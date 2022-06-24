package com.uni.javacrud.servlet;

import com.uni.javacrud.beans.Edition;
import com.uni.javacrud.beans.Subscription;
import com.uni.javacrud.beans.User;
import com.uni.javacrud.dao.EditionDao;
import com.uni.javacrud.dao.SubscriptionDao;
import com.uni.javacrud.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserSubscriptionListServlet", value = "/userSubscriptions")
public class UserSubscriptionListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        User logginedUser = MyUtils.getLoginedUser(request.getSession());
        SubscriptionDao subscriptionDao = new SubscriptionDao(conn);

        String errorString = null;
        List<Subscription> subscriptions = null;
        try {
            subscriptions = subscriptionDao.querySubscriptionsByUserId(logginedUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("subscriptionList", subscriptions);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/userSubscriptionsView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
