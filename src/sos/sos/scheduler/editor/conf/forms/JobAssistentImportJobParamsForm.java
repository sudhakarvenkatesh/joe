package sos.scheduler.editor.conf.forms;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.jdom.Element;

import sos.scheduler.editor.app.Editor;
import sos.scheduler.editor.app.ErrorLog;
import sos.scheduler.editor.app.MainWindow;
import sos.scheduler.editor.app.Messages;
import sos.scheduler.editor.app.Options;
import sos.scheduler.editor.app.ResourceManager;
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.conf.ISchedulerUpdate;
import sos.scheduler.editor.conf.SchedulerDom;
import sos.scheduler.editor.conf.listeners.JobListener;
import sos.scheduler.editor.conf.listeners.JobsListener;
import sos.scheduler.editor.conf.listeners.ParameterListener;

import com.sos.JSHelper.io.Files.JSXMLFile;
import com.sos.i18n.annotation.I18NResourceBundle;
import com.sos.scheduler.model.xmldoc.Description;
import com.sos.scheduler.model.xmldoc.Description.Configuration.Params;
import com.sos.scheduler.model.xmldoc.Note;
import com.sos.scheduler.model.xmldoc.Param;
import com.swtdesigner.SWTResourceManager;

@I18NResourceBundle(baseName = "JOEMessages", defaultLocale = "en")
public class JobAssistentImportJobParamsForm {
	@SuppressWarnings("unused")
	private final String											conClsName									= "JobAssistentImportJobParamsForm";
	@SuppressWarnings("unused")
	private final String											conSVNVersion								= "$Id$";
	private static final Logger										logger										= Logger.getLogger(JobAssistentImportJobParamsForm.class);
	private static final String										conMsgKeyASSISTENT_JOBPARAMETER_NO_SELECTED	= "assistent.jobparameter.no_selected";
	private static final String										conMsgKeyASSISTENT_JOBPARAMETER_EXIST		= "assistent.jobparameter.exist";
	private static final String										conMsgKeyMAIN_LISTENER_APPLY_CHANGES		= "MainListener.apply_changes";
	private static final String										conMsgKeyASSISTENT_JOBPARAMETER_REQUIRED	= "assistent.jobparameter.required";
	private static final String										conKeyPARAMETER_DESCRIPTION_				= "parameter_description_";
	private static final String										conKeyPARAMETER_DESCRIPTION_EN				= "parameter_description_en";
	private static final String										conKeyPARAMETER_DESCRIPTION_DE				= "parameter_description_de";
	public static final String										conSystemPropertyUSER_DIR					= "user.dir";
	private static final String										conStringFALSE								= "false";
	private static final String										conParamAttributeDEFAULT_VALUE				= "default_value";
	private static final String										conParamAttributeREQUIRED					= "required";
	private static final String										conParamNAME								= "name";
	private static final String										conTRUE										= "true";
	private final static String										conClassName								= "JobAssistentImportJobParamsForm";
	private static JAXBContext										context										= null;
	private static Unmarshaller										unmarshaller								= null;
	// private Text txtDescription = null;
	private Browser													txtDescription								= null;
	private Table													tblSelectedParams							= null;
	private Table													tableDescParameters							= null;
	private Shell													jobParameterShell							= null;
	private Text													txtValue									= null;
	private String													xmlPaths									= null;
	private Text													txtName										= null;
	private JobListener												joblistener									= null;
	// Tabelle aus der JobForm: Falls die Klasse �ber den Import Button vom JobFrom erfolgte
	private Table													tParameter									= null;
	private Button													butFinish									= null;
	private Button													butApply									= null;
	private Button													butNext										= null;
	private Button													butBack										= null;
	private ArrayList<HashMap<String, Object>>						listOfParams								= new ArrayList<HashMap<String, Object>>();
	private SchedulerDom											dom											= null;
	private ISchedulerUpdate										update										= null;
	private Button													butCancel									= null;
	private Button													showButton									= null;
	/** Wer hat ihn aufgerufen, der Job assistent oder job_chain assistent*/
	private int														assistentType								= -1;
	private Button													butPut										= null;
	private Button													butPutAll									= null;
	private Button													butRemove									= null;
	private Button													butRemoveAll								= null;
	private Combo													jobname										= null;
	private Element													jobBackUp									= null;
	private JobMainForm												jobForm										= null;
	private JobDocumentationForm									jobDocForm									= null;
	/** Hilsvariable f�r das Schliessen des Dialogs. 
	 * Das wird gebraucht wenn das Dialog �ber den "X"-Botten (oben rechts vom Dialog) geschlossen wird .*/
	private boolean													closeDialog									= false;
	private sos.scheduler.editor.conf.listeners.ParameterListener	paramListener								= null;
	private Text													refreshDetailsText							= null;

	public JobAssistentImportJobParamsForm() {
	}

	public JobAssistentImportJobParamsForm(SchedulerDom dom_, ISchedulerUpdate update_, Element job_, int assistentType_) {
		dom = dom_;
		update = update_;
		assistentType = assistentType_;
		joblistener = new JobListener(dom, job_, update);
		paramListener = new ParameterListener(dom, job_, update_, assistentType);
	}

