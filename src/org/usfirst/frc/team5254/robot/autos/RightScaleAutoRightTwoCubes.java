package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoDriveToDistance;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorSetDown;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOnWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.AutoSwitchHeightWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleAutoRightTwoCubes extends CommandGroup {

    public RightScaleAutoRightTwoCubes() {
    	
    /** Pop cube **/
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.5));
    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT + 6000));
    	
    /** Place cube on scale **/
    	addSequential(new RunPath(Paths.FROM_RIGHT.SCALE_RIGHT_TRAVEL, x -> {
			if (x < 0.8) return 1.0;
			else return 0.5;
    	}, 0.75));
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1));
    	addParallel(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new RunPath(Paths.FROM_RIGHT.SCALE_RIGHT_FINISH, 0.5, 0));
    	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE + 0.25, 1));
    	
    /** Elevator down **/
    	addParallel(new AutoElevatorDownWait(0.25));
    	addSequential(new AutoPIDTurn(-85));
    	
    /** Pick up second cube **/
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 6));
    	addSequential(new RunPath(Paths.FROM_RIGHT.SCALE_RIGHT_SECOND_CUBE_GRAB, 0.45, 0), 2.5);
    	addSequential(new AutoPIDTurn(-7));
    	addSequential(new AutoPIDTurn(7));
    	addSequential(new RunPath(Paths.straightLength(10), -0.3, 0));
    	
    /** Place second cube**/
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 2.5));
    	addParallel(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
     	addSequential(new AutoPIDTurn(-195));
    	addSequential(new RunPath(Paths.straightLength(33), 0.55, 0));    	
    	addSequential(new AutoIntakeOn(false, 0.65, 2));
    	
    /** Elevator down **/
    	 addParallel(new AutoElevatorDownWait(1));
    	 addSequential(new RunPath(Paths.straightLength(33), -0.35, 0));
    	
    }
}
