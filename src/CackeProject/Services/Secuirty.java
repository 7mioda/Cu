package CackeProject.Services;


import org.mindrot.jbcrypt.BCrypt;

import java.security.NoSuchAlgorithmException;


public class Secuirty {
    private final static String salt = BCrypt.gensalt(12);

    public static String getSecurePassword(String passwordToHash)throws NoSuchAlgorithmException
    {
        return   BCrypt.hashpw(passwordToHash,salt);

    }
    public static boolean matched(String originalPassword,String generatedSecuredPasswordHash){
        return  BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
    }

}
