//学生管理画面を表示するハンドラ。menu.jspからアクセスされる。
package com.example.action.student;

import com.example.action.Action;

import com.example.config.dao.StudentDao;
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

        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String isAttendStr = req.getParameter("f3");

        Integer entYear = null;
        if (entYearStr != null && !entYearStr.isEmpty() && !entYearStr.equals("0")) {
            entYear = Integer.parseInt(entYearStr);
        }

        Boolean isAttend = null;
        if (isAttendStr != null) {
            // チェックされている場合は在学中(true)のみ表示
            isAttend = true;
        } else {
            // チェックされていない場合はすべて表示するため null のまま
        }
        /**
         * DBにアクセスする StudentDaoを生成し、ログイン中の教員が所属する学校の学生情報を取得してそれをキーにクエリを出してもらう。
         * 戻り値は studentsにリスト型で入るので、それをそれぞれ state(リクエスト)にattributeする。
         */
        StudentDao dao = new StudentDao();
        List<Student> students = dao.filter(teacher.getSchoolCd(), entYear, classNum, isAttend);

        req.setAttribute("students", students);
        req.setAttribute("f1", entYearStr);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", isAttendStr);

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

        req.getRequestDispatcher("/WEB-INF/views/student/student_list.jsp").forward(req, res);
    }
}
