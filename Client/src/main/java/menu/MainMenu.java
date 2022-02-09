package menu;

import api.RestClient;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.Arrays;


/**
 * Creates a text-based GUI menu using lanterna library (https://github.com/mabe02/lanterna)
 *
 * @Author aandradeb
 */
public class MainMenu {

    private DefaultTerminalFactory terminalFactory; // Auto-detection for figuring out which terminal create
    private Screen terminal; // Save the detected terminal implementation
    private WindowBasedTextGUI textGUI;
    private Window menuOptions;

    private RestClient api = new RestClient();

    public MainMenu() {
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
            show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void show() throws IOException {
        this.menuOptions = new BasicWindow("Menu opciones");
        this.menuOptions.setHints(Arrays.asList(Window.Hint.CENTERED));
        this.menuOptions.setFixedSize(new TerminalSize(70, 10));

        // Create buttons
        Button btnAddEmployee = new Button("Insertar empleado");
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

        btnAddEmployee.addListener(new Button.Listener() {
            @Override
            public void onTriggered(Button button) {
                NewEmployeeMenu newEmployeeMenu = new NewEmployeeMenu(textGUI, menuOptions, api);
                newEmployeeMenu.show();
            }
        });

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


    private void deleteEmployee() {
    }

    private void displayAllEmployees() {
    }

    private void displayAllDepartments() {
    }


}

