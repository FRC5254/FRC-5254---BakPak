package org.usfirst.frc.team5254.robot.commands;

import org.usfirst.frc.team5254.robot.Robot;
import org.usfirst.frc.team5254.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * TODO delete this command its unused
 */
public class ElevatorJoystickControl extends Command {

	public ElevatorJoystickControl() {
		requires(Robot.Elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.Elevator.initElevator();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// OG
//		if (Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS) < 0) {
//			Robot.Elevator.on(Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS) * 0.75);
//		} else {
//			Robot.Elevator.on(Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS));
//		}
		
		// Option #1
		if (Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS) < 0 || Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_LEFT_TRIGGER_AXIS) > 0) {
			Robot.Elevator.on(Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS) * 0.75);
			Robot.Elevator.on(-Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_LEFT_TRIGGER_AXIS) * 0.75);//TODO test and tune these slow controls
		} else {
			Robot.Elevator.on(Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS));
			Robot.Elevator.on(Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_RIGHT_TRIGGER_AXIS) * 0.75);
		}
		
		// Option #2
//		if (Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS) < 0 || Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_LEFT_TRIGGER_AXIS) > 0) {
//			Robot.Elevator.on(Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS) * 0.75);
//			Robot.Elevator.on(RobotMap.ELE_HOLD_SPEED - (Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_LEFT_TRIGGER_AXIS) * 0.5));//TODO test and tune these slow controls
//		} else {
//			Robot.Elevator.on(Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS));
//			Robot.Elevator.on(RobotMap.ELE_HOLD_SPEED + (Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_RIGHT_TRIGGER_AXIS) * 0.5));
//		}
//		
		// Option #3 **
//		if (Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_LEFT_TRIGGER_AXIS) > 0) {
//			Robot.Elevator.on(RobotMap.ELE_HOLD_SPEED - 0.2);//TODO test and tune these slow controls
//		} else if (Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_RIGHT_TRIGGER_AXIS) > 0) {
//			Robot.Elevator.on(RobotMap.ELE_HOLD_SPEED + 0.2);
//		} else if (Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS) < 0){
//			Robot.Elevator.on(Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS) * 0.75);
//		} else {
//			Robot.Elevator.on(Robot.oi.operator.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS));
//		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.Elevator.off();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
