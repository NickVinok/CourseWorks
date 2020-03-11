package DataBase.Model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
public class Department {
    @Id
    private long id;
    private double square;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clutterClassId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ClutterClass clutterClass;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "enterpriseId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Enterprise enterprise;

}
