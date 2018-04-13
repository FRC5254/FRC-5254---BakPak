package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Tests intake release in auto
 */
public class TestAuto extends CommandGroup {

	public TestAuto() {
		
//		addSequential(new LeftSwitchAutoLeft());

		addParallel(new AutoIntakeOn(true,RobotMap.AUTO_INTAKE, 1.5));
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
	}
}
