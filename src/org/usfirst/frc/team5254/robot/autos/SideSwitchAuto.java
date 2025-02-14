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
 *
 */
public class SideSwitchAuto extends CommandGroup { //used to be leftSwitchAutoLeft

    public SideSwitchAuto(Path path1, Path path2) {
    	
    	super("SideSwitchAuto");
//		OLD CODE NO SPLINES
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
//		addSequential(new AutoTimerWait(1.0));
//		addSequential(new AutoDriveToDistance(1, 128));
//		addSequential(new AutoPIDTurn(90));
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 0.5));
//		addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
//		addSequential(new AutoTimerWait(0.5));
//		addSequential(new AutoTimedDrive(1, 1));
//		addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 15));
    	
    /** Pop cube **/
    	addParallel(new IntakeSetSpeed(RobotMap.AUTO_INTAKE), 1.5);
    	addSequential(new ElevatorSetAndHold(RobotMap.POP_HEIGHT));
    	
    /** Place into switch at a 90 deg **/
    	addSequential(new RunPath(path1, x -> {
    		if (x < 0.05) return 0.5;
    		else return 0.8;
    	}, 0));
    	addSequential(new ElevatorSetAndHold(RobotMap.SWITCH_HEIGHT));
    	addSequential(new RunPath(path2, 0.4, 0));
    	addSequential(new IntakeSetSpeed(RobotMap.AUTO_SWITCH_OUTAKE),2);
    	
    /** Elevator down **/
    	addSequential(new RunPath(Paths.straightLength(30), -0.3, 0));
    	addSequential(new ElevatorDown(RobotMap.ELE_DOWN_SPEED));
    }
}
