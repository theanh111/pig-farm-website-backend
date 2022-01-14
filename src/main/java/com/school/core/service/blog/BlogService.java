package com.school.core.service.blog;

import com.school.core.entity.Blog;
import com.school.core.entity.Farm;
import com.school.core.entity.ResponseData;
import com.school.core.repository.BlogRepository;
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
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public ResponseEntity<?> getAllBlog() {
        try {
            List<Blog> listBlog = blogRepository.getAllBlog();
            if (listBlog.size() > 0) {
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get All Blog");
                responseData.setData(listBlog);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get All Blog");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> getBlogByBlogCode(String blogCode) {
        try {
            Blog blog = blogRepository.getBlogByBlogCode(blogCode);

            if (blog != null) {
                List<Blog> listBlog = new ArrayList<>();
                listBlog.add(blog);
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get Blog By Blog Code");
                responseData.setData(listBlog);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get Blog By Blog Code");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }


    public ResponseEntity<?> saveBlog(Blog blog) {
        try {
            blogRepository.saveBlog(blog);
            return new ResponseEntity<>(new AppResponse().success("Lưu blog thành công!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error save blog!"), HttpStatus.BAD_REQUEST);
        }
    }



}
