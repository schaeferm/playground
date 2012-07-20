package org.jcryptool.visual.androidUnlockPattern.views;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.jcryptool.core.logging.utils.LogUtil;
import org.jcryptool.visual.androidUnlockPattern.Activator;

/**
 * 
 * @author Stefan Kraus <stefan.kraus05@gmail.com>
 * 
 * 
 */
public class Backend {

	private final static int arrayLengthStd = 10;

	private final static Color STANDARD = Display.getCurrent().getSystemColor(
			SWT.COLOR_WIDGET_BACKGROUND);
	private final static Color ROT = Display.getCurrent().getSystemColor(
			SWT.COLOR_RED);
	private final static Color GELB = Display.getCurrent().getSystemColor(
			SWT.COLOR_YELLOW);
	private final static Color GRUEN = Display.getCurrent().getSystemColor(
			SWT.COLOR_GREEN);
	private Color lineColor = Display.getCurrent().getSystemColor(
			SWT.COLOR_WIDGET_BACKGROUND);

	private boolean isActive[] = new boolean[arrayLengthStd];
	private boolean isChangable = false;
	private boolean first = true;
//	private boolean cancelShowed = false;
	private int modus;// 1=set;2=change;3=check
	private int length = 0;
	private int tryCount = 0;
	private int[] order = new int[arrayLengthStd];
	private int[] ordersaved;
	private int[] ordertmp;
	int[][] lines = new int[8][2];
	int[][] points = new int[8][4];

	private AndroidUnlockPattern visual;

