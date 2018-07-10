package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorSetDown;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Path;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath2;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WranglerSwitchAuto extends CommandGroup { // was LeftBackSideSwitchAutoLeft

    public WranglerSwitchAuto(Boolean leftside, Path path1, Path path2) {
    	double turn1 = 90.0;
    	double turn2 = -30.0;
    	double turn3 = 7;
    	double turn4 = -7;
    	double drive = 93.0;
    	int heightAddition = 10000;
    	
    	if (leftside == false) { // different values used for the right side ex. all PID turns negated
    		turn1 = -turn1;
    		turn2 = -turn2;
    		turn3 = -turn3;
    		turn4 = -turn4;
    		drive = 100.0;
    		heightAddition = 12000;
    	}
    	
    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT + heightAddition));
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.25));
        addSequential(new RunPath(Paths.straightLength(drive), x -> {
			if (x < 0.20) return 0.7;
			if (x < 0.80) return 0.7;//0.75
			else return 0.5;
		}, 0.75));
        addParallel(new AutoElevatorDownWait(1.7));
        addSequential(new RunPath2(path1, 0.5, 0, 0.107));// Path, speed, high gear, outtake
        addSequential(new AutoPIDTurn(turn1));
        addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 5));
        addSequential(new RunPath(path2, 0.5, 0));
        addSequential(new AutoPIDTurn(turn3));
    	addSequential(new AutoPIDTurn(turn4));
        addSequential(new RunPath(Paths.straightLength(10), -0.25, 0));
        addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
        addSequential(new RunPath(Paths.straightLength(22), 0.5, 0));
        addSequential(new AutoIntakeOn(false, RobotMap.AUTO_INTAKE, 0.75));
        addSequential(new RunPath(Paths.straightLength(24), -0.5, 0));
        addParallel(new AutoElevatorSetDown());
        addSequential(new AutoPIDTurn(turn2));
        addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 2));
        addSequential(new RunPath(Paths.straightLength(20), 0.75, 0));
    }
}
