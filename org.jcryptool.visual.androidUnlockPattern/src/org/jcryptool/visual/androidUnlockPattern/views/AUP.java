package org.jcryptool.visual.androidUnlockPattern.views;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

//import org.eclipse.swt.SWT;
//import org.eclipse.swt.graphics.Color;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Label;
import org.jcryptool.core.logging.utils.LogUtil;
//import org.jcryptool.visual.androidUnlockPattern.AndroidUnlockPatternPlugin;
//import org.jcryptool.visual.androidUnlockPattern.views.AupView.ApuState;

public class AUP {

	private final static int arrayLengthStd = 10;

//	private final static Color STANDARD = Display.getCurrent().getSystemColor(
//			SWT.COLOR_WIDGET_BACKGROUND);
//	private final static Color ROT = Display.getCurrent().getSystemColor(
//			SWT.COLOR_RED);
//	private final static Color GELB = Display.getCurrent().getSystemColor(
//			SWT.COLOR_YELLOW);
//	private final static Color GRUEN = Display.getCurrent().getSystemColor(
//			SWT.COLOR_GREEN);
//	private Color lineColor = Display.getCurrent().getSystemColor(
//			SWT.COLOR_WIDGET_BACKGROUND);

//	private boolean isChangeable = false;
//	private boolean first = true;
//	private boolean cancelShowed = false;
//	private int modus;// 1=set;2=change;3=check
//	private int length = 0;
	private int tryCount = 0;
//	private int[] order = new int[arrayLengthStd];
	private ArrayList<Integer> pattern = new ArrayList<Integer>();	//the pattern
	private boolean isActive[] = new boolean[arrayLengthStd];
//	private ArrayList<Boolean> isActive = new ArrayList<Boolean>();
//	private int[] ordersaved;
//	private int[] ordertmp;
//	int[][] lines = new int[8][2];
//	int[][] points = new int[8][4];

//	private AupView visual;

	/**
	 * Initialize some variables
	 * 
	 * @param aupView
	 */
//	public AUP(AupView aupView) {
	public AUP() {
//		visual = aupView;
//		for (int i = 0; i < lines.length; i++) {
//			for (int j = 0; j < lines[i].length; j++) {
//				lines[i][j] = 0;
//			}
//
//		}
//		for (int i = 0; i < points.length; i++) {
//			for (int j = 0; j < points[i].length; j++) {
//				points[i][j] = 0;
//			}
//
//		}
	}

	/**
	 * @return the points
	 */
//	public int[][] getPoints() {
//		return this.points;
//	}

//	/**
//	 * @param points
//	 *            the points to set
//	 */
//	public void setPoints(int[][] points) {
//		this.points = points;
//	}

	/**
	 * adds the number to the array which should be checked.
	 * 
	 * @param btnNummer
	 *            number which should be added to the patter
	 */
	public void add(int btnNummer) {
		pattern.add(btnNummer);
//		if (this.length > 9) {
//			LogUtil.logError("pattern is longer then 9 -> impossible"); //$NON-NLS-1$
//		} else {
//			this.order[this.length] = btnNummer + 1;
//		}
//		this.length++;
	}

//	public void btnMainClick(int btnNummer) {
//		add(btnNummer);
////		visual.getBtnCancel().setEnabled(true);
//		if (!isValid()) {
////			setColor(ROT);
////			visual.getBtnSave().setEnabled(false);
////			visual.getBtnSave().setBackground(STANDARD);
////			visual.setStatusText(Messages.Backend_InfoTextInvalid, ApuState.ERROR);
//		} else if (!isGreatEnough()) {
////			setColor(GELB);
////			visual.setStatusText(Messages.Backend_TEXT_TO_SHORT, ApuState.WARNING);
//		} else {
////			setColor(GRUEN);
////			visual.getBtnSave().setEnabled(true);
////			visual.getBtnSave().setBackground(GRUEN);
////			visual.setStatusText(Messages.Backend_TEXT_VALID, ApuState.INFO);
//		}
//		if (length > 1) {
////			for (int[] line : lines) {
////				if (line[0] == 0 && line[1] == 0) {
////					line[0] = order[length - 2];
////					line[1] = order[length - 1];
//////					visual.getCenterbox().redraw();
////					break; // TODO rethink... is this smart?
////				}
////			}
//		}
//	}

//	/**
//	 * Resets all state information! Use with great care.
//	 */
//	public void reset() {
////			ordersaved = new int[10];
////			for (int i = 0; i < ordersaved.length; i++) {
////				ordersaved[i] = 0;
////			}
////			resetBtn();
//			resetOrder();
//			save();
////			setModus(1);
//	}

//	public void btnSaveClick() {
//		if (modus == 1) { // set
//			setPattern();
//		} else if (modus == 2) { // change
//			if (!isChangeable) {
//				if (checkPattern()) {
//					isChangeable = true;
////					visual.updateProgress();
////					visual.getBtnSave().setEnabled(false);
////					visual.getBtnSave().setBackground(STANDARD);
//				}
//			} else {
//				setPattern();
//			}
//
//		} else if (modus == 3) { // check
//			checkPattern();
//			cancel();
//		} else {
////			visual.MsgBox(Messages.Backend_PopupErrorHeading,
////					Messages.Backend_PopupErrorMessage, SWT.ICON_ERROR
////							| SWT.OK);
//		}
//	}

