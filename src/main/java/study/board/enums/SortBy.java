package study.board.enums;

public enum SortBy {
    LATEST("최신순"), LIKES("인기순"), VIEWS("조회순");

    private final String displayValue;

    SortBy(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return this.displayValue;
    }
}
