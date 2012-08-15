package org.jcryptool.visual.androidUnlockPattern.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import org.jcryptool.core.util.fonts.FontService;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jcryptool.visual.androidUnlockPattern.AndroidUnlockPatternPlugin;

/**
 * 
 * @author Stefan Kraus
 * 
 */
public class AndroidUnlockPattern extends ViewPart {

	public enum ApuState {
		ERROR, WARNING, INFO, OK
	}

	// public static final String ID =
	// "org.jcryptool.visual.androidUnlockPattern.views.AndroidUnlockPattern";
	// private Action action1;
	// private Action action2;
	private Composite headingBox;
	private Group centerbox;
	private Composite controlBox;
	private Group helpBox;
	private Group optionbox;
	// private Label stDescription;
	private Label[] cntrBtn = new Label[9];
	private Region regionCircle;
	private Button setPattern;
	private Button changePattern;
	private Button checkPattern;
	private Button btnSave;
	private Button btnCancel;
	private Label descText;
	private Backend logic;
	private Label instrText1;
	private Label instrText2;
	private Label instrText3;
	private CLabel statusText;
	// private Canvas canv;
	private Composite parent;

	/**
	 * The constructor.
	 */
	public AndroidUnlockPattern() {
		logic = new Backend(this);
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 * 
	 * @param parent
	 *            a swt Composite, which is the parent..
	 */
	public void createPartControl(Composite parent) {
		this.parent = parent;

		// set context help
		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(
						parent,
						AndroidUnlockPatternPlugin.PLUGIN_ID
								+ ".ContextHelpView"); //$NON-NLS-1$

		// Create the ScrolledComposite to scroll horizontally and vertically
		final ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL
				| SWT.V_SCROLL);
		// Create a child composite to hold the controls
		final Composite child = new Composite(sc, SWT.NONE);
		child.setLayout(new FormLayout());

		sc.setContent(child);
		sc.setMinSize(300, 300);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);

		headingBox = new Composite(child, SWT.NONE);
		controlBox = new Composite(child, SWT.None);
		centerbox = new Group(child, SWT.NONE);
		centerbox.setText(Messages.AndroidUnlockPattern_centerbox_text);
		optionbox = new Group(controlBox, SWT.NONE);
		optionbox.setToolTipText(Messages.AndroidUnlockPattern_optionbox_toolTipText);
		helpBox = new Group(child, SWT.NONE);
		helpBox.setToolTipText(Messages.AndroidUnlockPattern_helpBox_toolTipText);
		helpBox.setText(Messages.AndroidUnlockPattern_GroupHeadingHelp);
		setPattern = new Button(optionbox, SWT.RADIO);
		GridData gd_setPattern = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_setPattern.widthHint = 56;
		gd_setPattern.minimumHeight = 30;
		gd_setPattern.minimumWidth = 30;
		setPattern.setLayoutData(gd_setPattern);
		changePattern = new Button(optionbox, SWT.RADIO);
		checkPattern = new Button(optionbox, SWT.RADIO);

		for (int i = 0; i < cntrBtn.length; i++) {
			cntrBtn[i] = new Label(centerbox, SWT.NONE);
			cntrBtn[i].setData("nummer", i); //$NON-NLS-1$
			cntrBtn[i].setSize(40, 40); // set initial size; will be updated during initiation
		}

		statusText = new CLabel(centerbox, SWT.LEFT);
		statusText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

		setPattern.setText(Messages.AndroidUnlockPattern_ModeSetText);
		changePattern.setText(Messages.AndroidUnlockPattern_ModeChangeText);
		checkPattern.setText(Messages.AndroidUnlockPattern_ModeCheckText);
		optionbox.setText(Messages.AndroidUnlockPattern_GroupHeadingModes);
		sc.setMinSize(new Point(500, 500));

		initLayout();
		addActions();
		centerResize();

