package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetDown;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleAutoRight extends CommandGroup {

    public LeftScaleAutoRight() {
		addSequential(new ElevatorSetHeight(RobotMap.DRIVE_HEIGHT));
		addSequential(new AutoTimerWait(.5));
    	addSequential(new AutoDriveToDistance(1, 210));
     	addSequential(new AutoPIDTurn(90));
     	addSequential(new AutoTimerWait(.5));
     	addSequential(new AutoDriveToDistance(1, 195));
     	addSequential(new AutoPIDTurn(90));
     	addSequential(new AutoTimerWait(.5));
     	addSequential(new ElevatorSetHeight(RobotMap.TOP_SCALE_HEIGHT));
     	addSequential(new AutoTimerWait(.5));
     	addSequential(new AutoDriveToDistance(0.5, 55));
     	addSequential(new AutoIntakeOn(false, 1));
     	addSequential(new AutoDriveToDistance(0.5, 24));
     	addSequential(new ElevatorSetDown());
    }
}
