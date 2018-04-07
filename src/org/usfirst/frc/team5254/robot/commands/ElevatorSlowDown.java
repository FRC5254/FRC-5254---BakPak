package org.usfirst.frc.team5254.robot.commands;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * DO NOT USE THIS COMMAND IN AUTO
 */
public class ElevatorSlowDown extends Command {

	double speed;
	
    public ElevatorSlowDown(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Elevator);
    	
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.Elevator.on(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.Elevator.endSlowDown();
//    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.Elevator.off();
		Robot.Elevator.elevator.setSelectedSensorPosition(0, 0, 10);// zero encoder
//		Robot.Elevator.unratchet();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
