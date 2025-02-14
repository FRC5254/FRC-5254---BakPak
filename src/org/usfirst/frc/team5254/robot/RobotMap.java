package org.usfirst.frc.team5254.robot;

public class RobotMap {

	// Solenoids
	public static final int CLIMBER_SLIDER_PISTON_IN = 2;
	public static final int CLIMBER_SLIDER_PISTON_OUT = 3;
	public static final int NO_FIRE_CROSSBOW = 4;
	public static final int FIRE_CROSSBOW = 5;
	public static final int SHIFTING_PISTON = 1;// was 7  
	

	// Sensors
	public static final int ELE_BUTTON = 4;

	// Gyro
	public static final int GYRO = 1;
	
	// MotorControllers
	public static final int CUBE_MECH_LEFT_FLYWHEEL = 6;
	public static final int CUBE_MECH_RIGHT_FLYWHEEL = 7;
	public static final int CLIMBER = 8;
	public static final int CLIMBER_2 = 9;
	
	// CAN Talon
	public static final int ELEVATOR = 6;// is 4 not on comp bot
	public static final int DRIVETRAIN_LEFT = 2;
	public static final int DRIVETRAIN_LEFT2 = 10;
	public static final int DRIVETRAIN_RIGHT = 9;
	public static final int DRIVETRAIN_RIGHT2 = 12;

	// Joysticks
	public static final int DRIVER_JOYSTICK = 0;
	public static final int OPERATOR_JOYSTICK = 1;

	public static final int DRIVER_THROTTLE_AXIS = 1;
	public static final int DRIVER_LEFT_TRIGGER_AXIS = 2;
	public static final int DRIVER_RIGHT_TRIGGER_AXIS = 3;
	public static final int DRIVER_TURN_AXIS = 4;

	public static final int OPERATOR_THROTTLE_AXIS = 1;
	public static final int OPERATOR_LEFT_TRIGGER_AXIS = 2;
	public static final int OPERATOR_RIGHT_TRIGGER_AXIS = 3;
	

	// Misc
	
		//Drivetrain
	public static final double DRIVETRAIN_GEAR_RATIO = (7.103);//54:30 Low gear ratio
	public static final double DRIVETRAIN_WHEEL_DIAMETER = 6;
	
	public static final double Kp = 0.3;

	public static final double TURN_P = 0.1;
	public static final double TURN_I = 0.032;
	public static final double TURN_D = 0.3; //was .6 in match 11

		//Elevator
	public static final int ELEVATOR_GEAR_RATIO = 1;
	public static final int ELEVATOR_AXIS_DIAMETER = 1;
	public static final int ENCODER_TICKS = 256;
	
	public static final int POP_HEIGHT = 1000;
	public static final int SWITCH_HEIGHT = 10774;
	public static final int UNOWNED_SCALE_HEIGHT = 28500;
	public static final int OWNED_SCALE_HEIGHT = 22500; // LOWER
	
	public static final double ELE_DOWN_SPEED = -0.4;// THIS IS NOT THE HOLD SPEED
	public static final double ELE_HOLD_SPEED = 0.25;// Holds between 0.21 (weak hold) and 0.48, also this number needs to be negative
	
	public static final int SLOW_DRIVE_TICK_THRESHOLD = 20000;//Height at which the elevator is to enable a slow drive measured in ticks
	
	//AUTO
	public static final double AUTO_INTAKE = -1;
	public static final double AUTO_SWITCH_OUTAKE = 0.75;
	public static final double AUTO_SCALE_OUTAKE = 0.75;
}
