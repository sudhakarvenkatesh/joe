package sos.scheduler.editor.classes;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/** \class IntegerField
 * 
 * \brief IntegerField -
 * 
 * \details
 *
 * \section IntegerField.java_intro_sec Introduction
 *
 * \section IntegerField.java_samples Some Samples
 *
 * \code .... code goes here ... \endcode
 *
 * <p style="text-align:center">
 * <br />
 * --------------------------------------------------------------------------- <br />
 * APL/Software GmbH - Berlin <br />
 * ##### generated by ClaviusXPress (http://www.sos-berlin.com) ######### <br />
 * ---------------------------------------------------------------------------
 * </p>
 * \author Uwe Risse \version 24.08.2011 \see reference
 *
 * Created on 24.08.2011 13:51:13 */

public class IntegerField extends Text {

    public IntegerField(Composite arg0, int arg1) {
        super(arg0, arg1);
        addListener();
    }

    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }

    private void addListener() {
        this.addVerifyListener(new VerifyListener() {

            public void verifyText(VerifyEvent e) {
                String string = e.text;
                char[] chars = new char[string.length()];
                string.getChars(0, chars.length, chars, 0);
                for (int i = 0; i < chars.length; i++) {
                    if (!('0' <= chars[i] && chars[i] <= '9')) {
                        e.doit = false;
                        return;
                    }
                }
            }
        });
    }

    @SuppressWarnings("unused")
    private final String conClassName = "IntegerField";

}