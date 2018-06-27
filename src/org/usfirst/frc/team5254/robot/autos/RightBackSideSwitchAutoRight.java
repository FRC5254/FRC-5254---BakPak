package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorSetDown;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOnWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath2;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightBackSideSwitchAutoRight extends CommandGroup {

    public RightBackSideSwitchAutoRight() {
    	
    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT + 15000));
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.25));
        addSequential(new RunPath(Paths.straightLength(95), x -> {
			if (x < 0.20) return 0.7;
			if (x < 0.80) return 0.7;//0.75
			else return 0.5;
		}, 0.75));
        addParallel(new AutoElevatorDownWait(1.7));
        addSequential(new RunPath2(Paths.FROM_RIGHT.BACK_SWITCH_PLACE, 0.5, 0, 0.107));// Path, speed, high gear, outtake
        addSequential(new AutoPIDTurn(-90));
        addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 5));
        addSequential(new RunPath(Paths.FROM_RIGHT.BACK_SWITCH_SECOND_CUBE, 0.5, 0));
        addSequential(new RunPath(Paths.straightLength(10), -0.25, 0));
        addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
        addSequential(new RunPath(Paths.straightLength(22), 0.5, 0));
        addSequential(new AutoIntakeOn(false, RobotMap.AUTO_INTAKE, 0.75));
        addSequential(new RunPath(Paths.straightLength(24), -0.5, 0));
        addParallel(new AutoElevatorSetDown());
        addSequential(new AutoPIDTurn(30));
        addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 2));
        addSequential(new RunPath(Paths.straightLength(20), 0.75, 0));
    }
}
