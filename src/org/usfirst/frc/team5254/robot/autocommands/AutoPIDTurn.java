package org.usfirst.frc.team5254.robot.autocommands;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPIDTurn extends Command {

	double angle;

	public AutoPIDTurn(double angle) {
		requires(Robot.Drivetrain);

		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.Drivetrain.PIDTurnInit(angle);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.Drivetrain.PIDTurnIsFinished();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.Drivetrain.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
