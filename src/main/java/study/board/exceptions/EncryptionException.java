package study.board.exceptions;

public class EncryptionException extends RuntimeException{
    public EncryptionException() {
        super("암호화 중 오류가 발생했습니다.");
    }
}
