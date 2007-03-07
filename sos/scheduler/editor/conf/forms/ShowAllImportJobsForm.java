package sos.scheduler.editor.conf.forms;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator; 
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import sos.scheduler.editor.app.MainWindow;
import sos.scheduler.editor.app.Messages;
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.conf.ISchedulerUpdate;
import sos.scheduler.editor.conf.SchedulerDom;
import sos.scheduler.editor.conf.listeners.JobsListener;
import sos.scheduler.editor.conf.listeners.JobListener;
import org.eclipse.swt.widgets.Table;

public class ShowAllImportJobsForm {
	private Shell shell = null;
	private Text txtTitle;
	private Text txtPath;
	private Tree tree = null;
	
	private String xmlPaths = null;
	private Text txtJobname = null; 
	private JobsListener listener;
	private JobListener joblistener;
	private SchedulerDom dom = null;
	private ISchedulerUpdate update = null;
	/** Parameter: Tabelle aus der JobForm*/
	private Table tParameter = null;
	
	private Button butImport = null; 
	private Button butParameters = null;
	private Button butdescription = null;
	
	/** true -> In der Treeview stehen nur standalone Jobs
	 * false -> In der treeview stehen nur orderjob */
	private String jobType = null; 
	
	private boolean assistent = false;
	
	public ShowAllImportJobsForm(SchedulerDom dom_, ISchedulerUpdate update_) {
		dom = dom_;
		update = update_;
		listener = new JobsListener(dom, update);
	}
	
	
	public ShowAllImportJobsForm(JobListener listener_, Table tParameter_) {
		joblistener = listener_;
		tParameter = tParameter_;		
		
	}
	
