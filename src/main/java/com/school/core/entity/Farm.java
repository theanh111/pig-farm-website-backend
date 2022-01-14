package com.school.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Farm {
    private Integer id;
    private String ma_trang_trai;
    private String ten_trang_trai;
    private String hinh_anh;
    private String ngay_nhap;
}
