package com.sos.joe.objects.jobchain.forms;
 
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_Down;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_JCNestedNodesForm_AddMissingNodes;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_JCNestedNodesForm_EndNode;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_JCNestedNodesForm_FullNode;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_JCNestedNodesForm_Insert;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_JCNestedNodesForm_NewNode;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_JCNestedNodesForm_RemoveNode;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_JCNestedNodesForm_Reorder;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_JobChainForm_Parameter;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_JobChainNodes_ApplyNode;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_JobChainNodes_Browse;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_JobChainNodes_Goto;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_B_Up;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_Cbo_JCNestedNodesForm_JobChain;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_Cbo_JobChainNodes_ErrorState;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_Cbo_JobChainNodes_NextState;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_E_0002;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_L_JCNestedNodesForm_JobChain;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_L_JobChainNodes_ErrorState;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_L_JobChainNodes_NextState;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_L_JobChainNodes_State;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_M_JCNestedNodesForm_NestedNodes;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_M_JCNestedNodesForm_RemoveNode;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_M_JobChain_StateAlreadyDefined;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_TCl_JCNestedNodesForm_ErrorState;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_TCl_JCNestedNodesForm_JobChain;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_TCl_JCNestedNodesForm_NextState;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_TCl_JCNestedNodesForm_Node;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_TCl_JCNestedNodesForm_OnError;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_TCl_JCNestedNodesForm_State;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_T_JobChainNodes_State;
import static com.sos.joe.globals.messages.SOSJOEMessageCodes.JOE_Tbl_JCNestedNodesForm_Nodes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.jdom.Element;

import sos.scheduler.editor.app.ContextMenu;
import sos.scheduler.editor.app.JOEMainWindow;
import com.sos.joe.xml.Utils;
import sos.scheduler.editor.classes.CompositeBaseClass;
import sos.scheduler.editor.classes.ISOSTableMenueListeners;
import sos.scheduler.editor.classes.SOSComboBox;
import sos.scheduler.editor.classes.SOSTable;
import sos.scheduler.editor.conf.composites.CompositeBaseAbstract.enuOperationMode;
import sos.util.SOSClassUtil;

import com.sos.joe.globals.JOEConstants;
import com.sos.joe.globals.interfaces.ISchedulerUpdate;
import com.sos.joe.globals.interfaces.IUnsaved;
import com.sos.joe.globals.interfaces.IUpdateLanguage;
import com.sos.joe.globals.messages.ErrorLog;
import com.sos.joe.globals.misc.ResourceManager;
import com.sos.joe.objects.jobchain.JobChainNestedListener;
import com.sos.joe.xml.IOUtils;
import com.sos.joe.xml.jobscheduler.MergeAllXMLinDirectory;
import com.sos.joe.xml.jobscheduler.SchedulerDom;
  
public class JobChainNestedNodesForm extends CompositeBaseClass /* SOSJOEMessageCodes */ implements ISOSTableMenueListeners, IUnsaved, IUpdateLanguage {

	private final String			conClassName	= "JobChainNodesForm";
	@SuppressWarnings("unused")
	private final String			conSVNVersion	= "$Id$";

	private Button					butAddMissingNodes;
	private Button					bNewNode		= null;
	private SOSTable					tNodes			= null;
	private Button					bApplyNode		= null;
	private SOSComboBox				cErrorState		= null;
	private Label					label9			= null;
	private SOSComboBox				cNextState		= null;
	private Label					label8			= null;
	private Button					bEndNode		= null;
	private Button					bFullNode		= null;
	private Composite				cType			= null;
	private SOSComboBox				cJobChain		= null;
	private Label					label7			= null;
	private Text					tState			= null;
	private Label					label6			= null;
	private JobChainNestedListener	listener		= null;
	private Group					jobChainGroup	= null;
	private Button					bRemoveNode		= null;
	private Group					gNodes			= null;
	private boolean					refresh			= false;
	private Button					butDetailsJob	= null;
	private Button					butBrowse		= null;
	private Button					butUp			= null;
	private Button					butDown			= null;
	private SchedulerDom			dom				= null;
	private Button					butGoto			= null;
	private boolean					isInsert		= false;
	private ISchedulerUpdate		update			= null;
	private Button					butInsert		= null;
	private Button					reorderButton	= null;

