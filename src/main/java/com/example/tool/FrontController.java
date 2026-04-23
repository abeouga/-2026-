package tool;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import tool.Action;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet{
    public void doGet(
        HttpServletRequest request,HttpServletResponse responce
    )throws ServletException,IOException{
        try{
            //パスを取得
            String path = request.getServletPath().substring(1);
            //ファイル名を取得しクラス名に変換
            String name =path.replace(".action", "Action").replace("/", ".");


            System.out.println("★ servlet path ->" + request.getServletPath());
            System.out.println("★ class name ->" + name);

            //アクションクラスのインスタンスを返却
            Action action = (Action)Class.forName(name).getDeclaredConstructor().newInstance();

            //変移先URLを取得
            //action.execute(request, responce);
            String url = action.execute(request, responce);
            request.getRequestDispatcher(url).forward(request,responce);
        }catch(Exception e){
            e.printStackTrace();
            //エラーメッセージへリダイレクト
            request.getRequestDispatcher("/error.jsp").forward(request,responce);
        }
    }
    public void doPost(
        HttpServletRequest request,HttpServletResponse responce
    )throws ServletException,IOException{
        doGet(request, responce);
    }
}