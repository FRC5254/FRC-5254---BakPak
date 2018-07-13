package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorSetDown;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOnWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Path;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchAutoTwoCube extends CommandGroup { // CenterSwitchAutoLeftTwoCube

    public CenterSwitchAutoTwoCube(Path path1, Path path2, Path path3, Path path4) {
    	
    /** Place preload cube **/
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addSequential(new AutoTimerWait(0.25));
		addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		addSequential(new RunPath(path1, x -> {
			if (x < 0.20) return 0.5;
			if (x < 0.75) return 0.85;
			else return 0.4;
		}, 0));
		addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 1));
		addParallel(new AutoElevatorDownWait(1.5));// decrease wait time as needed
		
		
	/** Drive the same path but backwards to return to starting position **/
		addSequential(new RunPath(path2, x -> {
			if (x < 0.20) return -0.5;
			if (x < 0.75) return -0.85;
			else return -0.4;
		}, 0));
		addParallel(new AutoIntakeOnWait(true, 0.5, 3.5));// these numbers need to be tuned (wait, intake)
		addSequential(new RunPath(path3, 0.4, 0), 4);//this number can probably be increased
		
		addParallel(new RunPath(Paths.straightLength(5), 0.5, 0));
		addSequential(new AutoPIDTurn(-7));// NEW
    	addSequential(new AutoPIDTurn(7));
    	
		addSequential(new RunPath(Paths.straightLength(35), -0.8, 0));
		
	/** Same code as above to place cube into switch again **/
		addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		addSequential(new RunPath(path4, x -> {
			if (x < 0.20) return 0.5;
			if (x < 0.75) return 0.85;
			else return 0.4;
		}, 0));
		addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 1.5));
		addSequential(new RunPath(Paths.straightLength(24), -0.75, 0));
		addSequential(new AutoElevatorSetDown());
    }
}
