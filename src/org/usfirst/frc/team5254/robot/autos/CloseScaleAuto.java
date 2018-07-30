package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Path;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CloseScaleAuto extends CommandGroup {

	/**
	 * @param path1 Straight scale travel path
	 * @param path2 Place on scale path (scale finish)
	 */
    public CloseScaleAuto(Path path1, Path path2) { // was LeftScaleAutoLeft
    	
    	super("CloseScaleAuto");
    								
    /** Pop cube **/
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));// Watch cube holding with this
    	
    /** Place cube on scale **/
    	addSequential(new RunPath(path1, x -> {
			if (x < 0.8) return 1.0;
			else return 0.5;
    	}, 0.75));
    	addParallel(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new RunPath(path2, 0.5, 0));
    	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE + 0.25, 2));
    
    /** Elevator down **/
    	addSequential(new RunPath(Paths.straightLength(30), -.25 ,0));
    	addSequential(new AutoElevatorSetDown());
    }
}