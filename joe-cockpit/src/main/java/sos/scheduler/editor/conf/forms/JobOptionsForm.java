package sos.scheduler.editor.conf.forms;

import java.text.Collator;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
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
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.conf.listeners.JobOptionsListener;

import com.sos.joe.globals.interfaces.IUnsaved;
import com.sos.joe.globals.messages.SOSJOEMessageCodes;
import com.sos.joe.xml.jobscheduler.SchedulerDom;

public class JobOptionsForm extends SOSJOEMessageCodes implements IUnsaved {

    private JobOptionsListener listener = null;
    private Group group = null;
    private Group group1 = null;
    private Group group2 = null;
    private Group group3 = null;
    @SuppressWarnings("unused")
    private Label label = null;
    private Text tDirectory = null;
    private Text tRegex = null;
    @SuppressWarnings("unused")
    private Label label2 = null;
    private Text sSetBackCount = null;
    private Button bIsMaximum = null;
    private Text sSetBackHours = null;
    private Text sSetBackMinutes = null;
    private Text sSetBackSeconds = null;
    @SuppressWarnings("unused")
    private Label label7 = null;
    @SuppressWarnings("unused")
    private Label label9 = null;
    private Table tErrorDelay = null;
    private Button bNewDelay = null;
    @SuppressWarnings("unused")
    private Label label4 = null;
    private Text sErrorCount = null;
    private Text sErrorHours = null;
    @SuppressWarnings("unused")
    private Label label14 = null;
    private Text sErrorMinutes = null;
    @SuppressWarnings("unused")
    private Label label17 = null;
    private Text sErrorSeconds = null;
    private Button bRemoveDelay = null;
    private Button bApply = null;
    private Composite composite = null;
    private Button bStop = null;
    private Button bDelay = null;
    @SuppressWarnings("unused")
    private Label label8 = null;
    private Label label5 = null;
    private Label label6 = null;
    private Button bApplySetback = null;
    private Table tSetback = null;
    private Button bNewSetback = null;
    private Button bRemoveSetback = null;
    private Label label30 = null;
    private Label label31 = null;
    @SuppressWarnings("unused")
    private Label label10 = null;
    @SuppressWarnings("unused")
    private Label label11 = null;
    private Button bApplyDirectory = null;
    private Label label1 = null;
    private Table tDirectories = null;
    private Button bNewDirectory = null;
    private Label label21 = null;
    private Button bRemoveDirectory = null;

    public JobOptionsForm(Composite parent, int style, SchedulerDom dom, Element job) {
        super(parent, style);
        listener = new JobOptionsListener(dom, job);
        initialize();
        initDirectories(listener.isDirectoryTrigger());
        initSetbacks(listener.isSetbackDelay());
        initErrorDelays(listener.isErrorDelay());
        group.setEnabled(Utils.isElementEnabled("job", dom, job));
    }

    public void apply() {
        if (isUnsaved()) {
            applyDelay();
        }
    }

    public boolean isUnsaved() {
        return bApply.isEnabled();
    }

    private void initialize() {
        this.setLayout(new FillLayout());
        createGroup();
        setSize(new org.eclipse.swt.graphics.Point(678, 425));
    }

