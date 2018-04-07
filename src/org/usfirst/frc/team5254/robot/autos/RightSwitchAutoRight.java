package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitchAutoRight extends CommandGroup {

    public RightSwitchAutoRight() {
    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addParallel(new AutoIntakeOn(true, 1.5));
		addSequential(new AutoTimerWait(1.0));
		addSequential(new AutoDriveToDistance(1, 128));
		addSequential(new AutoPIDTurn(-90));
		addParallel(new AutoIntakeOn(true, 0.5));
		addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		addSequential(new AutoTimerWait(0.5));
		addSequential(new AutoTimedDrive(1, 1));
		addSequential(new AutoIntakeOn(false, 15));
    }
}
