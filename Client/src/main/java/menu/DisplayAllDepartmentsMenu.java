package menu;

import api.RestClient;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.gui2.table.TableModel;
import dtos.Department;
import dtos.Employee;

import java.util.ArrayList;
import java.util.Arrays;

public class DisplayAllDepartmentsMenu {

    private WindowBasedTextGUI textGUI;
    private Window mainWindow;

    private Table<String> table;
    private Button exit;

    public DisplayAllDepartmentsMenu(WindowBasedTextGUI textGUI, Window mainWindow) {
        this.textGUI = textGUI;
        this.mainWindow = mainWindow;

        this.table = new Table<String>("  Deptno  ", "  Dname  ", "  Loc  ");
        this.exit = new Button("Atras");
    }

    public void show() {
        // Hide menuOptions window
        this.mainWindow.setVisible(false);

        // Create new window
        BasicWindow windowAllDepartments = new BasicWindow("Mostrar todos los departamentos");
        windowAllDepartments.setHints(Arrays.asList(Window.Hint.CENTERED));
        windowAllDepartments.setFixedSize(new TerminalSize(80, 30));

        // Create contentPanel so store the table and button
        Panel contentPanel = new Panel(new LinearLayout());

        // Expland the table vertically
        table.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center, LinearLayout.GrowPolicy.CanGrow));

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
                windowAllDepartments.close();
            }
        });

        // Add the table and button
        contentPanel.addComponent(new EmptySpace());
        contentPanel.addComponent(table);
        contentPanel.addComponent(new EmptySpace());
        contentPanel.addComponent(exit);

        // Add contentPanel to the menuEmployee
        windowAllDepartments.setComponent(contentPanel);

        // Add menuEmployee to the textGUI
        textGUI.addWindowAndWait(windowAllDepartments);
    }

    private void addDataToTable() {
        ArrayList<Department> departments = RestClient.getInstance().getAllDepartments();

        try {
            for (int i = 0; i < departments.size(); i++) {
                String deptno = String.valueOf(departments.get(i).getDeptno());
                String dname = departments.get(i).getDname();
                String loc = departments.get(i).getLoc();

                table.getTableModel().addRow(deptno, dname, loc);
            }
        } catch (NullPointerException npe) {
        }
    }

    private void removeDataTable() {
        table.setTableModel(new TableModel<>("  Deptno  ", "  Dname  ", "  Loc  "));
    }

    private String formatDate(String date) {
        return date.substring(0, 10);
    }

}
