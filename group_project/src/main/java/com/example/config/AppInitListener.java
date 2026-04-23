package com.example.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebListener
public class AppInitListener implements ServletContextListener {

    private static final String URL = "jdbc:h2:file:~/groupes;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("--- データベースの初期化を開始します ---");
        try {
            Class.forName("org.h2.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement()) {

                // テーブル作成
                String sqlSchool = "CREATE TABLE IF NOT EXISTS school (" +
                        "cd CHAR(3) PRIMARY KEY NOT NULL, " +
                        "name VARCHAR(20) DEFAULT NULL)";
                stmt.execute(sqlSchool);

                String sqlTeacher = "CREATE TABLE IF NOT EXISTS teacher (" +
                        "school_cd CHAR(3) NOT NULL, " +
                        "id VARCHAR(10) NOT NULL, " +
                        "password VARCHAR(30), " +
                        "name VARCHAR(10), " +
                        "PRIMARY KEY (school_cd, id), " +
                        "FOREIGN KEY (school_cd) REFERENCES school(cd))";
                stmt.execute(sqlTeacher);

                String sqlSubject = "CREATE TABLE IF NOT EXISTS subject (" +
                        "school_cd CHAR(3) NOT NULL, " +
                        "cd CHAR(3) NOT NULL, " +
                        "name VARCHAR(20), " +
                        "PRIMARY KEY (school_cd, cd), " +
                        "FOREIGN KEY (school_cd) REFERENCES school(cd))";
                stmt.execute(sqlSubject);

                String sqlClassNum = "CREATE TABLE IF NOT EXISTS class_num (" +
                        "school_cd CHAR(3) NOT NULL, " +
                        "class_num VARCHAR(5) NOT NULL, " +
                        "PRIMARY KEY (school_cd, class_num), " +
                        "FOREIGN KEY (school_cd) REFERENCES school(cd))";
                stmt.execute(sqlClassNum);

                String sqlStudent = "CREATE TABLE IF NOT EXISTS student (" +
                        "school_cd CHAR(3) NOT NULL, " +
                        "no VARCHAR(10) NOT NULL, " +
                        "name VARCHAR(10), " +
                        "ent_year INTEGER, " +
                        "class_num VARCHAR(5), " +
                        "is_attend BOOLEAN, " +
                        "PRIMARY KEY (school_cd, no), " +
                        "FOREIGN KEY (school_cd) REFERENCES school(cd), " +
                        "FOREIGN KEY (school_cd, class_num) REFERENCES class_num(school_cd, class_num))";
                stmt.execute(sqlStudent);

                String sqlTest = "CREATE TABLE IF NOT EXISTS test (" +
                        "school_cd CHAR(3) NOT NULL, " +
                        "student_no VARCHAR(10) NOT NULL, " +
                        "subject_cd CHAR(3) NOT NULL, " +
                        "no INTEGER NOT NULL, " +
                        "class_num VARCHAR(5), " +
                        "point INTEGER, " +
                        "PRIMARY KEY (school_cd, student_no, subject_cd, no), " +
                        "FOREIGN KEY (school_cd) REFERENCES school(cd), " +
                        "FOREIGN KEY (school_cd, student_no) REFERENCES student(school_cd, no), " +
                        "FOREIGN KEY (school_cd, subject_cd) REFERENCES subject(school_cd, cd))";
                stmt.execute(sqlTest);
                
                // seedデータ挿入
                stmt.execute("MERGE INTO school KEY (cd) VALUES ('001', 'テスト校')");
                stmt.execute("MERGE INTO teacher KEY (school_cd, id) VALUES ('001', 'admin', 'password', '管理者')");
                // クラスシードデータ
                stmt.execute("MERGE INTO class_num KEY (school_cd, class_num) VALUES ('001', '101')");
                stmt.execute("MERGE INTO class_num KEY (school_cd, class_num) VALUES ('001', '102')");
                stmt.execute("MERGE INTO class_num KEY (school_cd, class_num) VALUES ('001', '103')");
                // 科目シードデータ
                stmt.execute("MERGE INTO subject KEY (school_cd, cd) VALUES ('001', 'A01', '国語')");
                stmt.execute("MERGE INTO subject KEY (school_cd, cd) VALUES ('001', 'A02', '算数')");
                stmt.execute("MERGE INTO subject KEY (school_cd, cd) VALUES ('001', 'A03', '英語')");

                System.out.println("--- データベースの初期化・シード構築が完了しました ---");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
