package menu;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class Menu {

    private DefaultTerminalFactory terminalFactory;
    private Screen screen;
    private WindowBasedTextGUI textGUI;
    private Window window;
    private Panel contentPanel;
    private GridLayout gridLayout;

    public Menu() {
        try {
            this.terminalFactory = new DefaultTerminalFactory();
            this.screen = terminalFactory.createScreen();
            this.textGUI = new MultiWindowTextGUI(screen);
            this.window = new BasicWindow("Menu options");
            this.contentPanel = new Panel(new GridLayout(2));
            this.gridLayout = (GridLayout) contentPanel.getLayoutManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launch() {
        try {
            screen.startScreen();
            showMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showMenu() {
        try {
            contentPanel.addComponent(
                    new EmptySpace()
                            .setLayoutData(
                                    GridLayout.createHorizontallyFilledLayoutData(2)));
            contentPanel.addComponent(new Button("Insertar empleado", () -> insertNewEmployee()));
            contentPanel.addComponent(new Button("Borrar empleado", () -> deleteEmployee()));
            contentPanel.addComponent(new Button("Visualizar todos los empleados", () -> displayAllEmployees()));
            contentPanel.addComponent(new Button("Visualizar todos los departamentos", () -> displayAllDepartments()));
            contentPanel.addComponent(new Button("Salir", window::close));

            window.setComponent(contentPanel);
            textGUI.addWindowAndWait(window);

        } finally {
            if (screen != null) {
                try {
                    screen.stopScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void insertNewEmployee() {
        MessageDialog.showMessageDialog(textGUI, "MessageBox", "This is a message box", MessageDialogButton.OK);

    }

    private void deleteEmployee() {}

    private void displayAllEmployees() {}

    private void displayAllDepartments() {}

}

