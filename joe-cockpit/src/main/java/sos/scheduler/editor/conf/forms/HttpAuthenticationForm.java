package sos.scheduler.editor.conf.forms;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.jdom.Element;

import sos.scheduler.editor.app.MainWindow;
import sos.scheduler.editor.conf.listeners.HttpAuthenticationListener;
import sos.util.SOSCrypt;

import com.sos.joe.globals.interfaces.IUnsaved;
import com.sos.joe.globals.messages.ErrorLog;
import com.sos.joe.globals.messages.SOSJOEMessageCodes;
import com.sos.joe.xml.jobscheduler.SchedulerDom;

public class HttpAuthenticationForm extends SOSJOEMessageCodes implements IUnsaved {

    private HttpAuthenticationListener listener;
    private Group httpAuthenticationGroup = null;
    private Text txtUsername = null;
    private Text txtPassword = null;
    private Table tableHttpUsers = null;
    private Button butApplyHttpUser = null;
    private Button butRemoveHttpUser = null;
    private Button butEncrypt = null;
    private Text txtMD5Password = null;

    public HttpAuthenticationForm(Composite parent, int style, SchedulerDom dom, Element config) {
        super(parent, style);
        listener = new HttpAuthenticationListener(dom, config);
        initialize();
        listener.fillHttpAuthenticationTable(tableHttpUsers);
    }

    private void initialize() {
        this.setLayout(new FillLayout());
        createGroup();
        setSize(new org.eclipse.swt.graphics.Point(653, 468));
        txtUsername.setFocus();
    }

    private void createGroup() {
        GridLayout gridLayout = new GridLayout();
        httpAuthenticationGroup = JOE_G_HttpAuthenticationForm_AuthGroup.control(new Group(this, SWT.NONE));
        createGroup1();
        httpAuthenticationGroup.setLayout(gridLayout);
        new Label(httpAuthenticationGroup, SWT.NONE);
    }

