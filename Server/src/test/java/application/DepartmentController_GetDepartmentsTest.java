package application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentController_GetDepartmentsTest {

    @Autowired
    private MockMvc mvc;

    // Testing code 200
    @Test
    public void getDepartments_equalResponse() throws Exception {
        // !TODO Implement test logic
    }

    // Testing 403
    @Test
    public void getDepartments_NoTokenProvided() {
        // !TODO Implement test logic
    }
}
