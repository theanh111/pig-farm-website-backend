package com.school.core.repository;

import com.school.core.entity.Blog;
import com.school.core.entity.Farm;
import com.school.core.entity.Gallery;
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
public class GalleryRepository extends BaseRepository {
    public List<Gallery> getAllGallery() {
        List<Gallery> listGallery = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id, " +
                    "ten_anh, " +
                    "the_loai,  " +
                    "hinh_anh,  " +
                    "nguoi_nhap, " +
                    "ngay_nhap, " +
                    "trang_thai " +
                    "FROM tblgallery " +
                    "ORDER BY ngay_nhap DESC ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Gallery gallery = new Gallery();
                gallery.setId(resultSet.getInt("id"));
                gallery.setTen_anh(resultSet.getString("ten_anh"));
                gallery.setThe_loai(resultSet.getString("the_loai"));
                gallery.setHinh_anh(resultSet.getString("hinh_anh"));
                gallery.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                gallery.setNgay_nhap(resultSet.getString("ngay_nhap"));
                gallery.setTrang_thai(resultSet.getInt("trang_thai"));
                listGallery.add(gallery);
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getAllGallery!");
        }
        return listGallery;
    }


    public void saveGallery(Gallery gallery) {
        try {
            Date dateCurrent = new Date();
            String sqlQuery = "INSERT INTO tblgallery " +
                    "(ten_anh, the_loai, hinh_anh, nguoi_nhap, ngay_nhap) " +
                    "VALUES (?, ?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, gallery.getTen_anh());
            statement.setString(2, gallery.getThe_loai());
            statement.setString(3, gallery.getHinh_anh());
            statement.setString(4, gallery.getNguoi_nhap());
            statement.setTimestamp(5, new Timestamp(dateCurrent.getTime()));
            statement.execute();
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - saveGallery!");
        }
    }

    public void updateGallery(Gallery gallery) {
        try {
            String sqlQuery = "UPDATE tblgallery " +
                    "SET " +
                    "ten_anh = ?, " +
                    "hinh_anh = ?, " +
                    "the_loai = ? " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, gallery.getTen_anh());
            statement.setString(2, gallery.getHinh_anh());
            statement.setString(3, gallery.getThe_loai());
            statement.setInt(4, gallery.getId());
            statement.executeUpdate();
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - updateGallery!");
        }
    }
}
