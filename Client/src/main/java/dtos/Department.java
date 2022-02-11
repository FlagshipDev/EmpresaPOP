package dtos;

import org.json.JSONObject;

/**
 * DTO class to transfer data.
 * @author Aandrade
 */
public class Department {

    private int deptno;
    private String dname;
    private String loc;

    /**
     * Default constructor to save the data from json file
     * 
     * @param json JSONObject to save the data in each attribute.
     */
    public Department(JSONObject json) {
        this.deptno = json.getInt("deptno");
        this.dname = json.getString("dname");
        this.loc = json.getString("loc");
    }

    /**
     * @return number of department
     */
    public int getDeptno() {
        return deptno;
    }

    /**
     * @return name of department
     */
    public String getDname() {
        return dname;
    }

    /**
     * @return location of the department
     */
    public String getLoc() {
        return loc;
    }
}
