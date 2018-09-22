package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Path;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.commands.ElevatorDown;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetAndHold;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;
import org.usfirst.frc.team5254.robot.commands.IntakeSetSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TwoCubeScaleAuto extends CommandGroup { // was LeftScaleAutoLeftTwoCubes

    public TwoCubeScaleAuto(boolean leftside, Path path1, Path path2, Path path3) {
    	
    	super("TwoCubeScaleAuto");
    	
    	double turn1 = 85;
    	double turn2 = 7;
    	double turn3 = -7;
    	double turn4 = -180;
    	double intakeTime = 4.5;
    	
    	if (leftside == false){
    		turn1 = -turn1;
    		turn2 = -turn2;
    		turn3 = -turn3;	
    		turn4 = -turn4;
    		intakeTime = 6.0;
    	}
    	
    /** Pop cube **/
    	addParallel(new IntakeSetSpeed(RobotMap.AUTO_INTAKE),1.5);
    	addParallel(new ElevatorSetAndHold(RobotMap.SWITCH_HEIGHT + 3000));
    	
    /** Place cube on scale **/
    	addSequential(new RunPath(path1, x -> {
			if (x < 0.65) return 1.0;
			else return 0.5;
    	}, 0.75));
    	addParallel(new IntakeSetSpeed(RobotMap.AUTO_INTAKE),1);
    	addParallel(new ElevatorSetAndHold(RobotMap.UNOWNED_SCALE_HEIGHT));
    	addSequential(new RunPath(path2, 0.5, 0));
    	addSequential(new IntakeSetSpeed(1), 1);
    	
    /** Elevator down **/
    	
    	
    	addParallel(new AutoPIDTurn(turn1));
    	addSequential(new WaitCommand(0.25));
    	addSequential(new ElevatorDown(RobotMap.ELE_DOWN_SPEED));
    	
    /** Pick up second cube **/
    	addParallel(new IntakeSetSpeed(RobotMap.AUTO_INTAKE), intakeTime);
    	addSequential(new RunPath(path3, 0.45, 0), 2.5);
    	addSequential(new AutoPIDTurn(turn2));
    	addSequential(new AutoPIDTurn(turn3));
    	addSequential(new RunPath(Paths.straightLength(10), -0.3, 0));
    	
    /** Place second cube**/
    	addParallel(new IntakeSetSpeed(RobotMap.AUTO_INTAKE), 2.5);
    	addParallel(new ElevatorSetAndHold(RobotMap.UNOWNED_SCALE_HEIGHT));
     	addSequential(new AutoPIDTurn(turn4));
    	addSequential(new RunPath(Paths.straightLength(33), 0.65, 0));
    	addSequential(new IntakeSetSpeed(0.5), 2 );
    	
    /** Elevator down **/
    	
    	 addSequential(new RunPath(Paths.straightLength(33), -0.35, 0));
    	 addParallel(new WaitCommand(1));
    	 addSequential(new ElevatorDown(RobotMap.ELE_DOWN_SPEED));
    	
    }
}
