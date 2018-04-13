package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.autocommands.pathing.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchAutoLeft extends CommandGroup {

    public LeftSwitchAutoLeft() {
//		OLD CODE NO SPLINES
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
//		addSequential(new AutoTimerWait(1.0));
//		addSequential(new AutoDriveToDistance(1, 128));
//		addSequential(new AutoPIDTurn(90));
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 0.5));
//		addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
//		addSequential(new AutoTimerWait(0.5));
//		addSequential(new AutoTimedDrive(1, 1));
//		addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 15));
    	
//    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	addSequential(new RunPath(Paths.FROM_LEFT.SWITCH_LEFT_TRAVEL, x -> {
    		if (x < 0.05) return 0.5;
    		else return 0.8;
    	}));
//    	addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
    	addSequential(new RunPath(Paths.FROM_LEFT.SWITCH_LEFT_FINISH, 0.4));
//    	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 2));
    	addSequential(new RunPath(Paths.straightLength(30), -0.3));
//    	addSequential(new AutoElevatorSetDown());
    }
}
