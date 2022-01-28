//package com.school.core.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Pig {
//    private Integer id;
//    private String ma_so_ca_the;
//    private String tuoi;
//    private String nguoi_danh_gia;
//    private String dia_diem_danh_gia;
//    private String ngay_danh_gia;
//    private String gioi_tinh;
//    private String nhan_trung_trang;
//    private String tai_meo;
//    private String mom;
//    private String vanh_trang_quanh_mom;
//    private String mat;
//    private String da_co_khoang_den;
//    private String khoang_den_phu;
//    private String kieu_lung_bung;
//    private String chan;
//    private String da_mat;
//    private String kieu_duoi;
//    private String kieu_tai;
//    private String huong_tai;
//    private String kieu_long;
//    private String loai_mau_long;
//    private String hinh_dang_dau;
//    private Double trong_luong_co_the_kg;
//    private Double chieu_dai_dau_cm;
//    private Double chieu_dai_duoi_cm;
//    private Double chieu_rong_nguc_cm;
//    private Double chieu_cao_than_tai_chan_truoc_cm;
//    private Double chieu_dai_co_the_cm;
//    private Double chieu_dai_tai_cm;
//    private Double chieu_rong_than_tai_xuong_chau_cm;
//    private Double chu_vi_co_chan_cm;
//    private Double do_rong_dai_trang_vat_vai_cm;
//    private Integer so_bau_vu;
//    private String loai_con_giong;
//    private Integer so_con_trong_dan;
//    private Integer so_con_duc_trong_dan;
//    private Integer so_con_cai_trong_dan;
//    private String hinh_anh_1;
//    private String hinh_anh_2;
//    private String hinh_dang_long;
//    private String do_dai_long;
//    private String do_day_long;
//    private String pha_he;
//    private String ngay_nhap;
//    private Integer trang_thai;
//    private String nguoi_nhap;
//    private String tu_khoa;
//    private String nguoi_cap_nhat;
//    private String ngay_cap_nhat;
//}

package com.school.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pig {
    private Integer id;
    private String ma_so_ca_the;
    private String tuoi;
    private String nguoi_danh_gia;
    private String dia_diem_danh_gia;
    private String ngay_danh_gia;
    private String gioi_tinh;
    private String nhan_trung_trang;
    private String tai_meo;
    private String mom;
    private String vanh_trang_quanh_mom;
    private String mat;
    private String da_co_khoang_den;
    private String khoang_den_phu;
    private String kieu_lung_bung;
    private String chan;
    private String da_mat;
    private String kieu_duoi;
    private String kieu_tai;
    private String huong_tai;
    private String kieu_long;
    private String loai_mau_long;
    private String hinh_dang_dau;
    private String trong_luong_co_the_kg;
    private String chieu_dai_dau_cm;
    private String chieu_dai_duoi_cm;
    private String chieu_rong_nguc_cm;
    private String chieu_cao_than_tai_chan_truoc_cm;
    private String chieu_dai_co_the_cm;
    private String chieu_dai_tai_cm;
    private String chieu_rong_than_tai_xuong_chau_cm;
    private String chu_vi_co_chan_cm;
    private String do_rong_dai_trang_vat_vai_cm;
    private String so_bau_vu;
    private String loai_con_giong;
    private String so_con_trong_dan;
    private String so_con_duc_trong_dan;
    private String so_con_cai_trong_dan;
    private String hinh_anh_1;
    private String hinh_anh_2;
    private String hinh_dang_long;
    private String do_dai_long;
    private String do_day_long;
    private String pha_he;
    private String ngay_nhap;
    private Integer trang_thai;
    private String nguoi_nhap;
    private String tu_khoa;
    private String nguoi_cap_nhat;
    private String ngay_cap_nhat;
    private Integer ca_the_tieu_bieu;
    private Integer vi_tri_tieu_bieu;
}

