package com.finalEAMProject.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finalEAMProject.dao.UserDao;
import com.finalEAMProject.dao.UserDaoJpa;
import com.finalEAMProject.model.User;


@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        userDao = new UserDaoJpa(emf.createEntityManager());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            List<User> users = userDao.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/WEB-INF/views/user-list.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            User user = userDao.getUserById(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/user-form.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            userDao.deleteUserById(id);
            response.sendRedirect(request.getContextPath() + "/user?action=list");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("save".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user;
            if (id == null) {
                user = new User(username, password);
            } else {
                user = userDao.getUserById(id);
                user.setUsername(username);
                user.setPassword(password);
            }
            userDao.saveUser(user);
            response.sendRedirect(request.getContextPath() + "/user?action=list");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        userDao.close();
    }
}
