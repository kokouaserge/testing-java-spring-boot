package com.crud.restapi.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.restapi.bean.BeanValidator;
import com.crud.restapi.bean.ResultDTO;
import com.crud.restapi.model.User;
import com.crud.restapi.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BeanValidator beanValidator;

	@PostMapping("")
	public ResponseEntity<?> createUser(@RequestBody User reqData) {
		System.err.println(":::  UserController.createUser :::");
		ResultDTO<?> responsePacket = null;
		try {
			ArrayList<String> errorList = beanValidator.userValidate(reqData);
			if (errorList.size() != 0) {
				ResultDTO<ArrayList<String>> errorPacket = new ResultDTO<>(errorList,
						"Above fields values must not be empty", false);
				return new ResponseEntity<>(errorPacket, HttpStatus.BAD_REQUEST);
			}
			User isData = userService.isDataExist(reqData);
			if (isData == null) {
				responsePacket = new ResultDTO<>(userService.createUser(reqData), "User Created Successfully", true);
				return new ResponseEntity<>(responsePacket, HttpStatus.OK);
			} else {
				responsePacket = new ResultDTO<>("Record already exist", false);
				return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responsePacket = new ResultDTO<>(e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("")
	public ResponseEntity<?> findUsers() {
		System.err.println(":::  UserController.allUsers :::");
		ResultDTO<?> responsePacket = null;
		try {
			responsePacket = new ResultDTO<>(userService.getAllUsers(), "Users fetched successfully !!", true);
			return new ResponseEntity<>(responsePacket, HttpStatus.OK);
		} catch (Exception e) {
			responsePacket = new ResultDTO<>(e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
		System.err.println(":::  UserController.getUserById :::");
		ResultDTO<?> responsePacket = null;
		try {
			responsePacket = new ResultDTO<>(userService.getUserById(id), "User fetched successfully !!", true);
			return new ResponseEntity<>(responsePacket, HttpStatus.OK);
		} catch (Exception e) {
			responsePacket = new ResultDTO<>(e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("")
	public ResponseEntity<?> updateUser(@RequestBody User reqData) {
		System.err.println(":::  UserController.updateUser :::");
		ResultDTO<?> responsePacket = null;
		try {
			Optional<User> isData = userService.findUser(reqData);
			if (isData.isPresent()) {
				responsePacket = new ResultDTO<>(userService.updateUser(reqData, isData.get()),
						"User Updated Successfully", true);
				return new ResponseEntity<>(responsePacket, HttpStatus.OK);
			} else {
				responsePacket = new ResultDTO<>("Record not exist", false);
				return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responsePacket = new ResultDTO<>(e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
		System.err.println(":::  UserController.deleteUserById :::");
		ResultDTO<?> responsePacket = null;
		try {
			responsePacket = new ResultDTO<>(userService.deleteUserById(id), "User deleted successfully !!", true);
			return new ResponseEntity<>(responsePacket, HttpStatus.OK);
		} catch (Exception e) {
			responsePacket = new ResultDTO<>(e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}

}
