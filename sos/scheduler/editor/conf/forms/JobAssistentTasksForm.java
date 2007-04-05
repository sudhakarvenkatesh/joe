package sos.scheduler.editor.conf.forms;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.jdom.Element;
import com.swtdesigner.SWTResourceManager;
import sos.scheduler.editor.app.MainWindow;
import sos.scheduler.editor.app.Messages;
import sos.scheduler.editor.app.ResourceManager;
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.conf.ISchedulerUpdate;
import sos.scheduler.editor.conf.SchedulerDom;
import sos.scheduler.editor.conf.listeners.JobListener;
import sos.scheduler.editor.conf.listeners.JobsListener;

public class JobAssistentTasksForm {
	
	private Text              txtTasks     = null;
	
	private Text              txtTask      = null;
	
	private JobListener       joblistener  = null;
	
	private SchedulerDom      dom          = null;
	
	private ISchedulerUpdate  update       = null;
	
	private Button            butFinish    = null;
	
	private Button            butCancel    = null;
	
	private Button            butNext      = null;
	
	private Button            butShow      = null;		
	
	private Text              txtMinTasks  = null;
	
	/** Wer hat ihn aufgerufen, der Job assistent oder job_chain assistent*/
	private int               assistentType= -1; 
	
	private Shell             tasksShell   = null; 
	
	private Combo             jobname      = null;
	
	public JobAssistentTasksForm(SchedulerDom dom_, ISchedulerUpdate update_, Element job_, int assistentType_) {	
		dom = dom_;
		update = update_;		
		assistentType = assistentType_;
		joblistener = new JobListener(dom, job_, update);				
	}
	
	private void init() {
		Element job = joblistener.getJob();
		if(job != null && Utils.getAttributeValue("tasks", job).equals("unbounded")) {
			job.removeAttribute("tasks");			
		}
	}
	
	public void showTasksForm() {
		
		init();
		
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		tasksShell= new Shell(SWT.CLOSE | SWT.TITLE | SWT.APPLICATION_MODAL | SWT.BORDER);
		tasksShell.setLayout(gridLayout);
		tasksShell.setSize(473, 306);
		tasksShell.setImage(ResourceManager.getImageFromResource("/sos/scheduler/editor/editor.png"));
		String step = "  ";
		if (Utils.getAttributeValue("order", joblistener.getJob()).equalsIgnoreCase("yes"))
			step = step + " [Step 4 of 9]";
		else 
			step = step + " [Step 4 of 8]";
		tasksShell.setText("Tasks" + step);
		
		{
			if(Utils.getAttributeValue("tasks", joblistener.getJob()) != null && 
					!Utils.getAttributeValue("tasks", joblistener.getJob()).equals("unbounded")) {
		       joblistener.setTasks("");		
			}
		}
		
		{
			final Group jobGroup = new Group(tasksShell, SWT.NONE);
			jobGroup.setText("Job " + Utils.getAttributeValue("name", joblistener.getJob()));
			final GridData gridData = new GridData(GridData.FILL, GridData.CENTER, false, false, 2, 1);
			gridData.widthHint = 448;
			gridData.heightHint = 214;
			jobGroup.setLayoutData(gridData);
			final GridLayout gridLayout_1 = new GridLayout();
			gridLayout_1.marginWidth = 10;
			gridLayout_1.marginTop = 10;
			gridLayout_1.marginRight = 10;
			gridLayout_1.marginLeft = 10;
			gridLayout_1.marginHeight = 10;
			jobGroup.setLayout(gridLayout_1);
			
			{
				txtTasks = new Text(jobGroup, SWT.MULTI | SWT.WRAP);
				final GridData gridData_1 = new GridData(GridData.FILL, GridData.FILL, false, false);
				gridData_1.heightHint = 137;
				gridData_1.widthHint = 410;
				txtTasks.setLayoutData(gridData_1);
				txtTasks.setEditable(false);
				txtTasks.setText(Messages.getString("assistent.tasks"));
			}
			
			final Composite composite_1 = new Composite(jobGroup, SWT.NONE);
			composite_1.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false));
			final GridLayout gridLayout_3 = new GridLayout();
			gridLayout_3.marginWidth = 0;
			gridLayout_3.marginRight = 5;
			gridLayout_3.numColumns = 4;
			composite_1.setLayout(gridLayout_3);
			
