package org.usfirst.frc.team5254.robot;

public class RobotMap {

	// Solenoids
	public static final int SHIFTING_PISTON = 7;
	public static final int RACHET_PISTON = 2; 
	public static final int UNRACHET_PISTON = 3;
	
	// Sensors
	
	// Gyro
	public static final int GYRO = 1;
	
	// MotorControllers
	public static final int DRIVETRAIN_LEFT = 2;
	public static final int DRIVETRAIN_LEFT2 = 3;
	public static final int DRIVETRAIN_RIGHT = 0;
	public static final int DRIVETRAIN_RIGHT2 = 1;
	public static final int CUBE_MECH_LEFT_FLYWHEEL = 6;
	public static final int CUBE_MECH_RIGHT_FLYWHEEL = 7;
//	public static final int CLIMBER_LEFT = 8;
	
	// Joysticks
	public static final int DRIVER_JOYSTICK = 0;
	public static final int OPERATOR_JOYSTICK = 1;
	
	public static final int DRIVER_THROTTLE_AXIS = 1;
	public static final int DRIVER_TURN_AXIS = 4;
	
	public static final int OPERATOR_THROTTLE_AXIS = 1;
	
	// CAN Talon
	public static final int ELEVATOR = 11; // was 4
	public static final int ELEVATOR_ENCODER = 4; // was 4
	// Misc
	public static final double DRIVETRAIN_GEAR_RATIO = ((25.0 / 6.0) * (100.0 / 91.0) * (50.0 / 48.0) * (50.0 / 56.0) * (100.0 / 103.0));
	public static final double DRIVETRAIN_WHEEL_DIAMETER = 6;
	
	public static final int ELEVATOR_GEAR_RATIO = 1;
	public static final int ELEVATOR_AXIS_DIAMETER = 1;
	public static final int ENCODER_TICKS = 256;
	
	public static final double Kp = 0.3;
	
	public static final double TURN_P = 0.4;
	public static final double TURN_I = 0.032;
	public static final double TURN_D = 1.28;
	
	public static final double ELEVATOR_P = 0.0;
	public static final double ELEVATOR_I = 0.0;
	public static final double ELEVATOR_D = 0.0;
	
	public static final int kTimeoutMs = 0;
	public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
}

























