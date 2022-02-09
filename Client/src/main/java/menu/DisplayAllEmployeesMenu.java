package menu;

import api.RestClient;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;
import dtos.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayAllEmployeesMenu {

    private WindowBasedTextGUI textGUI;
    private Window mainWindow;

    private Table<String> table;

    public DisplayAllEmployeesMenu(WindowBasedTextGUI textGUI, Window mainWindow) {
        this.textGUI = textGUI;
        this.mainWindow = mainWindow;

        this.table = new Table<String>("Empno", "Empname", "Job", "Mgr", "Hirerdate", "Sal", "Comm", "Deptno");
    }

    public void show() {
        // Hide menuOptions window
        this.mainWindow.setVisible(false);

        // Create new window
        BasicWindow windowAllEmployees = new BasicWindow("Mostrar todos los empleados");
        windowAllEmployees.setHints(Arrays.asList(Window.Hint.CENTERED));
        windowAllEmployees.setFixedSize(new TerminalSize(80, 30));

        // Display the data saved from the request in the table
        displayData();

        // Add contentPanel to the menuEmployee
        windowAllEmployees.setComponent(table);

        // Add menuEmployee to the textGUI
        textGUI.addWindowAndWait(windowAllEmployees);
    }

    private void displayData() {
        ArrayList<Employee> employees = RestClient.getInstance().getAllEmployees();

        try {
            for (int i = 0; i < employees.size(); i++) {
                String empno = String.valueOf(employees.get(i).getEmpno());
                String empname = employees.get(i).getEmpname();
                String job = employees.get(i).getJob();
                String mgr = String.valueOf(employees.get(i).getMgr());
                String hiredate = formatDate(employees.get(i).getHiredate());
                String sal = String.valueOf(employees.get(i).getSal());
                String comm = String.valueOf(employees.get(i).getComm());
                String deptno = String.valueOf(employees.get(i).getDeptno());

                table.getTableModel().addRow(empno, empname, job, mgr, hiredate, sal, comm, deptno);
                table.setSelectAction(new Runnable() {
                    @Override
                    public void run() {
                        List<String> data = table.getTableModel().getRow(table.getSelectedRow());
                        for (int i = 0; i < data.size(); i++) {
                            System.out.println(data.get(i));
                        }
                    }
                });
            }
        } catch (NullPointerException npe){}
    }

    private String formatDate(String date) {
        return date.substring(0, 10);
    }

}
