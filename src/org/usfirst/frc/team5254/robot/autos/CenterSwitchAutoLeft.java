package org.usfirst.frc.team5254.robot.autos;


import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.pathing.*;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Not tested
 */
public class CenterSwitchAutoLeft extends CommandGroup {

	public CenterSwitchAutoLeft() {
		
//		OLD CODE, NO SPLINES (TODO look at parallels here are they in the wrong place? should the waits exist?)
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
//		addSequential(new AutoTimerWait(1.0));
//		addSequential(new AutoDriveToDistance(1.0, 10));
//		addSequential(new AutoTimerWait(.5));
//		addSequential(new AutoPIDTurn(-35));
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//		addSequential(new AutoTimerWait(.5));
//		addSequential(new AutoDriveToDistance(0.75, 95));
//		addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
//		addSequential(new AutoTimerWait(.5));
//		addSequential(new AutoPIDTurn(35));		
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//		addSequential(new AutoTimerWait(.5));
//		addSequential(new AutoTimedDrive(0.5, 1));
//		addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 3));
//		addSequential(new AutoDriveToDistance(-0.75, 24));
//		addSequential(new AutoElevatorSetDown());
		
	/** Robot approaches the switch on the left side and shoots cube in **/
		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addSequential(new AutoTimerWait(0.25));
		addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		addSequential(new RunPath(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, x -> {
			if (x < 0.20) return 0.5;
			if (x < 0.75) return 0.7;
			else return 0.4;
		}), 6);
		addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 3));
		addSequential(new RunPath(Paths.straightLength(24), -0.75));
		addSequential(new AutoElevatorSetDown());
	}
}
