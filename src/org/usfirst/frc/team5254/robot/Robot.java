package org.usfirst.frc.team5254.robot;

import org.usfirst.frc.team5254.robot.subsystems.*;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.autos.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorDown;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Robot extends IterativeRobot {
	
	// AUTO SELECTOR THING [UNUSED]
	private static String startingPosition = "L"; // L/R/C

	NetworkTable table;
	String gameData;
	int allianceNumber;
	
	Timer timer = new Timer();
	Timer autoTimer = new Timer();

	public static Drivetrain Drivetrain = new Drivetrain();
	public static Intake Intake = new Intake();
	public static Elevator Elevator = new Elevator();
	public static Climber Climber = new Climber();
	public static OI oi;
	
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	Command autonomousCommand;
	Command someCommand;
	
	@Override
	public void robotInit() {

		// Config smartdash
		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		NetworkTable table = inst.getTable("SmartDashboard");

		// Initializing cameras
		CameraServer.getInstance().startAutomaticCapture(1);
		CameraServer.getInstance().startAutomaticCapture(0);

		// This MUST BE LAST or a NullPointerException will be thrown
		oi = new OI();
	}

	@Override
	public void autonomousInit() {
		
		// TODO do the scale auto outline and make more logic in here to make it cooler
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		allianceNumber = DriverStation.getInstance().getLocation();

		// Dont look not finished
//		if (gameData != null) {
//			gameData = DriverStation.getInstance().getGameSpecificMessage().substring(0, 2);
//		} else {
//			System.out.println("Uh oh");
//			TryAgain();
//		}
		
		switch(gameData) {
			
		case "LL": // when switch and scale are on left side 
			
		/** Basic */
			autonomousCommand = new CrossBaselineAuto();
			
		/** Switch */
			// Center
			autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD);
			autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, Paths.FROM_CENTER.SWITCH_LEFT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SWITCH_AFTER_GRAB_CUBE);			
			autonomousCommand = new CenterSwitchScaleDrive(true, Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, Paths.FROM_CENTER.SWITCH_LEFT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SIDE_AFTER_GRAB_CUBE);
			
			// Not Center
			autonomousCommand = new SideSwitchAuto(Paths.FROM_LEFT.SWITCH_LEFT_TRAVEL, Paths.FROM_LEFT.SWITCH_LEFT_FINISH);
			autonomousCommand = new StuyPulseSwitchAuto(false, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2_CUT_SHORT, Paths.FROM_RIGHT.BACK_SWITCH_BACKUP);
			
		/** Scale */
			autonomousCommand = new CloseScaleAuto(Paths.FROM_LEFT.SCALE_LEFT_TRAVEL, Paths.FROM_LEFT.SCALE_LEFT_FINISH);
			autonomousCommand = new NullScaleAuto(true);
			autonomousCommand = new TwoCubeScaleAuto(true, Paths.FROM_LEFT.SCALE_LEFT_TRAVEL, Paths.FROM_LEFT.SCALE_LEFT_FINISH, Paths.FROM_LEFT.SCALE_LEFT_SECOND_CUBE_GRAB);
			autonomousCommand = new FarScaleAuto(Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2, Paths.FROM_RIGHT.SCALE_LEFT_FINISH);
			
		/** Other */
			autonomousCommand = new ScaleTravelAuto(Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2_CUT_SHORT);
			autonomousCommand = new NothingAuto();
			autonomousCommand = new TestAuto();			
			break; 
                
		case "LR": // switch is on left, scale is on right 
			
			autonomousCommand = new CrossBaselineAuto();
			autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD);
			autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, Paths.FROM_CENTER.SWITCH_LEFT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SWITCH_AFTER_GRAB_CUBE);			
			autonomousCommand = new CenterSwitchScaleDrive(false, Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, Paths.FROM_CENTER.SWITCH_LEFT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.RIGHT_SIDE_AFTER_GRAB_CUBE);
			autonomousCommand = new SideSwitchAuto(Paths.FROM_LEFT.SWITCH_LEFT_TRAVEL, Paths.FROM_LEFT.SWITCH_LEFT_FINISH);
			autonomousCommand = new WranglerSwitchAuto(true, Paths.FROM_LEFT.BACK_SWITCH_PLACE, Paths.FROM_LEFT.BACK_SWITCH_SECOND_CUBE);
			autonomousCommand = new CloseScaleAuto(Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, Paths.FROM_RIGHT.SCALE_RIGHT_FINISH);
			autonomousCommand = new NullScaleAuto(false);
			autonomousCommand = new TwoCubeScaleAuto(false, Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, Paths.FROM_RIGHT.SCALE_RIGHT_FINISH, Paths.FROM_RIGHT.SCALE_RIGHT_SECOND_CUBE_GRAB);
			autonomousCommand = new FarScaleAuto(Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2, Paths.FROM_LEFT.SCALE_RIGHT_FINISH);
			autonomousCommand = new ScaleTravelAuto(Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2_CUT_SHORT);
			autonomousCommand = new NothingAuto();
			autonomousCommand = new TestAuto();	
			break;   
                  
		case "RL": // switch is on right, scale is on left
			
			autonomousCommand = new CrossBaselineAuto();
			autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD);
			autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, Paths.FROM_CENTER.SWITCH_RIGHT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.RIGHT_SWITCH_AFTER_GRAB_CUBE);			
			autonomousCommand = new CenterSwitchScaleDrive(false, Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, Paths.FROM_CENTER.SWITCH_RIGHT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SIDE_AFTER_GRAB_CUBE);
			autonomousCommand = new SideSwitchAuto(Paths.FROM_RIGHT.SWITCH_RIGHT_TRAVEL, Paths.FROM_RIGHT.SWITCH_RIGHT_FINISH);
			autonomousCommand = new WranglerSwitchAuto(false, Paths.FROM_RIGHT.BACK_SWITCH_PLACE, Paths.FROM_RIGHT.BACK_SWITCH_SECOND_CUBE);
			autonomousCommand = new CloseScaleAuto(Paths.FROM_LEFT.SCALE_LEFT_TRAVEL, Paths.FROM_LEFT.SCALE_LEFT_FINISH);
			autonomousCommand = new NullScaleAuto(true);
			autonomousCommand = new TwoCubeScaleAuto(true, Paths.FROM_LEFT.SCALE_LEFT_TRAVEL, Paths.FROM_LEFT.SCALE_LEFT_FINISH, Paths.FROM_LEFT.SCALE_LEFT_SECOND_CUBE_GRAB);
			autonomousCommand = new FarScaleAuto(Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2, Paths.FROM_RIGHT.SCALE_LEFT_FINISH);
			autonomousCommand = new ScaleTravelAuto(Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2_CUT_SHORT);
			autonomousCommand = new NothingAuto();
			autonomousCommand = new TestAuto();	
			break;
        
		case "RR": // switch is on right, scale is on right
			
			autonomousCommand = new CrossBaselineAuto();
			autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD);
			autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, Paths.FROM_CENTER.SWITCH_RIGHT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.RIGHT_SWITCH_AFTER_GRAB_CUBE);			
			autonomousCommand = new CenterSwitchScaleDrive(false, Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, Paths.FROM_CENTER.SWITCH_RIGHT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SIDE_AFTER_GRAB_CUBE);
			autonomousCommand = new SideSwitchAuto(Paths.FROM_RIGHT.SWITCH_RIGHT_TRAVEL, Paths.FROM_RIGHT.SWITCH_RIGHT_FINISH);
			autonomousCommand = new StuyPulseSwitchAuto(true, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2_CUT_SHORT, Paths.FROM_LEFT.BACK_SWITCH_BACKUP);
			autonomousCommand = new CloseScaleAuto(Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, Paths.FROM_RIGHT.SCALE_RIGHT_FINISH);
			autonomousCommand = new NullScaleAuto(false);
			autonomousCommand = new TwoCubeScaleAuto(false, Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, Paths.FROM_RIGHT.SCALE_RIGHT_FINISH, Paths.FROM_RIGHT.SCALE_RIGHT_SECOND_CUBE_GRAB);
			autonomousCommand = new FarScaleAuto(Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2, Paths.FROM_LEFT.SCALE_RIGHT_FINISH);
			autonomousCommand = new ScaleTravelAuto(Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2_CUT_SHORT);
			autonomousCommand = new NothingAuto();
			autonomousCommand = new TestAuto();
			break;
		
		default:
			autonomousCommand = new CrossBaselineAuto();
			break;
			
       }	
		
