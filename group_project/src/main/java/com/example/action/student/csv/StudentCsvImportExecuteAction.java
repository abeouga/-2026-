package com.example.action.student.csv;

import com.example.action.Action;
import com.example.config.dao.StudentDao;
import com.example.model.Student;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class StudentCsvImportExecuteAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        Part filePart = req.getPart("csvFile");
        if (filePart == null || filePart.getSize() == 0) {
            req.setAttribute("error", "ファイルが選択されていないか、空のファイルです。");
            req.getRequestDispatcher("/WEB-INF/views/student/student_csv_import.jsp").forward(req, res);
            return;
        }

        String fileName = filePart.getSubmittedFileName();
        if (fileName == null || !fileName.toLowerCase().endsWith(".csv")) {
            req.setAttribute("error", "CSV形式のファイルを選択してください。");
            req.getRequestDispatcher("/WEB-INF/views/student/student_csv_import.jsp").forward(req, res);
            return;
        }

        int successCount = 0;
        int skipCount = 0;
        StudentDao dao = new StudentDao();

        // 読み込み処理。文字コードはUTF-8で固定
        try (BufferedReader br = new BufferedReader(new InputStreamReader(filePart.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                // BOM（Byte Order Mark）の除去
                if (isFirstLine && line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                }

                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // ヘッダー行をスキップ
                }

                if (line.trim().isEmpty()) {
                    continue; // 空行スキップ
                }

                String[] columns = line.split(",");
                if (columns.length < 4) {
                    skipCount++;
                    continue;
                }

                try {
                    String name = columns[0].trim();
                    int entYear = Integer.parseInt(columns[1].trim());
                    String classNum = columns[2].trim();
                    boolean isAttend = Boolean.parseBoolean(columns[3].trim());

                    if (name.isEmpty() || classNum.isEmpty()) {
                        skipCount++;
                        continue;
                    }

                    // 学生番号の自動採番
                    String no = dao.generateStudentNo(teacher.getSchoolCd(), entYear, classNum);

                    Student student = new Student();
                    student.setSchoolCd(teacher.getSchoolCd());
                    student.setNo(no);
                    student.setName(name);
                    student.setEntYear(entYear);
                    student.setClassNum(classNum);
                    student.setIsAttend(isAttend);

                    if (dao.save(student)) {
                        successCount++;
                    } else {
                        skipCount++;
                    }
                } catch (NumberFormatException e) {
                    // 数値変換エラーなどはスキップ
                    skipCount++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "ファイルの読み込み中にエラーが発生しました: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/student/student_csv_import.jsp").forward(req, res);
            return;
        }

        req.setAttribute("message", "CSV読み込みが完了しました。成功: " + successCount + " 件、スキップ: " + skipCount + " 件");
        req.getRequestDispatcher("/WEB-INF/views/student/student_csv_import.jsp").forward(req, res);
    }
}
