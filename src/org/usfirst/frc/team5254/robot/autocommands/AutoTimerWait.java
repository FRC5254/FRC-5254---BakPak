package org.usfirst.frc.team5254.robot.autocommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTimerWait extends Command {

	double time;
	Timer timer = new Timer();

	public AutoTimerWait(double time) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.time = time;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return timer.get() > time;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
