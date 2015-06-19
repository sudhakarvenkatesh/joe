package sos.scheduler.editor.conf.composites;

 
import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import sos.scheduler.editor.app.MainWindow;
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.conf.forms.JobChainNodesForm;
import sos.scheduler.editor.conf.listeners.JobChainListener;

 

import static com.sos.joe.globals.messages.SOSJOEMessageCodes.*;


public class SOSTabFileOrderSource extends CTabItem {

	private final Composite composite;
    private final String                                    conClassName                = "SOSTabFileOrderSource";
    final String                                            conMethodName               = conClassName + "::enclosing_method";
    @SuppressWarnings("unused") private final String        conSVNVersion               = "$Id$";
    @SuppressWarnings("unused") private static final Logger logger                      = Logger.getLogger(JobChainNodesForm.class);
    private Button                                          dumm2                       = null;
    @SuppressWarnings("unused") private Label               label8                      = null;
    private Group                                           gFileOrderSource            = null;
    private JobChainListener                                listener                    = null;
    private Button                                          bNewFileOrderSource         = null;
    private Button                                          bRemoveFileOrderSource      = null;
    private Button                                          bApplyFileOrderSource       = null;
    private Text                                            tDirectory                  = null;
    private Text                                            tDelayAfterError            = null;
    private Text                                            tNextState                  = null;
    private Text                                            tRegex                      = null;
    private Text                                            tRepeat                     = null;
    private Table                                           tFileOrderSource            = null;
    private Button                                          cbAlertWhenDirectoryMissing = null;
 	 
	public SOSTabFileOrderSource(final String caption, final CTabFolder parent,JobChainListener listener_ ) {
		super(parent, SWT.NONE);
		listener = listener_;
 		setText(caption);
		composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		composite.setLayout(layout);
		createContents();
        boolean isJobchain=!listener.isNestedJobchain();
        gFileOrderSource.setEnabled(isJobchain);
        if (isJobchain){
            initFileOrderSource();
        }
        
        composite.setEnabled(Utils.isElementEnabled("job_chain", listener.getDom(), listener.getChain()));       		
		this.setControl(composite);
        composite.layout();
        
	}

	 
 
