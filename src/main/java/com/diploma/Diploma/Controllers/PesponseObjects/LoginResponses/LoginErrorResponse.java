package com.diploma.Diploma.Controllers.PesponseObjects.LoginResponses;

import com.diploma.Diploma.Utils.LoginResponse;
import lombok.Data;

@Data
public class LoginErrorResponse implements LoginBasicResponse{
    private LoginResponse loginResponse;
    private String message;
    private String login;
}
