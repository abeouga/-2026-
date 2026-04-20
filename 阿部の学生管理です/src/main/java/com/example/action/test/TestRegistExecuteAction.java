package com.example.action.test;

import com.example.action.Action;

import com.example.dao.TestDao;
import com.example.model.Teacher;
import com.example.model.Test;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TestRegistExecuteAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        String[] studentNos = req.getParameterValues("studentNo_array");
        String subjectCd = req.getParameter("subjectCd");
        String noStr = req.getParameter("no");
        String classNum = req.getParameter("classNum");

        if (studentNos == null) {
            req.getRequestDispatcher("/WEB-INF/views/test/test_regist.jsp").forward(req, res);
            return;
        }

        Integer no = Integer.parseInt(noStr);
        TestDao dao = new TestDao();
        boolean hasError = false;

        // 簡単な実装として、エラーがあればそこで保存を中断して戻る仕様
        for (String studentNo : studentNos) {
            String pointStr = req.getParameter("point_" + studentNo);
            if (pointStr != null && !pointStr.isEmpty()) {
                try {
                    int point = Integer.parseInt(pointStr);
                    if (point < 0 || point > 100) {
                        req.setAttribute("pointError_" + studentNo, "0~100の範囲で入力してください");
                        hasError = true;
                    }
                } catch(NumberFormatException e) {
                    req.setAttribute("pointError_" + studentNo, "0~100の範囲で入力してください");
                    hasError = true;
                }
            } else {
                req.setAttribute("pointError_" + studentNo, "0~100の範囲で入力してください");
                hasError = true;
            }
        }

        if (hasError) {
            // エラーがあった場合はフォワードして表示を維持（TestRegistActionに再ディスパッチするか、リストを再構築してtest_regist.jspへ）
            req.setAttribute("inputError", "入力内容にエラーがあります。");
            // 簡単化のため、TestRegistActionにフォワードして再検索させる
            req.getRequestDispatcher("/testRegist.action?search=true").forward(req, res);
            return;
        }

        // 保存（1つのトランザクションで行うのが望ましいが、今回は順次保存）
        for (String studentNo : studentNos) {
            int point = Integer.parseInt(req.getParameter("point_" + studentNo));
            Test test = new Test(teacher.getSchoolCd(), studentNo, subjectCd, no, classNum, point);
            dao.save(test);
        }

        req.getRequestDispatcher("/WEB-INF/views/test/test_regist_done.jsp").forward(req, res);
    }
}