			{
				final Label tasksLabel = new Label(composite_1, SWT.NONE);
				final GridData gridData_1 = new GridData(GridData.FILL, GridData.CENTER, false, false);
				gridData_1.widthHint = 57;
				tasksLabel.setLayoutData(gridData_1);
				tasksLabel.setText("Tasks");
			}
			txtTask = new Text(composite_1, SWT.BORDER);
			txtTask.setFocus();
			txtTask.addModifyListener(new ModifyListener() {
				public void modifyText(final ModifyEvent e) {
					if(txtTask.getText() !=  null && txtTask.getText().trim().length() > 0)  {
						if(Utils.isNumeric(txtTask.getText())) {
							joblistener.setTasks(txtTask.getText());
						} else {							
							MainWindow.message(tasksShell, sos.scheduler.editor.app.Messages.getString("assistent.no_numeric"), SWT.ICON_WARNING | SWT.OK );
						}
						txtTask.setFocus();
					}
				}
			});
			txtTask.setFocus();
			final GridData gridData_5 = new GridData(GridData.FILL, GridData.CENTER, false, false);
			gridData_5.widthHint = 112;
			gridData_5.minimumWidth = 100;
			txtTask.setLayoutData(gridData_5);
			txtTask.setText(Utils.getAttributeValue("tasks", joblistener.getJob()));
			
			final Label minimumTasksLabel = new Label(composite_1, SWT.RIGHT);
			final GridData gridData_3 = new GridData(GridData.FILL, GridData.CENTER, false, false);
			gridData_3.widthHint = 95;
			minimumTasksLabel.setLayoutData(gridData_3);
			minimumTasksLabel.setText("Minimum Tasks");
			
