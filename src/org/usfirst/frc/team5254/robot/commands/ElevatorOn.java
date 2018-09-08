package org.usfirst.frc.team5254.robot.commands;

import org.usfirst.frc.team5254.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorOn extends Command {

	double power;

	public ElevatorOn(double power) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.power = power;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.Elevator.eleButton.get() == false) { // if the button is pressed
			if (power > 0) { // and if elevator is going down
				Robot.Elevator.setSpeed( 0.0);// stop
				Robot.Elevator.elevator.setSelectedSensorPosition(0, 0, 10);// zero encoder
			} 
		} else {// if button isnt pressed
			Robot.Elevator.setSpeed(power);// motor at set speed
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
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
