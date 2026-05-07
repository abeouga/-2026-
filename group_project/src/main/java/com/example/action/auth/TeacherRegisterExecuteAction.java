package com.example.action.auth;

import com.example.action.Action;
import com.example.config.dao.TeacherDao;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TeacherRegisterExecuteAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String schoolCd = req.getParameter("schoolCd");
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        if (schoolCd == null || id == null || password == null || name == null) {
            req.setAttribute("error", "全ての項目を入力してください。");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, res);
            return;
        }

        TeacherDao dao = new TeacherDao();
        
        // 既に同じIDが存在するかチェック
        if (dao.getById(id) != null) {
            req.setAttribute("error", "このユーザーIDは既に登録されています。");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, res);
            return;
        }

        // デフォルトでviewer権限を付与
        Teacher newTeacher = new Teacher(schoolCd, id, password, name, "viewer");
        dao.save(newTeacher);

        req.setAttribute("message", "アカウントが作成されました。ログインしてください。");
        req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, res);
    }
}
