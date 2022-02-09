package menu;

import api.RestClient;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.gui2.table.TableModel;
import dtos.Employee;

import java.util.ArrayList;
import java.util.Arrays;

public class DisplayAllEmployeesMenu {

    private WindowBasedTextGUI textGUI;
    private Window mainWindow;

    private Table<String> table;
    private Button exit;

    public DisplayAllEmployeesMenu(WindowBasedTextGUI textGUI, Window mainWindow) {
        this.textGUI = textGUI;
        this.mainWindow = mainWindow;

        this.table = new Table<String>("  Empno  ", "  Empname  ", "  Job  ", "  Mgr  ", "  Hirerdate  ", "  Sal  ", "  Comm  ", "  Deptno  ");
        this.exit = new Button("Atras");
    }

    public void show() {
        // Hide menuOptions window
        this.mainWindow.setVisible(false);

        // Create new window
        BasicWindow windowAllEmployees = new BasicWindow("Mostrar todos los empleados");
        windowAllEmployees.setHints(Arrays.asList(Window.Hint.CENTERED));
        windowAllEmployees.setFixedSize(new TerminalSize(80, 30));

        // Create contentPanel so store the table and button
        Panel contentPanel = new Panel(new LinearLayout());

        // Expland the table vertically
        table.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Beginning, LinearLayout.GrowPolicy.CanGrow));

        // Add the data saved from the request in the table
        addDataToTable();

        // Center the button
        exit.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center));

        // Add a function to the button
        exit.addListener(new Button.Listener() {
            @Override
            public void onTriggered(Button button) {
                mainWindow.setVisible(true);
                removeDataTable();
                windowAllEmployees.close();
            }
        });

        // Add the table and button
        contentPanel.addComponent(new EmptySpace());
        contentPanel.addComponent(table);
        contentPanel.addComponent(new EmptySpace());
        contentPanel.addComponent(exit);

        // Add contentPanel to the menuEmployee
        windowAllEmployees.setComponent(contentPanel);

        // Add menuEmployee to the textGUI
        textGUI.addWindowAndWait(windowAllEmployees);
    }

    private void addDataToTable() {
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
            }

        } catch (NullPointerException npe) {
        }
    }

    private void removeDataTable() {
        table.setTableModel(new TableModel<>("  Empno  ", "  Empname  ", "  Job  ", "  Mgr  ", "  Hirerdate  ", "  Sal  ", "  Comm  ", "  Deptno  "));
    }

    private String formatDate(String date) {
        return date.substring(0, 10);
    }

}
