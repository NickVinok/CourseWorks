package com.diploma.Diploma.Controllers.PesponseObjects.LoginResponses;

import com.diploma.Diploma.DataBase.Model.RolesTableInteraction;
import com.diploma.Diploma.DataBase.Model.Tables;
import com.diploma.Diploma.Utils.LoginResponse;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class LoginAdminResponse implements LoginBasicResponse {
    private LoginResponse loginResponse;
    private HashMap<String, List<RolesTableInteraction>> adminFormData;
}
