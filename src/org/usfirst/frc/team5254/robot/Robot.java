package org.usfirst.frc.team5254.robot;

import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autos.CenterSwitchAuto;
import org.usfirst.frc.team5254.robot.autos.CenterSwitchAutoTwoCube;
import org.usfirst.frc.team5254.robot.autos.CrossBaselineAuto;
import org.usfirst.frc.team5254.robot.autos.FakeCenterSwitchAuto;
import org.usfirst.frc.team5254.robot.autos.FarScaleAuto;
import org.usfirst.frc.team5254.robot.autos.NullScaleAuto;
import org.usfirst.frc.team5254.robot.autos.StuyPulseSwitchAuto;
import org.usfirst.frc.team5254.robot.autos.TwoCubeScaleAuto;
import org.usfirst.frc.team5254.robot.autos.WranglerSwitchAuto;
import org.usfirst.frc.team5254.robot.subsystems.Climber;
import org.usfirst.frc.team5254.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5254.robot.subsystems.Elevator;
import org.usfirst.frc.team5254.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	// AUTO SELECTOR THING
	private static String startingPosition = "L"; // L/R/C

	String gameData;
	int allianceNumber;
	
	public static Drivetrain Drivetrain;
	public static Intake Intake; 
	public static Elevator Elevator;
	public static Climber Climber;
	public static OI oi;
	
	Command autonomousCommand;
	
	@Override
	public void robotInit() {
		
		Intake = new Intake();
		Elevator = new Elevator();
		Climber = new Climber();
		Drivetrain = new Drivetrain();

		// Initializing cameras
		CameraServer.getInstance().startAutomaticCapture(1);
		CameraServer.getInstance().startAutomaticCapture(0);

		// This MUST BE LAST or a NullPointerException will be thrown
		oi = new OI();
	}

	@Override
	public void autonomousInit() {
		
		Robot.Drivetrain.shiftDown();
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		allianceNumber = DriverStation.getInstance().getLocation();
		
		// Do look its finished
		if (gameData != null) {
			gameData = DriverStation.getInstance().getGameSpecificMessage().substring(0, 2);
		} else {
			gameData = "";
			System.out.println("Uh oh ---------------- BAD STARTING GAME DATA ---------------- Running cross autoline");
		}
		
		switch(gameData) {
		
		case "LL": // when switch and scale are on left side 
			
//			autonomousCommand = new CrossBaselineAuto();
//			autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD);
//			autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, Paths.FROM_CENTER.SWITCH_LEFT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SWITCH_AFTER_GRAB_CUBE);			
//			autonomousCommand = new CenterSwitchScaleDrive(true, Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, Paths.FROM_CENTER.SWITCH_LEFT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SIDE_AFTER_GRAB_CUBE);
//			autonomousCommand = new SideSwitchAuto(Paths.FROM_LEFT.SWITCH_LEFT_TRAVEL, Paths.FROM_LEFT.SWITCH_LEFT_FINISH);
//			autonomousCommand = new StuyPulseSwitchAuto(false, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2_CUT_SHORT, Paths.FROM_RIGHT.BACK_SWITCH_BACKUP);
//			autonomousCommand = new CloseScaleAuto(Paths.FROM_LEFT.SCALE_LEFT_TRAVEL, Paths.FROM_LEFT.SCALE_LEFT_FINISH);
//			autonomousCommand = new NullScaleAuto(true);
//			autonomousCommand = new TwoCubeScaleAuto(true, Paths.FROM_LEFT.SCALE_LEFT_TRAVEL, Paths.FROM_LEFT.SCALE_LEFT_FINISH, Paths.FROM_LEFT.SCALE_LEFT_SECOND_CUBE_GRAB);
//			autonomousCommand = new FarScaleAuto(Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2, Paths.FROM_RIGHT.SCALE_LEFT_FINISH);
//			autonomousCommand = new ScaleTravelAuto(Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2_CUT_SHORT);
//			autonomousCommand = new NothingAuto();
//			autonomousCommand = new TestAuto();
			
			System.out.println("CenterSwitchAutoTwoCube");
			SmartDashboard.putString("DB/String 0", "CenterSwitchAutoTwoCube");
			break; 
                
		case "LR": // switch is on left, scale is on right 
			
//			autonomousCommand = new CrossBaselineAuto();
//			autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD);
//			autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, Paths.FROM_CENTER.SWITCH_LEFT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SWITCH_AFTER_GRAB_CUBE);			
//			autonomousCommand = new CenterSwitchScaleDrive(false, Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, Paths.FROM_CENTER.SWITCH_LEFT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.RIGHT_SIDE_AFTER_GRAB_CUBE);
//			autonomousCommand = new SideSwitchAuto(Paths.FROM_LEFT.SWITCH_LEFT_TRAVEL, Paths.FROM_LEFT.SWITCH_LEFT_FINISH);
//			autonomousCommand = new WranglerSwitchAuto(true, Paths.FROM_LEFT.BACK_SWITCH_PLACE_CUBE1);
//			autonomousCommand = new CloseScaleAuto(Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, Paths.FROM_RIGHT.SCALE_RIGHT_FINISH);
//			autonomousCommand = new NullScaleAuto(false);
//			autonomousCommand = new TwoCubeScaleAuto(false, Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, Paths.FROM_RIGHT.SCALE_RIGHT_FINISH, Paths.FROM_RIGHT.SCALE_RIGHT_SECOND_CUBE_GRAB);
			autonomousCommand = new FarScaleAuto(Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2, Paths.FROM_LEFT.SCALE_RIGHT_FINISH);
//			autonomousCommand = new ScaleTravelAuto(Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2_CUT_SHORT);
//			autonomousCommand = new NothingAuto();
//			autonomousCommand = new TestAuto();	
			System.out.println("CenterSwitchAutoTwoCube");
			SmartDashboard.putString("DB/String 1", "CenterSwitchAutoTwoCube");
			break;   
                  
		case "RL": // switch is on right, scale is on left
			
			autonomousCommand = new CrossBaselineAuto();
//			autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD);
//			autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, Paths.FROM_CENTER.SWITCH_RIGHT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.RIGHT_SWITCH_AFTER_GRAB_CUBE);			
//			autonomousCommand = new CenterSwitchScaleDrive(false, Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, Paths.FROM_CENTER.SWITCH_RIGHT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SIDE_AFTER_GRAB_CUBE);
//			autonomousCommand = new SideSwitchAuto(Paths.FROM_RIGHT.SWITCH_RIGHT_TRAVEL, Paths.FROM_RIGHT.SWITCH_RIGHT_FINISH);
//			autonomousCommand = new WranglerSwitchAuto(false, Paths.FROM_RIGHT.BACK_SWITCH_PLACE_CUBE1);
//			autonomousCommand = new CloseScaleAuto(Paths.FROM_LEFT.SCALE_LEFT_TRAVEL, Paths.FROM_LEFT.SCALE_LEFT_FINISH);
//			autonomousCommand = new NullScaleAuto(true);
//			autonomousCommand = new TwoCubeScaleAuto(true, Paths.FROM_LEFT.SCALE_LEFT_TRAVEL, Paths.FROM_LEFT.SCALE_LEFT_FINISH, Paths.FROM_LEFT.SCALE_LEFT_SECOND_CUBE_GRAB);
//			autonomousCommand = new FarScaleAuto(Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2, Paths.FROM_RIGHT.SCALE_LEFT_FINISH);
//			autonomousCommand = new ScaleTravelAuto(Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2_CUT_SHORT);
//			autonomousCommand = new NothingAuto();
//			autonomousCommand = new TestAuto();	
			System.out.println("CenterSwitchAutoTwoCube");
			SmartDashboard.putString("DB/String 2", "CenterSwitchAutoTwoCube");
			break;
        
		case "RR": // switch is on right, scale is on right
			
//			autonomousCommand = new CrossBaselineAuto();
//			autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD);
//			autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, Paths.FROM_CENTER.SWITCH_RIGHT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.RIGHT_SWITCH_AFTER_GRAB_CUBE);			
//			autonomousCommand = new CenterSwitchScaleDrive(false, Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, Paths.FROM_CENTER.SWITCH_RIGHT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SIDE_AFTER_GRAB_CUBE);
//			autonomousCommand = new SideSwitchAuto(Paths.FROM_RIGHT.SWITCH_RIGHT_TRAVEL, Paths.FROM_RIGHT.SWITCH_RIGHT_FINISH);
			autonomousCommand = new StuyPulseSwitchAuto(true, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2_CUT_SHORT, Paths.FROM_LEFT.BACK_SWITCH_BACKUP);
//			autonomousCommand = new CloseScaleAuto(Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, Paths.FROM_RIGHT.SCALE_RIGHT_FINISH);
//			autonomousCommand = new NullScaleAuto(false);
//			autonomousCommand = new TwoCubeScaleAuto(false, Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, Paths.FROM_RIGHT.SCALE_RIGHT_FINISH, Paths.FROM_RIGHT.SCALE_RIGHT_SECOND_CUBE_GRAB);
//			autonomousCommand = new FarScaleAuto(Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2, Paths.FROM_LEFT.SCALE_RIGHT_FINISH);
//			autonomousCommand = new ScaleTravelAuto(Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2_CUT_SHORT);
//			autonomousCommand = new NothingAuto();
//			autonomousCommand = new TestAuto();
			System.out.println("CenterSwitchAutoTwoCube");
			SmartDashboard.putString("DB/String 3", "CenterSwitchAutoTwoCube");
			break;
		
		default:
			if(!startingPosition.equals("C")) {
				autonomousCommand = new CrossBaselineAuto();
//				System.out.println(autonomousCommandString);
//				SmartDashboard.putString("DB/String 4", );
			} else {
				autonomousCommand = new FakeCenterSwitchAuto(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD); // goes left bc we like the left side idk
//				System.out.println(autonomousCommandString);
//				SmartDashboard.putString("DB/String 4", autonomousCommandString);
			}
			break;
       }	
		// Defaults to run the Test Auto
//		autonomousCommand = new TestAuto();
		
//		String autonomousCommandString = autonomousCommand.toString();
//		SmartDashboard.putString("DB/String 0", autonomousCommandString);
		// Schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
//		SmartDashboard.putNumber("Encoder Adverage", ( Math.abs( Robot.Drivetrain.getRightDistance() ) + Math.abs( Robot.Drivetrain.getLeftDistance() ) )/2);
		SmartDashboard.putNumber("left", Drivetrain.encoderLeft.getDistance());
		SmartDashboard.putNumber("right", Drivetrain.encoderRight.getDistance());
		SmartDashboard.putNumber("elevator height", Robot.Elevator.getHeight());
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
		Robot.Drivetrain.shiftDown();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
//		System.out.println(Robot.oi.driver.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS));
//		System.out.println(Robot.Elevator.elevator.getMotorOutputPercent());
		SmartDashboard.putNumber("left", Drivetrain.getLeftDistance());
		SmartDashboard.putNumber("right", Drivetrain.getRightDistance());
//		System.out.println(Robot.Elevator.elevator.getSelectedSensorPosition(0));
//		System.out.println(Robot.Elevator.eleButton.get());
		
		SmartDashboard.putNumber("POV", oi.driver.getPOV());


		SmartDashboard.putNumber("elevator height", Robot.Elevator.getHeight());
		
		if (Robot.Elevator.mightBreak()) {
			Robot.Elevator.dontBreak();
		}
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
