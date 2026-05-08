package com.example.action.schoolclass;

import com.example.config.dao.SchoolClassDao;
import com.example.model.Teacher;
import com.example.model.SchoolClass;
import com.example.action.Action;
import jakarta.servlet.http.*;

public class SchoolClassDeleteAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        Teacher user = (Teacher)session.getAttribute("user");

        String code = req.getParameter("class_num");

        SchoolClassDao dao = new SchoolClassDao();
        SchoolClass schoolClass = dao.get(user.getSchoolCd(), code);

        req.setAttribute("schoolClass", schoolClass);

        req.getRequestDispatcher("/WEB-INF/views/schoolclass/school_class_delete.jsp").forward(req, res);
    }
}