package ua.epam.crud.rest;

import com.google.gson.Gson;
import ua.epam.crud.model.Skill;
import ua.epam.crud.service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "SkillServlet", urlPatterns = "/api/v1/skills")
public class SkillServlet extends HttpServlet {

    private SkillService skillService = new SkillService();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<Skill> skills = skillService.getAll();
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String id = req.getParameter("id");
            if (id == null) {
                for (Skill skill : skills) {
                    writer.println(gson.toJson(skill));
                }
            } else {
                Skill skillById = skillService.getById(Long.parseLong(id));
                writer.println(gson.toJson(skillById));
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
            String name = req.getParameter("name");
            skillService.create(new Skill(id, name));
            Skill skillById = skillService.getById(Long.parseLong(req.getParameter("id")));
            writer.println(gson.toJson(skillById));
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
            Long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            skillService.update(new Skill(id, name));
            Skill skillById = skillService.getById(Long.parseLong(req.getParameter("id")));
            writer.println(gson.toJson(skillById));
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            Long id = Long.parseLong(req.getParameter("id"));
            skillService.delete(id);
            writer.println(gson.toJson(skillService.getAll()));
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
