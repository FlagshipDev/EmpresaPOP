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
public class DepartmentController_GetDepartmentsTest {

    @Autowired
    private MockMvc mvc;

    // Testing code 200
    @Test
    public void getDepartments_equalResponse() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/departments").header("AUTH_EMPRESAPOP", AppTest.AUTH_TOKEN)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].deptno").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].dname").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].loc").exists());
    }

    // Testing 403
    @Test
    public void getDepartments_NoTokenProvided() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/departments")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
