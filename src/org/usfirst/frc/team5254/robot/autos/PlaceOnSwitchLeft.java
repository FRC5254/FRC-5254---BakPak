package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.Robot;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.DriveToDistance;
import org.usfirst.frc.team5254.robot.autocommands.ElevatorToSetPoint;
import org.usfirst.frc.team5254.robot.autocommands.IntakeCube;
import org.usfirst.frc.team5254.robot.autocommands.OutakeCube;
import org.usfirst.frc.team5254.robot.autocommands.PIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.TimedDrive;
import org.usfirst.frc.team5254.robot.autocommands.TimedElevatorRaise;
import org.usfirst.frc.team5254.robot.autocommands.stopCubeMech;
import org.usfirst.frc.team5254.robot.commands.ElevatorRachet;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceOnSwitchLeft extends CommandGroup {

	
    public PlaceOnSwitchLeft() {
    	
    	addSequential(new TimedElevatorRaise(1)); 
    	addSequential(new DriveToDistance(1.0, 10));
    	addSequential(new AutoTimerWait(.5));
    	addSequential(new PIDTurn(-35));
    	addSequential(new AutoTimerWait(.5));
    	addSequential(new DriveToDistance(0.75, 95));// 87 was good
    	addSequential(new PIDTurn(35));
    	addSequential(new AutoTimerWait(.5));
    	addSequential(new TimedDrive(0.5, .75));
    	addSequential(new IntakeCube());
    	addSequential(new AutoTimerWait(2));
    	addSequential(new stopCubeMech());
    	
    }
}
