package com.example.action.student;

import com.example.action.Action;

import com.example.config.dao.StudentDao;
import com.example.model.Student;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class StudentUpdateAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        String no = req.getParameter("no");
        StudentDao dao = new StudentDao();
        Student student = dao.get(teacher.getSchoolCd(), no);
        
        req.setAttribute("student", student);

        // 入学年度リストを生成
        java.util.List<Integer> entYearSet = new java.util.ArrayList<>();
        int currentYear = java.time.LocalDate.now().getYear();
        for (int i = currentYear - 10; i <= currentYear + 1; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("ent_year_set", entYearSet);

        // クラス番号リストを取得
        com.example.config.dao.ClassNumDao classNumDao = new com.example.config.dao.ClassNumDao();
        java.util.List<String> classNumSet = classNumDao.filter(teacher.getSchoolCd());
        req.setAttribute("class_num_set", classNumSet);
        req.getRequestDispatcher("/WEB-INF/views/student/student_update.jsp").forward(req, res);
    }
}

