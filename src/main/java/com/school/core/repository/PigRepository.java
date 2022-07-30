package com.school.core.repository;

import com.school.core.entity.Pig;
import com.school.core.entity.PigLandingPage;
import com.school.core.entity.PigSpecial;
import com.school.core.response.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PigRepository extends BaseRepository {
    private final Logger log = LoggerFactory.getLogger(PigRepository.class);

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
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getPigLimit12!");
        }
        return listPigLimit12;
    }

    public List<PigSpecial> getPigSpecial() {
        List<PigSpecial> listPigSpecial = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id, " +
                    "ma_so_ca_the, " +
                    "hinh_anh_1, " +
                    "ca_the_tieu_bieu, " +
                    "vi_tri_tieu_bieu, " +
                    "tuoi " +
                    "FROM tblpig " +
                    "WHERE trang_thai = 1 " +
                    "AND ca_the_tieu_bieu = 1 " +
                    "ORDER BY vi_tri_tieu_bieu ASC" ;
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PigSpecial pigSpecial = new PigSpecial();
                pigSpecial.setId(resultSet.getInt("id"));
                pigSpecial.setMa_so_ca_the(resultSet.getString("ma_so_ca_the"));
                pigSpecial.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                pigSpecial.setCa_the_tieu_bieu(resultSet.getInt("ca_the_tieu_bieu"));
                pigSpecial.setVi_tri_tieu_bieu(resultSet.getInt("vi_tri_tieu_bieu"));
                pigSpecial.setTuoi(resultSet.getString("tuoi"));
                listPigSpecial.add(pigSpecial);
            }
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getPigSpecial!");
        }
        return listPigSpecial;
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
            log.info("SQL: " + sqlQuery);
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
                    "nguoi_nhap, " +
                    "nguoi_cap_nhat, " +
                    "ngay_cap_nhat " +
                    "FROM tblpig " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pig.setId(resultSet.getInt("id"));
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
                pig.setTrong_luong_co_the_kg(String.valueOf(resultSet.getDouble("trong_luong_co_the_kg")));
                pig.setChieu_dai_dau_cm(String.valueOf(resultSet.getDouble("chieu_dai_dau_cm")));
                pig.setChieu_dai_duoi_cm(String.valueOf(resultSet.getDouble("chieu_dai_duoi_cm")));
                pig.setChieu_rong_nguc_cm(String.valueOf(resultSet.getDouble("chieu_rong_nguc_cm")));
                pig.setChieu_cao_than_tai_chan_truoc_cm(String.valueOf(resultSet.getDouble("chieu_cao_than_tai_chan_truoc_cm")));
                pig.setChieu_dai_co_the_cm(String.valueOf(resultSet.getDouble("chieu_dai_co_the_cm")));
                pig.setChieu_dai_tai_cm(String.valueOf(resultSet.getDouble("chieu_dai_tai_cm")));
                pig.setChieu_rong_than_tai_xuong_chau_cm(String.valueOf(resultSet.getDouble("chieu_rong_than_tai_xuong_chau_cm")));
                pig.setChu_vi_co_chan_cm(String.valueOf(resultSet.getDouble("chu_vi_co_chan_cm")));
                pig.setDo_rong_dai_trang_vat_vai_cm(String.valueOf(resultSet.getDouble("do_rong_dai_trang_vat_vai_cm")));
                pig.setSo_bau_vu(String.valueOf(resultSet.getInt("so_bau_vu")));
                pig.setLoai_con_giong(resultSet.getString("loai_con_giong"));
                pig.setSo_con_trong_dan(String.valueOf(resultSet.getInt("so_con_trong_dan")));
                pig.setSo_con_duc_trong_dan(String.valueOf(resultSet.getInt("so_con_duc_trong_dan")));
                pig.setSo_con_cai_trong_dan(String.valueOf(resultSet.getInt("so_con_cai_trong_dan")));
                pig.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                pig.setHinh_anh_2(resultSet.getString("hinh_anh_2"));
                pig.setHinh_dang_long(resultSet.getString("hinh_dang_long"));
                pig.setDo_dai_long(resultSet.getString("do_dai_long"));
                pig.setDo_day_long(resultSet.getString("do_day_long"));
                pig.setPha_he(resultSet.getString("pha_he"));
                pig.setNgay_nhap(resultSet.getString("ngay_nhap"));
                pig.setTrang_thai(resultSet.getInt("trang_thai"));
                pig.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                pig.setNguoi_cap_nhat(resultSet.getString("nguoi_cap_nhat"));
                pig.setNgay_cap_nhat(resultSet.getString("ngay_cap_nhat"));
            }
            log.info("SQL: " + sqlQuery);
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
                    "nguoi_nhap, " +
                    "nguoi_cap_nhat, " +
                    "ngay_cap_nhat " +
                    "FROM tblpig " +
                    "WHERE dia_diem_danh_gia = ? " +
                    "AND trang_thai = 1";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, farmName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pig pig = new Pig();
                pig.setId(resultSet.getInt("id"));
                pig.setMa_so_ca_the(resultSet.getString("ma_so_ca_the"));
                pig.setTuoi(resultSet.getString("tuoi"));
                pig.setNguoi_danh_gia(resultSet.getString("nguoi_danh_gia"));
                pig.setDia_diem_danh_gia(resultSet.getString("dia_diem_danh_gia"));
                pig.setNgay_danh_gia(resultSet.getString("ngay_danh_gia"));
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
                pig.setTrong_luong_co_the_kg(String.valueOf(resultSet.getDouble("trong_luong_co_the_kg")));
                pig.setChieu_dai_dau_cm(String.valueOf(resultSet.getDouble("chieu_dai_dau_cm")));
                pig.setChieu_dai_duoi_cm(String.valueOf(resultSet.getDouble("chieu_dai_duoi_cm")));
                pig.setChieu_rong_nguc_cm(String.valueOf(resultSet.getDouble("chieu_rong_nguc_cm")));
                pig.setChieu_cao_than_tai_chan_truoc_cm(String.valueOf(resultSet.getDouble("chieu_cao_than_tai_chan_truoc_cm")));
                pig.setChieu_dai_co_the_cm(String.valueOf(resultSet.getDouble("chieu_dai_co_the_cm")));
                pig.setChieu_dai_tai_cm(String.valueOf(resultSet.getDouble("chieu_dai_tai_cm")));
                pig.setChieu_rong_than_tai_xuong_chau_cm(String.valueOf(resultSet.getDouble("chieu_rong_than_tai_xuong_chau_cm")));
                pig.setChu_vi_co_chan_cm(String.valueOf(resultSet.getDouble("chu_vi_co_chan_cm")));
                pig.setDo_rong_dai_trang_vat_vai_cm(String.valueOf(resultSet.getDouble("do_rong_dai_trang_vat_vai_cm")));
                pig.setSo_bau_vu(String.valueOf(resultSet.getInt("so_bau_vu")));
                pig.setLoai_con_giong(resultSet.getString("loai_con_giong"));
                pig.setSo_con_trong_dan(String.valueOf(resultSet.getInt("so_con_trong_dan")));
                pig.setSo_con_duc_trong_dan(String.valueOf(resultSet.getInt("so_con_duc_trong_dan")));
                pig.setSo_con_cai_trong_dan(String.valueOf(resultSet.getInt("so_con_cai_trong_dan")));
                pig.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                pig.setHinh_anh_2(resultSet.getString("hinh_anh_2"));
                pig.setHinh_dang_long(resultSet.getString("hinh_dang_long"));
                pig.setDo_dai_long(resultSet.getString("do_dai_long"));
                pig.setDo_day_long(resultSet.getString("do_day_long"));
                pig.setPha_he(resultSet.getString("pha_he"));
                pig.setNgay_nhap(resultSet.getString("ngay_nhap"));
                pig.setTrang_thai(resultSet.getInt("trang_thai"));
                pig.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                pig.setNguoi_cap_nhat(resultSet.getString("nguoi_cap_nhat"));
                pig.setNgay_cap_nhat(resultSet.getString("ngay_cap_nhat"));
                listPigByFarmName.add(pig);
            }
            log.info("SQL: " + sqlQuery);
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
                    "nguoi_nhap, " +
                    "nguoi_cap_nhat, " +
                    "ngay_cap_nhat " +
                    "FROM tblpig " +
                    "ORDER BY ngay_nhap DESC ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pig pig = new Pig();
                pig.setId(resultSet.getInt("id"));
                pig.setMa_so_ca_the(resultSet.getString("ma_so_ca_the"));
                pig.setTuoi(resultSet.getString("tuoi"));
                pig.setNguoi_danh_gia(resultSet.getString("nguoi_danh_gia"));
                pig.setDia_diem_danh_gia(resultSet.getString("dia_diem_danh_gia"));
                pig.setNgay_danh_gia(resultSet.getString("ngay_danh_gia"));
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
                pig.setTrong_luong_co_the_kg(String.valueOf(resultSet.getDouble("trong_luong_co_the_kg")));
                pig.setChieu_dai_dau_cm(String.valueOf(resultSet.getDouble("chieu_dai_dau_cm")));
                pig.setChieu_dai_duoi_cm(String.valueOf(resultSet.getDouble("chieu_dai_duoi_cm")));
                pig.setChieu_rong_nguc_cm(String.valueOf(resultSet.getDouble("chieu_rong_nguc_cm")));
                pig.setChieu_cao_than_tai_chan_truoc_cm(String.valueOf(resultSet.getDouble("chieu_cao_than_tai_chan_truoc_cm")));
                pig.setChieu_dai_co_the_cm(String.valueOf(resultSet.getDouble("chieu_dai_co_the_cm")));
                pig.setChieu_dai_tai_cm(String.valueOf(resultSet.getDouble("chieu_dai_tai_cm")));
                pig.setChieu_rong_than_tai_xuong_chau_cm(String.valueOf(resultSet.getDouble("chieu_rong_than_tai_xuong_chau_cm")));
                pig.setChu_vi_co_chan_cm(String.valueOf(resultSet.getDouble("chu_vi_co_chan_cm")));
                pig.setDo_rong_dai_trang_vat_vai_cm(String.valueOf(resultSet.getDouble("do_rong_dai_trang_vat_vai_cm")));
                pig.setSo_bau_vu(String.valueOf(resultSet.getInt("so_bau_vu")));
                pig.setLoai_con_giong(resultSet.getString("loai_con_giong"));
                pig.setSo_con_trong_dan(String.valueOf(resultSet.getInt("so_con_trong_dan")));
                pig.setSo_con_duc_trong_dan(String.valueOf(resultSet.getInt("so_con_duc_trong_dan")));
                pig.setSo_con_cai_trong_dan(String.valueOf(resultSet.getInt("so_con_cai_trong_dan")));
                pig.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                pig.setHinh_anh_2(resultSet.getString("hinh_anh_2"));
                pig.setHinh_dang_long(resultSet.getString("hinh_dang_long"));
                pig.setDo_dai_long(resultSet.getString("do_dai_long"));
                pig.setDo_day_long(resultSet.getString("do_day_long"));
                pig.setPha_he(resultSet.getString("pha_he"));
                pig.setNgay_nhap(resultSet.getString("ngay_nhap"));
                pig.setTrang_thai(resultSet.getInt("trang_thai"));
                pig.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                pig.setNguoi_cap_nhat(resultSet.getString("nguoi_cap_nhat"));
                pig.setNgay_cap_nhat(resultSet.getString("ngay_cap_nhat"));
                listPig.add(pig);
            }
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getAllPig!");
        }
        return listPig;
    }

    public List<Pig> getAllPigNotSpecial() {
        List<Pig> listPig = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id," +
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
                    "nguoi_nhap, " +
                    "nguoi_cap_nhat, " +
                    "ngay_cap_nhat " +
                    "FROM tblpig " +
                    "WHERE ca_the_tieu_bieu = 0 OR ca_the_tieu_bieu IS NULL";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pig pig = new Pig();
                pig.setId(resultSet.getInt("id"));
                pig.setMa_so_ca_the(resultSet.getString("ma_so_ca_the"));
                pig.setTuoi(resultSet.getString("tuoi"));
                pig.setNguoi_danh_gia(resultSet.getString("nguoi_danh_gia"));
                pig.setDia_diem_danh_gia(resultSet.getString("dia_diem_danh_gia"));
                pig.setNgay_danh_gia(resultSet.getString("ngay_danh_gia"));
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
                pig.setTrong_luong_co_the_kg(String.valueOf(resultSet.getDouble("trong_luong_co_the_kg")));
                pig.setChieu_dai_dau_cm(String.valueOf(resultSet.getDouble("chieu_dai_dau_cm")));
                pig.setChieu_dai_duoi_cm(String.valueOf(resultSet.getDouble("chieu_dai_duoi_cm")));
                pig.setChieu_rong_nguc_cm(String.valueOf(resultSet.getDouble("chieu_rong_nguc_cm")));
                pig.setChieu_cao_than_tai_chan_truoc_cm(String.valueOf(resultSet.getDouble("chieu_cao_than_tai_chan_truoc_cm")));
                pig.setChieu_dai_co_the_cm(String.valueOf(resultSet.getDouble("chieu_dai_co_the_cm")));
                pig.setChieu_dai_tai_cm(String.valueOf(resultSet.getDouble("chieu_dai_tai_cm")));
                pig.setChieu_rong_than_tai_xuong_chau_cm(String.valueOf(resultSet.getDouble("chieu_rong_than_tai_xuong_chau_cm")));
                pig.setChu_vi_co_chan_cm(String.valueOf(resultSet.getDouble("chu_vi_co_chan_cm")));
                pig.setDo_rong_dai_trang_vat_vai_cm(String.valueOf(resultSet.getDouble("do_rong_dai_trang_vat_vai_cm")));
                pig.setSo_bau_vu(String.valueOf(resultSet.getInt("so_bau_vu")));
                pig.setLoai_con_giong(resultSet.getString("loai_con_giong"));
                pig.setSo_con_trong_dan(String.valueOf(resultSet.getInt("so_con_trong_dan")));
                pig.setSo_con_duc_trong_dan(String.valueOf(resultSet.getInt("so_con_duc_trong_dan")));
                pig.setSo_con_cai_trong_dan(String.valueOf(resultSet.getInt("so_con_cai_trong_dan")));
                pig.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                pig.setHinh_anh_2(resultSet.getString("hinh_anh_2"));
                pig.setHinh_dang_long(resultSet.getString("hinh_dang_long"));
                pig.setDo_dai_long(resultSet.getString("do_dai_long"));
                pig.setDo_day_long(resultSet.getString("do_day_long"));
                pig.setPha_he(resultSet.getString("pha_he"));
                pig.setNgay_nhap(resultSet.getString("ngay_nhap"));
                pig.setTrang_thai(resultSet.getInt("trang_thai"));
                pig.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                pig.setNguoi_cap_nhat(resultSet.getString("nguoi_cap_nhat"));
                pig.setNgay_cap_nhat(resultSet.getString("ngay_cap_nhat"));
                listPig.add(pig);
            }
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - getAllPigNotSpecial!");
        }
        return listPig;
    }

    public List<Pig> getAllPigByFilter(Pig pigFilter) {
        List<Pig> listPig = new ArrayList<>();
        try {
            String sqlQuery = "SELECT " +
                    "id," +
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
                    "nguoi_nhap, " +
                    "nguoi_cap_nhat, " +
                    "ngay_cap_nhat " +
                    "FROM tblpig " +
                    "WHERE trang_thai = 1 ";

            sqlQuery += "AND ( " +
                    "ma_so_ca_the LIKE ? " +
                    "OR nguoi_danh_gia LIKE ? " +
                    "OR dia_diem_danh_gia LIKE ? " +
                    "OR loai_con_giong LIKE ? " +
                    ") ";

            sqlQuery += " ORDER BY ngay_nhap DESC";

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, "%" + pigFilter.getTu_khoa() + "%");
            statement.setString(2, "%" + pigFilter.getTu_khoa() + "%");
            statement.setString(3, "%" + pigFilter.getTu_khoa() + "%");
            statement.setString(4, "%" + pigFilter.getTu_khoa() + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pig pig = new Pig();
                pig.setId(resultSet.getInt("id"));
                pig.setMa_so_ca_the(resultSet.getString("ma_so_ca_the"));
                pig.setTuoi(resultSet.getString("tuoi"));
                pig.setNguoi_danh_gia(resultSet.getString("nguoi_danh_gia"));
                pig.setDia_diem_danh_gia(resultSet.getString("dia_diem_danh_gia"));
                pig.setNgay_danh_gia(resultSet.getString("ngay_danh_gia"));
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
                pig.setTrong_luong_co_the_kg(String.valueOf(resultSet.getDouble("trong_luong_co_the_kg")));
                pig.setChieu_dai_dau_cm(String.valueOf(resultSet.getDouble("chieu_dai_dau_cm")));
                pig.setChieu_dai_duoi_cm(String.valueOf(resultSet.getDouble("chieu_dai_duoi_cm")));
                pig.setChieu_rong_nguc_cm(String.valueOf(resultSet.getDouble("chieu_rong_nguc_cm")));
                pig.setChieu_cao_than_tai_chan_truoc_cm(String.valueOf(resultSet.getDouble("chieu_cao_than_tai_chan_truoc_cm")));
                pig.setChieu_dai_co_the_cm(String.valueOf(resultSet.getDouble("chieu_dai_co_the_cm")));
                pig.setChieu_dai_tai_cm(String.valueOf(resultSet.getDouble("chieu_dai_tai_cm")));
                pig.setChieu_rong_than_tai_xuong_chau_cm(String.valueOf(resultSet.getDouble("chieu_rong_than_tai_xuong_chau_cm")));
                pig.setChu_vi_co_chan_cm(String.valueOf(resultSet.getDouble("chu_vi_co_chan_cm")));
                pig.setDo_rong_dai_trang_vat_vai_cm(String.valueOf(resultSet.getDouble("do_rong_dai_trang_vat_vai_cm")));
                pig.setSo_bau_vu(String.valueOf(resultSet.getInt("so_bau_vu")));
                pig.setLoai_con_giong(resultSet.getString("loai_con_giong"));
                pig.setSo_con_trong_dan(String.valueOf(resultSet.getInt("so_con_trong_dan")));
                pig.setSo_con_duc_trong_dan(String.valueOf(resultSet.getInt("so_con_duc_trong_dan")));
                pig.setSo_con_cai_trong_dan(String.valueOf(resultSet.getInt("so_con_cai_trong_dan")));
                pig.setHinh_anh_1(resultSet.getString("hinh_anh_1"));
                pig.setHinh_anh_2(resultSet.getString("hinh_anh_2"));
                pig.setHinh_dang_long(resultSet.getString("hinh_dang_long"));
                pig.setDo_dai_long(resultSet.getString("do_dai_long"));
                pig.setDo_day_long(resultSet.getString("do_day_long"));
                pig.setPha_he(resultSet.getString("pha_he"));
                pig.setNgay_nhap(resultSet.getString("ngay_nhap"));
                pig.setTrang_thai(resultSet.getInt("trang_thai"));
                pig.setNguoi_nhap(resultSet.getString("nguoi_nhap"));
                pig.setNguoi_cap_nhat(resultSet.getString("nguoi_cap_nhat"));
                pig.setNgay_cap_nhat(resultSet.getString("ngay_cap_nhat"));
                listPig.add(pig);
            }
            log.info("SQL: " + sqlQuery);
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
            log.info("SQL: " + sqlQuery);
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
            log.info("SQL: " + sqlQuery);
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
            Date dateCurrent = new Date();
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
                    "pha_he = ?, " +
                    "nguoi_cap_nhat = ?, " +
                    "ngay_cap_nhat = ? " +
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
                statement.setDouble(22, Double.valueOf(pig.getTrong_luong_co_the_kg()));
            } else {
                statement.setString(22, null);
            }
            if (pig.getChieu_dai_dau_cm() != null) {
                statement.setDouble(23, Double.valueOf(pig.getChieu_dai_dau_cm()));
            } else {
                statement.setString(23, null);
            }
            if (pig.getChieu_dai_duoi_cm() != null) {
                statement.setDouble(24, Double.valueOf(pig.getChieu_dai_duoi_cm()));
            } else {
                statement.setString(24, null);
            }
            if (pig.getChieu_rong_nguc_cm() != null) {
                statement.setDouble(25, Double.valueOf(pig.getChieu_rong_nguc_cm()));
            } else {
                statement.setString(25, null);
            }
            if (pig.getChieu_cao_than_tai_chan_truoc_cm() != null) {
                statement.setDouble(26, Double.valueOf(pig.getChieu_cao_than_tai_chan_truoc_cm()));
            } else {
                statement.setString(26, null);
            }
            if (pig.getChieu_dai_co_the_cm() != null) {
                statement.setDouble(27, Double.valueOf(pig.getChieu_dai_co_the_cm()));
            } else {
                statement.setString(27, null);
            }
            if (pig.getChieu_dai_tai_cm() != null) {
                statement.setDouble(28, Double.valueOf(pig.getChieu_dai_tai_cm()));
            } else {
                statement.setString(28, null);
            }
            if (pig.getChieu_rong_than_tai_xuong_chau_cm() != null) {
                statement.setDouble(29, Double.valueOf(pig.getChieu_rong_than_tai_xuong_chau_cm()));
            } else {
                statement.setString(29, null);
            }
            if (pig.getChu_vi_co_chan_cm() != null) {
                statement.setDouble(30, Double.valueOf(pig.getChu_vi_co_chan_cm()));
            } else {
                statement.setString(30, null);
            }
            if (pig.getDo_rong_dai_trang_vat_vai_cm() != null) {
                statement.setDouble(31, Double.valueOf(pig.getDo_rong_dai_trang_vat_vai_cm()));
            } else {
                statement.setString(31, null);
            }
            if (pig.getSo_bau_vu() != null) {
                statement.setInt(32, Integer.valueOf(pig.getSo_bau_vu()));
            } else {
                statement.setString(32, null);
            }
            if (pig.getLoai_con_giong() != null) {
                statement.setString(33, pig.getLoai_con_giong());
            } else {
                statement.setString(33, null);
            }
            if (pig.getSo_con_trong_dan() != null) {
                statement.setDouble(34, Double.valueOf(pig.getSo_con_trong_dan()));
            } else {
                statement.setString(34, null);
            }
            if (pig.getSo_con_duc_trong_dan() != null) {
                statement.setDouble(35, Double.valueOf(pig.getSo_con_duc_trong_dan()));
            } else {
                statement.setString(35, null);
            }
            if (pig.getSo_con_cai_trong_dan() != null) {
                statement.setDouble(36, Double.valueOf(pig.getSo_con_cai_trong_dan()));
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
            if (pig.getNguoi_cap_nhat() != null) {
                statement.setString(43, pig.getNguoi_cap_nhat());
            } else {
                statement.setString(43, null);
            }
            statement.setTimestamp(44, new Timestamp(dateCurrent.getTime()));
            statement.setInt(45, pig.getId());
            statement.executeUpdate();
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
            log.info("UPDATE PIG: " + pig.getMa_so_ca_the());
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
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
            log.info("UPDATE PIG STATUS: " + pig.getMa_so_ca_the() + " - status: " + pig.getTrang_thai());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - updatePigStatus!");
        }
    }

    public void updatePigSpecialNull(Integer pigId) {
        try {
            String sqlQuery = "UPDATE tblpig " +
                    "SET " +
                    "ca_the_tieu_bieu = 0, " +
                    "vi_tri_tieu_bieu = null " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, pigId);
            log.info("SQL: " + sqlQuery);
            statement.executeUpdate();
            connection.commit();
            statement.close();
            log.info("UPDATE PIG SPECIAL TO NULL - PIG ID: " + pigId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - updatePigSpecialNull!");
        }
    }

    public void updatePigSpecial(PigSpecial pigSpecial) {
        try {
            String sqlQuery = "UPDATE tblpig " +
                    "SET " +
                    "ca_the_tieu_bieu = 1, " +
                    "vi_tri_tieu_bieu = ? " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, pigSpecial.getVi_tri_cap_nhat());
            statement.setInt(2, pigSpecial.getId_new());
            log.info("SQL: " + sqlQuery);
            statement.executeUpdate();
            connection.commit();
            statement.close();
            log.info("UPDATE PIG SPECIAL - PIG ID: " + pigSpecial.getId_new() + " - special position updated: " + pigSpecial.getVi_tri_cap_nhat());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - updatePigSpecialNull!");
        }
    }

    public void savePig(Pig pig) {
        try {
            Date dateCurrent = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String dateFixed = null;
            try {
                dateFixed = sdf2.format(sdf.parse(pig.getNgay_danh_gia()));
            } catch (ParseException e) {
                e.printStackTrace();
            };
            String sqlQuery = "INSERT INTO tblpig " +
                    "(ma_so_ca_the, tuoi, nguoi_danh_gia, dia_diem_danh_gia, ngay_danh_gia, " +
                    "gioi_tinh, nhan_trung_trang, tai_meo, mom, vanh_trang_quanh_mom, mat, da_co_khoang_den, " +
                    "khoang_den_phu, kieu_lung_bung, chan, da_mat, kieu_duoi, kieu_tai, huong_tai, kieu_long, " +
                    "loai_mau_long, hinh_dang_dau, trong_luong_co_the_kg, chieu_dai_dau_cm, chieu_dai_duoi_cm, " +
                    "chieu_rong_nguc_cm, chieu_cao_than_tai_chan_truoc_cm, chieu_dai_co_the_cm, chieu_dai_tai_cm, " +
                    "chieu_rong_than_tai_xuong_chau_cm, chu_vi_co_chan_cm, do_rong_dai_trang_vat_vai_cm, " +
                    "so_bau_vu, loai_con_giong, so_con_trong_dan, so_con_duc_trong_dan, so_con_cai_trong_dan, " +
                    "hinh_anh_1, hinh_anh_2, hinh_dang_long, do_dai_long, do_day_long, pha_he, ngay_nhap, nguoi_nhap, nguoi_cap_nhat, ngay_cap_nhat) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, pig.getMa_so_ca_the());
            if (pig.getTuoi() != null) {
                statement.setString(2, pig.getTuoi());
            } else {
                statement.setString(2, null);
            }
            if (pig.getNguoi_danh_gia() != null) {
                statement.setString(3, pig.getNguoi_danh_gia());
            } else {
                statement.setString(3, null);
            }
            if (pig.getDia_diem_danh_gia() != null) {
                statement.setString(4, pig.getDia_diem_danh_gia());
            } else {
                statement.setString(4, null);
            }
            if (dateFixed != null) {
                statement.setString(5, dateFixed);
            } else {
                statement.setString(5, null);
            }
            if (pig.getGioi_tinh() != null) {
                statement.setString(6, pig.getGioi_tinh());
            } else {
                statement.setString(6, null);
            }
            if (pig.getNhan_trung_trang() != null) {
                statement.setString(7, pig.getNhan_trung_trang());
            } else {
                statement.setString(7, null);
            }
            if (pig.getTai_meo() != null) {
                statement.setString(8, pig.getTai_meo());
            } else {
                statement.setString(8, null);
            }
            if (pig.getMom() != null) {
                statement.setString(9, pig.getMom());
            } else {
                statement.setString(9, null);
            }
            if (pig.getVanh_trang_quanh_mom() != null) {
                statement.setString(10, pig.getVanh_trang_quanh_mom());
            } else {
                statement.setString(10, null);
            }
            if (pig.getMat() != null) {
                statement.setString(11, pig.getMat());
            } else {
                statement.setString(11, null);
            }
            if (pig.getDa_co_khoang_den() != null) {
                statement.setString(12, pig.getDa_co_khoang_den());
            } else {
                statement.setString(12, null);
            }
            if (pig.getKhoang_den_phu() != null) {
                statement.setString(13, pig.getKhoang_den_phu());
            } else {
                statement.setString(13, null);
            }
            if (pig.getKieu_lung_bung() != null) {
                statement.setString(14, pig.getKieu_lung_bung());
            } else {
                statement.setString(14, null);
            }
            if (pig.getChan() != null) {
                statement.setString(15, pig.getChan());
            } else {
                statement.setString(15, null);
            }
            if (pig.getDa_mat() != null) {
                statement.setString(16, pig.getDa_mat());
            } else {
                statement.setString(16, null);
            }
            if (pig.getKieu_duoi() != null) {
                statement.setString(17, pig.getKieu_duoi());
            } else {
                statement.setString(17, null);
            }
            if (pig.getKieu_tai() != null) {
                statement.setString(18, pig.getKieu_tai());
            } else {
                statement.setString(18, null);
            }
            if (pig.getHuong_tai() != null) {
                statement.setString(19, pig.getHuong_tai());
            } else {
                statement.setString(19, null);
            }
            if (pig.getKieu_long() != null) {
                statement.setString(20, pig.getKieu_long());
            } else {
                statement.setString(20, null);
            }
            if (pig.getLoai_mau_long() != null) {
                statement.setString(21, pig.getLoai_mau_long());
            } else {
                statement.setString(21, null);
            }
            if (pig.getHinh_dang_dau() != null) {
                statement.setString(22, pig.getHinh_dang_dau());
            } else {
                statement.setString(22, null);
            }
            if (pig.getTrong_luong_co_the_kg() != null) {
                statement.setDouble(23, Double.valueOf(pig.getTrong_luong_co_the_kg()));
            } else {
                statement.setString(23, null);
            }
            if (pig.getChieu_dai_dau_cm() != null) {
                statement.setDouble(24, Double.valueOf(pig.getChieu_dai_dau_cm()));
            } else {
                statement.setString(24, null);
            }
            if (pig.getChieu_dai_duoi_cm() != null) {
                statement.setDouble(25, Double.valueOf(pig.getChieu_dai_duoi_cm()));
            } else {
                statement.setString(25, null);
            }
            if (pig.getChieu_rong_nguc_cm() != null) {
                statement.setDouble(26, Double.valueOf(pig.getChieu_rong_nguc_cm()));
            } else {
                statement.setString(26, null);
            }
            if (pig.getChieu_cao_than_tai_chan_truoc_cm() != null) {
                statement.setDouble(27, Double.valueOf(pig.getChieu_cao_than_tai_chan_truoc_cm()));
            } else {
                statement.setString(27, null);
            }
            if (pig.getChieu_dai_co_the_cm() != null) {
                statement.setDouble(28, Double.valueOf(pig.getChieu_dai_co_the_cm()));
            } else {
                statement.setString(28, null);
            }
            if (pig.getChieu_dai_tai_cm() != null) {
                statement.setDouble(29, Double.valueOf(pig.getChieu_dai_tai_cm()));
            } else {
                statement.setString(29, null);
            }
            if (pig.getChieu_rong_than_tai_xuong_chau_cm() != null) {
                statement.setDouble(30, Double.valueOf(pig.getChieu_rong_than_tai_xuong_chau_cm()));
            } else {
                statement.setString(30, null);
            }
            if (pig.getChu_vi_co_chan_cm() != null) {
                statement.setDouble(31, Double.valueOf(pig.getChu_vi_co_chan_cm()));
            } else {
                statement.setString(31, null);
            }
            if (pig.getDo_rong_dai_trang_vat_vai_cm() != null) {
                statement.setDouble(32, Double.valueOf(pig.getDo_rong_dai_trang_vat_vai_cm()));
            } else {
                statement.setString(32, null);
            }
            if (pig.getSo_bau_vu() != null) {
                statement.setInt(33, Integer.valueOf(pig.getSo_bau_vu()));
            } else {
                statement.setString(33, null);
            }
            if (pig.getLoai_con_giong() != null) {
                statement.setString(34, pig.getLoai_con_giong());
            } else {
                statement.setString(34, null);
            }
            if (pig.getSo_con_trong_dan() != null) {
                statement.setDouble(35, Integer.valueOf(pig.getSo_con_trong_dan()));
            } else {
                statement.setString(35, null);
            }
            if (pig.getSo_con_duc_trong_dan() != null) {
                statement.setDouble(36, Integer.valueOf(pig.getSo_con_duc_trong_dan()));
            } else {
                statement.setString(36, null);
            }
            if (pig.getSo_con_cai_trong_dan() != null) {
                statement.setDouble(37, Integer.valueOf(pig.getSo_con_cai_trong_dan()));
            } else {
                statement.setString(37, null);
            }
            if (pig.getHinh_anh_1() != null) {
                statement.setString(38, pig.getHinh_anh_1());
            } else {
                statement.setString(38, null);
            }
            if (pig.getHinh_anh_2() != null) {
                statement.setString(39, pig.getHinh_anh_2());
            } else {
                statement.setString(39, null);
            }
            if (pig.getHinh_dang_long() != null) {
                statement.setString(40, pig.getHinh_dang_long());
            } else {
                statement.setString(40, null);
            }
            if (pig.getDo_dai_long() != null) {
                statement.setString(41, pig.getDo_dai_long());
            } else {
                statement.setString(41, null);
            }
            if (pig.getDo_day_long() != null) {
                statement.setString(42, pig.getDo_day_long());
            } else {
                statement.setString(42, null);
            }
            if (pig.getPha_he() != null) {
                statement.setString(43, pig.getPha_he());
            } else {
                statement.setString(43, null);
            }
            statement.setTimestamp(44, new Timestamp(dateCurrent.getTime()));
            statement.setString(45, pig.getNguoi_nhap());
            statement.setString(46, pig.getNguoi_cap_nhat());
            statement.setTimestamp(47, new Timestamp(dateCurrent.getTime()));
            statement.execute();
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
            log.info("SAVE PIG: " + pig.getMa_so_ca_the());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - savePig!");
        }
    }

    public void deletePig(Pig pig) {
        try {
            String sqlQuery = "DELETE FROM tblpig WHERE " +
                    "id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, pig.getId());
            statement.executeUpdate();
            log.info("SQL: " + sqlQuery);
            connection.commit();
            statement.close();
            log.info("DELETE PIG: " + pig.getMa_so_ca_the());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error - deletePig!");
        }
    }
}
