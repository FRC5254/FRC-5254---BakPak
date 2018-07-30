package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorSetDown;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Path;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StuyPulseSwitchAuto extends CommandGroup { // LeftBackSideSwitchAutoRight

	/**
	 * @param leftside <code>true</code> If robot is lied up on left side
	 * 				   <code>false</code> Otherwise
	 * @param path1 Far scale travel
	 * @param path2 Far scale travel cut short
	 * @param path3 WOOSH
	 */
    public StuyPulseSwitchAuto(boolean leftside, Path path1, Path path2, Path path3) {
    	
    	super("StuyPulseSwitchAuto");
    	
    	double turn = 45;
    	double turn2 = 10;
    	double turn3 = -10;
    	double distance = 16;
    	
    	if(!leftside) {
    		turn = -35;
    		turn2 = -turn2;
    		turn3 = -turn3;
        	distance = 16;
    	}
    	
    	
    /** Pop cube **/
     	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1));
    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	
    /** Drive over to other side of field **/
    	addSequential(new RunPath(path1, x -> {
    		if (x < 0.05) return 0.5;
    		else return 1.0;
    	}, 0));
    	addSequential(new RunPath(path2, y -> {
    		if (y < 0.2) return 0.6;
    		else if (y < 0.85) return 0.8;
    		else return 0.3;
    	}, 0));

    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT + 7000)); //was 10,000
    	addSequential(new AutoPIDTurn(turn));
    	addSequential(new RunPath(Paths.straightLength(17), 0.65, 0), 4);
    	addSequential(new AutoIntakeOn(false, 1.0, 0.75));
    	
    	/** Backs up and puts elevator down **/
		addSequential(new RunPath(Paths.straightLength(10), -0.75, 0));
		addSequential(new AutoElevatorSetDown()); 
		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 5));
		addSequential(new RunPath(Paths.straightLength(12), 0.5, 0));
		addParallel(new RunPath(Paths.straightLength(5), 0.5, 0));
		addSequential(new AutoPIDTurn(turn2));// NEW
    	addSequential(new AutoPIDTurn(turn3));
		addSequential(new RunPath(Paths.straightLength(10), -0.25, 0));
	    addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT + 10000));
	    addSequential(new RunPath(Paths.straightLength(distance), 0.65, 0));
	    addSequential(new AutoIntakeOn(false, 1.0, 0.75));
	    
	    addParallel(new AutoElevatorSetDown());
	    addSequential(new RunPath(path3, -0.75, 1));
	    
    }
}
