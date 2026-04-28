package com.example.config.dao;

import com.example.model.ClassNum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassNumDao extends DaoBase {

    public List<String> filter(String schoolCd) throws Exception {
        List<String> list = new ArrayList<>();
        String sql = "SELECT class_num FROM class_num WHERE school_cd = ? ORDER BY class_num";
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            
            st.setString(1, schoolCd);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("class_num"));
                }
            }
        }
        return list;
    }
}
