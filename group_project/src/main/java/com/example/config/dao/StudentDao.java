package com.example.config.dao;

import com.example.model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends DaoBase {

    public List<Student> filter(String schoolCd, Integer entYear, String classNum, Boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM student WHERE school_cd = ?");
        List<Object> params = new ArrayList<>();
        params.add(schoolCd);

        if (entYear != null && entYear != 0) {
            sql.append(" AND ent_year = ?");
            params.add(entYear);
        }
        if (classNum != null && !classNum.isEmpty() && !classNum.equals("0")) {
            sql.append(" AND class_num = ?");
            params.add(classNum);
        }
        if (isAttend != null) {
            sql.append(" AND is_attend = ?");
            params.add(isAttend);
        }

        sql.append(" ORDER BY no ASC");

        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                st.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(new Student(
                            rs.getString("school_cd"),
                            rs.getString("no"),
                            rs.getString("name"),
                            rs.getInt("ent_year"),
                            rs.getString("class_num"),
                            rs.getBoolean("is_attend")));
                }
            }
        }
        return list;
    }

    public Student get(String schoolCd, String no) throws Exception {
        Student student = null;
        String sql = "SELECT * FROM student WHERE school_cd = ? AND no = ?";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, schoolCd);
            st.setString(2, no);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    student = new Student(
                            rs.getString("school_cd"),
                            rs.getString("no"),
                            rs.getString("name"),
                            rs.getInt("ent_year"),
                            rs.getString("class_num"),
                            rs.getBoolean("is_attend"));
                }
            }
        }
        return student;
    }

    public boolean save(Student student) throws Exception {
        String sqlSelect = "SELECT * FROM student WHERE school_cd = ? AND no = ?";
        try (Connection con = getConnection()) {
            boolean exists = false;
            try (PreparedStatement stSelect = con.prepareStatement(sqlSelect)) {
                stSelect.setString(1, student.getSchoolCd());
                stSelect.setString(2, student.getNo());
                try (ResultSet rs = stSelect.executeQuery()) {
                    if (rs.next()) {
                        exists = true;
                    }
                }
            }

            if (exists) {
                String sqlUpdate = "UPDATE student SET name = ?, ent_year = ?, class_num = ?, is_attend = ? WHERE school_cd = ? AND no = ?";
                try (PreparedStatement stUpdate = con.prepareStatement(sqlUpdate)) {
                    stUpdate.setString(1, student.getName());
                    stUpdate.setInt(2, student.getEntYear());
                    stUpdate.setString(3, student.getClassNum());
                    stUpdate.setBoolean(4, student.getIsAttend());
                    stUpdate.setString(5, student.getSchoolCd());
                    stUpdate.setString(6, student.getNo());
                    return stUpdate.executeUpdate() > 0;
                }
            } else {
                String sqlInsert = "INSERT INTO student (school_cd, no, name, ent_year, class_num, is_attend) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stInsert = con.prepareStatement(sqlInsert)) {
                    stInsert.setString(1, student.getSchoolCd());
                    stInsert.setString(2, student.getNo());
                    stInsert.setString(3, student.getName());
                    stInsert.setInt(4, student.getEntYear());
                    stInsert.setString(5, student.getClassNum());
                    stInsert.setBoolean(6, student.getIsAttend());
                    return stInsert.executeUpdate() > 0;
                }
            }
        }
    }

    public boolean delete(String schoolCd, String no) throws Exception {
        try (Connection con = getConnection()) {
            boolean result = false;
            con.setAutoCommit(false);
            try {
                String sqlTest = "DELETE FROM test WHERE school_cd = ? AND student_no = ?";
                try (PreparedStatement stTest = con.prepareStatement(sqlTest)) {
                    stTest.setString(1, schoolCd);
                    stTest.setString(2, no);
                    stTest.executeUpdate();
                }

                String sqlStudent = "DELETE FROM student WHERE school_cd = ? AND no = ?";
                try (PreparedStatement stStudent = con.prepareStatement(sqlStudent)) {
                    stStudent.setString(1, schoolCd);
                    stStudent.setString(2, no);
                    result = stStudent.executeUpdate() > 0;
                }
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
            return result;
        }
    }

    // クラス番号や入学年度をもとに学生番号をここでくみ上げます。
    public String generateStudentNo(String schoolCd, int entYear, String classNum) throws Exception {
        String yearStr = String.format("%02d", entYear % 100);
        String classDigit = classNum.substring(classNum.length() - 1);
        String prefix = yearStr + classDigit;
        String sql = "SELECT MAX(no) FROM student WHERE school_cd = ? AND no LIKE ?";

        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, schoolCd);
            st.setString(2, prefix + "%");
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String maxNo = rs.getString(1);
                    if (maxNo != null && maxNo.length() >= 6) {
                        try {
                            int nextSeq = Integer.parseInt(maxNo.substring(3)) + 1;
                            return prefix + String.format("%03d", nextSeq);
                        } catch (NumberFormatException e) {
                            // ignore and fallback
                        }
                    }
                }
            }
        }
        return prefix + "001";
    }
}
