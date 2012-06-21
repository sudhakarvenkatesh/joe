package sos.scheduler.editor.conf.forms;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.jdom.Element;

import sos.scheduler.editor.app.ContextMenu;
import sos.scheduler.editor.app.Editor;
import sos.scheduler.editor.app.IUpdateLanguage;
import sos.scheduler.editor.app.MainWindow;
import sos.scheduler.editor.app.Messages;
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.conf.ISchedulerUpdate;
import sos.scheduler.editor.conf.SchedulerDom;
import sos.scheduler.editor.conf.listeners.ScriptsListener;

;
public class ScriptsForm extends Composite implements IUpdateLanguage {
	private ScriptsListener	listener		= null;
	private Group			scriptsGroup	= null;
	private static Table	table			= null;
	private Button			butRemove		= null;
	private Label			label			= null;
	private SchedulerDom	dom				= null;
	private Button			butNew			= null;
	private static String	MONITOR			= "process";

	public ScriptsForm(Composite parent, int style, SchedulerDom pobjXMLDom, ISchedulerUpdate update, Element elem) {
		super(parent, style);
		try {
			this.dom = pobjXMLDom;
			listener = new ScriptsListener(pobjXMLDom, update, elem);
			initialize();
			setToolTipText();
			listener.fillTable(table);
		}
		catch (Exception e) {
			try {
				new sos.scheduler.editor.app.ErrorLog("error in " + sos.util.SOSClassUtil.getMethodName(), e);
			}
			catch (Exception ee) {
			}
			System.err.println("..error in ScriptsForm.init() " + e.getMessage());
		}
	}

	private void initialize() {
		try {
			this.setLayout(new FillLayout());
			createGroup();
			setSize(new org.eclipse.swt.graphics.Point(656, 400));
		}
		catch (Exception e) {
			try {
				new sos.scheduler.editor.app.ErrorLog("error in " + sos.util.SOSClassUtil.getMethodName(), e);
			}
			catch (Exception ee) {
			}
			System.err.println("..error in ScriptsForm.initialize() " + e.getMessage());
		}
	}

	private void createGroup() {
		try {
			GridLayout gridLayout = new GridLayout();
			gridLayout.numColumns = 2;
			scriptsGroup = new Group(this, SWT.NONE);
			// scriptsGroup.setText("Monitors");
			scriptsGroup.setText(sos.scheduler.editor.conf.listeners.SchedulerListener.MONITOR);
			scriptsGroup.setLayout(gridLayout);
			if (Utils.isElementEnabled("job", dom, listener.getParent())) {
				scriptsGroup.setEnabled(true);
			}
			else {
				scriptsGroup.setEnabled(false);
			}
			createTable();
			butRemove = new Button(scriptsGroup, SWT.NONE);
			butRemove.setText("Remove");
			butRemove.setEnabled(false);
			butRemove.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
					int c = MainWindow.message(getShell(), "Do you want remove the monitor?", SWT.ICON_QUESTION | SWT.YES | SWT.NO);
					if (c != SWT.YES)
						return;
					butRemove.setEnabled(listener.delete(table));
					table.deselectAll();
					// txtName.setText("");
					// txtOrdering.setText("");
				}
			});
			butRemove.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
			label = new Label(scriptsGroup, SWT.SEPARATOR | SWT.HORIZONTAL);
			label.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, false, false));
			label.setText("Label");
		}
		catch (Exception e) {
			try {
				new sos.scheduler.editor.app.ErrorLog("error in " + sos.util.SOSClassUtil.getMethodName(), e);
			}
			catch (Exception ee) {
			}
			System.err.println("..error in ScriptsForm.createGroup() " + e.getMessage());
		}
	}

	private void createTable() {
		try {
			GridData gridData2 = new org.eclipse.swt.layout.GridData(GridData.FILL, GridData.FILL, true, true, 1, 3);
			gridData2.widthHint = 425;
			table = new Table(scriptsGroup, SWT.FULL_SELECTION | SWT.BORDER);
			table.addMouseListener(new MouseAdapter() {
				public void mouseDoubleClick(final MouseEvent e) {
					if (table.getSelectionCount() > 0)
						ContextMenu.goTo(Utils.getAttributeValue("name", listener.getParent()) + "_@_" + table.getSelection()[0].getText(0), dom,
								Editor.MONITOR);
				}
			});
			table.setHeaderVisible(true);
			table.setLayoutData(gridData2);
			table.setLinesVisible(true);
			table.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
					if (table.getSelectionCount() > 0) {
						/*TableItem item = table.getSelection()[0];
						txtName.setText(item.getText(0));
						txtOrdering.setText(item.getText(1));		
						txtName.setBackground(null);
						txtOrdering.setBackground(null);
						*/
						if (Utils.isElementEnabled("job", dom, (Element) e.item.getData())) {
							butRemove.setEnabled(true);
						}
						else {
							butRemove.setEnabled(false);
							return;
						}
					}
				}
			});
			TableColumn tableColumn1 = new TableColumn(table, SWT.NONE);
			tableColumn1.setWidth(281);
			tableColumn1.setText("Name");
			TableColumn tableColumn2 = new TableColumn(table, SWT.NONE);
			tableColumn2.setWidth(205);
			tableColumn2.setText("Ordering");
			butNew = new Button(scriptsGroup, SWT.NONE);
			butNew.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					addMonitor();
				}
			});
			butNew.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
			butNew.setText("New");
		}
		catch (Exception e) {
			try {
				new sos.scheduler.editor.app.ErrorLog("error in " + sos.util.SOSClassUtil.getMethodName(), e);
			}
			catch (Exception ee) {
			}
			System.err.println("..error in ScriptsForm.createTable() " + e.getMessage());
		}
	}

	public void setToolTipText() {
		butRemove.setToolTipText(Messages.getTooltip("scripts.btn_remove"));
		table.setToolTipText(Messages.getTooltip("scripts.table"));
		butNew.setToolTipText(Messages.getTooltip("scripts.btn_add_new"));
	}

	private void addMonitor() {
		// if(table.getSelectionCount() > 0)
		// tabell, alte name, ordering,neue name
		// listener.save(table, table.getSelection()[0].getText(0), txtOrdering.getText(),txtName.getText() );
		// else
		// listener.save(table, txtName.getText(), txtOrdering.getText(), null );
		// txtName.setText("");
		// txtOrdering.setText("");
		// listener.save(table, "monitor" + table.getItemCount() , String.valueOf(table.getItemCount()), null );
		listener.save(table, MONITOR + table.getItemCount(), String.valueOf(table.getItemCount()), null);
		butRemove.setEnabled(false);
		table.deselectAll();
		// txtName.setFocus();
	}

	public static Table getTable() {
		return table;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
