package study.board.exceptions;

public class NoArticleFoundException extends RuntimeException{
    public NoArticleFoundException(){
        super("존재하지 않는 글입니다.");
    }
}
