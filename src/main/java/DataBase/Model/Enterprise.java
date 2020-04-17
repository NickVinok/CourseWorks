package DataBase.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Enterprise {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private double area;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "enterprise")
    private List<Department> departments = new ArrayList<>();

    public void addDepartment(Department department){
        department.setEnterprise(this);
        departments.add(department);
    }
    public void deleteDepartment(Department department){
        departments.remove(department);
        department.setEnterprise(null);
    }
}
