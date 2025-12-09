package fs.smartsupply.identity.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fs.smartsupply.identity.DTO.AuthResponse;
import fs.smartsupply.identity.DTO.LoginRequest;
import fs.smartsupply.identity.DTO.RegisterRequest;
import fs.smartsupply.identity.entity.User;
import fs.smartsupply.identity.repository.UserRepository;

import java.util.Optional;

@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    
    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }
        
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already registered");
        }
        
        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        user = userRepository.save(user);
        
        // Generate JWT token
        String token = jwtService.generateToken(user.getUsername());
        
        return new AuthResponse(user.getId(), token, user.getUsername(), user.getEmail());
    }
    
    public AuthResponse login(LoginRequest request) {
        // Find user by username or email
        Optional<User> userOpt = userRepository.findByUsernameOrEmail(
            request.getUsernameOrEmail(),
            request.getUsernameOrEmail()
        );
        
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid username/email or password");
        }
        
        User user = userOpt.get();
        
        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username/email or password");
        }
        
        // Generate JWT token
        String token = jwtService.generateToken(user.getUsername());
        
        return new AuthResponse(user.getId(), token, user.getUsername(), user.getEmail());
    }
}

