package application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeController_DeleteEmployeeTest {

    @Autowired
    private MockMvc mvc;

    // Testing code 200
    @Test
    public void deleteEmployee_succesfulyDeleted() {
        // !TODO Implement test logic
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
