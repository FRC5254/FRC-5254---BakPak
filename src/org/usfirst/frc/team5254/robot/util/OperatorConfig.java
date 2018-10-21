package org.usfirst.frc.team5254.robot.util;

public enum OperatorConfig {

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
	
	WREN (1, 1, 2, 3, 4, 6, 5, 8), //TODO update prefs (who else?)
	JADEN (1, 1, 2, 3, 4, 6, 5, 8), 
	SAM (1, 1, 2, 3, 4, 6, 5, 8), 
	KAT (1, 1, 3, 2, 4, 6, 5, 8);
	
	public int elevatorUpAxis;
	public int downButton;
	public int switchHeightButton;
	public int ownedScaleHeightButton;
	public int unownedScaleHeightButton;
	public int intakeButton;
	public int outtakeButton;
	public int slowOuttakeButton;
	
	
	OperatorConfig(int elevatorUpAxis, int downButton, int switchHeightButton, int ownedScaleHeightButton, int unownedScaleHeightButton,
			int intakeButton, int outtakeButton,  int slowOuttakeButton) {
		this.elevatorUpAxis = elevatorUpAxis;
		this.downButton = downButton;
		this.switchHeightButton = switchHeightButton;
		this.ownedScaleHeightButton = ownedScaleHeightButton;
		this.unownedScaleHeightButton = unownedScaleHeightButton;
		this.intakeButton = intakeButton;
		this.outtakeButton = outtakeButton;
		this.slowOuttakeButton = slowOuttakeButton;
		
	}
	
}
