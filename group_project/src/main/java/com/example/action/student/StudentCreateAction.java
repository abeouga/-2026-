package com.example.action.student;

import com.example.action.Action;

import com.example.config.dao.ClassNumDao;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentCreateAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        // 入学年度リストを生成
        List<Integer> entYearSet = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for (int i = year - 10; i <= year + 1; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("ent_year_set", entYearSet);

        // クラス番号リストを取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumSet = classNumDao.filter(teacher.getSchoolCd());
        req.setAttribute("class_num_set", classNumSet);

        req.getRequestDispatcher("/WEB-INF/views/student/student_create.jsp").forward(req, res);
    }
}

