package sos.scheduler.editor.conf.listeners;

import static sos.scheduler.editor.app.SOSJOEMessageCodes.JOE_E_0002;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.TableItem;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.filter.ElementFilter;
import org.jdom.filter.Filter;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import sos.scheduler.editor.app.ErrorLog;
import sos.scheduler.editor.app.MainWindow;
import sos.scheduler.editor.app.Options;
import sos.scheduler.editor.app.SOSJOEMessageCodes;
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.classes.JobChainNodeWrapper;
import sos.scheduler.editor.classes.SOSTable;
import sos.scheduler.editor.conf.SchedulerDom;
import sos.util.SOSClassUtil;

import com.sos.JSHelper.io.Files.JSFile;
import com.sos.JSHelper.io.Files.JSXMLFile;
import com.sos.scheduler.model.objects.JSObjJob;
import com.sos.scheduler.model.objects.JSObjJobChain;

public class JobChainListener extends JOEJobChainDataProvider {

	@SuppressWarnings("unused")
	private final String		conClassName	= this.getClass().getSimpleName();
	@SuppressWarnings("unused")
	private static final String	conSVNVersion	= "$Id$";
	private final Logger		logger			= Logger.getLogger(this.getClass());
	private Element				_objFileOrderSource	= null;
	//	private final SOSString	sosString			= new SOSString();

	private ArrayList<String>	listOfAllState		= null;

	public JobChainListener(final SchedulerDom dom, final Element jobChain) {
		_dom = dom;
		objJobChain = jobChain;
		objElement = jobChain;
		getJOMJobChain();
		if (objJobChain.getParentElement() != null)
			_config = objJobChain.getParentElement().getParentElement();
	}

	/**
	 * This Method seems to be used to modify the name of the jobChain
	*
	* \brief setChainName
	*
	* \details
	*
	* \return void
	*
	 */
	@Override
	public void setChainName(final String name) {

		_dom.setChanged(true);

		String oldjobChainName = getChainName();

		//F�r job_chain node Parameter
		if (objJobChain != null && _dom.getFilename() != null) {
			CTabItem currentTab = MainWindow.getContainer().getCurrentTab();

			String path = _dom.isDirectory() ? _dom.getFilename() : new java.io.File(_dom.getFilename()).getParent();
			try {
				if (currentTab.getData("details_parameter") != null) {
					HashMap h = new HashMap();
					h = (HashMap) currentTab.getData("details_parameter");
					if (!h.containsKey(objJobChain)) {
						h.put(objJobChain, new java.io.File(path, oldjobChainName + JSObjJobChain.conFileNameExtension4NodeParameterFile).getCanonicalPath());
					}
				}
				else {
					HashMap h = new HashMap();
					h.put(objJobChain, new java.io.File(path, oldjobChainName + JSObjJobChain.conFileNameExtension4NodeParameterFile).getCanonicalPath());
					currentTab.setData("details_parameter", h);

				}
				// f�r das Speicher per FTP
				String filename = _dom.isLifeElement() ? new File(_dom.getFilename()).getParent() : _dom.getFilename();
				currentTab.setData("ftp_details_parameter_file", filename + "/" + name + JSObjJobChain.conFileNameExtension4NodeParameterFile);
				if (oldjobChainName != null && oldjobChainName.length() > 0
						&& new File(filename + "/" + oldjobChainName + JSObjJobChain.conFileNameExtension4NodeParameterFile).exists()) {
					currentTab.setData("ftp_details_parameter_remove_file", oldjobChainName + JSObjJobChain.conFileNameExtension4NodeParameterFile);
				}

			}
			catch (Exception e) {
				new ErrorLog("error in setChainName, cause: " + e.toString(), e);
			}
		}

		if (oldjobChainName != null && oldjobChainName.length() > 0) {
			if (_dom.isChanged() && (_dom.isDirectory() && !Utils.existName(oldjobChainName, objJobChain, "job_chain") || _dom.isLifeElement())) {
				_dom.setChangedForDirectory("job_chain", oldjobChainName, SchedulerDom.DELETE);
			}
		}

		Utils.setAttribute("name", name, objJobChain);
		objJSJobChain.setName(name);

		if (_dom.isDirectory() || _dom.isLifeElement())
			_dom.setChangedForDirectory("job_chain", name, SchedulerDom.MODIFY);
	}

