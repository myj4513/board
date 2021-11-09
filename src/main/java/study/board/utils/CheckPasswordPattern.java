package study.board.utils;

import java.util.regex.Pattern;

public class CheckPasswordPattern {
    private static Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#$%^&*()+|=]{8,20}$");

    public static boolean isValidPassword(String password){
        return pattern.matcher(password).matches();
    }
}