			txtMinTasks = new Text(composite_1, SWT.BORDER);
			txtMinTasks.addModifyListener(new ModifyListener() {
				public void modifyText(final ModifyEvent e) {
					if(txtMinTasks.getText() !=  null && txtMinTasks.getText().trim().length() > 0)  {
						if(Utils.isNumeric(txtMinTasks.getText())) {
							joblistener.setMintasks(txtMinTasks.getText());
						} else {							
							MainWindow.message(tasksShell, sos.scheduler.editor.app.Messages.getString("assistent.no_numeric"), SWT.ICON_WARNING | SWT.OK );
						}
					}
				}
			});
			final GridData gridData_4 = new GridData(GridData.FILL, GridData.CENTER, true, false);
			gridData_4.widthHint = 89;
			txtMinTasks.setLayoutData(gridData_4);
		}
		
		{
			final Composite composite = new Composite(tasksShell, SWT.NONE);
			composite.setLayoutData(new GridData());
			final GridLayout gridLayout_2 = new GridLayout();
			gridLayout_2.marginWidth = 0;
			composite.setLayout(gridLayout_2);
			{
				butCancel = new Button(composite, SWT.NONE);
				butCancel.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(final SelectionEvent e) {
						close();
					}
				});
				butCancel.setText("Cancel");
			}
		}
		
		final Composite composite_2 = new Composite(tasksShell, SWT.NONE);
		composite_2.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));
		final GridLayout gridLayout_4 = new GridLayout();
		gridLayout_4.marginWidth = 0;
		gridLayout_4.numColumns = 3;
		composite_2.setLayout(gridLayout_4);
		
		{
			butShow = new Button(composite_2, SWT.NONE);
			butShow.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));
			butShow.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					if(!checkTasks()) return;					
					MainWindow.message(tasksShell, Utils.getElementAsString(joblistener.getJob()), SWT.OK );
					txtTask.setFocus();
				}
			});
			butShow.setText("Show");
		}
		
		{
			butFinish = new Button(composite_2, SWT.NONE);
			butFinish.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));
			butFinish.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					
					if(!checkTasks()) return;	
					if(jobname != null)
						jobname.setText(Utils.getAttributeValue("name",joblistener.getJob()));				
					JobsListener j = new JobsListener(dom, update);
					j.newImportJob(joblistener.getJob(), assistentType);	
					MainWindow.message(tasksShell,  Messages.getString("assistent.finish") + "\n\n" + Utils.getElementAsString(joblistener.getJob()), SWT.OK );
					tasksShell.dispose();
				}
			});
			butFinish.setText("Finish");
		}
		{
			butNext = new Button(composite_2, SWT.NONE);
			butNext.setFont(SWTResourceManager.getFont("", 8, SWT.BOLD));
			butNext.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
			butNext.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {					
					if(!checkTasks()) return;
					Element job = joblistener.getJob();
					if(job.getChild("process") != null) {
						JobAssistentProcessForms process = new JobAssistentProcessForms(dom, update, job, assistentType);
						process.showProcessForm();	
						if(jobname != null) 													
							process.setJobname(jobname);
					} else {
						JobAssistentScriptForms script = new JobAssistentScriptForms(dom, update, job, assistentType);
						script.showScriptForm();	
						if(jobname != null) 													
							script.setJobname(jobname);
					}
					tasksShell.dispose();
				}
			});
			butNext.setText("Next");
		}
		tasksShell.layout();
		setToolTipText();
		java.awt.Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();		
		tasksShell.setBounds((screen.width - tasksShell.getBounds().width) /2, 
				(screen.height - tasksShell.getBounds().height) /2, 
				tasksShell.getBounds().width, 
				tasksShell.getBounds().height);
		tasksShell.open();
	}
	
	public void setToolTipText() {
		butCancel.setToolTipText(Messages.getTooltip("assistent.cancel"));
		butNext.setToolTipText(Messages.getTooltip("assistent.next"));
		butShow.setToolTipText(Messages.getTooltip("assistent.show"));
		butFinish.setToolTipText(Messages.getTooltip("assistent.finish"));
		txtTask.setToolTipText(Messages.getTooltip("assistent.task"));
		txtMinTasks.setToolTipText(Messages.getTooltip("assistent.min_task"));
	}
		
	
	/**
	 * �berpr�fung
	 * min_tasks darf tasks nicht �berschreiten
	 */
	private boolean checkTasks() {				
		if((txtTask.getText() != null && txtTask.getText().trim().length() > 0) &&
				(txtMinTasks.getText() != null && txtMinTasks.getText().trim().length() > 0)) {
			if(Integer.parseInt(txtMinTasks.getText()) > Integer.parseInt(txtTask.getText())) {
				MainWindow.message(tasksShell, sos.scheduler.editor.app.Messages.getString("assistent.error.min_task_too_large"), SWT.ICON_WARNING | SWT.OK );
				return false;
			}			
		}
		
		if((txtTask.getText() != null && txtTask.getText().trim().length() == 0) &&
				(txtMinTasks.getText() != null && txtMinTasks.getText().trim().length() > 0)) {
			if(Integer.parseInt(txtMinTasks.getText()) > 1) {
				MainWindow.message(tasksShell, sos.scheduler.editor.app.Messages.getString("min_task_to_great"), SWT.ICON_WARNING | SWT.OK );
				return false;
			}
			
		}
		return true;
	}
		
	private void close() {
		int cont = MainWindow.message(tasksShell, sos.scheduler.editor.app.Messages.getString("assistent.cancel"), SWT.ICON_WARNING | SWT.OK |SWT.CANCEL );
		if(cont == SWT.OK)
			tasksShell.dispose();
	}

	public void setJobname(Combo jobname) {
		this.jobname = jobname;
	}
}