package sos.scheduler.editor.conf.container;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jdom.Element;

import sos.scheduler.editor.app.MainWindow;
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.classes.FormBaseClass;
import sos.scheduler.editor.conf.listeners.JobListener;

import com.sos.joe.globals.JOEConstants;
import com.sos.joe.globals.messages.SOSJOEMessageCodes;

public class JobOptions extends FormBaseClass {

    private Combo cboHistoryWithLog = null;
    private Combo cboHistoryOnProcess = null;
    private Combo cboHistory = null;
    private Combo logLevel = null;
    private Combo stdErrlogLevel = null;
    private Label lblJobChainJob = null;
    private Composite cOrder = null;
    private Button bOrderYes = null;
    private Button bOrderNo = null;
    private Button bStopOnError = null;
    private Group gOptionsGroup;
    private Group gMainOptionsGroup;
    private Combo sPriority = null;
    private Text sIdleTimeout = null;
    private Text sTimeout = null;
    private Text sTasks = null;
    private Text tIgnoreSignals = null;
    private Label label11 = null;
    private Label label13 = null;
    private Label label15 = null;
    private Label label17 = null;
    private Text tMintasks = null;
    private Text tCredentialKey = null;
    private Button bForceIdletimeout = null;
    private Combo cSignals = null;
    private Combo comVisible = null;
    private Button addButton = null;
    private Text txtWarnIfLongerThan = null;
    private Text txtWarnIfShorterThan = null;
    private boolean init = true;
    private JobListener objJobDataProvider = null;

    public JobOptions(Composite pParentComposite, JobListener pobjJobDataProvider) {
        super(pParentComposite, pobjJobDataProvider);
        objJobDataProvider = pobjJobDataProvider;
        init = true;
        createGroup();
        createMainOptionsGroup();
        initForm();
        init = false;
    }

