package com.school.core.repository;

import com.school.core.entity.*;
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
public class NewsRepository extends BaseRepository {
    private final Logger log = LoggerFactory.getLogger(NewsRepository.class);

    public List<News> getAllNews() {
        List<News> listBlog = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id, " +
                    "ma_tin_tuc, " +
                    "ten_tin_tuc,  " +
                    "noi_dung,  " +
                    "hinh_anh, " +
                    "hinh_anh_1, " +
                    "hinh_anh_2, " +
                    "nguoi_nhap, " +
                    "ngay_nhap, " +
                    "nguoi_cap_nhat, " +
                    "ngay_cap_nhat " +
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
                news.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                news.setHinh_anh_2(resultSet.getString("hinh_anh_2"));
                news.setMa_tin_tuc(resultSet.getString("ma_tin_tuc"));
                news.setTen_tin_tuc(resultSet.getString("ten_tin_tuc"));
                news.setNoi_dung(fixedHTMLCode3);
                news.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                news.setNgay_nhap(resultSet.getString("ngay_nhap"));
                news.setNguoi_cap_nhat(resultSet.getString("nguoi_cap_nhat"));
                news.setNgay_cap_nhat(resultSet.getString("ngay_cap_nhat"));
                listBlog.add(news);
            }
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getAllNews!");
        }
        return listBlog;
    }

    public List<News> getAllNewsByFilter(News newsFilter) {
        List<News> listNewsByFilter = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id," +
                    "ma_tin_tuc, " +
                    "ten_tin_tuc, " +
                    "noi_dung, " +
                    "hinh_anh, " +
                    "ngay_nhap " +
                    "FROM tblnews " +
                    "WHERE trang_thai = 1 ";

            sqlQuery += "AND ( " +
                    "ma_tin_tuc LIKE ? " +
                    "OR ten_tin_tuc LIKE ? " +
                    "OR noi_dung LIKE ? " +
                    "OR hinh_anh LIKE ? " +
                    ") ";

            sqlQuery += " ORDER BY ngay_nhap DESC";

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, "%" + newsFilter.getTu_khoa() + "%");
            statement.setString(2, "%" + newsFilter.getTu_khoa() + "%");
            statement.setString(3, "%" + newsFilter.getTu_khoa() + "%");
            statement.setString(4, "%" + newsFilter.getTu_khoa() + "%");

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
                news.setMa_tin_tuc(resultSet.getString("ma_tin_tuc"));
                news.setTen_tin_tuc(resultSet.getString("ten_tin_tuc"));
                news.setNoi_dung(fixedHTMLCode3);
                news.setHinh_anh(resultSet.getString("hinh_anh"));
                news.setNgay_nhap(resultSet.getString("ngay_nhap"));

                listNewsByFilter.add(news);
            }
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getAllNewsByFilter!");
        }
        return listNewsByFilter;
    }

    public Integer countNews() {
        Integer numberOfNews = null;
        try {
            String sqlQuery = "select count(id) as total from tblnews ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                numberOfNews = resultSet.getInt("total");
            }
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - countNews!");
        }
        return numberOfNews;
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
                    "hinh_anh_1, " +
                    "hinh_anh_2, " +
                    "ngay_nhap, " +
                    "nguoi_cap_nhat, " +
                    "ngay_cap_nhat " +
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
                news.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                news.setHinh_anh_2(resultSet.getString("hinh_anh_2"));
                String fixedHTMLCode = null;
                String fixedHTMLCode2 = null;
                String fixedHTMLCode3 = null;
                fixedHTMLCode = resultSet.getString("noi_dung").replaceAll("&lt;", "<");
                fixedHTMLCode2 = fixedHTMLCode.replaceAll("&gt;", ">");
                fixedHTMLCode3 = fixedHTMLCode2.replaceAll("text-align:none;", "text-align:center;");
                news.setNoi_dung(fixedHTMLCode3);
                news.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                news.setNgay_nhap(resultSet.getString("ngay_nhap"));
                news.setNguoi_cap_nhat(resultSet.getString("nguoi_cap_nhat"));
                news.setNgay_cap_nhat(resultSet.getString("ngay_cap_nhat"));
            }
            log.info("SQL: " + sqlQuery);
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
            String fixedHTMLCode3 = null;
            String fixedHTMLCode4 = null;
            fixedHTMLCode = news.getNoi_dung().replaceAll("<", "&lt;");
            fixedHTMLCode2 = fixedHTMLCode.replaceAll(">", "&gt;");
            fixedHTMLCode3 = fixedHTMLCode2.replaceAll("alt=\"undefined\"", "alt=\"hinh-anh-tin-tuc\"");
            fixedHTMLCode4 = fixedHTMLCode3.replaceAll("img", "img class=\"responsive-screen-size\"");
            Date dateCurrent = new Date();
            String sqlQuery = "INSERT INTO tblnews " +
                    "(ma_tin_tuc, ten_tin_tuc, hinh_anh, hinh_anh_1, hinh_anh_2, noi_dung, nguoi_nhap, ngay_nhap, nguoi_cap_nhat, ngay_cap_nhat) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, news.getMa_tin_tuc());
            statement.setString(2, news.getTen_tin_tuc());
            statement.setString(3, news.getHinh_anh());
            statement.setString(4, news.getHinh_anh_1());
            statement.setString(5, news.getHinh_anh_2());
            statement.setString(6, fixedHTMLCode4);
            statement.setString(7, news.getNguoi_nhap());
            statement.setTimestamp(8, new Timestamp(dateCurrent.getTime()));
            statement.setString(9, news.getNguoi_cap_nhat());
            statement.setTimestamp(10, new Timestamp(dateCurrent.getTime()));
            statement.execute();
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
            log.info("SAVE NEWS: " + news.getMa_tin_tuc());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - saveNews!");
        }
    }

    public void updateNews(News news) {
        try {
            Date dateCurrent = new Date();
            String fixedHTMLCode = null;
            String fixedHTMLCode2 = null;
            String fixedHTMLCode3 = null;
            String fixedHTMLCode4 = null;
            fixedHTMLCode = news.getNoi_dung().replaceAll("<", "&lt;");
            fixedHTMLCode2 = fixedHTMLCode.replaceAll(">", "&gt;");
            fixedHTMLCode3 = fixedHTMLCode2.replaceAll("alt=\"undefined\"", "alt=\"hinh-anh-tin-tuc\"");
            fixedHTMLCode4 = fixedHTMLCode3.replaceAll("img", "img class=\"responsive-screen-size\"");
            String sqlQuery = "UPDATE tblnews " +
                    "SET " +
                    "ma_tin_tuc = ?, " +
                    "ten_tin_tuc = ?, " +
                    "noi_dung = ?, " +
                    "hinh_anh = ?, " +
                    "hinh_anh_1 = ?, " +
                    "hinh_anh_2 = ?, " +
                    "nguoi_cap_nhat = ?, " +
                    "ngay_cap_nhat = ? " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, news.getMa_tin_tuc());
            statement.setString(2, news.getTen_tin_tuc());
            statement.setString(3, fixedHTMLCode4);
            statement.setString(4, news.getHinh_anh());
            statement.setString(5, news.getHinh_anh_1());
            statement.setString(6, news.getHinh_anh_2());
            statement.setString(7, news.getNguoi_cap_nhat());
            statement.setTimestamp(8, new Timestamp(dateCurrent.getTime()));
            statement.setInt(9, news.getId());

            statement.executeUpdate();
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
            log.info("UPDATE NEWS: " + news.getMa_tin_tuc());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - updateNews!");
        }
    }

    public void deleteNews(News news) {
        try {
            String sqlQuery = "DELETE FROM tblnews WHERE " +
                    "id = ? " ;
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, news.getId());
            statement.executeUpdate();
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
            log.info("DELETE NEWS: " + news.getMa_tin_tuc());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - deleteNews!");
        }
    }
}
