package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.autocommands.AutoDriveToDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossBaselineAuto extends CommandGroup {

	public CrossBaselineAuto() {
		addSequential(new AutoDriveToDistance(0.5, 100));
	}
}
