package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetDown;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchAutoLeft extends CommandGroup {

    public LeftSwitchAutoLeft() {
    	addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
    	addParallel(new AutoIntakeOn(true, 1));
    	addSequential(new AutoDriveToDistance(1, 128));
    	addSequential(new AutoPIDTurn(90));
    	addSequential(new AutoTimedDrive(1, 2));
    }
}
