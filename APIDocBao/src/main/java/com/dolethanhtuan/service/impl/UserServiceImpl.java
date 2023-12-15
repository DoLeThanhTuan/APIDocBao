package com.dolethanhtuan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dolethanhtuan.dto.CategoryDTO;
import com.dolethanhtuan.dto.UserDTO;
import com.dolethanhtuan.entity.CategoryEntity;
import com.dolethanhtuan.entity.NewsEntity;
import com.dolethanhtuan.entity.UserEntity;
import com.dolethanhtuan.repository.NewsRepository;
import com.dolethanhtuan.repository.UserRepository;
import com.dolethanhtuan.service.IUserService;
import com.dolethanhtuan.utils.converter.UserConverter;

@Component
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private NewsRepository newsRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public int countUser() {
		return userRepository.findAll().size();
	}
	@Override
	public List<UserDTO> findAllUser() {
		List<UserDTO> listUserD = new ArrayList<>();
		for (UserEntity userE : userRepository.findAll()) {
			UserDTO userD = new UserDTO();
			userD = userConverter.toDTO(userE);
			listUserD.add(userD);
		}
		return listUserD;
	}
	@Override
	public List<UserDTO> findAllUserLikeUsername(String username) {
		List<UserEntity> listE = userRepository.findAllByUsernameContaining(username);
		List<UserDTO> listD = new ArrayList<>();
		for (UserEntity userEntity : listE) {
			listD.add(userConverter.toDTO(userEntity));
		}
		return listD;
	}
	@Override
	public UserDTO findOneByUsername(String username) {
		UserEntity userE = userRepository.findOneByUsername(username);
		if(userE == null) return null;
		return userConverter.toDTO(userE);
	}
	@Override
	public UserDTO findOneById(int id) {
		UserEntity userE = userRepository.findOneById(id);
		if(userE == null)
			return null;
		return userConverter.toDTO(userE);
	}
	@Override
	public UserDTO findOneByUsernameAndPasswordAndStatus(String username, String password, int status) {
		UserEntity userE = userRepository.findOneByUsernameAndPasswordAndStatus(username, password, status);
		if(userE == null) return null;
		return userConverter.toDTO(userE);
	}

	@Override
	public UserDTO insertUser(UserDTO userD) {
		UserEntity userE = userConverter.toEntity(userD);
		userE.setStatus(1);
		userE = userRepository.save(userE);
		return userConverter.toDTO(userE);
	}

	@Override
	public UserDTO updateUser(UserDTO userD) {
		UserEntity userEOld = userRepository.findOneById(userD.getId());
		userEOld = userConverter.toEntity(userEOld, userD);
		userEOld = userRepository.save(userEOld);
		return userConverter.toDTO(userEOld);
	}

	@Override
	public void deleteUser(int idUser) {
		userRepository.deleteById(idUser);
	}

	@Override
	public void addFavouriteNews(int idUser,int idNews) {
		NewsEntity newsE = newsRepository.findOneById(idNews);
		UserEntity userE = userRepository.findOneById(idUser);
		List<NewsEntity> listNewsE = userE.getListNewsfavourite();
		listNewsE.add(newsE);
		userE.setListNewsfavourite(listNewsE);
		userRepository.save(userE);
	}

	@Override
	public void deleteFavouriteNews(int idUser,int idNews) {
		UserEntity userE = userRepository.findOneById(idUser);
		List<NewsEntity> listNewsE =userE.getListNewsfavourite();
		for (NewsEntity newsEntity : listNewsE) {
			if(newsEntity.getId() == idNews)
				listNewsE.remove(newsEntity);
		}
		userE.setListNewsfavourite(listNewsE);
		userRepository.save(userE);
	}
	@Override
	public boolean existUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	@Override
	public void activeUser(int idUser, int status) {
		UserEntity userE = userRepository.findOneById(idUser);
		userE.setStatus(status);
		userRepository.save(userE);
	}
	@Override
	public void addCateCare(UserDTO userD) {
		UserEntity userE = userRepository.findOneById(userD.getId());
		if(userE == null) return;
		List<CategoryEntity> listCateCare = new ArrayList<>();
		for (CategoryDTO cateD : userD.getListCateCare()) {
			CategoryEntity cateE = modelMapper.map(cateD, CategoryEntity.class);
			listCateCare.add(cateE);
		}
		userE.setListCateCare(listCateCare);
		userRepository.save(userE);
	}
	
}
