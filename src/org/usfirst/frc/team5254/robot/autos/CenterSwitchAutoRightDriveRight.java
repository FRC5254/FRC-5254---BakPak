package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorSetDown;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOnWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchAutoRightDriveRight extends CommandGroup {

    public CenterSwitchAutoRightDriveRight() {
    	
    /** Place preload cube **/
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addSequential(new AutoTimerWait(0.25));
		addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		addSequential(new RunPath(Paths.FROM_CENTER.SWITCH_RIGHT_FORWARD, x -> {
			if (x < 0.20) return 0.5;
			if (x < 0.75) return 0.85;
			else return 0.4;
		}));
		addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 1));
		addParallel(new AutoElevatorDownWait(1.5));// decrease wait time as needed
		
		
	/** Drive the same path but backwards to return to starting position **/
		addSequential(new RunPath(Paths.FROM_CENTER.SWITCH_RIGHT_BACKWARD, x -> {
			if (x < 0.20) return -0.5;
			if (x < 0.75) return -0.85;
			else return -0.4;
		}));
		addParallel(new AutoIntakeOnWait(1, 2.5));// these numbers need to be tuned (wait, intake)
		
	/** Grab second cube and back up **/
		addSequential(new RunPath(Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, 0.35));//this number can probably be increased
		addSequential(new RunPath(Paths.straightLength(35), -0.8));
		
	/** Drive over to the right side of the switch **/
		addSequential(new RunPath(Paths.FROM_CENTER.RIGHT_SIDE_AFTER_GRAB_CUBE, x -> {
			if (x < 0.10) return 0.5;
			if (x < 0.90) return 0.85;
			else return 0.4;
		}));
		addSequential(new RunPath(Paths.straightLength(24), 0.75));
    }
}
