package study.board.enums;

public enum Category {
    ALL("전체"), DAILY("일상"), HUMOR("유머"), GAME("게임"), SPORTS("스포츠");

    private final String displayValue;

    Category(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return this.displayValue;
    }
}
