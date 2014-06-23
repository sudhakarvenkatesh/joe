package sos.scheduler.editor.classes;

import java.awt.Point;
import java.util.prefs.Preferences;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.sos.dialog.classes.FormBase;

/**
* \class WindowsSaver
*
* \brief WindowsSaver -
*
* \details
*
* \code
*   .... code goes here ...
* \endcode
*
* <p style="text-align:center">
* <br />---------------------------------------------------------------------------
* <br /> APL/Software GmbH - Berlin
* <br />##### generated by ClaviusXPress (http://www.sos-berlin.com) #########
* <br />---------------------------------------------------------------------------
* </p>
* \author Uwe Risse
* \version 08.11.2011
* \see reference
*
* Created on 08.11.2011 15:29:14
 */

public class WindowsSaver1
{

	@SuppressWarnings("unused")
	private static final String	conSVNVersion		= "$Id: WindowsSaver.java 20734 2013-07-22 08:46:55Z kb $";
	private static final Logger	logger				= Logger.getLogger(FormBase.class);

	private static final String	conPropertyWIDTH	= "width";
	@SuppressWarnings("unused")
	private final String		conClassName		= "WindowsSaver";
	private final Shell			shell;
	private final Preferences	prefs;
	private final Point			defaultSize;
	private final Point			defaultLocation;
	private final String		className;

	private String				strKey				= "";

	public void setKey(final String pstrKey) {
		strKey = pstrKey;
	}

	public WindowsSaver1(final Class<?> c, final Shell s, final int x, final int y) {
		prefs = Preferences.userNodeForPackage(c);
		shell = s;
		className = c.getName();
		defaultSize = new Point(x, y);
		defaultLocation = new Point(100, 100);
	}

	private int getInt(final String s, final int def) {
		try {
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e) {
			return def;
		}
	}

	public void restoreWindow() {
		logger.debug("restoreWindow: " + className);
		shell.setSize(getInt(prefs.get("win:sizeX:" + className, String.valueOf(defaultSize.x)), defaultSize.x),
				getInt(prefs.get("win:sizeY:" + className, String.valueOf(defaultSize.y)), defaultSize.y));
		shell.setLocation(getInt(prefs.get("win:locateX:" + className, String.valueOf(defaultLocation.x)), defaultLocation.x),
				getInt(prefs.get("win:locateY:" + className, String.valueOf(defaultLocation.y)), defaultLocation.y));
	}

	public void restoreWindowLocation() {
		shell.setLocation(getInt(prefs.get("win:locateX:" + className, String.valueOf(defaultLocation.x)), defaultLocation.x),
				getInt(prefs.get("win:locateY:" + className, String.valueOf(defaultLocation.y)), defaultLocation.y));
	}

	public void restoreWindowSize() {
		shell.setSize(getInt(prefs.get("win:sizeX:" + className, String.valueOf(defaultSize.x)), defaultSize.x),
				getInt(prefs.get("win:sizeY:" + className, String.valueOf(defaultSize.y)), defaultSize.y));
	}

	public void saveWindow() {
		logger.debug("saveWindow");
		prefs.put("win:sizeX:" + className, String.valueOf(shell.getSize().x));
		prefs.put("win:sizeY:" + className, String.valueOf(shell.getSize().y));
		prefs.put("win:locateX:" + className, String.valueOf(shell.getLocation().x));
		prefs.put("win:locateY:" + className, String.valueOf(shell.getLocation().y));
	}

	private String getPropertyKey() {
		return "properties/" + strKey;
	}

	public void saveProperty(final String pstrPropName, final String pstrPropValue) {
		prefs.node(getPropertyKey()).put(pstrPropName, pstrPropValue);
		logger.debug(String.format("saveProperty %1$s = %2$s", pstrPropName, pstrPropValue));
	}

	public String getProperty(final String pstrPropName) {
		String strR = prefs.node(getPropertyKey()).get(pstrPropName, "");
		logger.debug(String.format("getProperty %1$s = %2$s", pstrPropName, strR));
		return strR;
	}

	public void saveTableColumn(final String tableName, final TableColumn t) {
		String strCaption = (String) t.getData("caption");
		if (strCaption == null) {
			strCaption = t.getText();
		}
		String name = tableName + "/col/" + "_" + strCaption;
		logger.debug("save column: " + name);
		prefs.node(name).put(conPropertyWIDTH, String.valueOf(t.getWidth()));
	}

	public void TableColumnOrderRestore(final Table pobjTable) {

		String name = pobjTable.getData("caption") + "/colorder/default";
		String strNoOfColumn = prefs.node(name).get("NoOfColumns", "");
		if (strNoOfColumn.length() > 0) {
			int intNoOfColumn = new Integer(strNoOfColumn);
			if (intNoOfColumn == pobjTable.getColumnCount()) {
				int[] i = new int[intNoOfColumn];
				String strColOrder = prefs.node(name).get("columnorder", "");
				if (strColOrder.length() > 0) {
					int iCol = 0;
					for (String strIndex : strColOrder.split(";")) {
						if (strIndex.length() > 0) {
							i[iCol++] = new Integer(strIndex);
						}
					}
					pobjTable.setColumnOrder(i);
				}
			}
		}
	}

	public void TableColumnOrderSave(final Table pobjTable) {
		String strOrder = "";
		int intSize = pobjTable.getColumnOrder().length;
		for (int i : pobjTable.getColumnOrder()) {
			strOrder += i + ";";
		}
		String name = pobjTable.getData("caption") + "/colorder/default";
		logger.debug("save column order: " + name + ", " + strOrder);
		prefs.node(name).put("columnorder", strOrder);
		prefs.node(name).put("NoOfColumns", intSize + "");

		//	        for (int i = 0; i < order.length / 2; i++) {
		//	          int temp = order[i];
		//	          order[i] = order[order.length - i - 1];
		//	          order[order.length - i - 1] = temp;
		//	        }
		//	        table.setColumnOrder(order);
	}

	public void restoreTableColumn(final String tableName, final TableColumn t, final int def) {
		String strCaption = (String) t.getData("caption");
		if (strCaption == null) {
			strCaption = t.getText();
		}
		String name = tableName + "/col/" + "_" + strCaption;
		try {
			String strVal = prefs.node(name).get(conPropertyWIDTH, String.valueOf(def));
			logger.debug(String.format("restore column '%1$s' with size '%2$s': ", name, strVal));
			t.setWidth(this.getInt(strVal, def));
		}
		catch (Exception e) {
			t.setWidth(def);
		}
	}
}
