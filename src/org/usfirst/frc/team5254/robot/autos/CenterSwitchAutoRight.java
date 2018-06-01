package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.autocommands.pathing.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;
import org.usfirst.frc.team5254.robot.commands.IntakeOn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchAutoRight extends CommandGroup {

	public CenterSwitchAutoRight() {
		
//		OLD CODE NO SPLINES
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
//		addSequential(new AutoTimerWait(1.0));
//		addSequential(new AutoDriveToDistance(1.0, 10));
//		addSequential(new AutoTimerWait(.5));
//		addSequential(new AutoPIDTurn(25));
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//		addSequential(new AutoTimerWait(.5));
//		addSequential(new AutoDriveToDistance(0.75, 85));
//		addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
//		addSequential(new AutoTimerWait(.5));
//		addSequential(new AutoPIDTurn(-25));
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//		addSequential(new AutoTimerWait(.5));
//		addSequential(new AutoTimedDrive(0.5, .75));
//		addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 3));
//		addSequential(new AutoDriveToDistance(-0.75, 24));
//		addSequential(new AutoElevatorSetDown());
	
	/** Pop cube **/
		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addSequential(new AutoTimerWait(0.25));
		
	/** Drives to switch **/
		addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		addSequential(new RunPath(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, x -> {
			if (x < 0.20) return 0.5;
			if (x < 0.75) return 0.85;
			else return 0.4;
		}, 0));
		
	/** Places cube **/
		addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 3));
		
	/** Lowers elevator **/
		addSequential(new RunPath(Paths.straightLength(24), -0.75, 0));
		addSequential(new AutoElevatorSetDown());
	}
}
