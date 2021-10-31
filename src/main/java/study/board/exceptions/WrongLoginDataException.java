package study.board.exceptions;

public class WrongLoginDataException extends RuntimeException{
    public WrongLoginDataException() {
        super("아이디 혹은 비밀번호가 일치하지 않습니다.");
    }
}
