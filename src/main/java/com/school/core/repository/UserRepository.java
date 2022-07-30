package com.school.core.repository;

import com.school.core.entity.User;
import com.school.core.entity.UserPrinciple;
import com.school.core.response.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger log = LoggerFactory.getLogger(PigRepository.class);

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
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - save user!");
        }
    }

    public String findUserEmailByEmail(String userEmail) {
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
            log.info("SQL: " + sqlQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - findUserEmailByEmail!");
        }
        return email;
    }

    public String findUserEmailByUsername(String userName) {
        String email = null;
        try {
            String sqlQuery = "SELECT " +
                    "user_email " +
                    "FROM tblusers " +
                    "WHERE user_name = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                email = resultSet.getString("user_email");
            }
            log.info("SQL: " + sqlQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - findUserEmailByUsername!");
        }
        return email;
    }

    public String findUserPasswordByUsername(String username) {
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
            log.info("SQL: " + sqlQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - findUserPasswordByUsername!");
        }
        return passwordHash;
    }

    public String findUsernameByUsername(String username) {
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
            log.info("SQL: " + sqlQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - findUsernameByUsername!");
        }
        return usernameDB;
    }

    public User findUserByUsername(String username) {
        User user = new User();
        try {
            String sqlQuery = "SELECT " +
                    "user_name, " +
                    "user_email, " +
                    "user_first_name, " +
                    "user_last_name, " +
                    "user_avatar, " +
                    "user_phone_number, " +
                    "role_id, " +
                    "dob, " +
                    "user_status, " +
                    "user_gender " +
                    "FROM tblusers " +
                    "WHERE user_name = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setUsername(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("user_email"));
                user.setFirst_name(resultSet.getString("user_first_name"));
                user.setLast_name(resultSet.getString("user_last_name"));
                user.setAvatar(resultSet.getString("user_avatar"));
                user.setPhone_number(resultSet.getString("user_phone_number"));
                user.setRole_id(resultSet.getInt("role_id"));
                user.setDob(resultSet.getDate("dob"));
                user.setStatus(resultSet.getString("user_status"));
                user.setGender(resultSet.getString("user_gender"));
            }
            log.info("SQL: " + sqlQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - findUserByUsername!");
        }
        return user;
    }

    public Optional<UserPrinciple> findOneWithAuthoritiesByUsername(String username) {
        String sqlQuery = "SELECT " +
                "id, " +
                "user_password, " +
                "user_name, " +
                "role_id, " +
                "user_status " +
                "FROM tblusers " +
                "WHERE user_name = ?";
        UserPrinciple userPrinciple = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
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
            log.info("SQL: " + sqlQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
            log.info("SQL: " + sqlQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error!");
        }
        return false;
    }

    public boolean isUserConfirmEmail(String userEmail) {
        String confirmed_at = null;
        try {
            String sqlQuery = "SELECT " +
                    "confirmed_at " +
                    "FROM tblconfirmtoken " +
                    "WHERE user_email = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getDate("confirmed_at") != null) {
                    confirmed_at = resultSet.getDate("confirmed_at").toString();
                }
            }
            if (confirmed_at != null && confirmed_at.length() > 0) {
                return true;
            }
            log.info("SQL: " + sqlQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error! - isUserConfirmEmail");
        }
        return false;
    }

    public void enableUser(String email) {
        try {
            String sqlQuery = "UPDATE tblusers SET user_status = 'A' " +
                    "WHERE user_email = ?";
            PreparedStatement statement = this.connection.prepareStatement(sqlQuery);
            statement.setString(1, email);
            statement.executeUpdate();
            log.info("SQL: " + sqlQuery);
            this.connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
