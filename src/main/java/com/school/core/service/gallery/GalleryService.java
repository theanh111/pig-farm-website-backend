package com.school.core.service.gallery;

import com.school.core.entity.Farm;
import com.school.core.entity.Gallery;
import com.school.core.entity.ResponseData;
import com.school.core.repository.FarmRepository;
import com.school.core.repository.GalleryRepository;
import com.school.core.response.AppResponse;
import com.school.core.response.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GalleryService {
    @Autowired
    private GalleryRepository galleryRepository;

    public ResponseEntity<?> getAllGallery() {
        try {
            List<Gallery> listGallery = galleryRepository.getAllGallery();
            if (listGallery.size() > 0) {
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get All Gallery");
                responseData.setData(listGallery);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get All Gallery");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> saveGallery(Gallery gallery) {
        try {
            galleryRepository.saveGallery(gallery);
            return new ResponseEntity<>(new AppResponse().success("Lưu ảnh trang chủ thành công!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error save gallery!"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateGallery(Gallery gallery) {
        try {
            galleryRepository.updateGallery(gallery);
            return new ResponseEntity<>(new AppResponse().success("Cập nhật ảnh trang chủ thành công!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error update gallery!"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteGallery(Gallery gallery) {
        try {
            galleryRepository.deleteGallery(gallery);
            return new ResponseEntity<>(new AppResponse().success("Xóa gallery thành công!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error delete gallery!"), HttpStatus.BAD_REQUEST);
        }
    }
}
