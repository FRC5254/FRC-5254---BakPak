package org.usfirst.frc.team5254.robot.commands;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorRelease extends Command {

    public ElevatorRelease() {
    	requires(Robot.Elevator);
 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.Elevator.Unrachet();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.Elevator.Unrachet();
    	Robot.Elevator.setToHeight(Robot.Elevator.elevator.getSelectedSensorPosition(0) + 1000);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.Elevator.endSetHeight();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.Elevator.Unrachet();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
