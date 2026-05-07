package com.example.action.auth;

import com.example.action.Action;
import com.example.config.dao.TeacherDao;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class TeacherRoleListAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher user = (Teacher) session.getAttribute("user");

        if (user == null || !"admin".equals(user.getRole())) {
            req.setAttribute("error", "権限管理画面には管理者(admin)のみアクセスできます。");
            req.getRequestDispatcher("/WEB-INF/views/auth/error.jsp").forward(req, res);
            return;
        }

        TeacherDao dao = new TeacherDao();
        List<Teacher> list = dao.getAll(user.getSchoolCd());

        req.setAttribute("teacherList", list);
        req.getRequestDispatcher("/WEB-INF/views/auth/teacher_role_list.jsp").forward(req, res);
    }
}
