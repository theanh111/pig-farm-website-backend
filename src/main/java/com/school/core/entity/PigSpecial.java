
package com.school.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PigSpecial {
    private Integer id;
    private Integer id_new;
    private Integer id_old;
    private String ma_so_ca_the;
    private String tuoi;
    private String hinh_anh_1;
    private Integer ca_the_tieu_bieu;
    private Integer vi_tri_tieu_bieu;
    private Integer vi_tri_cap_nhat;
}

