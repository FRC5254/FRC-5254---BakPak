package org.usfirst.frc.team5254.robot.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Waits to lower the elevator for the time set
 */
public class AutoElevatorDownWait extends CommandGroup {

    public AutoElevatorDownWait(double time) {
    	addSequential(new AutoTimerWait(time));
    	addSequential(new AutoElevatorSetDown());
    }
}
