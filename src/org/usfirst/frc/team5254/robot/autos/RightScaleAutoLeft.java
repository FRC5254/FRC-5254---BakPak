package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoDriveToDistance;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleAutoLeft extends CommandGroup {

    public RightScaleAutoLeft() {
//		OLD CODE NO SPLINES (old code was never tested)
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
//		addSequential(new AutoTimerWait(1.0));
//    	addSequential(new AutoDriveToDistance(1, 210));
//     	addSequential(new AutoPIDTurn(-90));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new AutoDriveToDistance(1, 195));
//     	addSequential(new AutoPIDTurn(90));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new AutoDriveToDistance(0.5, 55));
//     	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE,1));
//     	addSequential(new AutoDriveToDistance(0.5, 24));
    	
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1));
    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	addSequential(new RunPath(Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, x -> {
    		if (x < 0.05) return 0.5;
    		else return 0.8;
    	}));
    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
    	addSequential(new RunPath(Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2, y -> {
    		if (y < 0.2) return 0.6;
    		else if (y < 0.85) return 0.8;
    		else return 0.3;
    	}));
    	addParallel(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new AutoTimerWait(0.25));
    	addSequential(new RunPath(Paths.FROM_RIGHT.SCALE_LEFT_FINISH, 0.25));
    	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE, 1));
    	addParallel(new RunPath(Paths.straightLength(30), -0.25));
    	addSequential(new AutoElevatorDownWait(0.5));
    	
    }
}
