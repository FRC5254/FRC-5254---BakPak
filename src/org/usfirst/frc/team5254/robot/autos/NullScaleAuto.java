package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorDown;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;
import org.usfirst.frc.team5254.robot.commands.IntakeSetSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NullScaleAuto extends CommandGroup { // was LeftNullScaleAutoLeft

    public NullScaleAuto(boolean leftSide) {
    	
    	super("NullScaleAuto");

    	double turn = 90.0;
    	
    	if (!leftSide) {
    		turn = -turn;
    	}
    	
    /** Pop cube **/
    	addParallel(new IntakeSetSpeed(RobotMap.AUTO_INTAKE),1.5);
    	addParallel(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	
    /** Place on left scale at a 90 deg. **/
    	addSequential(new RunPath(Paths.straightLength(302), 0.9, 0));
    	addSequential(new AutoTimerWait(0.5));
    	addSequential(new AutoPIDTurn(turn));
    	addSequential(new AutoTimerWait(0.5));
    	addSequential(new RunPath(Paths.straightLength(12), -0.5, 0), 4);
    	addSequential(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new RunPath(Paths.straightLength(18), 0.5, 0));
    	addSequential(new IntakeSetSpeed(RobotMap.AUTO_SCALE_OUTAKE), 2);
    	
    /** Elevator down **/
    	addSequential(new RunPath(Paths.straightLength(30), -0.25, 0),2);
    	addSequential(new ElevatorDown(RobotMap.ELE_DOWN_SPEED));
    }
}
