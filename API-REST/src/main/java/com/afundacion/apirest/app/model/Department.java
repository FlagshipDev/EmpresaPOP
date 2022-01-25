package com.afundacion.apirest.app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="course")
public class Department implements Serializable {

    @Id
    @Column(name="deptno")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer deptno;

    @Column(name="dname")
    private String dname;

    @Column(name="loc")
    private String loc;

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
