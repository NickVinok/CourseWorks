package Controllers.PesponseObjects.LoginResponses;

import Utils.LoginResponse;
import lombok.Data;

@Data
public class LoginErrorResponse implements LoginBasicResponse{
    private LoginResponse loginResponse;
    private String message;
}
