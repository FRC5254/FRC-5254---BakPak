package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
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
public class WranglerSwitchAuto extends CommandGroup { // was LeftBackSideSwitchAutoLeft

    public WranglerSwitchAuto(Boolean leftside, Path path1) {
    	
    	super("WranglerSwitchAuto");

    	
    	// TODO consideer making the turn into switch more so first cube isnt so on the edge
    	// 2.0 (the more reliable auto)
    	double turn1 = 90.0;
    	double turn2 = -30.0;
    	double turn3 = 7;
    	double turn4 = -7;
    	double drive = 230.0; //93
    	
    	if (leftside == false) { // different values used for the right side ex. all PID turns negated
    		turn1 = -turn1;
    		turn2 = -turn2;
    		turn3 = -turn3;
    		turn4 = -turn4;
    		drive = 225.0;
    	}
    	
    	/*GOes VroomM VroooM*/
    	addParallel(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
    	addParallel(new IntakeSetSpeed(RobotMap.AUTO_INTAKE), 1.25);
        addSequential(new RunPath(Paths.straightLength(drive), x -> {
			if (x < 0.20) return 0.7;
			if (x < 0.80) return 0.7;//0.75
			else return 0.5;
		}, 0.75));
//        addSequential(new RunPath(path1, 0.5, 0));// Path, speed, high gear
        
        /*UP goES eLE out gOES CuBE*/
        addSequential(new AutoPIDTurn(turn1));
    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
    	addSequential(new RunPath(path1, 0.5, 0), 5);
    	addSequential(new IntakeSetSpeed(RobotMap.AUTO_INTAKE),0.75);
    	
    	/*MoaR CubES*/
    	addSequential(new RunPath(Paths.straightLength(15), -0.75, 0));
    	addSequential(new ElevatorDown(RobotMap.ELE_DOWN_SPEED));
    	addParallel(new IntakeSetSpeed(RobotMap.AUTO_INTAKE), 7);
		addSequential(new RunPath(Paths.straightLength(19), 0.5, 0), 3);
		addParallel(new RunPath(Paths.straightLength(12), 0.5, 0), 3);
		addSequential(new AutoPIDTurn(turn3));// NEW
    	addSequential(new AutoPIDTurn(turn4));
    	addSequential(new AutoTimerWait(0.5));
		addSequential(new RunPath(Paths.straightLength(10), -0.25, 0));
		
		addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
        addSequential(new RunPath(Paths.straightLength(22), 0.5, 0), 2);
        addSequential(new AutoPIDTurn(-15));// TODO
        addSequential(new IntakeSetSpeed(RobotMap.AUTO_INTAKE) , .75);
        addSequential(new RunPath(Paths.straightLength(24), -0.5, 0));
        
		/* EvEN MOar CUbes*/
        addParallel(new ElevatorDown(RobotMap.ELE_DOWN_SPEED));
        addSequential(new AutoPIDTurn(turn2));
        addParallel(new IntakeSetSpeed(RobotMap.AUTO_INTAKE), 2);
        addSequential(new RunPath(Paths.straightLength(20), 0.75, 0));
    }
}
