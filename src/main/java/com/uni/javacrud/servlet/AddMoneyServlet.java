package com.uni.javacrud.servlet;

import com.uni.javacrud.beans.User;
import com.uni.javacrud.dao.UserDao;
import com.uni.javacrud.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "AddMoneyServlet", value = "/addmoney")
public class AddMoneyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/AddMoneyView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User loginedUser = MyUtils.getLoginedUser(session);

        if (loginedUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.setAttribute("user", loginedUser);

        String moneyStr =  request.getParameter("money");

        boolean hasError = false;
        String errorString = null;

        Connection conn = MyUtils.getStoredConnection(request);
        UserDao userDao = new UserDao(conn);
        User user = null;

        float money = 0f;

        try {
            money = Float.parseFloat(moneyStr);
        } catch (Exception e) {
            hasError = true;
            errorString = e.getMessage();
        }

        if (money < 0) {
            hasError = true;
            errorString = "Amount of money cannot be lower than 0";
        } else {
            try {
                userDao.addMoney(loginedUser.getId(), loginedUser.getMoney(), money);
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        if (hasError) {
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", loginedUser);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/AddMoneyView.jsp");

            dispatcher.forward(request, response);
        } else {
            try {
                user = userDao.findUserById(loginedUser.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
}
