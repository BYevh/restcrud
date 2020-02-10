package ua.epam.crud.rest;

import com.google.gson.Gson;
import ua.epam.crud.model.Account;
import ua.epam.crud.model.Developer;
import ua.epam.crud.model.Skill;
import ua.epam.crud.service.AccountService;
import ua.epam.crud.service.DeveloperService;
import ua.epam.crud.service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

@WebServlet(name = "DeveloperServlet", urlPatterns = "/api/v1/developers")
public class DeveloperServlet extends HttpServlet {

    private DeveloperService developerService = new DeveloperService();
    private SkillService skillService = new SkillService();
    private AccountService accountService = new AccountService();
    private Gson gson = new Gson();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            ArrayList<Developer> developers = developerService.getAll();
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String id = req.getParameter("id");
            if (id == null) {
                for (Developer developer : developers) {
                    writer.println(gson.toJson(developer));
                }
            } else {
                Developer developerById = developerService.getById(Long.parseLong(id));
                writer.println(gson.toJson(developerById));
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
            developerService.create(getDeveloperFromRequest(req));
            Developer developerById = developerService.getById(Long.parseLong(req.getParameter("id")));
            writer.println(gson.toJson(developerById));
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            developerService.update(getDeveloperFromRequest(req));
            Developer developerById = developerService.getById(Long.parseLong(req.getParameter("id")));
            writer.println(gson.toJson(developerById));
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
            developerService.delete(id);
            writer.println(gson.toJson(developerService.getAll()));
            writer.flush();

        } catch (IOException e) {
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.sendError(400);
            writer.flush();
        }

    }

    private Developer getDeveloperFromRequest(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        HashSet<Skill> skills = skillService.createSetOfSkills(String.join(" ", req.getParameterValues("skill")));
        Long idStatus = Long.parseLong(req.getParameter("account"));
        Account account = accountService.createAccount(id, idStatus);
        return new Developer(id, name, skills, account);
    }

}
