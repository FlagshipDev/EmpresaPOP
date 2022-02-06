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

    public void postEmployee(Employee dto) {
        JSONObject body = new JSONObject();
        body.put("body", dto);
        String message =  api.postRequest("/employee", body) ? "Todo correcto" : "algo ha fallado";
        System.out.println(message);
    }

    public void deleteEmployee(Employee dto) {
        if(api.deleteRequest("/employee/"+dto.getEmpno())){
            System.out.println("Todo ok");
        } else {
            System.out.println("Algo ha fallado");
        }
    }


}