	/**
	 * Checks if the inputed pattern is valid. If so it is in case of the <b>first input</b> temporary saved. 
	 * In case of a <b>matching confirmation input</b> it is saved and the mode is changed to <i>check</i>.
	 * In case of a <b>not matching confirmation input</b> the input is reseted and an information message is displayed in the status text.
	 */
//	private void setPattern() {
//		if (isValid() && isGreatEnough()) {
//			if (first) {
//				first = false;
//				ordertmp = new int[arrayLengthStd];
//				for (int i = 0; i < order.length; i++) {
//					ordertmp[i] = order[i];
//				}
////				visual.getBtnSave().setText(Messages.AndroidUnlockPattern_ButtonSaveText);
////				visual.updateProgress();
////				visual.setStatusText("", null); //$NON-NLS-1$
//				cancel();
//			} else if (Arrays.equals(ordertmp, order)) {
//				saveOrder();
//				cancel();
////				setModus(3);
////				visual.setStatusText(Messages.Backend_PopupSavedMessage, ApuState.INFO);
//			} else {
//				// MsgBox unequal pattern or Error
//				btnCancelClick();
////				visual.setStatusText(Messages.Backend_PopupNotSavedMessage, ApuState.ERROR);
//			}
//		} else {
//			btnCancelClick();
////			visual.MsgBox(Messages.Backend_PopupInvalidHeading,
////					Messages.Backend_PopupInvalidMessage, SWT.ICON_ERROR
////							| SWT.OK);
//		}
//	}

	/**
	 * Checks if entered pattern equals saved one. Counts also the failed check attempts.
	 */
//	private boolean checkPattern() {
//		if (Arrays.equals(order, ordersaved)) {
////			visual.setStatusText(Messages.Backend_PopupValidMessage, ApuState.OK);
////			resetBtn();
//			resetOrder();
//			tryCount = 0;
//			return true;
//		} else {
//			tryCount++;
////			visual.setStatusText(String.format(Messages.Backend_PopupWrongMessage, tryCount), ApuState.ERROR);
////			resetBtn();
//			resetOrder();
//			
//		}
//		return false;
//	}

	/**
	 * Compares two patterns and returns true if they match. Counts also the failed check attempts.
	 */
	public boolean comparePattern(AUP comparator) {
		//TODO implement a comparison; see checkPattern() above
		return false;
	}
	
//	private void saveOrder() {
//		if (order.length == arrayLengthStd) {
////				&& ordersaved.length == arrayLengthStd) {
////			for (int i = 0; i < order.length; i++) {
////				ordersaved[i] = order[i];
////			}
//			save();
//		} else {
//			LogUtil.logError("Interal Error: order.length!=ordersaved.length!=10"); //$NON-NLS-1$
//		}
//	}
	
//	/**
//	 * Resets the user input and cleans up the Mainbutton area.
//	 */
//	public void cancel() {
////		resetBtn();
//		resetOrder();
//	}
	
	
//	/**
//	 * Simulated click to 'Cancel' button.<br>
//	 * Resets the user input, cleans up the Mainbutton area and deletes the status text + image.
//	 */
//	public void btnCancelClick() {
//		cancel();
//		//modusChanged();
//		
////		switch(modus) {
////		case 1: {
////			if (first) visual.getTextFeld().setText(Messages.Mode_Set_1); 
////			else visual.getTextFeld().setText(Messages.Mode_Set_2);
////			break; }
////		case 2: {
////			if (!isChangeable) visual.getTextFeld().setText(Messages.Mode_Change_1);
////			else if (first) visual.getTextFeld().setText(Messages.Mode_Change_2);
////			else visual.getTextFeld().setText(Messages.Mode_Set_2);
////			break; }
////		case 3: visual.getTextFeld().setText(Messages.Mode_Check_1); break;
////		}
//		
//		//reset text only when order comes from user
////		if(!silent) visual.getStatusLabel().setText("");
////		visual.setStatusText("", null); //$NON-NLS-1$
//	}

