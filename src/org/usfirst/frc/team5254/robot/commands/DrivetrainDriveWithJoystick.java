package org.usfirst.frc.team5254.robot.commands;

import org.usfirst.frc.team5254.robot.Robot;
import org.usfirst.frc.team5254.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Allows the driver to arcade drive the robot with the joysticks
 */
public class DrivetrainDriveWithJoystick extends Command {

	public DrivetrainDriveWithJoystick() {
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (DriverStation.getInstance().isAutonomous()) {
			Robot.drivetrain.shiftDown();
		} else {
			Robot.drivetrain.shiftDown();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		double left = Robot.oi.driver.getRawAxis(RobotMap.DRIVER_THROTTLE_AXIS);
		double right = -Robot.oi.driver.getRawAxis(RobotMap.DRIVER_TURN_AXIS);
		
		Robot.drivetrain.drive(left, right);
		
		// speed acceleration code
//		Robot.Drivetrain.drive(Math.abs(left)*left,
//				Math.abs(right)*right); // When you invert these make them inverted in slow turn
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
