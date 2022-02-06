package menu;

import api.RestClient;
import dtos.Employee;
import org.json.JSONObject;

import java.util.Scanner;

public class Menu {

    private RestClient api;
    private Scanner scn;
    private int option;

    public Menu() {
        this.api = new RestClient();
        this.scn = new Scanner(System.in);
        this.option = 12;
    }

    public void launch() {
        while(this.option != 5) {
            showMenu();
            System.out.print("\tEscoge una opcion: ");
            option = scn.nextInt();

            switch (option) {
                case 1: option1(); break;
                case 2: option2(); break;
                case 3: option3(); break;
                case 4: option4(); break;
            }
        }
    }

    private void showMenu() {
        System.out.println("Opciones disponibles:");
        System.out.println("\t1.- Insertar empleado");
        System.out.println("\t2.- Borrar empleado");
        System.out.println("\t3.- Visualizar todos los empleados");
        System.out.println("\t4.- Visualizar todos los departamentos");
        System.out.println("\t5.- Salir");
    }

    private void option1() {
        System.out.print("Numero de empleado: ");
        int empno = scn.nextInt();
        System.out.print("Nombre del empleado: ");
        String ename = scn.next();
        System.out.print("Trabajo del empleado: ");
        String job = scn.next();
        System.out.print("Mgr del empleado: ");
        Integer mgr = scn.nextInt();
        System.out.print("Fecha en la que el empleado fue contratado: ");
        String hiredate = scn.next();
        System.out.print("Salario del empleado: ");
        Integer sal = scn.nextInt();
        System.out.print("Comm del empleado: ");
        Integer comm = scn.nextInt();
        System.out.print("Numero de departamento del empleado: ");
        Integer deptno = scn.nextInt();

        JSONObject jsonEmployee = new JSONObject();
        jsonEmployee.put("empno", empno);
        jsonEmployee.put("ename", ename);
        jsonEmployee.put("job", job);
        jsonEmployee.put("mgr", mgr);
        jsonEmployee.put("hiredate", hiredate);
        jsonEmployee.put("sal", sal);
        jsonEmployee.put("comm", comm);
        jsonEmployee.put("deptno", deptno);

        Employee newEmployee = new Employee(jsonEmployee);
        api.postEmployee(newEmployee);
    }

    private void option2() {}

    private void option3() {}

    private void option4() {}
}
