package study.board.exceptions;

public class DuplicateLoginIdException extends RuntimeException{

    public DuplicateLoginIdException(){
        super("이미 사용중인 아이디입니다.");
    }
}
