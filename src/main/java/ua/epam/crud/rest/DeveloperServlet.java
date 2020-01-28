package ua.epam.crud.rest;

import com.google.gson.Gson;
import ua.epam.crud.model.Developer;
import ua.epam.crud.repository.jdbc.JdbcDeveloperRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "DeveloperServlet", urlPatterns = "/api/v1/developers")
public class DeveloperServlet extends HttpServlet {

    private JdbcDeveloperRepository jdbcDeveloperRepository = new JdbcDeveloperRepository();
    private Gson gson = new Gson();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            ArrayList<Developer> developers = jdbcDeveloperRepository.getAll();
            String developerJsonString = this.gson.toJson(developers);

            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            writer.println(developerJsonString);
            writer.flush();
            super.doGet(req, resp);
        } catch (ServletException | IOException e) {
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.sendError(500);
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
