package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.Robot;
import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.DrivetrainDriveWithJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends PIDSubsystem {

	// Declaring left drivetrain controllers
	public static WPI_TalonSRX driveControllerLeft1;
	public static WPI_TalonSRX driveControllerLeft2;
	public static SpeedControllerGroup driveControllersLeft;

	// Declaring right drivetrain controllers
	public static WPI_TalonSRX driveControllerRight1;
	public static WPI_TalonSRX driveControllerRight2;
	public static SpeedControllerGroup driveControllersRight;

	// Declaring drivetrain
	public static DifferentialDrive drivetrain;
	
	// Declaring shifting piston
	public static Solenoid shiftingPiston;

	// Declaring auto controllers
	public static ADXRS450_Gyro gyro;
	public static Encoder encoderLeft;
	public static Encoder encoderRight;
	
	// Ramping factor
	public static double rampingFactor; 
	// TODO ask when to use public/private and static and final and void and protected (which can't be used consecutively?

	// Declaring auto variables
	double angle;

	public Drivetrain() {
		super("DriveTrain", RobotMap.TURN_P, RobotMap.TURN_I, RobotMap.TURN_D);
		setAbsoluteTolerance(1.0);
		// getPIDController().setContinuous(true);
		/* ^^^ TODO does uncommenting this harm autos? 
		 * (note: commented in 2017 bc the robot was just spinning in circles when we ran auto.
		 * When we commented this its stopped doing that and ran the auto we wanted it to.)
		 */
		getPIDController().setInputRange(-360.0, 360.0);
		getPIDController().setOutputRange(-1, 1);
	    
		//Instantiating left drivetrain controllers
		driveControllerLeft1 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT);
		driveControllerLeft2 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT2);
		
		driveControllersLeft = new SpeedControllerGroup(driveControllerLeft1, driveControllerLeft2);
		
		//Instantiating right drivetrain controllers
		driveControllerRight1 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT);
		driveControllerRight2 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT2);
		
		driveControllersRight = new SpeedControllerGroup(driveControllerRight1, driveControllerRight2);
		
		drivetrain = new DifferentialDrive(driveControllersLeft, driveControllersRight);
		 
		//Instantiating shifting piston
		shiftingPiston = new Solenoid(RobotMap.SHIFTING_PISTON);
		
		//Instantiating auto controllers
		gyro = new ADXRS450_Gyro();
		encoderLeft = new Encoder(0, 1, true, Encoder.EncodingType.k4X);// 2,3 dont work on comp bot ---- marked encoder that dont work on protobot, also unwired it
		encoderRight = new Encoder(2, 3, true, Encoder.EncodingType.k4X);
		
		//Initializing encoders
		encoderLeft.reset();
		encoderLeft.setMaxPeriod(0.1);
		encoderLeft.setMinRate(1);
		encoderLeft.setDistancePerPulse(1);
		encoderLeft.setReverseDirection(true);
		encoderLeft.setSamplesToAverage(7);
		encoderLeft.setDistancePerPulse((RobotMap.ENCODER_TICKS * RobotMap.DRIVETRAIN_GEAR_RATIO) /
				 (RobotMap.DRIVETRAIN_WHEEL_DIAMETER * Math.PI));// * 100 <-- with one encoder add in this to the end
		
		encoderRight.reset();
		encoderRight.setMaxPeriod(0.1);
		encoderRight.setMinRate(1);
		encoderRight.setDistancePerPulse(1);
		encoderRight.setReverseDirection(false);
		encoderRight.setSamplesToAverage(7);
		encoderRight.setDistancePerPulse((RobotMap.ENCODER_TICKS * RobotMap.DRIVETRAIN_GEAR_RATIO) /
				 (RobotMap.DRIVETRAIN_WHEEL_DIAMETER * Math.PI * 100));
	}

	@Override
	protected void initDefaultCommand() {
			setDefaultCommand(new DrivetrainDriveWithJoystick());
	}

	// TeleOp Methods
	/* Goes through a switch depending on who is driving and what their preferences are 
	 * For the case that the controls are TankDrive the Throttle variable is really leftSideDrive
	 * and the Turn variable is really rightSideDrive
	 */
	public void drive(double Throttle, double Turn) {
		switch(Robot.dp.dc) {
		case ARCADE:	
			drivetrain.arcadeDrive(Throttle, Turn);
			break;
		case TANK:
			drivetrain.tankDrive(Throttle, Turn);
			break;
		case CURVATURE:
			drivetrain.curvatureDrive(Throttle, Turn, false); // TODO true or false? QuickTurn? variable?
			break;
		}
		
		// System.out.println("drive: " + Throttle + ", "+ Turn);
	}

	public void shiftDown() {
		shiftingPiston.set(true);
	}

	public void shiftUp() {
		shiftingPiston.set(false);
	}


	// Auto Methods
	public void setRamping(double rampingSpeed) {
		driveControllerLeft1.configOpenloopRamp(rampingSpeed);
		driveControllerLeft2.configOpenloopRamp(rampingSpeed);
		driveControllerRight1.configOpenloopRamp(rampingSpeed);
		driveControllerRight2.configOpenloopRamp(rampingSpeed);
	}
	public double getRightDistance() {
		return ((encoderRight.get()) / (RobotMap.ENCODER_TICKS * RobotMap.DRIVETRAIN_GEAR_RATIO)
				* (RobotMap.DRIVETRAIN_WHEEL_DIAMETER * Math.PI));
	}
	
	public double getLeftDistance() {
		return ((encoderLeft.get()) / (RobotMap.ENCODER_TICKS * RobotMap.DRIVETRAIN_GEAR_RATIO)
				* (RobotMap.DRIVETRAIN_WHEEL_DIAMETER * Math.PI));
	}
	
	public void setLeftRightSpeeds(double leftSpeed, double rightSpeed) {
		driveControllersLeft.set(leftSpeed);
		driveControllersRight.set(-rightSpeed);
	}


	public void PIDTurnInit(double angle) {
		gyro.reset();
		this.angle = angle;
		Robot.Drivetrain.setSetpoint(gyro.getAngle() + angle);
		Robot.Drivetrain.enable();
		// System.out.println("Setpoint: " + angle);
	}


	@Override
	protected double returnPIDInput() {
		return gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		drivetrain.arcadeDrive(0.0, -output * 0.65);// * 0.8
//		System.out.println("Angle: " + gyro.getAngle());
//		System.out.println("Output" + -output);
	}
}