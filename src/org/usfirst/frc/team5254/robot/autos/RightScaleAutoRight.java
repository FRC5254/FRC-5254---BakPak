package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleAutoRight extends CommandGroup {

    public RightScaleAutoRight() {
//		OLD CODE NO SPLINES (angle approach)
//    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
//		addSequential(new AutoTimerWait(1.0)); 
//    	addSequential(new AutoDriveToDistance(1 , 210));
//		addSequential(new AutoTimerWait(.5));
//     	addSequential(new AutoPIDTurn(-30));
//     	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 0.5));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new AutoDriveToDistance(0.75, 65));
//     	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE, 1));
//     	addSequential(new AutoDriveToDistance(-0.5, 65));
//     	addSequential(new AutoElevatorSetDown());
    	
//		OLD CODE NO SPLINES (90 degree approach) - this needs elevator set down!!
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
//		addSequential(new AutoTimerWait(1.0));
//    	addSequential(new AutoDriveToDistance(1 , 295));//285
//		addSequential(new AutoTimerWait(.5));
//     	addSequential(new AutoPIDTurn(-90));
//     	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 0.5));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new AutoDriveToDistance(0.75, 25));
//     	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE, 3));
//     	addSequential(new AutoDriveToDistance(-0.5, 25));
    	
    /** Pop cube **/
//    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
//    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1));
    	
    /** Place on scale **/
    	addSequential(new RunPath(Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, x -> {
			if (x < .05) return 0.2;
			else return 0.8;
    	}));
//    	addParallel(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new RunPath(Paths.FROM_RIGHT.SCALE_RIGHT_FINISH, .25));
//    	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE, 2));
    	
    /** Elevator down **/ 
    	addSequential(new RunPath(Paths.straightLength(30), -.25));
//    	addSequential(new AutoElevatorSetDown());
    }
}
