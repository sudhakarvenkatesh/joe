package com.sos.joe.jobdoc.editor.forms;

import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_AuthorsForm_Apply;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_AuthorsForm_Remove;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_G_AuthorsForm_Authors;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_L_AuthorsForm_EMail;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_L_Name;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_TCl_AuthorsForm_EMail;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_TCl_AuthorsForm_Name;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_T_AuthorsForm_EMail;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_T_AuthorsForm_Name;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_Tbl_AuthorsForm_Authors;

import org.eclipse.swt.SWT;
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

import com.sos.dialog.classes.SOSGroup;
import com.sos.dialog.classes.SOSLabel;
import com.sos.joe.jobdoc.editor.listeners.ReleaseAuthorsListener;
import com.sos.joe.xml.Utils;
import com.sos.joe.xml.jobdoc.DocumentationDom;

public class AuthorsForm extends JobDocBaseForm<ReleaseAuthorsListener> {

    private Group authorsGroup = null;
    private Label label4 = null;
    private Text tName = null;
    private Label label5 = null;
    private Text tEmail = null;
    private Button bApply = null;
    private Table tAuthors = null;
    private Button bRemoveAutho = null;

    public AuthorsForm(Composite parent, int style, DocumentationDom dom, Element parentElement) {
        super(parent, style);
        listener = new ReleaseAuthorsListener(dom, parentElement);
        initialize();
    }

    private void initialize() {
        createGroup();
        setReleaseStatus(false);
        listener.fillAuthors(tAuthors);
    }

    private void createGroup() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 5;
        authorsGroup = JOE_G_AuthorsForm_Authors.Control(new SOSGroup(this, SWT.NONE));
        authorsGroup.setLayout(gridLayout);
        createCreated();
        createModified();
        createComposite();
        createGroup1();
    }

    private void createGroup1() {
        GridData gridData5 = new GridData(GridData.FILL, GridData.FILL, true, true, 4, 2);
        gridData5.widthHint = 486;
        GridData gridData12 = new GridData(GridData.FILL, GridData.BEGINNING, false, false);
        GridLayout gridLayout1 = new GridLayout();
        gridLayout1.numColumns = 5;
        label4 = JOE_L_Name.control(new SOSLabel(authorsGroup, SWT.NONE));
        label4.setLayoutData(new GridData());
        GridData gridData7 = new GridData(GridData.FILL, GridData.CENTER, true, false);
        gridData7.widthHint = 121;
        tName = JOE_T_AuthorsForm_Name.control(new Text(authorsGroup, SWT.BORDER));
        tName.setLayoutData(gridData7);
        tName.addModifyListener(new org.eclipse.swt.events.ModifyListener() {

            @Override
            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
                if (!tName.getText().isEmpty() && !tEmail.getText().isEmpty()) {
                    bApply.setEnabled(true);
                    getShell().setDefaultButton(bApply);
                }
                Utils.setBackground(tName, bApply.isEnabled());
            }
        });
        label5 = JOE_L_AuthorsForm_EMail.control(new SOSLabel(authorsGroup, SWT.NONE));
        label5.setLayoutData(new GridData());
        GridData gridData8 = new GridData(GridData.FILL, GridData.CENTER, true, false);
        gridData8.widthHint = 183;
        tEmail = JOE_T_AuthorsForm_EMail.control(new Text(authorsGroup, SWT.BORDER));
        tEmail.setLayoutData(gridData8);
        tEmail.addModifyListener(new org.eclipse.swt.events.ModifyListener() {

            @Override
            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
                if (!tName.getText().isEmpty() && !tEmail.getText().isEmpty()) {
                    bApply.setEnabled(true);
                    getShell().setDefaultButton(bApply);
                }
                Utils.setBackground(tEmail, bApply.isEnabled());
            }
        });
        GridData gridData9 = new GridData(GridData.FILL, GridData.CENTER, false, false);
        bApply = JOE_B_AuthorsForm_Apply.control(new Button(authorsGroup, SWT.NONE));
        bApply.setLayoutData(gridData9);
        bApply.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                applyAuthor(tName.getText(), tEmail.getText());
                bApply.setEnabled(false);
                tName.setText("");
                tEmail.setText("");
                tAuthors.deselectAll();
                tName.setFocus();
            }
        });
        GridData gridData11 = new GridData(GridData.FILL, GridData.FILL, true, true, 4, 1);
        tAuthors = JOE_Tbl_AuthorsForm_Authors.control(new Table(authorsGroup, SWT.FULL_SELECTION | SWT.BORDER));
        tAuthors.setHeaderVisible(true);
        tAuthors.setLinesVisible(true);
        tAuthors.setLayoutData(gridData11);
        tAuthors.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                if (tAuthors.getSelectionCount() > 0) {
                    TableItem author = tAuthors.getItem(tAuthors.getSelectionIndex());
                    tName.setText(author.getText(0));
                    tEmail.setText(author.getText(1));
                    bRemoveAutho.setEnabled(true);
                    tName.setFocus();
                }
                bApply.setEnabled(false);
            }
        });
        TableColumn tableColumn2 = JOE_TCl_AuthorsForm_Name.control(new TableColumn(tAuthors, SWT.NONE));
        tableColumn2.setWidth(250);
        TableColumn tableColumn11 = JOE_TCl_AuthorsForm_EMail.control(new TableColumn(tAuthors, SWT.NONE));
        tableColumn11.setWidth(60);
        bRemoveAutho = JOE_B_AuthorsForm_Remove.control(new Button(authorsGroup, SWT.NONE));
        bRemoveAutho.setLayoutData(gridData12);
        bRemoveAutho.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                if (tAuthors.getSelectionCount() > 0) {
                    listener.removeAuthor((Element) tAuthors.getSelection()[0].getData());
                    listener.fillAuthors(tAuthors);
                    tName.setText("");
                    tEmail.setText("");
                    bApply.setEnabled(false);
                    bRemoveAutho.setEnabled(false);
                    tAuthors.deselectAll();
                    Utils.setBackground(tAuthors, true);
                }
            }
        });
    }

    private void createComposite() {
        //
    }

    private void createCreated() {
        //
    }

    private void createModified() {
        //
    }

    @Override
    public void apply() {
        applyRelease();
    }

    @Override
    public boolean isUnsaved() {
        return false;
    }

    private void setReleaseStatus(boolean enabled) {
        bApply.setEnabled(false);
        bRemoveAutho.setEnabled(false);
        if (enabled) {
            listener.fillAuthors(tAuthors);
        }
    }

    private void applyAuthor(String name, String email) {
        name = name.trim();
        email = email.trim();
        for (int i = 0; i < tAuthors.getItemCount(); i++) {
            TableItem author = tAuthors.getItem(i);
            if (author.getText(0).equalsIgnoreCase(name)) {
                author.setText(0, name);
                author.setText(1, email);
                Utils.setBackground(tAuthors, true);
                return;
            }
        }
        TableItem author = new TableItem(tAuthors, SWT.NONE);
        author.setText(0, name);
        author.setText(1, email);
        Utils.setBackground(tAuthors, true);
        listener.applyRelease(tAuthors);
    }

    private void applyRelease() {
        tName.setText("");
        tEmail.setText("");
    }

    @Override
    public void openBlank() {
        // TO DO Auto-generated method stub

    }

    @Override
    protected void applySetting() {
        // TO DO Auto-generated method stub

    }

    @Override
    public boolean applyChanges() {
        return false;
    }

}