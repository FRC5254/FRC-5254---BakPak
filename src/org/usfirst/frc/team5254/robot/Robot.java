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
	
	public final String SmartDashboard = "SmartDashboard";
	
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

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */

	@Override
	public void autonomousInit() {
		
		String autoSelected = SmartDashboard.getString("Auto Selector", NothingAuto);
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		timer.reset();
		timer.start();

		System.out.format("Auto: %s '%s' %n", m_ds.getAlliance(), autoSelected);
		
		switch (autoSelected) {
		
		default:
			autonomousCommand = new NothingAuto();
			break;

		case CrossAutoLine:
			autonomousCommand = new CrossAutoLine();
			break;
		}
		
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
		/**
		 * This makes sure that the autonomous stops running when teleop starts running.
		 * If you want the autonomous to continue until interrupted by another command,
		 * remove this line or comment it out.
		 */

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