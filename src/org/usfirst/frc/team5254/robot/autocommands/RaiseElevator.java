package org.usfirst.frc.team5254.robot.autocommands;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseElevator extends Command {
	
	double Throttle;
	double Time;

    public RaiseElevator(double Throttle, double Time) {
    	
    	this.Throttle = Throttle;
    	this.Time = Time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.Elevator.autoTimedRaiseInit(Throttle, Time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.Elevator.autoTimedRaise(Throttle, Time);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.Elevator.StopLadder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
