package sos.scheduler.editor.conf.listeners;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.sos.joe.xml.Utils;

import com.sos.dialog.swtdesigner.SWTResourceManager;
import com.sos.joe.globals.JOEConstants;
import com.sos.joe.globals.interfaces.ISchedulerUpdate;
import com.sos.joe.globals.messages.ErrorLog;
import com.sos.joe.globals.options.Options;
import com.sos.joe.xml.jobscheduler.SchedulerDom;
import com.sos.scheduler.model.objects.JSObjJob;
import com.sos.scheduler.model.objects.JSObjParams;
import com.sos.scheduler.model.objects.Param;
import com.sos.scheduler.model.objects.Params.CopyParams;

public class JobParameterListener {
	public void setJobname(final String jobname) {
		this.jobname = jobname;
	}
	 
	private final ISchedulerUpdate	_main					= null;
	private final SchedulerDom		_dom					= null;
	private final Element			_parent					= null;
	//	private List				_params					= null;
	private JSObjParams				objParamList			= null;
	//	private Params				objParamList					= null;
	private List					_environments			= null;
	private List					_includeParams			= null;
	private String					jobname					= "";
	private static HashMap			parameterDescription	= new HashMap();
	private static HashMap			parameterRequired		= new HashMap();
	//default ist config
	private int						type					= JOEConstants.CONFIG;
	private JSObjJob				objJSJob				= null;

	public JobParameterListener(final JSObjJob pobjJob, final ISchedulerUpdate update) {
		objJSJob = pobjJob;
		type = JOEConstants.JOB_PARAMETER;  
		initParams();
	}

	private void initParams() {
		objParamList = objJSJob.getParams();
	}

	private void initEnvironment() {
		_parent.addContent(0, new Element("environment"));
		_environments = _parent.getChild("environment").getChildren();
	}

	public void fillParams(final Table table) {
		table.removeAll();
		objParamList = objJSJob.getParams();
		if (objParamList != null) {
			// TODO in JSObjJob implementieren und Liste zur�ckgeben
			for (Object objItem : objParamList.getParamOrCopyParamsOrInclude()) {
				if (objItem instanceof Param) {
					Param objP = (Param) objItem;
					String name = objP.getName();
					String value = objP.getValue();
					TableItem item = new TableItem(table, SWT.NONE);
					item.setText(0, name);
					item.setText(1, value);
				}
				if (objItem instanceof CopyParams) {
					CopyParams objP = (CopyParams) objItem;
					TableItem item = new TableItem(table, SWT.NONE);
					item.setText(0, "<from>");
					item.setText(1, objP.getFrom());
				}
			}
		}
	}

	@Deprecated public void fillParams(final ArrayList listOfParams, final Table table, final boolean refreshTable) {
		if (refreshTable) {
			if (objParamList != null)
				//				objParamList.clear();
				table.removeAll();
		}
		for (int i = 0; i < listOfParams.size(); i++) {
			HashMap h = (HashMap) listOfParams.get(i);
			if (h.get("name") != null) {
				TableItem item = existsParams(h.get("name").toString(), table, h.get("default_value") != null ? h.get("default_value").toString() : "");
				if (!refreshTable && item != null) {
					if (h.get("required") != null && h.get("required").equals("true")) {
						if (h.get("value") == null || h.get("value").toString().length() == 0)
							item.setBackground(Options.getRequiredColor());
						else
							item.setBackground(SWTResourceManager.getColor(247, 247, 247));
					}
					//existParam = true;
				}
				else {
					String pname = h.get("name").toString();
					String pvalue = h.get("default_value") != null ? h.get("default_value").toString() : "";
					String desc_de = h.get("description_de") != null ? h.get("description_de").toString() : "";
					String desc_en = h.get("description_en") != null ? h.get("description_en").toString() : "";
					saveParameter(table, pname, pvalue, desc_de, desc_en, h.get("required") != null ? h.get("required").equals("true") : false);
				}
			}
		}
	}

