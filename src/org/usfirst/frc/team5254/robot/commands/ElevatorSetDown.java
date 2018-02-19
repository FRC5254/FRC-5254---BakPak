package org.usfirst.frc.team5254.robot.commands;

import org.usfirst.frc.team5254.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevatorSetDown extends CommandGroup {

	public ElevatorSetDown() {

		addSequential(new ElevatorRelease());
		addParallel(new ElevatorUnratchet());
		addParallel(new ElevatorSlowDown(RobotMap.ELE_DOWN_SPEED)); // this is the speed the motor runs to power the elevator down at a reasonable speed so we dont break the chain
		end();
	}
}

