package org.usfirst.frc.team5254.robot.autocommands;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSwitchHeightWait extends CommandGroup {

    public AutoSwitchHeightWait(double time) {
    	addSequential(new AutoTimerWait(time));
    	addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
    }
}
