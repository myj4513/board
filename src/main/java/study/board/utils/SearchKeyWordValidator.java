package study.board.utils;

import java.util.Arrays;

public class SearchKeyWordValidator {

    private static String[] excludedKeyWords = {"ex1", "ex2", "ex3"};

    public static boolean isValid(String q){
        if(q.isBlank() || q.length()<2 || q==null)
            return false;
        return true;
    }

    public static String[] filterKeyWords(String[] keyWords){
        return Arrays.stream(keyWords).filter(keyWord -> Arrays.stream(excludedKeyWords).noneMatch(eky -> keyWord.contains(eky))).toArray(String[]::new);
    }

}
