package Controllers.PesponseObjects.LoginResponses;

import Utils.LoginResponse;
import lombok.Data;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Data
public class LoginHeadResearcherResponse implements LoginBasicResponse{
        private LoginResponse loginResponse;
        private static Map<String, String> availableTables = Stream.of(new String[][]{
                {"Потенциально опасная ситуация", "potentiallyDangerousSituation"},
                {"Сценарий аварии", "emergencyScenario"},
                {"Коэффициенты","coefficients"},
                {"Общие коэффициенты","generalCoefficients"},
                {"Подтип чрезвычайного события","emergencySubType"},
                {"Коэффициент подтипа ЧС","emergencySubTypeCoefficients"},
                {"Чрезвычайное событие","emergency"},
                {"Поражающий фактор","exposureType"}
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}