	/**
	 * Initialize some variables
	 * 
	 * @param androidUnlockPattern
	 */
	public Backend(AndroidUnlockPattern androidUnlockPattern) {
		visual = androidUnlockPattern;
		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < lines[i].length; j++) {
				lines[i][j] = 0;
			}

		}
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points[i].length; j++) {
				points[i][j] = 0;
			}

		}
	}

	/**
	 * @return the points
	 */
	public int[][] getPoints() {
		return this.points;
	}

	/**
	 * @param points
	 *            the points to set
	 */
	public void setPoints(int[][] points) {
		this.points = points;
	}

	/**
	 * adds the number to the array which should be checked.
	 * 
	 * @param btnNummer
	 *            number which should be added to the patter
	 */
	public void add(int btnNummer) {
		if (this.length > 9) {
			LogUtil.logError("pattern is longer then 9 -> impossible"); //$NON-NLS-1$
		} else {
			this.order[this.length] = btnNummer + 1;
		}
		this.length++;
	}

	public void btnMainClick(int btnNummer) {
		add(btnNummer);
		visual.getBtnCancel().setEnabled(true);
		// if (modus == 1) {
		// // if (first) {
		// // if (!isValid()) {
		// // setColor(ROT);
		// // } else if (!isGreatEnough()) {
		// // setColor(GELB);
		// // visual.setText(Messages.Backend_TEXT_TO_SHORT);
		// // } else {
		// // setColor(GRUEN);
		// // visual.getBtnSave().setEnabled(true);
		// // visual.setText(Messages.Backend_TEXT_VALID);
		// // }
		// // if (length > 1) {
		// // for (int[] line : lines) {
		// // if (line[0] == 0 && line[1] == 0) {
		// // line[0] = order[length - 2];
		// // line[1] = order[length - 1];
		// // visual.getCenterbox().redraw();
		// // break; // TODO rethink... is this smart?
		// // }
		// // }
		// // }
		// // }
		// if (!isValid()) {
		// setColor(ROT);
		// } else if (!isGreatEnough()) {
		// setColor(GELB);
		// visual.setText(Messages.Backend_TEXT_TO_SHORT);
		// } else {
		// setColor(GRUEN);
		// visual.getBtnSave().setEnabled(true);
		// visual.setText(Messages.Backend_TEXT_VALID);
		// }
		// if (length > 1) {
		// for (int[] line : lines) {
		// if (line[0] == 0 && line[1] == 0) {
		// line[0] = order[length - 2];
		// line[1] = order[length - 1];
		// visual.getCenterbox().redraw();
		// break; // TODO rethink... is this smart?
		// }
		// }
		// }
		// // } else if (modus == 2) {
		// // if (!isValid()) {
		// // if (isChangable && first) {
		// // setColor(ROT);
		// // }
		// // } else if (!isGreatEnough()) {
		// // if (isChangable && first) {
		// // setColor(GELB);
		// // }
		// // } else {
		// // visual.getBtnSave().setEnabled(true);
		// // if (isChangable && first) {
		// // setColor(GRUEN);
		// // }
		// // }
		// // if (isChangable && first) {
		// // if (length > 1) {
		// // for (int[] line : lines) {
		// // if (line[0] == 0 && line[1] == 0) {
		// // line[0] = order[length - 2];
		// // line[1] = order[length - 1];
		// // visual.getCenterbox().redraw();
		// // break; // TODO rethink... is this smart?
		// // }
		// // }
		// // }
		// //
		// // }
		// } else if (modus == 2) {
		// if (!isValid()) {
		// setColor(ROT);
		// } else if (!isGreatEnough()) {
		// setColor(GELB);
		// } else {
		// visual.getBtnSave().setEnabled(true);
		// setColor(GRUEN);
		// }
		// if (length > 1) {
		// for (int[] line : lines) {
		// if (line[0] == 0 && line[1] == 0) {
		// line[0] = order[length - 2];
		// line[1] = order[length - 1];
		// visual.getCenterbox().redraw();
		// break; // TODO rethink... is this smart?
		// }
		// }
		// }
		// } else if (modus == 3) {
		// visual.getBtnSave().setEnabled(true);
		// }

		if (!isValid()) {
			setColor(ROT);
			visual.getBtnSave().setEnabled(false);
//			visual.setText(Messages.Backend_InfoTextInvalid);
			visual.getStatusText().setText(Messages.Backend_InfoTextInvalid);
		} else if (!isGreatEnough()) {
			setColor(GELB);
//			visual.setText(Messages.Backend_TEXT_TO_SHORT);
			visual.getStatusText().setText(Messages.Backend_TEXT_TO_SHORT);
		} else {
			setColor(GRUEN);
			visual.getBtnSave().setEnabled(true);
//			visual.setText(Messages.Backend_TEXT_VALID);
			visual.getStatusText().setText(Messages.Backend_TEXT_VALID);
		}
		if (length > 1) {
			for (int[] line : lines) {
				if (line[0] == 0 && line[1] == 0) {
					line[0] = order[length - 2];
					line[1] = order[length - 1];
					visual.getCenterbox().redraw();
					break; // TODO rethink... is this smart?
				}
			}
		}
	}

	public void btnHelpClick() {
		// TODO Auto-generated method stub

	}

	public void btnResetClick() {
		int tmp = visual.MsgBox(Messages.Backend_PopupResetHeading,
				Messages.Backend_PopupResetMessage, SWT.YES | SWT.NO
						| SWT.ICON_WARNING);
		if (tmp == SWT.YES) {
			ordersaved = new int[10];
			for (int i = 0; i < ordersaved.length; i++) {
				ordersaved[i] = 0;
			}
			visual.getStatusText().setText("");
			resetBtn();
			resetOrder();
			save();
			setModus(1);

		} else {

			// nichts oder cancel? -> nichts
		}

	}

	public void btnSaveClick() {
		if (modus == 1) { // set
			setPattern();
		} else if (modus == 2) { // change
			if (!isChangable) {
				if (checkPattern()) {
					isChangable = true;
					visual.getTextFeld().setText(Messages.Backend_InfoTextNew);
					visual.getBtnSave().setEnabled(false);
				}
			} else {
				setPattern();
			}

		} else if (modus == 3) { // check
			checkPattern();
			modusChanged();
		} else {
			visual.MsgBox(Messages.Backend_PopupErrorHeading,
					Messages.Backend_PopupErrorMessage, SWT.ICON_ERROR
							| SWT.OK);
		}
	}

	private void setPattern() {
		if (isValid() && isGreatEnough()) {
			if (first) {
				first = false;
				ordertmp = new int[arrayLengthStd];
				for (int i = 0; i < order.length; i++) {
					ordertmp[i] = order[i];
				}
				visual.getBtnSave().setText(
						Messages.AndroidUnlockPattern_ButtonSaveText);
				visual.getTextFeld().setText(Messages.Backend_TEXT_SET_SECOND);
				visual.getStatusText().setText("");
				resetBtn();
				resetOrder();
			} else if (Arrays.equals(ordertmp, order)) {
				saveOrder();

				//no error popup -> do not display
//				visual.MsgBox(Messages.Backend_PopupSavedHeading,
//						Messages.Backend_PopupSavedMessage,
//						SWT.ICON_INFORMATION | SWT.OK);
				//TODO Nutzer mittels im Beschreibungsbereich darüber informieren
				visual.getStatusText().setText(Messages.Backend_PopupSavedMessage);
				resetBtn();
				resetOrder();
				setModus(3);
			} else {
				// MsgBox unequal pattern or Error
				btnCancelClick();
//				visual.MsgBox(Messages.Backend_PopupNotSavedHeading,
//						Messages.Backend_PopupNotSavedMessage,
//						SWT.ICON_ERROR | SWT.OK);
				visual.getStatusText().setText(Messages.Backend_PopupNotSavedMessage);
			}
		} else {
//			first = true;
//			isChangable = false;
//			resetBtn();
//			resetOrder();
//			visual.MsgBox(Messages.Backend_PopupInvalidHeading,
//					Messages.Backend_PopupInvalidMessage, SWT.ICON_INFORMATION
//							| SWT.OK);
//			modusChanged();
			btnCancelClick();
			visual.MsgBox(Messages.Backend_PopupInvalidHeading,
					Messages.Backend_PopupInvalidMessage, SWT.ICON_ERROR
							| SWT.OK);
		}
	}

	/**
	 * check ob eigegebenes pattern= gespeichertem, nur bestimmte oft.
	 */
	private boolean checkPattern() {
		if (Arrays.equals(order, ordersaved)) {
			//no error popup -> do not display
//			visual.MsgBox(Messages.Backend_PopupValidHeading,
//					Messages.Backend_PopupValidMessage, SWT.ICON_INFORMATION
//							| SWT.OK);
			//TODO inform user
			visual.getStatusText().setText(Messages.Backend_PopupValidMessage);
			resetBtn();
			resetOrder();
			tryCount = 0;
			return true;
		} else {
			tryCount++;
//			visual.MsgBox(
//					Messages.Backend_PopupWrongHeading,
//					String.format(Messages.Backend_PopupWrongMessage, tryCount),
//					SWT.ICON_ERROR | SWT.OK);
			visual.getStatusText().setText(String.format(Messages.Backend_PopupWrongMessage, tryCount));
			resetBtn();
			resetOrder();
			
		}
		return false;
	}

	private void saveOrder() {
		if (order.length == arrayLengthStd
				&& ordersaved.length == arrayLengthStd) {
			for (int i = 0; i < order.length; i++) {
				ordersaved[i] = order[i];
			}
			save();
		} else {
			LogUtil.logError("Interal Error: order.length!=ordersaved.length!=10"); //$NON-NLS-1$
		}

	}

