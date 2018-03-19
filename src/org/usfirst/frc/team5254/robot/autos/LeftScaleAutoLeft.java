package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetDown;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;
import org.usfirst.frc.team5254.robot.commands.IntakeOn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleAutoLeft extends CommandGroup {

    public LeftScaleAutoLeft() {
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addParallel(new AutoIntakeOn(true, 1));
		addSequential(new AutoTimerWait(.5));
    	addSequential(new AutoDriveToDistance(1 , 210));
		addSequential(new AutoTimerWait(.5));
     	addSequential(new AutoPIDTurn(30));
     	addParallel(new AutoIntakeOn(true, 0.5));
     	addSequential(new AutoTimerWait(.5));
     	addSequential(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
     	addSequential(new AutoTimerWait(.5));
     	addSequential(new AutoDriveToDistance(0.75, 65));
     	addSequential(new AutoIntakeOn(false, 1));
     	addSequential(new AutoDriveToDistance(-0.5, 65));
    	
//    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
//		addParallel(new AutoIntakeOn(true, 1.5));
//		addSequential(new AutoTimerWait(1.0));
//    	addSequential(new AutoDriveToDistance(1 , 295));//285
//		addSequential(new AutoTimerWait(.5));
//     	addSequential(new AutoPIDTurn(90));
//     	addParallel(new AutoIntakeOn(true, 0.5));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new AutoDriveToDistance(0.75, 25));
//     	addSequential(new AutoIntakeOn(false, 3));
//     	addSequential(new AutoDriveToDistance(-0.5, 25));
    }
}
