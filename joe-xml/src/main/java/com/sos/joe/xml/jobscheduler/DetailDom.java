package com.sos.joe.xml.jobscheduler;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Text;
import org.jdom.output.SAXOutputter;

import com.sos.joe.globals.JOEConstants;
import com.sos.joe.globals.messages.ErrorLog;
import com.sos.joe.globals.options.Options;
import com.sos.joe.xml.DomParser;

public class DetailDom extends DomParser {

    @SuppressWarnings("unused")
    private final static String conSVNVersion = "$Id: DetailDom.java 14983 2011-08-21 08:47:30Z kb $";

    public DetailDom() {
        super(new String[] {}, new String[] {}, Options.getDetailXSLT());
    }

    public void initDetail() {
        Element application = new Element("job_chain");
        Document document_ = new Document(new Element("settings").addContent(application));
        setDoc(document_);
    }

    public boolean read(String filename) throws JDOMException, IOException {
        return read(filename, false);
    }

    public boolean read(String filename, boolean validate) throws JDOMException, IOException {
        StringReader sr = new StringReader(readFile(filename));
        Document doc = getBuilder(validate).build(sr);
        sr.close();
        setDoc(doc);
        // set comments as attributes
        setComments(getDoc().getContent());
        setChanged(false);
        setFilename(filename);
        return true;
    }

    public boolean readString(String str, boolean validate) throws JDOMException, IOException {
        StringReader sr = new StringReader(str);
        Document doc = getBuilder(validate).build(sr);
        sr.close();
        setDoc(doc);
        // set comments as attributes
        setComments(getDoc().getContent());
        setChanged(false);
        return true;
    }

    private String readFile(String filename) throws IOException {
        String encoding = DEFAULT_ENCODING;
        String line = null;
        StringBuffer sb = new StringBuffer();
        Pattern p3 = Pattern.compile("<?xml.+encoding\\s*=\\s*\"([^\"]+)\"");
        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {
            while ((line = br.readLine()) != null) {
                Matcher m3 = p3.matcher(line);
                if (m3.find()) {
                    encoding = m3.group(1);
                }
                // System.out.println(line);
                sb.append(line + "\n");
            }
            String str = new String(sb.toString().getBytes(), encoding);
            JOEConstants.SCHEDULER_ENCODING = encoding;
            setFilename(filename);
            return str;
        } finally {
            br.close();
        }
    }

    /*
     * public void write(String filename) throws IOException, JDOMException {
     * File f = new File(filename); try { String encoding =
     * JOEConstants.SCHEDULER_ENCODING; if (encoding.equals("")) encoding =
     * DEFAULT_ENCODING; JDOMSource in = new JDOMSource(getDoc()); Format format
     * = Format.getPrettyFormat(); format.setEncoding(encoding); XMLOutputter
     * outp = new XMLOutputter(format); outp.output(in.getDocument(), new
     * FileWriter(f)); } catch (Exception e) { int res =
     * MainWindow.message(Messages.getString("MainListener.outputInvalid", new
     * String[] { e.getMessage() }), SWT.ICON_WARNING | SWT.YES | SWT.NO); if
     * (res == SWT.NO) return;
     * System.out.println("..error in DetailDom.save. Could not save file " +
     * e.getMessage()); } setFilename(filename); setChanged(false); }
     */
    public void write(String filename) throws IOException, JDOMException {
        String encoding = JOEConstants.SCHEDULER_ENCODING;
        if (encoding.equals(""))
            encoding = DEFAULT_ENCODING;
        reorderDOM();
        FormatDetailHandler handler = new FormatDetailHandler(this);
        handler.setEnconding(encoding);
        SAXOutputter saxo = new SAXOutputter(handler);
        saxo.output(getDoc());
        try {
            getBuilder(false).build(new StringReader(handler.getXML()));
        } catch (JDOMException e) {
            int res = ErrorLog.message(Messages.getMsg("MainListener.outputInvalid", e.getMessage()), SWT.ICON_WARNING | SWT.YES | SWT.NO);
            if (res == SWT.NO)
                return;
        }
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filename), encoding);
        writer.write(handler.getXML());
        writer.close();
        setFilename(filename);
        setChanged(false);
    }

    public void writeElement(String filename, Document doc) throws IOException, JDOMException {
        String encoding = JOEConstants.SCHEDULER_ENCODING;
        if (encoding.equals(""))
            encoding = DEFAULT_ENCODING;
        reorderDOM(doc.getRootElement());
        FormatDetailHandler handler = new FormatDetailHandler(this);
        handler.setEnconding(encoding);
        SAXOutputter saxo = new SAXOutputter(handler);
        // saxo.output(getDoc());
        saxo.output(doc);
        try {
            getBuilder(false).build(new StringReader(handler.getXML()));
        } catch (JDOMException e) {
            int res = ErrorLog.message(Messages.getMsg("MainListener.outputInvalid", e.getMessage()), SWT.ICON_WARNING | SWT.YES | SWT.NO);
            if (res == SWT.NO)
                return;
        }
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filename), encoding);
        writer.write(handler.getXML());
        writer.close();
        // FileOutputStream stream = new FileOutputStream(new File(filename));
        // XMLOutputter out = new XMLOutputter(getFormat());
        // out.output(_doc, stream);
        // stream.close();
        // setFilename(filename);
        setChanged(false);
        // deorderDOM();
    }

    /*
     * public String getXML(Element element) throws JDOMException { String
     * encoding = JOEConstants.SCHEDULER_ENCODING; if (encoding.equals(""))
     * encoding = DEFAULT_ENCODING; JDOMSource in = new JDOMSource(element);
     * Format format = Format.getPrettyFormat(); format.setEncoding(encoding);
     * XMLOutputter outp = new XMLOutputter(format); String _xML =
     * outp.outputString(getDoc()); return _xML; }
     */
    public String getXML(Element element) throws JDOMException {
        reorderDOM(element);
        FormatDetailHandler handler = new FormatDetailHandler(this);
        handler.setEnconding(DEFAULT_ENCODING);
        SAXOutputter saxo = new SAXOutputter(handler);
        saxo.output(element);
        return handler.getXML();
    }

    private void setComments(List content) {
        if (content != null) {
            String comment = null;
            for (Iterator it = content.iterator(); it.hasNext();) {
                Object o = it.next();
                if (o instanceof Comment) {
                    comment = ((Comment) o).getText();
                } else if (o instanceof Element) {
                    Element e = (Element) o;
                    if (comment != null) { // set comment as value
                        e.setAttribute("__comment__", comment.trim());
                        comment = null;
                    }
                    setComments(e.getContent()); // recursion
                } else if (!(o instanceof Text)) {
                    comment = null;
                }
            }
        }
    }
}
