package study.board.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class FieldErrorResponse {

    public final LocalDateTime localDateTime;
    public final HttpStatus status;
    public final Map fieldErr;

    public FieldErrorResponse(HttpStatus status, Map fieldErr) {
        localDateTime = LocalDateTime.now();
        this.status = status;
        this.fieldErr = fieldErr;
    }
}
