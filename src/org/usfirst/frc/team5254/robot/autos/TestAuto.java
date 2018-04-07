package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetDown;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAuto extends CommandGroup {

	public TestAuto() {
//		addSequential(new LeftSwitchAutoLeft());
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addParallel(new AutoIntakeOn(true, 1.5));
	}
}
