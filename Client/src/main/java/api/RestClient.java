package api;

import dtos.Department;
import dtos.Employee;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RestClient {

    private final RestClientHelper api = new RestClientHelper();

    public ArrayList<Employee> getEmployees() {
        JSONArray jsonResponse = api.getResponse("/employees");
        ArrayList<Employee> employees = new ArrayList();
        for (int indice = 0; indice < jsonResponse.length(); indice++) {
            try {
                employees.add(new Employee(jsonResponse.getJSONObject(indice)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return employees;
    }

    public ArrayList<Department> getDepartments() {
        JSONArray jsonResponse = api.getResponse("/departments");
        ArrayList<Department> department = new ArrayList();
        for (int indice = 0; indice < jsonResponse.length(); indice++) {
            try {
                department.add(new Department(jsonResponse.getJSONObject(indice)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return department;
    }

    public ArrayList<Employee> postEmployee(Employee dto) {
        // crear el dto del empleado
        JSONObject body = new JSONObject();
        //body.put("empno", dto.getEmpno());

        if(api.postRequest("/employee", body)){
            System.out.println("Todo correcto");
        } else {
            System.out.println("algo ha fallado");
        }
        return getEmployees();
    }

    public ArrayList<Employee> deleteEmployee(Employee dto) {
        if(api.deleteRequest("/employee/"+dto.getEmpno())){
            System.out.println("Todo ok");
        } else {
            System.out.println("Algo ha fallado");
        }
        return getEmployees();
    }


}
