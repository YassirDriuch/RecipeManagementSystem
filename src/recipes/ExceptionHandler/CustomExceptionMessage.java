package recipes.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@AllArgsConstructor
@Data
public class CustomExceptionMessage{
    private LocalTime timestamp;
    private int status;
    private String error, message, path;

}
