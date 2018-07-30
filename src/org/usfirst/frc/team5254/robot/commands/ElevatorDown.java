package org.usfirst.frc.team5254.robot.commands;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * DO NOT USE THIS COMMAND IN AUTO
 */
public class ElevatorDown extends Command {

	double speed;
	
    public ElevatorDown(double speed) {
    	requires(Robot.elevator);
    	
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.on(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevator.endSlowDown();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.off();
		Robot.elevator.elevator.setSelectedSensorPosition(0, 0, 10);// zero encoder
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
