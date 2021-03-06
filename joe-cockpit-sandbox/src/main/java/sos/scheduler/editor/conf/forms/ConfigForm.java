package sos.scheduler.editor.conf.forms;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import sos.scheduler.editor.conf.listeners.ConfigListener;

import com.sos.joe.globals.interfaces.ISchedulerUpdate;
import com.sos.joe.globals.interfaces.IUpdateLanguage;
import com.sos.joe.globals.messages.SOSJOEMessageCodes;
import com.sos.joe.globals.misc.ResourceManager;
import com.sos.joe.globals.options.Options;
import com.sos.joe.xml.IOUtils;
import com.sos.joe.xml.jobscheduler.MergeAllXMLinDirectory;
import com.sos.joe.xml.jobscheduler.SchedulerDom;

/**
 * @author sky2000
 */
public class ConfigForm extends SOSJOEMessageCodes implements IUpdateLanguage {

	private Button			butBrowse_2					= null;

	private Combo			cConfigurationDeleteEvent	= null;

	@SuppressWarnings("unused")
	private Label			label12_3					= null;

	private Button			butBrowse_1					= null;

	private Combo			cConfigurationModifyEvent	= null;

	@SuppressWarnings("unused")
	private Label			label12_2					= null;

	private Button			butBrowse					= null;

	private Combo			cConfigurationAddEvent		= null;

	private Label			label12_1					= null;

	private ConfigListener	listener					= null;

	private Group			gConfig						= null;

	@SuppressWarnings("unused")
	private Label			label						= null;

	private Text			tSpoolerID					= null;

	@SuppressWarnings("unused")
	private Label			label7						= null;

	private Text			tParameter					= null;

	@SuppressWarnings("unused")
	private Label			label10						= null;

	private Text			tIncludePath				= null;

	@SuppressWarnings("unused")
	private Label			label11						= null;

	private Text			tLogDir						= null;

	@SuppressWarnings("unused")
	private Label			label12						= null;

	private Text			tMailXSLTStylesheet			= null;

	private Group			gPorts						= null;

	private Button			cSamePorts					= null;

	private Label			label14						= null;

	private Text			sPort						= null;

	private Text			sTcpPort					= null;

	@SuppressWarnings("unused")
	private Label			label4						= null;

	private Text			sUdpPort					= null;

	private Group			gMainScheduler				= null;

	private Label			label1						= null;

	private Text			tMainSchedulerHost			= null;

	private Label			label2						= null;

	private Text			sMainSchedulerPort			= null;

	private Group			gJavaOptions				= null;

	@SuppressWarnings("unused")
	private Label			label8						= null;

	private Text			tJavaClassPath				= null;

	@SuppressWarnings("unused")
	private Label			label9						= null;

	private Text			tJavaOptions				= null;

	private Group			group						= null;

	private Text			tComment					= null;

	private Text			tIpAddress					= null;

	private Button			button						= null;

	private Text			txtCentralConfigDir			= null;

	/**
	 * @param parent
	 * @param style
	 */
	public ConfigForm(final Composite parent, final int style, final SchedulerDom dom, final ISchedulerUpdate _update) {
		super(parent, style);
		listener = new ConfigListener(dom);
		initialize();
		setToolTipText();
		tSpoolerID.setFocus();

	}

	private void initialize() {
		this.setLayout(new FillLayout());
		createGConfig();
		setSize(new org.eclipse.swt.graphics.Point(714, 501));

		// set all values
		listener.getDom().setInit(true);
		tSpoolerID.setText(listener.getSpoolerID());
		tParameter.setText(listener.getParam());
		tIncludePath.setText(listener.getIncludePath());
		tIpAddress.setText(listener.getIpAddress());
		tLogDir.setText(listener.getLogDir());
		tMailXSLTStylesheet.setText(listener.getMailXSLTStylesheet());
		txtCentralConfigDir.setText(listener.getCentralConfigDir());

		cSamePorts.setSelection(listener.isPort());
		if (listener.isPort()) {
			sPort.setText(listener.getPort());
		}
		else {
			sTcpPort.setText(listener.getTcpPort());
			sUdpPort.setText(listener.getUdpPort());
		}

		tMainSchedulerHost.setText(listener.getMainSchedulerHost());
		sMainSchedulerPort.setText(listener.getMainSchedulerPort());

		tJavaClassPath.setText(listener.getJavaClasspath());
		tJavaOptions.setText(listener.getJavaOptions());

		tComment.setText(listener.getComment());

		setEditable();
		listener.getDom().setInit(false);
		tSpoolerID.setFocus();
	}