	public JobAssistentImportJobParamsForm(SchedulerDom dom_, ISchedulerUpdate update_, JobListener joblistener_, int assistentType_) {
		dom = dom_;
		update = update_;
		joblistener = joblistener_;
		jobBackUp = (Element) joblistener.getJob().clone();
		this.assistentType = assistentType_;
		paramListener = new ParameterListener(dom, joblistener_.getJob(), update_, assistentType);
	}

	public JobAssistentImportJobParamsForm(SchedulerDom dom_, ISchedulerUpdate update_, JobListener joblistener_, Table tParameter_, int assistentType_) {
		dom = dom_;
		update = update_;
		joblistener = joblistener_;
		jobBackUp = (Element) joblistener.getJob().clone();
		tParameter = tParameter_;
		this.assistentType = assistentType_;
		paramListener = new ParameterListener(dom, joblistener_.getJob(), update_, assistentType);
	}

	public ArrayList<HashMap<String, Object>> parseDocuments(String xmlFilename, String type) {
		// Wizard ohne Jobbeschreibung starten
		if (xmlFilename == null || xmlFilename.trim().length() == 0)
			return new ArrayList<HashMap<String, Object>>();
		xmlPaths = sos.scheduler.editor.app.Options.getSchedulerData();
		if (!xmlFilename.replaceAll("\\\\", "/").startsWith(xmlPaths.replaceAll("\\\\", "/")))
			xmlFilename = (xmlPaths.endsWith("/") || xmlPaths.endsWith("\\") ? xmlPaths.concat(xmlFilename) : xmlPaths.concat("\\").concat(xmlFilename));
		xmlFilename = xmlFilename.replaceAll("\\\\", "/");
		ArrayList<HashMap<String, Object>> listOfParams1 = null;
		try {
			listOfParams1 = new ArrayList<HashMap<String, Object>>();
			if (!new File(xmlFilename).exists()) {
				MainWindow.message(jobParameterShell, "JobDescription File does not exist: " + xmlFilename, SWT.OK);
				return listOfParams1;
			}
			Params objParams = getParamList(xmlFilename);
			if (objParams == null) {
				logger.debug("return, due to objParams==null");
				return listOfParams1;
			}
			// Use a Transformer for output
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			HashMap<String, Object> h = null;
			for (int i = 0; i < objParams.getParam().size(); i++) {
				Param objParam = objParams.getParam().get(i);
				// if (elMain.getName().equalsIgnoreCase("param") && (type.length() == 0 ||
				// elMain.getAttributeValue(type).equalsIgnoreCase("true"))) {
				h = new HashMap<String, Object>();
				h.put(conParamNAME, objParam.getName());
				h.put(conParamAttributeDEFAULT_VALUE, objParam.getDefaultValue());
				String strIsRequired = conStringFALSE;
				if (objParam.isRequired() == true) {
					strIsRequired = conTRUE;
				}
				h.put(conParamAttributeREQUIRED, strIsRequired);
				String strLanguage = Options.getLanguage();
				// download : http://mirrors.ibiblio.org/pub/mirrors/maven2/com/sun/org/apache/jaxp-ri/1.4/jaxp-ri-1.4.jar
				DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance("com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl",
						this.getClass().getClassLoader());
				DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
				org.w3c.dom.Document doc = docBuilder.newDocument();
				// //////////////////////
				// Creating the XML tree
				// create the root element and add it to the document
				doc.setXmlStandalone(true);
				doc.setXmlVersion("1.0");
				org.w3c.dom.Element root = doc.createElement("param");
				doc.appendChild(root);
				//
				for (int j = 0; j < objParam.getNote().size(); j++) {
					Note objNote = objParam.getNote().get(j);
					int intSize = objNote.getContent().size();
					for (int k = 0; k < intSize; k++) {
						if (objNote.getLanguage().equalsIgnoreCase(strLanguage)) {
							Object objO = objNote.getContent().get(k);
							if (objO instanceof org.apache.xerces.dom.ElementNSImpl) {
								org.apache.xerces.dom.ElementNSImpl objElem = (org.apache.xerces.dom.ElementNSImpl) objO;
								boolean flgRecursivelyImportTheSubtree = true;
								root.appendChild(doc.importNode(objElem, flgRecursivelyImportTheSubtree));
							} // if (objO instanceof org.apache.xerces.dom.ElementNSImpl)
						} // if (objNote.getLanguage().equalsIgnoreCase("en"))
					} // for (int k = 0
					DOMSource source = new DOMSource(root);
					ByteArrayOutputStream bout = new ByteArrayOutputStream();
					StreamResult result = new StreamResult(bout);
					// TODO workaround, i don't know how to get the encoding of the original xml-document from SAXB
					transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
					transformer.transform(source, result);
					String strTempXML = bout.toString();
					logger.debug(strTempXML);
					h.put(conKeyPARAMETER_DESCRIPTION_ + strLanguage, strTempXML);
				}
				listOfParams1.add(h);
			}
		}
		catch (Exception ex) {
			try {
				MainWindow.message(jobParameterShell, "error in " + sos.util.SOSClassUtil.getMethodName() + ex.getLocalizedMessage(), SWT.OK);
				new ErrorLog("error in " + sos.util.SOSClassUtil.getMethodName(), ex);
			}
			catch (Exception ee) {
				// tu nichts
			}
			ex.printStackTrace(System.err);
		}
		return listOfParams1;
	}

