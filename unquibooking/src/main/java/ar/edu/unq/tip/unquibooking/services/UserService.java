package ar.edu.unq.tip.unquibooking.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.tip.unquibooking.exception.UserBadRequestException;
import ar.edu.unq.tip.unquibooking.exception.UserNotFoundException;
import ar.edu.unq.tip.unquibooking.model.User;
import ar.edu.unq.tip.unquibooking.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public ArrayList<User> getAllUsers(){
		return (ArrayList<User>) userRepository.findAll();
	}
	
	public User saveUser(User user) throws UserBadRequestException {
		if(!validateUser(user)) {
			throw new UserBadRequestException("Wrong user");
		}
		return userRepository.save(user);
	}

	public User getUSer(Long id) throws UserNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(id);
    	if(!optionalUser.isPresent()) {
    		throw new UserNotFoundException("Desk not found");
    	}
        return optionalUser.get();
	}
	
	public ArrayList<User> getUserByMail(String mail){
		return userRepository.findByMail(mail);
	}
	
	public boolean validateName(String name) {
		return name.length() > 3 && name.split(" ").length >= 2 && !name.matches(".*[1234567890].*");
	}
	
	public boolean validateMail(String mail) {
		ArrayList<String> allMails = (ArrayList<String>) getAllUsers().stream().map(u -> u.getMail().toLowerCase()).collect(Collectors.toList());
    	boolean uniqueMail = allMails.contains(mail);
		return mail.substring(mail.length()-19, mail.length()).equals("@alu.edu.unq.com.ar") && mail.length()>19 && !uniqueMail;
	}
	
	public boolean validatePassword(String password) {
		return password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*[1234567890].*") && (password.length() >=8 && password.length()<=15);
	}

	private boolean validateUser(User user) {
		return validateName(user.getName()) && validateMail(user.getMail()) && validatePassword(user.getPassword());
		
	}
	
}
