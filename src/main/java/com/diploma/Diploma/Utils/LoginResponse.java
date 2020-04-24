package com.diploma.Diploma.Utils;

import lombok.Data;

@Data
public class LoginResponse {
    private boolean isPresent;
    private boolean isPasswordCorrect;
    private boolean isResearcher;
    private boolean isEnterpriseAdmin;
    private boolean isHeadResearcher;
    private boolean isSecurityManager;
}