	public ArrayList parseDocuments() {
		
		String xmlFilename = "";//"C:/scheduler/test_configs/accarda/ask/jobs/JobSchedulerRenameFile.xml";
		xmlPaths = sos.scheduler.editor.app.Options.getSchedulerHome() ;
		xmlPaths = (xmlPaths.endsWith("/") || xmlPaths.endsWith("\\") ? xmlPaths.concat("jobs") : xmlPaths.concat("/jobs"));
		ArrayList listOfDoc = null;
		try {
			
			listOfDoc = new ArrayList();
			java.util.Vector filelist = sos.util.SOSFile.getFilelist(xmlPaths, "^.*\\.xml$",java.util.regex.Pattern.CASE_INSENSITIVE,true);
			Iterator fileIterator = filelist.iterator();
			
			while (fileIterator.hasNext()) {
				xmlFilename = fileIterator.next().toString();
				//System.out.println("*************************" + ++counter + " " +  xmlFilename);
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder.build( new File( xmlFilename ) );
				Element root = doc.getRootElement();
				List listMainElements = root.getChildren();  
				HashMap h = null;
				for( int i=0; i<listMainElements.size(); i++ ){					
					Element elMain  = (Element)(listMainElements.get( i ));
					if(elMain.getName().equalsIgnoreCase("job")) {
						//System.out.println("Name= " + elMain.getAttributeValue("name") + ", title = " + elMain.getAttributeValue("title"));
						h = new HashMap();
						h.put("name", elMain.getAttributeValue("name"));
						h.put("title", elMain.getAttributeValue("title"));
						h.put("filename", xmlFilename);
						h.put("job", elMain);
						listOfDoc.add(h);						
					}							
				}
			}
			
			
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		return listOfDoc;
	}
	
	public void showAllImportJobs(String type_, boolean assistent_) {
		jobType = type_;
		assistent = assistent_;
		showAllImportJobs();
	}
	
	public void showAllImportJobs() {
		try {
			
			shell = new Shell(SWT.CLOSE | SWT.TITLE | SWT.APPLICATION_MODAL | SWT.BORDER);
			final GridLayout gridLayout = new GridLayout();
			shell.setLayout(gridLayout);
			shell.setText("Import Jobs");
			
			final Group group_1 = new Group(shell, SWT.BORDER);
			final GridLayout gridLayout_3 = new GridLayout();
			gridLayout_3.numColumns = 2;
			group_1.setLayout(gridLayout_3);
			group_1.setLayoutData(new GridData(595, 133));
			Label jobnameLabel;
			
			Label lblPath;
			Composite composite;
			{
				jobnameLabel = new Label(group_1, SWT.NONE);
				jobnameLabel.setText("Jobname");
			}
			{
				txtJobname = new Text(group_1, SWT.BORDER);
				final GridData gridData = new GridData(GridData.FILL, GridData.CENTER, false, false);
				gridData.widthHint = 498;
				txtJobname.setLayoutData(gridData);
				txtJobname.setText("");
			}
			
			final Label lblTitle = new Label(group_1, SWT.NONE);
			lblTitle.setText("Title");
			
			txtTitle = new Text(group_1, SWT.BORDER);
			final GridData gridData = new GridData(GridData.FILL, GridData.CENTER, false, false);
			gridData.widthHint = 510;
			txtTitle.setLayoutData(gridData);
			lblPath = new Label(group_1, SWT.NONE);
			lblPath.setText("Path");
			
			txtPath = new Text(group_1, SWT.BORDER);
			txtPath.setEditable(false);
			final GridData gridData_1 = new GridData(GridData.FILL, GridData.CENTER, false, false);
			gridData_1.widthHint = 400;
			txtPath.setLayoutData(gridData_1);
			{
				composite = new Composite(group_1, SWT.NONE);
				composite.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1));
				final GridLayout gridLayout_2 = new GridLayout();
				gridLayout_2.numColumns = 5;
				composite.setLayout(gridLayout_2);
				
				/*if(assistent) {
					final Button butFinish = new Button(composite, SWT.NONE);
					butFinish.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(final SelectionEvent e) {
							if(tree.getSelectionCount()== 0) {
								int cont = MainWindow.message(shell, sos.scheduler.editor.app.Messages.getString("no_selected_tree"), SWT.ICON_WARNING | SWT.OK );
								return;
							}
							if(listener != null) {
								if(txtJobname.getText() == null || txtJobname.getText().length() == 0) {
									int cont = MainWindow.message(shell, sos.scheduler.editor.app.Messages.getString("no_jobname"), SWT.ICON_WARNING | SWT.OK );
									txtJobname.setFocus();
									return;
								}						
								
								if(txtJobname.getText().concat(".xml").equalsIgnoreCase(new File(txtPath.getText()).getName())) {
									int cont = MainWindow.message(shell, sos.scheduler.editor.app.Messages.getString("edit_jobname"), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
									if(cont == SWT.YES) {
										txtJobname.setFocus();
										return;
									}						
								}
							}
							HashMap attr = getJobFromDescription();
							ShowAllImportJobParamsForm defaultParams = new ShowAllImportJobParamsForm();
							ArrayList listOfParams = defaultParams.parseDocuments(txtPath.getText());							
							attr.put("params", listOfParams);
							Element job = listener.createJobElement(attr);						
							listener.newImportJob(job);
							shell.dispose();
						}
					});
					butFinish.setText("Finish");
				}
				*/
				{
					butImport = new Button(composite, SWT.NONE);				
					butImport.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(final SelectionEvent e) {
							
							//System.out.println("importieren");
							if(tree.getSelectionCount()== 0) {
								int cont = MainWindow.message(shell, sos.scheduler.editor.app.Messages.getString("no_selected_tree"), SWT.ICON_WARNING | SWT.OK );
								return;
							}
							if(listener != null) {
								if(txtJobname.getText() == null || txtJobname.getText().length() == 0) {
									int cont = MainWindow.message(shell, sos.scheduler.editor.app.Messages.getString("no_jobname"), SWT.ICON_WARNING | SWT.OK );
									txtJobname.setFocus();
									return;
								}						
								
								if(txtJobname.getText().concat(".xml").equalsIgnoreCase(new File(txtPath.getText()).getName())) {
									int cont = MainWindow.message(shell, sos.scheduler.editor.app.Messages.getString("edit_jobname"), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
									if(cont == SWT.YES) {
										txtJobname.setFocus();
										return;
									}						
								}
							}
							HashMap h = getJobFromDescription();
							
							ShowAllImportJobParamsForm defaultParams = new ShowAllImportJobParamsForm();
							ArrayList listOfParams = defaultParams.parseDocuments(txtPath.getText());							
							h.put("params", listOfParams);
							
							
							if(listener != null) {
								listener.newImportJob(h);
							} else if(joblistener != null) {							
								joblistener.fillParams(listOfParams, tParameter);
							}
							
							shell.close();
						}
					});
					butImport.setLayoutData(new GridData(GridData.BEGINNING, GridData.END, false, true));
				}
				butImport.setText("Finish");
				final Button butCancel = new Button(composite, SWT.NONE);
				butCancel.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(final SelectionEvent e) {
						shell.dispose();
					}
				});
				butCancel.setText("Cancel");
				
				butParameters = new Button(composite, SWT.NONE);
				
				butParameters.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(final SelectionEvent e) {
						if(txtJobname.getText().length() == 0) {
							int cont = MainWindow.message(shell, sos.scheduler.editor.app.Messages.getString("no_jobname"), SWT.ICON_WARNING | SWT.OK );
							txtJobname.setFocus();
							return;
						}
						
						if(listener != null) {
							if(txtJobname.getText().concat(".xml").equalsIgnoreCase(new File(txtPath.getText()).getName())) {
								int cont = MainWindow.message(shell, sos.scheduler.editor.app.Messages.getString("edit_jobname"), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
								if(cont == SWT.YES) {
									txtJobname.setFocus();
									return;
								}						
							}
						}
						
						if(txtPath.getText().length() == 0) {
							int cont = MainWindow.message(shell, sos.scheduler.editor.app.Messages.getString("no_jobdescription"), SWT.ICON_WARNING | SWT.OK );
							txtPath.setFocus();
							return;
						}												
						
						HashMap h = getJobFromDescription();
						if(assistent) {
							ShowAllImportJobParamsForm paramsForm = new ShowAllImportJobParamsForm(dom, update, h);					
							paramsForm.showAllImportJobParams(txtPath.getText(), assistent);
						}else if(listener != null) {
							ShowAllImportJobParamsForm paramsForm = new ShowAllImportJobParamsForm(dom, update, h);					
							paramsForm.showAllImportJobParams(txtPath.getText());
						} else {
							ShowAllImportJobParamsForm paramsForm = new ShowAllImportJobParamsForm(joblistener.get_dom(), joblistener.get_main(), joblistener, tParameter, h);					
							paramsForm.showAllImportJobParams(txtPath.getText());
						}												
						
						shell.close();
					}
				});
				butParameters.setText("Parameters");
				
				if(assistent) {
					butParameters.setText("Next");
				} else if(this.joblistener != null) {					
					butParameters.setText("Import Parameter");
					this.butImport.setVisible(false);
				} else {
					//butParameters.setVisible(true);
					this.butImport.setVisible(true);
					butImport.setText("Import");
				}
				{
					butdescription = new Button(composite, SWT.NONE);
					butdescription.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(final SelectionEvent e) {
							try  {							
								Process p =Runtime.getRuntime().exec("cmd /C START iExplore ".concat(txtPath.getText())); 							
							} catch(Exception ex) {
								System.out.println("..could not open description " + txtJobname.getText() + " " + ex);							
							}						
						}
					});
					butdescription.setLayoutData(new GridData());
					butdescription.setText("Description");
				}
				
				if(assistent) {
					final Button butShow = new Button(composite, SWT.NONE);
					butShow.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(final SelectionEvent e) {
							HashMap attr = getJobFromDescription();
							ShowAllImportJobParamsForm defaultParams = new ShowAllImportJobParamsForm();
							ArrayList listOfParams = defaultParams.parseDocuments(txtPath.getText());							
							attr.put("params", listOfParams);
							Element job = listener.createJobElement(attr);
							MainWindow.message(shell, Utils.getElementAsString(job), SWT.OK );
						}
					});
					butShow.setText("Show");
				}
				
			}
			{
				final Group jobnamenGroup = new Group(shell, SWT.NONE);
				final GridLayout gridLayout_1 = new GridLayout();
				jobnamenGroup.setLayout(gridLayout_1);
				jobnamenGroup.setText("jobnamen");
				jobnamenGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
						| GridData.FILL_VERTICAL));
				
				{				
					tree = new Tree(jobnamenGroup, SWT.FULL_SELECTION | SWT.BORDER);
					tree.setHeaderVisible(true);
					//tree.setToolTipText(tree.getSelection().length > 0 && tree.getSelection()[0].getData() != null ? tree.getSelection()[0].getData().toString():"");
					tree.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(final SelectionEvent e) {
							//System.out.println("Hallo " + tree.getSelection()[0].getText());
							txtJobname.setText(tree.getSelection()[0].getText());
							txtTitle.setText(tree.getSelection()[0].getText(1));
							txtPath.setText(tree.getSelection()[0].getText(2));
						}
					});
					tree.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
					TreeColumn column1 = new TreeColumn(tree, SWT.LEFT);
					column1.setText("Name");					
					column1.setWidth(200);				
					TreeColumn column2 = new TreeColumn(tree, SWT.LEFT);
					column2.setText("Title");					
					column2.setWidth(200);
					TreeColumn column3 = new TreeColumn(tree, SWT.LEFT);
					column3.setText("Filename");					
					column3.setWidth(200);
					{					
						try {
							createTreeIteam();
						} catch (Exception e) {
							System.err.print(e.getMessage());
						}						
					}
					
					
				}
			}
			setToolTipText();
			shell.layout();
			shell.pack();
			shell.open();
			
		} catch(Exception e) {
			System.err.println("error in ShowAllImportJobsForm.showAllImportJob(): " + e.getMessage());
		}
	}
	
	private void createTreeIteam() throws Exception { 
		try {
			ArrayList listOfDoc = parseDocuments();
			String filename = "";
			String lastParent = "";
			TreeItem parentItemTreeItem =null;
			boolean loop = true;
			for (int i = 0; i < listOfDoc.size(); i++) {
				HashMap h = (HashMap)listOfDoc.get(i);
				loop = true;
				if(jobType != null && jobType.equals("order")){
					Element job = (Element)h.get("job");
					if(!(Utils.getAttributeValue("order", job).equals("yes") ||
							Utils.getAttributeValue("order", job).equals("both"))) {
						loop = false;
					} 
				} else if(jobType != null && jobType.equals("standalonejob")){
					Element job = (Element)h.get("job");
					if(!(Utils.getAttributeValue("order", job).equals("no") ||
							Utils.getAttributeValue("order", job).equals("both"))) {
						loop = false;
					}
				}
				if(loop) {
					filename = h.get("filename").toString();
					if(new File(filename).getParentFile().equals(new File(xmlPaths))) {							
						final TreeItem newItemTreeItem = new TreeItem(tree, SWT.NONE);
						newItemTreeItem.setText(0, h.get("name").toString());
						newItemTreeItem.setText(1, h.get("title").toString());
						newItemTreeItem.setText(2, filename);
						newItemTreeItem.setData(h.get("job"));
					} else {
						//Kindknoten
						//System.out.println("Kindknoten: " + filename);
						if(!lastParent.equalsIgnoreCase(new File(filename).getParentFile().getPath())) {						
							if(!new File(lastParent).getName().equals(tree.getItems()[tree.getItems().length -1].getText())) {
								parentItemTreeItem = new TreeItem(tree, SWT.NONE);
								parentItemTreeItem.setText(0, new File(filename).getParentFile().getName());
								lastParent = new File(filename).getParentFile().getPath();
							} else {
								parentItemTreeItem = new TreeItem(parentItemTreeItem, SWT.NONE);
								parentItemTreeItem.setText(0, new File(filename).getParentFile().getName());
								lastParent = new File(filename).getParentFile().getPath();
							}
						}
						
						final TreeItem newItemTreeItem = new TreeItem(parentItemTreeItem, SWT.NONE);
						newItemTreeItem.setText(0, h.get("name").toString());
						newItemTreeItem.setText(1, h.get("title").toString());
						newItemTreeItem.setText(2, filename);						
					}
				}
			}
		} catch(Exception e) {
			System.out.println("error in ShowAllImportJobsForm.createTreeIteam(): " + e.getMessage());
		}
	}
	
	public void setToolTipText() {
		butImport.setToolTipText(Messages.getTooltip("butImport"));
		butParameters.setToolTipText(Messages.getTooltip("butParameters"));
		butdescription.setToolTipText(Messages.getTooltip("butdescription"));
		tree.setToolTipText(Messages.getTooltip("tree"));
		txtJobname.setToolTipText(Messages.getTooltip("jobname"));
		txtTitle.setToolTipText(Messages.getTooltip("jobtitle"));
		txtPath.setToolTipText(Messages.getTooltip("jobdescription"));		
		
	}
	
	/**
	 * Felder und Attribute werden aus der Jobdokumnetation genommen und in eine hashMap gepackt.
	 * @return HashMap
	 */
	private HashMap getJobFromDescription() {
		HashMap h = new HashMap();
		try {
//			elMain ist ein Job Element der Jobbeschreibung 
			Element elMain = (Element)tree.getSelection()[0].getData();
			//Attribute der Job bestimmen
			h.put("order", (elMain.getAttributeValue("order") != null && elMain.getAttributeValue("order").equals("both") ? "yes" : elMain.getAttributeValue("order")));
			h.put("tasks", elMain.getAttributeValue("tasks"));			
			h.put("name", txtJobname.getText());
			h.put("title", txtTitle.getText());
			
			//relativen phad bestimmen
			String sHome = sos.scheduler.editor.app.Options.getSchedulerHome().replaceAll("/", "\\\\");
			String currPath = txtPath.getText().replaceAll("/", "\\\\"); 
			if(currPath.indexOf(sHome) > -1) {
				h.put("filename", currPath.substring(sHome.length() + 1));
			} else {
				h.put("filename", txtPath.getText());
			}			
			
			//Element script
			Element script = elMain.getChild("script", elMain.getNamespace());
			if(script != null) {
				//hilfsvariable: es gibt script informationen
				h.put("script", "script");
				
				if(script.getAttributeValue("language") != null)
					h.put("script_language", script.getAttributeValue("language"));								
				
				if(script.getAttributeValue("java_class") != null)					
					h.put("script_java_class", script.getAttributeValue("java_class"));
				
				if(script.getAttributeValue("com_class") != null) 
					h.put("script_com_class", script.getAttributeValue("com_class"));
				
				if(script.getAttributeValue("filename") != null) 
					h.put("script_filename", script.getAttributeValue("filename"));
				
				if(script.getAttributeValue("use_engine") != null) 
					h.put("script_use_engine", script.getAttributeValue("use_engine"));
				
				
				//script includes bestimmen
				List comClassInclude = script.getChildren("include", elMain.getNamespace());
				ArrayList listOfIncludeFilename = new ArrayList();
				for (int i = 0; i < comClassInclude.size(); i++ ) {
					Element inc = (Element)comClassInclude.get(i);
					listOfIncludeFilename.add(inc.getAttribute("file").getValue());
				}
				h.put("script_include_file", listOfIncludeFilename);																	
			}		
			
			//Element monitor
			Element monitor = elMain.getChild("monitor", elMain.getNamespace());
			if(monitor != null) {
				//hilfsvariable: es gibt Monito Informationen
				h.put("monitor", "monitor");
				Element mon_script = monitor.getChild("script", elMain.getNamespace());
				if(mon_script != null) {
					if(mon_script.getAttributeValue("language") != null)
						h.put("monitor_script_language", mon_script.getAttributeValue("language"));								
					
					if(mon_script.getAttributeValue("java_class") != null)					
						h.put("monitor_script_java_class", mon_script.getAttributeValue("java_class"));
					
					if(mon_script.getAttributeValue("com_class") != null) 
						h.put("monitor_script_com_class", mon_script.getAttributeValue("com_class"));
					
					if(mon_script.getAttributeValue("filename") != null) 
						h.put("monitor_script_filename", mon_script.getAttributeValue("filename"));
					
					if(mon_script.getAttributeValue("use_engine") != null) 
						h.put("monitor_script_use_engine", mon_script.getAttributeValue("use_engine"));
					
					
					//script monitor includes bestimmen
					List comClassInclude = mon_script.getChildren("include", elMain.getNamespace());
					ArrayList listOfIncludeFilename = new ArrayList();
					for (int i = 0; i < comClassInclude.size(); i++ ) {
						Element inc = (Element)comClassInclude.get(i);
						listOfIncludeFilename.add(inc.getAttribute("file").getValue());
					}
					h.put("monitor_script_include_file", listOfIncludeFilename);																	
				}	
			}
			//Element process aus der Dokumentation zu Execute aus der Konfiguration 
			Element process = elMain.getChild("process", elMain.getNamespace());
			if(process != null) {
				h.put("process", "process"); //hilfsvariable: es gibt proces Informationen
				if(process.getAttributeValue("file") != null) 
					h.put("process_file", process.getAttributeValue("file"));
				
				if(process.getAttributeValue("param") != null) 					
					h.put("process_param", process.getAttributeValue("param"));
				
				if(process.getAttributeValue("log") != null) 
					h.put("process_log", process.getAttributeValue("log"));
				
//				environment Variablen bestimmen
				Element environment = process.getChild("environment", elMain.getNamespace());
				if(environment != null) {
					List listOfEnvironment = environment.getChildren("variable", elMain.getNamespace());
					ArrayList listOfIncludeFilename = new ArrayList();
					for (int i = 0; i < listOfEnvironment.size(); i++ ) {
						HashMap hEnv = new HashMap();
						Element env = (Element)listOfEnvironment.get(i);
						hEnv.put("name", (env.getAttribute("name") != null ? env.getAttribute("name").getValue() :""));
						hEnv.put("value", (env.getAttribute("value") != null ? env.getAttribute("value").getValue() :""));						
						listOfIncludeFilename.add(hEnv);
					}
					h.put("process_environment", listOfIncludeFilename);
				}
			}
			
		} catch (Exception e) {
			System.out.println("..error in ShowAllImportJobsForm.getJobFromDescription() " + e.getMessage());
		}
		return h;
	}
	
}
