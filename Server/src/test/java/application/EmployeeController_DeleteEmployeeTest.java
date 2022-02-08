package application;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeController_DeleteEmployeeTest {

    @Autowired
    private MockMvc mvc;

    @Before
    public void postTempEmployee() throws Exception {

    }

    // Testing code 200
    @Test
    public void deleteEmployee_succesfulyDeleted() throws Exception {
        JSONObject tempEmployee = new JSONObject();
        tempEmployee.put("empno", 9999);
        tempEmployee.put("ename", "Pepe de Pura");
        tempEmployee.put("job", "Presidente");
        tempEmployee.put("mgr", 9000);
        tempEmployee.put("hiredate", new Date());
        tempEmployee.put("sal", 9000);
        tempEmployee.put("comm", 9000);
        tempEmployee.put("deptno", 10);
        mvc.perform(MockMvcRequestBuilders.post("/employee")
                        .header("AUTH_EMPRESAPOP", AppTest.AUTH_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tempEmployee.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    // Testing code 400
    @Test
    public void deleteEmployee_IdDontExist() {
        // !TODO Implement test logic
    }

    // Testing code 403
    @Test
    public void deleteEmployee_Forbidden() {
        // !TODO Implement test logic
    }
}
