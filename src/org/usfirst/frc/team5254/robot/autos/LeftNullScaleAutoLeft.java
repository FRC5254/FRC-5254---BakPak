package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoDriveToDistance;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorSetDown;
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
public class LeftNullScaleAutoLeft extends CommandGroup {

    public LeftNullScaleAutoLeft() {
    	
    /** Pop cube **/
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
    	addParallel(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	
    /** Place on left scale at a 90 deg. **/
    	addSequential(new RunPath(Paths.straightLength(305), 0.9, 0));
    	addSequential(new AutoTimerWait(0.5));
    	addSequential(new AutoPIDTurn(90));
    	addSequential(new AutoTimerWait(0.5));
    	addSequential(new RunPath(Paths.straightLength(12), -0.5, 0), 4);
    	addSequential(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new RunPath(Paths.straightLength(18), 0.5, 0));
    	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE, 2));
    	
    /** Elevator down **/
    	addSequential(new RunPath(Paths.straightLength(30), -0.25, 0),2);
    	addSequential(new AutoElevatorSetDown());
    }
}
