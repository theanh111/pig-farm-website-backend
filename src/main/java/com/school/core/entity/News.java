package com.school.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private Integer id;
    private String ma_tin_tuc;
    private String ten_tin_tuc;
    private String noi_dung;
    private String nguoi_nhap;
    private String hinh_anh;
    private String ngay_nhap;
}
