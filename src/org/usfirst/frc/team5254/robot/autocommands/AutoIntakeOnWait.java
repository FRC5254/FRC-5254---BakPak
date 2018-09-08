package org.usfirst.frc.team5254.robot.autocommands;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.util.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Intake waits a defined amount of time before activiating
 */
public class AutoIntakeOnWait extends CommandGroup {

    public AutoIntakeOnWait(Direction direction, double wait, double intake) {
    	addSequential(new AutoTimerWait(wait));
        addSequential(new AutoIntakeOn(direction, RobotMap.AUTO_INTAKE, intake));
    }
}
