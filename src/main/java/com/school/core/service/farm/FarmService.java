package com.school.core.service.farm;

import com.school.core.entity.*;
import com.school.core.repository.FarmRepository;
import com.school.core.response.AppResponse;
import com.school.core.response.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FarmService {
    @Autowired
    private FarmRepository farmRepository;

    public ResponseEntity<?> getFarmByFarmCode(String farmCode) {
        try {
            Farm farm = farmRepository.getFarmByFarmCode(farmCode);

            if (farm != null) {
                List<Farm> listFarm = new ArrayList<>();
                listFarm.add(farm);
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get Farm By Farm Code");
                responseData.setData(listFarm);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get Farm By Farm Code");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> getFarmById(Integer id) {
        try {
            Farm farm = farmRepository.getFarmById(id);

            if (farm != null) {
                List<Farm> listFarm = new ArrayList<>();
                listFarm.add(farm);
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get Farm By Id");
                responseData.setData(listFarm);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get Farm By Id");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> getAllFarm() {
        try {
            List<Farm> listFarm = farmRepository.getAllFarm();
            if (listFarm.size() > 0) {
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get All Farm");
                responseData.setData(listFarm);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get All Farm");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> getAllFarmByFilter(Farm farmFilter) {
        try {
            List<Farm> listFarmByFilter = farmRepository.getAllFarmByFilter(farmFilter);
            if (listFarmByFilter.size() > 0) {
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get Farm By Filter");
                responseData.setData(listFarmByFilter);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get Farm By Filter");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> countFarm() {
        try {
            Integer totalFarm = farmRepository.countFarm();
            List<Integer> listData = new ArrayList<>();

            if (totalFarm != null) {
                listData.add(totalFarm);
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Count Farm Successfully!");
                responseData.setData(listData);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Count Farm");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> saveFarm(Farm farm) {
        try {
            String farmNameCheck = farmRepository.getFarmName(farm.getTen_trang_trai());
            if (farmNameCheck != null) {
                return new ResponseEntity<>(new AppResponse().badRequest("Tên cơ sở chăn nuôi đã tồn tại!"), HttpStatus.BAD_REQUEST);
            } else {
                farmRepository.saveFarm(farm);
                return new ResponseEntity<>(new AppResponse().success("Lưu cơ sở chăn nuôi thành công!"), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error save farm!"), HttpStatus.BAD_REQUEST);
        }
    }



    public ResponseEntity<?> updateFarm(Farm farm) {
        try {
            farmRepository.updateFarm(farm);
            return new ResponseEntity<>(new AppResponse().success("Cập nhật cơ sở chăn nuôi thành công!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error update farm!"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteFarm(Farm farm) {
        try {
            farmRepository.deleteFarm(farm);
            return new ResponseEntity<>(new AppResponse().success("Xóa farm thành công!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error delete farm!"), HttpStatus.BAD_REQUEST);
        }
    }
}
