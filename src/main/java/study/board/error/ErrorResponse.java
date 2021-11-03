package study.board.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    public final LocalDateTime timestamp;
    public final HttpStatus status;
    public final String message;

    public ErrorResponse(HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.status = status;
    }
}
