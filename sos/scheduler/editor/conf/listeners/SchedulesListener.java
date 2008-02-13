package sos.scheduler.editor.conf.listeners;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.jdom.Comment;
import org.jdom.Element;
import sos.scheduler.editor.app.Editor;
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.conf.ISchedulerUpdate;
import sos.scheduler.editor.conf.SchedulerDom;
import sos.scheduler.editor.conf.forms.JobsForm;


public class SchedulesListener { 
	
	private SchedulerDom     _dom;
	
	private ISchedulerUpdate _main;
	
	private Element          _config;
	
	private Element          _schedules;
	
	private List             _list;
	
	
	public SchedulesListener(SchedulerDom dom, ISchedulerUpdate update) {
		_dom = dom;
		_main = update;
		if(_dom.isLifeElement()) {
			 
		} else {
		_config = _dom.getRoot().getChild("config");
		_schedules = _config.getChild("schedules");
		
		if (_schedules != null)
			_list = _schedules.getChildren("schedule");
		}
		
	}
	
	
	private void initSchedules() {
		if (_config.getChild("schedules") == null) {
			Element _schedules = new Element("schedules");
			_config.addContent(_schedules);
			_list = _schedules.getChildren("schedule");
		} else {
			_schedules = _config.getChild("schedules");
			_list = _schedules.getChildren("schedule");
		}
	}
	
	
	public void fillTable(Table table) {		
		table.removeAll();  
		if (_list != null) {
			for (Iterator it = _list.iterator(); it.hasNext();) {
				Object o = it.next();
				if (o instanceof Element) {
					Element e = (Element) o;
					TableItem item = new TableItem(table, SWT.NONE);
					item.setData(e);
					String name = Utils.getAttributeValue("name", e);
					/*if(!Utils.isElementEnabled("job", _dom, e)) {
						item.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));						
					} else {
						item.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
					}*/					
					item.setText(0, name);
					
				}
			}
		}
	}
	
	
	public void newScheduler(Table table) {
		Element schedule = new Element("schedule");
		schedule.setAttribute("name", "schedule" + (table.getItemCount() + 1));
		//Element runtime = new Element("run_time");
		schedule.setAttribute("let_run", "no");
		if (_list == null)
			initSchedules();
		_list.add(schedule);
		_dom.setChanged(true);
		_dom.setChangedForDirectory("schedule", Utils.getAttributeValue("name", schedule), SchedulerDom.NEW);		
		fillTable(table);
		table.setSelection(table.getItemCount() - 1);
		_main.updateSchedules();
		//_main.expandJob("job" + (table.getItemCount()));  
	}
	
	
	
	public boolean deleteJob(Table table) {
		int index = table.getSelectionIndex();
		if (index >= 0) {
			TableItem item = table.getItem(index);
			Element e = (Element) item.getData();
			_dom.setJobDisabled(Utils.getAttributeValue("name", e), false);
			e.detach();
			_dom.setChanged(true);
			_dom.setChangedForDirectory("schedule", Utils.getAttributeValue("name", e) ,SchedulerDom.DELETE);
			table.remove(index);
			_main.updateJobs();
			if(_list==null)
				initSchedules();
			if (_list.size() == 0) {
				_config.removeChild("jobs");
				_schedules = null;
				_list = null;
			}
			
			if (index >= table.getItemCount())
				index--;
			if (index >= 0) {
				table.setSelection(index);
				return true;
			}
		}
		return false;
	}
	
	
}
