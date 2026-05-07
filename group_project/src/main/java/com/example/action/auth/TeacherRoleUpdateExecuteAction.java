package com.example.action.auth;

import com.example.action.Action;
import com.example.config.dao.TeacherDao;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TeacherRoleUpdateExecuteAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher user = (Teacher) session.getAttribute("user");

        if (user == null || !"admin".equals(user.getRole())) {
            req.setAttribute("error", "権限管理画面には管理者(admin)のみアクセスできます。");
            req.getRequestDispatcher("/WEB-INF/views/auth/error.jsp").forward(req, res);
            return;
        }

        String targetId = req.getParameter("id");
        String newRole = req.getParameter("role");

        if (targetId != null && newRole != null) {
            TeacherDao dao = new TeacherDao();
            Teacher target = dao.getById(targetId);
            if (target != null && target.getSchoolCd().equals(user.getSchoolCd())) {
                target.setRole(newRole);
                dao.save(target);
            }
        }

        res.sendRedirect("teacherRoleList.action");
    }
}
