package com.example.action.subject;

import com.example.action.Action;

import com.example.dao.SubjectDao;
import com.example.model.Subject;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SubjectCreateExecuteAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        if (cd == null || cd.length() != 3) {
            req.setAttribute("cdError", "科目コードは3文字で入力してください");
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            req.getRequestDispatcher("/WEB-INF/views/subject/subject_create.jsp").forward(req, res);
            return;
        }

        Subject subject = new Subject(teacher.getSchoolCd(), cd, name);
        SubjectDao dao = new SubjectDao();

        try {
            boolean saved = dao.save(subject);
            if (!saved) {
                // If save returns false or logic changes
                req.setAttribute("duplicateError", "科目コードが重複しています");
                req.setAttribute("cd", cd);
                req.setAttribute("name", name);
                req.getRequestDispatcher("/WEB-INF/views/subject/subject_create.jsp").forward(req, res);
                return;
            }
        } catch (Exception e) {
            req.setAttribute("duplicateError", "登録に失敗しました。(科目コードが重複しています)");
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            req.getRequestDispatcher("/WEB-INF/views/subject/subject_create.jsp").forward(req, res);
            return;
        }

        req.setAttribute("subject", subject);
        req.getRequestDispatcher("/WEB-INF/views/subject/subject_create_done.jsp").forward(req, res);
    }
}

