package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Path;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorDown;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;
import org.usfirst.frc.team5254.robot.commands.IntakeSetSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CloseScaleAuto extends CommandGroup {

    public CloseScaleAuto(Path path1, Path path2) { // was LeftScaleAutoLeft
    	
    	super("CloseScaleAuto");

//		OLD CODE NO SPLINES
//    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1));
//    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
//    	addSequential(new ElevatorSetDown());
//		addSequential(new AutoTimerWait(.5));//rm
//    	addSequential(new AutoDriveToDistance(1 , 210));
//		addSequential(new AutoTimerWait(.5));
//     	addSequential(new AutoPIDTurn(30));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
//     	addSequential(new AutoTimerWait(.5));//shorten
//     	addSequential(new AutoDriveToDistance(0.75, 65));
//     	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE, 1));//shorten
//     	addSequential(new AutoDriveToDistance(-0.5, 65));
//     	addSequential(new ElevatorSetDown());
    								
    /** Pop cube **/
    	addParallel(new IntakeSetSpeed(RobotMap.AUTO_INTAKE) ,1.5);
    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
    	
    /** Place cube on scale **/
    	addSequential(new RunPath(path1, x -> {
			if (x < 0.8) return 1.0;
			else return 0.5;
    	}, 0.75));
    	addParallel(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new RunPath(path2, 0.5, 0));
    	addSequential(new IntakeSetSpeed(RobotMap.AUTO_SCALE_OUTAKE + 0.25), 2);
    
    /** Elevator down **/
    	addSequential(new RunPath(Paths.straightLength(30), -.25 ,0));
    	addSequential(new ElevatorDown(RobotMap.ELE_DOWN_SPEED));
    }
}