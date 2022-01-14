package com.school.core.service.pig;

import com.school.core.entity.*;
import com.school.core.repository.PigRepository;
import com.school.core.response.AppResponse;
import com.school.core.response.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PigService {
    @Autowired
    private PigRepository pigRepository;

    public ResponseEntity<?> getPigById(Integer id) {
        try {
            Pig pig = pigRepository.getPigById(id);

            if (pig != null) {
                List<Pig> listPig = new ArrayList<>();
                listPig.add(pig);
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get Pig By Id");
                responseData.setData(listPig);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get Pig By Id");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> getPigByFarmName(String farmName) {
        try {
            List<Pig> listPigByFarmName = pigRepository.getPigByFarmName(farmName);

            if (listPigByFarmName.size() > 0) {
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get Pig By Farm Name");
                responseData.setData(listPigByFarmName);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get Pig By Farm Name");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> getPigLimit12() {
        try {
            List<PigLandingPage> listPigLandingPage = pigRepository.getPigLimit12();
            if (listPigLandingPage.size() > 0) {
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get Pig Limit 12");
                responseData.setData(listPigLandingPage);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get Pig Limit 12");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }


    public ResponseEntity<?> getAllPig() {
        try {
            List<Pig> listPig = pigRepository.getAllPig();
            if (listPig.size() > 0) {
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get All Pig");
                responseData.setData(listPig);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get All Pig");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> countPig() {
        try {
            Integer totalPig = pigRepository.countPig();
            List<Integer> listData = new ArrayList<>();

            if (totalPig != null) {
                listData.add(totalPig);
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Count Pig Successfully!");
                responseData.setData(listData);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Count Pig");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> savePig(Pig pig) {
        try {
            String pigCodeCheck = pigRepository.getPigCode(pig.getMa_so_ca_the());
            if (pigCodeCheck != null) {
                return new ResponseEntity<>(new AppResponse().badRequest("Mã số cá thể đã tồn tại!"), HttpStatus.BAD_REQUEST);
            } else {
                pigRepository.savePig(pig);
                return new ResponseEntity<>(new AppResponse().success("Lưu cá thể thành công!"), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error save pig!"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> savePigImport(DataRequest dataRequest) {
        try {
            List<String> listSuccess = new ArrayList<>();
            List<String> listFailed = new ArrayList<>();
            List<Pig> listData = dataRequest.getData();
            for (int i = 0; i < listData.size(); i++) {
                Pig pig = listData.get(i);
                String pigCodeCheck = pigRepository.getPigCode(pig.getMa_so_ca_the());
                if (pigCodeCheck != null) {
                   listFailed.add(pigCodeCheck);
                } else {
                    pigRepository.savePig(pig);
                    listSuccess.add(pig.getMa_so_ca_the());
                }
            }
            ResponseDataImport responseDataImport = new ResponseDataImport();
            responseDataImport.setDataSuccess(listSuccess);
            responseDataImport.setDataFailed(listFailed);
            return new ResponseEntity<>(new AppResponse().successWithObject("Hoàn thành lưu dư liệu!", responseDataImport), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error save pig import!"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updatePig(Pig pig) {
        try {
            pigRepository.updatePig(pig);
            return new ResponseEntity<>(new AppResponse().success("Cập nhật cá thể thành công!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error update pig!"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updatePigStatus(Pig pig) {
        try {
            pigRepository.updatePigStatus(pig);
            return new ResponseEntity<>(new AppResponse().success("Cập nhật trạng thái cá thể thành công!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error update pig status!"), HttpStatus.BAD_REQUEST);
        }
    }
}
