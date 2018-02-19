package org.usfirst.frc.team5254.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevatorSetDown extends CommandGroup {

	public ElevatorSetDown() {

		addSequential(new ElevatorRelease());
		addParallel(new ElevatorUnrachet());// start --> latest
		addParallel(new ElevatorSlowDown(0.14)); // was.1, -.14 (went backwards), 
		end();
	}
}

