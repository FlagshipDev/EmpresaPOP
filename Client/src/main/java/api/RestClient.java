package api;

import dto.EmployeeDTO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RestClient {

    private final RestClientHelper api = new RestClientHelper();

    public ArrayList<EmployeeDTO> getEmployees() {
        JSONArray jsonResponse = api.getResponse("/employees");
        ArrayList<EmployeeDTO> employees = new ArrayList();
        for (int indice = 0; indice < jsonResponse.length(); indice++) {
            try {
                employees.add(new EmployeeDTO(jsonResponse.getJSONObject(indice)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return employees;
    }

    public ArrayList<EmployeeDTO> postEmployee(EmployeeDTO dto) {
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

    public ArrayList<EmployeeDTO> deleteEmployee(EmployeeDTO dto) {
        if(api.deleteRequest("/employee/"+dto.getEmpno)){
            System.out.println("Todo ok");
        } else {
            System.out.println("Algo ha fallado");
        }
        return getEmployees();
    }
}
