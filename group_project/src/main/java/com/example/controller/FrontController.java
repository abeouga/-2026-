package com.example.controller;

import com.example.action.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "*.action" }) // 末尾にactionの付くリクエストはこのハンドラに集められる。
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            /**
             * 1. リクエストパラメータに含まれるURLを getServletPathで取り出し、actionのついている末尾のファイル名を取り出す。
             * subStringはURLに含まれるスラッシュを取り除く。
             */
            String path = req.getServletPath().substring(1);
            /** 2. actionの拡張子を取り除く。(.actionを空白に置き換える。) */
            String name = path.replace(".action", "");

            /**
             * 3. actionの先頭の文字列をパッケージ名として取り出す。(student,subject,test,authのいずれか)
             * startsWithで文字列が特定の文字で始まっているかを判定する。 trueならpackageNameにその文字列を追加する。
             */
            String packageName = "";
            if (name.startsWith("student"))
                packageName = "student.";
            else if (name.startsWith("subject"))
                packageName = "subject.";
            else if (name.startsWith("test"))
                packageName = "test.";
            else
                packageName = "auth.";

            /**
             * 4. 最終的にここで結合し、パスを作成する。
             * name.substring(0, 1).toUpperCase()で文字列の先頭の文字列を大文字にする。
             * name.substring(1)で文字列の2文字目以降を取り出す。
             * 例: "studentList" -> "com.example.action.student.StudentListAction"
             */
            String className = "com.example.action." + packageName + name.substring(0, 1).toUpperCase()
                    + name.substring(1) + "Action";
            /**
             * 5. クラスを生成し、executeメソッドを実行することで、各アクションクラスの処理を呼び出す。
             */
            Action action = (Action) Class.forName(className).getDeclaredConstructor().newInstance();
            action.execute(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/auth/error.jsp").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
