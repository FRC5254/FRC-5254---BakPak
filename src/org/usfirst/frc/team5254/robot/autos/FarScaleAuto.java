package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Path;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;
import org.usfirst.frc.team5254.robot.commands.IntakeSetSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class FarScaleAuto extends CommandGroup {

    public FarScaleAuto(Path path1, Path path2, Path path3) { // LeftScaleAutoRight
    	
    	super("FarScaleAuto");

//		OLD CODE NO SPLINES (old code wasn't ever tuned)
//		addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
//    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
//		addSequential(new AutoTimerWait(1.0));
//    	addSequential(new AutoDriveToDistance(1, 210));
//     	addSequential(new AutoPIDTurn(90));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new AutoDriveToDistance(1, 195));
//     	addSequential(new AutoPIDTurn(-90));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
//     	addSequential(new AutoTimerWait(.5));
//     	addSequential(new AutoDriveToDistance(0.5, 55));
//     	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE, 1));
//     	addSequential(new AutoDriveToDistance(0.5, 24));
     
    /** Pop cube **/
     	addParallel(new IntakeSetSpeed(RobotMap.AUTO_INTAKE) ,1);
    	addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	
    /** Drive over to other side of field **/
    	addSequential(new RunPath(path1, x -> {
    		if (x < 0.05) return 0.5;
    		else return 0.8;
    	}, 0));
    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
    	addSequential(new RunPath(path2, y -> {
    		if (y < 0.2) return 0.6;
    		else if (y < 0.85) return 0.8;
    		else return 0.3;
    	}, 0));
    	
    /** Place cube on scale **/
    	addParallel(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new AutoTimerWait(0.25));
    	addSequential(new RunPath(path3, 0.25, 0));
    	addSequential(new IntakeSetSpeed(RobotMap.AUTO_SCALE_OUTAKE - 0.15), 1);
    
    	/** Elevator down **/
    	addParallel(new RunPath(Paths.straightLength(30), -0.25, 0));
    	addSequential(new AutoElevatorDownWait(1.0));
    }
}
