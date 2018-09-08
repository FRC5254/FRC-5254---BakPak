package org.usfirst.frc.team5254.robot.autocommands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class AutoTimedElevatorRaise extends Command {

	Timer timer = new Timer();
	double time;

	public AutoTimedElevatorRaise(double time) {
		requires(Robot.Elevator);

		this.time = time;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.Elevator.setSpeed(-1);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return timer.get() > time;
	}

	// Called once after isFinished returns true
	protected void end() {
		timer.stop();
		Robot.Elevator.setSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
