/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5254.robot;

import org.usfirst.frc.team5254.robot.subsystems.Climber;
import org.usfirst.frc.team5254.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5254.robot.subsystems.Elevator;
import org.usfirst.frc.team5254.robot.subsystems.Intake;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autos.CenterSwitchAuto;
import org.usfirst.frc.team5254.robot.autos.CenterSwitchAutoTwoCube;
import org.usfirst.frc.team5254.robot.autos.CenterSwitchScaleDrive;
import org.usfirst.frc.team5254.robot.autos.CloseScaleAuto;
import org.usfirst.frc.team5254.robot.autos.CrossBaselineAuto;
import org.usfirst.frc.team5254.robot.autos.FakeCenterSwitchAuto;
import org.usfirst.frc.team5254.robot.autos.FarScaleAuto;
import org.usfirst.frc.team5254.robot.autos.NothingAuto;
import org.usfirst.frc.team5254.robot.autos.NullScaleAuto;
import org.usfirst.frc.team5254.robot.autos.ScaleTravelAuto;
import org.usfirst.frc.team5254.robot.autos.SideSwitchAuto;
import org.usfirst.frc.team5254.robot.autos.StuyPulseSwitchAuto;
import org.usfirst.frc.team5254.robot.autos.TestAuto;
import org.usfirst.frc.team5254.robot.autos.TwoCubeScaleAuto;
import org.usfirst.frc.team5254.robot.autos.WranglerSwitchAuto;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * full send only
 */
public class Robot extends IterativeRobot {
	
	// Autonomous variables
	private static String startingPosition = "L"; // L/R/C
	String gameData; // Used for auto selector 
	
	Command autonomousCommand; // (very important)
	
	// SmartDash
	NetworkTable table; // Used when putting print statements to SmartDashboard
 
	// Subsystems
	public static Drivetrain drivetrain;
	public static Intake intake;
	public static Elevator elevator;
	public static Climber climber;
	public static OI oi;
	
	/**
	 * This function is run when the robot is first started up.
	 * 
	 * <p>Initializes cameras, subsystems, robot controls and Network tables
	 */
	@Override
	public void robotInit() {

		// Config smartdash
		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		NetworkTable table = inst.getTable("SmartDashboard");

		// Initializing cameras
		CameraServer.getInstance().startAutomaticCapture(1);
		CameraServer.getInstance().startAutomaticCapture(0);

		// Subsystem initalization 
		drivetrain = new Drivetrain();
		intake = new Intake();
		elevator = new Elevator();
		climber = new Climber();
		
		// This MUST BE LAST or a NullPointerException will be thrown
		oi = new OI();
		}

	/**
	 * This function is run when the robot first enters the autonomous period.
	 * 
	 * <p>
	 * Obtains the game data from the field, checks game data, and selects the auto to run depending on game data
	 */
	@Override
	public void autonomousInit() {
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
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
			autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, Paths.FROM_CENTER.SWITCH_LEFT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SWITCH_AFTER_GRAB_CUBE);			
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
			
			System.out.println(autonomousCommand.toString()); // TODO test this will it work? Yo Justin if you see this tell me how to make this work
			SmartDashboard.putString("DB/String 0", autonomousCommand.toString());
			break; 
                
		case "LR": // switch is on left, scale is on right 
			
//			autonomousCommand = new CrossBaselineAuto();
//			autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD);
			autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, Paths.FROM_CENTER.SWITCH_LEFT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SWITCH_AFTER_GRAB_CUBE);			
//			autonomousCommand = new CenterSwitchScaleDrive(false, Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, Paths.FROM_CENTER.SWITCH_LEFT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.RIGHT_SIDE_AFTER_GRAB_CUBE);
//			autonomousCommand = new SideSwitchAuto(Paths.FROM_LEFT.SWITCH_LEFT_TRAVEL, Paths.FROM_LEFT.SWITCH_LEFT_FINISH);
//			autonomousCommand = new WranglerSwitchAuto(true, Paths.FROM_LEFT.BACK_SWITCH_PLACE_CUBE1);
//			autonomousCommand = new CloseScaleAuto(Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, Paths.FROM_RIGHT.SCALE_RIGHT_FINISH);
//			autonomousCommand = new NullScaleAuto(false);
//			autonomousCommand = new TwoCubeScaleAuto(false, Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, Paths.FROM_RIGHT.SCALE_RIGHT_FINISH, Paths.FROM_RIGHT.SCALE_RIGHT_SECOND_CUBE_GRAB);
//			autonomousCommand = new FarScaleAuto(Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2, Paths.FROM_LEFT.SCALE_RIGHT_FINISH);
//			autonomousCommand = new ScaleTravelAuto(Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2_CUT_SHORT);
//			autonomousCommand = new NothingAuto();
//			autonomousCommand = new TestAuto();	
			
			System.out.println(autonomousCommand.toString());
			SmartDashboard.putString("DB/String 1", autonomousCommand.toString());
			break;   
                  
		case "RL": // switch is on right, scale is on left
			
