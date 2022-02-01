package application.repository;

import application.models.DeptModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DeptModel, String> {
}