package com.school.core.service.contact;

import com.school.core.entity.Blog;
import com.school.core.entity.Contact;
import com.school.core.entity.ResponseData;
import com.school.core.repository.BlogRepository;
import com.school.core.repository.ContactRepository;
import com.school.core.response.AppResponse;
import com.school.core.response.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public ResponseEntity<?> getAllContact() {
        try {
            List<Contact> listContact = contactRepository.getAllContact();
            if (listContact.size() > 0) {
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get All Contact");
                responseData.setData(listContact);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get All Contact");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }


    public ResponseEntity<?> saveContact(Contact contact) {
        try {
            contactRepository.saveContact(contact);
            return new ResponseEntity<>(new AppResponse().success("Lưu thông tin liên hệ thành công!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error update contact!"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateContact(Contact contact) {
        try {
            contactRepository.updateContact(contact);
            return new ResponseEntity<>(new AppResponse().success("Cập nhật contact thành công!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error delete contact!"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteContact(Contact contact) {
        try {
            contactRepository.deleteContact(contact);
            return new ResponseEntity<>(new AppResponse().success("Xóa contact thành công!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error delete contact!"), HttpStatus.BAD_REQUEST);
        }
    }



}
