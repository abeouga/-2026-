package com.example.action.student;

import com.example.action.Action;

import com.example.dao.StudentDao;
import com.example.model.Student;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class StudentUpdateExecuteAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        String entYearStr = req.getParameter("entYear");
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String classNum = req.getParameter("classNum");
        String isAttendStr = req.getParameter("isAttend");

        if (name == null || name.isEmpty()) {
            Student student = new Student(teacher.getSchoolCd(), no, name, Integer.parseInt(entYearStr), classNum, isAttendStr != null);
            req.setAttribute("student", student);
            req.setAttribute("nameError", "このフィールドを入力してください");

            // 入学年度リストを生成
            java.util.List<Integer> entYearSet = new java.util.ArrayList<>();
            int currentYear = java.time.LocalDate.now().getYear();
            for (int i = currentYear - 10; i <= currentYear + 1; i++) {
                entYearSet.add(i);
            }
            req.setAttribute("ent_year_set", entYearSet);

            // クラス番号リストを取得
            com.example.dao.ClassNumDao classNumDao = new com.example.dao.ClassNumDao();
            java.util.List<String> classNumSet = classNumDao.filter(teacher.getSchoolCd());
            req.setAttribute("class_num_set", classNumSet);

            req.getRequestDispatcher("/WEB-INF/views/student/student_update.jsp").forward(req, res);
            return;
        }

        Student student = new Student();
        student.setSchoolCd(teacher.getSchoolCd());
        student.setEntYear(Integer.parseInt(entYearStr));
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setIsAttend(isAttendStr != null);

        StudentDao dao = new StudentDao();
        dao.save(student);

        req.setAttribute("student", student);
        req.getRequestDispatcher("/WEB-INF/views/student/student_update_done.jsp").forward(req, res);
    }
}

