package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
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
public class CenterSwitchAutoLeftDriveLeft extends CommandGroup {

    public CenterSwitchAutoLeftDriveLeft() {
    	
	/** Pop cube **/
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addSequential(new AutoTimerWait(0.25));
	
	/** Place pre-load cube **/
		addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		addSequential(new RunPath(Paths.FROM_CENTER.SWITCH_LEFT_FORWARD, x -> {
			if (x < 0.20) return 0.5;// 0.2 to 0.1
			if (x < 0.75) return 0.85;// make .75 greater
			else return 0.4;// faster!
		}));
		addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 1));
		addParallel(new AutoElevatorDownWait(1.5));// decrease wait time
			
	/** Drive the same path but backwards to return to starting position **/
		addSequential(new RunPath(Paths.FROM_CENTER.SWITCH_LEFT_BACKWARD, x -> {
			if (x < 0.20) return -0.5;// same as above changes bc y not
			if (x < 0.75) return -0.85;
			else return -0.4;
		}));
		addParallel(new AutoIntakeOnWait(1, 2.5));
			
	/** Grabs closest cube from cube zone **/
		addSequential(new RunPath(Paths.FROM_CENTER.GRAB_SECOND_CUBE_FORWARD, 0.35));//this number can probably not be increased
		addSequential(new RunPath(Paths.straightLength(35), -0.8));
			
	/** Drive to the side of the switch the scale is on **/
		addSequential(new RunPath(Paths.FROM_CENTER.LEFT_SIDE_AFTER_GRAB_CUBE, x -> {
			if (x < 0.10) return 0.5;// same as above changes bc y not
			if (x < 0.90) return 0.85;
			else return 0.4;
		}));
		addSequential(new RunPath(Paths.straightLength(24), 0.75));
    }
}
