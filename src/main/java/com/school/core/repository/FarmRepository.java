package com.school.core.repository;

import com.school.core.entity.Farm;
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
public class FarmRepository extends BaseRepository {
    private final Logger log = LoggerFactory.getLogger(FarmRepository.class);

    public List<Farm> getAllFarm() {
        List<Farm> listFarm = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id, " +
                    "ma_trang_trai, " +
                    "ten_trang_trai, " +
                    "hinh_anh,  " +
                    "ngay_nhap, " +
                    "nguoi_nhap, " +
                    "nguoi_cap_nhat, " +
                    "ngay_cap_nhat " +
                    "FROM tblfarm " +
                    "ORDER BY ngay_nhap DESC ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Farm farm = new Farm();
                farm.setId(resultSet.getInt("id"));
                farm.setMa_trang_trai(resultSet.getString("ma_trang_trai"));
                farm.setTen_trang_trai(resultSet.getString("ten_trang_trai"));
                farm.setHinh_anh(resultSet.getString("hinh_anh"));
                farm.setNgay_nhap(resultSet.getString("ngay_nhap"));
                farm.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                farm.setNguoi_cap_nhat(resultSet.getString("nguoi_cap_nhat"));
                farm.setNgay_cap_nhat(resultSet.getString("ngay_cap_nhat"));
                listFarm.add(farm);
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getAllFarm!");
        }
        return listFarm;
    }

    public List<Farm> getAllFarmByFilter(Farm farmFilter) {
        List<Farm> listFarmByFilter = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id," +
                    "ma_trang_trai, " +
                    "ten_trang_trai, " +
                    "hinh_anh, " +
                    "ngay_nhap " +
                    "FROM tblfarm ";

            sqlQuery += "WHERE ( " +
                    "ma_trang_trai LIKE ? " +
                    "OR ten_trang_trai LIKE ? " +
                    "OR hinh_anh LIKE ? " +
                    ") ";

            sqlQuery += " ORDER BY ngay_nhap DESC";

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, "%" + farmFilter.getTu_khoa() + "%");
            statement.setString(2, "%" + farmFilter.getTu_khoa() + "%");
            statement.setString(3, "%" + farmFilter.getTu_khoa() + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Farm farm = new Farm();
                farm.setId(resultSet.getInt("id"));
                farm.setMa_trang_trai(resultSet.getString("ma_trang_trai"));
                farm.setTen_trang_trai(resultSet.getString("ten_trang_trai"));
                farm.setHinh_anh(resultSet.getString("hinh_anh"));
                farm.setNgay_nhap(resultSet.getString("ngay_nhap"));

                listFarmByFilter.add(farm);
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getAllFarmByFilter!");
        }
        return listFarmByFilter;
    }

    public Integer countFarm() {
        Integer numberOfFarm = null;
        try {
            String sqlQuery = "select count(id) as total from tblfarm ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                numberOfFarm = resultSet.getInt("total");
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - countFarm!");
        }
        return numberOfFarm;
    }

    public Farm getFarmById(Integer id) {
        Farm farm = new Farm();
        try {
            String sqlQuery = "SELECT " +
                    "id, " +
                    "ma_trang_trai, " +
                    "ten_trang_trai, " +
                    "hinh_anh,  " +
                    "ngay_nhap, " +
                    "nguoi_nhap, " +
                    "ngay_cap_nhat, " +
                    "nguoi_cap_nhat " +
                    "FROM tblfarm " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                farm.setId(resultSet.getInt("id"));
                farm.setMa_trang_trai(resultSet.getString("ma_trang_trai"));
                farm.setTen_trang_trai(resultSet.getString("ten_trang_trai"));
                farm.setHinh_anh(resultSet.getString("hinh_anh"));
                farm.setNgay_nhap(resultSet.getString("ngay_nhap"));
                farm.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                farm.setNgay_cap_nhat(resultSet.getString("ngay_cap_nhat"));
                farm.setNguoi_cap_nhat(resultSet.getString("nguoi_cap_nhat"));
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getFarmById!");
        }
        return farm;
    }

    public Farm getFarmByFarmCode(String farmCode) {
        Farm farm = new Farm();
        try {
            String sqlQuery = "SELECT " +
                    "id, " +
                    "ma_trang_trai, " +
                    "ten_trang_trai, " +
                    "hinh_anh,  " +
                    "ngay_nhap, " +
                    "nguoi_nhap, " +
                    "nguoi_cap_nhat, " +
                    "ngay_cap_nhat " +
                    "FROM tblfarm " +
                    "WHERE ma_trang_trai = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, farmCode);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                farm.setId(resultSet.getInt("id"));
                farm.setMa_trang_trai(resultSet.getString("ma_trang_trai"));
                farm.setTen_trang_trai(resultSet.getString("ten_trang_trai"));
                farm.setHinh_anh(resultSet.getString("hinh_anh"));
                farm.setNgay_nhap(resultSet.getString("ngay_nhap"));
                farm.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                farm.setNguoi_cap_nhat(resultSet.getString("nguoi_cap_nhat"));
                farm.setNgay_cap_nhat(resultSet.getString("ngay_cap_nhat"));
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getFarmByFarmCode!");
        }
        return farm;
    }

    public String getFarmName(String farmName) {
        String farmNameDB = null;
        try {
            String sqlQuery = "SELECT " +
                    "ten_trang_trai " +
                    "FROM tblfarm " +
                    "WHERE " +
                    "ten_trang_trai = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, farmName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                farmNameDB = resultSet.getString("ten_trang_trai");
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getFarmName!");
        }
        return farmNameDB;
    }

    public void saveFarm(Farm farm) {
        try {
            Date dateCurrent = new Date();
            String sqlQuery = "INSERT INTO tblfarm " +
                    "(ma_trang_trai, ten_trang_trai, hinh_anh, ngay_nhap, nguoi_nhap, ngay_cap_nhat, nguoi_cap_nhat) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, farm.getMa_trang_trai());
            statement.setString(2, farm.getTen_trang_trai());
            statement.setString(3, farm.getHinh_anh());
            statement.setTimestamp(4, new Timestamp(dateCurrent.getTime()));
            statement.setString(5, farm.getNguoi_nhap());
            statement.setTimestamp(6, new Timestamp(dateCurrent.getTime()));
            statement.setString(7, farm.getNguoi_cap_nhat());
            statement.execute();
            connection.commit();
            statement.close();
            log.info("SAVE FARM: " + farm.getMa_trang_trai());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - saveFarm!");
        }
    }

    public void updateFarm(Farm farm) {
        try {
            Date dateCurrent = new Date();
            String sqlQuery = "UPDATE tblfarm " +
                    "SET " +
                    "ma_trang_trai = ?, " +
                    "ten_trang_trai = ?, " +
                    "hinh_anh = ?, " +
                    "nguoi_cap_nhat = ?, " +
                    "ngay_cap_nhat = ? " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, farm.getMa_trang_trai());
            statement.setString(2, farm.getTen_trang_trai());
            statement.setString(3, farm.getHinh_anh());
            statement.setString(4, farm.getNguoi_cap_nhat());
            statement.setTimestamp(5, new Timestamp(dateCurrent.getTime()));
            statement.setInt(6, farm.getId());
            statement.executeUpdate();
            connection.commit();
            statement.close();
            log.info("UPDATE FARM: " + farm.getMa_trang_trai());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - updateFarm!");
        }
    }

    public void deleteFarm(Farm farm) {
        try {
            String sqlQuery = "DELETE FROM tblfarm WHERE " +
                    "id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, farm.getId());
            statement.executeUpdate();
            connection.commit();
            statement.close();
            log.info("DELETE FARM: " + farm.getMa_trang_trai());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - deleteFarm!");
        }
    }
}
