package application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dept")
public class DeptModel {
    @Id
    @Column(name="deptno", nullable = false)
    private int deptNo;

    @Column(name="dname", length = 14)
    private String dName;

    @Column(name="loc",length = 13)
    private String loc;
}
