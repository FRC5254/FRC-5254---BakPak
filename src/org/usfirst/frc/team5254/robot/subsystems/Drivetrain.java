package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.Robot;
import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.DrivetrainDriveWithJoystick;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Drivetrain extends PIDSubsystem {
	
	//Initializing left drivetrain controllers
	public static VictorSP driveControllerLeft1 = new VictorSP(RobotMap.DRIVETRAIN_LEFT);
	public static VictorSP driveControllerLeft2 = new VictorSP(RobotMap.DRIVETRAIN_LEFT2);
	public static SpeedControllerGroup driveControllersLeft = new SpeedControllerGroup(driveControllerLeft1, driveControllerLeft2);
	
	//Initializing right drivetrain controllers
	public static VictorSP driveControllerRight1 = new VictorSP(RobotMap.DRIVETRAIN_RIGHT);
	public static VictorSP driveControllerRight2 = new VictorSP(RobotMap.DRIVETRAIN_RIGHT2);
	public static SpeedControllerGroup driveControllersRight = new SpeedControllerGroup(driveControllerRight1, driveControllerRight2);
	
	//Initializing drivetrain
	public static DifferentialDrive drivetrain = new DifferentialDrive(driveControllersLeft, driveControllersRight); 
	
	//Initializing shifting piston
	public static Solenoid shiftingPiston = new Solenoid(RobotMap.SHIFTING_PISTON);
	
	//Initializing auto controllers
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	public static Encoder encoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
	
	public static Timer timer = new Timer();
	
	//Initializing auto variables
	double angle;
	private static int finalTicks;
	private int remainingTicks;
	private double remainingDistance;
	private double Throttle;
	private double finalThrottle;
	
	public Drivetrain() {	
		super("DriveTrain", RobotMap.TURN_P, RobotMap.TURN_I, RobotMap.TURN_D);
		setAbsoluteTolerance(3.0);
		getPIDController().setContinuous(true);
	}
	
	//TeliOp Methods
	public void drive(double Throttle,double Turn) {
		drivetrain.arcadeDrive(Throttle, -Turn);
	}

	public void stop() {
		drivetrain.arcadeDrive(0.0,0.0);
	}
	public void shiftDown() {
		shiftingPiston.set(true);
	}
	public void shiftUp() {
		shiftingPiston.set(false);
	}
	public void slowTurn(double Throttle, double Turn) {
		drivetrain.arcadeDrive(Throttle, 0.5 * Turn);
	}
	
	//Auto Methods
	public void initEncoder(boolean direction) {
		encoder.reset();
		encoder.setMaxPeriod(0.1);
		encoder.setMinRate(1);
		encoder.setDistancePerPulse(1);
		encoder.setReverseDirection(direction);
		encoder.setSamplesToAverage(7);
		
	}
	
	public void autoDistanceDriveInit(double Throttle, double Distance) {
		
		this.Throttle = Throttle;
		finalTicks = (int) ((Distance / (RobotMap.WHEEL_DIAMETER * Math.PI)) * RobotMap.ENCODER_TICKS * RobotMap.GEAR_RATIO);
		if (Throttle > 0) {
			initEncoder(true);
		} else {
			initEncoder(false);
		}
		gyro.reset();
		timer.reset();
		timer.start();
	}
	
	public void autoDriveToDistance() {
		remainingTicks = Math.abs(finalTicks) - Math.abs(encoder.get());
		remainingDistance = (Math.abs(remainingTicks) / (RobotMap.ENCODER_TICKS * RobotMap.GEAR_RATIO))
				* (RobotMap.WHEEL_DIAMETER * Math.PI);

		if (Throttle > 0) {
			if (remainingDistance < Throttle * 15) {
				finalThrottle = remainingDistance / 15;// TODO time these values
			} else {
				finalThrottle = Throttle;
			}

			if (timer.get() < 0.25 && remainingDistance > 10.0) {
				finalThrottle = timer.get() * 4.0;
			}

			if (finalThrottle > Throttle) {
				finalThrottle = Throttle;
			}

			if (finalThrottle < 0.35) {
				finalThrottle = 0.35;
			}
		} else {
			if (remainingDistance < Math.abs(Throttle) * 15) {
				finalThrottle = -remainingDistance / 15;
			} else {
				finalThrottle = Throttle;

			}

			if (timer.get() < 0.25 && remainingDistance > 10.0) {
				finalThrottle = -timer.get() * 4.0;
			}

			if (finalThrottle < Throttle) {
				finalThrottle = Throttle;
			}

			if (finalThrottle > -0.35) {
				finalThrottle = -0.35;
			}
		}
		
		drive(-finalThrottle, -gyro.getAngle() * RobotMap.Kp);
		 System.out.println(gyro.getAngle() + " " + Throttle + " " +
		 remainingDistance + " " + finalThrottle + " " + encoder.get() + " " +
		 remainingTicks);
	}	
	
	public boolean driveAutoIsFinished() {
		return remainingTicks < 21;
	}
	
	public void autoDistanceDriveFast() {
		remainingTicks = Math.abs(finalTicks) - Math.abs(encoder.get());
		drive(-Throttle, -gyro.getAngle() + this.angle);
	}
	
	public void PIDTurnInit() {
		Robot.Drivetrain.setSetpoint(gyro.getAngle() + this.angle);
		Robot.Drivetrain.enable();
	}
	
	@Override
	protected double returnPIDInput() {
		return gyro.getAngle();
	}
	
	@Override
	protected void usePIDOutput(double output) {
		drive(0.0, output);
	}
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DrivetrainDriveWithJoystick());
		
	}

	
	
}