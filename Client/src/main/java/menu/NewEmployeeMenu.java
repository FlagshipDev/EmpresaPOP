package menu;

import api.RestClient;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import dtos.Employee;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Arrays;

public class NewEmployeeMenu {

    private WindowBasedTextGUI textGUI;
    private Window mainWindow;

    // Create labels, textboxes and buttons for the menuEmployee
    private Label lblEmpno;
    private Label lblEmpname;
    private Label lblJob;
    private Label lblMgr;
    private Label lblHiredate;
    private Label lblSal;
    private Label lblComm;
    private Label lblDepno;

    private TextBox tboxEmpno;
    private TextBox tboxEmpname;
    private TextBox tboxJob;
    private TextBox tboxMgr;
    private TextBox tboxHiredate;
    private TextBox tboxSal;
    private TextBox tboxComm;
    private TextBox tboxDepno;

    private Button btnClose;
    private Button btnInsert;

    private Label[] labels;
    private TextBox[] textBoxes;

    public NewEmployeeMenu(WindowBasedTextGUI textGUI, Window menuOptions, RestClient api) {
        this.textGUI = textGUI;
        this.mainWindow = menuOptions;

        this.lblEmpno = new Label("Numero");
        this.lblEmpname = new Label("Nombre");
        this.lblJob = new Label("Trabajo");
        this.lblMgr = new Label("Mgr");
        this.lblHiredate = new Label("Fecha de contratacion\n(dd/mm/yyyy)");
        this.lblSal = new Label("Salario");
        this.lblComm = new Label("Comm");
        this.lblDepno = new Label("Nº departamento");

        this.tboxEmpno = new TextBox();
        this.tboxEmpname = new TextBox();
        this.tboxJob = new TextBox();
        this.tboxMgr = new TextBox();
        this.tboxHiredate = new TextBox();
        this.tboxSal = new TextBox();
        this.tboxComm = new TextBox();
        this.tboxDepno = new TextBox();

        this.btnClose = new Button("Cerrar");
        this.btnInsert = new Button("Insertar");

        this.labels = new Label[]{lblEmpno, lblEmpname, lblJob, lblMgr, lblHiredate, lblSal, lblComm, lblDepno};
        this.textBoxes = new TextBox[]{tboxEmpno, tboxEmpname, tboxJob, tboxMgr, tboxHiredate, tboxSal, tboxComm, tboxDepno};
    }

    public void show() {
        // Hide menuOptions window
        this.mainWindow.setVisible(false);

        // Create new window
        BasicWindow menuEmployees = new BasicWindow("Insertar empleado");
        menuEmployees.setHints(Arrays.asList(Window.Hint.CENTERED));
        menuEmployees.setFixedSize(new TerminalSize(60, 25));

        // Create contentPanel
        Panel contentPanel = new Panel(new GridLayout(3));
        GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
        gridLayout.setVerticalSpacing(1);

        // Expand texboxes 2 columns of 3
        for (int i = 0; i < this.textBoxes.length; i++) {
            this.textBoxes[i].setLayoutData(gridLayout.createLayoutData(
                    GridLayout.Alignment.FILL,
                    GridLayout.Alignment.BEGINNING,
                    true,
                    false,
                    2,
                    1));
        }

        // Add a listener to every button to do an action
        btnClose.addListener(new Button.Listener() {
            @Override
            public void onTriggered(Button button) {
                mainWindow.setVisible(true);
                menuEmployees.close();
            }
        });

        btnInsert.addListener(new Button.Listener() {
            @Override
            public void onTriggered(Button button) {
                switch (validateTextBoxes()) {
                    case "Empty textboxes":
                        MessageDialog.showMessageDialog(textGUI, "Error", "No puedes insertar campos vacios!", MessageDialogButton.OK);
                        break;
                    case "Negative numbers":
                        MessageDialog.showMessageDialog(textGUI, "Error", "Los numeros negativos no estan permitidos", MessageDialogButton.OK);
                        break;
                    case "Numbers with letters":
                        MessageDialog.showMessageDialog(textGUI, "Error", "Algunos campos deber ser solo numeros!", MessageDialogButton.OK);
                        break;
                    case "Bad date format":
                        MessageDialog.showMessageDialog(textGUI, "Error", "Error de formato de fecha", MessageDialogButton.OK);
                        break;
                    case "OK":
                        Integer empno = Integer.parseInt(tboxEmpno.getText());
                        String empname = tboxEmpname.getText();
                        String job = tboxJob.getText();
                        Integer mgr = Integer.parseInt(tboxMgr.getText());
                        String hiredate = tboxHiredate.getText();
                        Integer sal = Integer.parseInt(tboxSal.getText());
                        Integer comm = Integer.parseInt(tboxComm.getText());
                        Integer deptno = Integer.parseInt(tboxDepno.getText());

                        JSONObject jsonEmployee = new JSONObject();
                        jsonEmployee.put("empno", empno);
                        jsonEmployee.put("empname", empname);
                        jsonEmployee.put("job", job);
                        jsonEmployee.put("mgr", mgr);
                        jsonEmployee.put("hiredate", hiredate);
                        jsonEmployee.put("sal", sal);
                        jsonEmployee.put("comm", comm);
                        jsonEmployee.put("deptno", deptno);

                        Employee newEmployee = new Employee(jsonEmployee);
                        RestClient.getInstance().createNewEmployee(newEmployee);
                        break;
                }
            }
        });

        // Add labels, textboxes, blanklines and buttons to the contentPanel
        contentPanel.addComponent(new EmptySpace().setLayoutData(
                GridLayout.createHorizontallyFilledLayoutData(3)));

        for (int i = 0; i < this.labels.length; i++) {
            contentPanel.addComponent(this.labels[i]);
            contentPanel.addComponent(this.textBoxes[i]);
        }

        contentPanel.addComponent(new EmptySpace().setLayoutData(
                GridLayout.createHorizontallyFilledLayoutData(3)));

        contentPanel.addComponent(new Separator(Direction.HORIZONTAL).setLayoutData(
                GridLayout.createHorizontallyFilledLayoutData(3)));

        contentPanel.addComponent(btnClose);
        contentPanel.addComponent(btnInsert);

        // Add contentPanel to the menuEmployee
        menuEmployees.setComponent(contentPanel);

        // Add menuEmployee to the textGUI
        textGUI.addWindowAndWait(menuEmployees);
    }

    private String validateTextBoxes() {
        String message = "OK";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (emptyTextBoxes()) {
            message = "Empty textboxes";
        } else {
            try {
                if (Integer.parseInt(this.tboxEmpno.getText()) < 1 || Integer.parseInt(this.tboxMgr.getText()) < 1 || Integer.parseInt(this.tboxSal.getText()) < 1
                        || Integer.parseInt(this.tboxComm.getText()) < 1 || Integer.parseInt(this.tboxDepno.getText()) < 1) {
                    message = "Negative numbers";
                }

                sdf.setLenient(false);
                sdf.parse(this.tboxHiredate.getText());
            } catch (NumberFormatException nfe) {
                message = "Numbers with letters";
            } catch (Exception e) {
                message = "Bad date format";
            }
        }
        return message;
    }

    private boolean emptyTextBoxes() {
        for (int i = 0; i < this.textBoxes.length; i++) {
            if (this.textBoxes[i].getText().equals("")) {
                return true;
            }
        }
        return false;
    }


}
