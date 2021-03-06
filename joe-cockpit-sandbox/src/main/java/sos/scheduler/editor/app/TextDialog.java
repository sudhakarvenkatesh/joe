package sos.scheduler.editor.app;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.sos.joe.globals.messages.ErrorLog;
import com.sos.joe.globals.messages.Messages;
import com.sos.joe.globals.misc.ResourceManager;
import com.sos.joe.globals.options.Options;

public class TextDialog extends Dialog {
	private Shell	_shell						= null;
	private Text	_styledText					= null;
	private Point	_size						= new Point(300, 250);
	private Image	_image						= null;
	private Button	clipboardButton				= null;
	private int		_shellStyle					= SWT.CLOSE | SWT.TITLE | SWT.APPLICATION_MODAL;
	private boolean	clipBoardClick				= false;
	private Button	butApply					= null;
	private boolean	applyBoardClick				= false;
	private boolean	bSaveWindow					= false;
	private boolean	bEdit						= false;
	private Label	addPredefinedFunctionsLabel	= null;
	private Combo	cboFunctions				= null;
	private boolean	showFunctions				= false;
	private String	scriptLanguage				= "";
	private Button	butShowSiteInFuture			= null;

	public TextDialog(Shell parent, int shellStyle, int textStyle) {
		super(parent, SWT.NONE);
		_shellStyle = shellStyle;
		init();
	}

	public TextDialog(Shell parent) {
		super(parent, SWT.NONE);
		init();
	}

	public void setContent(String content) {
		setContent(content, SWT.LEFT);
		bEdit = true;
	}

	/**
	 * Schreibt den String in das Dialog und schrolt zu der Stelle, 
	 * in der selectString steht 
	 * @param content
	 * @param selectString
	 */
	public void setContent(String content, String selectString) {
		try {
			String _selectString = "=\"" + selectString + "\"";
			int pos = content.indexOf(_selectString);
			if (pos == -1) {
				pos = content.indexOf(selectString);
			}
			setContent(content, SWT.LEFT);
			_styledText.setSelection(pos, pos);
			_styledText.showSelection();
			bEdit = true;
		}
		catch (Exception e) {
			try {
				new ErrorLog("error in TextDilalog.setContent()", e);
			}
			catch (Exception ee) {
			}
		}
	}

	public void setContent(String content, int alignment) {
		content = content.replaceAll("\r", "");
		_styledText.setText(content);
		//_styledText.setLineAlignment(0, _styledText.getLineCount(), alignment);
		/*_styledText.append("test line1\r\n");
		_styledText.append("test line2\r");
		_styledText.append("\ntest line3\r\n");
		*/
		bEdit = true;
	}

	public void setSize(Point size) {
		_size = size;
	}

	public Text getStyledText() {
		return _styledText;
	}

/*
	public StyledText getStyledText() {
		return _styledText;
	}
*/
	private void init() {
		Shell parent = getParent();
		_shell = new Shell(parent, _shellStyle);
		_shell.addShellListener(new ShellAdapter() {
			@Override public void shellClosed(final ShellEvent e) {
				if (bSaveWindow)
					Options.saveWindow(_shell, "xml_dialog");
				else
					if (!bSaveWindow && butApply.isEnabled()) {
						close();
					}
			}
		});
		_shell.setVisible(false);
		_shell.setText(getText());
		try {
			_image = ResourceManager.getImageFromResource("/sos/scheduler/editor/JOEConstants.png");
			_shell.setImage(_image);
		}
		catch (Exception e) {
			try {
				new ErrorLog("error in TextDilalog.init()", e);
			}
			catch (Exception ee) {
			}
			e.printStackTrace();
			return;
		}
		setDialog();
	}

