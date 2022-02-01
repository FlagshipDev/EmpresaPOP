package application.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="emp")
public class EmployeeModel {
    @Id
    @Column(name="empno", nullable = false)
    private int empNo;

    @Column(name="ename", length = 10)
    private String empName;

    @Column(name="job",length = 9)
    private String job;

    @Column(name="mgr")
    private int mgr;

    @Column(name="hiredate")
    private Date hireDate;

    @Column(name="sal")
    private double sal;

    @Column(name="comm", nullable = true)
    private double comm;

    @Column(name="deptnp")
    private double deptno;

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
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

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
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

    public void setSal(double sal) {
        this.sal = sal;
    }

    public double getComm() {
        return comm;
    }

    public void setComm(double comm) {
        this.comm = comm;
    }

    public double getDeptno() {
        return deptno;
    }

    public void setDeptno(double deptno) {
        this.deptno = deptno;
    }
}
