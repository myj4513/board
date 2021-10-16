package study.board.utils;

public enum Category {
    일상, 유머, 게임, 스포츠;

    public static boolean contains(String category){

        for(Category c : Category.values()){
            if(c.name().equals(category)) return true;
        }
        return false;
    }
}
