package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.DrivetrainDriveWithJoystick;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * This subsystem consists of the robot's drivetrain: 4 CIM motors, 4 victors, 1 solenoid and 2 pistons to shift gears.
 * The Drive subsystem has several control methods including velocity control, and position control. 
 * The Drive subsystem also has several methods that handle autonomous maneuvering, and manual control.
 */
public class Drivetrain extends PIDSubsystem {

	// Initializing left drivetrain controllers
	public static VictorSP driveControllerLeft1 = new VictorSP(RobotMap.DRIVETRAIN_LEFT);
	public static VictorSP driveControllerLeft2 = new VictorSP(RobotMap.DRIVETRAIN_LEFT2);
	public static SpeedControllerGroup driveControllersLeft = new SpeedControllerGroup(driveControllerLeft1, driveControllerLeft2);

	// Initializing right drivetrain controllers
	public static VictorSP driveControllerRight1 = new VictorSP(RobotMap.DRIVETRAIN_RIGHT);
	public static VictorSP driveControllerRight2 = new VictorSP(RobotMap.DRIVETRAIN_RIGHT2);
	public static SpeedControllerGroup driveControllersRight = new SpeedControllerGroup(driveControllerRight1, driveControllerRight2);

	// Initializing drivetrain
	public static DifferentialDrive drivetrain = new DifferentialDrive(driveControllersLeft, driveControllersRight);

	// Initializing shifting piston
	public static Solenoid shiftingPiston = new Solenoid(RobotMap.SHIFTING_PISTON);

	// Initializing auto controllers
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	public static Encoder encoderLeft = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
	public static Encoder encoderRight = new Encoder(2, 3, true, Encoder.EncodingType.k4X);
	
	public static Timer timer = new Timer();

	// Initializing auto variables
	double angle;
	private static int finalTicks;
	private int remainingTicks;
	private double remainingDistance;
	private double throttle;
	private double finalThrottle;

	public Drivetrain() {
		super("DriveTrain", RobotMap.TURN_P, RobotMap.TURN_I, RobotMap.TURN_D);
		setAbsoluteTolerance(1.0);
		getPIDController().setInputRange(-360.0, 360.0);
		getPIDController().setOutputRange(-1, 1);
	}

	@Override
	protected void initDefaultCommand() {
			setDefaultCommand(new DrivetrainDriveWithJoystick());
	}

	/* TeleOp Methods */
	
	/**
	 * Used to allow joystick control of the DT from the driver
	 * 
	 * @param Throttle The joystick axis that controls the forwards/backwards motion of the robot
	 * @param Turn The joystick axis that controls the turning of the robot
	 */
	public void drive(double Throttle, double Turn) {
		drivetrain.arcadeDrive(Throttle, Turn);
		// System.out.println("drive: " + Throttle + ", "+ Turn);
	}
	
	/**
	 * Sets all drive motors to 0
	 */
	public void stop() { //hammer time
		drivetrain.arcadeDrive(0.0, 0.0);
		// System.out.println("stop");
	}

	public void shiftDown() {
		shiftingPiston.set(true);
	}

	public void shiftUp() {
		shiftingPiston.set(false);
	}

	/**
	 * Multiples the turn input by 0.5
	 * 
	 * @param Throttle The joystick axis that controls the forwards/backwards motion of the robot
	 * @param Turn The joystick axis that controls the turning of the robot
	 */
	public void slowTurn(double Throttle, double Turn) {
		drivetrain.arcadeDrive(Throttle, 0.5 * Turn);
	}

	/* Auto Methods */
	
	/**
	 * Resets and initializes the encoders for auto 
	 * 
	 * @param direction T/F value for the encoders to read a direction as positive TODO I have no idea what this param is
	 */
	public void initEncoder(boolean direction) { 
		encoderLeft.reset();
		encoderLeft.setMaxPeriod(0.1);
		encoderLeft.setMinRate(1);
		encoderLeft.setDistancePerPulse(1);
		encoderLeft.setReverseDirection(direction);
		encoderLeft.setSamplesToAverage(7);
		encoderLeft.setDistancePerPulse((RobotMap.ENCODER_TICKS * RobotMap.DRIVETRAIN_GEAR_RATIO) /
				 (RobotMap.DRIVETRAIN_WHEEL_DIAMETER * Math.PI));// * 100 <-- with one encoder add in this to the end
		
		encoderRight.reset();
		encoderRight.setMaxPeriod(0.1);
		encoderRight.setMinRate(1);
		encoderRight.setDistancePerPulse(1);
		encoderRight.setReverseDirection(direction);
		encoderRight.setSamplesToAverage(7);
		encoderRight.setDistancePerPulse((RobotMap.ENCODER_TICKS * RobotMap.DRIVETRAIN_GEAR_RATIO) /
				 (RobotMap.DRIVETRAIN_WHEEL_DIAMETER * Math.PI * 100)); // TODO only one encoder is multiplied by 100 here...
																		// but also it works...
		
	}
	
	public double getRightDistance() {
		return ((encoderRight.get()) / (RobotMap.ENCODER_TICKS * RobotMap.DRIVETRAIN_GEAR_RATIO)
				* (RobotMap.DRIVETRAIN_WHEEL_DIAMETER * Math.PI));
	}
	
	public double getLeftDistance() {
		return ((encoderLeft.get()) / (RobotMap.ENCODER_TICKS * RobotMap.DRIVETRAIN_GEAR_RATIO)
				* (RobotMap.DRIVETRAIN_WHEEL_DIAMETER * Math.PI));
	}
	
	/**
	 * Sets the speeds of the sides of the drivetrain (between -1 and 1 for each)
	 * 
	 * @param leftSpeed
	 * @param rightSpeed
	 */
	public void setLeftRightSpeeds(double leftSpeed, double rightSpeed) {
		driveControllersLeft.set(leftSpeed);
		driveControllersRight.set(-rightSpeed);
	}

	/**
	 * Initializes the PID turn by setting the gyro's set point to the angle (param)
	 * 
	 * @param angle
	 */
	public void PIDTurnInit(double angle) {
		gyro.reset();
		this.angle = angle;
		setSetpoint(gyro.getAngle() + angle);
		enable();
		// System.out.println("Setpoint: " + angle);
	}

	public boolean PIDTurnIsFinished() {
		return onTarget();
	}

	@Override
	protected double returnPIDInput() {
		return gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		drivetrain.arcadeDrive(0.0, -output * 0.65);// 0.65 is a damping factor the lower the number is the slower the turn
//		System.out.println("Angle: " + gyro.getAngle());
//		System.out.println("Output" + -output);
	}
}