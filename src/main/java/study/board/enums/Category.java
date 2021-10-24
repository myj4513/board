package study.board.enums;

public enum Category {
    DAILY("일상"), HUMOR("유머"), GAME("게임"), SPORTS("스포츠");

    private String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName(){
        return this.categoryName;
    }

    public static boolean contains(String categoryName){
        for(Category c : Category.values()){
            if(c.getCategoryName().equals(categoryName)) return true;
        }
        return false;
    }
}
