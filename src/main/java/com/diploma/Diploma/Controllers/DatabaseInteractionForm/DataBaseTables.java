package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/dataBaseTables")
public class DataBaseTables {
    @GetMapping
    public HashMap<String, String> getDataBaseTables(){
        HashMap<String, String> engToRusTables = new HashMap<>();
        engToRusTables.put("department", "Отдел/цех");
        engToRusTables.put("destructionType", "Тип разрушения");
        engToRusTables.put("emergency", "Чрезвычайное событие");
        engToRusTables.put("emergencySubType", "Подтип чрезвычайного события");
        engToRusTables.put("enterprise", "Предприятие");
        engToRusTables.put("equipmentClass", "Класс оборудования");
        engToRusTables.put("equipmentInDepartment", "Оборудование в цеху/отделе");
        engToRusTables.put("equipmentType", "Тип оборудования");
        engToRusTables.put("event", "Событие");
        engToRusTables.put("exposureType", "Поражающий фактор");
        engToRusTables.put("potentiallyDangerousSituation", "Потенциально-опасная ситуация");
        engToRusTables.put("role", "Роль");
        engToRusTables.put("substance", "Вещество");
        engToRusTables.put("substanceType", "Тип вещества");
        engToRusTables.put("user", "Пользователь");
        return engToRusTables;
    }
}
