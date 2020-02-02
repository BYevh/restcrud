package ua.epam.crud.rest;

import com.google.gson.Gson;
import ua.epam.crud.model.Account;
import ua.epam.crud.model.Skill;
import ua.epam.crud.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "SkillServlet", urlPatterns = "/api/v1/accounts")
public class AccountServlet extends HttpServlet {

    AccountService accountService = new AccountService();
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<Account> accounts = accountService.getAll();
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String id = req.getParameter("id");
            if (id == null) {
                for (Account account : accounts) {
                    writer.println(gson.toJson(account));
                }
            } else {
                Account accountById = accountService.getById(Long.parseLong(id));
                writer.println(gson.toJson(accountById));
            }
            writer.flush();
        } catch (IOException e) {
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.sendError(400);
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            Long id = Long.parseLong(req.getParameter("id"));
            Long id_status = Long.parseLong(req.getParameter("id_status"));
            accountService.create(accountService.createAccount(id, id_status));
            Account accountById = accountService.getById(Long.parseLong(req.getParameter("id")));
            writer.println(gson.toJson(accountById));
            writer.flush();

        } catch (IOException e) {
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.sendError(400);
            writer.flush();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            Long id = Long.parseLong(req.getParameter("id"));
            Long id_status = Long.parseLong(req.getParameter("id_status"));
            accountService.update(accountService.createAccount(id, id_status));
            Account accountById = accountService.getById(Long.parseLong(req.getParameter("id")));
            writer.println(gson.toJson(accountById));
            writer.flush();

        } catch (IOException e) {
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.sendError(400);
            writer.flush();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            Long id = Long.parseLong(req.getParameter("id"));
            accountService.delete(id);
            writer.println(gson.toJson(accountService.getAll()));
            writer.flush();

        } catch (IOException e) {
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.sendError(400);
            writer.flush();
        }
    }
}
