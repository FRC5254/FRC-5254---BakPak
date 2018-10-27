package org.usfirst.frc.team5254.robot.commands;

import org.usfirst.frc.team5254.robot.Robot;
import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.util.DriveControl;
import org.usfirst.frc.team5254.robot.util.DriverConfig;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainDriveWithJoystick extends Command {

	public DrivetrainDriveWithJoystick() {
		requires(Robot.Drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		double left = Robot.oi.driver.getRawAxis(Robot.dp.joystick1);
		double right = -Robot.oi.driver.getRawAxis(Robot.dp.joystick2);
		
//		if (Robot.dp.dc == DriveControl.TANK) {
//			Robot.Drivetrain.drive(left, -right);
//		} else {
//			Robot.Drivetrain.drive(left, right);
//		}
		
		if (Robot.oi.driver.getRawAxis(RobotMap.DRIVER_LEFT_TRIGGER_AXIS) > 0.5) {
			Robot.Drivetrain.drive(left, right * 0.5);
    	} else {
    		Robot.Drivetrain.drive(left, right);
    	}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.Drivetrain.drive(0,0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
