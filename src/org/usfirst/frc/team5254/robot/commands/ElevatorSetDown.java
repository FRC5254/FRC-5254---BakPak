package org.usfirst.frc.team5254.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevatorSetDown extends CommandGroup {

	public ElevatorSetDown() {

		addSequential(new ElevatorRelease());
		addSequential(new ElevatorDown());
		end();

		// TODO work?
	}
}
