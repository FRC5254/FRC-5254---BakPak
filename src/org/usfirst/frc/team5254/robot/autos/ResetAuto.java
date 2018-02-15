package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.TimedElevatorRaise;
import org.usfirst.frc.team5254.robot.commands.ElevatorUnrachet;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ResetAuto extends CommandGroup {

    public ResetAuto() {
    	
    	addSequential(new ElevatorUnrachet());
    	addParallel(new AutoTimerWait(0.5));
    	addParallel(new TimedElevatorRaise(5));
    }
}
