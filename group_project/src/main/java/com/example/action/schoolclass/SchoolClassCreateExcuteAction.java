package com.example.action.schoolclass;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.action.Action;
import com.example.config.dao.SchoolClassDao;
import com.example.model.SchoolClass;
import com.example.model.Teacher;

public class SchoolClassCreateExcuteAction implements Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
            HttpSession session = req.getSession();

        Teacher user = (Teacher) session.getAttribute("user");

        String classCode = req.getParameter("classNum");
        // バリデーション
        if (classCode == null || !classCode.matches("^(10[1-9]|1[1-9][0-9]|20[1-9]|2[0-9][0-9])$")) {
            req.setAttribute("classNum", classCode);
            req.setAttribute("error", "クラスコードは101から299までしか登録できません");
            req.getRequestDispatcher("/WEB-INF/views/schoolclass/school_class_create.jsp").forward(req, res);
            return;
        }

        SchoolClassDao dao = new SchoolClassDao();
        SchoolClass schoolClass = dao.get(user.getSchoolCd(), classCode);
        if (schoolClass != null) {
            req.setAttribute("school_num", classCode);
            req.setAttribute("error", "クラスコードが重複しています");
            req.getRequestDispatcher("/WEB-INF/views/schoolclass/school_class_create.jsp").forward(req, res);
            return;
        }
        SchoolClass newSchoolClass = new SchoolClass();
        newSchoolClass.setClassNum(classCode);
        newSchoolClass.setSchoolCd(user.getSchoolCd());
        boolean line = dao.save(newSchoolClass);

        session.setAttribute("line", line);

        req.getRequestDispatcher("/WEB-INF/views/schoolclass/school_class_create_done.jsp").forward(req, res);
    }
}