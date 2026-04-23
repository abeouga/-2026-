//学生管理画面を表示するハンドラ。menu.jspからアクセスされる。
package com.example.action.student;

import com.example.action.Action;

import com.example.dao.StudentDao;
import com.example.model.Student;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class StudentAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        /** これも認証チェック。menu.jspを表示するハンドラの処理と同じ。ログイン情報をセッション内に保持してなかったら最初の画面を再描画する。 */
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        String entYearStr = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String isAttendStr = req.getParameter("isAttend");

        Integer entYear = null;
        if (entYearStr != null && !entYearStr.isEmpty() && !entYearStr.equals("0")) {
            entYear = Integer.parseInt(entYearStr);
        }

        Boolean isAttend = null;
        if (isAttendStr != null && !isAttendStr.isEmpty()) {
            isAttend = Boolean.parseBoolean(isAttendStr);
        } else {
            // 指定なしで検索
            isAttend = true;
        }
        /**
         * DBにアクセスする StudentDaoを生成し、ログイン中の教員が所属する学校の学生情報を取得してそれをキーにクエリを出してもらう。
         * 戻り値は studentsにリスト型で入るので、それをそれぞれ state(リクエスト)にattributeする。
         */
        StudentDao dao = new StudentDao();
        List<Student> students = dao.filter(teacher.getSchoolCd(), entYear, classNum, isAttend);

        req.setAttribute("students", students);
        req.setAttribute("fEntYear", entYearStr);
        req.setAttribute("fClassNum", classNum);
        req.setAttribute("fIsAttend", isAttendStr);

        req.getRequestDispatcher("/WEB-INF/views/student/student_list.jsp").forward(req, res);
    }
}
