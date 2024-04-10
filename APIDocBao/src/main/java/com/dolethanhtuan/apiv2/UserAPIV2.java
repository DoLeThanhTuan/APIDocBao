package com.dolethanhtuan.apiv2;

import com.dolethanhtuan.dto.UserDTO;
import com.dolethanhtuan.response.ResponseError;
import com.dolethanhtuan.response.ResponseSuccess;
import com.dolethanhtuan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAPIV2 {
	@Autowired
	private IUserService userService;
	
	
	@GetMapping(path = "api/user/all/v2")
	public ResponseSuccess<List<UserDTO>> getAllUser(){
		List<UserDTO> listUser = userService.findAllUser();
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Success",listUser);
	}

	@GetMapping(path = "api/user/count/v2")
	public ResponseSuccess<Integer> countUser(){
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Success",userService.countUser());
	}
	@GetMapping(path="api/user/v2/{id}")
	public ResponseSuccess<UserDTO> getOneUserById(@PathVariable("id") int id) {
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Success",userService.findOneById(id));
	}
	@GetMapping(path="api/user/UN/v2")
	public ResponseSuccess<UserDTO> getOneUserByUsername(@RequestParam("username") String username) {
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Success",userService.findOneByUsername(username));
	}
	@GetMapping(path="api/user/v2")
	public ResponseSuccess<List<UserDTO>> search(@RequestParam("txtSearch") String txtSearch) {
		if(txtSearch == null)
			return new ResponseError(HttpStatus.BAD_REQUEST.value(),"Geted Fail");
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Success",userService.findAllUserLikeUsername(txtSearch));
	}
	@PostMapping(path = "api/user/add/v2")
	public ResponseSuccess<UserDTO> addUser(@RequestBody UserDTO user) {
		if(userService.existUsername(user.getUsername()))
			return new ResponseError(HttpStatus.BAD_REQUEST.value(),"Post Fail");
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Success",userService.insertUser(user));
	}
	@PostMapping(path = "api/user/signup/v2")
	public ResponseSuccess<UserDTO> signup(@RequestBody UserDTO user) {
		if(userService.existUsername(user.getUsername()))
			return new ResponseError(HttpStatus.BAD_REQUEST.value(),"Post Fail");
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Success",userService.insertUser(user));
	}
	@PostMapping(path = {"api/user/signin/v2","api/user/login/v2"})
	public ResponseSuccess<UserDTO> signin(@RequestBody UserDTO user) {
		UserDTO userD = userService.findOneByUsernameAndPasswordAndStatus(user.getUsername(), user.getPassword(), 1);
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Login Success",userD);
	}
	@PutMapping(path = {"api/user/v2"})
	public ResponseSuccess<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
		if(userDTO.getId() == 0)
			return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Update Fail");
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Success",userService.updateUser(userDTO));
	}
	@DeleteMapping(path="api/user/v2/{id}")
	public ResponseSuccess<?> deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Deleted user("+id+") success");
	}
	@PutMapping(path="api/user/active/v2")
	public ResponseSuccess<?> changeStatusUser(@RequestParam("user_id") Integer user_id,
			@RequestParam("status") Integer status) {
		if(user_id == null)
			return new ResponseError(HttpStatus.BAD_REQUEST.value(),"Change status fail");
		userService.activeUser(user_id, status);
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Change status success");
	}
	@PostMapping(path="api/user/cateCare/v2")
	public ResponseSuccess<?> cateCare(@RequestBody UserDTO userD) {
		userService.addCateCare(userD);
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Success");
	}
	
	
}
