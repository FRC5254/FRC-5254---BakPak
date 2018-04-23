package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoDriveToDistance;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorSetDown;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimedDrive;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightPartnerScaleAutoRight extends CommandGroup {

    public RightPartnerScaleAutoRight() {
    	
    /** Pop cube **/
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
    	addParallel(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	
    /** Place on right scale at a 90 deg **/
    	addSequential(new AutoDriveToDistance(1 , 295));
    	addSequential(new AutoPIDTurn(-90));
    	addSequential(new AutoTimedDrive(-.5,1));
    	addSequential(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new AutoDriveToDistance(.75,36));
    	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE, 2));
    
    /** Elevator down **/
    	addSequential(new RunPath(Paths.straightLength(30), -.25));
    	addSequential(new AutoElevatorSetDown());
    	
    }
}
