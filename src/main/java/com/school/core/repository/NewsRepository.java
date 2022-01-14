package com.school.core.repository;

import com.school.core.entity.Blog;
import com.school.core.entity.Farm;
import com.school.core.entity.News;
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
public class NewsRepository extends BaseRepository {
    public List<News> getAllNews() {
        List<News> listBlog = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id, " +
                    "ma_tin_tuc, " +
                    "ten_tin_tuc,  " +
                    "noi_dung,  " +
                    "hinh_anh, " +
                    "nguoi_nhap, " +
                    "ngay_nhap " +
                    "FROM tblnews " +
                    "ORDER BY ngay_nhap DESC ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                News news = new News();
                String fixedHTMLCode = null;
                String fixedHTMLCode2 = null;
                String fixedHTMLCode3 = null;
                fixedHTMLCode = resultSet.getString("noi_dung").replaceAll("&lt;", "<");
                fixedHTMLCode2 = fixedHTMLCode.replaceAll("&gt;", ">");
                fixedHTMLCode3 = fixedHTMLCode2.replaceAll("text-align:none;", "text-align:center;");
                news.setId(resultSet.getInt("id"));
                news.setHinh_anh(resultSet.getString("hinh_anh"));
                news.setMa_tin_tuc(resultSet.getString("ma_tin_tuc"));
                news.setTen_tin_tuc(resultSet.getString("ten_tin_tuc"));
                news.setNoi_dung(fixedHTMLCode3);
                news.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                news.setNgay_nhap(resultSet.getString("ngay_nhap"));
                listBlog.add(news);
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getAllNews!");
        }
        return listBlog;
    }

    public Integer countBlog() {
        Integer numberOfBlog = null;
        try {
            String sqlQuery = "select count(id) as total from tblblog ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                numberOfBlog = resultSet.getInt("total");
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - countBlog!");
        }
        return numberOfBlog;
    }

    public News getNewsByNewsCode(String newsCode) {
        News news = new News();
        try {
            String sqlQuery = "SELECT " +
                    "id, " +
                    "ma_tin_tuc, " +
                    "ten_tin_tuc,  " +
                    "noi_dung,  " +
                    "nguoi_nhap, " +
                    "hinh_anh, " +
                    "ngay_nhap " +
                    "FROM tblnews " +
                    "WHERE ma_tin_tuc = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, newsCode);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                news.setId(resultSet.getInt("id"));
                news.setMa_tin_tuc(resultSet.getString("ma_tin_tuc"));
                news.setTen_tin_tuc(resultSet.getString("ten_tin_tuc"));
                news.setHinh_anh(resultSet.getString("hinh_anh"));
                String fixedHTMLCode = null;
                String fixedHTMLCode2 = null;
                String fixedHTMLCode3 = null;
                fixedHTMLCode = resultSet.getString("noi_dung").replaceAll("&lt;", "<");
                fixedHTMLCode2 = fixedHTMLCode.replaceAll("&gt;", ">");
                fixedHTMLCode3 = fixedHTMLCode2.replaceAll("text-align:none;", "text-align:center;");
                news.setNoi_dung(fixedHTMLCode3);
                news.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                news.setNgay_nhap(resultSet.getDate("ngay_nhap").toString());
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getNewsByNewsCode!");
        }
        return news;
    }


    public void saveNews(News news) {
        try {
            String fixedHTMLCode = null;
            String fixedHTMLCode2 = null;
            fixedHTMLCode = news.getNoi_dung().replaceAll("<", "&lt;");
            fixedHTMLCode2 = fixedHTMLCode.replaceAll(">", "&gt;");
            Date dateCurrent = new Date();
            String sqlQuery = "INSERT INTO tblnews " +
                    "(ma_tin_tuc, ten_tin_tuc, hinh_anh, noi_dung, nguoi_nhap, ngay_nhap) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, news.getMa_tin_tuc());
            statement.setString(2, news.getTen_tin_tuc());
            statement.setString(3, news.getHinh_anh());
            statement.setString(4, fixedHTMLCode2);
            statement.setString(5, news.getNguoi_nhap());
            statement.setTimestamp(6, new Timestamp(dateCurrent.getTime()));
            statement.execute();
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - saveNews!");
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
