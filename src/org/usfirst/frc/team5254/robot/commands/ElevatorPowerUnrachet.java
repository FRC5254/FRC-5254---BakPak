package org.usfirst.frc.team5254.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevatorPowerUnrachet extends CommandGroup {

	Timer timer= new Timer();
	
    public ElevatorPowerUnrachet() {
        addSequential(new ElevatorUnrachet());
        addParallel(new ElevatorOn(-0.25));
        
    }
}