    private void createGroup() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        group = JOE_G_JobOptionsForm_RunOptions.control(new Group(this, SWT.NONE));
        createGroup1();
        group.setLayout(gridLayout);
        createGroup3();
        createGroup2();
    }

    private void createGroup1() {
        GridData gridData51 = new org.eclipse.swt.layout.GridData();
        gridData51.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData51.widthHint = 90;
        gridData51.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        GridData gridData41 = new org.eclipse.swt.layout.GridData();
        gridData41.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData41.widthHint = -1;
        gridData41.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        GridData gridData31 = new org.eclipse.swt.layout.GridData();
        gridData31.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData31.verticalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
        GridData gridData210 = new org.eclipse.swt.layout.GridData();
        gridData210.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData210.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        GridData gridData110 = new org.eclipse.swt.layout.GridData();
        gridData110.horizontalSpan = 5;
        gridData110.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData110.heightHint = 10;
        gridData110.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        GridData gridData4 = new org.eclipse.swt.layout.GridData();
        gridData4.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData4.grabExcessHorizontalSpace = true;
        gridData4.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        GridData gridData3 = new org.eclipse.swt.layout.GridData();
        gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData3.grabExcessHorizontalSpace = true;
        gridData3.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        GridLayout gridLayout1 = new GridLayout();
        gridLayout1.numColumns = 5;
        GridData gridData = new org.eclipse.swt.layout.GridData();
        gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
        group1 = JOE_G_JobOptionsForm_StartWhenDirectoryChanged.control(new Group(group, SWT.NONE));
        group1.setLayout(gridLayout1);
        group1.setLayoutData(gridData);
        label = JOE_L_JobOptionsForm_WatchDirectory.control(new Label(group1, SWT.NONE));
        tDirectory = JOE_T_JobOptionsForm_WatchDirectory.control(new Text(group1, SWT.BORDER));
        tDirectory.setLayoutData(gridData3);
        label11 = JOE_L_JobOptionsForm_FileRegex.control(new Label(group1, SWT.NONE));
        tRegex = JOE_T_JobOptionsForm_FileRegex.control(new Text(group1, SWT.BORDER));
        tRegex.setLayoutData(gridData4);
        bApplyDirectory = JOE_B_JobOptionsForm_ApplyDir.control(new Button(group1, SWT.NONE));
        bApplyDirectory.setEnabled(false);
        bApplyDirectory.setLayoutData(gridData51);
        bApplyDirectory.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                applyDirectory();
            }
        });
        label1 = new Label(group1, SWT.SEPARATOR | SWT.HORIZONTAL);
        label1.setLayoutData(gridData110);
        createTable3();
        bNewDirectory = JOE_B_JobOptionsForm_NewDir.control(new Button(group1, SWT.NONE));
        bNewDirectory.setLayoutData(gridData41);
        bNewDirectory.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                tDirectories.deselectAll();
                listener.newDirectory();
                initDirectory(true);
                tDirectory.setFocus();
            }
        });
        label21 = new Label(group1, SWT.SEPARATOR | SWT.HORIZONTAL);
        label21.setLayoutData(gridData210);
        bRemoveDirectory = JOE_B_JobOptionsForm_RemoveDir.control(new Button(group1, SWT.NONE));
        bRemoveDirectory.setEnabled(false);
        bRemoveDirectory.setLayoutData(gridData31);
        bRemoveDirectory.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                if (tDirectories.getSelectionCount() > 0) {
                    int index = tDirectories.getSelectionIndex();
                    listener.deleteDirectory(index);
                    tDirectories.remove(index);
                    if (index >= tDirectories.getItemCount()) {
                        index--;
                    }
                    if (tDirectories.getItemCount() > 0) {
                        tDirectories.setSelection(index);
                        listener.selectDirectory(index);
                        initDirectory(true);
                    } else {
                        initDirectory(false);
                        bRemoveDirectory.setEnabled(false);
                    }
                }
            }
        });
        tDirectory.addModifyListener(new org.eclipse.swt.events.ModifyListener() {

            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
                if (!"".equals(tDirectory.getText())) {
                    getShell().setDefaultButton(bApplyDirectory);
                }
                bApplyDirectory.setEnabled(!"".equals(tDirectory.getText()));
            }
        });
        tRegex.addModifyListener(new org.eclipse.swt.events.ModifyListener() {

            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
                if (!"".equals(tDirectory.getText())) {
                    getShell().setDefaultButton(bApplyDirectory);
                }
                bApplyDirectory.setEnabled(!"".equals(tDirectory.getText()));
            }
        });
    }

    private void createGroup2() {
        GridData gridData23 = new org.eclipse.swt.layout.GridData();
        gridData23.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData23.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        GridData gridData22 = new org.eclipse.swt.layout.GridData(GridData.FILL, GridData.CENTER, false, false, 12, 1);
        gridData22.heightHint = 10;
        GridData gridData21 = new org.eclipse.swt.layout.GridData();
        gridData21.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData21.widthHint = 90;
        gridData21.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        GridData gridData18 = new org.eclipse.swt.layout.GridData();
        gridData18.widthHint = 60;
        GridData gridData17 = new org.eclipse.swt.layout.GridData();
        gridData17.widthHint = 25;
        GridData gridData16 = new org.eclipse.swt.layout.GridData();
        gridData16.widthHint = 25;
        GridData gridData15 = new org.eclipse.swt.layout.GridData();
        gridData15.widthHint = 25;
        GridData gridData13 = new org.eclipse.swt.layout.GridData();
        gridData13.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData13.verticalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
        GridData gridData12 = new org.eclipse.swt.layout.GridData();
        gridData12.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData12.verticalSpan = 1;
        gridData12.verticalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
        GridLayout gridLayout3 = new GridLayout();
        gridLayout3.numColumns = 12;
        GridData gridData1 = new org.eclipse.swt.layout.GridData();
        gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData1.grabExcessHorizontalSpace = true;
        gridData1.grabExcessVerticalSpace = true;
        gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
        group2 = JOE_G_JobOptionsForm_DelayAfterError.control(new Group(group, SWT.NONE));
        group2.setLayoutData(gridData1);
        group2.setLayout(gridLayout3);
        label4 = JOE_L_JobOptionsForm_ErrorCount.control(new Label(group2, SWT.NONE));
        sErrorCount = JOE_T_JobOptionsForm_ErrorCount.control(new Text(group2, SWT.BORDER));
        sErrorCount.addVerifyListener(new VerifyListener() {

            public void verifyText(final VerifyEvent e) {
                e.doit = Utils.isOnlyDigits(e.text);
            }
        });
        composite = new Composite(group2, SWT.NONE);
        composite.setLayout(new RowLayout(SWT.HORIZONTAL));
        composite.setLayoutData(new org.eclipse.swt.layout.GridData(GridData.END, GridData.CENTER, true, false));
        bStop = JOE_B_JobOptionsForm_Stop.control(new Button(group2, SWT.RADIO));
        bStop.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                getShell().setDefaultButton(bApply);
                bApply.setEnabled(true);
                switchDelay(!bStop.getSelection());
            }
        });
        bDelay = JOE_B_JobOptionsForm_Delay.control(new Button(group2, SWT.RADIO));
        bDelay.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                bApply.setEnabled(true);
                switchDelay(bDelay.getSelection());
            }
        });
        sErrorHours = JOE_T_JobOptionsForm_ErrorHours.control(new Text(group2, SWT.BORDER));
        sErrorHours.addVerifyListener(new VerifyListener() {

            public void verifyText(final VerifyEvent e) {
                e.doit = Utils.isOnlyDigits(e.text);
            }
        });
        label14 = JOE_L_Colon.control(new Label(group2, SWT.NONE));
        sErrorMinutes = JOE_T_JobOptionsForm_ErrorMinutes.control(new Text(group2, SWT.BORDER));
        sErrorMinutes.addVerifyListener(new VerifyListener() {

            public void verifyText(final VerifyEvent e) {
                e.doit = Utils.isOnlyDigits(e.text);
            }
        });
        label17 = JOE_L_Colon.control(new Label(group2, SWT.NONE));
        sErrorSeconds = JOE_T_JobOptionsForm_ErrorSeconds.control(new Text(group2, SWT.BORDER));
        sErrorSeconds.addVerifyListener(new VerifyListener() {

            public void verifyText(final VerifyEvent e) {
                e.doit = Utils.isOnlyDigits(e.text);
            }
        });
        label8 = JOE_L_JobOptionsForm_DelayFormat.control(new Label(group2, SWT.NONE));
        bApply = JOE_B_JobOptionsForm_ApplyDelay.control(new Button(group2, SWT.NONE));
        bApply.setLayoutData(gridData21);
        bApply.setEnabled(false);
        bApply.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                applyDelay();
            }
        });
        label5 = new Label(group2, SWT.SEPARATOR | SWT.HORIZONTAL);
        label5.setLayoutData(gridData22);
        createTable();
        bNewDelay = JOE_B_JobOptionsForm_NewDelayAfterError.control(new Button(group2, SWT.NONE));
        bNewDelay.setLayoutData(gridData13);
        label6 = new Label(group2, SWT.SEPARATOR | SWT.HORIZONTAL);
        label6.setLayoutData(gridData23);
        bNewDelay.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                tErrorDelay.deselectAll();
                listener.newErrorDelay();
                initErrorDelay(true);
                getShell().setDefaultButton(bApply);
                bApply.setEnabled(true);
                sErrorCount.setFocus();
            }
        });
        bRemoveDelay = JOE_B_JobOptionsForm_RemoveDelay.control(new Button(group2, SWT.NONE));
        bRemoveDelay.setEnabled(false);
        bRemoveDelay.setLayoutData(gridData12);
        bRemoveDelay.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                if (tErrorDelay.getSelectionCount() > 0) {
                    int index = tErrorDelay.getSelectionIndex();
                    listener.deleteErrorDelay(index);
                    tErrorDelay.remove(index);
                    if (index >= tErrorDelay.getItemCount()) {
                        index--;
                    }
                    if (tErrorDelay.getItemCount() > 0) {
                        tErrorDelay.setSelection(index);
                        listener.selectErrorDelay(index);
                        initErrorDelay(true);
                    } else {
                        initErrorDelay(false);
                        bRemoveDelay.setEnabled(false);
                    }
                }
            }
        });
        sErrorCount.setLayoutData(gridData18);
        sErrorCount.addModifyListener(new org.eclipse.swt.events.ModifyListener() {

            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
                getShell().setDefaultButton(bApply);
                bApply.setEnabled(true);
            }
        });
        sErrorHours.setLayoutData(gridData17);
        sErrorHours.addModifyListener(new org.eclipse.swt.events.ModifyListener() {

            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
                Utils.setBackground(0, 23, sErrorHours);
                getShell().setDefaultButton(bApply);
                bApply.setEnabled(true);
            }
        });
        sErrorMinutes.setLayoutData(gridData16);
        sErrorMinutes.addModifyListener(new org.eclipse.swt.events.ModifyListener() {

            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
                Utils.setBackground(0, 59, sErrorMinutes);
                getShell().setDefaultButton(bApply);
                bApply.setEnabled(true);
            }
        });
        sErrorSeconds.setLayoutData(gridData15);
        sErrorSeconds.addModifyListener(new org.eclipse.swt.events.ModifyListener() {

            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
                if (Utils.str2int(sErrorHours.getText()) > 0 || Utils.str2int(sErrorMinutes.getText()) > 0) {
                    Utils.setBackground(0, 59, sErrorSeconds);
                } else {
                    sErrorSeconds.setBackground(null);
                }
                getShell().setDefaultButton(bApply);
                bApply.setEnabled(true);
            }
        });
    }

    private void createGroup3() {
        GridData gridData29 = new org.eclipse.swt.layout.GridData();
        gridData29.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData29.widthHint = 90;
        gridData29.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        GridData gridData28 = new org.eclipse.swt.layout.GridData();
        gridData28.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData28.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        GridData gridData27 = new org.eclipse.swt.layout.GridData();
        gridData27.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData27.verticalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
        GridData gridData26 = new org.eclipse.swt.layout.GridData();
        gridData26.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData26.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        GridData gridData25 = new org.eclipse.swt.layout.GridData(GridData.FILL, GridData.CENTER, false, false, 11, 1);
        gridData25.heightHint = 10;
        GridData gridData10 = new org.eclipse.swt.layout.GridData();
        gridData10.widthHint = 25;
        gridData10.horizontalSpan = 1;
        GridData gridData9 = new org.eclipse.swt.layout.GridData();
        gridData9.widthHint = 25;
        GridData gridData8 = new org.eclipse.swt.layout.GridData();
        gridData8.widthHint = 25;
        GridData gridData6 = new org.eclipse.swt.layout.GridData();
        gridData6.horizontalSpan = 1;
        gridData6.grabExcessHorizontalSpace = true;
        GridLayout gridLayout2 = new GridLayout();
        gridLayout2.numColumns = 11;
        GridData gridData2 = new org.eclipse.swt.layout.GridData();
        gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData2.grabExcessHorizontalSpace = true;
        gridData2.grabExcessVerticalSpace = true;
        gridData2.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
        group3 = JOE_G_JobOptionsForm_DelayOrderAfterSetBack.control(new Group(group, SWT.NONE));
        group3.setLayout(gridLayout2);
        group3.setLayoutData(gridData2);
        label2 = JOE_L_JobOptionsForm_SetBackCount.control(new Label(group3, SWT.NONE));
        sSetBackCount = JOE_T_JobOptionsForm_SetBackCount.control(new Text(group3, SWT.BORDER));
        sSetBackCount.addVerifyListener(new VerifyListener() {

            public void verifyText(final VerifyEvent e) {
                e.doit = Utils.isOnlyDigits(e.text);
            }
        });
        sSetBackCount.setLayoutData(new GridData(48, SWT.DEFAULT));
        sSetBackCount.addModifyListener(new org.eclipse.swt.events.ModifyListener() {

            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
                getShell().setDefaultButton(bApplySetback);
                bApplySetback.setEnabled(true);
            }
        });
        bIsMaximum = JOE_B_JobOptionsForm_IsMax.control(new Button(group3, SWT.CHECK));
        bIsMaximum.setLayoutData(gridData6);
        bIsMaximum.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                getShell().setDefaultButton(bApplySetback);
                bApplySetback.setEnabled(true);
            }
        });
        final Label delayLabel = JOE_L_JobOptionsForm_Delay.control(new Label(group3, SWT.NONE));
        sSetBackHours = JOE_T_JobOptionsForm_SetBackHours.control(new Text(group3, SWT.BORDER));
        sSetBackHours.addVerifyListener(new VerifyListener() {

            public void verifyText(final VerifyEvent e) {
                e.doit = Utils.isOnlyDigits(e.text);
            }
        });
        label7 = JOE_L_Colon.control(new Label(group3, SWT.NONE));
        sSetBackMinutes = JOE_T_JobOptionsForm_SetBackMinutes.control(new Text(group3, SWT.BORDER));
        sSetBackMinutes.addVerifyListener(new VerifyListener() {

            public void verifyText(final VerifyEvent e) {
                e.doit = Utils.isOnlyDigits(e.text);
            }
        });
        label9 = JOE_L_Colon.control(new Label(group3, SWT.NONE));
        sSetBackSeconds = JOE_T_JobOptionsForm_SetBackSeconds.control(new Text(group3, SWT.BORDER));
        sSetBackSeconds.addVerifyListener(new VerifyListener() {

            public void verifyText(final VerifyEvent e) {
                e.doit = Utils.isOnlyDigits(e.text);
            }
        });
        label10 = JOE_L_JobOptionsForm_DelayFormat.control(new Label(group3, SWT.NONE));
        bApplySetback = JOE_B_JobOptionsForm_ApplySetBack.control(new Button(group3, SWT.NONE));
        bApplySetback.setEnabled(false);
        bApplySetback.setLayoutData(gridData29);
        bApplySetback.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                applySetback();
            }
        });
        label31 = new Label(group3, SWT.SEPARATOR | SWT.HORIZONTAL);
        label31.setLayoutData(gridData25);
        createTable2();
        bNewSetback = JOE_B_JobOptionsForm_NewSetBack.control(new Button(group3, SWT.NONE));
        bNewSetback.setEnabled(true);
        bNewSetback.setLayoutData(gridData28);
        bNewSetback.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                tSetback.deselectAll();
                listener.newSetbackDelay();
                initSetback(true);
                getShell().setDefaultButton(bApplySetback);
                bApplySetback.setEnabled(true);
                sSetBackCount.setFocus();
            }
        });
        label30 = new Label(group3, SWT.SEPARATOR | SWT.HORIZONTAL);
        label30.setLayoutData(gridData26);
        bRemoveSetback = JOE_B_JobOptionsForm_RemoveSetback.control(new Button(group3, SWT.NONE));
        bRemoveSetback.setEnabled(false);
        bRemoveSetback.setLayoutData(gridData27);
        bRemoveSetback.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                if (tSetback.getSelectionCount() > 0) {
                    int index = tSetback.getSelectionIndex();
                    listener.deleteSetbackDelay(index);
                    tSetback.remove(index);
                    if (index >= tSetback.getItemCount()) {
                        index--;
                    }
                    if (tSetback.getItemCount() > 0) {
                        tSetback.setSelection(index);
                        listener.selectSetbackDelay(index);
                        initSetback(true);
                    } else {
                        initSetback(false);
                        bRemoveSetback.setEnabled(false);
                    }
                }
            }
        });
        sSetBackHours.setLayoutData(gridData8);
        sSetBackHours.addModifyListener(new org.eclipse.swt.events.ModifyListener() {

            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
                Utils.setBackground(0, 23, sSetBackHours);
                getShell().setDefaultButton(bApplySetback);
                bApplySetback.setEnabled(true);
            }
        });
        sSetBackMinutes.setLayoutData(gridData9);
        sSetBackMinutes.addModifyListener(new org.eclipse.swt.events.ModifyListener() {

            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
                Utils.setBackground(0, 59, sSetBackMinutes);
                getShell().setDefaultButton(bApplySetback);
                bApplySetback.setEnabled(true);
            }
        });
        sSetBackSeconds.setLayoutData(gridData10);
        sSetBackSeconds.addModifyListener(new org.eclipse.swt.events.ModifyListener() {

            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
                if (Utils.str2int(sSetBackHours.getText()) > 0 || Utils.str2int(sSetBackMinutes.getText()) > 0) {
                    Utils.setBackground(0, 59, sSetBackSeconds);
                } else {
                    sSetBackSeconds.setBackground(null);
                }
                getShell().setDefaultButton(bApplySetback);
                bApplySetback.setEnabled(true);
            }
        });
    }

    private void createTable() {
        tErrorDelay = JOE_Tbl_JobOptionsForm_ErrorDelay.control(new Table(group2, SWT.BORDER | SWT.FULL_SELECTION));
        tErrorDelay.setSortDirection(SWT.UP);
        tErrorDelay.setHeaderVisible(true);
        tErrorDelay.setLayoutData(new org.eclipse.swt.layout.GridData(GridData.FILL, GridData.FILL, true, true, 11, 3));
        tErrorDelay.setLinesVisible(true);
        tErrorDelay.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                if (tErrorDelay.getSelectionCount() > 0) {
                    listener.selectErrorDelay(tErrorDelay.getSelectionIndex());
                    initErrorDelay(true);
                    sErrorCount.selectAll();
                } else {
                    initErrorDelay(false);
                }
                bRemoveDelay.setEnabled(tErrorDelay.getSelectionCount() > 0);
            }
        });
        TableColumn tableColumn = JOE_TCl_JobOptionsForm_ErrorCount.control(new TableColumn(tErrorDelay, SWT.NONE));
        tErrorDelay.setSortColumn(tableColumn);
        tableColumn.setWidth(150);
        TableColumn tableColumn1 = JOE_TCl_JobOptionsForm_Delayhhmmss.control(new TableColumn(tErrorDelay, SWT.NONE));
        tableColumn1.setWidth(250);
    }

    private void createTable2() {
        tSetback = JOE_Tbl_JobOptionsForm_SetBack.control(new Table(group3, SWT.BORDER | SWT.FULL_SELECTION));
        tSetback.setSortDirection(SWT.UP);
        tSetback.setHeaderVisible(true);
        tSetback.setLayoutData(new org.eclipse.swt.layout.GridData(GridData.FILL, GridData.FILL, true, true, 10, 3));
        tSetback.setLinesVisible(true);
        tSetback.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                if (tSetback.getSelectionCount() > 0) {
                    listener.selectSetbackDelay(tSetback.getSelectionIndex());
                    initSetback(true);
                    sSetBackCount.selectAll();
                } else {
                    initSetback(false);
                }
                bRemoveSetback.setEnabled(tSetback.getSelectionCount() > 0);
            }
        });
        TableColumn tableColumn2 = JOE_TCl_JobOptionsForm_SetBackCount.control(new TableColumn(tSetback, SWT.NONE));
        tSetback.setSortColumn(tableColumn2);
        tableColumn2.setWidth(150);
        TableColumn tableColumn3 = JOE_TCl_JobOptionsForm_IsMax.control(new TableColumn(tSetback, SWT.NONE));
        tableColumn3.setWidth(80);
        TableColumn tableColumn4 = JOE_TCl_JobOptionsForm_Delayhhmmss.control(new TableColumn(tSetback, SWT.NONE));
        tableColumn4.setWidth(250);
    }

    private void createTable3() {
        GridData gridData30 = new org.eclipse.swt.layout.GridData();
        gridData30.horizontalSpan = 4;
        gridData30.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData30.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData30.grabExcessHorizontalSpace = true;
        gridData30.grabExcessVerticalSpace = true;
        gridData30.verticalSpan = 3;
        tDirectories = JOE_Tbl_JobOptionsForm_Dirs.control(new Table(group1, SWT.BORDER | SWT.FULL_SELECTION));
        tDirectories.setHeaderVisible(true);
        tDirectories.setLayoutData(gridData30);
        tDirectories.setLinesVisible(true);
        tDirectories.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                if (tDirectories.getSelectionCount() > 0) {
                    listener.selectDirectory(tDirectories.getSelectionIndex());
                    initDirectory(true);
                    tDirectory.setFocus();
                } else {
                    initDirectory(false);
                }
                bRemoveDirectory.setEnabled(tDirectories.getSelectionCount() > 0);
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
                tDirectory.selectAll();
            }
        });
        TableColumn tableColumn5 = JOE_TCl_JobOptionsForm_Dir.control(new TableColumn(tDirectories, SWT.NONE));
        tableColumn5.setWidth(300);
        TableColumn tableColumn6 = JOE_TCl_JobOptionsForm_Regex.control(new TableColumn(tDirectories, SWT.NONE));
        tableColumn6.setWidth(250);
    }

    private void initDirectories(boolean enabled) {
        tDirectory.setEnabled(enabled);
        tRegex.setEnabled(enabled);
        tDirectory.setEnabled(enabled);
        bNewDirectory.setEnabled(true);
        initDirectory(false);
        listener.fillDirectories(tDirectories);
    }

    private void initDirectory(boolean enabled) {
        tDirectory.setEnabled(enabled);
        tRegex.setEnabled(enabled);
        if (enabled) {
            tDirectory.setText(listener.getDirectory());
            tRegex.setText(listener.getRegex());
        } else {
            tDirectory.setText("");
            tRegex.setText("");
        }
        bApplyDirectory.setEnabled(false);
    }

    private void applyDirectory() {
        if (Utils.isRegExpressions(tRegex.getText())) {
            listener.applyDirectory(tDirectory.getText(), tRegex.getText());
            listener.fillDirectories(tDirectories);
            initDirectory(false);
            getShell().setDefaultButton(null);
        } else {
            MainWindow.message(JOE_M_NoRegex.params(tRegex.getText()), SWT.ICON_INFORMATION);
        }
    }

    private void initSetbacks(boolean enabled) {
        sSetBackCount.setEnabled(enabled);
        bIsMaximum.setEnabled(enabled);
        sSetBackHours.setEnabled(enabled);
        sSetBackMinutes.setEnabled(enabled);
        sSetBackSeconds.setEnabled(enabled);
        bNewSetback.setEnabled(true);
        initSetback(false);
        listener.fillSetbacks(tSetback);
    }

    private void initSetback(boolean enabled) {
        sSetBackCount.setEnabled(enabled);
        bIsMaximum.setEnabled(enabled);
        sSetBackHours.setEnabled(enabled);
        sSetBackMinutes.setEnabled(enabled);
        sSetBackSeconds.setEnabled(enabled);
        if (enabled) {
            bIsMaximum.setSelection(listener.isMaximum());
            sSetBackHours.setText(Utils.fill(2, listener.getSetbackCountHours()));
            sSetBackMinutes.setText(Utils.fill(2, listener.getSetbackCountMinutes()));
            if (!"".equals(listener.getSetbackCountHours() + listener.getSetbackCountMinutes())) {
                sSetBackSeconds.setText(Utils.fill(2, listener.getSetbackCountSeconds()));
            } else {
                sSetBackSeconds.setText(listener.getSetbackCountSeconds());
            }
            sSetBackCount.setText(listener.getSetbackCount());
        }
        bApplySetback.setEnabled(false);
    }

    private void applySetback() {
        int maximum = 0;
        int maximumMax = 0;
        int maxSetback = 0;
        int sel = tSetback.getSelectionIndex();
        TableItem[] setback = tSetback.getItems();
        if ("0".equals(sSetBackCount.getText())) {
            MainWindow.message(JOE_M_ZeroNotAllowed.label(), SWT.ICON_INFORMATION);
        } else {
            for (int i = 0; i < setback.length; i++) {
                if (setback[i].getText(1).equalsIgnoreCase(JOE_M_Yes.label()) && sel != i) {
                    maximum = maximum + 1;
                }
            }
            if (bIsMaximum.getSelection()) {
                maximum = maximum + 1;
            }
            boolean found = false;
            for (int i = 0; i < setback.length; i++) {
                if (setback[i].getText(0).equals(sSetBackCount.getText()) && sel != i) {
                    found = true;
                }
            }
            for (int i = 0; i < setback.length; i++) {
                if (i != sel && maximumMax < Utils.str2int(setback[i].getText(0)) && setback[i].getText(1).equalsIgnoreCase(JOE_M_Yes.label())) {
                    maximumMax = Utils.str2int(setback[i].getText(0));
                }
            }
            for (int i = 0; i < setback.length; i++) {
                if (i != sel && !setback[i].getText(1).equalsIgnoreCase(JOE_M_Yes.label()) && maxSetback < Utils.str2int(setback[i].getText(0))) {
                    maxSetback = Utils.str2int(setback[i].getText(0));
                }
            }
            if (bIsMaximum.getSelection() && Utils.str2int(sSetBackCount.getText()) > maximumMax) {
                maximumMax = Utils.str2int(sSetBackCount.getText());
            }
            if (Utils.str2int(sSetBackCount.getText()) > maxSetback) {
                maxSetback = Utils.str2int(sSetBackCount.getText());
            }
            if (maximum > 1) {
                MainWindow.message(JOE_M_0032.label(), SWT.ICON_INFORMATION);
                sSetBackCount.setFocus();
            } else {
                if (found) {
                    MainWindow.message(JOE_M_0033.label(), SWT.ICON_INFORMATION);
                    sSetBackCount.setFocus();
                } else {
                    if ("".equals(sSetBackCount.getText())) {
                        MainWindow.message(JOE_M_0034.label(), SWT.ICON_INFORMATION);
                        sSetBackCount.setFocus();
                    } else {
                        if (maximumMax > 0 && maximumMax < Utils.str2int(sSetBackCount.getText())
                                || maxSetback > Utils.str2int(sSetBackCount.getText()) && bIsMaximum.getSelection()) {
                            MainWindow.message(JOE_M_0035.label(), SWT.ICON_INFORMATION);
                            sSetBackCount.setFocus();
                        } else {
                            String delay = sSetBackSeconds.getText();
                            if (!"".equals(sSetBackMinutes.getText() + sSetBackHours.getText())) {
                                delay = Utils.getTime(sSetBackHours.getText(), sSetBackMinutes.getText(), sSetBackSeconds.getText(), true);
                            }
                            if ("00".equals(delay) || "".equals(delay)) {
                                delay = "0";
                            }
                            listener.applySetbackDelay(sSetBackCount.getText(), bIsMaximum.getSelection(), delay);
                            listener.fillSetbacks(tSetback);
                            initSetback(false);
                            getShell().setDefaultButton(null);
                            sortTable(tSetback);
                            listener.newSetbacks(tSetback);
                        }
                    }
                }
            }
        }
    }

    private void initErrorDelays(boolean enabled) {
        bNewDelay.setEnabled(true);
        bStop.setEnabled(enabled);
        bDelay.setEnabled(enabled);
        initErrorDelay(false);
        listener.fillTable(tErrorDelay);
        bRemoveDelay.setEnabled(false);
    }

    private void initErrorDelay(boolean enabled) {
        bStop.setEnabled(enabled);
        bDelay.setEnabled(enabled);
        sErrorCount.setEnabled(enabled);
        sErrorHours.setEnabled(enabled);
        sErrorMinutes.setEnabled(enabled);
        sErrorSeconds.setEnabled(enabled);
        if (enabled) {
            boolean isStop = listener.isStop();
            bStop.setSelection(isStop);
            bDelay.setSelection(!isStop);
            if (isStop) {
                sErrorHours.setEnabled(false);
                sErrorMinutes.setEnabled(false);
                sErrorSeconds.setEnabled(false);
            } else {
                sErrorHours.setText(Utils.fill(2, listener.getErrorCountHours()));
                sErrorMinutes.setText(Utils.fill(2, listener.getErrorCountMinutes()));
                sErrorSeconds.setText(Utils.fill(2, listener.getErrorCountSeconds()));
            }
            sErrorCount.setText(listener.getErrorCount());
        }
        bApply.setEnabled(false);
    }

    private void switchDelay(boolean enabled) {
        sErrorHours.setEnabled(enabled);
        sErrorMinutes.setEnabled(enabled);
        sErrorSeconds.setEnabled(enabled);
    }

    private void applyDelay() {
        boolean found = false;
        TableItem[] errorDelay = tErrorDelay.getItems();
        int sel = tErrorDelay.getSelectionIndex();
        int maximum = 0;
        int maxErrorDelay = 0;
        int maxAktErrorDelay = 0;
        for (int i = 0; i < errorDelay.length; i++) {
            if (i != sel && !errorDelay[i].getText(1).equalsIgnoreCase(JOE_M_Stop.label())
                    && maxAktErrorDelay < Utils.str2int(errorDelay[i].getText(0))) {
                maxAktErrorDelay = Utils.str2int(errorDelay[i].getText(0));
            }
        }
        for (int i = 0; i < errorDelay.length; i++) {
            if (i != sel && maxErrorDelay < Utils.str2int(errorDelay[i].getText(0)) && errorDelay[i].getText(1).equalsIgnoreCase(JOE_M_Stop.label())) {
                maxErrorDelay = Utils.str2int(errorDelay[i].getText(0));
            }
        }
        for (int i = 0; i < errorDelay.length; i++) {
            if (errorDelay[i].getText(1).equalsIgnoreCase(JOE_M_Stop.label()) && sel != i) {
                maximum = maximum + 1;
            }
        }
        if (bStop.getSelection()) {
            maximum = maximum + 1;
        }
        for (int i = 0; i < errorDelay.length; i++) {
            if (errorDelay[i].getText(0).equals(sErrorCount.getText()) && sel != i) {
                found = true;
            }
        }
        if (bStop.getSelection() && Utils.str2int(sErrorCount.getText()) > maxErrorDelay) {
            maxErrorDelay = Utils.str2int(sErrorCount.getText());
        }
        if (Utils.str2int(sErrorCount.getText()) > maxAktErrorDelay) {
            maxAktErrorDelay = Utils.str2int(sErrorCount.getText());
        }
        if (found) {
            MainWindow.message(JOE_M_0036.label(), SWT.ICON_INFORMATION);
            sErrorCount.setFocus();
        } else {
            if (maxErrorDelay > 0 && maxErrorDelay < Utils.str2int(sErrorCount.getText()) || maxAktErrorDelay > Utils.str2int(sErrorCount.getText())
                    && bStop.getSelection()) {
                MainWindow.message(JOE_M_0037.label(), SWT.ICON_INFORMATION);
                sErrorCount.setFocus();
            } else {
                if (maximum > 1) {
                    MainWindow.message(JOE_M_0038.label(), SWT.ICON_INFORMATION);
                } else {
                    if ("".equals(sErrorCount.getText())) {
                        MainWindow.message(JOE_M_0039.label(), SWT.ICON_INFORMATION);
                        sErrorCount.setFocus();
                    } else {
                        String delay = Utils.getTime(sErrorHours.getText(), sErrorMinutes.getText(), sErrorSeconds.getText(), true);
                        if (bStop.getSelection()) {
                            delay = JOE_M_Stop.label();
                        }
                        listener.applyErrorDelay(sErrorCount.getText(), delay);
                        listener.fillTable(tErrorDelay);
                        sortTable(tErrorDelay);
                        listener.newErrorDelays(tErrorDelay);
                        initErrorDelay(false);
                        getShell().setDefaultButton(null);
                    }
                }
            }
        }
    }

    private void sortTable(Table t) {
        TableItem[] items = t.getItems();
        Collator collator = Collator.getInstance(Locale.getDefault());
        int index = 0;
        for (int i = 1; i < items.length; i++) {
            String value1 = items[i].getText(index);
            for (int j = 0; j < i; j++) {
                String value2 = items[j].getText(index);
                if (collator.compare(value1, value2) < 0) {
                    String[] values = { items[i].getText(0), items[i].getText(1) };
                    items[i].dispose();
                    TableItem item = new TableItem(t, SWT.NONE, j);
                    item.setText(values);
                    items = t.getItems();
                    break;
                }
            }
        }
    }

}