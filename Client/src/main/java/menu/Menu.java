package menu;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;


/**
 * Creates a text-based GUI menu using lanterna library (https://github.com/mabe02/lanterna)
 *
 * @Author aandradeb
 */
public class Menu {

    private DefaultTerminalFactory terminalFactory; // Auto-detection for figuring out which terminal create
    private Screen terminal; // Save the terminal implementation detected
    private WindowBasedTextGUI textGUI;
    private Window menuOptions;
    private Panel contentPanel;
    private LinearLayout linearLayout;

    public Menu() {
        try {
            this.terminalFactory = new DefaultTerminalFactory()
                    .setTerminalEmulatorTitle("EmpresaPOP")
                    .setInitialTerminalSize(new TerminalSize(100, 42));
            this.terminal = terminalFactory.createScreen();
            this.textGUI = new MultiWindowTextGUI(terminal);
            this.contentPanel = new Panel(new LinearLayout());
            this.linearLayout = (LinearLayout) contentPanel.getLayoutManager();

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

    private void showMenu() {
        this.menuOptions = new BasicWindow("Menu options");
        this.menuOptions.setFixedSize(new TerminalSize(60, 8));

        Button btnAddEmployee = new Button("Insertar empleado", () -> insertNewEmployee());
        btnAddEmployee.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center));

        Button btnDeleteEmployee = new Button("Borrar empleado", () -> deleteEmployee());
        btnDeleteEmployee.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center));

        Button btnSeeAllEmployeers = new Button("Visualizar todos los empleados", () -> displayAllEmployees());
        btnSeeAllEmployeers.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center));

        Button btnSeeAllDepartments = new Button("Visualizar todos los departamentos", () -> displayAllDepartments());
        btnSeeAllDepartments.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center));

        Button btnExit = new Button("Salir", menuOptions::close);
        btnExit.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center));

        contentPanel.addComponent(new EmptySpace());
        contentPanel.addComponent(btnAddEmployee);
        contentPanel.addComponent(btnDeleteEmployee);
        contentPanel.addComponent(btnSeeAllEmployeers);
        contentPanel.addComponent(btnSeeAllDepartments);
        contentPanel.addComponent(new EmptySpace());
        contentPanel.addComponent(btnExit);

        menuOptions.setComponent(contentPanel);
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
        MessageDialog.showMessageDialog(textGUI, "MessageBox", "This is a message box", MessageDialogButton.OK);
    }

    private void deleteEmployee() {}

    private void displayAllEmployees() {}

    private void displayAllDepartments() {}

}

