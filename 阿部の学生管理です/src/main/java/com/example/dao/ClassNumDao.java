package com.example.dao;

import com.example.model.ClassNum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassNumDao extends DaoBase {

    public List<ClassNum> filter(String schoolCd) throws Exception {
        List<ClassNum> list = new ArrayList<>();
        String sql = "SELECT * FROM class_num WHERE school_cd = ? ORDER BY class_num";
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
             
            st.setString(1, schoolCd);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(new ClassNum(
                        rs.getString("school_cd"),
                        rs.getString("class_num")
                    ));
                }
            }
        }
        return list;
    }
}
