package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class CrossBaselineAuto extends CommandGroup {

	public CrossBaselineAuto() {
		super("CrossBaselineAuto");
		
	/** Pop height **/
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
		
	/** Drive forward to cross the autoline **/
		addSequential(new RunPath(Paths.straightLength(100), x -> {
			if (x < 0.20) return 0.7;
			if (x < 0.80) return 0.7;//0.75
			else return 0.5;
		}, 0.75));
		
	}
}
