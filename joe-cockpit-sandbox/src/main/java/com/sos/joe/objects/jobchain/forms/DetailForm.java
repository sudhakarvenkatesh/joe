package com.sos.joe.objects.jobchain.forms;
import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.jdom.Element;
import org.jdom.xpath.XPath;

import sos.scheduler.editor.app.JOEMainWindow;
import com.sos.joe.xml.Utils;
import sos.scheduler.editor.classes.ISOSTableMenueListeners;
import sos.scheduler.editor.classes.SOSComboBox;
import sos.scheduler.editor.classes.SOSTable;
import sos.scheduler.editor.conf.forms.DetailXMLEditorDialogForm;
import sos.scheduler.editor.conf.listeners.DetailsListener;
import sos.util.SOSClassUtil;

import com.sos.dialog.classes.WindowsSaver;
import com.sos.dialog.swtdesigner.SWTResourceManager;
import com.sos.joe.globals.JOEConstants;
import com.sos.joe.globals.interfaces.IDetailUpdate;
import com.sos.joe.globals.interfaces.ISchedulerUpdate;
import com.sos.joe.globals.interfaces.IUpdateLanguage;
import com.sos.joe.globals.messages.ErrorLog;
import com.sos.joe.globals.messages.SOSJOEMessageCodes;
import com.sos.joe.globals.misc.ResourceManager;
import com.sos.joe.globals.options.Options;
import com.sos.joe.objects.job.JobListener;
import com.sos.joe.objects.jobchain.JobChainConfigurationListener;
import com.sos.joe.wizard.forms.JobAssistentImportJobsForm;
import com.sos.joe.xml.jobscheduler.DetailDom;
import com.sos.joe.xml.jobscheduler.SchedulerDom;

public class DetailForm extends SOSJOEMessageCodes implements IUpdateLanguage, ISOSTableMenueListeners {
	@SuppressWarnings("unused") private final String	conSVNVersion				= "$Id$";
	private static final Logger							logger						= Logger.getLogger(DetailForm.class);
	private Button										butDown						= null;
	private Button										butUp						= null;
	private String										jobChainname				= "";
	private DetailsListener								detailListener				= null;
	private String										state						= null;
	private SOSComboBox									comboLanguage				= null;
	private Text										txtJobchainNote				= null;
	private Button										butApply					= null;
	private Text										txtName						= null;
	private Text										txtValue					= null;
	private SOSTable									tableParams					= null;
	private Button										butApplyParam				= null;
	private Button										butRemove					= null;
	private Button										cancelButton				= null;
	private Text										txtParamNote				= null;
	private Group										parameterGroup				= null;
	private Group										jobChainGroup				= null;
	// private Button butOpen = null;
	/** Hifsvariable, wann der butApply enabled bzw. disabled gesetzt werden soll*/
	private boolean										isEditable					= false;
	/** Hifsvariable, wann der butApplyParam enabled bzw. disabled gesetzt werden soll*/
	private boolean										isEditableParam				= false;
	private Label										statusBar					= null;
	/** wer hat ihn aufgerufen*/
	private int											type						= -1;
	private Tree										tree						= null;
	private Composite									parent						= null;
	private JobChainConfigurationListener				confListener				= null;
	private DetailDom									dom							= null;
	private IDetailUpdate								gui							= null;
	private Button										butXML						= null;
	private Button										butDocumentation			= null;
	private Text										paramText					= null;
	private Button										butText						= null;
	private Text										txtParamsFile				= null;
	private boolean										isLifeElement				= false;
	private String										path						= null;
	private String										_orderId					= null;
	// wird nur f�r wizzard verwendet
	private ISchedulerUpdate							update						= null;
	private SchedulerDom								schedulerDom				= null;
	// Verwendung in Wizzard
	private Text										butRefreshWizzardNoteParam	= null;
	private final JobListener							joblistener					= null;
	private String										jobname						= "";
	private final String								jobDocumentation			= null;
	private final WindowsSaver							w;

	public DetailForm(final Composite parent_, final int style, final int type_, final DetailDom dom_, final IDetailUpdate gui_, final boolean isLifeElement_,
			final String path_) {
		super(parent_, style);
		w = new WindowsSaver(this.getClass(), getShell(), 643, 600);
		dom = dom_;
		gui = gui_;
		type = type_;
		initialize();
		setToolTipText();
		parent = parent_;
		isLifeElement = isLifeElement_;
		path = path_;
	}

	private void saveWindowPosAndSize() {
		w.saveWindow();
	}

	/**
	 * @wbp.parser.constructor
	 */
	public DetailForm(final Composite parent_, final int style, final String jobChainname_, final String state_, final String orderId, final int type_,
			final DetailDom dom_, final IDetailUpdate gui_, final boolean isLifeElement_, final String path_) {
		super(parent_, style);
		dom = dom_;
		gui = gui_;
		type = type_;
		jobChainname = jobChainname_;
		state = state_;
		_orderId = orderId;
		w = new WindowsSaver(this.getClass(), getShell(), 643, 600);
		w.restoreWindowLocation();
		initialize();
		w.restoreWindowSize();
		parent = parent_;
		isLifeElement = isLifeElement_;
		path = path_;
		logger.debug("DeailForm before open");
		open();
	}

