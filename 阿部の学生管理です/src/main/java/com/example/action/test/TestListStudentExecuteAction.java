package com.example.action.test;

import com.example.action.Action;

import com.example.dao.TestDao;
import com.example.model.Teacher;
import com.example.model.Test;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class TestListStudentExecuteAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        String studentNo = req.getParameter("fStudentNo");

        if (studentNo == null || studentNo.isEmpty()) {
            req.setAttribute("studentNoError", "このフィールドを入力してください");
            req.getRequestDispatcher("/testList.action").forward(req, res);
            return;
        }

        TestDao dao = new TestDao();
        List<Test> testList = dao.filterByStudent(teacher.getSchoolCd(), studentNo);

        if (testList.isEmpty()) {
            req.setAttribute("notFoundMsg", "学生情報が存在しませんでした");
        }

        req.setAttribute("testList", testList);
        req.setAttribute("fStudentNo", studentNo);

        req.getRequestDispatcher("/WEB-INF/views/test/test_list_student.jsp").forward(req, res);
    }
}