	public void applyChain(final String name, final boolean ordersRecoverable, final boolean visible, final boolean distributed, final String title) {
		String oldjobChainName = getChainName();
		if (oldjobChainName != null && oldjobChainName.length() > 0) {
			if (_dom.isDirectory() || _dom.isLifeElement()) {
				_dom.setChangedForDirectory("job_chain", oldjobChainName, SchedulerDom.DELETE);
			}
		}
		setName(name);
		setOrdersRecoverable(ordersRecoverable);
		setVisible(visible);
		setDistributed(distributed);
		setTitle(title);
	}

	public void populateTable4FileOrderSource() {
		if (objJobChainNodesTable == null | objJSJobChain == null) {
			return;
		}

		objJobChainNodesTable.removeAll();

		//		for (FileOrderSource objNode : objJSJobChain.getFileOrderSourceList()) {
		//			TableItem item = new TableItem(table, SWT.NONE);
		//			item.setData("Element", objNode);
		//			String directory = objNode.getDirectory();
		//			String regex = objNode.getRegex();
		//			String next_state = objNode.getNextState();
		//
		//			String strDelayAfterError = BigInt2String(objNode.getDelayAfterError());
		//			String strRepeat = objNode.getRepeat();
		//			String strMaxFiles = BigInt2String(objNode.getMax());
		//
		//			item.setText(new String[] { directory, regex, next_state, strDelayAfterError, strRepeat, strMaxFiles });
		//		}
		//
		if (objJobChain != null) {
			Iterator it = objJobChain.getChildren().iterator();
			// TODO create class FileOrderSourceWrapper
			while (it.hasNext()) {
				Element node = (Element) it.next();
				if (node.getName() == conTagFILE_ORDER_SOURCE) {
					String directory = Utils.getAttributeValue(conAttrDIRECTORY, node);
					String regex = Utils.getAttributeValue(conAttrREGEX, node);
					String next_state = Utils.getAttributeValue(conAttrNEXT_STATE, node);

					String strDelayAfterError = Utils.getAttributeValue(conAttrDELAY_AFTER_ERROR, node);
					String strRepeat = Utils.getAttributeValue(conAttrREPEAT, node);
					String strMaxFiles = Utils.getAttributeValue(conAttrMAX, node);

					TableItem item = new TableItem(objJobChainNodesTable, SWT.NONE);
					item.setData("Element", node);
					item.setText(new String[] { directory, regex, next_state, strDelayAfterError, strRepeat, strMaxFiles });
				}
			}
		}
	}

	//	public void fillFileOrderSink(final SOSTable table) {
	//
	//		if (table == null | objJSJobChain == null) {
	//			return;
	//		}
	//		table.removeAll();
	//
	//		//		for (FileOrderSink objNode : objJSJobChain.getFileOrderSinkList()) {
	//		//			TableItem item = new TableItem(table, SWT.NONE);
	//		//			item.setData("Element", objNode);
	//		//			String state = objNode.getState();
	//		//			String moveFileTo = objNode.getMoveTo();
	//		//			String remove = objNode.getRemove();
	//		//			item.setText(new String[] { state, moveFileTo, remove });
	//		//		}
	//		//
	//		if (objJobChain != null) {
	//			Iterator it = objJobChain.getChildren().iterator();
	//			while (it.hasNext()) {
	//				Element node = (Element) it.next();
	//				if (node.getName() == "file_order_sink") {
	//					String state = Utils.getAttributeValue(conAttrSTATE, node);
	//					String moveFileTo = Utils.getAttributeValue("move_to", node);
	//					String remove = Utils.getAttributeValue("remove", node);
	//					TableItem item = new TableItem(table, SWT.NONE);
	//					item.setText(new String[] { state, moveFileTo, remove });
	//				}
	//			}
	//		}
	//	}

