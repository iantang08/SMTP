import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {
    
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return bytesToHex(digest);
    }
    
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x",b));
        }
        return hexString.toString();
    }
    
    public static boolean comparePassword(String enteredPassword, String storedHash) throws NoSuchAlgorithmException {
        String hashed = hashPassword(enteredPassword);
        return hashed.equals(storedHash);
    }

}