//			autonomousCommand = new CrossBaselineAuto();
//			autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD);
			autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, Paths.FROM_CENTER.SWITCH_RIGHT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.RIGHT_SWITCH_AFTER_GRAB_CUBE);			
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
			
			System.out.println(autonomousCommand.toString());
			SmartDashboard.putString("DB/String 2", autonomousCommand.toString());
			break;
        
		case "RR": // switch is on right, scale is on right
			
//			autonomousCommand = new CrossBaselineAuto();
//			autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD);
			autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, Paths.FROM_CENTER.SWITCH_RIGHT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.RIGHT_SWITCH_AFTER_GRAB_CUBE);			
//			autonomousCommand = new CenterSwitchScaleDrive(false, Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, Paths.FROM_CENTER.SWITCH_RIGHT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SIDE_AFTER_GRAB_CUBE);
//			autonomousCommand = new SideSwitchAuto(Paths.FROM_RIGHT.SWITCH_RIGHT_TRAVEL, Paths.FROM_RIGHT.SWITCH_RIGHT_FINISH);
//			autonomousCommand = new StuyPulseSwitchAuto(true, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2_CUT_SHORT, Paths.FROM_LEFT.BACK_SWITCH_BACKUP);
//			autonomousCommand = new CloseScaleAuto(Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, Paths.FROM_RIGHT.SCALE_RIGHT_FINISH);
//			autonomousCommand = new NullScaleAuto(false);
//			autonomousCommand = new TwoCubeScaleAuto(false, Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, Paths.FROM_RIGHT.SCALE_RIGHT_FINISH, Paths.FROM_RIGHT.SCALE_RIGHT_SECOND_CUBE_GRAB);
//			autonomousCommand = new FarScaleAuto(Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2, Paths.FROM_LEFT.SCALE_RIGHT_FINISH);
//			autonomousCommand = new ScaleTravelAuto(Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2_CUT_SHORT);
//			autonomousCommand = new NothingAuto();
//			autonomousCommand = new TestAuto();
			
			System.out.println(autonomousCommand.toString());
			SmartDashboard.putString("DB/String 3", autonomousCommand.toString());
			break;
		
		default:
			if(!startingPosition.equals("C")) {
				autonomousCommand = new CrossBaselineAuto();
				System.out.println("CrossBaselineAuto");
				SmartDashboard.putString("DB/String 6", "CrossBaselineAuto");
			} else {
				autonomousCommand = new FakeCenterSwitchAuto(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD); // Travels to the left side of the switch
				System.out.println("FakeCenterSwitchAuto");
				SmartDashboard.putString("DB/String 7", "FakeCenterSwitchAuto");
			}
			break;
       }	
		
		// Hard code testing of autos/pathing
