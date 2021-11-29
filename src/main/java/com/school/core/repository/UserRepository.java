package com.school.core.repository;

import com.school.core.entity.User;
import com.school.core.entity.UserPrinciple;
import com.school.core.response.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends BaseRepository {
    public void saveUser(User user) {
        try {
            Date dateCurrent = new Date();
            String sqlQuery = "INSERT INTO tblusers " +
                    "(user_name, user_email, user_password, user_first_name, user_last_name, user_phone_number, " +
                    "user_avatar, role_id, dob, user_status, user_gender, dtime_entered) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirst_name());
            statement.setString(5, user.getLast_name());
            statement.setString(6, user.getPhone_number());
            if (user.getGender().equals("male")) {
                statement.setString(7, "/images/user/avatar-male.jpg");
            } else if (user.getGender().equals("female")) {
                statement.setString(7, "/images/user/avatar-female.jpg");
            }
            statement.setInt(8, 4); // Default: 4 (User)
            statement.setTimestamp(9, new Timestamp(user.getDob().getTime()));
            statement.setString(10, "I"); // Default: I (Inactive), A (Active) - Must confirm email
            statement.setString(11, user.getGender());
            statement.setTimestamp(12, new Timestamp(dateCurrent.getTime()));
            statement.execute();
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - save user!");
        }
    }

    public String findUserEmail(String userEmail) {
        String email = null;
        try {
            String sqlQuery = "SELECT " +
                    "user_email " +
                    "FROM tblusers " +
                    "WHERE user_email = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                email = resultSet.getString("user_email");
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error!");
        }
        return email;
    }

    public String findUserPassword(String username) {
        String passwordHash = null;
        try {
            String sqlQuery = "SELECT " +
                    "user_password " +
                    "FROM tblusers " +
                    "WHERE user_name = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                passwordHash = resultSet.getString("user_password");
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error!");
        }
        return passwordHash;
    }

    public String findUsername(String username) {
        String usernameDB = null;
        try {
            String sqlQuery = "SELECT " +
                    "user_name " +
                    "FROM tblusers " +
                    "WHERE user_name = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                usernameDB = resultSet.getString("user_name");
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error!");
        }
        return usernameDB;
    }

    public Optional<UserPrinciple> findOneWithAuthoritiesByUsername(String username) {
        String sql = "SELECT " +
                "id, " +
                "user_password, " +
                "user_name, " +
                "role_id, " +
                "user_status " +
                "FROM tblusers " +
                "WHERE user_name = ?";
        UserPrinciple userPrinciple = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userPrinciple = new UserPrinciple();
                userPrinciple.setId(resultSet.getInt("id"));
                userPrinciple.setUsername(resultSet.getString("user_name"));
                userPrinciple.setPassword(resultSet.getString("user_password"));
                String roleId = String.valueOf(resultSet.getInt("role_id"));
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(roleId));
                userPrinciple.setRoles(authorities);
                userPrinciple.setStatus(resultSet.getString("user_status"));
                userPrinciple.setRoleId(resultSet.getInt("role_id"));
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Optional<UserPrinciple> opt = Optional.of(userPrinciple);
        return opt;
    }

    public boolean isUserExist(String userEmail) {
        String email = null;
        try {
            String sqlQuery = "SELECT " +
                    "user_email " +
                    "FROM tblusers " +
                    "WHERE user_email = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                email = resultSet.getString("user_email");
            }
            if (email != null && email.length() > 0) {
                return true;
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error!");
        }
        return false;
    }

    public void enableUser(String email) {
        try {
            String sql = "UPDATE tblusers SET user_status = 'A' " +
                    "WHERE user_email = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.executeUpdate();
            this.connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
