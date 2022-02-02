package application.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.type.IntegerType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="emp")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeModel {
    @Id
    @Column(name="empno", nullable = false)
    private Integer empNo;

    @Column(name="ename", length = 10)
    private String empName;

    @Column(name="job",length = 9)
    private String job;

    @Column(name="mgr")
    private Integer mgr;

    @Column(name="hiredate")
    private Date hireDate;

    @Column(name="sal")
    private Double sal;

    @Column(name="comm")
    private Double comm;

    @Column(name="deptno", nullable = false)
    private Double deptno;

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
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
