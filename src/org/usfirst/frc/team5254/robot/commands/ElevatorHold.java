package org.usfirst.frc.team5254.robot.commands;

import org.usfirst.frc.team5254.robot.Robot;
import org.usfirst.frc.team5254.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorHold extends Command {

    public ElevatorHold() {
    	requires (Robot.Elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (Math.abs(Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS)) < 0.1){ //If the Joystick is not being used
  
    		if ((Robot.Elevator.eleButton.get() == false) || (Math.abs(Robot.Elevator.elevator.getSelectedSensorPosition(0)) < 1000)) { //If the button is pressed
  			
    			Robot.Elevator.off(); //Elevator off
    			
    		} else { //If the button is not pressed
    			
    			Robot.Elevator.on(RobotMap.ELE_HOLD_SPEED); //Hold the elevator in place
    		}
	
    	} else { //If the joystick is being used		
    
    			Robot.Elevator.on(Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS)); //the joystick controls the elevator
    		
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
