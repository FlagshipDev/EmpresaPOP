package application.exception;

public class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(String emnpNo) {
        super("EmpresaPOP API - Could not find department " + emnpNo);
    }
}
