package com.diploma.Diploma.Controllers;

import com.diploma.Diploma.DataBase.Model.Interaction;
import com.diploma.Diploma.DataBase.Model.Keys.RolesTableInteractionKey;
import com.diploma.Diploma.DataBase.Model.Role;
import com.diploma.Diploma.DataBase.Model.RolesTableInteraction;
import com.diploma.Diploma.DataBase.Model.Tables;
import com.diploma.Diploma.DataBase.Repo.InteractionRepo;
import com.diploma.Diploma.DataBase.Repo.RoleRepo;
import com.diploma.Diploma.DataBase.Repo.RolesTableInteractionRepo;
import com.diploma.Diploma.DataBase.Repo.TablesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/temporal")
public class CreateRolesInteractionTableController {
    @Autowired
    private TablesRepo tRepo;
    @Autowired
    private InteractionRepo iRepo;
    @Autowired
    private RoleRepo rRepo;
    @Autowired
    private RolesTableInteractionRepo rtiRepo;

    @GetMapping
    public void createTable(){
        List<Tables> tablesList = tRepo.findAll();
        List<Interaction> interactionsList = iRepo.findAll();
        List<Role> rolesList = rRepo.findAll();
        for(Role r : rolesList){
            for(Tables t : tablesList){
                for(Interaction i : interactionsList){
                    RolesTableInteractionKey key = new RolesTableInteractionKey(r.getId(),t.getId(),i.getId());
                    RolesTableInteraction rti = new RolesTableInteraction();
                    rti.setRolesTableInteractionKey(key);
                    rti.setRole(r);
                    rti.setTables(t);
                    rti.setInteraction(i);
                    rti.setHas(false);

                    rtiRepo.save(rti);
                }
            }
        }
    }
}
