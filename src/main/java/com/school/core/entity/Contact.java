package com.school.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private Integer id;
    private String ten_lien_he;
    private String dia_chi;
    private String sdt;
    private String email;
    private String nguoi_nhap;
    private String ngay_nhap;
    private Integer trang_thai;
    private String nguoi_cap_nhat;
    private String ngay_cap_nhat;
    private String tu_khoa;
}