    private void createGroup1() {
        final Group group_1 = JOE_G_HttpAuthenticationForm_Group.control(new Group(httpAuthenticationGroup, SWT.NONE));
        final GridData gridData_2 = new GridData(GridData.FILL, GridData.FILL, true, true, 1, 2);
        gridData_2.heightHint = 427;
        gridData_2.widthHint = 525;
        group_1.setLayoutData(gridData_2);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 5;
        group_1.setLayout(gridLayout);
        final Label lblUsername = JOE_L_HttpAuthenticationForm_UserName.control(new Label(group_1, SWT.NONE));
        txtUsername = JOE_T_HttpAuthenticationForm_UserName.control(new Text(group_1, SWT.BORDER));
        txtUsername.addFocusListener(new FocusAdapter() {

            public void focusGained(final FocusEvent e) {
                txtUsername.setFocus();
            }
        });
        txtUsername.addKeyListener(new KeyAdapter() {

            public void keyPressed(final KeyEvent e) {
                if (e.keyCode == SWT.CR && !"".equals(txtUsername.getText())) {
                    applyHttpUser();
                }
            }
        });
        txtUsername.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                //
            }
        });
        txtUsername.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        final Label lblPassword = JOE_L_HttpAuthenticationForm_Password.control(new Label(group_1, SWT.NONE));
        txtPassword = JOE_T_HttpAuthenticationForm_Password.control(new Text(group_1, SWT.BORDER));
        txtPassword.addKeyListener(new KeyAdapter() {

            public void keyPressed(final KeyEvent e) {
                if (e.keyCode == SWT.CR && !"".equals(txtPassword.getText())) {
                    encrypt();
                }
            }
        });
        txtPassword.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                butEncrypt.setEnabled(!"".equals(txtPassword.getText()));
            }
        });
        txtPassword.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        butEncrypt = JOE_B_HttpAuthenticationForm_Encrypt.control(new Button(group_1, SWT.NONE));
        butEncrypt.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(final SelectionEvent e) {
                if (txtPassword.getText() != null && !txtPassword.getText().isEmpty()) {
                    encrypt();
                }
            }
        });
        butEncrypt.setEnabled(false);
        butEncrypt.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
        new Label(group_1, SWT.NONE);
        new Label(group_1, SWT.NONE);
        final Label md5PasswordLabel = JOE_L_HttpAuthenticationForm_MD5PW.control(new Label(group_1, SWT.NONE));
        txtMD5Password = JOE_T_HttpAuthenticationForm_MD5PW.control(new Text(group_1, SWT.BORDER));
        txtMD5Password.addKeyListener(new KeyAdapter() {

            public void keyPressed(final KeyEvent e) {
                if (e.keyCode == SWT.CR && !"".equals(txtUsername.getText())) {
                    applyHttpUser();
                }
            }
        });
        txtMD5Password.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                butApplyHttpUser.setEnabled(!"".equals(txtMD5Password.getText()));
            }
        });
        txtMD5Password.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        butApplyHttpUser = JOE_B_HttpAuthenticationForm_Apply.control(new Button(group_1, SWT.NONE));
        butApplyHttpUser.setEnabled(false);
        butApplyHttpUser.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(final SelectionEvent e) {
                applyHttpUser();
            }
        });
        butApplyHttpUser.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
        tableHttpUsers = JOE_Tbl_HttpAuthenticationForm_Users.control(new Table(group_1, SWT.FULL_SELECTION | SWT.BORDER));
        tableHttpUsers.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(final SelectionEvent e) {
                if (tableHttpUsers.getSelectionCount() > 0) {
                    TableItem item = tableHttpUsers.getItem(tableHttpUsers.getSelectionIndex());
                    txtUsername.setText(item.getText(0));
                    txtPassword.setText("");
                    txtMD5Password.setText(item.getText(1));
                    butApplyHttpUser.setEnabled(false);
                    butEncrypt.setEnabled(false);
                    txtUsername.setFocus();
                }
                butRemoveHttpUser.setEnabled(tableHttpUsers.getSelectionCount() > 0);
            }
        });
        tableHttpUsers.setLinesVisible(true);
        tableHttpUsers.setHeaderVisible(true);
        final GridData gridData_1 = new GridData(GridData.FILL, GridData.FILL, true, true, 4, 1);
        gridData_1.minimumHeight = 100;
        gridData_1.horizontalIndent = 4;
        tableHttpUsers.setLayoutData(gridData_1);
        final TableColumn urlPathTableColumn = JOE_TCl_HttpAuthenticationForm_NameColumn.control(new TableColumn(tableHttpUsers, SWT.NONE));
        urlPathTableColumn.setWidth(150);
        final TableColumn pathTableColumn = JOE_TCl_HttpAuthenticationForm_PWColumn.control(new TableColumn(tableHttpUsers, SWT.NONE));
        pathTableColumn.setWidth(250);
        butRemoveHttpUser = JOE_B_HttpAuthenticationForm_Remove.control(new Button(group_1, SWT.NONE));
        butRemoveHttpUser.setEnabled(false);
        butRemoveHttpUser.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, false, false));
        butRemoveHttpUser.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(final SelectionEvent e) {
                if (tableHttpUsers.getSelectionCount() > 0) {
                    tableHttpUsers.remove(tableHttpUsers.getSelectionIndex());
                    tableHttpUsers.deselectAll();
                    txtUsername.setText("");
                    txtPassword.setText("");
                    txtMD5Password.setText("");
                    butApplyHttpUser.setEnabled(false);
                    butEncrypt.setEnabled(false);
                    listener.applyHttpUser(tableHttpUsers.getItems());
                }
                butRemoveHttpUser.setEnabled(false);
            }
        });
    }

    private void applyHttpUser() {
        String passw = txtMD5Password.getText();
        String name = txtUsername.getText();
        TableItem[] items = tableHttpUsers.getItems();
        boolean found = false;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getText(0).equals(name)) {
                items[i].setText(1, passw);
                found = true;
            }
        }
        if (!found) {
            TableItem item = new TableItem(tableHttpUsers, SWT.NONE);
            item.setText(new String[] { name, passw });
        }
        tableHttpUsers.deselectAll();
        txtPassword.setText("");
        txtMD5Password.setText("");
        txtUsername.setText("");
        butRemoveHttpUser.setEnabled(false);
        butApplyHttpUser.setEnabled(false);
        butEncrypt.setEnabled(false);
        txtPassword.setFocus();
        listener.applyHttpUser(tableHttpUsers.getItems());
    }

    public boolean isUnsaved() {
        return false;
    }

    public void apply() {
        applyHttpUser();
    }

    private void encrypt() {
        try {
            String _encrypt = SOSCrypt.md5encrypt(txtPassword.getText());
            txtMD5Password.setText(_encrypt.toUpperCase());
        } catch (Exception ex) {
            new ErrorLog(JOE_E_0002.params(sos.util.SOSClassUtil.getMethodName()) + JOE_M_0016.params(txtPassword.getText()), ex);
            MainWindow.message(getShell(), JOE_M_0016.params(txtPassword.getText()), SWT.ICON_WARNING | SWT.OK);
        }
    }

}