	/**
	 * Checks if a pattern can be load from a savefile and sets the applicable
	 * mode
	 */
	public void init() {

		restore();
//		if (ordersaved == null || ordersaved.length != 10 || ordersaved[0] == 0
//				|| ordersaved[1] == 0 || ordersaved[2] == 0
//				|| ordersaved[3] == 0) { // noch kein Pattern vorhanden, oder
//											// falsch gespeichert
//			ordersaved = new int[10];
//			for (int i = 0; i < ordersaved.length; i++) {
//				ordersaved[i] = 0;
//			}
//			setModus(1);
//		} else {// pattern vorhanden. Modus wird auf check gesetzt
////			setModus(3);
//		}
	}

	/**
	 * check whether the pattern is long enough (>4)
	 * 
	 * @return
	 */
	private boolean isGreatEnough() {
		if (pattern.size() < 4) {
			return false;
		}
		return true;
	}

	/**
	 * check whether the pattern is valid(without checking the length)
	 * 
	 * @return
	 */
	private boolean isValid() {
		for (int i = 0; i < isActive.length; i++) {
			isActive[i] = false;
		}

		for (int i = 0; i < pattern.size() - 1; i++) {	// the last point has no successor -> no connection to check -> ignore it

			switch (pattern.get(i)) {
			case 0:
				
				if (isActive[0] || (pattern.get(i+1) == 2 && !isActive[1])
						|| (pattern.get(i+1) == 6 && !isActive[3])
						|| (pattern.get(i+1) == 8 && !isActive[4])) {
					return false;
				}

				isActive[0] = true;
				break;
			case 1:
				if (isActive[1] || (pattern.get(i+1) == 7 && !isActive[4])) {
					return false;
				}
				isActive[1] = true;
				break;
			case 2:
				if (isActive[2] || (pattern.get(i+1) == 0 && !isActive[1])
						|| (pattern.get(i+1) == 6 && !isActive[4])
						|| (pattern.get(i+1) == 8 && !isActive[5])) {
					return false;
				}
				isActive[2] = true;
				break;
			case 3:
				if (isActive[3] || (pattern.get(i+1) == 5 && !isActive[4])) {
					return false;
				}
				isActive[3] = true;
				break;
			case 4:
				if (isActive[4]) {
					return false;
				}
				isActive[4] = true;
				break;
			case 5:
				if (isActive[5] || (pattern.get(i+1) == 3 && !isActive[4])) {
					return false;
				}
				isActive[5] = true;
				break;
			case 6:
				if (isActive[6] || (pattern.get(i+1) == 0 && !isActive[3])
						|| (pattern.get(i+1) == 2 && !isActive[4])
						|| (pattern.get(i+1) == 8 && !isActive[7])) {
					return false;
				}
				isActive[6] = true;
				break;
			case 7:
				if (isActive[7] || (pattern.get(i+1) == 1 && !isActive[4])) {
					return false;
				}
				isActive[7] = true;
				break;
			case 8:
				if (isActive[8] || (pattern.get(i+1) == 0 && !isActive[4])
						|| (pattern.get(i+1) == 2 && !isActive[5])
						|| (pattern.get(i+1) == 6 && !isActive[7])) {
					return false;
				}
				isActive[8] = true;
				break;
			}

		}

		return true;
	}

