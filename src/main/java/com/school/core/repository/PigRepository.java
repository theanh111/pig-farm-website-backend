package com.school.core.repository;

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
public class PigRepository extends BaseRepository {
    public List<PigLandingPage> getPigLimit12() {
        List<PigLandingPage> listPigLimit12 = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id, " +
                    "ma_so_ca_the, " +
                    "tuoi, " +
                    "hinh_anh_1 " +
                    "FROM tblpig " +
                    "WHERE trang_thai = 1 " +
                    "ORDER BY ngay_nhap DESC " +
                    "LIMIT 12";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PigLandingPage pig = new PigLandingPage();
                pig.setId(resultSet.getInt("id"));
                pig.setMa_so_ca_the(resultSet.getString("ma_so_ca_the"));
                pig.setTuoi(resultSet.getString("tuoi"));
                pig.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                listPigLimit12.add(pig);
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getPigLimit12!");
        }
        return listPigLimit12;
    }

    public Integer countPig() {
        Integer numberOfPig = null;
        try {
            String sqlQuery = "select count(id) as total from tblpig ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                numberOfPig = resultSet.getInt("total");
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - countPig!");
        }
        return numberOfPig;
    }

    public Pig getPigById(Integer id) {
        Pig pig = new Pig();
        try {
            String sqlQuery = "SELECT " +
                    "id," +
                    "dau_thoi_gian, " +
                    "ma_so_ca_the, " +
                    "tuoi, " +
                    "nguoi_danh_gia, " +
                    "dia_diem_danh_gia, " +
                    "ngay_danh_gia, " +
                    "gioi_tinh, " +
                    "nhan_trung_trang, " +
                    "tai_meo, " +
                    "mom, " +
                    "vanh_trang_quanh_mom, " +
                    "mat, " +
                    "da_co_khoang_den, " +
                    "khoang_den_phu, " +
                    "kieu_lung_bung, " +
                    "chan, " +
                    "da_mat, " +
                    "kieu_duoi, " +
                    "kieu_tai, " +
                    "huong_tai, " +
                    "kieu_long, " +
                    "loai_mau_long, " +
                    "hinh_dang_dau, " +
                    "trong_luong_co_the_kg, " +
                    "chieu_dai_dau_cm, " +
                    "chieu_dai_duoi_cm, " +
                    "chieu_rong_nguc_cm, " +
                    "chieu_cao_than_tai_chan_truoc_cm, " +
                    "chieu_dai_co_the_cm, " +
                    "chieu_dai_tai_cm, " +
                    "chieu_rong_than_tai_xuong_chau_cm, " +
                    "chu_vi_co_chan_cm, " +
                    "do_rong_dai_trang_vat_vai_cm, " +
                    "so_bau_vu, " +
                    "loai_con_giong, " +
                    "so_con_trong_dan, " +
                    "so_con_duc_trong_dan, " +
                    "so_con_cai_trong_dan, " +
                    "hinh_anh_1, " +
                    "hinh_anh_2, " +
                    "hinh_dang_long, " +
                    "do_dai_long, " +
                    "do_day_long, " +
                    "pha_he, " +
                    "ngay_nhap, " +
                    "trang_thai, " +
                    "nguoi_nhap " +
                    "FROM tblpig " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pig.setId(resultSet.getInt("id"));
                pig.setDau_thoi_gian(resultSet.getString("dau_thoi_gian"));
                pig.setMa_so_ca_the(resultSet.getString("ma_so_ca_the"));
                pig.setTuoi(resultSet.getString("tuoi"));
                pig.setNguoi_danh_gia(resultSet.getString("nguoi_danh_gia"));
                pig.setDia_diem_danh_gia(resultSet.getString("dia_diem_danh_gia"));
                if (resultSet.getDate("ngay_danh_gia") != null) {
                    pig.setNgay_danh_gia(resultSet.getDate("ngay_danh_gia").toString());
                }
                pig.setGioi_tinh(resultSet.getString("gioi_tinh"));
                pig.setNhan_trung_trang(resultSet.getString("nhan_trung_trang"));
                pig.setTai_meo(resultSet.getString("tai_meo"));
                pig.setMom(resultSet.getString("mom"));
                pig.setVanh_trang_quanh_mom(resultSet.getString("vanh_trang_quanh_mom"));
                pig.setMat(resultSet.getString("mat"));
                pig.setDa_co_khoang_den(resultSet.getString("da_co_khoang_den"));
                pig.setKhoang_den_phu(resultSet.getString("khoang_den_phu"));
                pig.setKieu_lung_bung(resultSet.getString("kieu_lung_bung"));
                pig.setChan(resultSet.getString("chan"));
                pig.setDa_mat(resultSet.getString("da_mat"));
                pig.setKieu_duoi(resultSet.getString("kieu_duoi"));
                pig.setKieu_tai(resultSet.getString("kieu_tai"));
                pig.setHuong_tai(resultSet.getString("huong_tai"));
                pig.setKieu_long(resultSet.getString("kieu_long"));
                pig.setLoai_mau_long(resultSet.getString("loai_mau_long"));
                pig.setHinh_dang_dau(resultSet.getString("hinh_dang_dau"));
                pig.setTrong_luong_co_the_kg(resultSet.getDouble("trong_luong_co_the_kg"));
                pig.setChieu_dai_dau_cm(resultSet.getDouble("chieu_dai_dau_cm"));
                pig.setChieu_dai_duoi_cm(resultSet.getDouble("chieu_dai_duoi_cm"));
                pig.setChieu_rong_nguc_cm(resultSet.getDouble("chieu_rong_nguc_cm"));
                pig.setChieu_cao_than_tai_chan_truoc_cm(resultSet.getDouble("chieu_cao_than_tai_chan_truoc_cm"));
                pig.setChieu_dai_co_the_cm(resultSet.getDouble("chieu_dai_co_the_cm"));
                pig.setChieu_dai_tai_cm(resultSet.getDouble("chieu_dai_tai_cm"));
                pig.setChieu_rong_than_tai_xuong_chau_cm(resultSet.getDouble("chieu_rong_than_tai_xuong_chau_cm"));
                pig.setChu_vi_co_chan_cm(resultSet.getDouble("chu_vi_co_chan_cm"));
                pig.setDo_rong_dai_trang_vat_vai_cm(resultSet.getDouble("do_rong_dai_trang_vat_vai_cm"));
                pig.setSo_bau_vu(resultSet.getInt("so_bau_vu"));
                pig.setLoai_con_giong(resultSet.getString("loai_con_giong"));
                pig.setSo_con_trong_dan(resultSet.getInt("so_con_trong_dan"));
                pig.setSo_con_duc_trong_dan(resultSet.getInt("so_con_duc_trong_dan"));
                pig.setSo_con_cai_trong_dan(resultSet.getInt("so_con_cai_trong_dan"));
                pig.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                pig.setHinh_anh_2(resultSet.getString("hinh_anh_2"));
                pig.setHinh_dang_long(resultSet.getString("hinh_dang_long"));
                pig.setDo_dai_long(resultSet.getString("do_dai_long"));
                pig.setDo_day_long(resultSet.getString("do_day_long"));
                pig.setPha_he(resultSet.getString("pha_he"));
                if (resultSet.getDate("ngay_nhap") != null) {
                    pig.setNgay_nhap(resultSet.getDate("ngay_nhap").toString());
                }
                pig.setTrang_thai(resultSet.getInt("trang_thai"));
                pig.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getPigById!");
        }
        return pig;
    }

    public List<Pig> getPigByFarmName(String farmName) {
        List<Pig> listPigByFarmName = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id," +
                    "dau_thoi_gian, " +
                    "ma_so_ca_the, " +
                    "tuoi, " +
                    "nguoi_danh_gia, " +
                    "dia_diem_danh_gia, " +
                    "ngay_danh_gia, " +
                    "gioi_tinh, " +
                    "nhan_trung_trang, " +
                    "tai_meo, " +
                    "mom, " +
                    "vanh_trang_quanh_mom, " +
                    "mat, " +
                    "da_co_khoang_den, " +
                    "khoang_den_phu, " +
                    "kieu_lung_bung, " +
                    "chan, " +
                    "da_mat, " +
                    "kieu_duoi, " +
                    "kieu_tai, " +
                    "huong_tai, " +
                    "kieu_long, " +
                    "loai_mau_long, " +
                    "hinh_dang_dau, " +
                    "trong_luong_co_the_kg, " +
                    "chieu_dai_dau_cm, " +
                    "chieu_dai_duoi_cm, " +
                    "chieu_rong_nguc_cm, " +
                    "chieu_cao_than_tai_chan_truoc_cm, " +
                    "chieu_dai_co_the_cm, " +
                    "chieu_dai_tai_cm, " +
                    "chieu_rong_than_tai_xuong_chau_cm, " +
                    "chu_vi_co_chan_cm, " +
                    "do_rong_dai_trang_vat_vai_cm, " +
                    "so_bau_vu, " +
                    "loai_con_giong, " +
                    "so_con_trong_dan, " +
                    "so_con_duc_trong_dan, " +
                    "so_con_cai_trong_dan, " +
                    "hinh_anh_1, " +
                    "hinh_anh_2, " +
                    "hinh_dang_long, " +
                    "do_dai_long, " +
                    "do_day_long, " +
                    "pha_he, " +
                    "ngay_nhap, " +
                    "trang_thai, " +
                    "nguoi_nhap " +
                    "FROM tblpig " +
                    "WHERE dia_diem_danh_gia = ? " +
                    "AND trang_thai = 1";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, farmName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pig pig = new Pig();
                pig.setId(resultSet.getInt("id"));
                pig.setDau_thoi_gian(resultSet.getString("dau_thoi_gian"));
                pig.setMa_so_ca_the(resultSet.getString("ma_so_ca_the"));
                pig.setTuoi(resultSet.getString("tuoi"));
                pig.setNguoi_danh_gia(resultSet.getString("nguoi_danh_gia"));
                pig.setDia_diem_danh_gia(resultSet.getString("dia_diem_danh_gia"));
                if (resultSet.getDate("ngay_danh_gia") != null) {
                    pig.setNgay_danh_gia(resultSet.getDate("ngay_danh_gia").toString());
                }
                pig.setGioi_tinh(resultSet.getString("gioi_tinh"));
                pig.setNhan_trung_trang(resultSet.getString("nhan_trung_trang"));
                pig.setTai_meo(resultSet.getString("tai_meo"));
                pig.setMom(resultSet.getString("mom"));
                pig.setVanh_trang_quanh_mom(resultSet.getString("vanh_trang_quanh_mom"));
                pig.setMat(resultSet.getString("mat"));
                pig.setDa_co_khoang_den(resultSet.getString("da_co_khoang_den"));
                pig.setKhoang_den_phu(resultSet.getString("khoang_den_phu"));
                pig.setKieu_lung_bung(resultSet.getString("kieu_lung_bung"));
                pig.setChan(resultSet.getString("chan"));
                pig.setDa_mat(resultSet.getString("da_mat"));
                pig.setKieu_duoi(resultSet.getString("kieu_duoi"));
                pig.setKieu_tai(resultSet.getString("kieu_tai"));
                pig.setHuong_tai(resultSet.getString("huong_tai"));
                pig.setKieu_long(resultSet.getString("kieu_long"));
                pig.setLoai_mau_long(resultSet.getString("loai_mau_long"));
                pig.setHinh_dang_dau(resultSet.getString("hinh_dang_dau"));
                pig.setTrong_luong_co_the_kg(resultSet.getDouble("trong_luong_co_the_kg"));
                pig.setChieu_dai_dau_cm(resultSet.getDouble("chieu_dai_dau_cm"));
                pig.setChieu_dai_duoi_cm(resultSet.getDouble("chieu_dai_duoi_cm"));
                pig.setChieu_rong_nguc_cm(resultSet.getDouble("chieu_rong_nguc_cm"));
                pig.setChieu_cao_than_tai_chan_truoc_cm(resultSet.getDouble("chieu_cao_than_tai_chan_truoc_cm"));
                pig.setChieu_dai_co_the_cm(resultSet.getDouble("chieu_dai_co_the_cm"));
                pig.setChieu_dai_tai_cm(resultSet.getDouble("chieu_dai_tai_cm"));
                pig.setChieu_rong_than_tai_xuong_chau_cm(resultSet.getDouble("chieu_rong_than_tai_xuong_chau_cm"));
                pig.setChu_vi_co_chan_cm(resultSet.getDouble("chu_vi_co_chan_cm"));
                pig.setDo_rong_dai_trang_vat_vai_cm(resultSet.getDouble("do_rong_dai_trang_vat_vai_cm"));
                pig.setSo_bau_vu(resultSet.getInt("so_bau_vu"));
                pig.setLoai_con_giong(resultSet.getString("loai_con_giong"));
                pig.setSo_con_trong_dan(resultSet.getInt("so_con_trong_dan"));
                pig.setSo_con_duc_trong_dan(resultSet.getInt("so_con_duc_trong_dan"));
                pig.setSo_con_cai_trong_dan(resultSet.getInt("so_con_cai_trong_dan"));
                pig.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                pig.setHinh_anh_2(resultSet.getString("hinh_anh_2"));
                pig.setHinh_dang_long(resultSet.getString("hinh_dang_long"));
                pig.setDo_dai_long(resultSet.getString("do_dai_long"));
                pig.setDo_day_long(resultSet.getString("do_day_long"));
                pig.setPha_he(resultSet.getString("pha_he"));
                if (resultSet.getDate("ngay_nhap") != null) {
                    pig.setNgay_nhap(resultSet.getDate("ngay_nhap").toString());
                }
                pig.setTrang_thai(resultSet.getInt("trang_thai"));
                pig.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                listPigByFarmName.add(pig);
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getPigByFarmName!");
        }
        return listPigByFarmName;
    }

    public List<Pig> getAllPig() {
        List<Pig> listPig = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id," +
                    "dau_thoi_gian, " +
                    "ma_so_ca_the, " +
                    "tuoi, " +
                    "nguoi_danh_gia, " +
                    "dia_diem_danh_gia, " +
                    "ngay_danh_gia, " +
                    "gioi_tinh, " +
                    "nhan_trung_trang, " +
                    "tai_meo, " +
                    "mom, " +
                    "vanh_trang_quanh_mom, " +
                    "mat, " +
                    "da_co_khoang_den, " +
                    "khoang_den_phu, " +
                    "kieu_lung_bung, " +
                    "chan, " +
                    "da_mat, " +
                    "kieu_duoi, " +
                    "kieu_tai, " +
                    "huong_tai, " +
                    "kieu_long, " +
                    "loai_mau_long, " +
                    "hinh_dang_dau, " +
                    "trong_luong_co_the_kg, " +
                    "chieu_dai_dau_cm, " +
                    "chieu_dai_duoi_cm, " +
                    "chieu_rong_nguc_cm, " +
                    "chieu_cao_than_tai_chan_truoc_cm, " +
                    "chieu_dai_co_the_cm, " +
                    "chieu_dai_tai_cm, " +
                    "chieu_rong_than_tai_xuong_chau_cm, " +
                    "chu_vi_co_chan_cm, " +
                    "do_rong_dai_trang_vat_vai_cm, " +
                    "so_bau_vu, " +
                    "loai_con_giong, " +
                    "so_con_trong_dan, " +
                    "so_con_duc_trong_dan, " +
                    "so_con_cai_trong_dan, " +
                    "hinh_anh_1, " +
                    "hinh_anh_2, " +
                    "hinh_dang_long, " +
                    "do_dai_long, " +
                    "do_day_long, " +
                    "pha_he, " +
                    "ngay_nhap, " +
                    "trang_thai, " +
                    "nguoi_nhap " +
                    "FROM tblpig " +
                    "ORDER BY ngay_nhap DESC ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pig pig = new Pig();
                pig.setId(resultSet.getInt("id"));
                pig.setDau_thoi_gian(resultSet.getString("dau_thoi_gian"));
                pig.setMa_so_ca_the(resultSet.getString("ma_so_ca_the"));
                pig.setTuoi(resultSet.getString("tuoi"));
                pig.setNguoi_danh_gia(resultSet.getString("nguoi_danh_gia"));
                pig.setDia_diem_danh_gia(resultSet.getString("dia_diem_danh_gia"));
                if (resultSet.getDate("ngay_danh_gia") != null) {
                    pig.setNgay_danh_gia(resultSet.getDate("ngay_danh_gia").toString());
                }
                pig.setGioi_tinh(resultSet.getString("gioi_tinh"));
                pig.setNhan_trung_trang(resultSet.getString("nhan_trung_trang"));
                pig.setTai_meo(resultSet.getString("tai_meo"));
                pig.setMom(resultSet.getString("mom"));
                pig.setVanh_trang_quanh_mom(resultSet.getString("vanh_trang_quanh_mom"));
                pig.setMat(resultSet.getString("mat"));
                pig.setDa_co_khoang_den(resultSet.getString("da_co_khoang_den"));
                pig.setKhoang_den_phu(resultSet.getString("khoang_den_phu"));
                pig.setKieu_lung_bung(resultSet.getString("kieu_lung_bung"));
                pig.setChan(resultSet.getString("chan"));
                pig.setDa_mat(resultSet.getString("da_mat"));
                pig.setKieu_duoi(resultSet.getString("kieu_duoi"));
                pig.setKieu_tai(resultSet.getString("kieu_tai"));
                pig.setHuong_tai(resultSet.getString("huong_tai"));
                pig.setKieu_long(resultSet.getString("kieu_long"));
                pig.setLoai_mau_long(resultSet.getString("loai_mau_long"));
                pig.setHinh_dang_dau(resultSet.getString("hinh_dang_dau"));
                pig.setTrong_luong_co_the_kg(resultSet.getDouble("trong_luong_co_the_kg"));
                pig.setChieu_dai_dau_cm(resultSet.getDouble("chieu_dai_dau_cm"));
                pig.setChieu_dai_duoi_cm(resultSet.getDouble("chieu_dai_duoi_cm"));
                pig.setChieu_rong_nguc_cm(resultSet.getDouble("chieu_rong_nguc_cm"));
                pig.setChieu_cao_than_tai_chan_truoc_cm(resultSet.getDouble("chieu_cao_than_tai_chan_truoc_cm"));
                pig.setChieu_dai_co_the_cm(resultSet.getDouble("chieu_dai_co_the_cm"));
                pig.setChieu_dai_tai_cm(resultSet.getDouble("chieu_dai_tai_cm"));
                pig.setChieu_rong_than_tai_xuong_chau_cm(resultSet.getDouble("chieu_rong_than_tai_xuong_chau_cm"));
                pig.setChu_vi_co_chan_cm(resultSet.getDouble("chu_vi_co_chan_cm"));
                pig.setDo_rong_dai_trang_vat_vai_cm(resultSet.getDouble("do_rong_dai_trang_vat_vai_cm"));
                pig.setSo_bau_vu(resultSet.getInt("so_bau_vu"));
                pig.setLoai_con_giong(resultSet.getString("loai_con_giong"));
                pig.setSo_con_trong_dan(resultSet.getInt("so_con_trong_dan"));
                pig.setSo_con_duc_trong_dan(resultSet.getInt("so_con_duc_trong_dan"));
                pig.setSo_con_cai_trong_dan(resultSet.getInt("so_con_cai_trong_dan"));
                pig.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                pig.setHinh_anh_2(resultSet.getString("hinh_anh_2"));
                pig.setHinh_dang_long(resultSet.getString("hinh_dang_long"));
                pig.setDo_dai_long(resultSet.getString("do_dai_long"));
                pig.setDo_day_long(resultSet.getString("do_day_long"));
                pig.setPha_he(resultSet.getString("pha_he"));
                if (resultSet.getDate("ngay_nhap") != null) {
                    pig.setNgay_nhap(resultSet.getDate("ngay_nhap").toString());
                }
                pig.setTrang_thai(resultSet.getInt("trang_thai"));
                pig.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                listPig.add(pig);
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getAllPig!");
        }
        return listPig;
    }

    public List<Pig> getAllPigByFilter(Pig pigFilter) {
        List<Pig> listPig = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id," +
                    "dau_thoi_gian, " +
                    "ma_so_ca_the, " +
                    "tuoi, " +
                    "nguoi_danh_gia, " +
                    "dia_diem_danh_gia, " +
                    "ngay_danh_gia, " +
                    "gioi_tinh, " +
                    "nhan_trung_trang, " +
                    "tai_meo, " +
                    "mom, " +
                    "vanh_trang_quanh_mom, " +
                    "mat, " +
                    "da_co_khoang_den, " +
                    "khoang_den_phu, " +
                    "kieu_lung_bung, " +
                    "chan, " +
                    "da_mat, " +
                    "kieu_duoi, " +
                    "kieu_tai, " +
                    "huong_tai, " +
                    "kieu_long, " +
                    "loai_mau_long, " +
                    "hinh_dang_dau, " +
                    "trong_luong_co_the_kg, " +
                    "chieu_dai_dau_cm, " +
                    "chieu_dai_duoi_cm, " +
                    "chieu_rong_nguc_cm, " +
                    "chieu_cao_than_tai_chan_truoc_cm, " +
                    "chieu_dai_co_the_cm, " +
                    "chieu_dai_tai_cm, " +
                    "chieu_rong_than_tai_xuong_chau_cm, " +
                    "chu_vi_co_chan_cm, " +
                    "do_rong_dai_trang_vat_vai_cm, " +
                    "so_bau_vu, " +
                    "loai_con_giong, " +
                    "so_con_trong_dan, " +
                    "so_con_duc_trong_dan, " +
                    "so_con_cai_trong_dan, " +
                    "hinh_anh_1, " +
                    "hinh_anh_2, " +
                    "hinh_dang_long, " +
                    "do_dai_long, " +
                    "do_day_long, " +
                    "pha_he, " +
                    "ngay_nhap, " +
                    "trang_thai, " +
                    "nguoi_nhap " +
                    "FROM tblpig " +
                    "WHERE trang_thai = 1 ";

            if (pigFilter.getKeyWord() != null && pigFilter.getKeyWord().length() > 0) {
                sqlQuery += " AND ( " +
                        "ma_so_ca_the LIKE ? " +
                        "OR gioi_tinh LIKE ? " +
                        "OR nhan_trung_trang LIKE ? " +
                        "OR tai_meo LIKE ? " +
                        "OR mom LIKE ? " +
                        "OR vanh_trang_quanh_mom LIKE ? " +
                        "OR mat LIKE ? " +
                        "OR da_co_khoang_den LIKE ? " +
                        "OR khoang_den_phu LIKE ? " +
                        "OR kieu_lung_bung LIKE ? " +
                        "OR chan LIKE ? " +
                        "OR da_mat LIKE ? " +
                        "OR kieu_duoi LIKE ? " +
                        "OR kieu_tai LIKE ? " +
                        "OR huong_tai LIKE ? " +
                        "OR kieu_long LIKE ? " +
                        "OR loai_mau_long LIKE ? " +
                        "OR hinh_dang_dau LIKE ? ) ";
            }
            if (pigFilter.getGioi_tinh() != null && pigFilter.getGioi_tinh().length() > 0) {
                sqlQuery += "AND gioi_tinh LIKE ? ";
            }
            if (pigFilter.getNhan_trung_trang() != null && pigFilter.getNhan_trung_trang().length() > 0) {
                sqlQuery += "AND nhan_trung_trang LIKE ? ";
            }
            if (pigFilter.getTai_meo() != null && pigFilter.getTai_meo().length() > 0) {
                sqlQuery += "AND tai_meo LIKE ? ";
            }
            if (pigFilter.getMom() != null && pigFilter.getMom().length() > 0) {
                sqlQuery += "AND mom LIKE ? ";
            }
            if (pigFilter.getVanh_trang_quanh_mom() != null && pigFilter.getVanh_trang_quanh_mom().length() > 0) {
                sqlQuery += "AND vanh_trang_quanh_mom LIKE ? ";
            }
            if (pigFilter.getMat() != null && pigFilter.getMat().length() > 0) {
                sqlQuery += "AND mat LIKE ? ";
            }
            if (pigFilter.getDa_co_khoang_den() != null && pigFilter.getDa_co_khoang_den().length() > 0) {
                sqlQuery += "AND da_co_khoang_den LIKE ? ";
            }
            if (pigFilter.getKhoang_den_phu() != null && pigFilter.getKhoang_den_phu().length() > 0) {
                sqlQuery += "AND khoang_den_phu LIKE ? ";
            }
            if (pigFilter.getKieu_lung_bung() != null && pigFilter.getKieu_lung_bung().length() > 0) {
                sqlQuery += "AND kieu_lung_bung LIKE ? ";
            }
            if (pigFilter.getChan() != null && pigFilter.getChan().length() > 0) {
                sqlQuery += "AND chan LIKE ? ";
            }
            if (pigFilter.getDa_mat() != null && pigFilter.getDa_mat().length() > 0) {
                sqlQuery += "AND da_mat LIKE ? ";
            }
            if (pigFilter.getKieu_duoi() != null && pigFilter.getKieu_duoi().length() > 0) {
                sqlQuery += "AND kieu_duoi LIKE ? ";
            }
            if (pigFilter.getKieu_tai() != null && pigFilter.getKieu_tai().length() > 0) {
                sqlQuery += "AND kieu_tai LIKE ? ";
            }
            if (pigFilter.getHuong_tai() != null && pigFilter.getHuong_tai().length() > 0) {
                sqlQuery += "AND huong_tai LIKE ? ";
            }
            if (pigFilter.getKieu_long() != null && pigFilter.getKieu_long().length() > 0) {
                sqlQuery += "AND kieu_long LIKE ? ";
            }
            if (pigFilter.getLoai_mau_long() != null && pigFilter.getLoai_mau_long().length() > 0) {
                sqlQuery += "AND loai_mau_long LIKE ? ";
            }
            if (pigFilter.getHinh_dang_dau() != null && pigFilter.getHinh_dang_dau().length() > 0) {
                sqlQuery += "AND hinh_dang_dau LIKE ? ";
            }

            sqlQuery += " ORDER BY ngay_nhap DESC";

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            if (pigFilter.getKeyWord() != null && pigFilter.getKeyWord().length() > 0) {
                statement.setString(1, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(2, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(3, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(4, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(5, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(6, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(7, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(8, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(9, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(10, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(11, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(12, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(13, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(14, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(15, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(16, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(17, "%" + pigFilter.getKeyWord() + "%");
                statement.setString(18, "%" + pigFilter.getKeyWord() + "%");

            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pig pig = new Pig();
                pig.setId(resultSet.getInt("id"));
                pig.setDau_thoi_gian(resultSet.getString("dau_thoi_gian"));
                pig.setMa_so_ca_the(resultSet.getString("ma_so_ca_the"));
                pig.setTuoi(resultSet.getString("tuoi"));
                pig.setNguoi_danh_gia(resultSet.getString("nguoi_danh_gia"));
                pig.setDia_diem_danh_gia(resultSet.getString("dia_diem_danh_gia"));
                if (resultSet.getDate("ngay_danh_gia") != null) {
                    pig.setNgay_danh_gia(resultSet.getDate("ngay_danh_gia").toString());
                }
                pig.setGioi_tinh(resultSet.getString("gioi_tinh"));
                pig.setNhan_trung_trang(resultSet.getString("nhan_trung_trang"));
                pig.setTai_meo(resultSet.getString("tai_meo"));
                pig.setMom(resultSet.getString("mom"));
                pig.setVanh_trang_quanh_mom(resultSet.getString("vanh_trang_quanh_mom"));
                pig.setMat(resultSet.getString("mat"));
                pig.setDa_co_khoang_den(resultSet.getString("da_co_khoang_den"));
                pig.setKhoang_den_phu(resultSet.getString("khoang_den_phu"));
                pig.setKieu_lung_bung(resultSet.getString("kieu_lung_bung"));
                pig.setChan(resultSet.getString("chan"));
                pig.setDa_mat(resultSet.getString("da_mat"));
                pig.setKieu_duoi(resultSet.getString("kieu_duoi"));
                pig.setKieu_tai(resultSet.getString("kieu_tai"));
                pig.setHuong_tai(resultSet.getString("huong_tai"));
                pig.setKieu_long(resultSet.getString("kieu_long"));
                pig.setLoai_mau_long(resultSet.getString("loai_mau_long"));
                pig.setHinh_dang_dau(resultSet.getString("hinh_dang_dau"));
                pig.setTrong_luong_co_the_kg(resultSet.getDouble("trong_luong_co_the_kg"));
                pig.setChieu_dai_dau_cm(resultSet.getDouble("chieu_dai_dau_cm"));
                pig.setChieu_dai_duoi_cm(resultSet.getDouble("chieu_dai_duoi_cm"));
                pig.setChieu_rong_nguc_cm(resultSet.getDouble("chieu_rong_nguc_cm"));
                pig.setChieu_cao_than_tai_chan_truoc_cm(resultSet.getDouble("chieu_cao_than_tai_chan_truoc_cm"));
                pig.setChieu_dai_co_the_cm(resultSet.getDouble("chieu_dai_co_the_cm"));
                pig.setChieu_dai_tai_cm(resultSet.getDouble("chieu_dai_tai_cm"));
                pig.setChieu_rong_than_tai_xuong_chau_cm(resultSet.getDouble("chieu_rong_than_tai_xuong_chau_cm"));
                pig.setChu_vi_co_chan_cm(resultSet.getDouble("chu_vi_co_chan_cm"));
                pig.setDo_rong_dai_trang_vat_vai_cm(resultSet.getDouble("do_rong_dai_trang_vat_vai_cm"));
                pig.setSo_bau_vu(resultSet.getInt("so_bau_vu"));
                pig.setLoai_con_giong(resultSet.getString("loai_con_giong"));
                pig.setSo_con_trong_dan(resultSet.getInt("so_con_trong_dan"));
                pig.setSo_con_duc_trong_dan(resultSet.getInt("so_con_duc_trong_dan"));
                pig.setSo_con_cai_trong_dan(resultSet.getInt("so_con_cai_trong_dan"));
                pig.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                pig.setHinh_anh_2(resultSet.getString("hinh_anh_2"));
                pig.setHinh_dang_long(resultSet.getString("hinh_dang_long"));
                pig.setDo_dai_long(resultSet.getString("do_dai_long"));
                pig.setDo_day_long(resultSet.getString("do_day_long"));
                pig.setPha_he(resultSet.getString("pha_he"));
                if (resultSet.getDate("ngay_nhap") != null) {
                    pig.setNgay_nhap(resultSet.getDate("ngay_nhap").toString());
                }
                pig.setTrang_thai(resultSet.getInt("trang_thai"));
                pig.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                listPig.add(pig);
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getAllPigByFilter!");
        }
        return listPig;
    }

    public String getPigName(String pigName) {
        String pigCodeDB = null;
        try {
            String sqlQuery = "SELECT " +
                    "name " +
                    "FROM tblpig " +
                    "WHERE " +
                    "name = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, pigName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pigCodeDB = resultSet.getString("name");
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getPigName!");
        }
        return pigCodeDB;
    }

    public String getPigCode(String pigCode) {
        String pigCodeDB = null;
        try {
            String sqlQuery = "SELECT " +
                    "ma_so_ca_the " +
                    "FROM tblpig " +
                    "WHERE " +
                    "ma_so_ca_the = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, pigCode);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pigCodeDB = resultSet.getString("ma_so_ca_the");
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getPigCode!");
        }
        return pigCodeDB;
    }

    public void updatePig(Pig pig) {
        try {
            String sqlQuery = "UPDATE tblpig " +
                    "SET " +
                    "tuoi = ?, " +
                    "nguoi_danh_gia = ?, " +
                    "dia_diem_danh_gia = ?, " +
                    "ngay_danh_gia = ?, " +
                    "gioi_tinh = ?, " +
                    "nhan_trung_trang = ?, " +
                    "tai_meo = ?, " +
                    "mom = ?, " +
                    "vanh_trang_quanh_mom = ?, " +
                    "mat = ?, " +
                    "da_co_khoang_den = ?, " +
                    "khoang_den_phu = ?, " +
                    "kieu_lung_bung = ?, " +
                    "chan = ?, " +
                    "da_mat = ?, " +
                    "kieu_duoi = ?, " +
                    "kieu_tai = ?, " +
                    "huong_tai = ?, " +
                    "kieu_long = ?, " +
                    "loai_mau_long = ?, " +
                    "hinh_dang_dau = ?, " +
                    "trong_luong_co_the_kg = ?, " +
                    "chieu_dai_dau_cm = ?, " +
                    "chieu_dai_duoi_cm = ?, " +
                    "chieu_rong_nguc_cm = ?, " +
                    "chieu_cao_than_tai_chan_truoc_cm = ?, " +
                    "chieu_dai_co_the_cm = ?, " +
                    "chieu_dai_tai_cm = ?, " +
                    "chieu_rong_than_tai_xuong_chau_cm = ?, " +
                    "chu_vi_co_chan_cm = ?, " +
                    "do_rong_dai_trang_vat_vai_cm = ?, " +
                    "so_bau_vu = ?, " +
                    "loai_con_giong = ?, " +
                    "so_con_trong_dan = ?, " +
                    "so_con_duc_trong_dan = ?, " +
                    "so_con_cai_trong_dan = ?, " +
                    "hinh_anh_1 = ?, " +
                    "hinh_anh_2 = ?, " +
                    "hinh_dang_long = ?, " +
                    "do_dai_long = ?, " +
                    "do_day_long = ?, " +
                    "pha_he = ? " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            if (pig.getTuoi() != null) {
                statement.setString(1, pig.getTuoi());
            } else {
                statement.setString(1, null);
            }
            if (pig.getNguoi_danh_gia() != null) {
                statement.setString(2, pig.getNguoi_danh_gia());
            } else {
                statement.setString(2, null);
            }
            if (pig.getDia_diem_danh_gia() != null) {
                statement.setString(3, pig.getDia_diem_danh_gia());
            } else {
                statement.setString(3, null);
            }
            if (pig.getNgay_danh_gia() != null) {
                statement.setString(4, pig.getNgay_danh_gia());
            } else {
                statement.setString(4, null);
            }
            if (pig.getGioi_tinh() != null) {
                statement.setString(5, pig.getGioi_tinh());
            } else {
                statement.setString(5, null);
            }
            if (pig.getNhan_trung_trang() != null) {
                statement.setString(6, pig.getNhan_trung_trang());
            } else {
                statement.setString(6, null);
            }
            if (pig.getTai_meo() != null) {
                statement.setString(7, pig.getTai_meo());
            } else {
                statement.setString(7, null);
            }
            if (pig.getMom() != null) {
                statement.setString(8, pig.getMom());
            } else {
                statement.setString(8, null);
            }
            if (pig.getVanh_trang_quanh_mom() != null) {
                statement.setString(9, pig.getVanh_trang_quanh_mom());
            } else {
                statement.setString(9, null);
            }
            if (pig.getMat() != null) {
                statement.setString(10, pig.getMat());
            } else {
                statement.setString(10, null);
            }
            if (pig.getDa_co_khoang_den() != null) {
                statement.setString(11, pig.getDa_co_khoang_den());
            } else {
                statement.setString(11, null);
            }
            if (pig.getKhoang_den_phu() != null) {
                statement.setString(12, pig.getKhoang_den_phu());
            } else {
                statement.setString(12, null);
            }
            if (pig.getKieu_lung_bung() != null) {
                statement.setString(13, pig.getKieu_lung_bung());
            } else {
                statement.setString(13, null);
            }
            if (pig.getChan() != null) {
                statement.setString(14, pig.getChan());
            } else {
                statement.setString(14, null);
            }
            if (pig.getDa_mat() != null) {
                statement.setString(15, pig.getDa_mat());
            } else {
                statement.setString(15, null);
            }
            if (pig.getKieu_duoi() != null) {
                statement.setString(16, pig.getKieu_duoi());
            } else {
                statement.setString(16, null);
            }
            if (pig.getKieu_tai() != null) {
                statement.setString(17, pig.getKieu_tai());
            } else {
                statement.setString(17, null);
            }
            if (pig.getHuong_tai() != null) {
                statement.setString(18, pig.getHuong_tai());
            } else {
                statement.setString(18, null);
            }
            if (pig.getKieu_long() != null) {
                statement.setString(19, pig.getKieu_long());
            } else {
                statement.setString(19, null);
            }
            if (pig.getLoai_mau_long() != null) {
                statement.setString(20, pig.getLoai_mau_long());
            } else {
                statement.setString(20, null);
            }
            if (pig.getHinh_dang_dau() != null) {
                statement.setString(21, pig.getHinh_dang_dau());
            } else {
                statement.setString(21, null);
            }
            if (pig.getTrong_luong_co_the_kg() != null) {
                statement.setDouble(22, pig.getTrong_luong_co_the_kg());
            } else {
                statement.setString(22, null);
            }
            if (pig.getChieu_dai_dau_cm() != null) {
                statement.setDouble(23, pig.getChieu_dai_dau_cm());
            } else {
                statement.setString(23, null);
            }
            if (pig.getChieu_dai_duoi_cm() != null) {
                statement.setDouble(24, pig.getChieu_dai_duoi_cm());
            } else {
                statement.setString(24, null);
            }
            if (pig.getChieu_rong_nguc_cm() != null) {
                statement.setDouble(25, pig.getChieu_rong_nguc_cm());
            } else {
                statement.setString(25, null);
            }
            if (pig.getChieu_cao_than_tai_chan_truoc_cm() != null) {
                statement.setDouble(26, pig.getChieu_cao_than_tai_chan_truoc_cm());
            } else {
                statement.setString(26, null);
            }
            if (pig.getChieu_dai_co_the_cm() != null) {
                statement.setDouble(27, pig.getChieu_dai_co_the_cm());
            } else {
                statement.setString(27, null);
            }
            if (pig.getChieu_dai_tai_cm() != null) {
                statement.setDouble(28, pig.getChieu_dai_tai_cm());
            } else {
                statement.setString(28, null);
            }
            if (pig.getChieu_rong_than_tai_xuong_chau_cm() != null) {
                statement.setDouble(29, pig.getChieu_rong_than_tai_xuong_chau_cm());
            } else {
                statement.setString(29, null);
            }
            if (pig.getChu_vi_co_chan_cm() != null) {
                statement.setDouble(30, pig.getChu_vi_co_chan_cm());
            } else {
                statement.setString(30, null);
            }
            if (pig.getDo_rong_dai_trang_vat_vai_cm() != null) {
                statement.setDouble(31, pig.getDo_rong_dai_trang_vat_vai_cm());
            } else {
                statement.setString(31, null);
            }
            if (pig.getSo_bau_vu() != null) {
                statement.setInt(32, pig.getSo_bau_vu());
            } else {
                statement.setString(32, null);
            }
            if (pig.getLoai_con_giong() != null) {
                statement.setString(33, pig.getLoai_con_giong());
            } else {
                statement.setString(33, null);
            }
            if (pig.getSo_con_trong_dan() != null) {
                statement.setDouble(34, pig.getSo_con_trong_dan());
            } else {
                statement.setString(34, null);
            }
            if (pig.getSo_con_duc_trong_dan() != null) {
                statement.setDouble(35, pig.getSo_con_duc_trong_dan());
            } else {
                statement.setString(35, null);
            }
            if (pig.getSo_con_cai_trong_dan() != null) {
                statement.setDouble(36, pig.getSo_con_cai_trong_dan());
            } else {
                statement.setString(36, null);
            }
            if (pig.getHinh_anh_1() != null) {
                statement.setString(37, pig.getHinh_anh_1());
            } else {
                statement.setString(37, null);
            }
            if (pig.getHinh_anh_2() != null) {
                statement.setString(38, pig.getHinh_anh_2());
            } else {
                statement.setString(38, null);
            }
            if (pig.getHinh_dang_long() != null) {
                statement.setString(39, pig.getHinh_dang_long());
            } else {
                statement.setString(39, null);
            }
            if (pig.getDo_dai_long() != null) {
                statement.setString(40, pig.getDo_dai_long());
            } else {
                statement.setString(40, null);
            }
            if (pig.getDo_day_long() != null) {
                statement.setString(41, pig.getDo_day_long());
            } else {
                statement.setString(41, null);
            }
            if (pig.getPha_he() != null) {
                statement.setString(42, pig.getPha_he());
            } else {
                statement.setString(42, null);
            }
            statement.setInt(43, pig.getId());
            statement.executeUpdate();
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - updatePig!");
        }
    }

    public void updatePigStatus(Pig pig) {
        try {
            String sqlQuery = "UPDATE tblpig " +
                    "SET " +
                    "trang_thai = ? " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, pig.getTrang_thai());
            statement.setInt(2, pig.getId());
            statement.executeUpdate();
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - updatePigStatus!");
        }
    }

    public void savePig(Pig pig) {
        try {
            Date dateCurrent = new Date();
            String sqlQuery = "INSERT INTO tblpig " +
                    "(dau_thoi_gian, ma_so_ca_the, tuoi, nguoi_danh_gia, dia_diem_danh_gia, ngay_danh_gia, " +
                    "gioi_tinh, nhan_trung_trang, tai_meo, mom, vanh_trang_quanh_mom, mat, da_co_khoang_den, " +
                    "khoang_den_phu, kieu_lung_bung, chan, da_mat, kieu_duoi, kieu_tai, huong_tai, kieu_long, " +
                    "loai_mau_long, hinh_dang_dau, trong_luong_co_the_kg, chieu_dai_dau_cm, chieu_dai_duoi_cm, " +
                    "chieu_rong_nguc_cm, chieu_cao_than_tai_chan_truoc_cm, chieu_dai_co_the_cm, chieu_dai_tai_cm, " +
                    "chieu_rong_than_tai_xuong_chau_cm, chu_vi_co_chan_cm, do_rong_dai_trang_vat_vai_cm, " +
                    "so_bau_vu, loai_con_giong, so_con_trong_dan, so_con_duc_trong_dan, so_con_cai_trong_dan, " +
                    "hinh_anh_1, hinh_anh_2, hinh_dang_long, do_dai_long, do_day_long, pha_he, ngay_nhap) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, pig.getDau_thoi_gian());
            statement.setString(2, pig.getMa_so_ca_the());
            if (pig.getTuoi() != null) {
                statement.setString(3, pig.getTuoi());
            } else {
                statement.setString(3, null);
            }
            if (pig.getNguoi_danh_gia() != null) {
                statement.setString(4, pig.getNguoi_danh_gia());
            } else {
                statement.setString(4, null);
            }
            if (pig.getDia_diem_danh_gia() != null) {
                statement.setString(5, pig.getDia_diem_danh_gia());
            } else {
                statement.setString(5, null);
            }
            if (pig.getNgay_danh_gia() != null) {
                statement.setString(6, pig.getNgay_danh_gia());
            } else {
                statement.setString(6, null);
            }
            if (pig.getGioi_tinh() != null) {
                statement.setString(7, pig.getGioi_tinh());
            } else {
                statement.setString(7, null);
            }
            if (pig.getNhan_trung_trang() != null) {
                statement.setString(8, pig.getNhan_trung_trang());
            } else {
                statement.setString(8, null);
            }
            if (pig.getTai_meo() != null) {
                statement.setString(9, pig.getTai_meo());
            } else {
                statement.setString(9, null);
            }
            if (pig.getMom() != null) {
                statement.setString(10, pig.getMom());
            } else {
                statement.setString(10, null);
            }
            if (pig.getVanh_trang_quanh_mom() != null) {
                statement.setString(11, pig.getVanh_trang_quanh_mom());
            } else {
                statement.setString(11, null);
            }
            if (pig.getMat() != null) {
                statement.setString(12, pig.getMat());
            } else {
                statement.setString(12, null);
            }
            if (pig.getDa_co_khoang_den() != null) {
                statement.setString(13, pig.getDa_co_khoang_den());
            } else {
                statement.setString(13, null);
            }
            if (pig.getKhoang_den_phu() != null) {
                statement.setString(14, pig.getKhoang_den_phu());
            } else {
                statement.setString(14, null);
            }
            if (pig.getKieu_lung_bung() != null) {
                statement.setString(15, pig.getKieu_lung_bung());
            } else {
                statement.setString(15, null);
            }
            if (pig.getChan() != null) {
                statement.setString(16, pig.getChan());
            } else {
                statement.setString(16, null);
            }
            if (pig.getDa_mat() != null) {
                statement.setString(17, pig.getDa_mat());
            } else {
                statement.setString(17, null);
            }
            if (pig.getKieu_duoi() != null) {
                statement.setString(18, pig.getKieu_duoi());
            } else {
                statement.setString(18, null);
            }
            if (pig.getKieu_tai() != null) {
                statement.setString(19, pig.getKieu_tai());
            } else {
                statement.setString(19, null);
            }
            if (pig.getHuong_tai() != null) {
                statement.setString(20, pig.getHuong_tai());
            } else {
                statement.setString(20, null);
            }
            if (pig.getKieu_long() != null) {
                statement.setString(21, pig.getKieu_long());
            } else {
                statement.setString(21, null);
            }
            if (pig.getLoai_mau_long() != null) {
                statement.setString(22, pig.getLoai_mau_long());
            } else {
                statement.setString(22, null);
            }
            if (pig.getHinh_dang_dau() != null) {
                statement.setString(23, pig.getHinh_dang_dau());
            } else {
                statement.setString(23, null);
            }
            if (pig.getTrong_luong_co_the_kg() != null) {
                statement.setDouble(24, pig.getTrong_luong_co_the_kg());
            } else {
                statement.setString(24, null);
            }
            if (pig.getChieu_dai_dau_cm() != null) {
                statement.setDouble(25, pig.getChieu_dai_dau_cm());
            } else {
                statement.setString(25, null);
            }
            if (pig.getChieu_dai_duoi_cm() != null) {
                statement.setDouble(26, pig.getChieu_dai_duoi_cm());
            } else {
                statement.setString(26, null);
            }
            if (pig.getChieu_rong_nguc_cm() != null) {
                statement.setDouble(27, pig.getChieu_rong_nguc_cm());
            } else {
                statement.setString(27, null);
            }
            if (pig.getChieu_cao_than_tai_chan_truoc_cm() != null) {
                statement.setDouble(28, pig.getChieu_cao_than_tai_chan_truoc_cm());
            } else {
                statement.setString(28, null);
            }
            if (pig.getChieu_dai_co_the_cm() != null) {
                statement.setDouble(29, pig.getChieu_dai_co_the_cm());
            } else {
                statement.setString(29, null);
            }
            if (pig.getChieu_dai_tai_cm() != null) {
                statement.setDouble(30, pig.getChieu_dai_tai_cm());
            } else {
                statement.setString(30, null);
            }
            if (pig.getChieu_rong_than_tai_xuong_chau_cm() != null) {
                statement.setDouble(31, pig.getChieu_rong_than_tai_xuong_chau_cm());
            } else {
                statement.setString(31, null);
            }
            if (pig.getChu_vi_co_chan_cm() != null) {
                statement.setDouble(32, pig.getChu_vi_co_chan_cm());
            } else {
                statement.setString(32, null);
            }
            if (pig.getDo_rong_dai_trang_vat_vai_cm() != null) {
                statement.setDouble(33, pig.getDo_rong_dai_trang_vat_vai_cm());
            } else {
                statement.setString(33, null);
            }
            if (pig.getSo_bau_vu() != null) {
                statement.setInt(34, pig.getSo_bau_vu());
            } else {
                statement.setString(34, null);
            }
            if (pig.getLoai_con_giong() != null) {
                statement.setString(35, pig.getLoai_con_giong());
            } else {
                statement.setString(35, null);
            }
            if (pig.getSo_con_trong_dan() != null) {
                statement.setDouble(36, pig.getSo_con_trong_dan());
            } else {
                statement.setString(36, null);
            }
            if (pig.getSo_con_duc_trong_dan() != null) {
                statement.setDouble(37, pig.getSo_con_duc_trong_dan());
            } else {
                statement.setString(37, null);
            }
            if (pig.getSo_con_cai_trong_dan() != null) {
                statement.setDouble(38, pig.getSo_con_cai_trong_dan());
            } else {
                statement.setString(38, null);
            }
            if (pig.getHinh_anh_1() != null) {
                statement.setString(39, pig.getHinh_anh_1());
            } else {
                statement.setString(39, null);
            }
            if (pig.getHinh_anh_2() != null) {
                statement.setString(40, pig.getHinh_anh_2());
            } else {
                statement.setString(40, null);
            }
            if (pig.getHinh_dang_long() != null) {
                statement.setString(41, pig.getHinh_dang_long());
            } else {
                statement.setString(41, null);
            }
            if (pig.getDo_dai_long() != null) {
                statement.setString(42, pig.getDo_dai_long());
            } else {
                statement.setString(42, null);
            }
            if (pig.getDo_day_long() != null) {
                statement.setString(43, pig.getDo_day_long());
            } else {
                statement.setString(43, null);
            }
            if (pig.getPha_he() != null) {
                statement.setString(44, pig.getPha_he());
            } else {
                statement.setString(44, null);
            }
            statement.setTimestamp(45, new Timestamp(dateCurrent.getTime()));
            statement.execute();
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - savePig!");
        }
    }


}