	//	public void populateNodesTable2(final Table table) {
	//
	//		if (table == null) {
	//			return;
	//		}
	//
	//		table.removeAll();
	//
	//		String state = "";
	//		String nodetype = "";
	//		String strJobName = "";
	//		String next = "";
	//		String error = "";
	//		String onError = "";
	//		String strDelayOnStart = "";
	//
	//		setStates();
	//
	//		for (JobChainNode objNode : objJSJobChain.getJobChainNodeList()) {
	//			strJobName = objNode.getJob();
	//			if (strJobName.length() <= 0) { // Ein EndNode
	//				nodetype = "Endnode";
	//			}
	//			else {
	//				nodetype = "Job";
	//			}
	//			state = objNode.getState();
	//			String strHasParameters = "";
	//			nodetype = "Job";
	//			next = objNode.getNextState();
	//			error = objNode.getErrorState();
	//			strDelayOnStart = BigInt2String(objNode.getDelay());
	//
	//			int conItemIndexHasParams = 7;
	//			TableItem item = new TableItem(table, SWT.NONE);
	//			item.setData("element", objNode);
	//			item.setData("elementtype", nodetype);
	//			if (DetailsListener.existDetailsParameter(state, getChainName(), strJobName, _dom, update, false, null)) {
	//				item.setBackground(getColor4NodeParameter());
	//				strHasParameters = "yes";
	//			}
	//			else {
	//				item.setBackground(null);
	//			}
	//			item.setText(new String[] { state, nodetype, strJobName, next, error, onError, strDelayOnStart, strHasParameters });
	//
	//			int conItemIndexNext = 3;
	//			int conItemIndexError = 4;
	//			if (!next.equals("") && !isStateDefined(next))
	//				item.setBackground(conItemIndexNext, getColor4InvalidValues());
	//
	//			if (!error.equals("") && !isStateDefined(error)) {
	//				item.setBackground(conItemIndexError, getColor4InvalidValues());
	//			}
	//			if (strHasParameters.length() > 0) {
	//				item.setBackground(conItemIndexHasParams, getColor4hasParameter());
	//			}
	//		}
	//
	//		state = "";
	//		strJobName = "";
	//		next = "";
	//		error = "";
	//		onError = "";
	//		strDelayOnStart = "";
	//
	//		for (JobChainNodeEnd objNode : objJSJobChain.getJobChainNodeEndList()) {
	//			state = objNode.getState();
	//			nodetype = "Endnode";
	//
	//			TableItem item = new TableItem(table, SWT.NONE);
	//			item.setData("element", objNode);
	//			item.setData("elementtype", nodetype);
	//
	//			item.setText(new String[] { state, nodetype, strJobName, next, error, onError, strDelayOnStart, "" });
	//		}
	//
	//	}
	//

	public SOSTable	objJobChainNodesTable	= null;

	public void populateNodesTable() {

		assert objJobChainNodesTable != null;
		assert objJobChain != null;

		objJobChainNodesTable.removeAll();

		setStates();
		int intIndex = 0;
		for (Object eleNode : objJobChain.getChildren()) {
			JobChainNodeWrapper objJobChainNode = getJobChainNodeWrapper((Element) eleNode);
			objJobChainNode.setIndex(++intIndex);
			if (objJobChainNode.isNode4NodesTable() == true) {
				String strJobName = objJobChainNode.getJobName();
				if (objJobChainNode.isJobNode() == true && getJobFile(strJobName).exists() == false) {
					objJobChainNode.setJobIsMissing(true);
				}
				if (DetailsListener.existJobChainNodesParameter(objJobChainNode.getState(), getChainName(), strJobName, get_dom(), update, false, null)) {
					objJobChainNode.setHasNodeParameter(true);
				}

				String next = objJobChainNode.getNextState();
				if (!next.equals("") && !isStateDefined(next)) {
					objJobChainNode.setNextStateIsInvalid(true);
				}

				String error = objJobChainNode.getErrorState();
				if (!error.equals("") && !isStateDefined(error)) {
					objJobChainNode.setErrorStateIsInvalid(true);
				}
				TableItem objTI = new TableItem(objJobChainNodesTable, SWT.NONE);
				objTI.setData(objJobChainNode);
				objJobChainNode.populateTableItem(objTI);
			}
		}
	}

	public boolean isStateDefined(final String state) {

		for (String _state : _states) {
			if (_state.equals(state)) {
				return true;
			}
		}
		return false;
	}

	public void clearNode() {
		_node = null;
	}

	public void selectNode() {
		if (objJobChainNodesTable == null) {
			logger.debug("objJobChainNodesTable is null");
			clearNode();
		}
		else {
//			int index = getIndexOfNode(objJobChainNodesTable.getItem(getSelectionIndex()));
			_node = (JobChainNodeWrapper) objJobChainNodesTable.getItem(getSelectionIndex()).getData();
//			_node = getJobChainNodeWrapper((Element) objJobChain.getChildren().get(index));
			if (_states == null) {
				setStates();
			}
		}
	}

