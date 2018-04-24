package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class TestAuto extends CommandGroup {

	public TestAuto() {
		// runs left switch auto
//		addSequential(new LeftSwitchAutoLeft());
		
		
		//Tests intake release in auto
//		addParallel(new AutoIntakeOn(true,RobotMap.AUTO_INTAKE, 1.5));
//		addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		
    	addSequential(new AutoPIDTurn(90));
		
	}
}
