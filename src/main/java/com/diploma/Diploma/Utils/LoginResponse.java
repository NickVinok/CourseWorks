package com.diploma.Diploma.Utils;

import com.diploma.Diploma.DataBase.Model.User;
import lombok.Data;

@Data
public class LoginResponse {
    private boolean isPresent;
    private boolean isPasswordCorrect;
    private User user;
}
