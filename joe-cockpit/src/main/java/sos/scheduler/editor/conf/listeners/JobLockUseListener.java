package sos.scheduler.editor.conf.listeners;

import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.jdom.Element;

import sos.scheduler.editor.app.Utils;

import com.sos.joe.xml.jobscheduler.SchedulerDom;

public class JobLockUseListener {

    private SchedulerDom _dom = null;
    private Element _job = null;
    private List _lockUseList = null;
    private Element _lockUse = null;

    public JobLockUseListener(SchedulerDom dom, Element job) {
        _dom = dom;
        _job = job;
        _lockUseList = _job.getChildren("lock.use");
    }

    public void fillLockUse(Table table) {
        table.removeAll();
        Iterator it = _lockUseList.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, Utils.getAttributeValue("lock", e));
            item.setText(1, Utils.getAttributeValue("exclusive", e));
        }
    }

    public void newLockUse() {
        _lockUse = new Element("lock.use");
    }

    public void selectLockUse(int index) {
        if (index >= 0 && index < _lockUseList.size()) {
            _lockUse = (Element) _lockUseList.get(index);
        }
    }

    public void applyLockUse(String lockUse, boolean exclusive) {
        if (_lockUse == null) {
            newLockUse();
        }
        Utils.setAttribute("lock", lockUse, _lockUse, _dom);
        Utils.setAttribute("exclusive", exclusive, _lockUse, _dom);
        if (!_lockUseList.contains(_lockUse)) {
            _lockUseList.add(_lockUse);
        }
        _dom.setChanged(true);
        _dom.setChangedForDirectory("job", Utils.getAttributeValue("name", _job), SchedulerDom.MODIFY);
    }

    public void deleteLockUse(int index) {
        if (index >= 0 && index < _lockUseList.size()) {
            _lockUseList.remove(index);
            _lockUse = null;
            _dom.setChanged(true);
            _dom.setChangedForDirectory("job", Utils.getAttributeValue("name", _job), SchedulerDom.MODIFY);
        }
    }

    public String getLockUse() {
        return Utils.getAttributeValue("lock", _lockUse);
    }

    public boolean getExclusive() {
        if (Utils.getAttributeValue("exclusive", _lockUse) == null || Utils.getAttributeValue("exclusive", _lockUse).isEmpty()) {
            return true;
        } else {
            return "yes".equals(Utils.getAttributeValue("exclusive", _lockUse));
        }
    }

    public boolean isValidLock(String lock) {
        if ("".equals(lock)) {
            return false;
        }
        if (_lockUseList != null) {
            for (Iterator it = _lockUseList.iterator(); it.hasNext();) {
                Element e = (Element) it.next();
                if (Utils.getAttributeValue("lock", e).equals(lock)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String[] getLocks() {
        String[] names = null;
        if (_dom.isLifeElement()) {
            names = new String[0];
            return names;
        }
        Element locks = _dom.getRoot().getChild("config").getChild("locks");
        if (locks != null) {
            List list = locks.getChildren("lock");
            names = new String[list.size()];
            int i = 0;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Object o = it.next();
                if (o instanceof Element) {
                    String name = ((Element) o).getAttributeValue("name");
                    if (name == null) {
                        name = "";
                    }
                    names[i++] = name;
                }
            }
        }
        return names;
    }

    public Element getJob() {
        return _job;
    }

}