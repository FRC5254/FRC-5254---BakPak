package org.usfirst.frc.team5254.robot.autocommands;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.ElevatorDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoElevatorSetDown extends CommandGroup {

	public AutoElevatorSetDown() {
		addParallel(new ElevatorDown(RobotMap.ELE_DOWN_SPEED)); // this is the speed the motor runs to power the elevator down at a reasonable speed so we dont break the chain
		end();
	}
}

