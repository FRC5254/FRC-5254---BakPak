package org.usfirst.frc.team5254.robot.autos;


import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.pathing.*;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterSwitchAuto extends CommandGroup { // CenterSwitchAutoLeft
	// TODO add more of the time limits on things in autos (especially paths)
	
	/**
	 * @param path1 The initial path that chooses which side of the switch to approach
	 */
	public CenterSwitchAuto(Path path1) {
		
		super("CenterSwitchAuto");
		
	/** Pop cube **/
		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addSequential(new AutoTimerWait(0.25));// This is to prevent breaking the snake
		
	/** Robot approaches the switch on the side specified and raises the elevator**/
		addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		addSequential(new RunPath(path1, x -> {
			if (x < 0.20) return 0.5;// make .2 to .1
			if (x < 0.75) return 0.85;// make this faster and for more time
			else return 0.4; //faster!
		}, 0), 6);
		
	/** Shoots cube into switch **/	
		addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 3));
		
	/** Backs up and puts elevator down **/
		addSequential(new RunPath(Paths.straightLength(24), -0.75, 0));
		addSequential(new AutoElevatorSetDown());
	}
}
