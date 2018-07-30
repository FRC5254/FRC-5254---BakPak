/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5254.robot;

/** 
 * Place to put the constant variables used throughout the code.
 */
public class RobotMap {
	
	/* Calibration constants */
	
	// Drivetrain
	public static final double DRIVETRAIN_GEAR_RATIO = (7.103);// This isnt the real ratio - its been fudged till the autos worked accurately enough
	public static final double DRIVETRAIN_WHEEL_DIAMETER = 6;
	
	public static final double Kp = 0.3;
	
	public static final double TURN_P = 0.1;
	public static final double TURN_I = 0.032;
	public static final double TURN_D = 0.3;
	
	// Elevator
	public static final int ELEVATOR_GEAR_RATIO = 1;
	public static final int ELEVATOR_AXIS_DIAMETER = 1;
	public static final int ENCODER_TICKS = 256;
	
	public static final int POP_HEIGHT = 1000;
	public static final int SWITCH_HEIGHT = 10774;
	public static final int UNOWNED_SCALE_HEIGHT = 28500;// HIGHER NUMBER
	public static final int OWNED_SCALE_HEIGHT = 22500; // LOWER NUMBER
	
	public static final double ELE_DOWN_SPEED = 0.4;// THIS IS NOT THE HOLD SPEED
	public static final double ELE_HOLD_SPEED = -0.25;// Holds between 0.21 (weak hold) and 0.48, also this number NEEDS to be negative
	
	// Intake
	// Preset power values used for auto
	public static final double AUTO_INTAKE = 1;
	public static final double AUTO_SWITCH_OUTAKE = 0.75;
	public static final double AUTO_SCALE_OUTAKE = 0.75;
	

	// Do NOT change anything after this line unless you rewire the robot and
    // update the spreadsheet!
    // Port assignments should match up with the spreadsheet here:
    // https://docs.google.com/spreadsheets/d/12xSdR6-WzwyM-yQqQNXBqkpdfatipEjR_4rZcCemgr4/edit#gid=0
	
	/* Solenoids */
	public static final int SHIFTING_PISTON = 7;
	
	/* Sensors */
	public static final int ELE_BUTTON = 4;
	
	// Gyro
	public static final int GYRO = 1;
	
	/* MotorControllers */
	public static final int DRIVETRAIN_LEFT = 2;
	public static final int DRIVETRAIN_LEFT2 = 3;
	public static final int DRIVETRAIN_RIGHT = 0;
	public static final int DRIVETRAIN_RIGHT2 = 1;

	public static final int CUBE_MECH_LEFT_FLYWHEEL = 6;
	public static final int CUBE_MECH_RIGHT_FLYWHEEL = 7;
	
	public static final int CLIMBER = 8;
	public static final int CLIMBER_2 = 9;
	
	// CAN Talon
	public static final int ELEVATOR = 6;// is 4 not on comp bot

	/* Joysticks */
	public static final int DRIVER_JOYSTICK = 0;
	public static final int OPERATOR_JOYSTICK = 1;
	
	public static final int DRIVER_THROTTLE_AXIS = 1;
	public static final int DRIVER_RIGHT_TRIGGER_AXIS = 3;
	public static final int DRIVER_TURN_AXIS = 4;
	
	public static final int OPERATOR_THROTTLE_AXIS = 1;
	public static final int OPERATOR_LEFT_TRIGGER_AXIS = 2;
	public static final int OPERATOR_RIGHT_TRIGGER_AXIS = 3;
	
}