	/**
	 * Resets the GUI and progress information to the initial state of the current mode.
	 */
//	private void modusChanged() {
//		//cancel current operation
//		btnCancelClick();
//		
//		//reset progress
//		isChangeable = false;
//		first = true;
//		setColor(STANDARD);
////		visual.getBtnSave().setEnabled(false);
////		visual.getBtnSave().setBackground(STANDARD);
//		
//		switch(modus) {
//			case 1:
//			case 2: {
////				visual.getBtnSave().setText(Messages.Backend_ButtonContinueText);
//				break;
//			}
//			case 3: 
////				visual.getBtnSave().setText(Messages.Backend_ButtonCheckText);
//		}
//		
////		visual.updateProgress();
////		updateModus();
//	}

//	private void setColor(Color farbe) {
//		if (farbe != STANDARD) {
//			for (int i = 0; i < order.length; i++) {
//				if (order[i] != 0) {
//					if (farbe == GELB) {
////						Image img = AndroidUnlockPatternPlugin
////								.getImageDescriptor("icons/yellow.png").createImage(visual.getCntrBtn()[i].getDisplay()); //$NON-NLS-1$
////						visual.getCntrBtn()[order[i] - 1].setImage(img);
////						visual.getCntrBtn()[order[i] - 1].setData(
////								"icon", "icons/yellow.png"); //$NON-NLS-1$ //$NON-NLS-2$
//					} else if (farbe == ROT) {
////						Image img = AndroidUnlockPatternPlugin
////								.getImageDescriptor("icons/red.png").createImage(visual.getCntrBtn()[i].getDisplay()); //$NON-NLS-1$
////						visual.getCntrBtn()[order[i] - 1].setImage(img);
////						visual.getCntrBtn()[order[i] - 1].setData(
////								"icon", "icons/red.png"); //$NON-NLS-1$ //$NON-NLS-2$
//
//					} else if (farbe == GRUEN) {
////						Image img = AndroidUnlockPatternPlugin
////								.getImageDescriptor("icons/green.png").createImage(visual.getCntrBtn()[i].getDisplay()); //$NON-NLS-1$
////						visual.getCntrBtn()[order[i] - 1].setImage(img);
////						visual.getCntrBtn()[order[i] - 1].setData(
////								"icon", "icons/green.png"); //$NON-NLS-1$ //$NON-NLS-2$
//
//					}
//					// visual.getCntrBtn()[order[i] - 1].setBackground(farbe);
//					lineColor = farbe;
////					visual.getCenterbox().redraw();
//				}
//			}
//		} else {
////			for (Label btn : visual.getCntrBtn()) {
////				Image img = AndroidUnlockPatternPlugin
////						.getImageDescriptor("icons/black.png").createImage(btn.getDisplay()); //$NON-NLS-1$
////				btn.setImage(img);
////				btn.setData("icon", "icons/black.png"); //$NON-NLS-1$ //$NON-NLS-2$
////
////			}
//			// lineColor=farbe;
//		}
////		visual.getCenterbox().redraw();
//	}

//	private void resetBtn() {
////		for (Label btn : visual.getCntrBtn()) {
////			btn.setEnabled(true);
////		}
//		for (int[] line : lines) {
//			line[0] = 0;
//			line[1] = 0;
//		}
//		recalculateLines();
//		setColor(STANDARD);
//
//	}
//	/**
//	 * resets the set pattern
//	 */
//	private void resetOrder() {
//		order = new int[10];
//		for (int i = 0; i < order.length; i++) {
//			order[i] = 0;
//		}
//		length = 0;
//	}

