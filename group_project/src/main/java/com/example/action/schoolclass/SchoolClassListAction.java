package com.example.action.schoolclass;

import com.example.action.Action;

import com.example.config.dao.SchoolClassDao;
import com.example.model.SchoolClass;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class SchoolClassListAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        /** ログイン確認 */
        HttpSession session = req.getSession();
        Teacher user = (Teacher) session.getAttribute("user");
        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        SchoolClassDao dao = new SchoolClassDao();
        List<SchoolClass> list = dao.filter(user.getSchoolCd());

        java.util.List<SchoolClass> list1 = new java.util.ArrayList<>();
        java.util.List<SchoolClass> list2 = new java.util.ArrayList<>();
        
        for (SchoolClass c : list) {
            if (c.getClassNum() != null && c.getClassNum().startsWith("1")) {
                list1.add(c);
            } else if (c.getClassNum() != null && c.getClassNum().startsWith("2")) {
                list2.add(c);
            }
        }

        session.setAttribute("list1", list1);
        session.setAttribute("list2", list2);

        req.getRequestDispatcher("/WEB-INF/views/schoolclass/school_class_list.jsp").forward(req, res);
    }
}