	public void clearFileOrderSource() {
		_objFileOrderSource = null;
	}

	public int getSelectionIndex() {
		int index = objJobChainNodesTable.getSelectionIndex();
		return index;
	}

	public void selectFileOrderSource() {
		if (objJobChainNodesTable == null) {
			clearFileOrderSource();
		}
		else {
			int index = getSelectionIndex();
			_objFileOrderSource = (Element) objJobChain.getChildren(conTagFILE_ORDER_SOURCE).get(index);
		}
	}

	public boolean isFullNode() {
		return _node.isJobNode();
	}

	public boolean isFileSinkNode() {
		return _node.isFileSinkNode();
	}

	public String getFileOrderSource(final String a) {
		return Utils.getAttributeValue(a, _objFileOrderSource);
	}

	public void applyNode(final boolean isJobchainNode, final String state, final String job, final String delay, final String next, final String error,
			final boolean removeFile, final String moveTo, final String onError) throws Exception {
		try {
			if (_node != null) {//Wenn der Knotentyp ge�ndert wird, alten l�schen und einen neuen anlegen.
				if (getState().equals(state) == false) {
					DetailsListener.changeNodeParameters(getState(), state, getChainName(), _dom);
				}

				if (isJobchainNode && _node.isFileSinkNode() || !isJobchainNode && _node.isJobNode()) {
					_node.detach();
					_node = null;
				}
			}

			if (_node == null) {
				if (isJobchainNode) {
					_node = getNewJobChainNode();
				}
				else {
					_node = getNewFileOrderSink();
				}
			}
			setState(state);
			if (isJobchainNode) {
				_node.setJobName(job).setDelay(delay).setNextState(next).setErrorState(error).setOnError(onError);
			}
			else {
				_node.setMoveTo(moveTo).setRemoveFileB(removeFile);
			}
			setDirty();
			setStates();
		}
		catch (Exception e) {
			new ErrorLog(SOSJOEMessageCodes.JOE_E_0002.params(SOSClassUtil.getMethodName()), e);
		}
	}

	public void setFileOrderSink(final String state, final boolean removeFile, final String moveTo) {
		try {
			_node = null;
			for (Element element : getFileOrderSinkList()) {
				if (element.getAttribute(conAttrSTATE).toString().equalsIgnoreCase(state)) {
					_node = getJobChainNodeWrapper(element);
					break;
				}
			}

			if (_node == null) {
				_node = getNewFileOrderSink();
			}
			_node.setState(state);
			_node.setMoveTo(moveTo);
			_node.setRemoveFileB(removeFile);
		}
		catch (Exception e) {
			new ErrorLog(SOSJOEMessageCodes.JOE_E_0002.params(conClassName), e);
		}
	}

	public void applyInsertNode(final boolean isJobchainNode, final String state, final String job, final String delay, final String next, final String error,
			final boolean removeFile, final String moveTo, final String onError) {

		JobChainNodeWrapper objCurrentNode = _node;

		JobChainNodeWrapper objNode2Insert = null;
		if (isJobchainNode) {
			objNode2Insert = getAJobChainNode().setJobName(job).setDelay(delay).setNextState(next).setErrorState(error).setOnError(onError);
		}
		else {
			objNode2Insert = getAFileOrderSink().setMoveTo(moveTo).setRemoveFileB(removeFile).setState(state);
		}

		objNode2Insert.setState(state);

		boolean found = false;
		List<Element> list = objJobChain.getChildren();
		if (list.size() > 0 && objCurrentNode != null) {
			// TODO Attribut "index" einf�hren. Damit entf�llt die umsortiererei und die Reihenfolge ist nicht l�nger relevant

			for (int i = 0; i <= list.size(); i++) {
				if (list.get(i).equals(objCurrentNode.getDOMElement())) {
					JobChainNodeWrapper objPreviousNode = getJobChainNodeWrapper(list.get(i - 1));
					objPreviousNode.setNextState(state);
					objNode2Insert.setNextState(objCurrentNode.getState());
					objJobChain.addContent(i + 1, objNode2Insert.getDOMElement());
					found = true;
					break;
				}
			}
			//			for (int i = 0; i < list.size(); i++) {
			//				if (list.get(i).equals(objCurrentNode)) {
			//					if (i > 0) {
			//						Element previousNode = (Element) list.get(i - 1);
			//						Utils.setAttribute(conAttrNEXT_STATE, state, previousNode, _dom);
			//						String strNextNextState = Utils.getAttributeValue(conAttrSTATE, (Element) list.get(i));
			//						Utils.setAttribute(conAttrNEXT_STATE, strNextNextState, objNode2Insert);
			//						objJobChain.addContent(objJobChain.indexOf(previousNode) + 1, objNode2Insert);
			//
			//						found = true;
			//						break;
			//					}
			//				}
			//			}
		}

		if (!found) {
			objJobChain.addContent(0, objNode2Insert.getDOMElement());
		}

		_node = objNode2Insert;
		setDirty();
		setStates();
	}

