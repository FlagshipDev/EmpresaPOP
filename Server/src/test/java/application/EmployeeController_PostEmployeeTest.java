package application;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeController_PostEmployeeTest {

    @Autowired
    private MockMvc mvc;

    // Testing code 201
    @Test
    public void postEmployee_successfullyCreated() throws Exception {
        //Create Temp Employee
        JSONObject tempEmployee = new JSONObject();
        tempEmployee.put("empno", 9999);
        tempEmployee.put("empname", "Pepe");
        tempEmployee.put("job", "Pdte");
        tempEmployee.put("mgr", 9000);
        tempEmployee.put("hiredate", "2022-01-01");
        tempEmployee.put("sal", 9000.0);
        tempEmployee.put("comm", 9000.0);
        tempEmployee.put("deptno", 10.0);
        mvc.perform(MockMvcRequestBuilders.post("/employee")
                        .header("AUTH_EMPRESAPOP", AppTest.AUTH_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tempEmployee.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        //Delete Employee
        mvc.perform(MockMvcRequestBuilders.delete("/employee/" +
                                tempEmployee.getInt("empno"))
                        .header("AUTH_EMPRESAPOP", AppTest.AUTH_TOKEN)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    // Testing code 400
    @Test
    public void postEmployee_IdDontExist() throws Exception {
        //Create Temp Employee
        JSONObject tempEmployee = new JSONObject();
        tempEmployee.put("empno", "abcd");
        tempEmployee.put("empname", "Pepe");
        tempEmployee.put("job", "Pdte");
        tempEmployee.put("mgr", 9000);
        tempEmployee.put("hiredate", "2022-01-01");
        tempEmployee.put("sal", 9000.0);
        tempEmployee.put("comm", 9000.0);
        tempEmployee.put("deptno", 10.0);
        mvc.perform(MockMvcRequestBuilders.post("/employee")
                        .header("AUTH_EMPRESAPOP", AppTest.AUTH_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tempEmployee.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(400));
    }
    // Testing code 403
    @Test
    public void postEmployee_Forbidden() throws Exception {
        //Create Temp Employee
        JSONObject tempEmployee = new JSONObject();
        tempEmployee.put("empno", 9999);
        tempEmployee.put("empname", "Pepe");
        tempEmployee.put("job", "Pdte");
        tempEmployee.put("mgr", 9000);
        tempEmployee.put("hiredate", "2022-01-01");
        tempEmployee.put("sal", 9000.0);
        tempEmployee.put("comm", 9000.0);
        tempEmployee.put("deptno", 10.0);
        mvc.perform(MockMvcRequestBuilders.post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tempEmployee.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(403));
    }
}
