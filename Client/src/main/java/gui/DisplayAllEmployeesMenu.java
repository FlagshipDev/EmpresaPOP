package gui;

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

    private static Table<String> table;
    private Button exit;

    /**
     * Default constructor
     *
     * @param textGUI Basic gui
     * @param mainWindow Root window
     */
    public DisplayAllEmployeesMenu(WindowBasedTextGUI textGUI, Window mainWindow) {
        this.textGUI = textGUI;
        this.mainWindow = mainWindow;

        this.table = new Table<String>("  Empno  ", "  Empname  ", "  Job  ", "  Mgr  ", "  Hirerdate  ", "  Sal  ", "  Comm  ", "  Deptno  ");
        this.exit = new Button("Atras");
    }

    /**
     * Show the menu display all employees
     */
    public void show() {
        // Hide menuOptions window
        this.mainWindow.setVisible(false);

        // Create new window
        BasicWindow windowAllEmployees = new BasicWindow("Mostrar todos los empleados");
        windowAllEmployees.setHints(Arrays.asList(Window.Hint.CENTERED));
        windowAllEmployees.setFixedSize(new TerminalSize(81, 30));

        // Create contentPanel so store the table and button
        Panel contentPanel = new Panel(new LinearLayout());

        // Expland the table vertically
        table.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Beginning, LinearLayout.GrowPolicy.CanGrow));

        // Add the data saved from the request in the table
        updateDataToTable();

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

    /**
     * Add data from the request to the table
     */
    private static void updateDataToTable() {
        table.getTableModel().clear();
        ArrayList<Employee> employees = RestClient.getInstance().getAllEmployees();
        employees.forEach(employee ->
                table.getTableModel().addRow(String.valueOf(employee.getEmpno()),
                        employee.getEmpname(),
                        employee.getJob(),
                        String.valueOf(employee.getMgr()),
                        formatDate(employee.getHiredate()),
                        String.valueOf(employee.getSal()),
                        String.valueOf(employee.getComm()),
                        String.valueOf(employee.getDeptno())));

    }

    /**
     * Remove the actual data in the table
     */
    private void removeDataTable() {
        table.setTableModel(new TableModel<>("  Empno  ", "  Empname  ", "  Job  ", "  Mgr  ", "  Hirerdate  ", "  Sal  ", "  Comm  ", "  Deptno  "));
    }

    /**
     * Format date
     *
     * @param date Data passed
     * @return New date in good format (yyyy-mm-dd)
     */
    private static String formatDate(String date) {
        return date.substring(0, 10);
    }

}
