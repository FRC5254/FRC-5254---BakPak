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


	COLBY (DriveControl.ARCADE, 1, 4, 1, 7, 2, 6, 5, true, 0.5),
	RORY (DriveControl.ARCADE, 1, 4, 1, 7, 2, 6, 5, true, 0.5);
	
	public DriveControl dc;
	public int joystick1;
	public int joystick2;
	public int turnButton;
	public int slowTurnButton; // TODO is it useful to have them as two separate buttons? or should it just be 1?
	public int slowDrive;//				^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	public int shiftUpButton;
	public int shiftDownButton;
	public boolean lowGearDefault; // this value does nothing <:)
	public double voltageRampingFactor;
	
	
	DriverConfig(DriveControl dc, int joystick1, int joystick2, int turnButton, int slowTurnButton,
			int slowDrive, int shiftUpButton, int shiftDownButton, boolean lowGearDefault, double voltageRampingFactor) {
		this.dc = dc;
		this.joystick1 = joystick1;
		this.joystick2 = joystick2;
		this.turnButton = turnButton;
		this.slowTurnButton = slowTurnButton;
		this.slowDrive = slowDrive;
		this.shiftUpButton = shiftUpButton;
		this.shiftDownButton = shiftDownButton;
		this.lowGearDefault = lowGearDefault;
		this.voltageRampingFactor = voltageRampingFactor;		
	}
}
