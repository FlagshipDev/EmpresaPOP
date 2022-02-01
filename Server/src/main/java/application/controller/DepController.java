package application.controller;

import application.models.DeptModel;
import application.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepController {

    @Autowired
    private final DepartmentRepository repository;

    DepController(DepartmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/departments")
    public List<DeptModel> getAllDepts() {
        return repository.findAll();
    }
}
