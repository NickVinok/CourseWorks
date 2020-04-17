package Controllers.PesponseObjects.LoginResponses;

import Utils.LoginResponse;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class LoginEnterpriseAdminResponse implements LoginBasicResponse{
    private LoginResponse loginResponse;
    private static Map<String, String> availableTables = Stream.of(new String[][]{
            {"Предприятие", "enterprise"},
            {"Цех", "department"},
            {"Класс оборудования","equipmentClass"},
            {"Вещество","substance"},
            {"Тип оборудования","equipmentType"},
            {"Оборудование в цехе","equipmentInDepartment"},
            {"Событие","event"},
            {"Зона","zone"},
            {"Территория","territory"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}
