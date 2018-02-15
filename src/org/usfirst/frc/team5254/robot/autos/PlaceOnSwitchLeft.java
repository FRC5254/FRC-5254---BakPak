package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.Robot;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.DriveToDistance;
import org.usfirst.frc.team5254.robot.autocommands.ElevatorToSetPoint;
import org.usfirst.frc.team5254.robot.autocommands.IntakeCube;
import org.usfirst.frc.team5254.robot.autocommands.OutakeCube;
import org.usfirst.frc.team5254.robot.autocommands.PIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.TimedDrive;
import org.usfirst.frc.team5254.robot.autocommands.stopCubeMech;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceOnSwitchLeft extends CommandGroup {

	private static Timer timer = new Timer();
	
    public PlaceOnSwitchLeft() {
    	
    	timer.reset();
    	timer.start();
    	
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
