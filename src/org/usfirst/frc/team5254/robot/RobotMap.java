package org.usfirst.frc.team5254.robot;

public class RobotMap {

	// Solenoids
	public static final int CLIMBER_SLIDER_PISTON = 1;
	public static final int RATCHET_PISTON = 2;
	public static final int UNRATCHET_PISTON = 3;
	public static final int FIRE_CROSSBOW = 5;
	public static final int NO_FIRE_CROSSBOW = 4;
	public static final int SHIFTING_PISTON = 7;
	

	// Sensors
	public static final int ELE_BUTTON = 4;

	// Gyro
	public static final int GYRO = 1;
	
	// MotorControllers
	public static final int DRIVETRAIN_LEFT = 2;
	public static final int DRIVETRAIN_LEFT2 = 3;
	public static final int DRIVETRAIN_RIGHT = 0;
	public static final int DRIVETRAIN_RIGHT2 = 1;
	public static final int CUBE_MECH_LEFT_FLYWHEEL = 6;
	public static final int CUBE_MECH_RIGHT_FLYWHEEL = 7;
	public static final int CLIMBER = 8;
	public static final int CLIMBER_2 = 9;

	// Joysticks
	public static final int DRIVER_JOYSTICK = 0;
	public static final int OPERATOR_JOYSTICK = 1;

	public static final int DRIVER_THROTTLE_AXIS = 1;
	public static final int DRIVER_TURN_AXIS = 4;
	public static final int DRIVER_RIGHT_TRIGGER_AXIS = 3;

	public static final int OPERATOR_THROTTLE_AXIS = 1;

	// CAN Talon
	public static final int ELEVATOR = 4;// is 6 on comp bot

	// Misc
	
		//Drivetrain
	public static final double DRIVETRAIN_GEAR_RATIO = (7.103);
	public static final double DRIVETRAIN_WHEEL_DIAMETER = 6;
	
	public static final double Kp = 0.3;

	public static final double TURN_P = 0.4;
	public static final double TURN_I = 0.032;
	public static final double TURN_D = 1.28;

		//Elevator
	public static final int ELEVATOR_GEAR_RATIO = 1;
	public static final int ELEVATOR_AXIS_DIAMETER = 1;
	public static final int ENCODER_TICKS = 256;
	
	public static final int TOP_SCALE_HEIGHT = 41000;
	public static final int SWITCH_HEIGHT = 15254;
	public static final int DRIVE_HEIGHT = 2000;
	
	public static final double ELE_DOWN_SPEED = 0.4;
	
	public static final int SLOW_DRIVE_TICK_THRESHOLD = 20000;//height at which the elevator is to enable a slow drive measured in ticks
}
