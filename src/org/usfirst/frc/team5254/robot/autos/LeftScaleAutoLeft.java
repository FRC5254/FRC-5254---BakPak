package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetDown;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleAutoLeft extends CommandGroup {

    public LeftScaleAutoLeft() {
    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	addParallel(new AutoIntakeOn(true, 1));
    	addSequential(new ElevatorSetDown());
		addSequential(new AutoTimerWait(.5));//rm
    	addSequential(new AutoDriveToDistance(1 , 210));
		addSequential(new AutoTimerWait(.5));
     	addSequential(new AutoPIDTurn(30));
     	addSequential(new AutoTimerWait(.5));
     	addSequential(new ElevatorSetHeight(RobotMap.TOP_SCALE_HEIGHT));
     	addSequential(new AutoTimerWait(.5));//shorten
     	addSequential(new AutoDriveToDistance(0.75, 65));
     	addSequential(new AutoIntakeOn(false, 1));//shorten
     	addSequential(new AutoDriveToDistance(-0.5, 65));
     	addSequential(new ElevatorSetDown());
    }
}
