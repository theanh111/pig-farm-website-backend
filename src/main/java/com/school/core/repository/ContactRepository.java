package com.school.core.repository;

import com.school.core.entity.Contact;
import com.school.core.response.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger log = LoggerFactory.getLogger(ContactRepository.class);

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
                    "nguoi_cap_nhat, " +
                    "ngay_cap_nhat, " +
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
                contact.setNguoi_cap_nhat(resultSet.getString("nguoi_cap_nhat"));
                contact.setNgay_cap_nhat(resultSet.getString("ngay_cap_nhat"));
                listContact.add(contact);
            }
            log.info("SQL: " + sqlQuery);
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
                    "(ten_lien_he, dia_chi, sdt, email, nguoi_nhap, ngay_nhap, nguoi_cap_nhat, ngay_cap_nhat) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, contact.getTen_lien_he());
            statement.setString(2, contact.getDia_chi());
            statement.setString(3, contact.getSdt());
            statement.setString(4, contact.getEmail());
            statement.setString(5, contact.getNguoi_nhap());
            statement.setTimestamp(6, new Timestamp(dateCurrent.getTime()));
            statement.setString(7, contact.getNguoi_cap_nhat());
            statement.setTimestamp(8, new Timestamp(dateCurrent.getTime()));
            statement.execute();
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
            log.info(("SAVE CONTACT: " + contact.getTen_lien_he()));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - saveContact!");
        }
    }

    public void updateContact(Contact contact) {
        try {
            Date dateCurrent = new Date();
            String sqlQuery = "UPDATE tblcontact " +
                    "SET " +
                    "ten_lien_he = ?, " +
                    "dia_chi = ?, " +
                    "sdt = ?, " +
                    "email = ?, " +
                    "nguoi_cap_nhat = ?, " +
                    "ngay_cap_nhat = ? " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, contact.getTen_lien_he());
            statement.setString(2, contact.getDia_chi());
            statement.setString(3, contact.getSdt());
            statement.setString(4, contact.getEmail());
            statement.setString(5, contact.getNguoi_cap_nhat());
            statement.setTimestamp(6, new Timestamp(dateCurrent.getTime()));
            statement.setInt(7, contact.getId());
            statement.executeUpdate();
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
            log.info("UPDATE CONTACT: " + contact.getTen_lien_he());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - updateContact!");
        }
    }

    public void deleteContact(Contact contact) {
        try {
            String sqlQuery = "DELETE FROM tblcontact WHERE " +
                    "id = ? " ;
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, contact.getId());
            statement.executeUpdate();
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
            log.info("DELETE CONTACT: " + contact.getTen_lien_he());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - deleteContact!");
        }
    }
}
