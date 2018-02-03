package com.serere.user.service;

import com.serere.user.User;
import com.serere.user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserDAO userDAO;


    public User createUser(User user) {
        return userDAO.save(user);
    }

    public User getUser(Integer userId) {
        return userDAO.findOne(userId);
    }

    public Iterable<User> getAlluser() {
        return userDAO.findAll();
    }

    public void deleteUser(Integer userId) {
        userDAO.delete(userId);
    }

    public User updateUser(Integer userId, String newPassword) {
        User userToBeUpdated = userDAO.findOne(userId);
        userToBeUpdated.setPassword(newPassword);
        return userDAO.save(userToBeUpdated);
    }

    public List<User> sortedUser(String username){
        return userDAO.findByUsername(username);
    }


    public List<User> sortedPagedList(String username, PageRequest pageRequest) {
        return userDAO.getSortedUsername(username, pageRequest);
    }

    public List<User> pagedList(PageRequest pageRequest) {
        return userDAO.getPagedList(pageRequest);
    }
}
