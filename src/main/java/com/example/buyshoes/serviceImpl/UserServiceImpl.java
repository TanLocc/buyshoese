package com.example.buyshoes.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;


import com.example.buyshoes.dto.UserDto;
import com.example.buyshoes.entities.User;
import com.example.buyshoes.repository.UserRepository;
import com.example.buyshoes.service.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public  class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;


	@Autowired
	private ModelMapper modelMapper;

	@Override
	public User saveUser(UserDto userDto) {
		// TODO Auto-generated method stub

		User user = new User();
		byte[] image;
		if(userDto.getId()!=null){
			user = userRepository.findById(userDto.getId()).orElse(new User());
			user.setUpdatedAt(new Date());
			if(userDto.getAvatar()==null||userDto.getAvatar().getSize()==0) {
				image = (userRepository.findById(user.getId())).get().getAvatar();
				user.setAvatar(image);
			}
			else {
				image = userDto.getAvatar().getBytes();
				user.setAvatar(image);
			}
		} else {
			if(userDto.getAvatar()!=null&&userDto.getAvatar().getSize()!=0) {
				image = userDto.getAvatar().getBytes();
				user.setAvatar(image);
			}
			user.setCreatedAt(new Date());
			user.setUpdatedAt(new Date());
		}
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
		user.setAddress(userDto.getAddress());
		user.setRole(userDto.getRole());
		return userRepository.save(user);
	}


	@Override
	public User getUser(Long id) {
		User user = userRepository.getById(id);
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		User user = getUser(id);
		userRepository.delete(user);
	}

	@Override
	public void showPhoto(HttpServletResponse response,Long id) throws IOException {
		response.setContentType("image/jpeg");
		System.out.println("getuserphotobyid: ok ok  vs id="+id);
		User user = getUser(id);
		response.setHeader("Content-Disposition", "inline;filename=\""+ user.getUsername() + "\"");
		response.setContentType("image/jpeg");
		byte[] bytes = user.getAvatar();
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}

//	@Override
//	public User getUserByUsernameAndPassword(String username, String password) {
//		Criteria cr = getCurrentSession().createCriteria(User.class);
//        cr.add(Restrictions.eq("username",username));
//        cr.add(Restrictions.eq("password",password));
//        User user = (User) cr.list().get(0);
//		return user;
//	}

	@Override
	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}

	@Override
	public User findByUsername(String userName){
		return userRepository.findByUsername(userName);
	}



	@Override
	public Page<User> listAll(int pageNum, String sortField, String sortDir) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
						: Sort.by(sortField).descending()
		);

		return userRepository.findAll(pageable);
	}



}
