package helper;

import java.util.Random;

public class GenerateToken {
    public static String generateToken(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

        Random rand = new Random();

        String tokenUser = "";
        for (int i = 0; i < 70; i++) {
            tokenUser += characters.charAt(rand.nextInt(characters.length()));
        }

        return tokenUser;
    }
}
