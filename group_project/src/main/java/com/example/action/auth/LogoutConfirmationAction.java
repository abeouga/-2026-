package com.example.action.auth;

import com.example.action.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutConfirmationAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        /** ログアウト確認画面を表示する。 */
        req.getRequestDispatcher("/WEB-INF/views/auth/logout_confirmation.jsp").forward(req, res);
    }
}
