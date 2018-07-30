package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Path;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FarScaleAuto extends CommandGroup {

	/**
	 * @param path1 Inital straight drive (scale travel)
	 * @param path2 Second scale travel
	 * @param path3 Scale finish
	 */
    public FarScaleAuto(Path path1, Path path2, Path path3) { // LeftScaleAutoRight
    	
    	super("FarScaleAuto");
    	
    /** Pop cube **/
     	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1));
    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	
    /** Drive over to other side of field **/
    	addSequential(new RunPath(path1, x -> {
    		if (x < 0.05) return 0.5;
    		else return 0.8;
    	}, 0));
    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
    	addSequential(new RunPath(path2, y -> {
    		if (y < 0.2) return 0.6;
    		else if (y < 0.85) return 0.8;
    		else return 0.3;
    	}, 0));
    	
    /** Place cube on scale **/
    	addParallel(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new AutoTimerWait(0.25));
    	addSequential(new RunPath(path3, 0.25, 0));
    	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE - 0.15, 1));
    
    /** Elevator down **/
    	addParallel(new RunPath(Paths.straightLength(30), -0.25, 0));
    	addSequential(new AutoElevatorDownWait(1.0));
    }
}
