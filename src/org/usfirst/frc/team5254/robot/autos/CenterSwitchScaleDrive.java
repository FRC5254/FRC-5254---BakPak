package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOnWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Path;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchScaleDrive extends CommandGroup { // CenterSwitchAutoLeftDriveLeft

    public CenterSwitchScaleDrive(boolean samesideScale, Path path1, Path path2, Path path3, Path path4) {
    	
    	super("CenterSwitchScaleDrive");

    	double distance = 35;
    	
    	if (!samesideScale) {
    		distance = 45;
    	}
	/** Pop cube **/
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addSequential(new AutoTimerWait(0.25));
	
	/** Place pre-load cube **/
		addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		addSequential(new RunPath(path1, x -> {
			if (x < 0.20) return 0.5;// 0.2 to 0.1
			if (x < 0.75) return 0.85;// make .75 greater
			else return 0.4;// faster!
		}, 0));
		addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 1));
		addParallel(new AutoElevatorDownWait(1.5));// decrease wait time
			
	/** Drive the same path but backwards to return to starting position **/
		addSequential(new RunPath(path2, x -> {
			if (x < 0.20) return -0.5;// same as above changes bc y not
			if (x < 0.75) return -0.85;
			else return -0.4;
		}, 0));
		addParallel(new AutoIntakeOnWait(true, 1, 2.5));
			
	/** Grabs closest cube from cube zone **/
		addSequential(new RunPath(path3, 0.35, 0));//this number can probably not be increased
		addSequential(new RunPath(Paths.straightLength(distance), -0.8, 0));
			
	/** Drive to the side of the switch the scale is on **/
		addSequential(new RunPath(path4, x -> {
			if (x < 0.10) return 0.5;// same as above changes bc y not
			if (x < 0.90) return 0.85;
			else return 0.6;// was .4
		}, 0));
		addSequential(new RunPath(Paths.straightLength(24), 0.75, 0));
    }
}