		logic.init();
		child.pack();	//update the size of the visuals child's
//		child.layout(true);
	}

	/**
	 * sets the initial Layout
	 */
	private void initLayout() {

		headingBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		headingBox.setLayout(new FormLayout());
		final FormData fd_headingBox = new FormData(-1, -1);
		fd_headingBox.left = new FormAttachment(0);
		fd_headingBox.right = new FormAttachment(100);
		fd_headingBox.top = new FormAttachment(0);
		headingBox.setLayoutData(fd_headingBox);

		// top
		controlBox.setLayout(new FormLayout());
		final FormData fd_controlBox = new FormData(-1, -1);
		fd_controlBox.bottom = new FormAttachment(centerbox, 0, SWT.BOTTOM);
		fd_controlBox.top = new FormAttachment(headingBox, 6);
		fd_controlBox.right = new FormAttachment(centerbox, -6);
		fd_controlBox.left = new FormAttachment(0);
		controlBox.setLayoutData(fd_controlBox);

		// optionbox
		GridLayout gl_optionbox = new GridLayout();
		gl_optionbox.marginWidth = 10;
		gl_optionbox.marginHeight = 10;
		gl_optionbox.makeColumnsEqualWidth = true;
		optionbox.setLayout(gl_optionbox);
		final FormData fdOb = new FormData(-1, -1);
		fdOb.left = new FormAttachment(0, 10);
		fdOb.top = new FormAttachment(0);
		optionbox.setLayoutData(fdOb);
		btnSave = new Button(controlBox, SWT.None);
		FormData fd_btnSave = new FormData();
		fd_btnSave.top = new FormAttachment(optionbox, 6);
		fd_btnSave.right = new FormAttachment(optionbox, 0, SWT.RIGHT);
		fd_btnSave.left = new FormAttachment(optionbox, 0, SWT.LEFT);
		fd_btnSave.height = 30;
		fd_btnSave.width = 80;
		btnSave.setLayoutData(fd_btnSave);
		btnSave.setText(Messages.AndroidUnlockPattern_ButtonSaveText);
		// btnCheck = new Button(bottombox, SWT.None);
		btnCancel = new Button(controlBox, SWT.NONE);
		btnCancel.setEnabled(false);
		btnCancel.setToolTipText(Messages.AndroidUnlockPattern_btnCancel_toolTipText);
		FormData fd_btnCancel = new FormData();
		fd_btnCancel.top = new FormAttachment(btnSave, 6);
		fd_btnCancel.right = new FormAttachment(optionbox, 0, SWT.RIGHT);
		fd_btnCancel.left = new FormAttachment(optionbox, 0, SWT.LEFT);
		fd_btnCancel.width = 80;
		fd_btnCancel.height = 30;
		btnCancel.setLayoutData(fd_btnCancel);
		btnCancel.setText(Messages.AndroidUnlockPattern_ButtonCancelText);

		// center
		final GridLayout clayout = new GridLayout(3, false);
		clayout.horizontalSpacing = 15;
		clayout.verticalSpacing = 15;
		centerbox.setLayout(clayout);
		final FormData fdCb = new FormData(0, 0);
		fdCb.bottom = new FormAttachment(100, -163);
		fdCb.top = new FormAttachment(headingBox, 6);
		fdCb.left = new FormAttachment(0, 156);
		fdCb.right = new FormAttachment(100, -10);
		centerbox.setLayoutData(fdCb);

		Label heading = new Label(headingBox, SWT.NONE);
		heading.setFont(FontService.getHeaderFont());
		FormData fd_heading = new FormData();
		fd_heading.top = new FormAttachment(0, 10);
		fd_heading.left = new FormAttachment(0, 10);
		heading.setLayoutData(fd_heading);
		heading.setText(Messages.AndroidUnlockPattern_Heading);
		
		helpBox.setLayout(new GridLayout(2, true));
		final FormData fd_helpBox = new FormData(180, -1);
		fd_helpBox.top = new FormAttachment(centerbox, 6);
		fd_helpBox.bottom = new FormAttachment(100, -10);
		fd_helpBox.left = new FormAttachment(0, 10);
		fd_helpBox.right = new FormAttachment(100, -10);
		helpBox.setLayoutData(fd_helpBox);

		instrText1 = new Label(helpBox, SWT.READ_ONLY | SWT.WRAP);
		instrText1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		instrText1.setAlignment(SWT.LEFT);
		instrText1.setText(Messages.Mode_Set_1);
		
		descText = new Label(helpBox, SWT.WRAP);
		descText.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false, 1, 3));
		descText.setText(Messages.AndroidUnlockPattern_HelpTextInit);
		
		instrText2 = new Label(helpBox, SWT.READ_ONLY | SWT.WRAP);
		instrText2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		instrText2.setAlignment(SWT.LEFT);
		instrText2.setText(Messages.Mode_Set_1);
		
		instrText3 = new Label(helpBox, SWT.READ_ONLY | SWT.WRAP);
		instrText3.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		instrText3.setAlignment(SWT.LEFT);
		instrText3.setText(Messages.Mode_Set_1);

	}

	// Code from dev.eclipse.org Licence: Eclipse Public License
	// http://dev.eclipse.org/viewcvs/viewvc.cgi/org.eclipse.swt.snippets/src/org/eclipse/swt/snippets/Snippet294.java?view=co
	private int[] circle(int r, int offsetX, int offsetY) {
		int[] polygon = new int[8 * r + 4];
		// x^2 + y^2 = r^2
		for (int i = 0; i < 2 * r + 1; i++) {
			int x = i - r;
			int y = (int) Math.sqrt(r * r - x * x);
			polygon[2 * i] = offsetX + x;
			polygon[2 * i + 1] = offsetY + y;
			polygon[8 * r - 2 * i - 2] = offsetX + x;
			polygon[8 * r - 2 * i - 1] = offsetY - y;
		}
		return polygon;
	}

	/**
	 * add listener to buttons etc
	 * 
	 */
	private void addActions() {
		centerbox.addListener(SWT.Resize, new Listener() {

			@Override
			public void handleEvent(Event event) {
				centerResize();
				centerbox.redraw();
			}
		});

		// centrcalBtns
		for (int i = 0; i < cntrBtn.length; i++) {
//			cntrBtn[i].addSelectionListener(new SelectionListener() {
//				@Override
//				public void widgetSelected(SelectionEvent e) {
//					if (e.widget
//							.getData("icon").toString().regionMatches(false, 6, "b", 0, 1)) { //$NON-NLS-1$ //$NON-NLS-2$
//						// to get here the button needs to be unclicked
//						// (in this case e.widget.getData("icon").toString() is
//						// "icons/black.png")
//						// for performance reasons only the 7. char of the
//						// string is checked
//						final int btnNummer = (Integer) e.widget
//								.getData("nummer"); //$NON-NLS-1$
//						logic.btnMainClick(btnNummer);
//					}
//				}
//
//				@Override
//				public void widgetDefaultSelected(SelectionEvent e) {
//
//				}
//			});
			cntrBtn[i].addMouseListener(new MouseListener() {

				@Override
				public void mouseDoubleClick(MouseEvent e) {
					
				}

				@Override
				public void mouseDown(MouseEvent e) {
					if (e.widget.getData("icon").toString().regionMatches(false, 6, "b", 0, 1)) { //$NON-NLS-1$ //$NON-NLS-2$
						final int btnNummer = (Integer) e.widget.getData("nummer"); //$NON-NLS-1$
						logic.btnMainClick(btnNummer);
					}
					
				}

				@Override
				public void mouseUp(MouseEvent e) {
					
				}
				
			});
		}

		btnSave.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				logic.btnSaveClick();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		btnCancel.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				btnCancel.setEnabled(false);
				btnSave.setEnabled(false);
				logic.btnCancelClick();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		// btnCheck.addSelectionListener(new SelectionListener() {
		//
		// @Override
		// public void widgetSelected(SelectionEvent e) {
		// logic.btnCheckClick();
		// }
		//
		// @Override
		// public void widgetDefaultSelected(SelectionEvent e) {
		//
		//
		// }
		// });
		centerbox.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				drawLines(e);
			}

		});

		setPattern.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				logic.setModus(1);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

		});
		changePattern.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				logic.setModus(2);
				setStatusText("", null); //$NON-NLS-1$
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

		});
		checkPattern.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				logic.setModus(3);
				setStatusText("", null); //$NON-NLS-1$
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

		});

	}

	protected void drawLines(PaintEvent e) {
		centerResize();
		e.gc.setForeground(logic.getLineColor());
		e.gc.setLineWidth(10);
		for (int[] point : logic.getPoints()) {
			e.gc.drawLine(point[0], point[1], point[2], point[3]);
		}

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		parent.setFocus();
	}

	public int MsgBox(String header, String msg, int options) {
		final MessageBox mb = new MessageBox(Display.getDefault()
				.getActiveShell(), options);
		mb.setText(header);
		mb.setMessage(msg);
		return mb.open();
	}

	/**
	 * sets Text into the standard Textfield/Label
	 */