	public void fillEnvironment(final Table table) {
		if (_environments != null) {
			Iterator it = _environments.iterator();
			while (it.hasNext()) {
				Object o = it.next();
				if (o instanceof Element) {
					TableItem item = new TableItem(table, SWT.NONE);
					item.setText(0, ((Element) o).getAttributeValue("name"));
					item.setText(1, ((Element) o).getAttributeValue("value") != null ? ((Element) o).getAttributeValue("value") : "");
				}
			}
		}
	}

	public void fillIncludeParams(final Table table) {
		if (_includeParams != null) {
			Iterator it = _includeParams.iterator();
			while (it.hasNext()) {
				Object o = it.next();
				if (o instanceof Element) {
					TableItem item = new TableItem(table, SWT.NONE);
					Element elem = (Element) o;
					if (elem.getAttribute("file") != null) {
						item.setText(0, Utils.getAttributeValue("file", elem));
						item.setText(2, "file");
					}
					else {
						item.setText(0, Utils.getAttributeValue("live_file", elem));
						item.setText(2, "live_file");
					}
					item.setText(1, ((Element) o).getAttributeValue("node") != null ? ((Element) o).getAttributeValue("node") : "");
				}
			}
		}
	}

	public TableItem existsParams(final String name, final Table table, final String replaceValue) {
		try {
			for (int i = 0; i < table.getItemCount(); i++) {
				if (table.getItem(i).getText(0).equals(name)) {
					return table.getItem(i);
				}
			}
		}
		catch (Exception e) {
				new ErrorLog("error in " + sos.util.SOSClassUtil.getMethodName(), e);
			System.out.println("error in ParameterListener.existsParams " + e.getMessage());
		}
		return null;
	}

	public void deleteParameter(final Table table, final int index) {
		if (objParamList != null) {
			// TODO			objParamList.remove(index);
			_dom.setChanged(true);
			//if(type == JOEConstants.JOB) _dom.setChangedForDirectory("job", Utils.getAttributeValue("name",_parent), SchedulerDom.MODIFY);
			Utils.setChangedForDirectory(_parent, _dom);
		}
		//		if (objParamList.size() == 0) {
		//			_parent.removeChild("params");
		//		}
		table.remove(index);
	}

	public void deleteEnvironment(final Table table, final int index) {
		if (_environments != null) {
			_environments.remove(index);
			_dom.setChanged(true);
			//_dom.setChangedForDirectory("job", Utils.getAttributeValue("name",_parent), SchedulerDom.MODIFY);
			Utils.setChangedForDirectory(_parent, _dom);
			//if(type == JOEConstants.JOB) _dom.setChangedForDirectory("job", Utils.getAttributeValue("name",_parent), SchedulerDom.MODIFY);
		}
		table.remove(index);
	}

	public void deleteIncludeParams(final Table table, final int index) {
		if (_includeParams != null) {
			_includeParams.remove(index);
			_dom.setChanged(true);
			//if(type == JOEConstants.JOB) _dom.setChangedForDirectory("job", Utils.getAttributeValue("name",_parent), SchedulerDom.MODIFY);
			Utils.setChangedForDirectory(_parent, _dom);
		}
		table.remove(index);
	}

