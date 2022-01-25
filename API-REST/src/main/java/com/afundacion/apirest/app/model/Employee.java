package com.afundacion.apirest.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="emp")
public class Employee implements Serializable {

    @Id
    @Column(name="empno")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer empno;

    @Column(name="ename")
    private String ename;

    @Column(name="job")
    private String job;

    @Column(name="mgr")
    private Integer mgr;

    @Column(name="hiredate")
    private Date hiredate;

    @Column(name="sal")
    private float sal;

    @Column(name="comm")
    private float comm;

    @JoinColumn(name="emp", referencedColumnName = "deptno")
    @Column(name="deptno")
    private Integer deptno;

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public float getSal() {
        return sal;
    }

    public void setSal(float sal) {
        this.sal = sal;
    }

    public float getComm() {
        return comm;
    }

    public void setComm(float comm) {
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }
}
