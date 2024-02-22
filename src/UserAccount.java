import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class UserAccount {
    private String username;
    private String hashedPassword;

    public UserAccount(String username, String password) throws NoSuchAlgorithmException {
        this.username = username;
        this.hashedPassword = password; 
    }

    // Method to update password
    public void updatePassword(String newPassword) throws NoSuchAlgorithmException {
        this.hashedPassword = PasswordHashing.hashPassword(newPassword);
    }
    
    public String getPassword() {
    	return hashedPassword;
    }

	public String getUsername() {
		return username;
	}
}