package com.example.action.test;

import com.example.action.Action;
import com.example.config.dao.SchoolClassDao;
import com.example.config.dao.SubjectDao;
import com.example.model.SchoolClass;
import com.example.model.Subject;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestListAction implements Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            return;
        }

        String schoolCd = teacher.getSchoolCd();

        // 入学年度リストを生成
        List<Integer> entYearList = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear - 10; i <= currentYear + 1; i++) {
            entYearList.add(i);
        }

        // クラスリストを取得
        SchoolClassDao classDao = new SchoolClassDao();
        List<SchoolClass> classList = classDao.filter(schoolCd);

        // 科目リストを取得
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(schoolCd);

        // リクエスト属性にセット
        request.setAttribute("entYearList", entYearList);
        request.setAttribute("classList", classList);
        request.setAttribute("subjectList", subjectList);

        request.getRequestDispatcher("/WEB-INF/views/test/test_list.jsp").forward(request, response);
    }
}