//		TODO DELETE THESE
//		autonomousCommand = new CrossBaselineAuto();
//		autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER .SWITCH_LEFT_FORWARD); weren't tested
//		autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD); ^^^^^^^^^^^^^
//		autonomousCommand = new NullScaleAuto(true);
//		autonomousCommand = new NullScaleAuto(false);
//		autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, Paths.FROM_CENTER.SWITCH_LEFT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SWITCH_AFTER_GRAB_CUBE);
//		autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, Paths.FROM_CENTER.SWITCH_RIGHT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.RIGHT_SWITCH_AFTER_GRAB_CUBE);
//		autonomousCommand = new WranglerSwitchAuto(true, Paths.FROM_LEFT.BACK_SWITCH_PLACE, Paths.FROM_LEFT.BACK_SWITCH_SECOND_CUBE);
//		autonomousCommand = new WranglerSwitchAuto(false, Paths.FROM_RIGHT.BACK_SWITCH_PLACE, Paths.FROM_RIGHT.BACK_SWITCH_SECOND_CUBE);
//		autonomousCommand = new StuyPulseSwitchAuto(true, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2_CUT_SHORT, Paths.FROM_LEFT.BACK_SWITCH_BACKUP);
//		autonomousCommand = new StuyPulseSwitchAuto(false, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2_CUT_SHORT, Paths.FROM_RIGHT.BACK_SWITCH_BACKUP);
//		autonomousCommand = new TwoCubeScaleAuto(true, Paths.FROM_LEFT.SCALE_LEFT_TRAVEL, Paths.FROM_LEFT.SCALE_LEFT_FINISH, Paths.FROM_LEFT.SCALE_LEFT_SECOND_CUBE_GRAB);
//		autonomousCommand = new TwoCubeScaleAuto(false, Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, Paths.FROM_RIGHT.SCALE_RIGHT_FINISH, Paths.FROM_RIGHT.SCALE_RIGHT_SECOND_CUBE_GRAB);
//		autonomousCommand = new FarScaleAuto(Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2, Paths.FROM_LEFT.SCALE_RIGHT_FINISH);
//		autonomousCommand = new FarScaleAuto(Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2, Paths.FROM_RIGHT.SCALE_LEFT_FINISH);

		// Schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
