//ログイン処理を実行するハンドラ。login.jspのログインボタンで呼び出される。
package com.example.action.auth;

import com.example.action.Action;

import com.example.config.dao.TeacherDao;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginExecuteAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        /** リクエストパラメータを受け取る */
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        /** TeacherDaoのオブジェクトを作成し、getメソッドでログイン情報を取得する。 */
        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.get(id, password);
        /**
         * ログイン情報が取得できたら、セッションを開き、セッションにユーザー情報を userという変数名で保存し、メニュー画面に遷移する。
         * 最後にプロジェクトのルートパスと遷移させたいメニューのパスを結合してsendRedirectにより自動画面遷移する。
         */
        if (teacher != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", teacher);
            res.sendRedirect(req.getContextPath() + "/menu.action");
        } else {
            /** ログイン情報が取得できなかった場合、エラーメッセージをstateに保存し、ログイン画面に遷移する。 */
            req.setAttribute("errorMessage", "IDまたはパスワードが確認できませんでした。");
            req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, res);
        }
    }
}
