package fs.smartsupply.identity.DTO;

import java.util.UUID;

public class AuthResponse {
    
    private UUID userId;
    private String token;
    private String tokenType = "Bearer";
    private String username;
    private String email;
    
    public AuthResponse() {
    }
    
    public AuthResponse(UUID id, String token, String username, String email) {
        this.userId = id;
        this.token = token;
        this.username = username;
        this.email = email;
    }

    public UUID getUserId() {
        return userId;
    }
    
    public void setUserId(UUID id) {
        this.userId = id;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getTokenType() {
        return tokenType;
    }
    
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}