//		SmartDashboard.putNumber("Encoder Adverage", ( Math.abs( Robot.Drivetrain.getRightDistance() ) + Math.abs( Robot.Drivetrain.getLeftDistance() ) )/2);
//		SmartDashboard.putNumber("left", Drivetrain.encoderLeft.getDistance());
//		SmartDashboard.getNumber("right", Drivetrain.encoderRight.getDistance());
//		SmartDashboard.getNumber("Gyro", Drivetrain.gyro.getAngle());
//	System.out.println("Right " + Robot.Drivetrain.encoderRight.get());
//	System.out.println("Right Distance " + Robot.Drivetrain.getRightDistance());
//	System.out.println("Left Distance " + Robot.Drivetrain.getLeftDistance());
//	System.out.println("Left " + Robot.Drivetrain.encoderLeft.get());
//		System.out.println(Robot.Drivetrain.encoderLeft.getDistance());
//		System.out.println(Robot.Elevator.elevator.getSelectedSensorPosition(0));
//		System.out.println(Robot.oi.driver.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS));
	}

	@Override
	public void teleopInit() {
//		Auto = false;
		timer.reset();
		timer.start();
		
		someCommand = new ElevatorDown(RobotMap.ELE_DOWN_SPEED);
		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
//		System.out.println(Robot.oi.driver.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS));
//		System.out.println(Robot.Elevator.elevator.getMotorOutputPercent());
//		SmartDashboard.putNumber("Distance", Robot.Drivetrain.getDistance());
//		System.out.println(Robot.Elevator.elevator.getSelectedSensorPosition(0));
		System.out.println(Robot.Elevator.eleButton.get());

		if (Robot.Elevator.mightBreak()) {
			Robot.Elevator.dontBreak();
		}
		// This is and elevator down failsafe
		
//		if (timer.get() == 125) {
//			timer.stop();
//			someCommand.start();
//		}
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}