	public String changeNodeSequence(final boolean flgDirectionIsUpwards, final boolean isJobchainNode, final int intSelectedTabItem,
			final boolean flgDoReorderStates) {

		String state = _node.getState();
		String job = _node.getJobName();
		String delay = _node.getDelay();
		String next = _node.getNextState();
		String error = _node.getErrorState();
		boolean removeFile = false; // TODO implement in NodeWrapper,
		String moveTo = ""; // TODO implement in NodeWrapper

		try {
			if (flgDoReorderStates) {
				// TODO I18N
				String msg = "The node " + state + " is an Endnode and cannot be changed with Node due to reorder is activated";

				if (_node.isEndNode() == true) {
					MainWindow.message(msg, SWT.ICON_INFORMATION);
					return "";
				}

				int intSelectionIndex = getSelectionIndex();
				if (intSelectionIndex != -1) {
					if (flgDirectionIsUpwards) {
						JobChainNodeWrapper objN = (JobChainNodeWrapper) objJobChainNodesTable.getItem(intSelectionIndex - 1).getData();
						if (objN.isEndNode()) {
							MainWindow.message(msg, SWT.ICON_INFORMATION);
							return "";
						}
					}
					else {
						if (intSelectionIndex < objJobChainNodesTable.getItemCount() - 1) {
							JobChainNodeWrapper objN = (JobChainNodeWrapper) objJobChainNodesTable.getItem(intSelectionIndex + 1).getData();
							if (objN.isEndNode()) {
								MainWindow.message(msg, SWT.ICON_INFORMATION);
								return "";
							}
						}
					}
				}
			}

			List l = objJobChain.getContent();
			int cIndex = -1;
			boolean found = false;

			for (int i = 0; i < objJobChain.getContentSize(); i++) {
				Object objI = l.get(i);
				if (objI instanceof Element) {
					Element elem_ = (Element) objI;
					if (flgDirectionIsUpwards) {
						if (elem_.equals(_node.getDOMElement())) {
							break;
						}
						else {
							cIndex = i;
							if (cIndex == -1) {
								cIndex = 0;
							}
						}
					}
					else {
						if (elem_.equals(_node.getDOMElement())) {
							found = true;
						}
						else {
							if (found) {
								cIndex = i;
								break;
							}
						}
					}
				}
			}

			JobChainNodeWrapper objSelectedNode = getJobChainNodeWrapper((Element) _node.getDOMElement().clone());
			boolean flgSwapped = false;
			if (flgDoReorderStates == false) {
				flgSwapped = true;
			}
			else {
				Filter elementFilter2 = new ElementFilter(conTagJOB_CHAIN_NODE, getChain().getNamespace());
				List elements = getChain().getContent(elementFilter2);
				int size = elements.size();
				for (int i = 0; i < size; ++i) {
					JobChainNodeWrapper objPredecessorNode = null;
					JobChainNodeWrapper prev2Element = null;
					JobChainNodeWrapper objSuccessorNode = null;

					if (elements.get(i).equals(_node.getDOMElement())) {
						if (i >= 2) {
							prev2Element = getJobChainNodeWrapper((Element) elements.get(i - 2));
						}

						if (i >= 1) {
							objPredecessorNode = getJobChainNodeWrapper((Element) elements.get(i - 1));
						}

						if (size > i + 1) {
							objSuccessorNode = getJobChainNodeWrapper((Element) elements.get(i + 1));
						}

						// TODO move to JobChain class
						if (flgDirectionIsUpwards) { // what if the next-state are not in a row?
							if (objSelectedNode.CanSwap(objPredecessorNode) == true) {
								flgSwapped = true;
								String strN = objSelectedNode.getNextState();
								objSelectedNode.SwapNextState(objPredecessorNode);
								objPredecessorNode.setNextState(strN);
								if (prev2Element != null) {
									prev2Element.SwapNextState(objSelectedNode);
								}
							}
						}
						else { // what if the next-state are not in a row?
							if (objSuccessorNode != null && objSuccessorNode.CanSwap(objSelectedNode) == true) {
								flgSwapped = true;
								String strN = objSuccessorNode.getNextState();
								objSuccessorNode.SwapNextState(objSelectedNode);
								objSelectedNode.setNextState(strN);
								if (objPredecessorNode != null) {
									objPredecessorNode.SwapNextState(objSuccessorNode);
								}
							}
						}
						break;
					}
				}
			}

			if (flgSwapped == true) {
				if (objJobChain.getChildren().contains(_node.getDOMElement())) {
					objJobChain.removeContent(_node.getDOMElement());
				}
				objJobChain.addContent(cIndex, objSelectedNode.getDOMElement());
				_node = objSelectedNode;
				setDirty();

				//			setStates();
				//			populateNodesTable(table);
				objJobChainNodesTable.getItem(intSelectedTabItem).dispose();
				int intNewIndex = intSelectedTabItem;
				if (flgDirectionIsUpwards) {
					intNewIndex--;
				}
				else {
					intNewIndex++;
				}
				objSelectedNode.populateTableItem(new TableItem(objJobChainNodesTable, SWT.None, intNewIndex));
				objJobChainNodesTable.setSelection(intNewIndex);
			}
		}
		catch (Exception e) {
			new ErrorLog("error in " + SOSClassUtil.getMethodName(), e);
		}

		return state;
	}

