//認証ロジックのLoginExecuteAction.javaでログインが成功した場合に遷移するメニュー画面を表示するハンドラ。
package com.example.action.auth;

import com.example.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MenuAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        /**
         * リクエストからセッションを取り出して、セッションに保存されている userが nullならログイン画面に遷移する。
         * このif内で使用されているメソッドは LoginExecuteActionの認証失敗時の処理と同じである。
         */
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/auth/menu.jsp").forward(req, res);
    }
}
