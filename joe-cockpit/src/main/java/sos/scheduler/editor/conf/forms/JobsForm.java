package sos.scheduler.editor.conf.forms;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
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
import org.jdom.Element;
import sos.scheduler.editor.app.ContextMenu;
import sos.scheduler.editor.app.MainWindow;
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.conf.listeners.JobsListener;
import com.sos.joe.globals.JOEConstants;
import com.sos.joe.globals.interfaces.ISchedulerUpdate;
import com.sos.joe.globals.messages.ErrorLog;
import com.sos.joe.globals.messages.SOSJOEMessageCodes;
import com.sos.joe.wizard.forms.JobAssistentForm;
import com.sos.joe.xml.jobscheduler.SchedulerDom;

public class JobsForm extends SOSJOEMessageCodes {

    private JobsListener listener = null;
    private Group group = null;
    private static Table table = null;
    private Button bNewJob = null;
    private Button bRemoveJob = null;
    private Label label = null;
    private SchedulerDom dom = null;
    private ISchedulerUpdate update = null;
    private Button butAssistent = null;
    private Button newOrderJob = null;

    public JobsForm(Composite parent, int style, SchedulerDom dom, ISchedulerUpdate update) {
        super(parent, style);
        try {
            this.dom = dom;
            this.update = update;
            listener = new JobsListener(dom, update);
            initialize();
            listener.fillTable(table);
        } catch (Exception e) {
            new ErrorLog(JOE_E_0002.params(sos.util.SOSClassUtil.getMethodName()), e);
        }
    }

    private void initialize() {
        try {
            this.setLayout(new FillLayout());
            createGroup();
            setSize(new org.eclipse.swt.graphics.Point(656, 400));
        } catch (Exception e) {
            new ErrorLog(JOE_E_0002.params(sos.util.SOSClassUtil.getMethodName()), e);
        }
    }

    private void createGroup() {
        try {
            GridLayout gridLayout = new GridLayout();
            gridLayout.numColumns = 2;
            group = JOE_G_JobsForm_Jobs.control(new Group(this, SWT.NONE));
            group.setLayout(gridLayout);
            createTable();
            bNewJob = JOE_B_JobsForm_NewStandaloneJob.control(new Button(group, SWT.NONE));
            bNewJob.setLayoutData(new org.eclipse.swt.layout.GridData(GridData.FILL, GridData.BEGINNING, false, false));
            getShell().setDefaultButton(bNewJob);
            bNewJob.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

                @Override
                public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                    listener.newJob(table, false);
                    bRemoveJob.setEnabled(table.getSelection().length > 0);
                }
            });
            newOrderJob = JOE_B_JobsForm_NewOrderJob.control(new Button(group, SWT.NONE));
            newOrderJob.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(final SelectionEvent e) {
                    listener.newJob(table, true);
                    bRemoveJob.setEnabled(table.getSelection().length > 0);
                }
            });
            newOrderJob.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
            butAssistent = JOE_B_JobsForm_JobWizard.control(new Button(group, SWT.NONE));
            butAssistent.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(final SelectionEvent e) {
                    try {
                        Utils.startCursor(getShell());
                        JobAssistentForm assitent = new JobAssistentForm(dom, update);
                        assitent.startJobAssistant();
                    } catch (Exception ex) {
                        new ErrorLog(JOE_E_0002.params(sos.util.SOSClassUtil.getMethodName()) + " ; " + JOE_M_0040.label(), ex);
                    } finally {
                        Utils.stopCursor(getShell());
                    }
                }
            });
            butAssistent.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
            bRemoveJob = JOE_B_JobsForm_RemoveJob.control(new Button(group, SWT.NONE));
            bRemoveJob.setEnabled(false);
            bRemoveJob.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

                @Override
                public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                    int c = MainWindow.message(getShell(), JOE_M_RemoveJob.label(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
                    if (c != SWT.YES) {
                        return;
                    }
                    if (table.getSelection().length > 0 && Utils.checkElement(table.getSelection()[0].getText(1), dom, JOEConstants.JOBS, null)) {
                        bRemoveJob.setEnabled(listener.deleteJob(table));
                    }
                }
            });
            bRemoveJob.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
            label = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
            label.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, false, false));
        } catch (Exception e) {
            new ErrorLog(JOE_E_0002.params(sos.util.SOSClassUtil.getMethodName()), e);
        }
    }

    private void createTable() {
        try {
            table = JOE_Tbl_JobsForm_Table.control(new Table(group, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK));
            table.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseDoubleClick(final MouseEvent e) {
                    if (table.getSelectionCount() > 0) {
                        ContextMenu.goTo(table.getSelection()[0].getText(1), dom, JOEConstants.JOB);
                    }
                }
            });
            table.setHeaderVisible(true);
            table.setLayoutData(new org.eclipse.swt.layout.GridData(GridData.FILL, GridData.FILL, true, true, 1, 5));
            table.setLinesVisible(true);
            TableColumn tableColumn5 = JOE_TCl_JobsForm_Disabled.control(new TableColumn(table, SWT.NONE));
            tableColumn5.setWidth(60);
            table.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

                @Override
                public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                    if (Utils.isElementEnabled("job", dom, (Element) e.item.getData())) {
                        bRemoveJob.setEnabled(table.getSelection().length > 0);
                    } else {
                        bRemoveJob.setEnabled(false);
                        return;
                    }
                    if (e.detail == SWT.CHECK) {
                        TableItem item = (TableItem) e.item;
                        listener.setJobEnabled((Element) item.getData(), !item.getChecked());
                    }
                }
            });
            TableColumn tableColumn = JOE_TCl_JobsForm_Name.control(new TableColumn(table, SWT.NONE));
            tableColumn.setWidth(100);
            TableColumn tableColumn1 = JOE_TCl_JobsForm_Title.control(new TableColumn(table, SWT.NONE));
            tableColumn1.setWidth(200);
            TableColumn tableColumn2 = JOE_TCl_JobsForm_SchedulerID.control(new TableColumn(table, SWT.NONE));
            tableColumn2.setWidth(100);
            TableColumn tableColumn3 = JOE_TCl_JobsForm_ProcessClass.control(new TableColumn(table, SWT.NONE));
            tableColumn3.setWidth(100);
            TableColumn tableColumn4 = JOE_TCl_JobsForm_Order.control(new TableColumn(table, SWT.NONE));
            tableColumn4.setWidth(40);
        } catch (Exception e) {
            new ErrorLog(JOE_E_0002.params(sos.util.SOSClassUtil.getMethodName()), e);
        }
    }

    public static Table getTable() {
        return table;
    }

}