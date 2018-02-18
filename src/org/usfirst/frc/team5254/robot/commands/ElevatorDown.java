package org.usfirst.frc.team5254.robot.commands;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorDown extends Command {// TODO is this still needed
	public ElevatorDown() {
		requires(Robot.Elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.Elevator.unrachet();
		Robot.Elevator.elevatorDown();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.Elevator.elevatorDownEnd();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.Elevator.rachet();
		Robot.Elevator.off();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
