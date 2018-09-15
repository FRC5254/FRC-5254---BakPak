package org.usfirst.frc.team5254.robot.commands;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorSetHeight extends Command {

	int ticks;

	public ElevatorSetHeight(int ticks) {
		requires(Robot.Elevator);

		this.ticks = ticks;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		Robot.Elevator.setToHeight(ticks);
		
		if (ticks > Math.abs(Robot.Elevator.elevator.getSelectedSensorPosition(0))) { // if i'm not at set position
			Robot.Elevator.setSpeed(1);//elevator go up 100% power
		} else { // at/past set position
			Robot.Elevator.setSpeed(0.0);// sets elevator motor to 0% power
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.Elevator.endSetHeight(ticks);
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
