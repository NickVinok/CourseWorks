package Utils;

import lombok.Data;

@Data
public class LoginResponse {
    private boolean isPresent;
    private boolean isPasswordCorrect;
    private boolean isResearcher;
    private boolean isAdmin;
}