    private void createGroup() {
        gOptionsGroup = SOSJOEMessageCodes.JOE_G_JobOptions_Options.control(new Group(objParent, SWT.NONE));
        final GridData gridDataGroup = new GridData(GridData.FILL, GridData.FILL, true, false, 8, 5);
        gridDataGroup.heightHint = 180;
        gridDataGroup.minimumHeight = 30;
        gOptionsGroup.setLayoutData(gridDataGroup);
        final GridLayout gridLayout_2 = new GridLayout();
        gridLayout_2.marginHeight = 0;
        gridLayout_2.numColumns = 4;
        gOptionsGroup.setLayout(gridLayout_2);
        lblJobChainJob = SOSJOEMessageCodes.JOE_L_JobOptions_JobChainJob.control(new Label(gOptionsGroup, SWT.NONE));
        lblJobChainJob.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent event) {
                if (event.keyCode == SWT.F1) {
                    MainWindow.message("F1 gedr�ckt", SWT.ICON_INFORMATION);
                }
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                //
            }
        });
        cOrder = new Composite(gOptionsGroup, SWT.NONE);
        cOrder.setLayout(new RowLayout());
        bOrderYes = SOSJOEMessageCodes.JOE_B_JobOptions_OrderYes.control(new Button(cOrder, SWT.RADIO));
        bOrderYes.setSelection(objJobDataProvider.getOrder());
        bOrderYes.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                if (init || !bOrderYes.getSelection()) {
                    return;
                }
                objJobDataProvider.setOrder(bOrderYes.getSelection());
                boolean _deleteRuntimeAttribute = false;
                Element job = objJobDataProvider.getJob();
                if (objJobDataProvider.getOrder()
                        && job != null
                        && job.getChild("run_time") != null
                        && (!sos.scheduler.editor.app.Utils.getAttributeValue("single_start", job.getChild("run_time")).isEmpty()
                                || !sos.scheduler.editor.app.Utils.getAttributeValue("let_run", job.getChild("run_time")).isEmpty() 
                                || !sos.scheduler.editor.app.Utils.getAttributeValue("once", job.getChild("run_time")).isEmpty())) {
                    if (bOrderYes.isVisible()) {
                        _deleteRuntimeAttribute =
                                Utils.checkElement(objJobDataProvider.getJobName(), objJobDataProvider.get_dom(), JOEConstants.JOB, "change_order");
                    }
                    if (_deleteRuntimeAttribute) {
                        objJobDataProvider.getJob().getChild("run_time").removeAttribute("single_start");
                        objJobDataProvider.getJob().getChild("run_time").removeAttribute("let_run");
                        objJobDataProvider.getJob().getChild("run_time").removeAttribute("once");
                    }
                }
            }
        });
        bOrderNo = SOSJOEMessageCodes.JOE_B_JobOptions_OrderNo.control(new Button(cOrder, SWT.RADIO));
        bOrderNo.setEnabled(true);
        bOrderNo.setSelection(!objJobDataProvider.getOrder());
        bOrderNo.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                if (init) {
                    return;
                }
                if (objJobDataProvider.getOrder()
                        && !Utils.checkElement(objJobDataProvider.getJobName(), objJobDataProvider.get_dom(), JOEConstants.JOB, "change_order")) {
                    init = true;
                    bOrderNo.setSelection(false);
                    bOrderYes.setSelection(true);
                    init = false;
                    return;
                }
                objJobDataProvider.setOrder(!bOrderNo.getSelection());
            }
        });
        final Label stop_on_errorLabel = SOSJOEMessageCodes.JOE_L_JobOptions_StopOnError.control(new Label(cOrder, SWT.NONE));
        bStopOnError = SOSJOEMessageCodes.JOE_B_JobOptions_StopOnError.control(new Button(cOrder, SWT.CHECK));
        bStopOnError.setSelection(objJobDataProvider.getStopOnError());
        bStopOnError.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent event) {
                if (objJobDataProvider.Check4HelpKey(event.keyCode, "job", "stop_on_error")) {
                    event.doit = true;
                    return;
                }
            }
        });
        bStopOnError.setSelection(true);
        bStopOnError.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(final SelectionEvent e) {
                if (init) {
                    return;
                }
                objJobDataProvider.setStopOnError(bStopOnError.getSelection());
            }
        });
        new Label(gOptionsGroup, SWT.NONE);
        new Label(gOptionsGroup, SWT.NONE);
        final Label logLevelLabel = SOSJOEMessageCodes.JOE_L_MailForm_LogLevel.control(new Label(gOptionsGroup, SWT.NONE));
        logLevel = SOSJOEMessageCodes.JOE_Cbo_MailForm_LogLevel.control(new Combo(gOptionsGroup, intComboBoxStyle));
        GridData gd_LogLevel = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
        gd_LogLevel.minimumWidth = 150;
        logLevel.setLayoutData(gd_LogLevel);
        logLevel.setText(objJobDataProvider.getValue("log_level"));
        

        logLevel.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                boolean inList = false;
                for (int i = 0; i < logLevel.getItems().length - 1; i++) {
                    if (logLevel.getItem(i).equals(logLevel.getText())) {
                        inList = true;
                    }
                }
                if ("".equals(logLevel.getText().trim()) || inList) {
                    objJobDataProvider.setValue("log_level", logLevel.getText());
                }
            }
        });

        logLevel.setItems(new String[] { "info", "debug1", "debug2", "debug3", "debug4", "debug5", "debug6", "debug7", "debug8", "debug9", "" });
        new Label(gOptionsGroup, SWT.NONE);
        new Label(gOptionsGroup, SWT.NONE);
        final Label stdErrlogLevelLabel = SOSJOEMessageCodes.JOE_L_JobOptionsForm_StdErrLogLevel.control(new Label(gOptionsGroup, SWT.NONE));
        stdErrlogLevel = SOSJOEMessageCodes.JOE_Cbo_JobOptionsForm_StdErrLogLevel.control(new Combo(gOptionsGroup, intComboBoxStyle));
        GridData gd_stdErrLogLevel = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
        gd_stdErrLogLevel.minimumWidth = 150;
        stdErrlogLevel.setLayoutData(gd_stdErrLogLevel);
        stdErrlogLevel.setItems(new String[] { "info", "error" });
        stdErrlogLevel.setText(objJobDataProvider.getStdErrLogLevel());
        stdErrlogLevel.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(final SelectionEvent e) {
                if ("info".equals(stdErrlogLevel.getText())) {
                    objJobDataProvider.removeAttribute("stderr_log_level");
                } else {
                    objJobDataProvider.setStdErrLogLevel(stdErrlogLevel.getText());
                }
            }
        });
        
        stdErrlogLevel.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                if ("".equals(stdErrlogLevel.getText().trim()) || "info".equals(stdErrlogLevel.getText()) || "error".equals(stdErrlogLevel.getText())) {
                    objJobDataProvider.setStdErrLogLevel(stdErrlogLevel.getText().trim());
                }
            }
        });
        
        
        new Label(gOptionsGroup, SWT.NONE);
        final Label label_2 = new Label(gOptionsGroup, SWT.HORIZONTAL | SWT.SEPARATOR);
        final GridData gridData_4 = new GridData(GridData.FILL, GridData.CENTER, false, false, 4, 1);
        gridData_4.heightHint = 8;
        label_2.setLayoutData(gridData_4);
        final Label historyLabel = SOSJOEMessageCodes.JOE_L_MailForm_History.control(new Label(gOptionsGroup, SWT.NONE));
        cboHistory = SOSJOEMessageCodes.JOE_Cbo_MailForm_History.control(new Combo(gOptionsGroup, intComboBoxStyle));
        cboHistory.setText(objJobDataProvider.getHistory());
        cboHistory.setItems(new String[] { "yes", "no", "" });
        cboHistory.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                if ("".equals(cboHistory.getText().trim()) || "yes".equals(cboHistory.getText()) || "no".equals(cboHistory.getText())) {
                    objJobDataProvider.setHistory(cboHistory.getText().trim());
                }
            }
        });

        GridData gd_cboHistory = new GridData(GridData.BEGINNING, GridData.CENTER, true, false);
        gd_cboHistory.minimumWidth = 150;
        cboHistory.setLayoutData(gd_cboHistory);
        new Label(gOptionsGroup, SWT.NONE);
        new Label(gOptionsGroup, SWT.NONE);
        final Label historyOnProcessLabel = SOSJOEMessageCodes.JOE_L_MailForm_HistoryOnProcess.control(new Label(gOptionsGroup, SWT.NONE));
        cboHistoryOnProcess = SOSJOEMessageCodes.JOE_Cbo_MailForm_HistoryOnProcess.control(new Combo(gOptionsGroup, intComboBoxStyle));
        cboHistoryOnProcess.setText(objJobDataProvider.getHistoryOnProcess());
        cboHistoryOnProcess.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                boolean isDigit = true;
                char[] c = cboHistoryOnProcess.getText().toCharArray();
                for (int i = 0; i < c.length; i++) {
                    isDigit = Character.isDigit(c[i]);
                    if (!isDigit) {
                        break;
                    }
                }
                if ("yes".equals(cboHistoryOnProcess.getText()) || "no".equals(cboHistoryOnProcess.getText()) || isDigit) {
                    objJobDataProvider.setHistoryOnProcess(cboHistoryOnProcess.getText());
                }
            }
        });
        cboHistoryOnProcess.addVerifyListener(new VerifyListener() {

            public void verifyText(final VerifyEvent e) {
                e.doit = Utils.isOnlyDigits(e.text);
            }
        });
        cboHistoryOnProcess.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(final SelectionEvent e) {
                cboHistoryOnProcess.setText(objJobDataProvider.getValue("history_on_process"));
            }
        });
        GridData gd_cboHistoryOnProcess = new GridData(GridData.BEGINNING, GridData.CENTER, true, false);
        gd_cboHistoryOnProcess.minimumWidth = 150;
        cboHistoryOnProcess.setLayoutData(gd_cboHistoryOnProcess);
        new Label(gOptionsGroup, SWT.NONE);
        new Label(gOptionsGroup, SWT.NONE);
        final Label historyWithLogLabel = SOSJOEMessageCodes.JOE_L_MailForm_HistoryWithLog.control(new Label(gOptionsGroup, SWT.NONE));
        cboHistoryWithLog = SOSJOEMessageCodes.JOE_Cbo_MailForm_HistoryWithLog.control(new Combo(gOptionsGroup, intComboBoxStyle));
        cboHistoryWithLog.setText(objJobDataProvider.getHistoryWithLog());
        cboHistoryWithLog.setItems(new String[] { "yes", "no", "gzip", "" });
        cboHistoryWithLog.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                if ("".equals(cboHistoryWithLog.getText().trim()) || "yes".equals(cboHistoryWithLog.getText()) || "no".equals(cboHistoryWithLog.getText())
                        || "gzip".equals(cboHistoryWithLog.getText())) {
                    objJobDataProvider.setHistoryWithLog(cboHistoryWithLog.getText().trim());
                }
            }
        });
        GridData gd_cboHistoryWithLog = new GridData(GridData.BEGINNING, GridData.CENTER, true, false);
        gd_cboHistoryWithLog.minimumWidth = 150;
        cboHistoryWithLog.setLayoutData(gd_cboHistoryWithLog);
        cboHistoryOnProcess.setItems(new String[] { "0", "1", "2", "3", "4", "" });
        objParent.layout();
    }

    private void createMainOptionsGroup() {
        gMainOptionsGroup = new Group(objParent, SWT.NONE);
        final GridData gridDataGroup = new GridData(GridData.FILL, GridData.FILL, true, true, 13, 4);
        gridDataGroup.heightHint = 100;
        gridDataGroup.minimumHeight = 30;
        gMainOptionsGroup.setLayoutData(gridDataGroup);
        final GridLayout gridLayout_2 = new GridLayout();
        gridLayout_2.marginHeight = 0;
        gridLayout_2.numColumns = 4;
        gMainOptionsGroup.setLayout(gridLayout_2);
        final GridData gridData_12 = new GridData(GridData.FILL, GridData.FILL, true, true);
        gridData_12.heightHint = 353;

        
        final Label credentialKeyLabel = SOSJOEMessageCodes.JOE_L_JobMainOptionForm_CredentialKey.control(new Label(gMainOptionsGroup, SWT.NONE));
        credentialKeyLabel.setLayoutData(new GridData(SWT.LEFT, GridData.CENTER, false, false));
        tCredentialKey = SOSJOEMessageCodes.JOE_T_JobMainOptionForm_MinTasks.control(new Text(gMainOptionsGroup, SWT.BORDER));
       
        tCredentialKey.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                objJobDataProvider.setCredentialKey(tCredentialKey.getText());
            }
        });
        tCredentialKey.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
        new Label(gMainOptionsGroup, SWT.NONE);
        new Label(gMainOptionsGroup, SWT.NONE);

        
        
        
        
        final Label ignore_signalLabel = SOSJOEMessageCodes.JOE_L_JobMainOptionForm_IgnoreSignals.control(new Label(gMainOptionsGroup, SWT.NONE));
        ignore_signalLabel.setLayoutData(new GridData(SWT.LEFT, GridData.CENTER, false, false));
        tIgnoreSignals = SOSJOEMessageCodes.JOE_T_JobMainOptionForm_IgnoreSignals.control(new Text(gMainOptionsGroup, SWT.BORDER));
        tIgnoreSignals.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                objJobDataProvider.setIgnoreSignal(tIgnoreSignals.getText());
            }
        });

        tIgnoreSignals.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, false));
        addButton = SOSJOEMessageCodes.JOE_B_JobMainOptionForm_Add.control(new Button(gMainOptionsGroup, SWT.NONE));
        addButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(final SelectionEvent e) {
                if (init) {
                    return;
                }
                if ("".equals(tIgnoreSignals.getText())) {
                    tIgnoreSignals.setText(cSignals.getText());
                } else {
                    tIgnoreSignals.setText(tIgnoreSignals.getText() + " " + cSignals.getText());
                }
            }
        });
        addButton.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
        cSignals = SOSJOEMessageCodes.JOE_Cbo_JobMainOptionForm_Signals.control(new Combo(gMainOptionsGroup, SWT.NONE));
        cSignals.setItems(new String[] { "SIGHUP", "SIGINT", "SIGQUIT", "SIGILL", "SIGTRAP", "SIGABRT", "SIGIOT", "SIGBUS", "SIGFPE", "SIGKILL",
                "SIGUSR1", "SIGSEGV", "SIGUSR2", "SIGPIPE", "SIGALRM", "SIGTERM", "SIGSTKFLT", "SIGCHLD", "SIGCONT", "SIGSTOP", "SIGTSTP", "SIGTTIN",
                "SIGTTOU", "SIGURG", "SIGXCPU", "SIGXFSZ", "SIGVTALRM", "SIGPROF", "SIGWINCH", "SIGPOLL", "SIGIO", "SIGPWR", "SIGSYS." });
        cSignals.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, false));
        label17 = SOSJOEMessageCodes.JOE_L_JobMainOptionForm_Priority.control(new Label(gMainOptionsGroup, SWT.NONE));
        final GridData gridData_7 = new GridData(SWT.LEFT, GridData.CENTER, false, false);
        gridData_7.widthHint = 41;
        label17.setLayoutData(gridData_7);
        sPriority = SOSJOEMessageCodes.JOE_Cbo_JobMainOptionForm_Priority.control(new Combo(gMainOptionsGroup, SWT.NONE));
        sPriority.setItems(new String[] { "idle", "below_normal", "normal", "above_normal", "high" });
        sPriority.addVerifyListener(new VerifyListener() {

            public void verifyText(final VerifyEvent e) {
                e.doit =
                        Utils.isOnlyDigits(e.text) || "idle".equals(e.text) || "below_normal".equals(e.text) || "normal".equals(e.text)
                                || "above_normal".equals(e.text) || "high".equals(e.text);
            }
        });
        final GridData gridData_1 = new GridData(GridData.FILL, GridData.CENTER, true, false);
        gridData_1.verticalIndent = -1;
        sPriority.setLayoutData(gridData_1);
        sPriority.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                Utils.setBackground(-20, 20, sPriority);
                objJobDataProvider.setPriority(sPriority.getText());
            }
        });
        new Label(gMainOptionsGroup, SWT.NONE);
        new Label(gMainOptionsGroup, SWT.NONE);
        final Label visibleLabel = SOSJOEMessageCodes.JOE_L_JobMainOptionForm_Visible.control(new Label(gMainOptionsGroup, SWT.NONE));
        visibleLabel.setLayoutData(new GridData(SWT.LEFT, GridData.CENTER, false, false));
        comVisible = SOSJOEMessageCodes.JOE_Cbo_JobMainOptionForm_Visible.control(new Combo(gMainOptionsGroup, SWT.READ_ONLY));
        comVisible.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        comVisible.setItems(new String[] { "yes", "no", "never", "" });
        comVisible.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                objJobDataProvider.setVisible(comVisible.getText());
            }
        });
        new Label(gMainOptionsGroup, SWT.NONE);
        new Label(gMainOptionsGroup, SWT.NONE);
        final Label minMaskLabel = SOSJOEMessageCodes.JOE_L_JobMainOptionForm_MinTasks.control(new Label(gMainOptionsGroup, SWT.NONE));
        minMaskLabel.setLayoutData(new GridData(SWT.LEFT, GridData.CENTER, false, false));
        tMintasks = SOSJOEMessageCodes.JOE_T_JobMainOptionForm_MinTasks.control(new Text(gMainOptionsGroup, SWT.BORDER));
        tMintasks.addVerifyListener(new VerifyListener() {

            public void verifyText(final VerifyEvent e) {
                e.doit = Utils.isOnlyDigits(e.text);
            }
        });
        tMintasks.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                objJobDataProvider.setMintasks(tMintasks.getText());
            }
        });
        tMintasks.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
        new Label(gMainOptionsGroup, SWT.NONE);
        new Label(gMainOptionsGroup, SWT.NONE);
        label15 = SOSJOEMessageCodes.JOE_L_JobMainOptionForm_Tasks.control(new Label(gMainOptionsGroup, SWT.NONE));
        label15.setLayoutData(new GridData(SWT.LEFT, GridData.CENTER, false, false));
        sTasks = SOSJOEMessageCodes.JOE_T_JobMainOptionForm_Tasks.control(new Text(gMainOptionsGroup, SWT.BORDER));
        sTasks.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
        sTasks.addVerifyListener(new VerifyListener() {

            public void verifyText(final VerifyEvent e) {
                e.doit = Utils.isOnlyDigits(e.text);
            }
        });
        sTasks.addSelectionListener(new SelectionAdapter() {

            public void widgetDefaultSelected(final SelectionEvent e) {
                //
            }
        });
        sTasks.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                objJobDataProvider.setTasks(sTasks.getText());
            }
        });
        new Label(gMainOptionsGroup, SWT.NONE);
        new Label(gMainOptionsGroup, SWT.NONE);
        label13 = SOSJOEMessageCodes.JOE_L_JobMainOptionForm_Timeout.control(new Label(gMainOptionsGroup, SWT.NONE));
        label13.setLayoutData(new GridData(SWT.LEFT, GridData.CENTER, false, false));
        sTimeout = SOSJOEMessageCodes.JOE_T_JobMainOptionForm_Timeout.control(new Text(gMainOptionsGroup, SWT.BORDER));
        sTimeout.addVerifyListener(new VerifyListener() {

            public void verifyText(final VerifyEvent e) {
                //
            }
        });
        sTimeout.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                objJobDataProvider.setTimeout(sTimeout.getText());
            }
        });
        sTimeout.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
        final Label hhmmssLabel = SOSJOEMessageCodes.JOE_L_JobAssistent_TimeFormat.control(new Label(gMainOptionsGroup, SWT.NONE));
        hhmmssLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1));
        label11 = SOSJOEMessageCodes.JOE_L_JobMainOptionForm_IdleTimeout.control(new Label(gMainOptionsGroup, SWT.NONE));
        label11.setLayoutData(new GridData(SWT.LEFT, GridData.CENTER, false, false));
        sIdleTimeout = SOSJOEMessageCodes.JOE_T_JobMainOptionForm_IdleTimeout.control(new Text(gMainOptionsGroup, SWT.BORDER));
        sIdleTimeout.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
        sIdleTimeout.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                objJobDataProvider.setIdleTimeout(sIdleTimeout.getText());
            }
        });
        final Label hhmmssLabel_1 = SOSJOEMessageCodes.JOE_L_JobMainOptionForm_IdleTimeoutFormat.control(new Label(gMainOptionsGroup, SWT.NONE));
        hhmmssLabel_1.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1));
        final Label warnIfLongerLabel = SOSJOEMessageCodes.JOE_L_JobMainOptionForm_WarnIfLonger.control(new Label(gMainOptionsGroup, SWT.NONE));
        txtWarnIfLongerThan = SOSJOEMessageCodes.JOE_T_JobMainOptionForm_WarnIfLonger.control(new Text(gMainOptionsGroup, SWT.BORDER));
        txtWarnIfLongerThan.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                objJobDataProvider.setWarnIfLongerThan(txtWarnIfLongerThan.getText());
            }
        });
        txtWarnIfLongerThan.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        final Label hhmmssLabel_1_1 = SOSJOEMessageCodes.JOE_L_JobMainOptionForm_WarnIfLongerFormat.control(new Label(gMainOptionsGroup, SWT.NONE));
        hhmmssLabel_1_1.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1));
        final Label warnIfShorterLabel = SOSJOEMessageCodes.JOE_L_JobMainOptionForm_WarnIfShorter.control(new Label(gMainOptionsGroup, SWT.NONE));
        txtWarnIfShorterThan = SOSJOEMessageCodes.JOE_T_JobMainOptionForm_WarnIfShorter.control(new Text(gMainOptionsGroup, SWT.BORDER));
        txtWarnIfShorterThan.addModifyListener(new ModifyListener() {

            public void modifyText(final ModifyEvent e) {
                if (init) {
                    return;
                }
                objJobDataProvider.setWarnIfShorterThan(txtWarnIfShorterThan.getText());
            }
        });
        txtWarnIfShorterThan.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        final Label hhmmssOrPercentageLabel =
                SOSJOEMessageCodes.JOE_L_JobMainOptionForm_WarnIfShorterFormat.control(new Label(gMainOptionsGroup, SWT.NONE));
        hhmmssOrPercentageLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1));
        final Label force_idle_timeoutLabel =
                SOSJOEMessageCodes.JOE_L_JobMainOptionForm_ForceIdleTimeout.control(new Label(gMainOptionsGroup, SWT.NONE));
        force_idle_timeoutLabel.setLayoutData(new GridData(SWT.LEFT, GridData.CENTER, false, false));
        bForceIdletimeout = SOSJOEMessageCodes.JOE_B_JobMainOptionForm_ForceIdleTimeout.control(new Button(gMainOptionsGroup, SWT.CHECK));
        bForceIdletimeout.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 3, 1));
        bForceIdletimeout.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(final SelectionEvent e) {
                if (init) {
                    return;
                }
                objJobDataProvider.setForceIdletimeout(bForceIdletimeout.getSelection());
            }
        });
        objParent.layout();
    }

    public void initForm() {
        bOrderYes.setSelection(objJobDataProvider.getOrder());
        bOrderNo.setSelection(!objJobDataProvider.getOrder());
        bStopOnError.setSelection(objJobDataProvider.getStopOnError());
        logLevel.setText(objJobDataProvider.getValue("log_level"));
        stdErrlogLevel.setText(objJobDataProvider.getStdErrLogLevel());
        cboHistory.setText(objJobDataProvider.getValue("history"));
        cboHistoryOnProcess.setText(objJobDataProvider.getValue("history_on_process"));
        cboHistoryWithLog.setText(objJobDataProvider.getValue("history_with_log"));
        int index = 0;
        bForceIdletimeout.setSelection(objJobDataProvider.getForceIdletimeout());
        index = sPriority.indexOf(objJobDataProvider.getPriority());
        if (index >= 0) {
            sPriority.select(index);
        } else {
            int p = Utils.str2int(objJobDataProvider.getPriority(), 20);
            if (p == -999) {
                sPriority.setText("");
            } else {
                if (p < -20) {
                    p = -20;
                }
                sPriority.setText(String.valueOf(p));
            }
        }
        sTasks.setText(objJobDataProvider.getTasks());
        if (objJobDataProvider.getMintasks() != null) {
            tMintasks.setText(objJobDataProvider.getMintasks());
        }
        if (objJobDataProvider.getCredentialKey() != null) {
            tCredentialKey.setText(objJobDataProvider.getCredentialKey());
        }
        if (objJobDataProvider.getPriority() != null) {
            sPriority.setText(objJobDataProvider.getPriority());
        }
        tIgnoreSignals.setText(objJobDataProvider.getIgnoreSignal());
        sTimeout.setText(objJobDataProvider.getTimeout());
        sIdleTimeout.setText(objJobDataProvider.getIdleTimeout());
        comVisible.setText(objJobDataProvider.getVisible());
        txtWarnIfLongerThan.setText(objJobDataProvider.getWarnIfLongerThan());
        txtWarnIfShorterThan.setText(objJobDataProvider.getWarnIfShorterThan());
        bForceIdletimeout.setSelection(objJobDataProvider.getForceIdletimeout());
    }

    public Button getbOrderYes() {
        return bOrderYes;
    }

    public Button getbOrderNo() {
        return bOrderNo;
    }

}