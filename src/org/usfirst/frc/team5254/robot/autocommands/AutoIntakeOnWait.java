package org.usfirst.frc.team5254.robot.autocommands;

import org.usfirst.frc.team5254.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoIntakeOnWait extends CommandGroup {

    public AutoIntakeOnWait(double wait, double intake) {
    	addSequential(new AutoTimerWait(wait));
        addSequential(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, intake));
    }
}