	public JobChainNestedNodesForm(final Composite parent, final int style, final SchedulerDom dom_, final Element jobChain) {
		super(parent, style);
		try {
			objParent.setRedraw(false);
			objParent = parent;
			dom = dom_;
			listener = new JobChainNestedListener(dom, jobChain);
			initialize();

			boolean existChainNodes = listener.hasNodesOrChains();
			jobChainGroup.setEnabled(existChainNodes);
			bNewNode.setEnabled(existChainNodes);
			if (existChainNodes)
				fillChain(false, false);
			objParent.setEnabled(Utils.isElementEnabled("job_chain", dom, jobChain));
		}
		catch (Exception e) {
			new ErrorLog(JOE_E_0002.params(conClassName), e);
		}
		finally {
			objParent.setRedraw(true);
			objParent.layout(true, true);
		}

	}

	@Override
	public void apply() {
		if (bApplyNode.isEnabled())
			applyNode();
	}

	@Override
	public boolean isUnsaved() {
		return bApplyNode.isEnabled();
	}

	private void initialize() {
		objParent.setLayout(new FillLayout());
		createGroup();
	}

	private void createGroup() {

		jobChainGroup = new Group(objParent, SWT.NONE);
		final GridLayout gridLayout = new GridLayout();
		jobChainGroup.setLayout(gridLayout);

		gNodes = getGroup(jobChainGroup, 4);
		gNodes.setText(JOE_M_JCNestedNodesForm_NestedNodes.params(listener.getChainName()));

		label6 = JOE_L_JobChainNodes_State.Control(new Label(gNodes, SWT.NONE));
		label6.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, true, false, 2, 1));

		tState = JOE_T_JobChainNodes_State.Control(new Text(gNodes, SWT.BORDER));
		tState.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				boolean valid = listener.isUniqueState(tState.getText());
				if (!valid)
					tState.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW));
				else
					tState.setBackground(null);
				bApplyNode.setEnabled(isValidNode() && valid);
				if (bApplyNode.getEnabled())
					getShell().setDefaultButton(bApplyNode);
			}
		});

		final GridData gridData18 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData18.widthHint = 459;
		tState.setLayoutData(gridData18);

		bApplyNode = JOE_B_JobChainNodes_ApplyNode.Control(new Button(gNodes, SWT.NONE));
		bApplyNode.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				applyNode();
			}
		});
		final GridData gridData7 = new GridData(GridData.FILL, GridData.BEGINNING, false, false);
		bApplyNode.setLayoutData(gridData7);
		bApplyNode.setEnabled(false);

		label7 = JOE_L_JCNestedNodesForm_JobChain.Control(new Label(gNodes, SWT.NONE));
		label7.setLayoutData(new GridData());

		butGoto = JOE_B_JobChainNodes_Goto.Control(new Button(gNodes, SWT.ARROW | SWT.DOWN));
		butGoto.setVisible(listener.get_dom() != null && !listener.get_dom().isLifeElement());
		butGoto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				ContextMenu.goTo(cJobChain.getText(), dom, JOEConstants.JOB_CHAIN);
			}
		});
		butGoto.setAlignment(SWT.RIGHT);

		cJobChain = new SOSComboBox(gNodes, JOE_Cbo_JCNestedNodesForm_JobChain);
		cJobChain.setMenu(new ContextMenu(cJobChain, dom, JOEConstants.JOB_CHAIN).getMenu());
		cJobChain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(final MouseEvent e) {
				if (refresh) {
					if (listener.getJobChains() != null) {
						cJobChain.setItems(listener.getJobChains());
						refresh = false;
					}
				}
			}
		});

		cJobChain.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				bApplyNode.setEnabled(isValidNode());
				if (bApplyNode.getEnabled())
					getShell().setDefaultButton(bApplyNode);
			}
		});
		cJobChain.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					applyNode();
				}
			}
		});
		final GridData gridData13 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData13.widthHint = 579;
		cJobChain.setLayoutData(gridData13);

		final Composite composite = new Composite(gNodes, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false));
		final GridLayout gridLayout_2 = new GridLayout();
		gridLayout_2.marginWidth = 0;
		gridLayout_2.marginHeight = 0;
		composite.setLayout(gridLayout_2);

		butBrowse = JOE_B_JobChainNodes_Browse.Control(new Button(composite, SWT.NONE));
		butBrowse.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		butBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				String jobname = IOUtils.getJobschedulerObjectPathName(MergeAllXMLinDirectory.MASK_JOB_CHAIN);
				if (jobname != null && jobname.length() > 0)
					cJobChain.setText(jobname);
			}
		});

		//		if(!listener.get_dom().isLifeElement()) {
		//		}

		label8 = JOE_L_JobChainNodes_NextState.Control(new Label(gNodes, SWT.NONE));
		label8.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1));

		cNextState = new SOSComboBox(gNodes, JOE_Cbo_JobChainNodes_NextState);
		cNextState.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				bApplyNode.setEnabled(isValidNode());
				if (bApplyNode.getEnabled())
					getShell().setDefaultButton(bApplyNode);
			}
		});
		cNextState.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					applyNode();
				}
			}
		});
		final GridData gridData14 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData14.widthHint = 80;
		cNextState.setLayoutData(gridData14);

		bNewNode = JOE_B_JCNestedNodesForm_NewNode.Control(new Button(gNodes, SWT.NONE));
		bNewNode.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				isInsert = false;
				getShell().setDefaultButton(null);
				tNodes.deselectAll();
				butDetailsJob.setEnabled(false);
				listener.selectNode(null);
				bRemoveNode.setEnabled(false);
				enableNode(true);
				fillNode(true);
				tState.setFocus();
			}
		});
		bNewNode.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, false, false));

		label9 = JOE_L_JobChainNodes_ErrorState.Control(new Label(gNodes, SWT.NONE));
		label9.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1));

		cErrorState = new SOSComboBox(gNodes, JOE_Cbo_JobChainNodes_ErrorState);
		cErrorState.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				bApplyNode.setEnabled(isValidNode());
				if (bApplyNode.getEnabled())
					getShell().setDefaultButton(bApplyNode);
			}
		});
		cErrorState.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					applyNode();
				}
			}
		});
		final GridData gridData15 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData15.widthHint = 80;
		cErrorState.setLayoutData(gridData15);

		butInsert = JOE_B_JCNestedNodesForm_Insert.Control(new Button(gNodes, SWT.NONE));
		butInsert.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				isInsert = true;
				String state = tState.getText();

				tState.setText("");

				//listener.selectNode(null);

				//tDelay.setText("");
				cErrorState.setText("");
				//cOnError.setText("");
				cJobChain.setText("");

				//n�chste status

				cNextState.setText(state);
				enableNode(true);
				bFullNode.setSelection(true);
				bEndNode.setSelection(false);
				//System.out.println("state=" + state + " tNextstate.getText(): " + tNextState.getText());
			}
		});
		butInsert.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));

		cType = new Composite(gNodes, SWT.NONE);
		final GridLayout gridLayout_4 = new GridLayout();
		gridLayout_4.marginBottom = 0;
		gridLayout_4.marginHeight = 0;
		gridLayout_4.marginWidth = 0;
		gridLayout_4.numColumns = 2;
		cType.setLayout(gridLayout_4);
		final GridData gridData5 = new GridData(GridData.FILL, GridData.CENTER, true, false, 3, 1);
		gridData5.widthHint = 387;
		gridData5.heightHint = 35;
		cType.setLayoutData(gridData5);

		bFullNode = JOE_B_JCNestedNodesForm_FullNode.Control(new Button(cType, SWT.RADIO));
		bFullNode.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, true));
		bFullNode.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
			}
		});
		bFullNode.setSelection(true);

		bEndNode = JOE_B_JCNestedNodesForm_EndNode.Control(new Button(cType, SWT.RADIO));
		bEndNode.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, true));
		bEndNode.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {

				if (bEndNode.getSelection()) {
					cNextState.setEnabled(false);
					cErrorState.setEnabled(false);
					cJobChain.setEnabled(false);
					cJobChain.setText("");
					cNextState.setText("");
					cErrorState.setText("");
				}

				if (bFullNode.getSelection()) {
					cNextState.setEnabled(true);
					cErrorState.setEnabled(true);
					cJobChain.setEnabled(true);
					if (bApplyNode.getEnabled())
						getShell().setDefaultButton(bApplyNode);
				}
				bApplyNode.setEnabled(isValidNode());

			}
		});

		//		Format
		new Label(gNodes, SWT.NONE);

		tNodes = (SOSTable) JOE_Tbl_JCNestedNodesForm_Nodes.Control(new SOSTable(gNodes, SWT.FULL_SELECTION | SWT.BORDER, this));
		tNodes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				selectNodes();
			}
		});
		tNodes.setLinesVisible(true);
		tNodes.setHeaderVisible(true);
		final GridData gridData4 = new GridData(GridData.FILL, GridData.FILL, true, true, 3, 4);
		gridData4.heightHint = 112;
		tNodes.setLayoutData(gridData4);

		final TableColumn tableColumn3 = JOE_TCl_JCNestedNodesForm_State.Control(new TableColumn(tNodes, SWT.NONE));
		tableColumn3.setWidth(90);

		final TableColumn newColumnTableColumn_3 = JOE_TCl_JCNestedNodesForm_Node.Control(new TableColumn(tNodes, SWT.NONE));
		newColumnTableColumn_3.setWidth(100);

		final TableColumn tableColumn4 = JOE_TCl_JCNestedNodesForm_JobChain.Control(new TableColumn(tNodes, SWT.NONE));
		tableColumn4.setWidth(200);

		final TableColumn tableColumn5 = JOE_TCl_JCNestedNodesForm_NextState.Control(new TableColumn(tNodes, SWT.NONE));
		tableColumn5.setWidth(90);

		final TableColumn tableColumn6 = JOE_TCl_JCNestedNodesForm_ErrorState.Control(new TableColumn(tNodes, SWT.NONE));
		tableColumn6.setWidth(90);

		final TableColumn newColumnTableColumn_4 = JOE_TCl_JCNestedNodesForm_OnError.Control(new TableColumn(tNodes, SWT.NONE));
		newColumnTableColumn_4.setWidth(100);

		final Composite composite_1 = new Composite(gNodes, SWT.NONE);
		composite_1.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
		final GridLayout gridLayout_5 = new GridLayout();
		gridLayout_5.marginWidth = 0;
		gridLayout_5.marginHeight = 0;
		gridLayout_5.numColumns = 3;
		composite_1.setLayout(gridLayout_5);

		butUp = JOE_B_Up.Control(new Button(composite_1, SWT.NONE));
		butUp.setLayoutData(new GridData());
		butUp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				if (tNodes.getSelectionCount() > 0) {
					int index = tNodes.getSelectionIndex();
					if (index > 0) {
						//es ist nur erlaubt zwischen fullnode oder zwischen endnode zu wechseln
						/*if(tNodes.getItemCount() > index){
							String select = tNodes.getSelection()[0].getText(1);
							String up = tNodes.getItem(index-1).getText(1);
							if(!select.equals(up)) {
								return;
								//System.out.println("up:    " +select + "   " + up);
							}
						}*/

						listener.MoveNodeUp(tNodes, true, bFullNode.getSelection() || bEndNode.getSelection(), tState.getText(), cJobChain.getText(), "",
								cNextState.getText(), cErrorState.getText(), index, bFullNode.getSelection(), reorderButton.getSelection());
						selectNodes();
					}
				}
			}
		});

		butUp.setImage(ResourceManager.getImageFromResource("/sos/scheduler/editor/icon_up.gif"));

		butDown = JOE_B_Down.Control(new Button(composite_1, SWT.NONE));
		butDown.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				if (tNodes.getSelectionCount() > 0) {
					int index = tNodes.getSelectionIndex();
					if (index == tNodes.getItemCount() - 1) {
						//System.out.println("Datensatz ist bereits ganz unten.");
					}
					else
						if (index >= 0) {

							/*String select = tNodes.getSelection()[0].getText(1);
							String up = tNodes.getItem(index+1).getText(1);
							if(!select.equals(up)) {
								//System.out.println("down: " + select + "   " + up);
								return;
							}*/

							listener.MoveNodeUp(tNodes, false, bFullNode.getSelection() || bEndNode.getSelection(), tState.getText(), cJobChain.getText(), "",
									cNextState.getText(), cErrorState.getText(), index, bFullNode.getSelection(), reorderButton.getSelection());
							selectNodes();
						}
				}
			}
		});
		butDown.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
		butDown.setImage(ResourceManager.getImageFromResource("/sos/scheduler/editor/icon_down.gif"));

		reorderButton = JOE_B_JCNestedNodesForm_Reorder.Control(new Button(composite_1, SWT.CHECK));

		butDetailsJob = JOE_B_JobChainForm_Parameter.Control(new Button(gNodes, SWT.NONE));
		butDetailsJob.setEnabled(false);
		butDetailsJob.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				if (tNodes.getSelectionCount() > 0)
					showDetails(tNodes.getSelection()[0].getText(0));
			}
		});
		butDetailsJob.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, false, false));

		butAddMissingNodes = JOE_B_JCNestedNodesForm_AddMissingNodes.Control(new Button(gNodes, SWT.NONE));
		butAddMissingNodes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				try {
					if (tNodes.getSelectionCount() > 0) {
						TableItem item = tNodes.getSelection()[0];
						if (!listener.checkForState(item.getText(3))) {
							listener.selectNode(null);
							listener.applyNode(true, item.getText(3), "", "", "", false);
						}

						if (!listener.checkForState(item.getText(4))) {
							listener.selectNode(null);
							listener.applyNode(true, item.getText(4), "", "", "", false);
						}

						listener.fillChain(tNodes);
						bApplyNode.setEnabled(false);
						bRemoveNode.setEnabled(false);
						listener.selectNode(null);
						fillNode(true);
						enableNode(false);
						//listener.applyNode(bFullNode.getSelection() || bEndNode.getSelection(), tState.getText(), cJob.getText(), tDelay.getText(), cNextState.getText(), cErrorState.getText(),bRemoveFile.getSelection(),tMoveTo.getText(), cOnError.getText());
					}
				}
				catch (Exception ex) {
					try {
						new ErrorLog(JOE_E_0002.params(SOSClassUtil.getMethodName()), ex);
					}
					catch (Exception ee) {
					}
				}
			}
		});
		butAddMissingNodes.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
		butAddMissingNodes.setEnabled(false);

		bRemoveNode = JOE_B_JCNestedNodesForm_RemoveNode.Control(new Button(gNodes, SWT.NONE));
		bRemoveNode.setEnabled(false);
		bRemoveNode.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				if (tNodes.getSelectionCount() > 0) {

					int c = JOEMainWindow.message(getShell(), JOE_M_JCNestedNodesForm_RemoveNode.label(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
					if (c != SWT.YES)
						return;

					int index = tNodes.getSelectionIndex();
					listener.deleteNode(tNodes);
					tNodes.remove(index);
					if (index >= tNodes.getItemCount())
						index--;
					boolean empty = tNodes.getItemCount() == 0;

					fillNode(empty);
					enableNode(!empty);
					bRemoveNode.setEnabled(!empty);
					if (!empty) {
						tNodes.select(index);
						listener.selectNode(tNodes);
					}
					else {
						listener.selectNode(null);
					}
				}
			}
		});
		bRemoveNode.setLayoutData(new GridData(GridData.FILL, GridData.END, false, false));

	} // private void createGroup()

	private void fillChain(final boolean enable, final boolean isNew) {
		listener.fillChain(tNodes);
		bNewNode.setEnabled(true);
		enableNode(false);
	}

	private void enableNode(final boolean enable) {
		bFullNode.setEnabled(enable);
		bEndNode.setEnabled(enable);
		tState.setEnabled(enable);
		cJobChain.setEnabled(enable);
		cNextState.setEnabled(enable);
		cErrorState.setEnabled(enable);
		butBrowse.setEnabled(enable);
		bApplyNode.setEnabled(false);
	}

	private void fillNode(final boolean clear) {

		boolean fullNode = listener.isFullNode();
		//boolean fileSinkNode = listener.isFileSinkNode();
		//boolean endNode = !fullNode && !fileSinkNode;
		boolean endNode = !fullNode;

		bFullNode.setSelection(clear || fullNode);
		bEndNode.setSelection(!clear && endNode);
		cNextState.setEnabled(fullNode);
		cErrorState.setEnabled(fullNode);
		cJobChain.setEnabled(fullNode);

		tState.setText(clear ? "" : listener.getState());

		cJobChain.setItems(listener.getJobChains());
		if (listener.getStates().length > 0)
			cNextState.setItems(listener.getStates());
		if (listener.getStates().length > 0)
			cErrorState.setItems(listener.getStates());

		int job = cJobChain.indexOf(listener.getNestedJobChain());
		if (clear || job == -1)
			cJobChain.setText(listener.getNestedJobChain());
		else
			cJobChain.select(job);

		int next = cNextState.indexOf(listener.getNextState());
		if (clear || !fullNode || next == -1)
			cNextState.setText(listener.getNextState());
		else
			cNextState.select(next);

		int error = cErrorState.indexOf(listener.getErrorState());
		if (clear || !fullNode || error == -1)
			cErrorState.setText(listener.getErrorState());
		else
			cErrorState.select(error);

		bApplyNode.setEnabled(false);

	}

	private void applyNode () {
		applyInputFields(false, OperationMode);
	}
	@Override
	public void applyInputFields(final boolean flgT, final enuOperationMode OperationMode) {

		String msg = "";
		if (!listener.isUniqueState(tState.getText()))
			msg = JOE_M_JobChain_StateAlreadyDefined.label();
		if (!msg.equals(""))
			JOEMainWindow.message(msg, SWT.ICON_INFORMATION);
		else {
			//listener.applyNode(bFullNode.getSelection() || bEndNode.getSelection(), tState.getText(), cJobChain.getText(), tDelay.getText(), cNextState.getText(), cErrorState.getText(),bRemoveFile.getSelection(),tMoveTo.getText(), cOnError.getText());
			if (isInsert)
				listener.applyInsertNode(tState.getText(), cJobChain.getText(), cNextState.getText(), cErrorState.getText(), bFullNode.getSelection());
			else
				listener.applyNode(bFullNode.getSelection() || bEndNode.getSelection(), tState.getText(), cJobChain.getText(), cNextState.getText(),
						cErrorState.getText(), bFullNode.getSelection());
			isInsert = false;
			listener.fillChain(tNodes);
			bApplyNode.setEnabled(false);
			bRemoveNode.setEnabled(false);
			listener.selectNode(null);
			fillNode(true);
			enableNode(false);
		}

	}

	private boolean isValidNode() {
		if (tState.getText().equals("") || bFullNode.getSelection() && cJobChain.getText().equals("")) {
			return false;
		}
		else {
			return true;
		}
	}

	public void setISchedulerUpdate(final ISchedulerUpdate update_) {
		update = update_;
	}

	private void showDetails(final String state) {

		//OrdersListener ordersListener =  new OrdersListener(listener.get_dom(), update);
		//String[] listOfOrders = ordersListener.getOrderIds();
		boolean isLifeElement = listener.get_dom().isLifeElement() || listener.get_dom().isDirectory();

		if (state == null) {
			DetailDialogForm detail = new DetailDialogForm(listener.getChainName(), isLifeElement, listener.get_dom().getFilename());
			detail.showDetails();
		}
		else {
			//DetailDialogForm detail = new DetailDialogForm(listener.getChainName(), state, listOfOrders, isLifeElement, listener.get_dom().getFilename());
			DetailDialogForm detail = new DetailDialogForm(listener.getChainName(), state, null, isLifeElement, listener.get_dom().getFilename());
			detail.showDetails();
		}
	}

	private void selectNodes() {

		if (tNodes.getSelectionCount() > 0) {
			listener.selectNode(tNodes);
			enableNode(true);
			fillNode(false);
			butDetailsJob.setEnabled(true);
		}
		else
			butDetailsJob.setEnabled(false);
		bRemoveNode.setEnabled(tNodes.getSelectionCount() > 0);

	}

	@Override
	public void setToolTipText() {
	}

	@Override
	public Listener getNewListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Listener getDeleteListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Listener getCopyListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Listener getPasteListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Listener getInsertListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Listener getCutListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Listener getEditListener() {
		// TODO Auto-generated method stub
		return null;
	}

} // @jve:decl-index=0:visual-constraint="10,10"