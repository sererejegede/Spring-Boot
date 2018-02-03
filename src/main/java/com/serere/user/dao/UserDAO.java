package com.serere.user.dao;

import com.serere.user.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends CrudRepository<User, Integer> {

    //Database Query By Method Name
    List<User> findByUsername(String username);

    //Return SortedStuff
    @Query(value = "SELECT u FROM User u WHERE u.username = ?1")
    List<User> getSortedUsername(String username, Pageable pageable);

    @Query(value = "SELECT u FROM User u")
    List<User> getPagedList(Pageable pageable);
}