	private void initialize() {
		this.setLayout(new FillLayout());
		createGroup();
		if (type == JOEConstants.JOB_CHAINS) {
			getShell().layout();
			getShell().open();
		}
	}

	private void createGroup() {
		try {
			final GridLayout gridLayout_3 = new GridLayout();
			gridLayout_3.verticalSpacing = 3;
			gridLayout_3.marginWidth = 0;
			gridLayout_3.marginTop = 0;
			gridLayout_3.marginRight = 0;
			gridLayout_3.marginLeft = 0;
			gridLayout_3.marginHeight = 0;
			gridLayout_3.marginBottom = 0;
			gridLayout_3.numColumns = 3;
			final Group composite = JOE_G_DetailForm_MainGroup.Control(new Group(this, SWT.NONE));
			composite.addDisposeListener(new DisposeListener() {
				@Override public void widgetDisposed(final DisposeEvent e) {
					if (butApply.isEnabled()) {
						save();
					}
				}
			});
			composite.setLayout(new GridLayout());
			final GridData gridData_6 = new GridData(GridData.FILL, GridData.CENTER, true, true, 3, 1);
			gridData_6.heightHint = 31;
			composite.setLayoutData(gridData_6);
			parameterGroup = JOE_G_DetailForm_ParameterGroup.Control(new Group(composite, SWT.NONE));
			parameterGroup.setEnabled(false);
			//			parameterGroup.setText("Detail Parameter");
			final GridData gridData_3 = new GridData(GridData.FILL, GridData.FILL, true, true);
			gridData_3.heightHint = 239;
			parameterGroup.setLayoutData(gridData_3);
			final GridLayout gridLayout_2 = new GridLayout();
			gridLayout_2.numColumns = 6;
			parameterGroup.setLayout(gridLayout_2);
			@SuppressWarnings("unused") final Label nameLabel = JOE_L_Name.Control(new Label(parameterGroup, SWT.NONE));
			//			nameLabel.setText("Name");
			txtName = JOE_T_DetailForm_Name.Control(new Text(parameterGroup, SWT.BORDER));
			//			txtName.addFocusListener(new FocusAdapter() {
			//				public void focusGained(final FocusEvent e) {
			//					txtName.selectAll();
			//				}
			//			});
			txtName.addModifyListener(new ModifyListener() {
				@Override public void modifyText(final ModifyEvent e) {
					if (!txtName.getText().equals("")
							&& (tableParams.getSelectionCount() == 0 || tableParams.getSelectionCount() > 0
									&& !tableParams.getSelection()[0].getText(0).equalsIgnoreCase(txtName.getText()))) {
						isEditableParam = true;
						butApplyParam.setEnabled(isEditableParam);
						txtValue.setEnabled(true);
						butText.setEnabled(true);
						paramText.setText("");
						txtParamNote.setEnabled(true);
					}
					else {
						butText.setEnabled(false);
					}
				}
			});
			txtName.addKeyListener(new KeyAdapter() {
				@Override public void keyPressed(final KeyEvent e) {
					if (e.keyCode == SWT.CR && !txtName.getText().equals("")) {
						addParam();
					}
				}
			});
			txtName.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
			txtName.setFocus();
			@SuppressWarnings("unused") final Label valueLabel = JOE_L_Value.Control(new Label(parameterGroup, SWT.NONE));
			//			valueLabel.setText("Value");
			txtValue = JOE_T_DetailForm_Value.Control(new Text(parameterGroup, SWT.BORDER));
			//			txtValue.addFocusListener(new FocusAdapter() {
			//				public void focusGained(final FocusEvent e) {
			//					txtValue.selectAll();
			//				}
			//			});
			txtValue.addKeyListener(new KeyAdapter() {
				@Override public void keyPressed(final KeyEvent e) {
					if (e.keyCode == SWT.CR && !txtName.getText().equals("")) {
						addParam();
					}
				}
			});
			txtValue.addModifyListener(new ModifyListener() {
				@Override public void modifyText(final ModifyEvent e) {
					if (!txtName.getText().equals("")
							&& (tableParams.getSelectionCount() == 0 || tableParams.getSelectionCount() > 0
									&& !tableParams.getSelection()[0].getText(1).equalsIgnoreCase(txtValue.getText()))) {
						isEditableParam = true;
						butApplyParam.setEnabled(isEditableParam);
						if (txtValue.getText().trim().length() > 0)
							butText.setEnabled(false);
						else
							butText.setEnabled(true);
					}
				}
			});
			txtValue.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
			butText = JOE_B_DetailForm_Text.Control(new Button(parameterGroup, SWT.NONE));
			butText.setEnabled(false);
			butText.addSelectionListener(new SelectionAdapter() {
				@Override public void widgetSelected(final SelectionEvent e) {
					String ntext = "";
					if (tableParams.getSelectionCount() > 0) {
						TableItem item = tableParams.getSelection()[0];
						ntext = item.getText(2);
					}
					String text = com.sos.joe.xml.Utils.showClipboard(ntext, getShell(), true, "");
					if (text != null && !text.trim().equalsIgnoreCase(ntext)) {
						paramText.setText(text);
						txtValue.setText("");
						txtValue.setEnabled(false);
						butText.setEnabled(true);
						addParam();
					}
					else
						if (text == null) {
							txtValue.setEnabled(true);
							butText.setEnabled(true);
						}
						else {
							txtValue.setEnabled(true);
							butText.setEnabled(false);
						}
					butApply.setEnabled(true);
				}
			});
			//			butText.setText("Text");
			butApplyParam = JOE_B_DetailForm_ApplyParam.Control(new Button(parameterGroup, SWT.NONE));
			butApplyParam.setEnabled(isEditableParam);
			butApplyParam.addSelectionListener(new SelectionAdapter() {
				@Override public void widgetSelected(final SelectionEvent e) {
					addParam();
				}
			});
			final GridData gridData_9 = new GridData(GridData.FILL, GridData.BEGINNING, false, false);
			butApplyParam.setLayoutData(gridData_9);
			//			butApplyParam.setText("Apply");
			tableParams = (SOSTable) JOE_Tbl_DetailForm_Params.Control(new SOSTable(parameterGroup, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER, this));
			tableParams.setEnabled(false);
			tableParams.addSelectionListener(new SelectionAdapter() {
				@Override public void widgetSelected(final SelectionEvent e) {
					if (tableParams.getSelectionCount() > 0) {
						TableItem item = tableParams.getSelection()[0];
						txtName.setText(item.getText(0));
						// param value ist angegeben
						if (item.getText(1) != null && item.getText(1).trim().length() > 0) {
							paramText.setText("");
							txtValue.setText(item.getText(1));
							txtValue.setEnabled(true);
							butText.setEnabled(false);
						}
						// param Textknoten ist angegeben
						if (item.getText(2) != null && item.getText(2).trim().length() > 0) {
							paramText.setText(item.getText(2));
							txtValue.setText("");
							txtValue.setEnabled(false);
							butText.setEnabled(true);
						}
						if (item.getText(1).trim().equals("") && item.getText(2).trim().equals("")) {
							paramText.setText("");
							txtValue.setText("");
							txtValue.setEnabled(true);
							butText.setEnabled(true);
						}
						txtParamNote.setText(detailListener.getParamNote(item.getText(0), comboLanguage.getText()));
						butRemove.setEnabled(true);
						txtParamNote.setEnabled(true);
						isEditableParam = false;
					}
					else {
						butRemove.setEnabled(false);
					}
				}
			});
			tableParams.setLinesVisible(true);
			tableParams.setHeaderVisible(true);
			final GridData gridData_4 = new GridData(GridData.FILL, GridData.FILL, true, true, 5, 7);
			tableParams.setLayoutData(gridData_4);
			final TableColumn newColumnTableColumn = JOE_TCl_DetailForm_NameColumn.Control(new TableColumn(tableParams, SWT.NONE));
			newColumnTableColumn.setWidth(118);
			newColumnTableColumn.addControlListener(new ControlAdapter() {
				@Override public void controlResized(final ControlEvent e) {
					w.saveTableColumn("tableParams", newColumnTableColumn);
				}
			});
			w.restoreTableColumn("tableParams", newColumnTableColumn, 118);
			final TableColumn newColumnTableColumn_1 = JOE_TCl_DetailForm_ValueColumn.Control(new TableColumn(tableParams, SWT.NONE));
			//			newColumnTableColumn_1.setText("Value");
			newColumnTableColumn_1.setWidth(150);
			newColumnTableColumn_1.addControlListener(new ControlAdapter() {
				@Override public void controlResized(final ControlEvent e) {
					w.saveTableColumn("tableParams", newColumnTableColumn_1);
				}
			});
			w.restoreTableColumn("tableParams", newColumnTableColumn_1, 150);
			final TableColumn newColumnTableColumn_2 = JOE_TCl_DetailForm_TextColumn.Control(new TableColumn(tableParams, SWT.NONE));
			//			newColumnTableColumn_2.setText("Text");
			newColumnTableColumn_2.setWidth(100);
			newColumnTableColumn_2.addControlListener(new ControlAdapter() {
				@Override public void controlResized(final ControlEvent e) {
					w.saveTableColumn("tableParams", newColumnTableColumn_2);
				}
			});
			w.restoreTableColumn("tableParams", newColumnTableColumn_2, 100);
			final Button butNew = JOE_B_DetailForm_New.Control(new Button(parameterGroup, SWT.NONE));
			butNew.addSelectionListener(new SelectionAdapter() {
				@Override public void widgetSelected(final SelectionEvent e) {
					txtName.setText("");
					txtValue.setText("");
					paramText.setText("");
					txtValue.setEnabled(true);
					paramText.setEnabled(true);
					tableParams.deselectAll();
					txtParamNote.setText("");
				}
			});
			butNew.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
			//			butNew.setText("New");
			final Composite composite_2 = JOE_Composite1.Control(new Composite(parameterGroup, SWT.NONE));
			final GridData gridData_2_1 = new GridData(GridData.CENTER, GridData.CENTER, false, false);
			gridData_2_1.heightHint = 67;
			composite_2.setLayoutData(gridData_2_1);
			composite_2.setLayout(new GridLayout());
			butUp = JOE_B_DetailForm_Up.Control(new Button(composite_2, SWT.NONE));
			butUp.addSelectionListener(new SelectionAdapter() {
				@Override public void widgetSelected(final SelectionEvent e) {
					detailListener.changeUp(tableParams);
				}
			});
			butUp.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
			butUp.setImage(ResourceManager.getImageFromResource("/sos/scheduler/editor/icon_up.gif"));
			butDown = JOE_B_DetailForm_Down.Control(new Button(composite_2, SWT.NONE));
			butDown.addSelectionListener(new SelectionAdapter() {
				@Override public void widgetSelected(final SelectionEvent e) {
					detailListener.changeDown(tableParams);
				}
			});
			butDown.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, false, false));
			butDown.setImage(ResourceManager.getImageFromResource("/sos/scheduler/editor/icon_down.gif"));
			final Button parameterButton = JOE_B_DetailForm_Wizard.Control(new Button(parameterGroup, SWT.NONE));
			parameterButton.addSelectionListener(new SelectionAdapter() {
				@Override public void widgetSelected(final SelectionEvent e) {
					startWizzard();
				}
			});
			// parameterButton.setVisible(type != JOEConstants.DETAILS);
			parameterButton.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
			butRemove = JOE_B_DetailForm_Remove.Control(new Button(parameterGroup, SWT.NONE));
			butRemove.addSelectionListener(new SelectionAdapter() {
				@Override public void widgetSelected(final SelectionEvent e) {
					if (tableParams.getSelectionCount() > 0) {
						detailListener.deleteParameter(tableParams, tableParams.getSelectionIndex());
						txtParamNote.setText("");
						txtName.setText("");
						txtValue.setText("");
						tableParams.deselectAll();
						butRemove.setEnabled(false);
						txtParamNote.setText("");
						isEditableParam = false;
						butApplyParam.setEnabled(isEditableParam);
						butApply.setEnabled(isEditable);
						txtName.setFocus();
						if (gui != null)
							gui.updateParam();
					}
				}
			});
			final GridData gridData_8 = new GridData(GridData.FILL, GridData.BEGINNING, false, true);
			gridData_8.widthHint = 64;
			gridData_8.minimumWidth = 50;
			butRemove.setLayoutData(gridData_8);
			//			butRemove.setText("Remove");
			final Button butTemp = JOE_B_DetailForm_TempDocumentation.Control(new Button(parameterGroup, SWT.NONE));
			butTemp.setLayoutData(new GridData());
			butTemp.setVisible(false);
			butApply = JOE_B_DetailForm_ApplyDetails.Control(new Button(parameterGroup, SWT.NONE));
			butApply.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
			butApply.setEnabled(isEditable);
			FontData fontDatas[] = butApply.getFont().getFontData();
			FontData data = fontDatas[0];
			//			butApply.setFont(new Fo nt(Display.getCurrent(), data.getName(), data.getHeight(), SWT.BOLD));
			butApply.setFont(SWTResourceManager.getFont(data.getName(), data.getHeight(), SWT.BOLD));
			butApply.addSelectionListener(new SelectionAdapter() {
				@Override public void widgetSelected(final SelectionEvent e) {
					save();
				}
			});
			cancelButton = JOE_B_DetailForm_Cancel.Control(new Button(parameterGroup, SWT.NONE));
			cancelButton.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
			cancelButton.addSelectionListener(new SelectionAdapter() {
				@Override public void widgetSelected(final SelectionEvent e) {
					saveWindowPosAndSize();
					if (butApply.getEnabled()) {
						//						int count = MainWindow.message(getShell(), com.sos.joe.globals.messages.Messages.getLabel("detailform.close"), SWT.ICON_WARNING | SWT.OK
						int count = JOEMainWindow.message(getShell(), JOE_M_0008.label(), SWT.ICON_WARNING | SWT.OK | SWT.CANCEL);
						if (count != SWT.OK) {
							return;
						}
					}
					getShell().dispose();
				}
			});
			txtParamNote = JOE_T_DetailForm_JobChainNote.Control(new Text(parameterGroup, SWT.V_SCROLL | SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.H_SCROLL));
			txtParamNote.setEnabled(false);
			txtParamNote.addVerifyListener(new VerifyListener() {
				@Override public void verifyText(final VerifyEvent e) {
					if (e.keyCode == 8 || e.keyCode == 127) {
						isEditableParam = true;
						butApplyParam.setEnabled(isEditableParam);
					}
				}
			});
			txtParamNote.addModifyListener(new ModifyListener() {
				@Override public void modifyText(final ModifyEvent e) {
					changeParameNote();
				}
			});
			final GridData gridData_5 = new GridData(GridData.FILL, GridData.FILL, true, true, 5, 3);
			gridData_5.heightHint = 73;
			txtParamNote.setLayoutData(gridData_5);
			comboLanguage = new SOSComboBox(parameterGroup, JOE_Cbo_DetailForm_Language);
			// TODO Languages aus Languages.properties lesen
			comboLanguage.setItems(new String[] { "de", "en" });
			final GridData gridData_7 = new GridData(GridData.FILL, GridData.BEGINNING, false, true);
			comboLanguage.setLayoutData(gridData_7);
			comboLanguage.addSelectionListener(new SelectionAdapter() {
				@Override public void widgetSelected(final SelectionEvent e) {
					txtJobchainNote.setText(detailListener.getNote(comboLanguage.getText()));
					if (tableParams.getSelectionCount() > 0) {
						TableItem item = tableParams.getSelection()[0];
						txtParamNote.setText(detailListener.getParamNote(item.getText(0), comboLanguage.getText()));
					}
					else
						if (txtName.getText() != null && txtName.getText().length() > 0) {
							txtParamNote.setText(detailListener.getParamNote(txtName.getText(), comboLanguage.getText()));
						}
						else
							if (txtParamNote.getText() != null && txtParamNote.getText().length() > 0) {
								txtParamNote.setText("");
							}
					isEditable = false;
					isEditableParam = false;
					// butApply.setEnabled(isEditable);
					butApplyParam.setEnabled(isEditableParam);
					butRemove.setEnabled(false);
				}
			});
			comboLanguage.select(0);
			butRefreshWizzardNoteParam = JOE_B_DetailForm_RefreshWizardNoteParam.Control(new Text(parameterGroup, SWT.CHECK));
			butRefreshWizzardNoteParam.addModifyListener(new ModifyListener() {
				@Override public void modifyText(final ModifyEvent e) {
					refreshTable();
				}
			});
			butRefreshWizzardNoteParam.setVisible(false);
			butRefreshWizzardNoteParam.setLayoutData(new GridData());
			paramText = JOE_T_DetailForm_Param.Control(new Text(parameterGroup, SWT.BORDER));
			paramText.setVisible(false);
			final GridData gridData_14 = new GridData(GridData.CENTER, GridData.BEGINNING, false, false);
			gridData_14.widthHint = 27;
			paramText.setLayoutData(gridData_14);
			jobChainGroup = JOE_G_DetailForm_NoteGroup.Control(new Group(parameterGroup, SWT.NONE));
			jobChainGroup.setEnabled(false);
			//			jobChainGroup.setText("Note");
			final GridLayout gridLayout_1 = new GridLayout();
			gridLayout_1.numColumns = 2;
			jobChainGroup.setLayout(gridLayout_1);
			final GridData gridData = new GridData(GridData.FILL, GridData.FILL, false, false, 6, 1);
			gridData.horizontalIndent = -1;
			jobChainGroup.setLayoutData(gridData);
			txtJobchainNote = JOE_T_DetailForm_JobChainNote.Control(new Text(jobChainGroup, SWT.V_SCROLL | SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.H_SCROLL));
			txtJobchainNote.addModifyListener(new ModifyListener() {
				@Override public void modifyText(final ModifyEvent e) {
					if (detailListener != null) {
						isEditable = true;
						if (gui != null)
							gui.updateNote();
						detailListener.setNote(txtJobchainNote.getText(), comboLanguage.getText());
						butApply.setEnabled(isEditable);
					}
				}
			});
			final GridData gridData_2 = new GridData(GridData.FILL, GridData.FILL, true, false, 1, 2);
			txtJobchainNote.setLayoutData(gridData_2);
			butXML = JOE_B_DetailForm_XML.Control(new Button(jobChainGroup, SWT.NONE));
			butXML.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
			butXML.setEnabled(false);
			butXML.addSelectionListener(new SelectionAdapter() {
				@Override public void widgetSelected(final SelectionEvent e) {
					try {
						if (dom != null && dom.isChanged()) {
							//							MainWindow.message("Please save jobchain configuration file before opening XML JOEConstants.", SWT.ICON_ERROR);
							JOEMainWindow.message(JOE_M_0020.label(), SWT.ICON_ERROR);
							return;
						}
						if (dom == null && butApply.isEnabled()) {
							// ungespeichert
							//							int c = MainWindow.message("Should the current values be saved?", SWT.YES | SWT.NO | SWT.ICON_ERROR);
							int c = JOEMainWindow.message(JOE_M_0021.label(), SWT.YES | SWT.NO | SWT.ICON_ERROR);
							if (c == SWT.YES)
								detailListener.save();
						}
						if (type == JOEConstants.JOB_CHAINS) {
							DetailXMLEditorDialogForm dialog = new DetailXMLEditorDialogForm(detailListener.getConfigurationFilename(), jobChainname, state,
									_orderId, type, isLifeElement, path);
							dialog.showXMLEditor();
							getShell().dispose();
						}
						else {
							if (dom != null && dom.getFilename() != null && dom.getFilename().length() > 0) {
								DetailXMLEditorDialogForm dialog = new DetailXMLEditorDialogForm(dom, type, isLifeElement, path);
								dialog.setConfigurationData(confListener, tree, parent);
								dialog.showXMLEditor();
							}
							else {
								//								MainWindow.message("Please save jobchain configuration file before opening XML JOEConstants.", SWT.ICON_ERROR);
								JOEMainWindow.message(JOE_M_0020.label(), SWT.ICON_ERROR);
							}
						}
					}
					catch (Exception ex) {
						try {
							//							System.out.println("..error in " + SOSClassUtil.getMethodName() + ": " + ex.getMessage());
							System.out.println(JOE_M_0010.params(SOSClassUtil.getMethodName(), ex.getMessage()));
							new ErrorLog(JOE_M_0002.params(SOSClassUtil.getMethodName()), ex);
						}
						catch (Exception ee) {
							// tu nichts
						}
					}
				}
			});
			//			butXML.setText("Open XML");
			butDocumentation = JOE_B_DetailForm_Documentation.Control(new Button(jobChainGroup, SWT.NONE));
			butDocumentation.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
			butDocumentation.addSelectionListener(new SelectionAdapter() {
				@Override public void widgetSelected(final SelectionEvent e) {
					String filename = null;
					try {
						if (type == JOEConstants.JOB_CHAINS) {
							filename = detailListener.getConfigurationFilename();
						}
						else {
							if (dom != null) {
								filename = dom.getFilename();
							}
						}
						if (filename != null && filename.length() > 0) {
							File file = new File(filename);
							if (file.exists()) {
								// Runtime.getRuntime().exec("cmd /C START iExplore ".concat(filename));
								Program prog = Program.findProgram("html");
								if (prog != null) {
									prog.execute(new File(filename).toURI().toURL().toString());
								}
								else {
									String[] split = Options.getBrowserExec(new File(filename).toURL().toString(), Options.getLanguage());
									Runtime.getRuntime().exec(split);
								}
							}
							else
								JOEMainWindow.message(JOE_M_0013.params(file.getCanonicalPath()), SWT.ICON_ERROR);
						}
						else
							JOEMainWindow.message(JOE_M_0012.label(), SWT.ICON_ERROR);
						//							MainWindow.message("Please save jobchain configuration before opening documentation.", SWT.ICON_ERROR);
					}
					catch (Exception ex) {
						logger.debug(JOE_M_0011.params(SOSClassUtil.getMethodName(), filename, ex.getMessage()));
						new ErrorLog(JOE_M_0011.params(SOSClassUtil.getMethodName(), filename, ex.getMessage()), ex);
					}
				}
			});
			//			butDocumentation.setText("Documentation");
			final Label fileLabel = JOE_L_DetailForm_JobDocumentation.Control(new Label(parameterGroup, SWT.NONE));
			fileLabel.setLayoutData(new GridData());
			//			fileLabel.setText("Job Documentation: ");
			txtParamsFile = JOE_T_DetailForm_ParamsFile.Control(new Text(parameterGroup, SWT.BORDER));
			//			txtParamsFile.addFocusListener(new FocusAdapter() {
			//				public void focusGained(final FocusEvent e) {
			//					txtParamsFile.selectAll();
			//				}
			//			});
			txtParamsFile.addModifyListener(new ModifyListener() {
				@Override public void modifyText(final ModifyEvent e) {
					detailListener.setParamsFileName(txtParamsFile.getText());
					if (gui != null)
						gui.updateNote();
					butApply.setEnabled(isEditable);
				}
			});
			txtParamsFile.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 4, 1));
			statusBar = JOE_L_DetailForm_ConfigFile.Control(new Label(composite, SWT.BORDER));
			final GridData gridData_11 = new GridData(GridData.FILL, GridData.END, false, false);
			gridData_11.widthHint = 496;
			gridData_11.heightHint = 18;
			statusBar.setLayoutData(gridData_11);
			//			statusBar.setText("Configurations File:");
			setToolTipText();
			if (type == JOEConstants.JOB_CHAINS)
				setEnabled_(false);
			setVisibility();
		}
		catch (Exception e) {
			try {
				//				new ErrorLog("error in " + SOSClassUtil.getMethodName() + "cause: " + e.toString(), e);
				new ErrorLog(JOE_M_0010.params(SOSClassUtil.getMethodName(), e.toString()), e);
			}
			catch (Exception ee) {
			}
		}
	}

	private void setVisibility() {
		if (type == JOEConstants.DETAILS) {
			cancelButton.setVisible(false);
			statusBar.setVisible(false);
			butApply.setVisible(false);
		}
	}

	private void addParam() {
		if (txtName.getText().length() > 0) {
			detailListener.setParam(txtName.getText(), txtValue.getText(), txtParamNote.getText(), paramText.getText(), comboLanguage.getText());
			txtParamNote.setText(detailListener.getParamNote(txtName.getText(), comboLanguage.getText()));
			tableParams.removeAll();
			detailListener.fillParams(tableParams);
			butApply.setEnabled(isEditable);
			txtName.setText("");
			txtValue.setText("");
			isEditableParam = false;
			butApplyParam.setEnabled(isEditableParam);
			txtName.setFocus();
			isEditableParam = false;
			butApplyParam.setEnabled(isEditableParam);
			isEditable = true;
			butApply.setEnabled(isEditable);
			butRemove.setEnabled(false);
			txtParamNote.setText("");
			if (gui != null)
				gui.updateParam();
			paramText.setText("");
			txtParamNote.setEnabled(false);
		}
	}

	private boolean discardChanges() {
		if (butApply.getEnabled()) {
			//			int count = MainWindow.message(getShell(), com.sos.joe.globals.messages.Messages.getString("detailform.open"), SWT.ICON_WARNING | SWT.OK | SWT.CANCEL);
			int count = JOEMainWindow.message(getShell(), JOE_M_0022.label(), SWT.ICON_WARNING | SWT.OK | SWT.CANCEL);
			if (count != SWT.OK) {
				return false;
			}
		}
		setEnabled_(true);
		return true;
	}

	public void open(final String orderId) {
		open();
	}

	public void open() {
		if (!discardChanges())
			return;
		if (!initForm())
			return;
		isEditable = true;
		txtJobchainNote.setFocus();
		butXML.setEnabled(true);
		butApply.setEnabled(false);
		butApplyParam.setEnabled(false);
		if (detailListener != null && detailListener.getConfigurationFilename() != null) {
			//			statusBar.setText("Configurations File: " + detailListener.getConfigurationFilename());
			statusBar.setText(JOE_M_0023.params(detailListener.getConfigurationFilename()));
		}
		txtName.setFocus();
	}

	private boolean initForm() {
		tableParams.removeAll();
		txtParamNote.setText("");
		txtName.setText("");
		txtValue.setText("");
		detailListener = new DetailsListener(jobChainname, state, _orderId, type, dom, isLifeElement, path);
		if (detailListener != null && detailListener.hasError()) {
			if (type == JOEConstants.DETAILS)
				dispose();
			getShell().dispose();
			return false;
		}
		if (detailListener.getNote(comboLanguage.getText()).length() > 0)
			txtJobchainNote.setText(detailListener.getNote(comboLanguage.getText()));
		txtParamsFile.setText(detailListener.getParamsFileName());
		detailListener.fillParams(tableParams);
		butRemove.setEnabled(false);
		return true;
	}

	private void setEnabled_(final boolean enabled) {
		jobChainGroup.setEnabled(enabled);
		parameterGroup.setEnabled(enabled);
		comboLanguage.setEnabled(enabled);
		txtJobchainNote.setEnabled(enabled);
		txtName.setEnabled(enabled);
		txtValue.setEnabled(enabled);
		tableParams.setEnabled(enabled);
		txtParamNote.setEnabled(enabled);
		butRemove.setEnabled(enabled);
	}

	public void setTree(final Tree tree_) {
		tree = tree_;
	}

	public void setJobChainConfigurationListener(final JobChainConfigurationListener confListener_) {
		confListener = confListener_;
	}

	public boolean hasErrors() {
		if (detailListener != null) {
			return detailListener.hasError();
		}
		return false;
	}

	private void changeParameNote() {
		if (detailListener == null)
			return;
		// Wert auf leer zur�cksetzen
		if (txtParamNote.getText() != null && txtParamNote.getText().length() == 0)
			return;
		if (txtName.getText() != null && txtName.getText().length() == 0) {
			//			MainWindow.message(getShell(), com.sos.joe.globals.messages.Messages.getString("tooltip.detail.param.missing_param_name_for_note"), SWT.ICON_WARNING
			JOEMainWindow.message(getShell(), JOE_M_0024.label(), SWT.ICON_WARNING | SWT.OK | SWT.CANCEL);
			return;
		}
		if (tableParams.getSelectionCount() == 0 || tableParams.getSelectionCount() > 0
				&& !txtParamNote.getText().equalsIgnoreCase(detailListener.getParamNote(tableParams.getSelection()[0].getText(0), comboLanguage.getText()))) {
			detailListener.setParam(txtName.getText(), txtValue.getText(), txtParamNote.getText(), paramText.getText(), comboLanguage.getText());
			isEditableParam = true;
			butApplyParam.setEnabled(isEditableParam);
			isEditable = true;
			butApply.setEnabled(isEditable);
			if (gui != null)
				gui.updateParamNote();
		}
	}

	@Override public void setToolTipText() {
	}

	public DetailDom getDom() {
		return dom;
	}

	private void save() {
		saveWindowPosAndSize();
		if (butApplyParam.isEnabled()) {
			addParam();
		}
		detailListener.save();
		if (schedulerDom != null) {
			DetailsListener.addMonitoring2Job(jobChainname, state, schedulerDom, update);
			JOEMainWindow.getContainer().getCurrentTab().setData("ftp_details_parameter_file", detailListener.getConfigurationFilename());
			JOEMainWindow.saveFTP(new java.util.HashMap());
		}
		if (type == JOEConstants.JOB_CHAINS) {
			isEditable = false;
			butApply.setEnabled(isEditable);
			getShell().dispose();
		}
		else {
			isEditable = false;
			butApply.setEnabled(isEditable);
		}
	}

	public void setParamsForWizzard(final SchedulerDom dom_, final ISchedulerUpdate update_) {
		schedulerDom = dom_;
		update = update_;
	}

	public void setParamsForWizzard(final SchedulerDom dom_, final ISchedulerUpdate update_, final String jobname_) {
		schedulerDom = dom_;
		update = update_;
		jobname = jobname_;
		getJobDocumentation();
	}

	private void createTempSchedulerDom() {
		if (schedulerDom == null) {
			schedulerDom = new SchedulerDom();
		}
		//		CTabFolder folder = new CTabFolder(parent, SWT.TOP | SWT.CLOSE);
		// Sonst Nullpointer Exception wenn Parameter aus Wizzard eingetragen werden.
		// update = new SchedulerForm(MainWindow.getContainer(), folder, SWT.NONE);
		// TODO joblistener = new JobListener(schedulerDom, detailListener.getParams().getParentElement(), update);
	}

	private void startWizzard() {
		Utils.startCursor(getShell());
		butApply.setEnabled(true);
		// Liste aller Jobdokumentation
		try {
			if (joblistener == null)
				createTempSchedulerDom();
			getJobDocumentation();
			if (jobDocumentation != null && jobDocumentation.trim().length() > 0) {
				// JobDokumentation ist bekannt -> d.h Parameter aus dieser Jobdoku extrahieren
				// JobAssistentImportJobParamsForm paramsForm = new JobAssistentImportJobParamsForm(listener.get_dom(), listener.get_main(),
				// new JobListener(dom, listener.getParent(), listener.get_main()), tParameter, onlyParams ? JOEConstants.JOB :
				// JOEConstants.JOB_WIZARD);
				//	TODO			JobAssistentImportJobParamsForm paramsForm = new JobAssistentImportJobParamsForm(schedulerDom, joblistener.get_main(), joblistener,
				//						tableParams, JOEConstants.PARAMETER);
				//				paramsForm.showAllImportJobParams(jobDocumentation);
				//				paramsForm.setDetailsRefresh(butRefreshWizzardNoteParam);
			}
			else {
				JobAssistentImportJobsForm importParameterForms = new JobAssistentImportJobsForm(joblistener, tableParams, JOEConstants.PARAMETER);
				importParameterForms.showAllImportJobs();
				importParameterForms.setDetailsRefresh(butRefreshWizzardNoteParam);
			}
			butApply.setEnabled(true);
			if (dom != null)
				dom.setChanged(true);
		}
		catch (Exception e) {
			logger.debug(JOE_M_0010.params(SOSClassUtil.getMethodName(), e.getMessage()));
			new ErrorLog(JOE_M_0010.params(SOSClassUtil.getMethodName(), e.getMessage()), e);
		}
		finally {
			Utils.stopCursor(getShell());
		}
	}

	/*
	 * �berpr�ft, ob der Import Knoten die Parameter ver�ndert hat. Es geht um die Parameterbeschreibung.
	 * Die Parameter Beschreibung ist hier leider anders abgelegt als bei den anderen Parametern.
	 */
	private void refreshTable() {
		try {
			detailListener.refreshParams(tableParams);
			butApply.setEnabled(true);
		}
		catch (Exception e) {
			try {
				System.out.println(JOE_M_0010.params(SOSClassUtil.getMethodName(), e.getMessage()));
				new ErrorLog(JOE_M_0010.params(SOSClassUtil.getMethodName(), e.getMessage()), e);
			}
			catch (Exception ee) {
				// tu nichts
			}
		}
	}

	private void getJobDocumentation() {
		if (jobname != null && jobname.length() > 0) {
			try {
				XPath x = XPath.newInstance("//job[@name='" + jobname + "']/description/include");
				List listOfElement = x.selectNodes(schedulerDom.getDoc());
				if (!listOfElement.isEmpty()) {
					Element include = (Element) listOfElement.get(0);
					if (include != null) {
						//						jobDocumentation = Utils.getAttributeValue("file", include);
					}
				}
				if (jobDocumentation != null && jobDocumentation.length() > 0 && txtParamsFile != null) {
					txtParamsFile.setText(jobDocumentation);
				}
			}
			catch (Exception e) {
				System.out.println(JOE_M_0010.params("setParamsForWizzard", e.toString()));
			}
		}
	}

	@Override public Listener getNewListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override public Listener getDeleteListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override public Listener getCopyListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override public Listener getPasteListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override public Listener getInsertListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override public Listener getCutListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override public Listener getEditListener() {
		// TODO Auto-generated method stub
		return null;
	}
}