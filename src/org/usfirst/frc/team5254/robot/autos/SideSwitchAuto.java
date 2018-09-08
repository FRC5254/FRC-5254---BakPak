package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorSetDown;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Path;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;
import org.usfirst.frc.team5254.robot.util.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideSwitchAuto extends CommandGroup { //used to be leftSwitchAutoLeft

	/**
	 * @param path1 Side switch travel
	 * @param path2 Side switch place
	 */
    public SideSwitchAuto(Path path1, Path path2) {
    	
    	super("SideSwitchAuto");
    	
    /** Pop cube **/
    	addParallel(new AutoIntakeOn(Direction.INTAKE, RobotMap.AUTO_INTAKE, 1.5));
    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	
    /** Place into switch at a 90 deg **/
    	addSequential(new RunPath(path1, x -> {
    		if (x < 0.05) return 0.5;
    		else return 0.8;
    	}, 0));
    	addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
    	addSequential(new RunPath(path2, 0.4, 0));
    	addSequential(new AutoIntakeOn(Direction.OUTTAKE, RobotMap.AUTO_SWITCH_OUTAKE, 2));
    	
    /** Elevator down **/
    	addSequential(new RunPath(Paths.straightLength(30), -0.3, 0));
    	addSequential(new AutoElevatorSetDown());
    }
}
