package org.usfirst.frc.team5254.robot.autos;


import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;
import org.usfirst.frc.team5254.robot.util.Direction;
import org.usfirst.frc.team5254.robot.util.StartingSide;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Testing random auto code snippets/full autos sometimes
 */
public class TestAuto extends CommandGroup {

	public TestAuto() {
		super("TestAuto");

		/** runs a wrangler auto lined up on left **/
//		addSequential(new WranglerSwitchAuto(StartingSide.LEFT, Paths.FROM_LEFT.BACK_SWITCH_PLACE_CUBE1));
		
		
		/** Tests intake release in auto **/
//		addParallel(new AutoIntakeOn(Direction.INTAKE,RobotMap.AUTO_INTAKE, 1.5));
//		addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		
		/** For PID tuning **/
//    	addSequential(new AutoPIDTurn(90));
		
		 /** Pop cube **/
    	addParallel(new AutoIntakeOn(Direction.INTAKE, RobotMap.AUTO_INTAKE, 2));
    	addParallel(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
	}
}
