package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.Robot;
import org.usfirst.frc.team5254.robot.autocommands.DriveToDistance;
import org.usfirst.frc.team5254.robot.autocommands.ElevatorToSetPoint;
import org.usfirst.frc.team5254.robot.autocommands.PIDTurn;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceOnSwitchRight extends CommandGroup {

	private static Timer timer = new Timer();
	
    public PlaceOnSwitchRight() {
    	
    	timer.reset();
    	timer.start();
    	
    	addSequential(new DriveToDistance(1.0, 17));
    	//addSequential(new ElevatorToSetPoint(1.0, 0.8));
    	addSequential(new PIDTurn(25));
    	addSequential(new DriveToDistance(1.0, 80));
    	addSequential(new PIDTurn(-25));
    	addSequential(new DriveToDistance(1.0, 17));
    	
    	if(timer.get() >= 10) {
    		Robot.CubeMech.Outake();
    	} 
    	if(timer.get() >= 13) {
    		Robot.CubeMech.StopFlywheels();
    	}
    }
}
