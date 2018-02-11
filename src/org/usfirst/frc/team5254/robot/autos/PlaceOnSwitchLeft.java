package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.Robot;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.DriveToDistance;
import org.usfirst.frc.team5254.robot.autocommands.ElevatorToSetPoint;
import org.usfirst.frc.team5254.robot.autocommands.OutakeCube;
import org.usfirst.frc.team5254.robot.autocommands.PIDTurn;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceOnSwitchLeft extends CommandGroup {

	private static Timer timer = new Timer();
	
    public PlaceOnSwitchLeft() {
//    	
//    	timer.reset();
//    	timer.start();
    	// TODO make this one auto
    	addSequential(new DriveToDistance(1.0, 10));
    	//addSequential(new ElevatorToSetPoint(1.0, 0.8));
    	addSequential(new PIDTurn(-25));
    	addSequential(new AutoTimerWait(.5));
    	addSequential(new DriveToDistance(0.5, 90));
    	addSequential(new PIDTurn(25));
    	addSequential(new AutoTimerWait(1));
    	addSequential(new OutakeCube());
//    	addSequential(new DriveToDistance(1.0, 17));
    	
//    	if(timer.get() >= 10) {
//    		Robot.CubeMech.Outake();
//    	} 
//    	if(timer.get() >= 13) {
//    		Robot.CubeMech.StopFlywheels();
//    	}
    }
}
