package com.example.buyshoes.service;


import com.example.buyshoes.dto.UserDto;
import com.example.buyshoes.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public interface UserService {


    User getUser( Long id);
    void deleteUser(Long id);
    void showPhoto(HttpServletResponse response, Long id) throws IOException;
//    public User getUserByUsernameAndPassword(String username, String password);
    User getUserByUsername(String username);

    User findByUsername(String userName);

    User saveUser(UserDto userDto);


    public Page<User> listAll(int pageNum, String sortField, String sortDir) ;
}
