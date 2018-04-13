package org.usfirst.frc.team5254.robot.autocommands;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoIntakeOn extends Command {

	boolean direction;
	double time;
	double speed;
	Timer timer = new Timer();

	public AutoIntakeOn(boolean direction, double speed, double time) {
		requires(Robot.Intake);

		this.direction = direction;
		this.time = time;
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (direction == true) {
			Robot.Intake.intakeLeftFlywheels.set(-speed);
			Robot.Intake.intakeRightFlywheels.set(-speed + .10);
		} else {
			Robot.Intake.intakeLeftFlywheels.set(speed);
			Robot.Intake.intakeRightFlywheels.set(speed);
			
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return timer.get() > time;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.Intake.off();
		timer.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