	public void applyFileOrderSource(final String directory, final String regex, final String next_state, final String max, final String repeat,
			final String delay_after_error) {

		if (_objFileOrderSource == null) {
			Element eleFileSorderSource = new Element(conTagFILE_ORDER_SOURCE);
			objJobChain.addContent(eleFileSorderSource);
			_objFileOrderSource = eleFileSorderSource;
		}
		Utils.setAttribute(conAttrDIRECTORY, directory, _objFileOrderSource, _dom);
		Utils.setAttribute(conAttrREGEX, regex, _objFileOrderSource, _dom);
		Utils.setAttribute(conAttrNEXT_STATE, next_state, _objFileOrderSource, _dom);
		Utils.setAttribute(conAttrMAX, max, _objFileOrderSource, _dom);
		Utils.setAttribute(conAttrREPEAT, repeat, _objFileOrderSource, _dom);
		Utils.setAttribute(conAttrDELAY_AFTER_ERROR, delay_after_error, _objFileOrderSource, _dom);
		setDirty();
	}

	private int getIndexOfNode(final TableItem item) {
		int index = 0;
		if (objJobChain != null) {
			Iterator it = objJobChain.getChildren().iterator();
			int i = 0;
			while (it.hasNext()) {
				Element node = (Element) it.next();
				if (Utils.getAttributeValue(conAttrSTATE, node).equals(item.getText(0))) {
					index = i;
				}
				i = i + 1;
			}
		}
		return index;
	}

	private int getIndexOfSource(final TableItem item) {
		int index = 0;
		if (objJobChain != null) {
			Iterator it = objJobChain.getChildren().iterator();
			int i = 0;
			while (it.hasNext()) {
				Element node = (Element) it.next();
				if (node.getName() == conTagFILE_ORDER_SOURCE) {
					if (Utils.getAttributeValue(conAttrDIRECTORY, node) == item.getText(0) && Utils.getAttributeValue(conAttrREGEX, node) == item.getText(1)) {
						index = i;
					}
					i = i + 1;
				}
			}
		}
		return index;
	}

	public void deleteNode() {
		int intSelectionIndex = getSelectionIndex();
		if (intSelectionIndex > 0) {
			List<Element> nodes = objJobChain.getChildren();
			int index = getIndexOfNode(objJobChainNodesTable.getItem(intSelectionIndex));
			if (index > 0) {
				try {
					JobChainNodeWrapper objPredecessor = getJobChainNodeWrapper(nodes.get(index - 1));
					JobChainNodeWrapper objSuccessor = getJobChainNodeWrapper(nodes.get(index + 1));
					if (checkNodesAreChained(objPredecessor, _node, objSuccessor)) {
						objPredecessor.setNextState(objSuccessor.getState());
					}
					DetailsListener.deleteDetailsState(objJobChainNodesTable.getSelection()[0].getText(0), getChainName(), _dom);
					nodes.remove(index);
					_node = null;
					setDirty();
					setStates();
				}
				catch (Exception e) { // ignore
					e.printStackTrace();
				}
			}
		}
	}

