package com.dolethanhtuan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dolethanhtuan.dto.UserDTO;
@Service
public interface IUserService {
	int countUser();
	List<UserDTO> findAllUser();
	List<UserDTO> findAllUserLikeUsername(String username);
	UserDTO findOneById(int id);
	UserDTO findOneByUsername(String username);
	UserDTO findOneByUsernameAndPasswordAndStatus(String username,String password,int status);
	UserDTO insertUser(UserDTO userD);
	UserDTO updateUser(UserDTO userD);
	void deleteUser(int idUser);
	void addFavouriteNews(int idUser,int idNews);
	void deleteFavouriteNews(int idUser,int idNews);
	boolean existUsername(String username);
	void activeUser(int idUser,int status);
	void addCateCare(UserDTO userD);
}
