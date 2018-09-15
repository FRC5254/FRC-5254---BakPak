package org.usfirst.frc.team5254.robot.autos;


import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.pathing.*;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * TODO add more of the time limits on things in autos (especially paths)
 */
public class CenterSwitchAuto extends CommandGroup { // CenterSwitchAutoLeft

	public CenterSwitchAuto(Path path1) {
		
		super("CenterSwitchAuto");
//		OLD CODE, NO SPLINES (TODO look at parallels here are they in the wrong place? should the waits exist?)
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
//		addSequential(new AutoTimerWait(1.0));
//		addSequential(new AutoDriveToDistance(1.0, 10));
//		addSequential(new AutoTimerWait(.5));
//		addSequential(new AutoPIDTurn(-35));
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//		addSequential(new AutoTimerWait(.5));
//		addSequential(new AutoDriveToDistance(0.75, 95));
//		addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
//		addSequential(new AutoTimerWait(.5));
//		addSequential(new AutoPIDTurn(35));		
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//		addSequential(new AutoTimerWait(.5));
//		addSequential(new AutoTimedDrive(0.5, 1));
//		addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 3));
//		addSequential(new AutoDriveToDistance(-0.75, 24));
//		addSequential(new AutoElevatorSetDown());
		
	/** Pop cube **/
		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addSequential(new WaitCommand(0.25));// This is to prevent breaking the snake
		
	/** Robot approaches the switch on the left side **/
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
