package com.example.myblog.service;

import com.example.myblog.dao.PostDao;
import com.example.myblog.dto.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostDao postDao;

    @Override
    public List<Post> getPostList(Map map) throws Exception {
        return postDao.getPostList(map);
    }

    @Override
    public int getDateCount(Map map) throws Exception {
        return postDao.getDateCount(map);
    }
}