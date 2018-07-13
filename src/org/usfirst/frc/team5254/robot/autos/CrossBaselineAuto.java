package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class CrossBaselineAuto extends CommandGroup {

	public CrossBaselineAuto() {
//		OLD CODE NO SPLINES
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
//		addSequential(new AutoTimerWait(1.0));
//		addSequential(new AutoDriveToDistance(1.0, 100));
		
	/** Pop height **/
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
		
	/** Drive forward **/
		addSequential(new RunPath(Paths.straightLength(100), x -> {
			if (x < 0.20) return 0.7;
			if (x < 0.80) return 0.7;//0.75
			else return 0.5;
		}, 0.75));
		
	}
}
