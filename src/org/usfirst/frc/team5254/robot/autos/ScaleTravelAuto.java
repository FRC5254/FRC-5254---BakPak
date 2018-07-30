package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Path;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleTravelAuto extends CommandGroup {
	
	/**
	 * @param path1 Scale travel 1
	 * @param path2 Scale travel 2
	 */
    public ScaleTravelAuto(Path path1, Path path2) {
    	
    	super("ScaleTravelAuto");

    /** Pop cube **/
     	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1));
    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	
    /** Drive partly over to other side of field **/
    	addSequential(new RunPath(path1, x -> {
    		if (x < 0.05) return 0.5;
    		else return 0.8;
    	}, 0));
    	addSequential(new RunPath(path2, y -> {
    		if (y < 0.2) return 0.6;
    		else if (y < 0.85) return 0.8;
    		else return 0.3;
    	}, 0));
    	
    }
}
