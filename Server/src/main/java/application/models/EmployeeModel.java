package application.models;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="emp")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeModel {
    @Id
    @Column(name="empno", nullable = false)
    private Integer empno;

    @Column(name="ename")
    private String empname;

    @Column(name="job")
    private String job;

    @Column(name="mgr")
    private Integer mgr;

    @Column(name="hiredate")
    private Date hiredate;

    @Column(name="sal")
    private Double sal;

    @Column(name="comm")
    private Double comm;

    @Column(name="deptno", nullable = false)
    private Double deptno;

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
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

    public double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public double getDeptno() {
        return deptno;
    }

    public void setDeptno(Double deptno) {
        this.deptno = deptno;
    }
}
