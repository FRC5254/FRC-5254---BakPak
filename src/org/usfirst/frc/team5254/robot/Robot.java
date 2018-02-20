package org.usfirst.frc.team5254.robot;

import org.usfirst.frc.team5254.robot.subsystems.*;
import org.usfirst.frc.team5254.robot.autos.*;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Robot extends IterativeRobot {

	NetworkTable table;
	String gameData;
	Boolean allianceColorRed;
	int allianceNumber;

	public static OI oi;
	public static Drivetrain Drivetrain = new Drivetrain();
	public static Intake Intake = new Intake();
	public static Elevator Elevator = new Elevator();
	public static Climber Climber = new Climber();

	public static PowerDistributionPanel pdp = new PowerDistributionPanel();

	// Auto modes
	private final String NothingAuto = "Do Nothing";
	private final String CrossAutoLine = "Cross Autoline";
	private final String SwitchAuto = "Switch Auto";
	private final String TestAuto = "Test Auto";

	private final String[] AutoModes = { NothingAuto, CrossAutoLine, SwitchAuto, TestAuto, };

	Command autonomousCommand;
	// Defining the autonomous commands into a string to be listed on the dashboard

	@Override
	public void robotInit() {
		oi = new OI();

		// Send auto modes
		SmartDashboard.putStringArray("Auto List", AutoModes);
		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		NetworkTable table = inst.getTable("SmartDashboard");

		// Initializing cameras
		CameraServer.getInstance().startAutomaticCapture(1);
		CameraServer.getInstance().startAutomaticCapture(0);
	}

	@Override
	public void autonomousInit() {

		String autoSelected = SmartDashboard.getString("Auto Selector", SwitchAuto);
		// TODO do the scale auto outline and make more logic in here to make it cooler
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		allianceColorRed = DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red;
		allianceNumber = DriverStation.getInstance().getLocation();
		
		
		if (gameData.charAt(0) == 'L') {
			
			
			if (gameData.charAt(1)== 'L') {
				
				switch (autoSelected) {

				case CrossAutoLine:
					autonomousCommand = new CrossBaselineAuto();
					break;

				case SwitchAuto:
					autonomousCommand = new PlaceOnSwitchLeftAuto();
					break;

				case TestAuto:
					autonomousCommand = new TestAuto();
					break;

				default:
					autonomousCommand = new NothingAuto();
					break;
				}	
			} else {
				
				switch (autoSelected) {

				case CrossAutoLine:
					autonomousCommand = new CrossBaselineAuto();
					break;

				case SwitchAuto:
					autonomousCommand = new PlaceOnSwitchLeftAuto();
					break;

				case TestAuto:
					autonomousCommand = new TestAuto();
					break;

				default:
					autonomousCommand = new NothingAuto();
					break;
				}	
			}
			
		} else {
				
			if (gameData.charAt(1) == 'L') {
				switch (autoSelected) {
				case CrossAutoLine:
					autonomousCommand = new CrossBaselineAuto();
					break;
	
				case SwitchAuto:
					autonomousCommand = new PlaceOnSwitchRightAuto();
					break;
	
				case TestAuto:
					autonomousCommand = new TestAuto();
					break;
	
				default:
					autonomousCommand = new NothingAuto();
					break;
				}
			
			} else {
				switch (autoSelected) {
				case CrossAutoLine:
					autonomousCommand = new CrossBaselineAuto();
					break;
	
				case SwitchAuto:
					autonomousCommand = new PlaceOnSwitchRightAuto();
					break;
	
				case TestAuto:
					autonomousCommand = new TestAuto();
					break;
	
				default:
					autonomousCommand = new NothingAuto();
					break;
				}
			}	
		}

		System.out.format("Auto: %s '%s' %n", m_ds.getAlliance(), autoSelected);

		// Schedule the autonomous command (example)
		if (autonomousCommand != null) {
			SmartDashboard.putString("DB/String 0", autoSelected);
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		System.out.println(Drivetrain.encoder.get());
	}

	@Override
	public void teleopInit() {

		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		System.out.println(Robot.oi.driver.getRawAxis(2));
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