//	protected void setText(String string) {
//		instrText1.setText(string);
//	}

	public void setBtnSaveText(String text) {
		btnSave.setText(text);
	}

	/**
	 * @return the setPattern
	 */
	public Button getSetPattern() {
		return setPattern;
	}

	/**
	 * @param setPattern
	 *            the setPattern to set
	 */
	public void setSetPattern(Button setPattern) {
		this.setPattern = setPattern;
	}

	/**
	 * @return the changePattern
	 */
	public Button getChangePattern() {
		return changePattern;
	}

	/**
	 * @param changePattern
	 *            the changePattern to set
	 */
	public void setChangePattern(Button changePattern) {
		this.changePattern = changePattern;
	}

	/**
	 * @return the checkPattern
	 */
	public Button getCheckPattern() {
		return checkPattern;
	}

	/**
	 * @param checkPattern
	 *            the checkPattern to set
	 */
	public void setCheckPattern(Button checkPattern) {
		this.checkPattern = checkPattern;
	}

	/**
	 * @return the cntrBtn
	 */
	public Label[] getCntrBtn() {
		return cntrBtn;
	}

	/**
	 * @return the btnSave
	 */
	public Button getBtnSave() {
		return btnSave;
	}

	/**
	 * @return the btnCancel
	 */
	public Button getBtnCancel() {
		return btnCancel;
	}

	//
	// /**
	// * @return the btnCheck
	// */
	// public Button getBtnCheck() {
	// return btnCheck;
	// }

