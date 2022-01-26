package com.school.core.service.news;

import com.school.core.entity.*;
import com.school.core.repository.NewsRepository;
import com.school.core.response.AppResponse;
import com.school.core.response.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public ResponseEntity<?> getAllNews() {
        try {
            List<News> listNews = newsRepository.getAllNews();
            if (listNews.size() > 0) {
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get All News");
                responseData.setData(listNews);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get All News");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> getNewsByNewsCode(String newsCode) {
        try {
            News news = newsRepository.getNewsByNewsCode(newsCode);

            if (news != null) {
                List<News> listNews = new ArrayList<>();
                listNews.add(news);
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get News By News Code");
                responseData.setData(listNews);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get News By News Code");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> getAllNewsByFilter(News newsFilter) {
        try {
            List<News> listNewsByFilter = newsRepository.getAllNewsByFilter(newsFilter);
            if (listNewsByFilter.size() > 0) {
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Get News By Filter");
                responseData.setData(listNewsByFilter);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Get News By Filter");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> countNews() {
        try {
            Integer totalNews = newsRepository.countNews();
            List<Integer> listData = new ArrayList<>();

            if (totalNews != null) {
                listData.add(totalNews);
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.setMessage("Count News Successfully!");
                responseData.setData(listData);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                ResponseData responseData = new ResponseData();
                responseData.setCode(400);
                responseData.setMessage("Can't Count News");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> saveNews(News news) {
        try {
            newsRepository.saveNews(news);
            return new ResponseEntity<>(new AppResponse().success("Lưu tin tức thành công!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error save blog!"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateNews(News news) {
        try {
            newsRepository.updateNews(news);
            return new ResponseEntity<>(new AppResponse().success("Cập nhật news thành công!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error update news!"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteNews(News news) {
        try {
            newsRepository.deleteNews(news);
            return new ResponseEntity<>(new AppResponse().success("Xóa news thành công!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Error delete news!"), HttpStatus.BAD_REQUEST);
        }
    }



}
