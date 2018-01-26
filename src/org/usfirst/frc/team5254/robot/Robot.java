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
	private final String SwitchAuto = "SwitchAuto";
	
	private final String[] AutoModes = {
			NothingAuto, CrossAutoLine
	};
	
	Command autonomousCommand;
	// Defining the autonomous commands into a string to be listed on the dashboard

	@Override
	public void robotInit() {
		oi = new OI();
		
		// Send auto modes
		SmartDashboard.putStringArray("Auto List", AutoModes);

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
		
		String autoSelected = SmartDashboard.getString("Auto Selector", NothingAuto);
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L')
		{
			switch (autoSelected) {
			
			default:
				autonomousCommand = new NothingAuto();
				break;

			case CrossAutoLine:
				autonomousCommand = new CrossAutoLine();
				break;
				
			case SwitchAuto:
				autonomousCommand = new PlaceOnSwitchLeft();
				break;
			}
			
		} else {
			
			switch (autoSelected) {
			
			default:
				autonomousCommand = new NothingAuto();
				break;

			case CrossAutoLine:
				autonomousCommand = new CrossAutoLine();
				break;
				
			case SwitchAuto:
				autonomousCommand = new PlaceOnSwitchRight();
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