	public void saveParameter(final Table table, final String name, final String value, final String parameterDescription_de,
			final String parameterDescription_en, final boolean required) {
		Element e = new Element("param");
		e.setAttribute("name", name);
		e.setAttribute("value", value);
		//parameterDescription_de = Utils.normalizedHTMLTags(parameterDescription_de);
		//parameterDescription_en = Utils.normalizedHTMLTags(parameterDescription_en);
		if ((_dom.isLifeElement() || _dom.isDirectory()) && objParamList == null) {
			Element params = _parent.getChild("params");
			if (params != null) {
				// TODO				objParamList = params.getChildren();
			}
		}
		if (objParamList == null)
			initParams();
		// TODO		objParamList.add(e); 
		TableItem item = new TableItem(table, SWT.NONE);
		item.setText(new String[] { name, value });
		if (parameterDescription_de != null && parameterDescription_de.trim().length() > 0) {
			item.setData("parameter_description_de", parameterDescription_de);
			parameterDescription.put("parameter_description_de_" + name, parameterDescription_de);
		}
		if (parameterDescription_en != null && parameterDescription_en.trim().length() > 0) {
			item.setData("parameter_description_en", parameterDescription_en);
			parameterDescription.put("parameter_description_en_" + name, parameterDescription_en);
		}
		if (required) {
			if (value.length() == 0) {
				item.setBackground(Options.getRequiredColor());
			}
			else {
				item.setBackground(SWTResourceManager.getColor(247, 247, 247));
			}
		}
		_dom.setChanged(true);
		if (type == JOEConstants.JOB_COMMANDS)
			_dom.setChangedForDirectory("job", jobname, SchedulerDom.MODIFY);
		//if(type == JOEConstants.JOB) _dom.setChangedForDirectory("job", Utils.getAttributeValue("name",_parent), SchedulerDom.MODIFY);
		Utils.setChangedForDirectory(_parent, _dom);
	}

