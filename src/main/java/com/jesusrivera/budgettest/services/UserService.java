package com.jesusrivera.budgettest.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesusrivera.budgettest.models.User;
import com.jesusrivera.budgettest.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository uR;
	
	public User create(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return uR.save(user);
    }
	
	public User findByEmail(String email) {
        return uR.findByEmail(email);
    }
	
	public User findById(Long id) {
		Optional<User> uO = uR.findById(id);
		if(uO.isPresent()) {
			return uO.get();
		}
		return null;
	}
	
	public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = uR.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

	public List<User> getAllUsers() {
		return uR.findAll();
	}
	
	public User updateUser(User user, Long id) {
		Optional<User> oU = uR.findById(id);
		if (oU.isPresent()) {
			User u = oU.get();
			u.setName(user.getName());
			u.setEmail(user.getEmail());
			u.setPassword(user.getPassword());
			return uR.save(u);
		}
		return null;
	}
	
	
	public String deleteUser(Long id) {
		Optional<User> oU = uR.findById(id);
		if (oU.isPresent()) {
			uR.delete(oU.get());
			return "succes";
		}
		return null;
	}
}
