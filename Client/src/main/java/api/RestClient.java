package api;

import dtos.Department;
import dtos.Employee;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class RestClient {

    // Default parameters
    private static final String DEFAULT_URL = "https://rest2daeva.easybyte.club/version/1";
    private static final String XAPIKEY = "TgGJrf2EdN1y4VrM79fzZoYP5Q1C89sM";

    // Making RestClient singleton, to avoid unexpected errors
    private static RestClient singleInstance = null;
    public static RestClient getInstance() {
        if(singleInstance == null) {
            singleInstance = new RestClient();
        }
        return singleInstance;
    }

    public ArrayList<Employee> getAllEmployees() {
        try {
            // Create HTTP Client
            CloseableHttpClient client = HttpClientBuilder.create().build();
            // Define HTTP Method
            HttpGet request = new HttpGet(DEFAULT_URL+"/employees");
            // Define AUTH for API-REST
            request.addHeader("AUTH_EMPRESAPOP", XAPIKEY);
            // Execute request and save response
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            // Check if status code is 200
            if (code == 200) {
                // Get entity response from request
                HttpEntity entity = response.getEntity();
                // Convert it to string
                String responseString = EntityUtils.toString(entity);
                // Convert it JSONArray
                JSONArray jsonResponse = new JSONArray(responseString);
                // Parse it to model Employee (ArrayList of)
                ArrayList<Employee> employees = new ArrayList();
                for (int indice = 0; indice < jsonResponse.length(); indice++) {
                    try {
                        employees.add(new Employee(jsonResponse.getJSONObject(indice)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return employees;
            } else {
                throw new RequestFailedException(code);
            }
        } catch (JSONException | IOException | RequestFailedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Department> getAllDepartments() {
        try {
            // Create HTTP Client
            CloseableHttpClient client = HttpClientBuilder.create().build();
            // Define HTTP Method
            HttpGet request = new HttpGet(DEFAULT_URL+"/departments");
            // Define AUTH for API-REST
            request.addHeader("AUTH_EMPRESAPOP", XAPIKEY);
            // Execute request and save response
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            // Check if status code is 200
            if (code == 200) {
                // Get entity response from request
                HttpEntity entity = response.getEntity();
                // Convert it to string
                String responseString = EntityUtils.toString(entity);
                // Convert it JSONArray
                JSONArray jsonResponse = new JSONArray(responseString);
                // Parse it to model Employee (ArrayList of)
                ArrayList<Department> departments = new ArrayList();
                for (int indice = 0; indice < jsonResponse.length(); indice++) {
                    try {
                        departments.add(new Department(jsonResponse.getJSONObject(indice)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return departments;
            } else {
                throw new RequestFailedException(code);
            }
        } catch (JSONException | IOException | RequestFailedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createNewEmployee(Employee dto) {
        // Create body request, parsing dto into a JSONObject
        JSONObject body = new JSONObject();
        body.put("empno", dto.getEmpno());
        body.put("empname", dto.getEmpname());
        body.put("job", dto.getJob());
        body.put("mgr", dto.getMgr());
        body.put("hiredate", dto.getHiredate());
        body.put("sal", dto.getSal());
        body.put("comm", dto.getComm());
        body.put("deptno", dto.getDeptno());

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost(DEFAULT_URL + "/employee");

            StringEntity params = new StringEntity(body.toString());
            request.addHeader("content-type", "application/json");
            request.addHeader("AUTH_EMPRESAPOP", XAPIKEY);
            request.setEntity(params);
            HttpResponse httpResp = httpClient.execute(request);
            int code = httpResp.getStatusLine().getStatusCode();
            if(code!=201) {
                throw new RuntimeException("Failed POST request - HTTP error code: " + code);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteEmployeeById(int id) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpDelete request = new HttpDelete(DEFAULT_URL + "/employee/" + id);
            request.addHeader("AUTH_EMPRESAPOP", XAPIKEY);
            HttpResponse httpResponse = httpClient.execute(request);
            int code = httpResponse.getStatusLine().getStatusCode();
            if(code!=200) {
                throw new RuntimeException("Failed DELETE request - HTTP error code: " + code);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
