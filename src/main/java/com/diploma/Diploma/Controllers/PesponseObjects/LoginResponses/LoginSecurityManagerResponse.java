package com.diploma.Diploma.Controllers.PesponseObjects.LoginResponses;

import com.diploma.Diploma.Utils.LoginResponse;
import lombok.Data;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Data
public class LoginSecurityManagerResponse implements LoginBasicResponse{
    private LoginResponse loginResponse;
    private static Map<String, String> availableTables = Stream.of(new String[][]{
            {"Пользователь", "user"},
            {"Роль", "role"},
            {"Право","right"},
            {"Права роли","rolesRight"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}
