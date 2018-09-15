package org.usfirst.frc.team5254.robot.autos;


import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;
import org.usfirst.frc.team5254.robot.commands.IntakeSetSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class TestAuto extends CommandGroup {

	public TestAuto() {
		super("TestAuto");

		// runs left switch auto
//		addSequential(new WranglerSwitchAuto(true, Paths.FROM_LEFT.BACK_SWITCH_PLACE, Paths.FROM_LEFT.BACK_SWITCH_SECOND_CUBE));
//		addSequential(new RightNullScaleAutoRight());
//		addSequential(new LeftScaleAutoLeftTwoCubes());
		
		
		//Tests intake release in auto
//		addParallel(new AutoIntakeOn(true,RobotMap.AUTO_INTAKE, 1.5));
//		addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		
//    	addSequential(new AutoPIDTurn(90));
		
//		addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE - 0.25, 1));
//		
//		 /** Pop cube **/
    	addParallel(new IntakeSetSpeed(RobotMap.AUTO_INTAKE), 2);
    	addParallel(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
//    	
//    /** First cube on scale **/
//    	addSequential(new RunPath(Paths.FROM_LEFT.SCALE_LEFT_TRAVEL, x -> {
//			if (x < .05) return 0.5;
//			else return 1.0;
//    	}, 0));
//    	addParallel(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
//    	addSequential(new RunPath(Paths.FROM_LEFT.SCALE_LEFT_FINISH, 0.5, 0));
//    	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE - 0.25, 1));
//    	
//    /** Elevator down **/
//    	addSequential(new AutoElevatorDownWait(0.01));
//    	addSequential(new RunPath(Paths.straightLength(20), x -> {
//    		if (x < .70) return 1.0;
//			else return 0.3;
//    	}, 0));
//    	
//    /** Pick up second cube **/
//    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 6));
//    	addSequential(new RunPath(Paths.FROM_LEFT.SCALE_LEFT_SECOND_CUBE_GRAB, 0.6, 0), 4);
//    	addSequential(new RunPath(Paths.straightLength(20), -0.3, 0));
    	
//    	addSequential(new RunPath(Paths.FROM_LEFT.SCALE_LEFT_SECOND_CUBE_PLACE, 0.25));
	}
}
