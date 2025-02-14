package org.usfirst.frc.team5254.robot.autos;


import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Path;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorDown;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetAndHold;
import org.usfirst.frc.team5254.robot.commands.IntakeSetSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * TODO add more of the time limits on things in autos (especially paths)
 */
public class FakeCenterSwitchAuto extends CommandGroup { // Very much not tested lol

	public FakeCenterSwitchAuto(Path path1) {
		
	/** Pop cube **/
		addParallel(new IntakeSetSpeed(RobotMap.AUTO_INTAKE), 1.5);
		addSequential(new ElevatorSetAndHold(RobotMap.POP_HEIGHT));
		
	/** Robot hits the switch on the side **/
		addParallel(new ElevatorDown(RobotMap.ELE_DOWN_SPEED));
		addSequential(new RunPath(path1, x -> {
			if (x < 0.20) return 0.5;
			if (x < 0.75) return 0.6;
			else return 0.3;
		}, 0), 6);
		
		
	/** Backs up and puts elevator down **/
		addSequential(new RunPath(Paths.straightLength(24), -0.75, 0));
	}
}
