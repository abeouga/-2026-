package com.example.action.schoolclass;

import com.example.config.dao.SchoolClassDao;
import com.example.action.Action;
import com.example.model.Teacher;
import com.example.model.SchoolClass;
import jakarta.servlet.http.*;

public class SchoolClassDeleteExecuteAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        
        Teacher user = (Teacher) session.getAttribute("user");
        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }
        
        String schoolCode = req.getParameter("class_num");

        SchoolClassDao dao = new SchoolClassDao();
        SchoolClass subject = dao.get(user.getSchoolCd(), schoolCode);
        boolean line = dao.delete(subject);

        session.setAttribute("line", line);

        req.getRequestDispatcher("/WEB-INF/views/schoolclass/school_class_delete_done.jsp").forward(req, res);
    }
}