	public void completeNodes() {
		try {
			selectNode();
			for (TableItem item : objJobChainNodesTable.getItems()) {
				JobChainNodeWrapper objN = (JobChainNodeWrapper) item.getData();
				if (objN.isJobNode() == true) {
					String strNextState = objN.getNextState();
					if (isStateDefined(strNextState) == false) {
						applyNode(true, strNextState, "", "", "", "", false, "", "");
						objN.setNextStateIsInvalid(false);
					}

					String strErrorState = objN.getErrorState();
					if (isStateDefined(strErrorState) == false) {
						applyNode(true, strErrorState, "", "", "", "", false, "", "");
						objN.setErrorStateIsInvalid(false);
					}
				}
			}
		}
		catch (Exception ex) {
			new ErrorLog(JOE_E_0002.params(SOSClassUtil.getMethodName()), ex);
		}
		finally {
			populateNodesTable();
		}
	}

	private boolean checkNodesAreChained(final JobChainNodeWrapper objPredecessorNode, final JobChainNodeWrapper objCurrentNode,
			final JobChainNodeWrapper objSuccessorNode) {
		boolean flgR = false;
		if (objCurrentNode.getNextState().equalsIgnoreCase(objSuccessorNode.getState())
				&& objPredecessorNode.getNextState().equalsIgnoreCase(objCurrentNode.getState())) {
			flgR = true;
		}
		return flgR;
	}

	public void deleteFileOrderSource() {
		List sources = objJobChain.getChildren(conTagFILE_ORDER_SOURCE);
		int index = getIndexOfSource(objJobChainNodesTable.getItem(getSelectionIndex()));
		sources.remove(index);
		clearFileOrderSource();
		setDirty();
	}

