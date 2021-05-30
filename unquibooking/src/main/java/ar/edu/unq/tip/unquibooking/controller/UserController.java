package ar.edu.unq.tip.unquibooking.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.tip.unquibooking.exception.UserBadRequestException;
import ar.edu.unq.tip.unquibooking.exception.UserNotFoundException;
import ar.edu.unq.tip.unquibooking.model.Booking;
import ar.edu.unq.tip.unquibooking.model.User;
import ar.edu.unq.tip.unquibooking.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping()
    public ArrayList<User> getAllUsers(){
         return userService.getAllUsers();
    }
	
	@PostMapping()
	public User saveUser(@RequestBody User user) throws UserBadRequestException {
		return userService.saveUser(user);
	}
	
	@GetMapping(path="/{id}")
	public User getUSer(@PathVariable("id") Long id) throws UserNotFoundException {
		return userService.getUSer(id);
	}
	
	@GetMapping("/query")
	public ArrayList<User> getUserByMail(@RequestParam("mail") String mail){
		return userService.getUserByMail(mail);
	}

}
