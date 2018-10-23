package org.usfirst.frc.team5254.robot.commands;

import org.usfirst.frc.team5254.robot.Robot;
import org.usfirst.frc.team5254.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeClose extends Command {

    public IntakeClose() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    		Robot.Intake.close();
//    		Robot.Intake.setSpeed(0);
//    		
//    		if (Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_LEFT_TRIGGER_AXIS) > 0.8) {
//        		Robot.Intake.open();
//        	} else {
//        		Robot.Intake.close();
//        	}
    		
    		if (Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_RIGHT_TRIGGER_AXIS) > 0.8) {
        		Robot.Intake.clamp();
        	} else {
        		Robot.Intake.close();
        	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
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
