package dtos;

import org.json.JSONObject;

/**
 * DTO class to transfer data.
 * @author Aandrade
 */
public class Employee {

    private int empno;
    private String empname;
    private String job;
    private Integer mgr;
    private String hiredate;
    private double sal;
    private double comm;
    private int deptno;

    /**
     * Default constructor to save the data from json file
     *
     * @param json JSONObject to save the data in each attribute.
     */
    public Employee(JSONObject json) {
        this.empno = json.getInt("empno");
        this.empname = json.getString("empname");
        this.job = json.getString("job");
        if(json.has("mgr"))
            this.mgr = json.getInt("mgr");
        this.hiredate = json.getString("hiredate");
        this.sal = json.getDouble("sal");
        if(json.has("comm"))
            this.comm = json.getDouble("comm");
        this.deptno = json.getInt("deptno");
    }

    /**
     * @return number of employee
     */
    public int getEmpno() {
        return empno;
    }

    /**
     * @return name of employee
     */
    public String getEmpname() {
        return empname;
    }

    /**
     * @return the job of the employee
     */
    public String getJob() {
        return job;
    }

    /**
     * @return mgr of employee
     */
    public int getMgr() {
        return mgr;
    }

    /**
     * @return date when the employee was hired
     */
    public String getHiredate() {
        return hiredate;
    }

    /**
     * @return salary of employee
     */
    public double getSal() {
        return sal;
    }

    /**
     * @return comm of employee
     */
    public double getComm() {
        return comm;
    }

    /**
     * @return number of department
     */
    public int getDeptno() {
        return deptno;
    }
}
