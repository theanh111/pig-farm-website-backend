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
public class BlogRepository extends BaseRepository {
    private final Logger log = LoggerFactory.getLogger(BlogRepository.class);


    public List<Blog> getAllBlog() {
        List<Blog> listBlog = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id, " +
                    "ma_blog, " +
                    "ten_blog,  " +
                    "noi_dung,  " +
                    "hinh_anh, " +
                    "hinh_anh_1, " +
                    "hinh_anh_2, " +
                    "nguoi_nhap, " +
                    "ngay_nhap, " +
                    "ngay_cap_nhat, " +
                    "nguoi_cap_nhat " +
                    "FROM tblblog " +
                    "ORDER BY ngay_nhap DESC ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Blog blog = new Blog();
                String fixedHTMLCode = null;
                String fixedHTMLCode2 = null;
                String fixedHTMLCode3 = null;
                fixedHTMLCode = resultSet.getString("noi_dung").replaceAll("&lt;", "<");
                fixedHTMLCode2 = fixedHTMLCode.replaceAll("&gt;", ">");
                fixedHTMLCode3 = fixedHTMLCode2.replaceAll("div style=\"text-align:none;\"", "div style=\"text-align:center;\"");
                blog.setId(resultSet.getInt("id"));
                blog.setHinh_anh(resultSet.getString("hinh_anh"));
                blog.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                blog.setHinh_anh_2(resultSet.getString("hinh_anh_2"));
                blog.setMa_blog(resultSet.getString("ma_blog"));
                blog.setTen_blog(resultSet.getString("ten_blog"));
                blog.setNoi_dung(fixedHTMLCode3);
                blog.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                blog.setNgay_nhap(resultSet.getString("ngay_nhap"));
                blog.setNguoi_cap_nhat(resultSet.getString("nguoi_cap_nhat"));
                blog.setNgay_cap_nhat(resultSet.getString("ngay_cap_nhat"));
                listBlog.add(blog);
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getAllBlog!");
        }
        return listBlog;
    }

    public List<Blog> getAllBlogByFilter(Blog blogFilter) {
        List<Blog> listBlogByFilter = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id," +
                    "ma_blog, " +
                    "ten_blog, " +
                    "noi_dung, " +
                    "hinh_anh, " +
                    "ngay_nhap " +
                    "FROM tblblog " +
                    "WHERE trang_thai = 1 ";

            sqlQuery += "AND ( " +
                    "ma_blog LIKE ? " +
                    "OR ten_blog LIKE ? " +
                    "OR noi_dung LIKE ? " +
                    "OR hinh_anh LIKE ? " +
                    ") ";

            sqlQuery += " ORDER BY ngay_nhap DESC";

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, "%" + blogFilter.getTu_khoa() + "%");
            statement.setString(2, "%" + blogFilter.getTu_khoa() + "%");
            statement.setString(3, "%" + blogFilter.getTu_khoa() + "%");
            statement.setString(4, "%" + blogFilter.getTu_khoa() + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Blog blog = new Blog();
                String fixedHTMLCode = null;
                String fixedHTMLCode2 = null;
                String fixedHTMLCode3 = null;
                fixedHTMLCode = resultSet.getString("noi_dung").replaceAll("&lt;", "<");
                fixedHTMLCode2 = fixedHTMLCode.replaceAll("&gt;", ">");
                fixedHTMLCode3 = fixedHTMLCode2.replaceAll("text-align:none;", "text-align:center;");
                blog.setId(resultSet.getInt("id"));
                blog.setMa_blog(resultSet.getString("ma_blog"));
                blog.setTen_blog(resultSet.getString("ten_blog"));
                blog.setNoi_dung(fixedHTMLCode3);
                blog.setHinh_anh(resultSet.getString("hinh_anh"));
                blog.setNgay_nhap(resultSet.getString("ngay_nhap"));

                listBlogByFilter.add(blog);
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getAllBlogByFilter!");
        }
        return listBlogByFilter;
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

    public Blog getBlogByBlogCode(String blogCode) {
        Blog blog = new Blog();
        try {
            String sqlQuery = "SELECT " +
                    "id, " +
                    "ma_blog, " +
                    "ten_blog,  " +
                    "noi_dung,  " +
                    "nguoi_nhap, " +
                    "hinh_anh, " +
                    "hinh_anh_1, " +
                    "hinh_anh_2, " +
                    "ngay_nhap, " +
                    "nguoi_cap_nhat, " +
                    "ngay_cap_nhat " +
                    "FROM tblblog " +
                    "WHERE ma_blog = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, blogCode);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                blog.setId(resultSet.getInt("id"));
                blog.setMa_blog(resultSet.getString("ma_blog"));
                blog.setTen_blog(resultSet.getString("ten_blog"));
                blog.setHinh_anh(resultSet.getString("hinh_anh"));
                blog.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                blog.setHinh_anh_2(resultSet.getString("hinh_anh_2"));
                String fixedHTMLCode = null;
                String fixedHTMLCode2 = null;
                String fixedHTMLCode3 = null;
                fixedHTMLCode = resultSet.getString("noi_dung").replaceAll("&lt;", "<");
                fixedHTMLCode2 = fixedHTMLCode.replaceAll("&gt;", ">");
                fixedHTMLCode3 = fixedHTMLCode2.replaceAll("div style=\"text-align:none;\"", "div style=\"text-align:center;\"");
                blog.setNoi_dung(fixedHTMLCode3);
                blog.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                blog.setNgay_nhap(resultSet.getString("ngay_nhap"));
                blog.setNgay_cap_nhat(resultSet.getString("ngay_cap_nhat"));
                blog.setNguoi_cap_nhat(resultSet.getString("nguoi_cap_nhat"));
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getBlogByBlogCode!");
        }
        return blog;
    }

    public void saveBlog(Blog blog) {
        try {
            String fixedHTMLCode = null;
            String fixedHTMLCode2 = null;
            fixedHTMLCode = blog.getNoi_dung().replaceAll("<", "&lt;");
            fixedHTMLCode2 = fixedHTMLCode.replaceAll(">", "&gt;");
            Date dateCurrent = new Date();
            String sqlQuery = "INSERT INTO tblblog " +
                    "(ma_blog, ten_blog, hinh_anh, hinh_anh_1, hinh_anh_2, noi_dung, nguoi_nhap, ngay_nhap, nguoi_cap_nhat, ngay_cap_nhat) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, blog.getMa_blog());
            statement.setString(2, blog.getTen_blog());
            statement.setString(3, blog.getHinh_anh());
            statement.setString(4, blog.getHinh_anh_1());
            statement.setString(5, blog.getHinh_anh_2());
            statement.setString(6, fixedHTMLCode2);
            statement.setString(7, blog.getNguoi_nhap());
            statement.setTimestamp(8, new Timestamp(dateCurrent.getTime()));
            statement.setString(9, blog.getNguoi_cap_nhat());
            statement.setTimestamp(10, new Timestamp(dateCurrent.getTime()));
            statement.execute();
            connection.commit();
            statement.close();
            log.info("ADD NEW BLOG: " + blog.getMa_blog());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - saveBlog!");
        }
    }

    public void updateBlog(Blog blog) {
        try {
            Date dateCurrent = new Date();
            String fixedHTMLCode = null;
            String fixedHTMLCode2 = null;
            fixedHTMLCode = blog.getNoi_dung().replaceAll("<", "&lt;");
            fixedHTMLCode2 = fixedHTMLCode.replaceAll(">", "&gt;");
            String sqlQuery = "UPDATE tblblog " +
                    "SET " +
                    "ma_blog = ?, " +
                    "ten_blog = ?, " +
                    "noi_dung = ?, " +
                    "hinh_anh = ?, " +
                    "hinh_anh_1 = ?, " +
                    "hinh_anh_2 = ?, " +
                    "nguoi_cap_nhat = ?, " +
                    "ngay_cap_nhat = ? " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, blog.getMa_blog());
            statement.setString(2, blog.getTen_blog());
            statement.setString(3, fixedHTMLCode2);
            statement.setString(4, blog.getHinh_anh());
            statement.setString(5, blog.getHinh_anh_1());
            statement.setString(6, blog.getHinh_anh_2());
            statement.setString(7, blog.getNguoi_cap_nhat());
            statement.setTimestamp(8, new Timestamp(dateCurrent.getTime()));
            statement.setInt(9, blog.getId());

            statement.executeUpdate();
            connection.commit();
            statement.close();
            log.info("UPDATE BLOG: " + blog.getMa_blog());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - updateBlog!");
        }
    }

    public void deleteBlog(Blog blog) {
        try {
            String sqlQuery = "DELETE FROM tblblog WHERE " +
                    "id = ? " ;
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, blog.getId());
            statement.executeUpdate();
            connection.commit();
            statement.close();
            log.info("DELETE BLOG: " + blog.getMa_blog());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - deleteBlog!");
        }
    }
}
