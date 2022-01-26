package com.school.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gallery {
    private Integer id;
    private String ten_anh;
    private String the_loai;
    private String hinh_anh;
    private String nguoi_nhap;
    private String ngay_nhap;
    private Integer trang_thai;
    private String nguoi_cap_nhat;
    private String ngay_cap_nhat;
}
