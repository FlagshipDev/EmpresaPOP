package app;

import menu.Menu;

public class Main {


    public static void main (String[] args) {
        Menu mainMenu = new Menu();
        mainMenu.launch();

        /*

        Ejemplos:

        ** GET EMPLOYEES **
        RestClient.getInstance().getAllEmployees()
                .forEach(employee ->
                        System.out.println(employee.getEmpno()));

        ** GET DEPARTMENTS**
        RestClient.getInstance().getAllDepartments()
                .forEach(department ->
                        System.out.println(department.getDname()));

       ** POST **
        JSONObject body = new JSONObject();
        body.put("empno", 999);
        body.put("empname", "test");
        body.put("job", "test");
        body.put("mgr", 1);
        body.put("hiredate", "1980-12-17T00:00:00.000+00:00");
        body.put("sal", 1);
        body.put("comm", 1);
        body.put("deptno", 1);

        RestClient.getInstance().createNewEmployee(new Employee(body));

        ** DELETE **
        RestClient.getInstance().deleteEmployeeById(1);
         */
    }
}