//	public void btnCancelClick() {
//		btnCancelClick(false);
//	}
	
//	public void btnCancelClick(boolean silent) {
	public void btnCancelClick() {
		resetBtn();
		resetOrder();
		//modusChanged();
		
		switch(modus) {
		case 1: {
			if (first) visual.getTextFeld().setText(Messages.TEXT_SET_INITIAL); 
			else visual.getTextFeld().setText(Messages.Backend_TEXT_SET_SECOND);
			break; }
		case 2: {
			if (!isChangable) visual.getTextFeld().setText(Messages.Backend_TEXT_CHANGE_INITIAL);
			else if (first) visual.getTextFeld().setText(Messages.Backend_InfoTextNew);
			else visual.getTextFeld().setText(Messages.Backend_TEXT_SET_SECOND);
			break; }
		case 3: visual.getTextFeld().setText(Messages.Backend_TEXT_CHECK_INITIAL); break;
		}
		
		//reset text only when order comes from user
//		if(!silent) visual.getStatusLabel().setText("");
		visual.getStatusText().setText("");

//		if (!cancelShowed && !silent) {
//			cancelShowed = true;
//			visual.MsgBox(Messages.Backend_PopupCancelHeading,
//					Messages.Backend_PopupCancelMessage, SWT.ICON_INFORMATION
//							| SWT.OK);
//		}
	}

	/*
	 * public void btnCheckClick() { if (modus == 1) { //PopUp? } else if (modus
	 * == 2) { // btn save aktivieren } else if (modus == 3) { // hier soll
	 * nicht passieren } }
	 */

	/**
	 * Checks if a pattern can be load from a savefile and sets the applicable
	 * mode
	 */
	public void init() {

		restore();
		if (ordersaved == null || ordersaved.length != 10 || ordersaved[0] == 0
				|| ordersaved[1] == 0 || ordersaved[2] == 0
				|| ordersaved[3] == 0) { // noch kein Pattern vorhanden, oder
											// falsch gespeichert
			ordersaved = new int[10];
			for (int i = 0; i < ordersaved.length; i++) {
				ordersaved[i] = 0;
			}
			setModus(1);
		} else {// pattern vorhanden. Modus wird auf check gesetzt
			setModus(3);
		}
	}

	/**
	 * check whether the pattern is long enough (>4)
	 * 
	 * @return
	 */
	private boolean isGreatEnough() {
		if (length < 4 || length > 9) {
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
		boolean isEnd = false;

		if (order.length != 10) {
			try {
				throw new Exception("Internal Error: Wrong length of Array"); //$NON-NLS-1$
			} catch (Exception e) {
				LogUtil.logError(e);
			}
		}
		if (!(order[9] == 0)) {
			LogUtil.logError("Internal Error:last cypher != 0"); //$NON-NLS-1$
			return false;
		}

		for (int i = 0; i < order.length - 1; i++) {

			switch (order[i]) {
			case 0:
				isEnd = true;
				/*
				 * // Startpunkt==Endpunkt if (i > 0 && order[i - 1] ==
				 * order[0]) { return false; } // weniger als 4 Punkte else if
				 * (i < 4) { return false; }
				 */
				break;
			case 1:

				if (isEnd || isActive[1] || (order[i + 1] == 3 && !isActive[2])
						|| (order[i + 1] == 7 && !isActive[4])
						|| (order[i + 1] == 9 && !isActive[5])) {
					return false;
				}

				isActive[1] = true;
				break;
			case 2:
				if (isEnd || isActive[2] || (order[i + 1] == 8 && !isActive[5])) {
					return false;
				}
				isActive[2] = true;
				break;
			case 3:
				if (isEnd || isActive[3] || (order[i + 1] == 1 && !isActive[2])
						|| (order[i + 1] == 7 && !isActive[5])
						|| (order[i + 1] == 9 && !isActive[6])) {
					return false;
				}
				isActive[3] = true;
				break;
			case 4:
				if (isEnd || isActive[4] || (order[i + 1] == 6 && !isActive[5])) {
					return false;
				}
				isActive[4] = true;
				break;
			case 5:
				if (isEnd || isActive[5]) {
					return false;
				}
				isActive[5] = true;
				break;
			case 6:
				if (isEnd || isActive[6] || (order[i + 1] == 4 && !isActive[5])) {
					return false;
				}
				isActive[6] = true;
				break;
			case 7:
				if (isEnd || isActive[7] || (order[i + 1] == 1 && !isActive[4])
						|| (order[i + 1] == 3 && !isActive[5])
						|| (order[i + 1] == 9 && !isActive[8])) {
					return false;
				}
				isActive[7] = true;
				break;
			case 8:
				if (isEnd || isActive[8] || (order[i + 1] == 2 && !isActive[5])) {
					return false;
				}
				isActive[8] = true;
				break;
			case 9:
				if (isEnd || isActive[9] || (order[i + 1] == 1 && !isActive[5])
						|| (order[i + 1] == 3 && !isActive[6])
						|| (order[i + 1] == 7 && !isActive[8])) {
					return false;
				}
				isActive[9] = true;
				break;
			}

		}

		return true;
	}

	private void modusChanged() {
		if (modus == 1) {
			visual.getTextFeld().setText(Messages.TEXT_SET_INITIAL);
			setColor(STANDARD);
			visual.getBtnSave().setText(Messages.Backend_ButtonContinueText);
			visual.getBtnSave().setEnabled(false);

		} else if (modus == 2) {
			visual.getTextFeld().setText(Messages.Backend_TEXT_CHANGE_INITIAL);
			setColor(STANDARD);
			visual.getBtnSave().setText(Messages.Backend_ButtonContinueText);
			visual.getBtnSave().setEnabled(false);

		} else if (modus == 3) {
			visual.getTextFeld().setText(Messages.Backend_TEXT_CHECK_INITIAL);
			setColor(STANDARD);
			visual.getBtnSave().setText(Messages.Backend_ButtonCheckText);
			visual.getBtnSave().setEnabled(false);
		} else {
			// FEhler
		}
		isChangable = false;
		first = true;
		// resetBtn();
		resetOrder();
		updateModus();
	}

	private void setColor(Color farbe) {
		if (farbe != STANDARD) {
			for (int i = 0; i < order.length; i++) {
				if (order[i] != 0) {
					if (farbe == GELB) {
						Image img = Activator
								.getImageDescriptor("icons/yellow.png").createImage(visual.getCntrBtn()[i].getDisplay()); //$NON-NLS-1$
						visual.getCntrBtn()[order[i] - 1].setImage(img);
						visual.getCntrBtn()[order[i] - 1].setData(
								"icon", "icons/yellow.png"); //$NON-NLS-1$ //$NON-NLS-2$
					} else if (farbe == ROT) {
						Image img = Activator
								.getImageDescriptor("icons/red.png").createImage(visual.getCntrBtn()[i].getDisplay()); //$NON-NLS-1$
						visual.getCntrBtn()[order[i] - 1].setImage(img);
						visual.getCntrBtn()[order[i] - 1].setData(
								"icon", "icons/red.png"); //$NON-NLS-1$ //$NON-NLS-2$

					} else if (farbe == GRUEN) {
						Image img = Activator
								.getImageDescriptor("icons/green.png").createImage(visual.getCntrBtn()[i].getDisplay()); //$NON-NLS-1$
						visual.getCntrBtn()[order[i] - 1].setImage(img);
						visual.getCntrBtn()[order[i] - 1].setData(
								"icon", "icons/green.png"); //$NON-NLS-1$ //$NON-NLS-2$

					}
					// visual.getCntrBtn()[order[i] - 1].setBackground(farbe);
					lineColor = farbe;
					visual.getCenterbox().redraw();
				}
			}
		} else {
			for (Button btn : visual.getCntrBtn()) {
				Image img = Activator
						.getImageDescriptor("icons/black.png").createImage(btn.getDisplay()); //$NON-NLS-1$
				btn.setData("icon", "icons/black.png"); //$NON-NLS-1$ //$NON-NLS-2$
				btn.setImage(img);

			}
			// lineColor=farbe;
		}
		visual.getCenterbox().redraw();
	}

	private void resetBtn() {
		for (Button btn : visual.getCntrBtn()) {
			btn.setEnabled(true);
		}
		for (int[] line : lines) {
			line[0] = 0;
			line[1] = 0;
		}
		recalculateLines();
		setColor(STANDARD);

	}

	private void resetOrder() {
		order = new int[10];
		for (int i = 0; i < order.length; i++) {
			order[i] = 0;
		}
		length = 0;

	}

	/**
	 * checks which Modus is active and set it in the UI also disables
	 * non-available modes
	 * 
	 */
	public void updateModus() {
		visual.getSetPattern().setEnabled(true);
		visual.getChangePattern().setEnabled(true);
		visual.getCheckPattern().setEnabled(true);
		if (getModus() == 1) { // Modus SET
			visual.getSetPattern().setSelection(true);
			visual.getChangePattern().setSelection(false);
			visual.getCheckPattern().setSelection(false);
			visual.getSetPattern().setEnabled(true);
			visual.getChangePattern().setEnabled(false);
			visual.getCheckPattern().setEnabled(false);
		} else if (getModus() == 2) { // Modus change
			visual.getSetPattern().setSelection(false);
			visual.getChangePattern().setSelection(true);
			visual.getCheckPattern().setSelection(false);
			visual.getSetPattern().setEnabled(false);
			visual.getChangePattern().setEnabled(true);
			visual.getCheckPattern().setEnabled(true);
		} else if (getModus() == 3) { // Modus check
			visual.getSetPattern().setSelection(false);
			visual.getChangePattern().setSelection(false);
			visual.getCheckPattern().setSelection(true);
			visual.getSetPattern().setEnabled(false);
			visual.getChangePattern().setEnabled(true);
			visual.getCheckPattern().setEnabled(true);
		} else { // Fehlerfall
			LogUtil.logError("schwerer Fehler in \"checkModus\" bitte den Entwickler kontaktieren - Please contact the developer"); //$NON-NLS-1$
		}
	}

	/**
	 * saves order[] in file "./Android" separated by semicolon
	 * 
	 * @return false on error else true
	 */
	public boolean save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
					"./Android"))); //$NON-NLS-1$
			for (int i = 0; i < order.length; i++) {
				writer.write(order[i] + ";"); //$NON-NLS-1$
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
		BufferedReader reader;
		ordersaved = new int[10];
		try {
			reader = new BufferedReader(new FileReader(new File("./Android"))); //$NON-NLS-1$
			String zeile = reader.readLine();
			while (zeile != null) {
				String[] values = zeile.split(";"); //$NON-NLS-1$
				for (int i = 0; i < values.length && i < 10; i++) {
					ordersaved[i] = Integer.parseInt(values[i]);
				}
				zeile = reader.readLine();
			}
		} catch (IOException e) {

			// e.printStackTrace();
			LogUtil.logWarning("SaveFile not found. Pattern must be saved at first."); //$NON-NLS-1$
		}
	}

	/**
	 * @return modus
	 */
	public int getModus() {
		return modus;
	}

	/**
	 * @param modus
	 *            the modus to set
	 */
	public void setModus(int modus) {
		this.modus = modus;
		modusChanged();
	}

