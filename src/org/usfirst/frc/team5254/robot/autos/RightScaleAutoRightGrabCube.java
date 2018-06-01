package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleAutoRightGrabCube extends CommandGroup {

    public RightScaleAutoRightGrabCube() {
    	
    /** Pop cube **/
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 2));
    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
    	
    /** First cube on scale **/
    	addSequential(new RunPath(Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, x -> {
			if (x < .05) return 0.5;
			else return 1.0;
    	}, 0));
    	addParallel(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new RunPath(Paths.FROM_RIGHT.SCALE_RIGHT_FINISH, .25, 0));
    	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE + 0.25, 1));
    	
    /** Elevator down **/
    	addParallel(new AutoElevatorDownWait(0.25));
    	
    /** Pick up second cube **/
    	addSequential(new RunPath(Paths.straightLength(20), -0.5, 0));
    	addSequential(new AutoTimerWait(1));
    	addSequential(new AutoPIDTurn(-85));// HERE
    	addSequential(new AutoTimerWait(0.5));
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 2.5));
    	addSequential(new RunPath(Paths.straightLength(53), x -> {
    		if (x < .70) return 0.75;
			else return 0.3;
    	}, 0));
    }
}
