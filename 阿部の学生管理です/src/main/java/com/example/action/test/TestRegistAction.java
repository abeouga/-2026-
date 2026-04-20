package com.example.action.test;

import com.example.action.Action;

import com.example.dao.ClassNumDao;
import com.example.dao.SubjectDao;
import com.example.dao.TestDao;
import com.example.model.ClassNum;
import com.example.model.Subject;
import com.example.model.Teacher;
import com.example.model.Test;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class TestRegistAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        ClassNumDao classNumDao = new ClassNumDao();
        List<ClassNum> classNumList = classNumDao.filter(teacher.getSchoolCd());

        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(teacher.getSchoolCd());

        req.setAttribute("classNumList", classNumList);
        req.setAttribute("subjectList", subjectList);

        // 検索要求があるか確認
        String entYearStr = req.getParameter("fEntYear");
        String classNum = req.getParameter("fClassNum");
        String subjectCd = req.getParameter("fSubjectCd");
        String noStr = req.getParameter("fNo");

        // 検索ボタンから来た場合
        if (req.getParameter("search") != null) {
            boolean error = false;
            if (entYearStr == null || entYearStr.isEmpty() || entYearStr.equals("0") ||
                classNum == null || classNum.isEmpty() || classNum.equals("0") ||
                subjectCd == null || subjectCd.isEmpty() || subjectCd.equals("0") ||
                noStr == null || noStr.isEmpty() || noStr.equals("0")) {
                
                req.setAttribute("searchError", "入学年度とクラスと科目と回数を選択してください");
                error = true;
            }

            if (!error) {
                TestDao testDao = new TestDao();
                List<Test> testList = testDao.filter(teacher.getSchoolCd(), Integer.parseInt(entYearStr), classNum, subjectCd, Integer.parseInt(noStr));
                req.setAttribute("testList", testList);
            }
        }

        req.setAttribute("fEntYear", entYearStr);
        req.setAttribute("fClassNum", classNum);
        req.setAttribute("fSubjectCd", subjectCd);
        req.setAttribute("fNo", noStr);

        req.getRequestDispatcher("/WEB-INF/views/test/test_regist.jsp").forward(req, res);
    }
}