//	protected Label getTextFeld() {
//		return instrText1;
//	}

	/**
	 * @return the centerbox
	 */
	public Group getCenterbox() {
		return centerbox;
	}

	/**
	 * @param centerbox
	 *            the centerbox to set
	 */
	// public void setCenterbox(Group centerbox) {
	// this.centerbox = centerbox;
	// }

	public void centerResize() {
		// centerButtons
		GridLayout layout = (GridLayout) centerbox.getLayout();
		int size = Math.min(centerbox.getClientArea().height
				- layout.marginHeight * 2 - layout.horizontalSpacing * 3
				- statusText.getClientArea().height,
				centerbox.getClientArea().width - layout.marginWidth * 2
						- layout.verticalSpacing * 2);
		if (size < 0)
			return; // Layout not yet initialized
		size = size / 3; // 3x3 centrcalBtns
		logic.recalculateLines();
		for (int i = 0; i < cntrBtn.length; i++) {
			if (cntrBtn[i].getData("icon") != null) { //$NON-NLS-1$
				String tmpStr = cntrBtn[i].getData("icon").toString(); //$NON-NLS-1$
				ImageData tmp = AndroidUnlockPatternPlugin
						.getImageDescriptor(tmpStr).getImageData()
						.scaledTo(size, size);
				cntrBtn[i].setImage(new Image(cntrBtn[i].getDisplay(), tmp));
			}
			regionCircle = new Region();
			regionCircle.add(circle(size / 2, cntrBtn[i].getBounds().width / 2,
					cntrBtn[i].getBounds().height / 2));

			cntrBtn[i].setRegion(regionCircle);
//			cntrBtn[i].setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			cntrBtn[i].setLayoutData(new GridData(size, size));
		}
	}

	public void Reset() {
		logic.btnResetClick();
	}

	protected void setStatusText(String message, ApuState state) {
		if(state == null) {
			statusText.setImage(null);
			statusText.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		} else {
		switch (state) {
			case ERROR:
				statusText.setImage(AndroidUnlockPatternPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "/icons/full/obj16/error_tsk.gif").createImage());
				statusText.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
				break;
			case WARNING:
				statusText.setImage(AndroidUnlockPatternPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "/icons/full/obj16/warn_tsk.gif").createImage());
				statusText.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
				break;
			case INFO:
				statusText.setImage(AndroidUnlockPatternPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "/icons/full/obj16/info_tsk.gif").createImage());
				statusText.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				break;
			case OK:
				statusText.setImage(AndroidUnlockPatternPlugin.getImageDescriptor("/icons/ok_st_obj.gif").createImage());
				statusText.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
				break;
			default:
				statusText.setImage(null);
			}
		}
		statusText.setText(message);
		//to avoid control from growing and to automatically lay out do not call pack(true) here
	}

	
	/**
	 * Updates the user progress information in the description box.
	 */
	protected void updateProgress() {
		switch(logic.getModus()) {
			case 1: {	// set				
				if (logic.isFirst()) {
						instrText1.setEnabled(true); 
						instrText2.setEnabled(false);
				} else {
					instrText1.setEnabled(false); 
					instrText2.setEnabled(true);
				}
				
				instrText1.setText(String.format(Messages.AndroidUnlockPattern_Step, 1, Messages.Mode_Set_1));
				instrText2.setText(String.format(Messages.AndroidUnlockPattern_Step, 2, Messages.Mode_Set_2));
				instrText3.setText("");				
				break;
			}
			case 2: {	// change
				if (!logic.isChangeable()) {
					instrText1.setEnabled(true); 
					instrText2.setEnabled(false);
					instrText3.setEnabled(false);
				} else if (logic.isFirst()) {
					instrText1.setEnabled(false); 
					instrText2.setEnabled(true);
					instrText3.setEnabled(false);
				} else {
					instrText1.setEnabled(false); 
					instrText2.setEnabled(false);
					instrText3.setEnabled(true);
				}
				
				instrText1.setText(String.format(Messages.AndroidUnlockPattern_Step, 1, Messages.Mode_Change_1));
				instrText2.setText(String.format(Messages.AndroidUnlockPattern_Step, 2, Messages.Mode_Change_2));
				instrText3.setText(String.format(Messages.AndroidUnlockPattern_Step, 3, Messages.Mode_Set_2));
				break;
			}
			case 3: {	// check
				instrText1.setEnabled(true);
				instrText1.setText(String.format(Messages.AndroidUnlockPattern_Step, 1, Messages.Mode_Check_1));
				instrText2.setText("");
				instrText3.setText("");
				break;
			}
		}
		helpBox.layout(true);
	}
}