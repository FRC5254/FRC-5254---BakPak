package org.usfirst.frc.team5254.robot.commands;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorRelease extends Command {

	int height;
	
	public ElevatorRelease() {
		requires(Robot.Elevator);
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		height = Math.abs(Robot.Elevator.elevator.getSelectedSensorPosition(0)) + 500;// DONT FORGET TO CHANGE ALL ELEVATOR MC COMMANDS DUBASS
		Robot.Elevator.unratchet();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.Elevator.unratchet();
		Robot.Elevator.setToHeight(height);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.Elevator.endSetHeight();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.Elevator.off();
		Robot.Elevator.unratchet();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