	private void setEditable() {
		sPort.setEnabled(cSamePorts.getSelection());
		sTcpPort.setEnabled(!cSamePorts.getSelection());
		sUdpPort.setEnabled(!cSamePorts.getSelection());
		if (!sPort.getEnabled() && sTcpPort.getText().equals("") && sUdpPort.getText().equals("")) {
			sTcpPort.setText(sPort.getText());
			sUdpPort.setText(sPort.getText());
		}
		if (sPort.getEnabled() && sTcpPort.getText().equals(sUdpPort.getText()) && !sTcpPort.getText().equals("")) {
			String s = sTcpPort.getText();
			sTcpPort.setText("");
			sUdpPort.setText("");
			sPort.setText(s);
		}
	}

	/**
	 * This method initializes gConfig
	 */
	private void createGConfig() {
		GridLayout gridLayout1 = new GridLayout();
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;

		gConfig = JOE_G_ConfigForm_Config.Control(new Group(this, SWT.NONE));
		gConfig.setLayout(gridLayout1);
		gConfig.setSize(new Point(798, 516));

		final Group group_1 = JOE_G_ConfigForm_Group1.Control(new Group(gConfig, SWT.NONE));
		group_1.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.numColumns = 2;
		group_1.setLayout(gridLayout_1);

		label = JOE_L_ConfigForm_SchedulerID.Control(new Label(group_1, SWT.NONE));

		tSpoolerID = JOE_T_ConfigForm_SchedulerID.Control(new Text(group_1, SWT.BORDER));
		tSpoolerID.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		tSpoolerID.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				listener.setSpoolerID(tSpoolerID.getText());
			}
		});

		label7 = JOE_L_ConfigForm_Params.Control(new Label(group_1, SWT.NONE));

		tParameter = JOE_T_ConfigForm_Params.Control(new Text(group_1, SWT.BORDER));
		tParameter.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		tParameter.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				listener.setParam(tParameter.getText());
			}
		});

		label10 = JOE_L_ConfigForm_IncludePath.Control(new Label(group_1, SWT.NONE));

		tIncludePath = JOE_T_ConfigForm_IncludePath.Control(new Text(group_1, SWT.BORDER));
		tIncludePath.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		tIncludePath.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				listener.setIncludePath(tIncludePath.getText());
			}
		});

		@SuppressWarnings("unused")
		final Label ipaddressLabel = JOE_L_ConfigForm_IPAddress.Control(new Label(group_1, SWT.NONE));

		tIpAddress = JOE_T_ConfigForm_IPAddress.Control(new Text(group_1, SWT.BORDER));
		tIpAddress.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				listener.setIpAddress(tIpAddress.getText());
			}
		});
		tIpAddress.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		label11 = JOE_L_ConfigForm_LogDir.Control(new Label(group_1, SWT.NONE));

		tLogDir = JOE_T_ConfigForm_LogDir.Control(new Text(group_1, SWT.BORDER));
		tLogDir.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		tLogDir.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				listener.setLogDir(tLogDir.getText());
			}
		});

		label12 = JOE_L_ConfigForm_MailXSLT.Control(new Label(group_1, SWT.NONE));

		tMailXSLTStylesheet = JOE_T_ConfigForm_MailXSLT.Control(new Text(group_1, SWT.BORDER));
		tMailXSLTStylesheet.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		tMailXSLTStylesheet.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				listener.setMailXSLTStylesheet(tMailXSLTStylesheet.getText());
			}
		});

		final Label centralConfigurationDirectoryLabel = JOE_L_ConfigForm_CentralConfigDir.Control(new Label(group_1, SWT.NONE));
		centralConfigurationDirectoryLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));

		txtCentralConfigDir = JOE_T_ConfigForm_CentralConfigDir.Control(new Text(group_1, SWT.BORDER));
		txtCentralConfigDir.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				listener.setCentralConfigDir(txtCentralConfigDir.getText());
			}
		});
		txtCentralConfigDir.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		createGPorts();
		createGMainScheduler();
		createGJavaOptions();
		createGroup();
	}

	/**
	 * This method initializes gPorts
	 */
	private void createGPorts() {
		GridData gridData42 = new GridData();
		gridData42.horizontalSpan = 2;
		gridData42.verticalAlignment = GridData.CENTER;
		gridData42.grabExcessHorizontalSpace = true;
		gridData42.horizontalAlignment = GridData.FILL;
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 6;

		final Group eventGroup = JOE_G_ConfigForm_Event.Control(new Group(gConfig, SWT.NONE));
		eventGroup.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false, 1, 2));
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		eventGroup.setLayout(gridLayout);

		label12_1 = JOE_L_ConfigForm_ConfigAddEvent.Control(new Label(eventGroup, SWT.NONE));
		final GridData gridData = new GridData(GridData.FILL, GridData.CENTER, false, false);
		gridData.widthHint = 153;
		label12_1.setLayoutData(gridData);

		cConfigurationAddEvent = JOE_Cbo_ConfigForm_ConfigAddEvent.Control(new Combo(eventGroup, SWT.NONE));
		cConfigurationAddEvent.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		cConfigurationAddEvent.setItems(listener.getJobs());
		cConfigurationAddEvent.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				listener.setConfigurationAddEvent(cConfigurationAddEvent.getText());
			}
		});
		cConfigurationAddEvent.setText(listener.getConfigurationAddEvent());

		butBrowse = JOE_B_ConfigForm_Browse1.Control(new Button(eventGroup, SWT.NONE));
		butBrowse.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
		butBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				String jobname = IOUtils.getJobschedulerObjectPathName(MergeAllXMLinDirectory.MASK_JOB);
				if (jobname != null && jobname.length() > 0) {
					cConfigurationAddEvent.setText(jobname);
				}
			}
		});

		label12_2 = JOE_L_ConfigForm_ConfigModifyEvent.Control(new Label(eventGroup, SWT.NONE));

		cConfigurationModifyEvent = JOE_Cbo_ConfigForm_ConfigModifyEvent.Control(new Combo(eventGroup, SWT.NONE));
		cConfigurationModifyEvent.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		cConfigurationModifyEvent.setItems(listener.getJobs());
		cConfigurationModifyEvent.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				listener.setConfigurationModifyEvent(cConfigurationModifyEvent.getText());
			}
		});
		cConfigurationModifyEvent.setText(listener.getConfigurationModifyEvent());

		butBrowse_1 = JOE_B_ConfigForm_Browse2.Control(new Button(eventGroup, SWT.NONE));
		butBrowse_1.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
		butBrowse_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				String jobname = IOUtils.getJobschedulerObjectPathName(MergeAllXMLinDirectory.MASK_JOB);
				if (jobname != null && jobname.length() > 0)
					cConfigurationModifyEvent.setText(jobname);
			}
		});

		label12_3 = JOE_L_ConfigForm_ConfigDeleteEvent.Control(new Label(eventGroup, SWT.NONE));

		cConfigurationDeleteEvent = JOE_Cbo_ConfigForm_ConfigDeleteEvent.Control(new Combo(eventGroup, SWT.NONE));
		cConfigurationDeleteEvent.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		cConfigurationDeleteEvent.setItems(listener.getJobs());
		cConfigurationDeleteEvent.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				listener.setConfigurationDeleteEvent(cConfigurationDeleteEvent.getText());
			}
		});
		cConfigurationDeleteEvent.setText(listener.getConfigurationDeleteEvent());

		butBrowse_2 = JOE_B_ConfigForm_Browse3.Control(new Button(eventGroup, SWT.NONE));
		butBrowse_2.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
		butBrowse_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				String jobname = IOUtils.getJobschedulerObjectPathName(MergeAllXMLinDirectory.MASK_JOB);
				if (jobname != null && jobname.length() > 0)
					cConfigurationDeleteEvent.setText(jobname);
			}
		});
	}

	/**
	 * This method initializes gMainScheduler
	 */
	private void createGMainScheduler() {
		GridData gridData18 = new GridData();
		gridData18.horizontalSpan = 2;
		gridData18.verticalAlignment = GridData.CENTER;
		gridData18.grabExcessHorizontalSpace = true;
		gridData18.horizontalAlignment = GridData.FILL;
		GridLayout gridLayout3 = new GridLayout();
		gridLayout3.numColumns = 4;
	}

	/**
	 * This method initializes gJavaOptions
	 */
	private void createGJavaOptions() {
		GridData gridData24 = new GridData();
		gridData24.horizontalIndent = 9;
		gridData24.horizontalAlignment = GridData.FILL;
		gridData24.grabExcessHorizontalSpace = true;
		gridData24.verticalAlignment = GridData.CENTER;
		GridData gridData22 = new GridData();
		gridData22.horizontalIndent = 9;
		gridData22.horizontalAlignment = GridData.FILL;
		gridData22.grabExcessHorizontalSpace = true;
		gridData22.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout8 = new GridLayout();
		gridLayout8.numColumns = 2;
		GridData gridData19 = new GridData();
		gridData19.horizontalAlignment = GridData.FILL;
		gridData19.verticalAlignment = GridData.CENTER;
		gridData19.horizontalSpan = 2;
		gridData19.grabExcessHorizontalSpace = true;
		GridLayout gridLayout6 = new GridLayout();
		gridLayout6.numColumns = 2;

		final Composite composite = JOE_Cmp_ConfigForm_CmpPort.Control(new Composite(gConfig, SWT.NONE));
		final GridData gridData = new GridData(GridData.FILL, GridData.BEGINNING, true, false);
		gridData.heightHint = 67;
		composite.setLayoutData(gridData);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 0;
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		gridLayout.numColumns = 2;
		composite.setLayout(gridLayout);
		GridLayout gridLayout11 = new GridLayout();
		gridLayout11.marginWidth = 0;
		gridLayout11.numColumns = 6;

		gPorts = JOE_G_ConfigForm_Ports.Control(new Group(composite, SWT.NONE));
		final GridData gridData_2 = new GridData(GridData.FILL, GridData.FILL, false, true);
		gridData_2.heightHint = 60;
		gPorts.setLayoutData(gridData_2);
		gPorts.setLayout(gridLayout11);
		GridData gridData3 = new GridData(GridData.FILL, GridData.CENTER, false, false, 6, 1);
		gridData3.horizontalIndent = 5;

		cSamePorts = JOE_B_ConfigForm_SamePortsCheckBtn.Control(new Button(gPorts, SWT.CHECK));
		cSamePorts.setLayoutData(gridData3);
		cSamePorts.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			@Override
			public void widgetSelected(final org.eclipse.swt.events.SelectionEvent e) {
				setEditable();
			}
		});

		label14 = JOE_L_ConfigForm_SchedulerPort.Control(new Label(gPorts, SWT.NONE));
		final GridData gridData_5 = new GridData(30, SWT.DEFAULT);
		gridData_5.horizontalIndent = 5;
		label14.setLayoutData(gridData_5);

		sPort = JOE_T_ConfigForm_SamePort.Control(new Text(gPorts, SWT.BORDER));
		sPort.setLayoutData(new GridData(60, SWT.DEFAULT));
		sPort.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				listener.setPort(sPort.getText());
			}
		});
		GridData gridData5 = new GridData(60, SWT.DEFAULT);
		gridData5.horizontalIndent = 10;

		final Label tcpLabel = JOE_L_ConfigForm_TCP.Control(new Label(gPorts, SWT.NONE));
		final GridData gridData_6 = new GridData();
		gridData_6.horizontalIndent = 10;
		tcpLabel.setLayoutData(gridData_6);

		sTcpPort = JOE_T_ConfigForm_TCP.Control(new Text(gPorts, SWT.BORDER));
		sTcpPort.setLayoutData(gridData5);
		sTcpPort.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				listener.setTcpPort(sTcpPort.getText());
			}
		});

		label4 = JOE_L_ConfigForm_UDP.Control(new Label(gPorts, SWT.NONE));

		sUdpPort = JOE_T_ConfigForm_UDP.Control(new Text(gPorts, SWT.BORDER));
		sUdpPort.setLayoutData(new GridData(60, SWT.DEFAULT));
		sUdpPort.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				listener.setUdpPort(sUdpPort.getText());
			}
		});

		GridLayout gridLayout7 = new GridLayout();
		gridLayout7.numColumns = 4;
		GridData gridData9 = new GridData();
		gridData9.horizontalSpan = 4;
		GridLayout gridLayout4 = new GridLayout();
		gridLayout4.numColumns = 4;

		gMainScheduler = JOE_G_ConfigForm_Supervisor.Control(new Group(composite, SWT.NONE));
		gMainScheduler.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		gMainScheduler.setLayout(gridLayout7);

		label1 = JOE_L_ConfigForm_Host.Control(new Label(gMainScheduler, SWT.NONE));
		label1.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, true));
		GridData gridData6 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData6.widthHint = 58;

		tMainSchedulerHost = JOE_T_ConfigForm_Host.Control(new Text(gMainScheduler, SWT.BORDER));
		tMainSchedulerHost.setLayoutData(gridData6);
		tMainSchedulerHost.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				listener.setMainScheduler(tMainSchedulerHost.getText() + ":" + listener.getMainSchedulerPort());
			}
		});

		label2 = JOE_L_ConfigForm_SupervisorPort.Control(new Label(gMainScheduler, SWT.NONE));
		label2.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, true));
		GridData gridData11 = new GridData(GridData.FILL, GridData.CENTER, false, true);
		gridData11.widthHint = 47;

		sMainSchedulerPort = JOE_T_ConfigForm_SupervisorPort.Control(new Text(gMainScheduler, SWT.BORDER));
		sMainSchedulerPort.setLayoutData(gridData11);
		sMainSchedulerPort.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				if (sMainSchedulerPort.getText().equals(""))
					sMainSchedulerPort.setBackground(Options.getRequiredColor());
				else
					sMainSchedulerPort.setBackground(null);
				listener.setMainScheduler(listener.getMainSchedulerHost() + ":" + sMainSchedulerPort.getText());
			}
		});

		gJavaOptions = JOE_G_ConfigForm_JavaOptions.Control(new Group(gConfig, SWT.NONE));
		gJavaOptions.setLayout(gridLayout8);
		gJavaOptions.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		label8 = JOE_L_ConfigForm_ClassPath.Control(new Label(gJavaOptions, SWT.NONE));

		tJavaClassPath = JOE_T_ConfigForm_ClassPath.Control(new Text(gJavaOptions, SWT.BORDER));
		tJavaClassPath.setLayoutData(gridData22);
		tJavaClassPath.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				listener.setJavaClasspath(tJavaClassPath.getText());
			}
		});

		label9 = JOE_L_ConfigForm_Options.Control(new Label(gJavaOptions, SWT.NONE));

		tJavaOptions = JOE_T_ConfigForm_Options.Control(new Text(gJavaOptions, SWT.BORDER));
		tJavaOptions.setLayoutData(gridData24);
		tJavaOptions.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				listener.setJavaOptions(tJavaOptions.getText());
			}
		});
	}

	/**
	 * This method initializes group
	 */
	private void createGroup() {
		group = JOE_G_ConfigForm_Comment.Control(new Group(gConfig, SWT.NONE));
		group.setLayoutData(new org.eclipse.swt.layout.GridData(GridData.FILL, GridData.FILL, true, true));
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		group.setLayout(gridLayout);

		button = JOE_B_ConfigForm_Comment.Control(new Button(group, SWT.NONE));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				String text = com.sos.joe.xml.Utils.showClipboard(tComment.getText(), getShell(), true, "");
				if (text != null)
					tComment.setText(text);
			}
		});
		button.setImage(ResourceManager.getImageFromResource("/sos/scheduler/editor/icon_edit.gif"));

		tComment = JOE_T_ConfigForm_Comment.Control(new Text(group, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER | SWT.H_SCROLL));
		tComment.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent e) {
				if (e.keyCode == 97 && e.stateMask == SWT.CTRL) {
					tComment.setSelection(0, tComment.getText().length());
				}
			}
		});
		tComment.setLayoutData(new org.eclipse.swt.layout.GridData(GridData.FILL, GridData.FILL, true, true, 1, 2));
		tComment.setFont(ResourceManager.getFont("Courier New", 8, SWT.NONE));
		tComment.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				listener.setComment(tComment.getText());
			}
		});
		new Label(group, SWT.NONE);
	}

	@Override
	public void setToolTipText() {
//
	}
} // @jve:decl-index=0:visual-constraint="10,10"
