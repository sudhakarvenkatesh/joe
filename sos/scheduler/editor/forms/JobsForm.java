package sos.scheduler.editor.forms;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import sos.scheduler.editor.app.DomParser;

import sos.scheduler.editor.app.IUpdateLanguage;
import sos.scheduler.editor.app.Messages;
import sos.scheduler.editor.app.IUpdate;
import sos.scheduler.editor.listeners.JobsListener;
import sos.scheduler.editor.listeners.MainListener;

import org.eclipse.swt.widgets.Label;
import org.jdom.Element;

public class JobsForm extends Composite implements  IUpdateLanguage {

	private JobsListener listener;

	private MainListener mainListener;

	private Group group = null;

	private Table table = null;

	private Button bNewJob = null;

	private Button bRemoveJob = null;

	private Label label = null;

	public JobsForm(Composite parent, int style, DomParser dom, IUpdate update,
			MainListener mainListener) {
		super(parent, style);
		this.mainListener = mainListener;
		listener = new JobsListener(dom, update);
		initialize();
		setToolTipText();		
		listener.fillTable(table);
	}

	private void initialize() {
		this.setLayout(new FillLayout());
		createGroup();
		setSize(new org.eclipse.swt.graphics.Point(656, 400));
	}

	/**
	 * This method initializes group
	 * 
	 */
	private void createGroup() {
		GridData gridData4 = new org.eclipse.swt.layout.GridData();
		gridData4.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData4.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		GridData gridData1 = new org.eclipse.swt.layout.GridData();
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		GridData gridData = new org.eclipse.swt.layout.GridData();
		gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData.verticalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		group = new Group(this, SWT.NONE);
		group.setText("Jobs");
		group.setLayout(gridLayout);
		createTable();
		bNewJob = new Button(group, SWT.NONE);
		bNewJob.setText("&New Job");
		bNewJob.setLayoutData(gridData);
		getShell().setDefaultButton(bNewJob);
		
		label = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setText("Label");
		label.setLayoutData(gridData4);
		bNewJob
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						listener.newJob(table);
						bRemoveJob.setEnabled(true);
					}
				});
		bRemoveJob = new Button(group, SWT.NONE);
		bRemoveJob.setText("Remove Job");
		bRemoveJob.setEnabled(false);
		bRemoveJob.setLayoutData(gridData1);
		bRemoveJob
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						bRemoveJob.setEnabled(listener.deleteJob(table));
					}
				});
	}

	/**
	 * This method initializes table
	 * 
	 */
	private void createTable() {
		GridData gridData2 = new org.eclipse.swt.layout.GridData();
		gridData2.verticalSpan = 3;
		gridData2.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		table = new Table(group, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
		table.setHeaderVisible(true);
		table.setLayoutData(gridData2);
		table.setLinesVisible(true);
		TableColumn tableColumn5 = new TableColumn(table, SWT.NONE);
		tableColumn5.setWidth(60);
		tableColumn5.setText("Disabled");
		table
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						if (e.detail == SWT.CHECK) {
							TableItem item = (TableItem) e.item;
							if (!listener.hasJobComment((Element) item
									.getData())) {
								listener.setJobDisabled(item.getText(1), item
										.getChecked());
							} else {
								mainListener
										.message(
												Messages
														.getString("MainListener.cannotDisable"),
												SWT.ICON_INFORMATION | SWT.OK);
								item.setChecked(false);
							}
						} else
							bRemoveJob.setEnabled(true);
					}
				});
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("Name");
		TableColumn tableColumn1 = new TableColumn(table, SWT.NONE);
		tableColumn1.setWidth(200);
		tableColumn1.setText("Title");
		TableColumn tableColumn2 = new TableColumn(table, SWT.NONE);
		tableColumn2.setWidth(100);
		tableColumn2.setText("Scheduler ID");
		TableColumn tableColumn3 = new TableColumn(table, SWT.NONE);
		tableColumn3.setWidth(100);
		tableColumn3.setText("Process Class");
		TableColumn tableColumn4 = new TableColumn(table, SWT.NONE);
		tableColumn4.setWidth(40);
		tableColumn4.setText("Order");
	}
	
	public void setToolTipText(){
		bNewJob.setToolTipText(Messages.getTooltip("jobs.btn_add_new"));
		bRemoveJob.setToolTipText(Messages.getTooltip("jobs.btn_remove"));
		table.setToolTipText(Messages.getTooltip("jobs.table"));

	 
  }	

} // @jve:decl-index=0:visual-constraint="10,10"
