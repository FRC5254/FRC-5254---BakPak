package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.autocommands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAuto extends CommandGroup {

	public TestAuto() {
		addSequential(new AutoPIDTurn(25));
	}
}
