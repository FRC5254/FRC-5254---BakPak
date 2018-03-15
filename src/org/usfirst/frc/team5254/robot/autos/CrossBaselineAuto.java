package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoDriveToDistance;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetDown;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossBaselineAuto extends CommandGroup {

	public CrossBaselineAuto() {
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	addParallel(new AutoIntakeOn(true, 1));
    	addSequential(new ElevatorSetDown());
		addSequential(new AutoDriveToDistance(1.0, 100));
	}
}
