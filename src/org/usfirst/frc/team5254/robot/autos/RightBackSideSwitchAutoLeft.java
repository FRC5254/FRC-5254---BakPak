package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorSetDown;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath2;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightBackSideSwitchAutoLeft extends CommandGroup {

    public RightBackSideSwitchAutoLeft() {
    	
    /** Pop cube **/
     	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1));
    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	
    /** Drive over to other side of field **/
    	addSequential(new RunPath(Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL, x -> {
    		if (x < 0.05) return 0.5;
    		else return 1.0;
    	}, 0));
    	addSequential(new RunPath(Paths.FROM_RIGHT.SCALE_LEFT_TRAVEL_2_CUT_SHORT, y -> {
    		if (y < 0.2) return 0.6;
    		else if (y < 0.85) return 0.8;
    		else return 0.3;
    	}, 0));

    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT + 10000));
    	addSequential(new AutoPIDTurn(-45));
    	addSequential(new RunPath(Paths.straightLength(17), 0.65, 0), 4);
    	addSequential(new AutoIntakeOn(false, 1.0, 0.75));
    	
    	/** Backs up and puts elevator down **/
		addSequential(new RunPath(Paths.straightLength(10), -0.75, 0));
		addSequential(new AutoElevatorSetDown()); 
		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 5));
		addSequential(new RunPath(Paths.straightLength(12), 0.5, 0));
		addSequential(new RunPath(Paths.straightLength(10), -0.25, 0));
	    addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT + 10000));
	    addSequential(new RunPath(Paths.straightLength(20), 0.65, 0));
	    addSequential(new AutoIntakeOn(false, 1.0, 0.75));
	    
	    addParallel(new AutoElevatorSetDown());
	    addSequential(new RunPath(Paths.FROM_RIGHT.BACK_SWITCH_BACKUP, -0.75, 1));
    }
}
