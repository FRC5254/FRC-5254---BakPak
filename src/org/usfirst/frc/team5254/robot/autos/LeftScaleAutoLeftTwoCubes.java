package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoDriveToDistance;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorSetDown;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOnWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.AutoSwitchHeightWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleAutoLeftTwoCubes extends CommandGroup {

    public LeftScaleAutoLeftTwoCubes() {
    	
    /** Pop cube **/
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 2));
    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
    	
    /** First cube on scale **/
    	addSequential(new RunPath(Paths.FROM_LEFT.SCALE_LEFT_TRAVEL, x -> {
			if (x < .05) return 0.5;
			else return 1.0;
    	}));
    	addParallel(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new RunPath(Paths.FROM_LEFT.SCALE_LEFT_FINISH, 0.5));
    	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE + 0.25, 1));
    	
    /** Elevator down **/
    	addParallel(new AutoElevatorDownWait(0.25));
    	
    /** Pick up second cube **/
    	
//    	addSequential(new RunPath(Paths.FROM_LEFT.SCALE_LEFT_SECOND_CUBE, 0.6));
    	addSequential(new RunPath(Paths.straightLength(10), -0.5)); // make longer t o
    	addSequential(new AutoTimerWait(1));
    	addSequential(new AutoPIDTurn(120));
    	addSequential(new AutoTimerWait(0.5));
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 4));
    	addSequential(new RunPath(Paths.straightLength(60), x -> {
    		if (x < .70) return 1.0;
			else return 0.3;
    	}));
    	
    /** Place second cube on scale **/
    	addParallel(new AutoSwitchHeightWait(0.75));
    	addSequential(new RunPath(Paths.straightLength(20), -0.5));
    	addSequential(new AutoPIDTurn(-130));
    	addSequential(new AutoTimerWait(0.5));
    	addSequential(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new RunPath(Paths.straightLength(27), x -> {
    		if (x < .70) return 0.5;
			else return 0.5;
    	}));
    	addParallel(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE - 0.15, 2));
    	
    /** Elevator down **/
    	addParallel(new AutoElevatorDownWait(1));
    	addSequential(new RunPath(Paths.straightLength(44), -0.35));
    	
    }
}
