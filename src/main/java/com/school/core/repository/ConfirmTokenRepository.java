package com.school.core.repository;

import com.school.core.entity.ConfirmToken;
import com.school.core.response.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Repository
public class ConfirmTokenRepository extends BaseRepository {
    private final Logger log = LoggerFactory.getLogger(BlogRepository.class);

    public ConfirmToken findTokenByToken(String token) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ConfirmToken confirmToken = new ConfirmToken();
        try {
            String sqlQuery = "SELECT " +
                    "user_email, " +
                    "DATE_FORMAT(created_at, '%Y-%m-%d %H:%i:%s') as createdAt, " +
                    "DATE_FORMAT(expires_at, '%Y-%m-%d %H:%i:%s') as expiresAt, " +
                    "DATE_FORMAT(confirmed_at, '%Y-%m-%d %H:%i:%s') as confirmedAt " +
                    "FROM tblconfirmtoken " +
                    "WHERE token = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String createdAt = simpleDateFormat.format(resultSet.getTimestamp("createdAt"));
                String expiresAt = simpleDateFormat.format(resultSet.getTimestamp("expiresAt"));
                confirmToken.setToken(token);
                confirmToken.setUser_email(resultSet.getString("user_email"));
                confirmToken.setCreated_at(createdAt);
                confirmToken.setExpires_at(expiresAt);
                if (resultSet.getDate("confirmedAt") != null) {
                    String confirmedAt = simpleDateFormat.format(resultSet.getTimestamp("confirmedAt"));
                    confirmToken.setConfirmed_at(confirmedAt);
                }
            }
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error!");
        }
        return confirmToken;
    }

    public void saveToken(ConfirmToken confirmToken) {
        try {
            String sqlQuery = "INSERT INTO tblconfirmtoken " +
                    "(token, user_email, created_at, expires_at) " +
                    "VALUES (?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, confirmToken.getToken());
            statement.setString(2, confirmToken.getUser_email());
            statement.setString(3, confirmToken.getCreated_at());
            statement.setString(4, confirmToken.getExpires_at());
            statement.execute();
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error!");
        }
    }

    public void updateConfirmedAt(String token, String confirmAt) {
        try {
            String sqlQuery = "UPDATE tblconfirmtoken SET confirmed_at = ? " +
                    "WHERE token = ?";
            PreparedStatement statement = this.connection.prepareStatement(sqlQuery);
            statement.setString(1, confirmAt);
            statement.setString(2, token);
            statement.executeUpdate();
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
