package gui;

import api.RestClient;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.gui2.table.TableModel;
import dtos.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteEmployeeMenu {

    private WindowBasedTextGUI textGUI;
    private Window mainWindow;

    private Table<String> table;
    private Button exit;

    /**
     * Default constructor
     *
     * @param textGUI Basic gui
     * @param mainWindow Root window
     */
    public DeleteEmployeeMenu(WindowBasedTextGUI textGUI, Window mainWindow) {
        this.textGUI = textGUI;
        this.mainWindow = mainWindow;

        this.table = new Table<String>("  Empno  ", "  Empname  ", "  Job  ", "  Mgr  ", "  Hirerdate  ", "  Sal  ", "  Comm  ", "  Deptno  ");
        this.exit = new Button("Atras");
    }

    /**
     * Show the menu delete employee
     */
    public void show() {
        // Hide menuOptions window
        this.mainWindow.setVisible(false);

        // Create new window
        BasicWindow windowAllEmployees = new BasicWindow("Borrar un empleado");
        windowAllEmployees.setHints(Arrays.asList(Window.Hint.CENTERED));
        windowAllEmployees.setFixedSize(new TerminalSize(81, 30));

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

        // When pressed one row in the table, you can delete a user
        table.setSelectAction(new Runnable() {
            @Override
            public void run() {
                List<String> data = table.getTableModel().getRow(table.getSelectedRow());
                int employeeId = Integer.parseInt(data.get(0));

                BasicWindow bw = new BasicWindow("Confirmacion");
                bw.setHints(Arrays.asList(Window.Hint.CENTERED));
                bw.setFixedSize(new TerminalSize(35, 10));

                Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));
                Panel panel2 = new Panel(new LinearLayout(Direction.HORIZONTAL));
                Panel panel3 = new Panel(new LinearLayout(Direction.HORIZONTAL));

                panel2.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center, LinearLayout.GrowPolicy.CanGrow));
                panel3.setPreferredSize(new TerminalSize(35, 2));

                Label question = new Label("\nSeguro/a que quieres borrar\nese empleado?");
                Button btnCancel = new Button("Cancelar", () -> bw.close());
                Button btnAccept = new Button("Aceptar");

                btnAccept.addListener(new Button.Listener() {
                    @Override
                    public void onTriggered(Button button) {
                        bw.close();
                        RestClient.getInstance().deleteEmployeeById(employeeId);
                        MessageDialog.showMessageDialog(textGUI, "Confirmacion", "Usuario borrado con exito!", MessageDialogButton.OK);
                        removeDataTable();
                        addDataToTable();
                    }
                });

                panel2.addComponent(question);

                for (int i = 0; i < 3; i++) { panel3.addComponent(new EmptySpace()); }
                panel3.addComponent(btnCancel);
                panel3.addComponent(btnCancel);
                panel3.addComponent(new EmptySpace());
                panel3.addComponent(btnAccept);

                panel.addComponent(panel2);
                panel.addComponent(panel3);

                bw.setComponent(panel);
                textGUI.addWindowAndWait(bw);
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
    private String formatDate(String date) {
        return date.substring(0, 10);
    }
}
