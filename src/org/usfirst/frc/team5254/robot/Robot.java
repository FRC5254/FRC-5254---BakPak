package org.usfirst.frc.team5254.robot;

import org.usfirst.frc.team5254.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5254.robot.subsystems.CubeMech;
import org.usfirst.frc.team5254.robot.subsystems.Elevator;
import org.usfirst.frc.team5254.robot.autos.*;

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

	static Timer timer = new Timer();
	
	NetworkTable table;
	String gameData;
	public static OI oi;
	public static Drivetrain Drivetrain = new Drivetrain();
	public static CubeMech CubeMech = new CubeMech();
	public static Elevator Elevator = new Elevator();

	public static PowerDistributionPanel pdp = new PowerDistributionPanel();

	// Auto modes
	private final String NothingAuto = "Do Nothing";
	private final String CrossAutoLine = "Cross Autoline";
	private final String SwitchAuto = "Switch Auto";
	private final String TestAuto = "Test Auto";
	
	private final String[] AutoModes = {
			NothingAuto, CrossAutoLine, SwitchAuto, TestAuto,
	};
	
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
	public void disabledInit() {

	}

	public static void stopTimer() {
		System.out.println(timer.get());
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		
		String autoSelected = SmartDashboard.getString("Auto Selector", SwitchAuto);
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		gameData = ("R");
		if(gameData.charAt(0) == 'L')
		{
			switch (autoSelected) {

			case CrossAutoLine:
				autonomousCommand = new CrossAutoLine();
				break;
				
			case SwitchAuto:
				autonomousCommand = new PlaceOnSwitchLeft();
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
				autonomousCommand = new CrossAutoLine();
				break;
				
			case SwitchAuto:
				autonomousCommand = new PlaceOnSwitchRight();
				break;
				
			case TestAuto:
				autonomousCommand = new TestAuto();
				break;
				
			default:
				autonomousCommand = new NothingAuto();
				break;
			}
		}
		
		timer.reset();
		timer.start();

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
	}

	@Override
	public void teleopInit() {
		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}