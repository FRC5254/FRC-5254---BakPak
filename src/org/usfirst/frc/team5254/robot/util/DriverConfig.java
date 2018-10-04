package org.usfirst.frc.team5254.robot.util;

public enum DriverConfig {
	
	/*
	 * A 1
	 * B 2
	 * X 3
	 * Y 4
	 * LB 5
	 * RB 6
	 * Back 7
	 * Start 8
	 * LJC 9 
	 * RJC 10
	 * 
	 * LX 0
	 * LY 1
	 * LT 2
	 * RT 3
	 * RX 4
	 * RY 5
	 */

	SAM (DriveControl.ARCADE, 1, 4, 1, 7, 2, 6, true, 0.5), //TODO update pref
	NICK (DriveControl.TANK, 1, 4, 1, 7, 2, 6, true, 0.5),// TODO rm Nick 
	KAT (DriveControl.CURVATURE, 1, 4, 1, 7, 2, 6, true, 0.5);
	
	public DriveControl dc;
	public int joystick1;
	public int Joystick2;
	public int turnButton;
	public int slowTurnButton; // TODO is it useful to have them as two separate buttons? or should it just be 1?
	public int slowDrive;//				^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	public int shiftButton;
	public boolean lowGearDefault;
	public double voltageRampingFactor;
	
	DriverConfig(DriveControl dc, int joystick1, int joystick2, int turnButton, int slowTurnButton,
			int slowDrive, int shiftButton, boolean lowGearDefault, double voltageRampingFactor) {
		this.dc = dc;
		this.joystick1 = joystick1;
		this.Joystick2 = joystick2;
		this.turnButton = turnButton;
		this.slowTurnButton = slowTurnButton;
		this.slowDrive = slowDrive;
		this.shiftButton = shiftButton;
		this.lowGearDefault = lowGearDefault;
		this.voltageRampingFactor = voltageRampingFactor;		
	}
}
