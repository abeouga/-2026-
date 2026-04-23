//ログイン画面を表示するハンドラ
package com.example.action.auth;

import com.example.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        /** getRequestDispatcherでログイン画面を表示し、fowardメソッドでリクエストとレスポンスを保持したまま画面を描画する。 */
        req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, res);
    }
}
