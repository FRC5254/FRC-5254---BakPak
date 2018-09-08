package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOnWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Path;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;
import org.usfirst.frc.team5254.robot.util.Direction;
import org.usfirst.frc.team5254.robot.util.FieldConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterSwitchScaleDrive extends CommandGroup {

	/** 
	 * @param samesideScale <code>true</code> if the robot is lined up on the left side
	 * 						<code>false</code> otherwise
	 * @param path1 First path to approach the switch
	 * @param path2 Second path to return to starting line up
	 * @param path3 Grabs second cube from cube pile path
	 * @param path4 Drive to scale path
	 */
    public CenterSwitchScaleDrive(FieldConfig fieldConfig, Path path1, Path path2, Path path3, Path path4) {
    	
    	super("CenterSwitchScaleDrive");

    	double distance = 35;
    	
    	if (fieldConfig == FieldConfig.LR || fieldConfig == FieldConfig.RL) {
    		distance = 45;
    	}
    	
	/** Pop cube **/
    	addParallel(new AutoIntakeOn(Direction.INTAKE, RobotMap.AUTO_INTAKE, 1.5));
		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addSequential(new AutoTimerWait(0.25));
	
	/** Place pre-load cube **/
		addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		addSequential(new RunPath(path1, x -> {
			if (x < 0.20) return 0.5;// 0.2 to 0.1
			if (x < 0.75) return 0.85;// make .75 greater
			else return 0.4;// faster!
		}, 0));
		addSequential(new AutoIntakeOn(Direction.OUTTAKE, RobotMap.AUTO_SWITCH_OUTAKE, 1));
		addParallel(new AutoElevatorDownWait(1.5));// decrease wait time
			
	/** Drive the same path but backwards to return to starting position **/
		addSequential(new RunPath(path2, x -> {
			if (x < 0.20) return -0.5;// same as above changes bc y not
			if (x < 0.75) return -0.85;
			else return -0.4;
		}, 0));
		
		/** Drive the same path but backwards to return to starting position **/
		addSequential(new RunPath(path2, x -> {
			if (x < 0.20) return -0.5;
			if (x < 0.75) return -0.85;
			else return -0.4;
		}, 0));
	
	/** Pick up second cube then back up **/
		addParallel(new AutoIntakeOnWait(Direction.INTAKE, 0.5, 3.5));// these numbers need to be tuned (wait, intake)
		addSequential(new RunPath(path3, 0.4, 0), 4);//this number can probably be increased
		addParallel(new RunPath(Paths.straightLength(5), 0.5, 0));
		addSequential(new AutoPIDTurn(-7));
    	addSequential(new AutoPIDTurn(7));// NEW needs testing
		
		addSequential(new RunPath(Paths.straightLength(distance), -0.8, 0));
			
	/** Drive to the side of the switch the scale is on plus some more drive**/
		addSequential(new RunPath(path4, x -> {
			if (x < 0.10) return 0.5;// same as above changes bc same path
			if (x < 0.90) return 0.85;
			else return 0.6;// was .4
		}, 0));
		
		addSequential(new RunPath(Paths.straightLength(24), 0.75, 0));
    }
}



