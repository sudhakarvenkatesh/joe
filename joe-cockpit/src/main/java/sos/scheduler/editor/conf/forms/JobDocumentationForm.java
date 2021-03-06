package sos.scheduler.editor.conf.forms;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.jdom.Element;

import com.sos.joe.globals.interfaces.ISchedulerUpdate;
import com.sos.joe.xml.jobscheduler.SchedulerDom;

import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.conf.container.JobDocumentation;
import sos.scheduler.editor.conf.listeners.JobListener;

public class JobDocumentationForm extends Composite {

    private JobListener objDataProvider = null;

    public JobDocumentationForm(Composite parent, int style, SchedulerDom dom, Element job, ISchedulerUpdate main) {
        super(parent, style);
        dom.setInit(true);
        this.setEnabled(Utils.isElementEnabled("job", dom, job));
        objDataProvider = new JobListener(dom, job, main);
        initialize();
        dom.setInit(false);
    }

    public void apply() {
        //
    }

    public boolean isUnsaved() {
        return false;
    }

    private void initialize() {
        this.setLayout(new FillLayout());
        new JobDocumentation(this, objDataProvider);
        setSize(new org.eclipse.swt.graphics.Point(723, 566));
    }

    public void initForm() {
        //
    }

}