	/**
	 * Create contents of the window
	 */
	protected void createContents() {
	    
 
 
        gFileOrderSource = new Group(composite, SWT.NONE);
        final GridData gridData_10 = new GridData(SWT.FILL, GridData.FILL, true, true);
        gridData_10.heightHint = 379;
        gridData_10.minimumHeight = 379;
        gFileOrderSource.setLayoutData(gridData_10);
        gFileOrderSource.setText(JOE_G_JCNodesForm_FileOrderSources.params(listener.getChainName()));
        final GridLayout gridLayout_1 = new GridLayout();
        gridLayout_1.marginTop = 5;
        gridLayout_1.marginBottom = 5;
        gridLayout_1.numColumns = 5;
        gFileOrderSource.setLayout(gridLayout_1);
        final Label directoryLabel = JOE_L_JCNodesForm_Directory.Control(new Label(gFileOrderSource, SWT.NONE));
        tDirectory = JOE_T_JCNodesForm_Directory.Control(new Text(gFileOrderSource, SWT.BORDER));
        tDirectory.addModifyListener(new ModifyListener() {
            @Override public void modifyText(final ModifyEvent e) {
                bApplyFileOrderSource.setEnabled(isValidSource());
                if (bApplyFileOrderSource.getEnabled())
                    getShell().setDefaultButton(bApplyFileOrderSource);
            }

       
        });
        tDirectory.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
            }
        });
        tDirectory.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        @SuppressWarnings("unused") final Label delay_after_errorLabel = JOE_L_JCNodesForm_DelayAfterError.Control(new Label(gFileOrderSource, SWT.NONE));
        tDelayAfterError = JOE_T_JCNodesForm_DelayAfterError.Control(new Text(gFileOrderSource, SWT.BORDER));
        tDelayAfterError.addModifyListener(new ModifyListener() {
            @Override public void modifyText(final ModifyEvent e) {
                bApplyFileOrderSource.setEnabled(isValidSource());
                if (bApplyFileOrderSource.getEnabled())
                    getShell().setDefaultButton(bApplyFileOrderSource);
            }
        });
        tDelayAfterError.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        bApplyFileOrderSource = JOE_B_JCNodesForm_ApplyFileOrderSource.Control(new Button(gFileOrderSource, SWT.NONE));
        bApplyFileOrderSource.addSelectionListener(new SelectionAdapter() {
            @Override public void widgetSelected(final SelectionEvent e) {
                applyFileOrderSource();
            }
        });
        bApplyFileOrderSource.setEnabled(false);
        bApplyFileOrderSource.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
        final Label regexLabel = JOE_L_JCNodesForm_Regex.Control(new Label(gFileOrderSource, SWT.NONE));
        tRegex = JOE_T_JCNodesForm_Regex.Control(new Text(gFileOrderSource, SWT.BORDER));
        tRegex.addModifyListener(new ModifyListener() {
            @Override public void modifyText(final ModifyEvent e) {
                bApplyFileOrderSource.setEnabled(isValidSource());
                if (bApplyFileOrderSource.getEnabled())
                    getShell().setDefaultButton(bApplyFileOrderSource);
            }
        });
        tRegex.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        @SuppressWarnings("unused") final Label repeatLabel = JOE_L_JCNodesForm_Repeat.Control(new Label(gFileOrderSource, SWT.NONE));
        tRepeat = JOE_T_JCNodesForm_Repeat.Control(new Text(gFileOrderSource, SWT.BORDER));
        tRepeat.addModifyListener(new ModifyListener() {
            @Override public void modifyText(final ModifyEvent e) {
                bApplyFileOrderSource.setEnabled(isValidSource());
                if (bApplyFileOrderSource.getEnabled())
                    getShell().setDefaultButton(bApplyFileOrderSource);
            }
        });
        tRepeat.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        new Label(gFileOrderSource, SWT.NONE);
        @SuppressWarnings("unused") final Label alertWhenDirectoryMissingLabel = JOE_L_JCNodesForm_AlertWhenDirectoryMissing .Control(new Label(gFileOrderSource, SWT.NONE));
        cbAlertWhenDirectoryMissing = new Button(gFileOrderSource, SWT.CHECK);
        cbAlertWhenDirectoryMissing.addSelectionListener(new SelectionAdapter() {
            @Override public void widgetSelected(final SelectionEvent e) {
                bApplyFileOrderSource.setEnabled(isValidSource());
                if (bApplyFileOrderSource.getEnabled())
                    getShell().setDefaultButton(bApplyFileOrderSource);
            }
        });                
        
        @SuppressWarnings("unused") final Label nextStateLabel = JOE_L_JCNodesForm_NextState.Control(new Label(gFileOrderSource, SWT.NONE));

        tNextState = JOE_T_JCNodesForm_NextState.Control(new Text(gFileOrderSource, SWT.BORDER));
        tNextState.addModifyListener(new ModifyListener() {
            @Override public void modifyText(final ModifyEvent e) {
                bApplyFileOrderSource.setEnabled(isValidSource());
                if (bApplyFileOrderSource.getEnabled())
                    getShell().setDefaultButton(bApplyFileOrderSource);
            }
        });
        tNextState.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

        
        tNextState.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        dumm2 = JOE_B_JCNodesForm_RemoveFileOrderSource.Control(new Button(gFileOrderSource, SWT.NONE));
        dumm2.setVisible(false);
        dumm2.setEnabled(false);
        tFileOrderSource = JOE_Tbl_JCNodesForm_FileOrderSource.Control(new Table(gFileOrderSource, SWT.BORDER));
        tFileOrderSource.addSelectionListener(new SelectionAdapter() {
            @Override public void widgetSelected(final SelectionEvent e) {
                if (tFileOrderSource.getSelectionCount() > 0) {
                    listener.selectFileOrderSource(tFileOrderSource);
                    bApplyFileOrderSource.setEnabled(false);
                    fillFileOrderSource();
                    enableFileOrderSource(true);
                }
                bRemoveFileOrderSource.setEnabled(tFileOrderSource.getSelectionCount() > 0);
            }
        });
        tFileOrderSource.setLinesVisible(true);
        tFileOrderSource.setHeaderVisible(true);
        final GridData gridData_9 = new GridData(GridData.FILL, GridData.FILL, true, true, 4, 2);
        gridData_9.minimumHeight = 40;
        gridData_9.heightHint = 138;
        tFileOrderSource.setLayoutData(gridData_9);
        final TableColumn newColumnTableColumn = JOE_TCl_JCNodesForm_Directory.Control(new TableColumn(tFileOrderSource, SWT.NONE));
        newColumnTableColumn.setWidth(300);
        final TableColumn newColumnTableColumn_1 = JOE_TCl_JCNodesForm_Regex.Control(new TableColumn(tFileOrderSource, SWT.NONE));
        newColumnTableColumn_1.setWidth(200);
        final TableColumn newColumnTableColumn_2 = JOE_TCl_JCNodesForm_NextState.Control(new TableColumn(tFileOrderSource, SWT.NONE));
        newColumnTableColumn_2.setWidth(100);
        bNewFileOrderSource = JOE_B_JCNodesForm_NewFileOrderSource.Control(new Button(gFileOrderSource, SWT.NONE));
        bNewFileOrderSource.addSelectionListener(new SelectionAdapter() {
            @Override public void widgetSelected(final SelectionEvent e) {
                getShell().setDefaultButton(null);
                tFileOrderSource.deselectAll();
                listener.selectFileOrderSource(null);
                bRemoveFileOrderSource.setEnabled(false);
                fillFileOrderSource();
                enableFileOrderSource(true);
                tDirectory.setFocus();
            }
        });
        bNewFileOrderSource.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
        bRemoveFileOrderSource = JOE_B_JCNodesForm_RemoveFileOrderSource.Control(new Button(gFileOrderSource, SWT.NONE));
        new Label(gFileOrderSource, SWT.NONE);
        new Label(gFileOrderSource, SWT.NONE);
        bRemoveFileOrderSource.addSelectionListener(new SelectionAdapter() {
            @Override public void widgetSelected(final SelectionEvent e) {
                if (tFileOrderSource.getSelectionCount() > 0) {
                    int index = tFileOrderSource.getSelectionIndex();
                    listener.deleteFileOrderSource(tFileOrderSource);
                    tFileOrderSource.remove(index);
                    if (index >= tFileOrderSource.getItemCount())
                        index--;
                    boolean empty = tFileOrderSource.getItemCount() == 0;
                    fillFileOrderSource();
                    enableFileOrderSource(!empty);
                    bRemoveFileOrderSource.setEnabled(!empty);
                    if (!empty) {
                        tFileOrderSource.select(index);
                        listener.selectFileOrderSource(tFileOrderSource);
                    }
                    else {
                        listener.selectFileOrderSource(null);
                    }
                }
            }
        });
        bRemoveFileOrderSource.setEnabled(false);
        bRemoveFileOrderSource.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, true));        
        

 	}

	  private Shell getShell(){
	        return composite.getShell();
	  }
	  
	  private boolean isValidSource() {
	     if (tDirectory.getText().equals("")) {
	         return false;
	     } else {
	         return true;
	     }
	  }
	    	  
	  public void applyFileOrderSource() {
	      if (bApplyFileOrderSource.getEnabled()){
    	      if (Utils.isRegExpressions(tRegex.getText())) {
    	          listener.applyFileOrderSource(tDirectory.getText(), tRegex.getText(), tNextState.getText(), tRepeat.getText(), tDelayAfterError.getText(),cbAlertWhenDirectoryMissing.getSelection());
    	          listener.fillFileOrderSource(tFileOrderSource);
    	          bApplyFileOrderSource.setEnabled(false);
    	          bRemoveFileOrderSource.setEnabled(false);
    	          listener.selectFileOrderSource(null);
    	          clearFileOrderSource();
    	          enableFileOrderSource(false);
    	      }
    	      else {
    	          MainWindow.message(JOE_M_NoRegex.params(tRegex.getText()), SWT.ICON_INFORMATION);
    	      }
	      }
	  }
	  
	 private void enableFileOrderSource(boolean enable) {
	      tDirectory.setEnabled(enable);
	      tRepeat.setEnabled(enable);
	      tDelayAfterError.setEnabled(enable);
	      tNextState.setEnabled(enable);
	      tRegex.setEnabled(enable);
	      cbAlertWhenDirectoryMissing.setEnabled(enable);
	      bApplyFileOrderSource.setEnabled(false);
	 }	  
	 
	    
	 private void fillFileOrderSource() {
	      
	     tDirectory.setText(listener.getFileOrderSource("directory"));
	     tRegex.setText(listener.getFileOrderSource("regex"));
	     tDelayAfterError.setText(listener.getFileOrderSource("delay_after_error"));
	     tRepeat.setText(listener.getFileOrderSource("repeat"));
	     tNextState.setText(listener.getFileOrderSource("next_state"));
	     cbAlertWhenDirectoryMissing.setSelection(listener.isAlertWhenDirectoryMissing());
	     bApplyFileOrderSource.setEnabled(false);
	 }
	    
	 private void clearFileOrderSource() {
         tDirectory.setText("");
         tRegex.setText("");
	     tDelayAfterError.setText("");
	     tRepeat.setText("");
	     tNextState.setText("");
	     cbAlertWhenDirectoryMissing.setSelection(false);
	     bApplyFileOrderSource.setEnabled(false);
	 }
	 
	 private void initFileOrderSource() {
         listener.fillFileOrderSource(tFileOrderSource);
	     bNewFileOrderSource.setEnabled(true);
	     enableFileOrderSource(false);
	    }
	    

	 
}