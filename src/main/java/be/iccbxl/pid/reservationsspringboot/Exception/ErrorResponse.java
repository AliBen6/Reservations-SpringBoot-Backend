package be.iccbxl.pid.reservationsspringboot.Exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    // Status of the error
    private int status;

    // General error message about nature of error
    private String message;

    // Specific errors in API request processing
    private List<String> details;

    private String path;
}