package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoDriveToDistance;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetDown;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleAutoRight extends CommandGroup {

    public RightScaleAutoRight() {
    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	addParallel(new AutoIntakeOn(true, 1.5));
		addSequential(new AutoTimerWait(1.0));
    	addSequential(new AutoDriveToDistance(1 , 210));
		addSequential(new AutoTimerWait(.5));
     	addSequential(new AutoPIDTurn(-30));
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
//     	addSequential(new AutoPIDTurn(-90));
//     	addParallel(new AutoIntakeOn(true, 0.5));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new AutoDriveToDistance(0.75, 25));
//     	addSequential(new AutoIntakeOn(false, 3));
//     	addSequential(new AutoDriveToDistance(-0.5, 25));
    }
}