	public void saveIncludeParams(final Table table, final String file, final String node, final boolean isLive) {
		boolean found = false;
		if (_includeParams != null) {
			int index = 0;
			Iterator it = _includeParams.iterator();
			while (it.hasNext()) {
				Object o = it.next();
				if (o instanceof Element) {
					Element e = (Element) o;
					if ((file.equals(e.getAttributeValue("live_file")) || file.equals(e.getAttributeValue("file")))
							&& (node.equals(e.getAttributeValue("node")) || table.getSelectionCount() > 0)) {
						found = true;
						e.removeAttribute("live_file");
						e.removeAttribute("file");
						if (isLive)
							e.setAttribute("live_file", file);
						else
							e.setAttribute("file", file);
						Utils.setAttribute("node", node, e);
						_dom.setChanged(true);
						//if(type == JOEConstants.JOB) _dom.setChangedForDirectory("job", Utils.getAttributeValue("name",_parent), SchedulerDom.MODIFY);
						Utils.setChangedForDirectory(_parent, _dom);
						table.getItem(index).setText(1, node);
						table.getItem(index).setText(2, isLive ? "live_file" : "file");
						break;
					}
					index++;
				}
			}
		}
		if (!found) {
			Element e = new Element("include");
			if (isLive)
				e.setAttribute("live_file", file);
			else
				e.setAttribute("file", file);
			e.setAttribute("node", node);
			_dom.setChanged(true);
			//if(type == JOEConstants.JOB) _dom.setChangedForDirectory("job", Utils.getAttributeValue("name",_parent), SchedulerDom.MODIFY);
			Utils.setChangedForDirectory(_parent, _dom);
			if (_includeParams == null)
				initParams();
			_includeParams.add(e);
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { file, node, isLive ? "live_file" : "file" });
		}
	}

	public void saveEnvironment(final Table table, final String name, final String value) {
		boolean found = false;
		if (_environments != null) {
			int index = 0;
			Iterator it = _environments.iterator();
			while (it.hasNext()) {
				Object o = it.next();
				if (o instanceof Element) {
					Element e = (Element) o;
					if (name.equals(e.getAttributeValue("name"))) {
						found = true;
						//Utils.setAttribute("value", value, e);
						e.setAttribute("value", value);
						_dom.setChanged(true);
						if (type == JOEConstants.JOB)
							_dom.setChangedForDirectory("job", Utils.getAttributeValue("name", _parent), SchedulerDom.MODIFY);
						Utils.setChangedForDirectory(_parent, _dom);
						table.getItem(index).setText(1, value);
						break;
					}
					index++;
				}
			}
		}
		if (!found) {
			Element e = new Element("variable");
			e.setAttribute("name", name);
			e.setAttribute("value", value);
			_dom.setChanged(true);
			//if(type == JOEConstants.JOB) _dom.setChangedForDirectory("job", Utils.getAttributeValue("name",_parent), SchedulerDom.MODIFY);
			Utils.setChangedForDirectory(_parent, _dom);
			if (_environments == null)
				initEnvironment();
			_environments.add(e);
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { name, value });
		}
	}

	public void saveParameter(final Table table, final String name, final String value) {
		objParamList.add(name, value);
		//		boolean found = false;
		//		Element params = _parent.getChild("params");
		//		if (params != null) {
		////  TODO			objParamList = params.getChildren();
		//		}
		//		if (objParamList != null) {
		//
		//			//if (name.equals("<from>") && type == JOEConstants.COMMANDS) {
		//			if (name.equals("<from>")) {
		//				found = table.getSelectionIndex() > -1;
		//			}
		//			else {
		//				int index = 0;
		//				Iterator it = objParamList.iterator()
		//						objParamList.values();
		//				while (it.hasNext()) {
		//					Object o = it.next();
		//					if (o instanceof Element) {
		//						Element e = (Element) o;
		//
		//						if (e.getName().equals("param")) {
		//							if (name.equals(e.getAttributeValue("name"))) {
		//								found = true;
		//								e.setAttribute("value", value);
		//
		//								_dom.setChanged(true);
		//								//if(type == JOEConstants.JOB) _dom.setChangedForDirectory("job", Utils.getAttributeValue("name",_parent), SchedulerDom.MODIFY);
		//								Utils.setChangedForDirectory(_parent, _dom);
		//								table.getItem(index).setText(1, value);
		//
		//								if (isParameterRequired(table.getItem(index).getText())) {
		//									if (value != null && value.length() > 0) {
		//										table.getItem(index).setBackground(SWTResourceManager.getColor(247, 247, 247));
		//
		//									}
		//									else {
		//										table.getItem(index).setBackground(Options.getRequiredColor());
		//									}
		//
		//								}
		//
		//							}
		//
		//							index++;
		//						}
		//					}
		//				}
		//			}
		//
		//			//if (name.equals("<from>") && found && type == JOEConstants.COMMANDS) {
		//			if (name.equals("<from>") && found) {
		//				int index = table.getSelectionIndex();
		//				table.getItem(index).setText(0, name);
		//				table.getItem(index).setText(1, value);
		////		TODO		Element e = (Element) objParamList.get(index);
		//				e.setName("copy_params");
		//				e.setAttribute("from", value);
		//				e.removeAttribute("name");
		//				e.removeAttribute("value");
		//				_dom.setChanged(true);
		//				//if(type == JOEConstants.JOB) _dom.setChangedForDirectory("job", Utils.getAttributeValue("name",_parent), SchedulerDom.MODIFY);
		//			}
		//
		//		}
		//
		//		if (!found) {
		//			Element e = new Element("param");
		//			if (!name.equals("<from>")) {
		//				e.setAttribute("name", name);
		//				e.setAttribute("value", value);
		//			}
		//			else {
		//				e.setName("copy_params");
		//				e.setAttribute("from", value);
		//			}
		//
		//			_dom.setChanged(true);
		//
		//			if (type == JOEConstants.JOB) {
		//				_dom.setChangedForDirectory("job", Utils.getAttributeValue("name", _parent), SchedulerDom.MODIFY);
		//			}
		//			if (objParamList == null) {
		//				initParams();
		//			}
		//			else {
		//				objParamList.add(name, value);
		//				TableItem item = new TableItem(table, SWT.NONE);
		//				item.setText(new String[] { name, value });
		//			}
		//
		//		}
		//		if (type == JOEConstants.JOB_COMMANDS)
		//			_dom.setChangedForDirectory("job", jobname, SchedulerDom.MODIFY);
		//		Utils.setChangedForDirectory(_parent, _dom);
		//
	}

	public SchedulerDom get_dom() {
		return _dom;
	}

	public ISchedulerUpdate get_main() {
		return _main;
	}

	public Element getParent() {
		return _parent;
	}

	public void getAllParameterDescription() {
		String xmlPaths = Options.getSchedulerData();
		String include = "";
		Element desc = _parent.getChild("description");
		if (desc != null) {
			Element inc = desc.getChild("include");
			if (inc != null)
				include = inc.getAttributeValue("file");
		}
		xmlPaths = xmlPaths.endsWith("/") || xmlPaths.endsWith("\\") ? xmlPaths.concat(include) : xmlPaths.concat("/").concat(include);
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(xmlPaths));
			Element root = doc.getRootElement();
			Element config = root.getChild("configuration", root.getNamespace());
			Element params = config.getChild("params", config.getNamespace());
			if (params == null)
				return;
			List listMainElements = params.getChildren("param", params.getNamespace());
			for (int i = 0; i < listMainElements.size(); i++) {
				Element elMain = (Element) listMainElements.get(i);
				if (elMain.getName().equalsIgnoreCase("param")) {
					List noteList = elMain.getChildren("note", elMain.getNamespace());
					for (int k = 0; k < noteList.size(); k++) {
						Element note = (Element) noteList.get(k);
						String language = Utils.getAttributeValue("language", note);
						if (note != null) {
							List notelist = note.getChildren();
							for (int j = 0; j < notelist.size(); j++) {
								Element elNote = (Element) notelist.get(j);
								parameterDescription.put("parameter_description_" + language + "_" + elMain.getAttributeValue("name"), elNote.getValue());
								if (elMain.getAttributeValue("required") != null)
									parameterRequired.put(elMain.getAttributeValue("name"), elMain.getAttributeValue("required"));
							}
						}
					}
				}
			}
		}
		catch (Exception ex) {
			try {
				new ErrorLog("error in " + sos.util.SOSClassUtil.getMethodName(), ex);
			}
			catch (Exception ee) {
				//tu nichts
			}
			ex.printStackTrace();
		}
	}

	/**
	 * Note/Beschreibung der Parameter
	 * @param name
	 * @return
	 */
	public String getParameterDescription(final String name) {
		return parameterDescription.get("parameter_description_" + Options.getLanguage() + "_" + name) != null ? parameterDescription.get(
				"parameter_description_" + Options.getLanguage() + "_" + name).toString() : "";
	}

	/**
	 * Note/Beschreibung der Parameter
	 * @param name
	 * @return
	 */
	public String getParameterDescription(final String name, final String language) {
		return parameterDescription.get("parameter_description_" + language + "_" + name) != null ? parameterDescription.get(
				"parameter_description_" + language + "_" + name).toString() : "";
	}

	private boolean isParameterRequired(final String name) {
		String _isIt = parameterRequired.get(name) != null ? parameterRequired.get(name).toString() : "";
		if (_isIt.equals("true")) {
			return true;
		}
		else {
			return false;
		}
	}

	//selektierte Datensatz wird eine Zeile nach oben verschoben
	public void changeUp(final Table table) {
		int index = table.getSelectionIndex();
		if (index < 0)//nichts ist selektiert
			return;
		if (index == 0)//ist bereits ganz oben
			return;
		if (objParamList == null)
			initParams();
		_dom.reorderDOM();
		Element params = _parent.getChild("params");
		if (params != null) {
			//			objParamList = params.getChildren();
			_includeParams = params.getChildren("include");
		}
		//	TODO	Element elem = (Element) objParamList.get(index);
		//		Object obj = elem.clone();
		//		objParamList.remove(elem);
		//		objParamList.add(index - 1, obj);
		table.removeAll();
		fillParams(table);
		table.select(index - 1);
		Utils.setChangedForDirectory(_parent, _dom);
		_dom.setChanged(true);
	}

	//selektierte Datensatz wird eine Zeile unten  verschoben
	public void changeDown(final Table table) {
		int index = table.getSelectionIndex();
		if (index < 0)//nichts ist selektiert
			return;
		if (index == table.getItemCount() - 1)//ist bereits ganz oben
			return;
		if (objParamList == null)
			initParams();
		//	TODO	Element elem = (Element) objParamList.get(index);
		//		Object obj = elem.clone();
		//		objParamList.remove(elem);
		//		objParamList.add(index + 1, obj);
		table.removeAll();
		fillParams(table);
		table.select(index + 1);
		Utils.setChangedForDirectory(_parent, _dom);
		_dom.setChanged(true);
	}
}
