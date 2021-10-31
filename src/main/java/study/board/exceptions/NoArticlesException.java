package study.board.exceptions;

public class NoArticlesException extends RuntimeException{
    public NoArticlesException(){
        super("게시글이 존재하지 않습니다.");
    }
}
