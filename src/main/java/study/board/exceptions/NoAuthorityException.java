package study.board.exceptions;

public class NoAuthorityException extends RuntimeException {
    public NoAuthorityException(){
        super("접근 권한이 없습니다.");
    }
}