	/**
	 * wird benutzt um die combox mit den jobnamen zu f�llen. hat hier eigentlich gar nichts zu suchen.
	*
	* \brief getJobs
	*
	* \details
	*
	* \return String[]
	*
	 */
	public String[] getJobs() {
		if (_config == null)
			return new String[0];
		Element job = _config.getChild("jobs");
		if (job != null) {
			int size = 0;
			List jobs = job.getChildren("job");
			Iterator it = jobs.iterator();
			int index = 0;
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String order_job = e.getAttributeValue("order");
				if (order_job != null && order_job.equals("yes")) {
					size = size + 1;
				}
			}
			String[] names = new String[size];

			it = jobs.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getAttributeValue("name");
				String order_job = e.getAttributeValue("order");
				if (order_job != null && order_job.equals("yes")) {
					names[index++] = name != null ? name : "";
				}
			}
			return names;
		}
		else
			return new String[0];
	}

	private void setStates() {
		List nodes = objJobChain.getChildren(conTagJOB_CHAIN_NODE);
		List sinks = objJobChain.getChildren("file_order_sink");
		List endNodes = objJobChain.getChildren("job_chain_node.end");
		Iterator it = nodes.iterator();
		_states = new String[sinks.size() + nodes.size() + endNodes.size()];

		listOfAllState = new ArrayList();

		int index = 0;
		while (it.hasNext()) {
			Element el = (Element) it.next();
			String state = el.getAttributeValue(conAttrSTATE);
			_states[index++] = state != null ? state : "";
			if (state != null && !listOfAllState.contains(state))
				listOfAllState.add(state);
			String errorState = el.getAttributeValue(conAttrERROR_STATE);
			if (errorState != null && !listOfAllState.contains(errorState))
				listOfAllState.add(errorState);
		}

		it = sinks.iterator();
		while (it.hasNext()) {
			String state = ((Element) it.next()).getAttributeValue(conAttrSTATE);
			_states[index++] = state != null ? state : "";
			if (state != null && !listOfAllState.contains(state))
				listOfAllState.add(state);
		}

		it = endNodes.iterator();
		while (it.hasNext()) {
			Element el = (Element) it.next();
			String state = el.getAttributeValue(conAttrSTATE);
			_states[index++] = state != null ? state : "";
			if (state != null && !listOfAllState.contains(state))
				listOfAllState.add(state);
		}
	}

	public String[] getStates() {
		if (_states == null)
			return new String[0];
		else
			if (_node != null) {
				String state = getState();
				int index = 0;
				int intLength = _states.length;

				String[] states = new String[intLength];
				for (int i = 0; i < intLength; i++) {
					if (!_states[i].equals(state))
						states[index++] = _states[i];
				}
				return states;
			}
			else {
				return _states;
			}
	}

	public String[] getAllStates() {
		try {
			if (listOfAllState == null)
				return new String[0];
			else {
				if (_node == null) {
					int index = 0;
					String[] states = new String[listOfAllState.size()];
					for (int i = 0; i < listOfAllState.size(); i++) {
						states[index++] = listOfAllState.get(i);
					}
					return states;
				}
				else {
					String errorState = getErrorState() != null ? getErrorState() : "";
					String state = getState() != null ? getState() : "";
					int i_ = 0;
					if (state.length() > 0)
						i_++;
					if (errorState.length() > 0)
						i_++;

					int index = 0;
					if (listOfAllState.size() - i_ < -1)
						i_ = 0;

					String[] states = new String[listOfAllState.size() - i_];
					for (int i = 0; i < listOfAllState.size(); i++) {
						if (!listOfAllState.get(i).equals(state) && !listOfAllState.get(i).equals(errorState))
							states[index++] = listOfAllState.get(i) != null ? listOfAllState.get(i).toString() : "";
					}
					return states;
				}
			}
		}
		catch (Exception e) {
			new ErrorLog(e.getLocalizedMessage(), e);
			return new String[0];
		}
	}

	public boolean isValidState(final String state) {
		if (_states != null) {
			for (int i = 0; i < _states.length; i++) {
				if (_states[i].equalsIgnoreCase(state) && !_states[i].equals(getState())) {
					return false;
				}
			}
		}
		return true;
	}

	public String getDiagramFileName() {

		String strR = null;
		try {
			strR = objJSJobChain.createDOTFile();
		}
		catch (Exception e) {
			new ErrorLog(e.getLocalizedMessage(), e);
		}
		return strR;
	}

	public void createMissingJobs() {

		try {
			int intIndex = 0;
			for (Object eleNode : objJobChain.getChildren()) {
				JobChainNodeWrapper objJobChainNode = getJobChainNodeWrapper((Element) eleNode);
				objJobChainNode.setIndex(++intIndex);
				String strJobName = objJobChainNode.getJobName();
				if (objJobChainNode.isNode4NodesTable() == true && isNotEmpty(strJobName)) {
					JSFile objF = getJobFile(strJobName);
					if (objF.exists() == false) {
						// TODO Template-Name as Parameter
						JSXMLFile objTemplateFile = new JSXMLFile(
								"R:/backup/sos/java/development/com.sos.scheduler.editor/src/sos/scheduler/editor/job-template.xml");
						System.out.println(objTemplateFile.getPath());
						if (objTemplateFile.exists() == true) {
							String strContent = objTemplateFile.getContent();
							strContent = strContent.replaceAll("\\$\\{jobname\\}", strJobName);
							strContent = strContent.replaceAll("\\$\\{title\\}", "title for " + strJobName);
							SAXBuilder objP = new SAXBuilder();
							Document document = objP.build(new InputSource(new StringReader(strContent)));
							objJobChain = (Element) document.getRootElement().clone();
							objF.WriteLine(strContent);
							objF.close();
						}
						//					else {
						//						objJobChain = new Element("job");
						//						setName("new job ");
						//						setTitle("new job");
						//						setVisible(true);
						//					}
					}
				}
			}
		}
		catch (Exception e) {
			new ErrorLog(e.getLocalizedMessage(), e);
		}
		finally {

		}
	}

	private JSFile getJobFile(final String strJobName) {
		String strLiveFolderPathName = "";
		if (strJobName.startsWith("/")) {
			strLiveFolderPathName = Options.getSchedulerHotFolder();
			strLiveFolderPathName += strJobName + JSObjJob.fileNameExtension;
		}
		else {
			strLiveFolderPathName = get_dom().getFilename();
			strLiveFolderPathName += "/" + strJobName + JSObjJob.fileNameExtension;
		}
		strLiveFolderPathName = strLiveFolderPathName.replaceAll("\\\\", "/");
		JSFile objF = new JSFile(strLiveFolderPathName);
		boolean flgR = objF.exists();
		return objF;
	}
}
