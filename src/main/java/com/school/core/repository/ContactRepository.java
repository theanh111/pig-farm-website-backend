package com.school.core.repository;

import com.school.core.entity.Blog;
import com.school.core.entity.Contact;
import com.school.core.entity.Farm;
import com.school.core.response.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ContactRepository extends BaseRepository {
    public List<Contact> getAllContact() {
        List<Contact> listContact = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id, " +
                    "ten_lien_he, " +
                    "dia_chi,  " +
                    "sdt,  " +
                    "email, " +
                    "nguoi_nhap, " +
                    "ngay_nhap, " +
                    "trang_thai " +
                    "FROM tblcontact " +
                    "ORDER BY ngay_nhap DESC ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setTen_lien_he(resultSet.getString("ten_lien_he"));
                contact.setDia_chi(resultSet.getString("dia_chi"));
                contact.setSdt(resultSet.getString("sdt"));
                contact.setEmail(resultSet.getString("email"));
                contact.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                contact.setNgay_nhap(resultSet.getString("ngay_nhap"));
                listContact.add(contact);
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getAllContact!");
        }
        return listContact;
    }

    public void saveContact(Contact contact) {
        try {
            Date dateCurrent = new Date();
            String sqlQuery = "INSERT INTO tblcontact " +
                    "(ten_lien_he, dia_chi, sdt, email, nguoi_nhap, ngay_nhap) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, contact.getTen_lien_he());
            statement.setString(2, contact.getDia_chi());
            statement.setString(3, contact.getSdt());
            statement.setString(4, contact.getEmail());
            statement.setString(5, contact.getNguoi_nhap());
            statement.setTimestamp(6, new Timestamp(dateCurrent.getTime()));
            statement.execute();
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - saveContact!");
        }
    }

    public void updateFarm(Farm farm) {
        try {
            String sqlQuery = "UPDATE tblfarm " +
                    "SET " +
                    "ten_trang_trai = ?, " +
                    "hinh_anh = ? " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, farm.getTen_trang_trai());
            statement.setString(2, farm.getHinh_anh());
            statement.setInt(3, farm.getId());
            statement.executeUpdate();
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - updateFarm!");
        }
    }
}