//		autonomousCommand = new CrossBaselineAuto();
//		autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER .SWITCH_LEFT_FORWARD); 
//		autonomousCommand = new CenterSwitchAuto(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD);
//		autonomousCommand = new NullScaleAuto(true);
//		autonomousCommand = new NullScaleAuto(false);
//		autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, Paths.FROM_CENTER.SWITCH_LEFT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.LEFT_SWITCH_AFTER_GRAB_CUBE);
//		autonomousCommand = new CenterSwitchAutoTwoCube(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, Paths.FROM_CENTER.SWITCH_RIGHT_BACKWARD, Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, Paths.FROM_CENTER.RIGHT_SWITCH_AFTER_GRAB_CUBE);
//		autonomousCommand = new WranglerSwitchAuto(true, Paths.FROM_LEFT.BACK_SWITCH_PLACE_CUBE1);
//		autonomousCommand = new WranglerSwitchAuto(false, Paths.FROM_RIGHT.BACK_SWITCH_PLACE_CUBE1);
//		autonomousCommand = new StuyPulseSwitchAuto(true, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2_CUT_SHORT, Paths.FROM_LEFT.BACK_SWITCH_BACKUP);
//		autonomousCommand = new StuyPulseSwitchAuto(false, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2_CUT_SHORT, Paths.FROM_RIGHT.BACK_SWITCH_BACKUP);
//		autonomousCommand = new TwoCubeScaleAuto(true, Paths.FROM_LEFT.SCALE_LEFT_TRAVEL, Paths.FROM_LEFT.SCALE_LEFT_FINISH, Paths.FROM_LEFT.SCALE_LEFT_SECOND_CUBE_GRAB);
//		autonomousCommand = new TwoCubeScaleAuto(false, Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, Paths.FROM_RIGHT.SCALE_RIGHT_FINISH, Paths.FROM_RIGHT.SCALE_RIGHT_SECOND_CUBE_GRAB);
//		autonomousCommand = new FarScaleAuto(Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL, Paths.FROM_LEFT.SCALE_RIGHT_TRAVEL_2, Paths.FROM_LEFT.SCALE_RIGHT_FINISH);
//		autonomousCommand = new FarScaleAuto(Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2, Paths.FROM_RIGHT.SCALE_LEFT_FINISH);
//		autonomousCommand = new TestAuto();
		
		System.out.println(autonomousCommand.toString());
		SmartDashboard.putString("DB/String 7", autonomousCommand.toString());
		
		// Schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 * 
	 * <p>
	 * Occasionally used to print out / place values on the smart dashboard Ex.
	 * <code> System.out.println(Robot.Elevator.elevator.getSelectedSensorPosition(0)); </code>
	 * is used to determine the height at which the elevator is throughout the
	 * autonomous period
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
//		SmartDashboard.putNumber("Encoder Adverage", (Math.abs(Robot.Drivetrain.getRightDistance() ) + Math.abs( Robot.Drivetrain.getLeftDistance() ) )/2);
//		SmartDashboard.putNumber("left", Drivetrain.encoderLeft.getDistance());
//		SmartDashboard.getNumber("right", Drivetrain.encoderRight.getDistance());
//		SmartDashboard.getNumber("Gyro", Drivetrain.gyro.getAngle());
//		System.out.println("Right " + Robot.Drivetrain.encoderRight.get());
//		System.out.println("Right Distance " + Robot.Drivetrain.getRightDistance());
//		System.out.println("Left Distance " + Robot.Drivetrain.getLeftDistance());
//		System.out.println("Left " + Robot.Drivetrain.encoderLeft.get());
//		System.out.println(Robot.Drivetrain.encoderLeft.getDistance());
//		System.out.println(Robot.Elevator.elevator.getSelectedSensorPosition(0));
//		System.out.println(Robot.oi.driver.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS));
	}

	/**
	 * This function initializes the robot for the beginning of teleop
	 */
	@Override
	public void teleopInit() {
	}

	/**
     * This function is called periodically during operator control.
     * 
     * <p>
	 * Occasionally used to print out / place values on the smart dashboard Ex.
	 * <code> System.out.println(Robot.Elevator.elevator.getSelectedSensorPosition(0)); </code>
	 * is used to determine the height at which the elevator is throughout the
	 * teleoperated period
     */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
//		System.out.println(Robot.oi.driver.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS));
//		System.out.println(Robot.Elevator.elevator.getMotorOutputPercent());
//		SmartDashboard.putNumber("DB/String 5", Robot.drivetrain.getLeftDistance());
//		SmartDashboard.putNumber("DB/String 6", Robot.drivetrain.getRightDistance());
//		System.out.println(Robot.Elevator.elevator.getSelectedSensorPosition(0));
//		System.out.println(Robot.Elevator.eleButton.get());

		// Critical code that saves the elevator from being broken
		// DO NOT REMOVE - IM SERIOUS
		if (Robot.elevator.mightBreak()) {
			Robot.elevator.dontBreak();
		} else {
			Robot.elevator.dontBreak();
		}
	}

	/**
	 * This function is run when the robot enters the disabled period
	 */
	@Override
	public void disabledInit() {
	}

	/**
	 * This function is called periodically while the robot is disabled
	 */
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}