	// TODO in eine JSDocDescription-Klasse einbauen
	private Params getParamList(final String pstrDocuFileName) throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::getParamList";
		String strUserDir = System.getProperty(conSystemPropertyUSER_DIR);
		Params objParams = null;
		try {
			JSXMLFile fleFile = new JSXMLFile(pstrDocuFileName);
			System.setProperty(conSystemPropertyUSER_DIR, fleFile.getParent());
			context = JAXBContext.newInstance(Description.class);
			// TODO how to tell jaxb that xinclude must be resolved?
			// saxB.setFeature("http://apache.org/xml/features/validation/schema", true);
			// saxB.setFeature("http://apache.org/xml/features/xinclude", true);
			unmarshaller = context.createUnmarshaller();
			File objTempFile = File.createTempFile("sosxmldoc", ".xml");
			objTempFile.deleteOnExit();
			String strTempFileName = objTempFile.getAbsolutePath();
			fleFile.writeDocument(strTempFileName);
			Description objDescr = (Description) unmarshaller.unmarshal(objTempFile);
			objParams = objDescr.getConfiguration().getParams();
		}
		catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			System.setProperty(conSystemPropertyUSER_DIR, strUserDir);
		}
		return objParams;
	} // private void getParamList

	

	/**
	 * 
	 * @param xmlFilename -> Job Dokumentation
	 */
	public void showAllImportJobParams(String xmlFilename) {
		try {
			jobParameterShell = new Shell(MainWindow.getSShell(), SWT.CLOSE | SWT.TITLE | SWT.APPLICATION_MODAL | SWT.BORDER | SWT.RESIZE);
			jobParameterShell.addShellListener(new ShellAdapter() {
				public void shellClosed(final ShellEvent e) {
					if (!closeDialog)
						close();
					e.doit = jobParameterShell.isDisposed();
				}
			});
			jobParameterShell.setImage(ResourceManager.getImageFromResource("/sos/scheduler/editor/editor.png"));
			java.awt.Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			jobParameterShell.setBounds((screen.width - jobParameterShell.getBounds().width) / 2, (screen.height - jobParameterShell.getBounds().height) / 2, 1,
					jobParameterShell.getBounds().height);
	//		jobParameterShell.setSize(MainWindow.getSShell().getSize().x,MainWindow.getSShell().getSize().y);

			final GridLayout gridLayout = new GridLayout();
			
			jobParameterShell.setLayout(gridLayout);
			String step = "  ";
			if (Utils.getAttributeValue("order", joblistener.getJob()).equalsIgnoreCase("yes"))
				step = step + " [Step 3 of 9]";
			else
				step = step + " [Step 3 of 8]";
			jobParameterShell.setText("Job Parameter" + step);
			Label nameLabel;
			final Group textParameterGroup = new Group(jobParameterShell, SWT.NONE);
			textParameterGroup.setText(" Job " + Utils.getAttributeValue(conParamNAME, joblistener.getJob()));
			final GridData gridData_3 = new GridData(GridData.FILL, GridData.FILL, true, true);
			gridData_3.minimumWidth = -1;
			textParameterGroup.setLayoutData(gridData_3);
			final GridLayout gridLayout_3 = new GridLayout();
			gridLayout_3.marginTop = 5;
			gridLayout_3.marginWidth = 10;
			gridLayout_3.marginRight = 10;
			gridLayout_3.marginLeft = 10;
			gridLayout_3.marginHeight = 10;
			gridLayout_3.marginBottom = 10;
			gridLayout_3.numColumns = 5;
			textParameterGroup.setLayout(gridLayout_3);
			final Composite composite_3 = new Composite(textParameterGroup, SWT.NONE);
			final GridData gridData_4 = new GridData(GridData.FILL, GridData.END, true, false, 5, 1);
			gridData_4.minimumHeight = 30;
			composite_3.setLayoutData(gridData_4);
			final GridLayout gridLayout_1 = new GridLayout();
			gridLayout_1.numColumns = 5;
			composite_3.setLayout(gridLayout_1);
			final Composite composite = new Composite(composite_3, SWT.NONE);
			composite.setLayoutData(new GridData(GridData.BEGINNING, GridData.FILL, true, true));
			final GridLayout gridLayout_4 = new GridLayout();
			gridLayout_4.marginWidth = 0;
			composite.setLayout(gridLayout_4);
			butCancel = new Button(composite, SWT.NONE);
			butCancel.setLayoutData(new GridData());
			butCancel.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					close();
				}
			});
			butCancel.setText("Cancel");
			final Composite composite_1 = new Composite(composite_3, SWT.NONE);
			composite_1.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, true));
			final GridLayout gridLayout_2 = new GridLayout();
			gridLayout_2.numColumns = 2;
			gridLayout_2.marginWidth = 0;
			composite_1.setLayout(gridLayout_2);
			showButton = new Button(composite_1, SWT.NONE);
			showButton.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));
			showButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					Utils.showClipboard(Utils.getElementAsString(joblistener.getJob()), jobParameterShell, false, null, false, null, false);
				}
			});
			showButton.setText("Show");
			if (assistentType == Editor.JOB)
				showButton.setVisible(false);
			butFinish = new Button(composite_1, SWT.NONE);
			butFinish.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));
			butFinish.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					if (assistentType == Editor.PARAMETER) {
						tParameter.removeAll();
						paramListener.fillParams(tParameter);
					}
					else
						if (assistentType == Editor.JOB || assistentType == Editor.JOB_WIZARD) {
							if (jobForm != null)
								jobForm.initForm();
							if (jobDocForm != null)
								jobDocForm.initForm();
						}
						else
							if (assistentType == Editor.JOB_CHAINS || assistentType == Editor.JOBS) {
								if (jobname != null)
									jobname.setText(Utils.getAttributeValue(conParamNAME, joblistener.getJob()));
								JobsListener listener = new JobsListener(dom, update);
								listener.newImportJob(joblistener.getJob(), assistentType);
							}
					if (Options.getPropertyBoolean("editor.job.show.wizard"))
						Utils.showClipboard(Utils.getElementAsString(joblistener.getJob()), jobParameterShell, false, null, false, null, true);
					// Event ausl�sen
					if (refreshDetailsText != null)
						refreshDetailsText.setText("X");
					closeDialog = true;
					jobParameterShell.dispose();
				}
			});
			butFinish.setText("Finish");
			butBack = new Button(composite_3, SWT.NONE);
			butBack.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, true));
			butBack.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					JobAssistentImportJobsForm importJobs = new JobAssistentImportJobsForm(dom, update, assistentType);
					if (jobname != null)
						importJobs.setJobname(jobname);
					importJobs.setBackUpJob(jobBackUp, jobForm);
					importJobs.showAllImportJobs(joblistener);
					closeDialog = true;
					jobParameterShell.dispose();
				}
			});
			butBack.setText("Back");
			butBack.setEnabled(false);
			butNext = new Button(composite_3, SWT.NONE);
			butNext.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, true));
			butNext.setFocus();
			butNext.setFont(SWTResourceManager.getFont("", 8, SWT.BOLD));
			butNext.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					Utils.startCursor(jobParameterShell);
					if (assistentType != Editor.JOB) {
						JobAssistentTasksForm tasks = new JobAssistentTasksForm(dom, update, joblistener.getJob(), assistentType);
						tasks.showTasksForm();
						if (jobname != null)
							tasks.setJobname(jobname);
						tasks.setBackUpJob(jobBackUp, jobForm);
					}
					closeDialog = true;
					Utils.startCursor(jobParameterShell);
					jobParameterShell.dispose();
				}
			});
			butNext.setText(" Next ");
			butNext.setEnabled(false);
			if (assistentType == Editor.JOB || assistentType == Editor.PARAMETER) {
				butNext.setEnabled(false);
				butBack.setEnabled(false);
			}
			else {
				butNext.setEnabled(true);
				butBack.setEnabled(true);
			}
			Utils.createHelpButton(composite_3, "assistent.parameters", jobParameterShell);
			final Label label_1 = new Label(textParameterGroup, SWT.BORDER);
			final GridData gridData_6_1 = new GridData(GridData.FILL, GridData.BEGINNING, false, false, 5, 1);
			gridData_6_1.heightHint = 0;
			label_1.setLayoutData(gridData_6_1);
			label_1.setText("label");
			{
				nameLabel = new Label(textParameterGroup, SWT.NONE);
				nameLabel.setLayoutData(new GridData());
				nameLabel.setText("Name");
			}
			{
				txtName = new Text(textParameterGroup, SWT.BORDER);
				txtName.addModifyListener(new ModifyListener() {
					public void modifyText(final ModifyEvent e) {
						if (butApply != null)
							butApply.setEnabled(txtName.getText().length() > 0);
					}
				});
				txtName.addKeyListener(new KeyAdapter() {
					public void keyPressed(final KeyEvent e) {
						if (e.keyCode == SWT.CR && !txtName.getText().equals("")) {
							addParam();
						}
					}
				});
				final GridData gridData = new GridData(GridData.FILL, GridData.CENTER, false, false);
				gridData.widthHint = 168;
				txtName.setLayoutData(gridData);
				txtName.setText("");
				txtName.setFocus();
			}
			final Label lblTitle = new Label(textParameterGroup, SWT.NONE);
			final GridData gridData_6 = new GridData(GridData.END, GridData.CENTER, false, false);
			gridData_6.widthHint = 41;
			lblTitle.setLayoutData(gridData_6);
			lblTitle.setAlignment(SWT.RIGHT);
			lblTitle.setText("Value");
			txtValue = new Text(textParameterGroup, SWT.BORDER);
			txtValue.addModifyListener(new ModifyListener() {
				public void modifyText(final ModifyEvent e) {
					butApply.setEnabled(txtName.getText().length() > 0);
				}
			});
			txtValue.addKeyListener(new KeyAdapter() {
				public void keyPressed(final KeyEvent e) {
					if (e.keyCode == SWT.CR && !txtName.getText().trim().equals(""))
						addParam();
				}
			});
			final GridData gridData_10 = new GridData(GridData.FILL, GridData.CENTER, true, false);
			gridData_10.widthHint = 175;
			txtValue.setLayoutData(gridData_10);
			{
				butApply = new Button(textParameterGroup, SWT.NONE);
				butApply.setEnabled(false);
				butApply.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
				butApply.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(final SelectionEvent e) {
						addParam();
					}
				});
				butApply.setText("Apply");
			}
			tableDescParameters = new Table(textParameterGroup, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
			tableDescParameters.addMouseListener(new MouseAdapter() {
				public void mouseDoubleClick(final MouseEvent e) {
					addParams();
				}
			});
			tableDescParameters.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					if (tableDescParameters.getSelectionCount() > -1) {
						txtDescription.setText((tableDescParameters.getSelection()[0].getData(conKeyPARAMETER_DESCRIPTION_ + Options.getLanguage()) != null ? tableDescParameters.getSelection()[0].getData(
								conKeyPARAMETER_DESCRIPTION_ + Options.getLanguage())
								.toString()
								: ""));
					}
				}
			});
			tableDescParameters.setLinesVisible(true);
			tableDescParameters.setHeaderVisible(true);
			final GridData gridData = new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1);
			gridData.widthHint = 245;
			tableDescParameters.setLayoutData(gridData);
			final TableColumn newColumnTableColumn = new TableColumn(tableDescParameters, SWT.NONE);
			newColumnTableColumn.setWidth(122);
			newColumnTableColumn.setText("Name");
			final TableColumn newColumnTableColumn_1 = new TableColumn(tableDescParameters, SWT.NONE);
			newColumnTableColumn_1.setWidth(145);
			newColumnTableColumn_1.setText("Value");
			final Composite composite_2 = new Composite(textParameterGroup, SWT.NONE);
			final GridData gridData_11 = new GridData(GridData.CENTER, GridData.CENTER, false, false);
			gridData_11.widthHint = 49;
			composite_2.setLayoutData(gridData_11);
			composite_2.setLayout(new GridLayout());
			butPut = new Button(composite_2, SWT.NONE);
			butPut.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					addParams();
				}
			});
			final GridData gridData_13 = new GridData(GridData.FILL, GridData.CENTER, false, false);
			gridData_13.widthHint = 33;
			butPut.setLayoutData(gridData_13);
			butPut.setText(">");
			butPutAll = new Button(composite_2, SWT.NONE);
			butPutAll.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					for (int i = 0; i < tableDescParameters.getItemCount(); i++) {
						paramListener.saveParameter(
								tblSelectedParams,
								tableDescParameters.getItem(i).getText(0),
								tableDescParameters.getItem(i).getText(1),
								(tableDescParameters.getItem(i).getData(conKeyPARAMETER_DESCRIPTION_DE) != null ? tableDescParameters.getItem(i)
										.getData(conKeyPARAMETER_DESCRIPTION_DE)
										.toString() : ""),
								(tableDescParameters.getItem(i).getData(conKeyPARAMETER_DESCRIPTION_EN) != null ? tableDescParameters.getItem(i)
										.getData(conKeyPARAMETER_DESCRIPTION_EN)
										.toString() : ""), tableDescParameters.getItem(i).getBackground().equals(Options.getRequiredColor()));
					}
					tableDescParameters.removeAll();
					butApply.setEnabled(false);
				}
			});
			butPutAll.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
			butPutAll.setText(">>");
			butRemove = new Button(composite_2, SWT.NONE);
			butRemove.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					removeParams();
				}
			});
			final GridData gridData_12 = new GridData(GridData.FILL, GridData.CENTER, false, false);
			gridData_12.widthHint = 29;
			butRemove.setLayoutData(gridData_12);
			butRemove.setText("<");
			butRemoveAll = new Button(composite_2, SWT.NONE);
			butRemoveAll.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					String remItem = null;
					ArrayList<HashMap<String, Object>> listOfParams = new ArrayList<HashMap<String, Object>>();
					for (int i = 0; i < tblSelectedParams.getItemCount(); i++) {
						TableItem item = tblSelectedParams.getItem(i);
						if (item.getBackground().equals(Options.getRequiredColor())) {
							remItem = (remItem != null ? remItem : "") + "\n\t" + item.getText(0);
							// merke die Parameter, die nicht gel�scht werden sollen, weil sie required sind
							HashMap<String, Object> h = new HashMap<String, Object>();
							h.put(conParamNAME, item.getText(0));
							h.put(conParamAttributeDEFAULT_VALUE, (item.getText(1) != null ? item.getText(1) : ""));
							h.put(conParamAttributeREQUIRED, conTRUE);
							h.put(conKeyPARAMETER_DESCRIPTION_DE,
									(item.getData(conKeyPARAMETER_DESCRIPTION_DE) != null ? item.getData(conKeyPARAMETER_DESCRIPTION_DE) : ""));
							h.put(conKeyPARAMETER_DESCRIPTION_EN,
									(item.getData(conKeyPARAMETER_DESCRIPTION_EN) != null ? item.getData(conKeyPARAMETER_DESCRIPTION_EN) : ""));
							listOfParams.add(h);
						}
						else {
							TableItem itemDP = new TableItem(tableDescParameters, SWT.NONE);
							itemDP.setText(0, item.getText(0));
							itemDP.setText(1, item.getText(1));
							itemDP.setData(conKeyPARAMETER_DESCRIPTION_DE, item.getData(conKeyPARAMETER_DESCRIPTION_DE));
							itemDP.setData(conKeyPARAMETER_DESCRIPTION_EN, item.getData(conKeyPARAMETER_DESCRIPTION_EN));
						}
					}
					txtName.setFocus();
					paramListener.fillParams(listOfParams, tblSelectedParams, true);
					if (remItem != null) {
						MainWindow.message(jobParameterShell, sos.scheduler.editor.app.Messages.getString(conMsgKeyASSISTENT_JOBPARAMETER_REQUIRED) + remItem,
								SWT.ICON_WARNING | SWT.OK);
					}
					tblSelectedParams.redraw();
					tblSelectedParams.deselectAll();
					tableDescParameters.deselectAll();
				}
			});
			butRemoveAll.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
			butRemoveAll.setText("<<");
			tblSelectedParams = new Table(textParameterGroup, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
			tblSelectedParams.addMouseListener(new MouseAdapter() {
				public void mouseDoubleClick(final MouseEvent e) {
					removeParams();
				}
			});
			final GridData gridData_1 = new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1);
			gridData_1.heightHint = 135;
			gridData_1.widthHint = 185;
			tblSelectedParams.setLayoutData(gridData_1);
			tblSelectedParams.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
					if (butApply.isEnabled()) {
						int ok = MainWindow.message(Messages.getString(conMsgKeyMAIN_LISTENER_APPLY_CHANGES), SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL);
						if (ok == SWT.YES) {
							addParam();
							return;
						}
					}
					if (tblSelectedParams.getSelectionCount() > -1) {
						txtName.setText(tblSelectedParams.getSelection()[0].getText(0));
						txtValue.setText(tblSelectedParams.getSelection()[0].getText(1));
						txtDescription.setText((tblSelectedParams.getSelection()[0].getData(conKeyPARAMETER_DESCRIPTION_ + Options.getLanguage()) != null ? tblSelectedParams.getSelection()[0].getData(
								conKeyPARAMETER_DESCRIPTION_ + Options.getLanguage())
								.toString()
								: ""));
						// txtName.setFocus();
						txtValue.setFocus();
						butApply.setEnabled(false);
					}
				}
			});
			tblSelectedParams.setLayoutDeferred(true);
			tblSelectedParams.setLinesVisible(true);
			tblSelectedParams.setHeaderVisible(true);
			final TableColumn colName = new TableColumn(tblSelectedParams, SWT.NONE);
			colName.setWidth(119);
			colName.setText("Name");
			final TableColumn colValue = new TableColumn(tblSelectedParams, SWT.NONE);
			colValue.setWidth(212);
			colValue.setText("Value");
			// txtDescription = new Text(textParameterGroup, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.H_SCROLL);
			txtDescription = new Browser(textParameterGroup, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.H_SCROLL);
			final GridData gridData_2 = new GridData(GridData.FILL, GridData.CENTER, false, false, 5, 1);
			gridData_2.heightHint = 108;
			txtDescription.setLayoutData(gridData_2);
			txtDescription.setBackground(SWTResourceManager.getColor(247, 247, 247));
			// txtDescription.setEditable(false);
			// der Wizard soll ohne Jobbeschreibung laufen
			if (!xmlFilename.equals("..")) {
				listOfParams = this.parseDocuments(xmlFilename, "");
			}
			fillTable(listOfParams);
			setToolTipText();
			
			jobParameterShell.layout();
			jobParameterShell.pack();
			jobParameterShell.open();
		}
		catch (Exception e) {
			try {
				new ErrorLog("error in " + sos.util.SOSClassUtil.getMethodName(), e);
			}
			catch (Exception ee) {
				// tu nichts
			}
			System.out.println("..error in JobAssistentImportJobParamsForm.showAllImportJobParams " + ": " + e.getMessage());
		}
	}

	private boolean isInList(HashMap item, ArrayList list) {
		for (int i = 0; i < list.size(); i++) {
			HashMap h = (HashMap) list.get(i);
			if (h.get("name").equals(item.get("name"))) {
				return true;
			}
		}
		return false;
	}

	public void fillTable(ArrayList list) throws Exception {
		ArrayList<HashMap<String, Object>> listOfRequired = new ArrayList<HashMap<String, Object>>();
		try {
			HashMap h = new HashMap();
			tableDescParameters.removeAll();

			ArrayList<HashMap<String, Object>> jobP = getParameters();
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					h = (HashMap) list.get(i);

					if (!isInList(h, jobP)) {
						if (h.get(conParamAttributeREQUIRED) != null && (h.get(conParamAttributeREQUIRED).equals(conTRUE))) {
							listOfRequired.add(h);
						}
					}
				}
			}

			//			eventuell vorhandene Parameters aus der Job Editor hinzuf�gen								
			paramListener.fillParams(jobP, tblSelectedParams, true);

			for (int i = 0; i < listOfRequired.size(); i++) {
				h = (HashMap) listOfRequired.get(i);

				if (h.get(conParamNAME) != null && paramListener.existsParams(h.get(conParamNAME).toString(), tblSelectedParams, null) == null) {
					TableItem item = new TableItem(tableDescParameters, SWT.NONE);
					item.setBackground(Options.getRequiredColor());
					item.setChecked(true);
					item.setText(0, (h.get(conParamNAME) != null ? h.get(conParamNAME).toString() : ""));
					item.setText(1, (h.get(conParamAttributeDEFAULT_VALUE) != null ? h.get(conParamAttributeDEFAULT_VALUE).toString() : ""));
					String desc_de = (h.get(conKeyPARAMETER_DESCRIPTION_DE) != null ? h.get(conKeyPARAMETER_DESCRIPTION_DE).toString() : "");
					item.setData(conKeyPARAMETER_DESCRIPTION_DE, desc_de);
					String desc_en = (h.get(conKeyPARAMETER_DESCRIPTION_EN) != null ? h.get(conKeyPARAMETER_DESCRIPTION_EN).toString() : "");
					item.setData(conKeyPARAMETER_DESCRIPTION_EN, desc_en);

				}

			}

			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					h = (HashMap) list.get(i);

					if (h.get(conParamAttributeREQUIRED) == null || (h.get("required").equals("false"))) {
						if (h.get(conParamNAME) != null && paramListener.existsParams(h.get("name").toString(), tblSelectedParams, null) == null) {
							TableItem item = new TableItem(tableDescParameters, SWT.NONE);
							item.setBackground(null);

							item.setChecked(true);
							item.setText(0, (h.get(conParamNAME) != null ? h.get(conParamNAME).toString() : ""));
							item.setText(1, (h.get(conParamAttributeDEFAULT_VALUE) != null ? h.get(conParamAttributeDEFAULT_VALUE).toString() : ""));
							String desc_de = (h.get(conKeyPARAMETER_DESCRIPTION_DE) != null ? h.get(conKeyPARAMETER_DESCRIPTION_DE).toString() : "");
							item.setData(conKeyPARAMETER_DESCRIPTION_DE, desc_de);
							String desc_en = (String) (h.get(conKeyPARAMETER_DESCRIPTION_EN) != null ? h.get(conKeyPARAMETER_DESCRIPTION_EN).toString() : "");
							item.setData(conKeyPARAMETER_DESCRIPTION_EN, desc_en);

						}
					}
				}

			}
		}
		catch (Exception e) {
			try {
				new ErrorLog("error in " + sos.util.SOSClassUtil.getMethodName(), e);
			}
			catch (Exception ee) {
				//tu nichts
			}

			throw new Exception("error in JobAssistentImportJobParamsForm.fillTable() " + e.toString());
		}
	}

	public void setToolTipText() {
		txtName.setToolTipText(Messages.getTooltip("job.param.name"));
		txtValue.setToolTipText(Messages.getTooltip("job.param.value"));
		tblSelectedParams.setToolTipText(Messages.getTooltip("tableparams"));
		tableDescParameters.setToolTipText(Messages.getTooltip("table_description_params"));
		butApply.setToolTipText(Messages.getTooltip("jobparameter.apply"));
		if (butNext != null)
			butNext.setToolTipText(Messages.getTooltip("assistent.next"));
		if (butFinish != null)
			butFinish.setToolTipText(Messages.getTooltip("assistent.finish"));
		if (butCancel != null)
			butCancel.setToolTipText(Messages.getTooltip("assistent.cancel"));
		if (showButton != null)
			showButton.setToolTipText(Messages.getTooltip("assistent.show"));
		if (butPut != null)
			butPut.setToolTipText(Messages.getTooltip("jobparameter.put"));
		if (butPutAll != null)
			butPutAll.setToolTipText(Messages.getTooltip("jobparameter.put_all"));
		if (butRemove != null)
			butRemove.setToolTipText(Messages.getTooltip("jobparameter.remove"));
		if (butRemoveAll != null)
			butRemoveAll.setToolTipText(Messages.getTooltip("jobparameter.remove_all"));
		butBack.setToolTipText(Messages.getTooltip("butBack"));
		txtDescription.setToolTipText(Messages.getTooltip("jobparameter.param_description"));
	}

	public ArrayList<HashMap<String, Object>> getParameters() {
		Element params = joblistener.getJob().getChild("params");
		ArrayList<HashMap<String, Object>> listOfParams = new ArrayList<HashMap<String, Object>>();
		List param = null;
		if (params != null) {
			param = params.getChildren("param");
		}
		if (param != null) {
			for (int i = 0; i < param.size(); i++) {
				Element el = (Element) param.get(i);
				HashMap<String, Object> h = new HashMap<String, Object>();
				h.put(conParamNAME, Utils.getAttributeValue(conParamNAME, el));
				h.put(conParamAttributeDEFAULT_VALUE, Utils.getAttributeValue("value", el));
				h.put("description_de", paramListener.getParameterDescription(Utils.getAttributeValue(conParamNAME, el), "de"));
				h.put("description_en", paramListener.getParameterDescription(Utils.getAttributeValue(conParamNAME, el), "en"));
				listOfParams.add(h);
			}
		}
		return listOfParams;
	}

	private boolean existItem(String name, Table tab) {
		for (int i = 0; tab != null && i < tab.getItemCount(); i++) {
			TableItem item = tab.getItem(i);
			if (item.getText(0) != null && item.getText(0).equals(name)) {
				return true;
			}
		}
		return false;
	}

	private void close() {
		int cont = MainWindow.message(jobParameterShell, sos.scheduler.editor.app.Messages.getString("assistent.cancel"), SWT.ICON_WARNING | SWT.OK
				| SWT.CANCEL);
		if (cont == SWT.OK) {
			if (jobBackUp != null) {
				joblistener.getJob().setContent(jobBackUp.cloneContent());
				List attr = ((Element) (jobBackUp.clone())).getAttributes();
				joblistener.getJob().getAttributes().clear();
				for (int i = 0; i < attr.size(); i++) {
					org.jdom.Attribute at = (org.jdom.Attribute) attr.get(i);
					joblistener.getJob().setAttribute(at.getName(), at.getValue());
				}
			}
			jobParameterShell.dispose();
		}
	}

	private void addParam() {
		if (txtName.getText() != null && txtName.getText().trim().length() == 0) {
			MainWindow.message(jobParameterShell, sos.scheduler.editor.app.Messages.getString("no_param_name"), SWT.ICON_WARNING | SWT.OK);
			txtName.setFocus();
			return;
		}
		paramListener.saveParameter(tblSelectedParams, txtName.getText(), txtValue.getText());
		txtName.setText("");
		txtValue.setText("");
		butApply.setEnabled(false);
		tblSelectedParams.deselectAll();
		txtName.setFocus();
	}

	public void setJobname(Combo jobname) {
		this.jobname = jobname;
	}

	public void setJobForm(JobMainForm jobForm_) {
		if (jobForm_ != null)
			jobForm = jobForm_;
	}

	public void setJobForm(JobDocumentationForm jobDocForm_) {
		if (jobDocForm_ != null)
			jobDocForm = jobDocForm_;
	}

	/**
	 * Der Wizard wurde f�r ein bestehende Job gestartet. 
	 * Beim verlassen der Wizard ohne Speichern, muss der bestehende Job ohne �nderungen wieder zur�ckgesetz werden.
	 * @param backUpJob
	 */
	public void setBackUpJob(Element backUpJob, JobMainForm jobForm_) {
		if (backUpJob != null)
			jobBackUp = (Element) backUpJob.clone();
		if (jobForm_ != null)
			jobForm = jobForm_;
	}

	private void addParams() {
		String strParamName = "";
		String strParamDefaultValue = "";
		if (tableDescParameters.getSelectionIndex() > -1) {
			String existParams = "";
			for (int i = 0; i < tableDescParameters.getSelectionIndices().length; i++) {
				TableItem item = tableDescParameters.getItem(tableDescParameters.getSelectionIndices()[i]);
				strParamName = item.getText(0);
				if (!existItem(strParamName, tblSelectedParams)) {
					strParamName = item.getText(0);
					strParamDefaultValue = item.getText(1);
					paramListener.saveParameter(tblSelectedParams, strParamName, strParamDefaultValue,
							(item.getData(conKeyPARAMETER_DESCRIPTION_DE) != null ? item.getData(conKeyPARAMETER_DESCRIPTION_DE).toString() : ""),
							(item.getData(conKeyPARAMETER_DESCRIPTION_EN) != null ? item.getData(conKeyPARAMETER_DESCRIPTION_EN).toString() : ""),
							item.getBackground().equals(Options.getRequiredColor()));
				}
				else {
					existParams = existParams + strParamName + "\n";
				}
				if (existParams.length() > 0)
					MainWindow.message(jobParameterShell, sos.scheduler.editor.app.Messages.getString(conMsgKeyASSISTENT_JOBPARAMETER_EXIST) + existParams,
							SWT.ICON_WARNING | SWT.OK);
			}
			tableDescParameters.remove(tableDescParameters.getSelectionIndices());
		}
		else {
			MainWindow.message(jobParameterShell, sos.scheduler.editor.app.Messages.getString(conMsgKeyASSISTENT_JOBPARAMETER_NO_SELECTED), SWT.ICON_WARNING
					| SWT.OK);
		}
		tableDescParameters.deselectAll();
		tblSelectedParams.deselectAll();
		// txtName.setFocus();
		txtName.setText(strParamName);
		txtValue.setText(strParamDefaultValue);
		txtValue.setFocus();
		butApply.setEnabled(false);
	}

	private void removeParams() {
		if (tblSelectedParams.getSelectionIndex() > -1) {
			String remItem = "";
			int i = 0;
			while (tblSelectedParams.getSelection().length > 0) {
				TableItem item = tblSelectedParams.getItem(tblSelectedParams.getSelectionIndices()[i]);
				if (item.getBackground().equals(Options.getRequiredColor())) {
					remItem = remItem + "\n\t" + item.getText(0);
					tblSelectedParams.deselect(tblSelectedParams.getSelectionIndices()[i]);
				}
				else {
					TableItem itemDesc = new TableItem(tableDescParameters, SWT.NONE);
					itemDesc.setText(0, item.getText(0));
					itemDesc.setText(1, item.getText(1));
					itemDesc.setData(conKeyPARAMETER_DESCRIPTION_DE, item.getData(conKeyPARAMETER_DESCRIPTION_DE));
					itemDesc.setData(conKeyPARAMETER_DESCRIPTION_EN, item.getData(conKeyPARAMETER_DESCRIPTION_EN));
					paramListener.deleteParameter(tblSelectedParams, tblSelectedParams.getSelectionIndices()[i]);
				}
			}
			if (remItem.length() > 0)
				MainWindow.message(jobParameterShell, sos.scheduler.editor.app.Messages.getString(conMsgKeyASSISTENT_JOBPARAMETER_REQUIRED) + remItem,
						SWT.ICON_WARNING | SWT.OK);
			tblSelectedParams.remove(tblSelectedParams.getSelectionIndices());
		}
		else {
			MainWindow.message(jobParameterShell, sos.scheduler.editor.app.Messages.getString("assistent.jobparameter.no_selected_table"), SWT.ICON_WARNING
					| SWT.OK);
		}
		tblSelectedParams.deselectAll();
		tableDescParameters.deselectAll();
		butApply.setEnabled(false);
		txtName.setText("");
		txtValue.setText("");
		txtName.setFocus();
	}

	// Details hat einen anderen Aufbau der Parameter Description.
	// Beim generieren der Parameter mit Wizard m�ssen die Parameterdescriptchen anders aufgebaut werden.
	public void setDetailsRefresh(Text refreshDetailsText_) {
		refreshDetailsText = refreshDetailsText_;
	}
}
