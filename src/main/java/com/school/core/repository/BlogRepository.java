package com.school.core.repository;

import com.school.core.entity.Blog;
import com.school.core.entity.Farm;
import com.school.core.entity.Pig;
import com.school.core.entity.PigLandingPage;
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
public class BlogRepository extends BaseRepository {
    public List<Blog> getAllBlog() {
        List<Blog> listBlog = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id, " +
                    "ma_blog, " +
                    "ten_blog,  " +
                    "noi_dung,  " +
                    "hinh_anh, " +
                    "nguoi_nhap, " +
                    "ngay_nhap " +
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
                fixedHTMLCode3 = fixedHTMLCode2.replaceAll("text-align:none;", "text-align:center;");
                blog.setId(resultSet.getInt("id"));
                blog.setHinh_anh(resultSet.getString("hinh_anh"));
                blog.setMa_blog(resultSet.getString("ma_blog"));
                blog.setTen_blog(resultSet.getString("ten_blog"));
                blog.setNoi_dung(fixedHTMLCode3);
                blog.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                blog.setNgay_nhap(resultSet.getString("ngay_nhap"));
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
                    "ngay_nhap " +
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
                String fixedHTMLCode = null;
                String fixedHTMLCode2 = null;
                String fixedHTMLCode3 = null;
                fixedHTMLCode = resultSet.getString("noi_dung").replaceAll("&lt;", "<");
                fixedHTMLCode2 = fixedHTMLCode.replaceAll("&gt;", ">");
                fixedHTMLCode3 = fixedHTMLCode2.replaceAll("text-align:none;", "text-align:center;");
                blog.setNoi_dung(fixedHTMLCode3);
                blog.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                blog.setNgay_nhap(resultSet.getDate("ngay_nhap").toString());
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
                    "(ma_blog, ten_blog, hinh_anh, noi_dung, nguoi_nhap, ngay_nhap) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, blog.getMa_blog());
            statement.setString(2, blog.getTen_blog());
            statement.setString(3, blog.getHinh_anh());
            statement.setString(4, fixedHTMLCode2);
            statement.setString(5, blog.getNguoi_nhap());
            statement.setTimestamp(6, new Timestamp(dateCurrent.getTime()));
            statement.execute();
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - saveBlog!");
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
