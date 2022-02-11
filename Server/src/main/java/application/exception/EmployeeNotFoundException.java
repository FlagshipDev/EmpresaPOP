package application.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Integer emnpNo) {
        super("EmpresaPOP API - Could not find employee " + emnpNo);
    }
}
