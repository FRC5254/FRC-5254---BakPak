package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoDriveToDistance;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossBaselineAuto extends CommandGroup {

	public CrossBaselineAuto() {
		addSequential(new ElevatorSetHeight(RobotMap.DRIVE_HEIGHT));
		addSequential(new AutoDriveToDistance(1.0, 100));
	}
}