	public String open(boolean bLoadWindow) {
		try {
			String s = "";
			if (bLoadWindow)
				Options.loadWindow(_shell, "xml_dialog");
			else
				_shell.setSize(_size);
			_shell.open();
			Display display = _shell.getDisplay();
			while (!_shell.isDisposed()) {
				s = _styledText.getText();
				if (!display.readAndDispatch())
					display.sleep();
			}
			if (_styledText != null)
				_styledText.dispose();
			return s;
		}
		catch (java.lang.IllegalArgumentException ex) {
			ex.printStackTrace();
			try {
				new ErrorLog("error in TextDialog.open , cause: " + ex.getMessage(), ex);
			}
			catch (Exception ee) {
			}
			return "";
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				new ErrorLog("error in TextDialog.open() , cause: " + e.getMessage(), e);
			}
			catch (Exception ee) {
			}
			return "";
		}
	}

	public void setClipBoard(boolean value) {
		clipboardButton.setVisible(value);
	}

	private void setDialog() {
		final GridLayout gridLayout = new GridLayout();
		_shell.setLayout(gridLayout);
		GridData gridData = new org.eclipse.swt.layout.GridData(GridData.FILL, GridData.FILL, true, true);
		//_styledText = new StyledText(_shell, SWT.V_SCROLL | SWT.BORDER | SWT.WRAP | SWT.H_SCROLL);
		_styledText = new Text(_shell, SWT.V_SCROLL | SWT.BORDER | SWT.WRAP | SWT.H_SCROLL);
		_styledText.addVerifyListener(new VerifyListener() {
			@Override public void verifyText(final VerifyEvent e) {
				//das ist CTRL-Z
			}
		});
		/*_styledText.addVerifyKeyListener(new VerifyKeyListener() {
			public void verifyKey(VerifyEvent event) {
				
				_styledText.setKeyBinding(SWT.CTRL | 'A', 10000);
				
				// check whether the current keystroke is a <CTRL>+<X>				
				boolean isCtrlX = (event.stateMask == SWT.CTRL) && (event.character == 'A' );
		System.out.println("isCtrlX: " + isCtrlX + " " + _styledText.getKeyBinding(SWT.CTRL | 'A') + " " + event.keyCode);
		    		// select one page if the previous keystroke was <CTRL>+<X> and 
				// the current keystroke is 'P'
				if (previousCtrlX && Character.toUpperCase(event.character) == 'P') {
					_styledText.invokeAction(SWT.SELECTED);
					// ignore the second key of a multi-keystroke
					event.doit = false;
				} else if (isCtrlX) {
					// ignore <CTRL>+<X> key strokes
		        		event.doit = false; 		
		    		}
				previousCtrlX = isCtrlX;
				
			}
		});*/
		_styledText.addKeyListener(new KeyAdapter() {
			@Override public void keyPressed(final KeyEvent e) {
				//System.out.println("keyCod: " + e.keyCode);
				if (e.keyCode == 122 && e.stateMask == SWT.CTRL)
					e.doit = false;
				//System.out.println("char: " + String.valueOf(e.character)+ " -> " + e.character +"keycode= " + e.keyCode + " mask= "+e.stateMask);
				if (e.keyCode == 97 && e.stateMask == SWT.CTRL) {
					try {
						_styledText.setSelection(0, _styledText.getText().length());
					}
					catch (Exception es) {
						//System.out.println(es.getMessage());
					}
				}
			}
		});
		_styledText.addModifyListener(new ModifyListener() {
			@Override public void modifyText(final ModifyEvent e) {
				if (bEdit)
					butApply.setEnabled(true);
			}
		});
		_styledText.setEditable(false);
		_styledText.setLayoutData(gridData);
		if (Options.getPropertyBoolean("JOEConstants.job.show.wizard")) {
			butShowSiteInFuture = new Button(getShell(), SWT.CHECK);
			butShowSiteInFuture.addSelectionListener(new SelectionAdapter() {
				@Override public void widgetSelected(final SelectionEvent e) {
					Options.setPropertyBoolean("JOEConstants.job.show.wizard", !butShowSiteInFuture.getSelection());
				}
			});
			butShowSiteInFuture.setLayoutData(new GridData());
			butShowSiteInFuture.setText(Messages.getString("TextDialog.butShowSiteInFuture.text"));
		}
		final Composite composite = new Composite(_shell, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false));
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.marginWidth = 0;
		gridLayout_1.marginHeight = 0;
		gridLayout_1.verticalSpacing = 0;
		gridLayout_1.horizontalSpacing = 2;
		gridLayout_1.numColumns = 5;
		composite.setLayout(gridLayout_1);
		butApply = new Button(composite, SWT.NONE);
		butApply.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));
		butApply.addSelectionListener(new SelectionAdapter() {
			@Override public void widgetSelected(final SelectionEvent e) {
				applyBoardClick = true;
				_shell.close();
			}
		});
		butApply.setEnabled(false);
		butApply.setText("Apply");
		clipboardButton = new Button(composite, SWT.NONE);
		clipboardButton.addSelectionListener(new SelectionAdapter() {
			@Override public void widgetSelected(final SelectionEvent e) {
				if (_styledText.getText().length() > 0)
					clipBoardClick = true;
				_shell.close();
			}
		});
		clipboardButton.setVisible(false);
		clipboardButton.setText("Clipboard");
		addPredefinedFunctionsLabel = new Label(composite, SWT.NONE);
		addPredefinedFunctionsLabel.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, false, false));
		addPredefinedFunctionsLabel.setVisible(false);
		addPredefinedFunctionsLabel.setText("Select predefined functions:");
		cboFunctions = new Combo(composite, SWT.NONE);
		cboFunctions.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		cboFunctions.addSelectionListener(new SelectionAdapter() {
			@Override public void widgetSelected(final SelectionEvent e) {
				if (cboFunctions.getText().length() > 0) {
					_styledText.append(Options.getProperty(scriptLanguage + cboFunctions.getText()));
					cboFunctions.setText("");
				}
			}
		});
		cboFunctions.setVisible(false);
		Button closeButton = new Button(composite, SWT.NONE);
		closeButton.setText("Close");
		closeButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			@Override public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				close();
			}
		});
	}

	public boolean isClipBoardClick() {
		return clipBoardClick;
	}

	public boolean isApplyBoardClick() {
		return applyBoardClick;
	}

	public void setVisibleApplyButton(boolean bApply) {
		butApply.setVisible(bApply);
	}

	public Shell getShell() {
		return _shell;
	}

	//location soll gespeichert werden 
	public void setBSaveWindow(boolean saveWindow) {
		bSaveWindow = saveWindow;
	}

	private void close() {
		if (butApply.getEnabled()) {
			int count = JOEMainWindow.message(_shell, Messages.getString("detailform.close"), SWT.ICON_WARNING | SWT.OK | SWT.CANCEL);
			if (count != SWT.OK) {
				return;
			}
		}
		_shell.close();
	}

	/**
	 * @return the showFunctions
	 */
	public boolean isShowFunctions() {
		return showFunctions;
	}

	/**
	 * @param showFunctions the showFunctions to set
	 */
	public void setShowFunctions(boolean showFunctions) {
		this.showFunctions = showFunctions;
		cboFunctions.setVisible(showFunctions);
		addPredefinedFunctionsLabel.setVisible(showFunctions);
	}

	/**
	 * @return the scriptLanguage
	 */
	public String getScriptLanguage() {
		return scriptLanguage;
	}

	/**
	 * @param scriptLanguage the scriptLanguage to set
	 */
	public void setScriptLanguage(String scriptLanguage_) {
		if (scriptLanguage_ != null)
			this.scriptLanguage = scriptLanguage_.toLowerCase();
		if (isShowFunctions()) {
			cboFunctions.setText("..please select");
			cboFunctions.setItems(Options.getPropertiesWithPrefix(scriptLanguage));
		}
	}

	/**
	 * 
	 * @return the butShowSiteInFuture
	 */
/*	
	public boolean getShowDialogButtonInFutureIsVisible() {
		if(butShowSiteInFuture != null && butShowSiteInFuture.isVisible())
		    return true;
		else
			return false;
	}

*/
	/**
	 * dont Show Wizzard Info
	 * @param butShowSiteInFuture the butShowSiteInFuture to set
	 */
	public void setShowWizzardInfo(boolean visible) {
		if (butShowSiteInFuture != null)
			this.butShowSiteInFuture.setVisible(visible);
	}
}