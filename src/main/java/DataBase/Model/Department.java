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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clutterClassId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ClutterClass clutterClass;
}
