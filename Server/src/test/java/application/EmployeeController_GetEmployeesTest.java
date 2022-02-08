package application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeController_GetEmployeesTest {

    @Autowired
    private MockMvc mvc;

    // Testing code 200
    @Test
    public void getEmployees_equalResponse() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/employees").header("AUTH_EMPRESAPOP", AppTest.AUTH_TOKEN)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].empno").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].empname").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].job").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].mgr").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].hiredate").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].sal").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].comm").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].deptno").exists());
    }

    // Testing 403
    /*@Test
    public void getEmployees_Forbidden() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }*/
}
