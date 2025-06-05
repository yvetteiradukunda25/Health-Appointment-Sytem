package com.example.healthcaresystem.user;
//
//import com.example.healthcaresystem.dto.RegisterRequest;
//import com.example.healthcaresystem.dto.LoginRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
public class UserService {

}


//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public User registerUser(RegisterRequest request) {
//        if (userRepository.existsByEmail(request.getEmail())) {
//            throw new RuntimeException("Email already exists");
//        }
//        User user = new User();
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        return userRepository.save(user);
//    }
//
//    public User authenticateUser(LoginRequest request) {
//        return userRepository.findByEmail(request.getEmail())
//                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
//                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
//    }