package menu;

import java.util.Scanner;

public class Menu {

    private Scanner scn;
    private int option;

    public Menu() {
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

    private void option1() {}

    private void option2() {}

    private void option3() {}

    private void option4() {}
}
