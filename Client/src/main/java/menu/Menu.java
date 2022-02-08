package menu;

import api.RestClient;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;


/**
 * Creates a text-based GUI menu using lanterna library (https://github.com/mabe02/lanterna)
 *
 * @Author aandradeb
 */
public class Menu {

    private DefaultTerminalFactory terminalFactory; // Auto-detection for figuring out which terminal create
    private Screen terminal; // Save the detected terminal implementation
    private WindowBasedTextGUI textGUI;
    private Window menuOptions;

    private RestClient api = new RestClient();

    public Menu() {
        try {
            this.terminalFactory = new DefaultTerminalFactory()
                    .setTerminalEmulatorTitle("EmpresaPOP")
                    .setInitialTerminalSize(new TerminalSize(100, 42));
            this.terminal = terminalFactory.createScreen();
            this.textGUI = new MultiWindowTextGUI(terminal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launch() {
        try {
            terminal.startScreen();
            showMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMenu() throws IOException {
        this.menuOptions = new BasicWindow("Menu opciones");
        this.menuOptions.setHints(Arrays.asList(Window.Hint.CENTERED));
        this.menuOptions.setFixedSize(new TerminalSize(70, 10));

        // Create buttons
        Button btnAddEmployee = new Button("Insertar empleado", () -> insertNewEmployee());
        Button btnDeleteEmployee = new Button("Borrar empleado", () -> deleteEmployee());
        Button btnSeeAllEmployeers = new Button("Visualizar todos los empleados", () -> displayAllEmployees());
        Button btnSeeAllDepartments = new Button("Visualizar todos los departamentos", () -> displayAllDepartments());
        Button btnExit = new Button("Salir", menuOptions::close);

        // Align buttons to center
        btnAddEmployee.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center));
        btnDeleteEmployee.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center));
        btnSeeAllEmployeers.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center));
        btnSeeAllDepartments.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center));
        btnExit.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center));

        // Add new buttons to my contentPanel
        Panel contentPanel = new Panel(new LinearLayout());
        contentPanel.addComponent(new EmptySpace());
        contentPanel.addComponent(new EmptySpace());
        contentPanel.addComponent(btnAddEmployee);
        contentPanel.addComponent(btnDeleteEmployee);
        contentPanel.addComponent(btnSeeAllEmployeers);
        contentPanel.addComponent(btnSeeAllDepartments);
        contentPanel.addComponent(new EmptySpace());
        contentPanel.addComponent(btnExit);

        // Add contentPanel to my window menuOptions
        menuOptions.setComponent(contentPanel);

        // Add menuOptions to my textGUI
        textGUI.addWindowAndWait(this.menuOptions);

        try {
            if (terminal != null) {
                terminal.stopScreen();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertNewEmployee() {
        BasicWindow menuEmployee = new BasicWindow("Insertar empleado");
        menuEmployee.setHints(Arrays.asList(Window.Hint.CENTERED));
        menuEmployee.setFixedSize(new TerminalSize(60, 25));

        // Create labels, textboxes and buttons for the menuEmployee
        Label lblEmpno = new Label("Numero");
        Label lblEname = new Label("Nombre");
        Label lblJob = new Label("Trabajo");
        Label lblMgr = new Label("Mgr");
        Label lblHiredate = new Label("Fecha de contratacion");
        Label lblSal = new Label("Salario");
        Label lblComm = new Label("Comm");
        Label lblDepno = new Label("Nº departamento");

        TextBox tboxEmpno = new TextBox();
        TextBox tboxEname = new TextBox();
        TextBox tboxJob = new TextBox();
        TextBox tboxMgr = new TextBox();
        TextBox tboxHiredate = new TextBox();
        TextBox tboxSal = new TextBox();
        TextBox tboxComm = new TextBox();
        TextBox tboxDepno = new TextBox();

        Button btnClose = new Button("Cerrar", menuEmployee::close);
        Button btnInsert = new Button("Insertar", menuEmployee::close);

        // Fill textboxes in the second column
        tboxEmpno.setLayoutData(GridLayout.createHorizontallyFilledLayoutData());
        tboxEname.setLayoutData(GridLayout.createHorizontallyFilledLayoutData());
        tboxJob.setLayoutData(GridLayout.createHorizontallyFilledLayoutData());
        tboxMgr.setLayoutData(GridLayout.createHorizontallyFilledLayoutData());
        tboxHiredate.setLayoutData(GridLayout.createHorizontallyFilledLayoutData());
        tboxSal.setLayoutData(GridLayout.createHorizontallyFilledLayoutData());
        tboxComm.setLayoutData(GridLayout.createHorizontallyFilledLayoutData());
        tboxDepno.setLayoutData(GridLayout.createHorizontallyFilledLayoutData());

        // Add labels, textboxes and buttons to the contentPanel
        Panel contentPanel = new Panel(new GridLayout(2));
        GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
        gridLayout.setHorizontalSpacing(3);

        Label[] labels = {lblEmpno, lblEname, lblJob, lblMgr, lblHiredate, lblSal, lblComm, lblDepno};
        TextBox[] textBoxes = {tboxEmpno, tboxEname, tboxJob, tboxMgr, tboxHiredate, tboxSal, tboxComm, tboxDepno};

        for (int i = 0; i < labels.length; i++) {
            contentPanel.addComponent(labels[i]);
            contentPanel.addComponent(textBoxes[i]);
        }


        // Empty space and separate line
        /*
        contentPanel.addComponent(
                new EmptySpace()
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(2)));

        contentPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(2)));
        */
        contentPanel.addComponent(btnClose);
        contentPanel.addComponent(btnInsert);

        // Add contentPanel to the menuEmployee
        menuEmployee.setComponent(contentPanel);

        // Add menuEmployee to the textGUI
        textGUI.addWindowAndWait(menuEmployee);
    }

    private void deleteEmployee() {}

    private void displayAllEmployees() {}

    private void displayAllDepartments() {
        System.out.println(api.getDepartments().toString());
    }

}

