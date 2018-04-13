package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorSetDown;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOnWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleAutoLeftTwoCubes extends CommandGroup {

    public LeftScaleAutoLeftTwoCubes() {
    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
    	addSequential(new RunPath(Paths.FROM_LEFT.SCALE_LEFT_TRAVEL, x -> {
			if (x < .05) return 0.2;
			else return 0.8;
    	}));
    	addParallel(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new RunPath(Paths.FROM_LEFT.SCALE_LEFT_FINISH, .25));
    	addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SCALE_OUTAKE, 2));
//    	addSequential(new RunPath(Paths.straightLength(30), -.25));
    	addParallel(new AutoElevatorDownWait(2));
    	addParallel(new AutoIntakeOnWait(3, 4));
    	addSequential(new RunPath(Paths.FROM_LEFT.SECOND_CUBE, 0.35));
    	addParallel(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new AutoTimerWait(2));
    	addSequential(new RunPath(Paths.FROM_LEFT.SECOND_CUBE, -0.25));
    }
}