//	public void centerResize() {
//		visual.centerResize();
//
//	}

	public void recalculateLines() {
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points[i].length; j++) {
				points[i][j] = 0;
			}
		}
		for (int i = 0; i < lines.length; i++) {
			if (lines[i][0] != 0 && lines[i][1] != 0) {
				points[i][0] = getCoordinates(lines[i][0])[0];
				points[i][1] = getCoordinates(lines[i][0])[1];
				points[i][2] = getCoordinates(lines[i][1])[0];
				points[i][3] = getCoordinates(lines[i][1])[1];
			}
		}
	}

	private int[] getCoordinates(int btn) {
		int xy[] = new int[2];
		if (btn % 3 == 1) {
			xy[0] = visual.getCntrBtn()[0].getSize().x / 2;
		} else if (btn % 3 == 2) {
			xy[0] = visual.getCenterbox().getSize().x / 2;
		} else if (btn % 3 == 0) {
			xy[0] = visual.getCenterbox().getSize().x
					- visual.getCntrBtn()[0].getSize().x / 2;
		}

		if (btn < 4 && btn > 0) {
			xy[1] = visual.getCntrBtn()[0].getSize().y / 2;
		} else if (btn < 7 && btn > 3) {
			xy[1] = visual.getCenterbox().getSize().y / 2;
		} else if (btn < 10 && btn > 6) {
			xy[1] = visual.getCenterbox().getSize().y
					- visual.getCntrBtn()[0].getSize().y / 2;
		}

		return xy;
	}

	public Color getLineColor() {
		return lineColor;
	}
}
