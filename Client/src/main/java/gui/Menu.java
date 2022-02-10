package gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.Arrays;


/**
 * Creates a text-based GUI menu using lanterna library (https://github.com/mabe02/lanterna)
 *
 * @author aandradeb
 */
public class Menu {

    private DefaultTerminalFactory terminalFactory;
    private Screen terminal;
    private WindowBasedTextGUI textGUI;
    private Window mainWindow;

    // Menus
    private InsertEmployeeMenu insertEmployeeMenu;
    private DeleteEmployeeMenu deleteEmployeeMenu;
    private DisplayAllDepartmentsMenu displayAllDepartmentsMenu;
    private DisplayAllEmployeesMenu displayAllEmployeesMenu;

    /**
     * Default constructor
     */
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

    /**
     * Show the main menu
     */
    public void show() {
        try {
            terminal.startScreen();
            // Create main window
            this.mainWindow = new BasicWindow("Menu opciones");
            this.mainWindow.setHints(Arrays.asList(Window.Hint.CENTERED));
            this.mainWindow.setFixedSize(new TerminalSize(70, 10));

            // Instance the submenus
            this.insertEmployeeMenu = new InsertEmployeeMenu(textGUI, mainWindow);
            this.deleteEmployeeMenu = new DeleteEmployeeMenu(textGUI, mainWindow);
            this.displayAllDepartmentsMenu = new DisplayAllDepartmentsMenu(textGUI, mainWindow);
            this.displayAllEmployeesMenu = new DisplayAllEmployeesMenu(textGUI, mainWindow);

            // Create buttons
            Button btnAddEmployee = new Button("Insertar empleado", () -> insertEmployeeMenu.show());
            Button btnDeleteEmployee = new Button("Borrar empleado", () -> deleteEmployeeMenu.show());
            Button btnSeeAllEmployeers = new Button("Visualizar todos los empleados", () -> displayAllEmployeesMenu.show());
            Button btnSeeAllDepartments = new Button("Visualizar todos los departamentos", () -> displayAllDepartmentsMenu.show());
            Button btnExit = new Button("Salir", mainWindow::close);

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
            mainWindow.setComponent(contentPanel);

            // Add menuOptions to my textGUI
            textGUI.addWindowAndWait(this.mainWindow);

            if (terminal != null) {
                terminal.stopScreen();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