	/**
	 * Resets the contained Pattern.
	 */
	public void deletePattern() {
		pattern.clear();
	}
	
//	/**
//	 * checks which Modus is active and set it in the UI also disables
//	 * non-available modes
//	 * 
//	 */
//	public void updateModus() {
////		visual.getSetPattern().setEnabled(true);
////		visual.getChangePattern().setEnabled(true);
////		visual.getCheckPattern().setEnabled(true);
//		if (getModus() == 1) { // Modus SET
//			visual.getSetPattern().setSelection(true);
//			visual.getChangePattern().setSelection(false);
//			visual.getCheckPattern().setSelection(false);
//			visual.getSetPattern().setEnabled(true);
//			visual.getChangePattern().setEnabled(false);
//			visual.getCheckPattern().setEnabled(false);
//		} else if (getModus() == 2) { // Modus change
//			visual.getSetPattern().setSelection(false);
//			visual.getChangePattern().setSelection(true);
//			visual.getCheckPattern().setSelection(false);
//			visual.getSetPattern().setEnabled(false);
//			visual.getChangePattern().setEnabled(true);
//			visual.getCheckPattern().setEnabled(true);
//		} else if (getModus() == 3) { // Modus check
//			visual.getSetPattern().setSelection(false);
//			visual.getChangePattern().setSelection(false);
//			visual.getCheckPattern().setSelection(true);
//			visual.getSetPattern().setEnabled(false);
//			visual.getChangePattern().setEnabled(true);
//			visual.getCheckPattern().setEnabled(true);
//		} else { // Fehlerfall
//			LogUtil.logError("schwerer Fehler in \"checkModus\" bitte den Entwickler kontaktieren - Please contact the developer"); //$NON-NLS-1$
//		}
//	}

	/**
	 * saves order[] in file "./Android" separated by semicolon
	 * 
	 * @return false on error else true
	 */
	public boolean save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
					"./Android"))); //$NON-NLS-1$
			for (int i = 0; i < pattern.size(); i++) {
				writer.write(pattern.get(i) + ";"); //$NON-NLS-1$
			}
			writer.close();
		} catch (IOException e) {
			LogUtil.logError("Error when saving file\n" + e.getMessage()); //$NON-NLS-1$
			return false;
		}
		return true;
	}

	/**
	 * read from file "./Android" and save in order[]
	 */
	public void restore() {
//		BufferedReader reader;
//		ordersaved = new int[10];
//		try {
//			reader = new BufferedReader(new FileReader(new File("./Android"))); //$NON-NLS-1$
//			String zeile = reader.readLine();
//			while (zeile != null) {
//				String[] values = zeile.split(";"); //$NON-NLS-1$
//				for (int i = 0; i < values.length && i < 10; i++) {
//					ordersaved[i] = Integer.parseInt(values[i]);
//				}
//				zeile = reader.readLine();
//			}
//		} catch (IOException e) {
//
//			// e.printStackTrace();
//			LogUtil.logWarning("SaveFile not found. Pattern must be saved at first."); //$NON-NLS-1$
//		}
	}

	/**
	 * @return modus
	 */
//	public int getModus() {
//		return modus;
//	}

	/**
	 * @param modus
	 *            the modus to set
	 */
//	public void setModus(int modus) {
//		this.modus = modus;
//		modusChanged();
//	}

//	public void recalculateLines() {
//		for (int i = 0; i < points.length; i++) {
//			for (int j = 0; j < points[i].length; j++) {
//				points[i][j] = 0;
//			}
//		}
//		for (int i = 0; i < lines.length; i++) {
//			if (lines[i][0] != 0 && lines[i][1] != 0) {
////				points[i][0] = getCoordinates(lines[i][0])[0];
////				points[i][1] = getCoordinates(lines[i][0])[1];
////				points[i][2] = getCoordinates(lines[i][1])[0];
////				points[i][3] = getCoordinates(lines[i][1])[1];
////				points[i][0] = visual.getCntrBtn()[lines[i][0]-1].getLocation().x + visual.getCntrBtn()[lines[i][0]-1].getSize().x / 2;
////				points[i][1] = visual.getCntrBtn()[lines[i][0]-1].getLocation().y + visual.getCntrBtn()[lines[i][0]-1].getSize().y / 2;
////				points[i][2] = visual.getCntrBtn()[lines[i][1]-1].getLocation().x + visual.getCntrBtn()[lines[i][1]-1].getSize().x / 2;
////				points[i][3] = visual.getCntrBtn()[lines[i][1]-1].getLocation().y + visual.getCntrBtn()[lines[i][1]-1].getSize().y / 2;
//			}
//		}
//	}

//	public Color getLineColor() {
//		return lineColor;
//	}
	
//	protected boolean isChangeable() {
//		return isChangeable;
//	}
//	
//	protected boolean isFirst() {
//		return first;
//	}
}
