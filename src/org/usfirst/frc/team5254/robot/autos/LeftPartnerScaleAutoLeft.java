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
public class LeftPartnerScaleAutoLeft extends CommandGroup {

    public LeftPartnerScaleAutoLeft() {
//    	
//    /** Pop cube **/
//    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//    	addParallel(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
//    	
//    /** Place on left scale at a 90 deg. **/
//    	addSequential(new RunPath(Paths.straightLength(305), 0.9));
//    	addSequential(new AutoTimerWait(0.5));
//    	addSequential(new AutoPIDTurn(90));
//    	addSequential(new AutoTimerWait(0.5));
//    	addSequential(new AutoTimedDrive(-.5, 2)); WE
//    	addSequential(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
////    	addSequential(new RunPath()); IM NOT DONE HERE
//    	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE, 2));
//    	
//    /** Elevator down **/
//    	addSequential(new RunPath(Paths.straightLength(30), -0.25));
//    	addSequential(new AutoElevatorSetDown());
    	
    /** Pop cube **/
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 2));
    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT + 6000));
    	
    /** Place cube on scale **/
    	addSequential(new RunPath(Paths.FROM_LEFT.SCALE_LEFT_TRAVEL, x -> {
			if (x < .05) return 0.25;
			else return 0.75;
    	}, 0));
    	addParallel(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new RunPath(Paths.FROM_LEFT.SCALE_LEFT_FINISH, 0.2, 0));
    	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE - 0.25, 1));
    
    /** Elevator down **/
    	addParallel(new AutoElevatorDownWait(1.5));
    	addSequential(new RunPath(Paths.straightLength(20), -0.4, 0), 5);
    	
    	
    	
    }
}
