package org.usfirst.frc.team5254.robot;

import org.usfirst.frc.team5254.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5254.robot.subsystems.CubeMech;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;


public class Robot extends IterativeRobot {

	static Timer timer = new Timer();

	Command autonomousCommand;

	public static OI oi;
	public static Drivetrain Drivetrain = new Drivetrain();
	public static CubeMech CubeMech = new CubeMech();

	public static PowerDistributionPanel pdp = new PowerDistributionPanel();

	// Auto modes

	// Defining the autonomous commands into a string to be listed on the dashboard

	@Override
	public void robotInit() {
		oi = new OI();

		// Send auto modes

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

		timer.reset();
		timer.start();


		// Schedule the autonomous command (example)
		if (autonomousCommand != null) {
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