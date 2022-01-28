package dto;

import org.json.JSONException;
import org.json.JSONObject;

public class EmployeeDTO {
    // poner atributos de los empleados

    private String nombre;

    public EmployeeDTO(JSONObject json) throws JSONException {

        this.nombre = json.getString("nombre");

    }
}
