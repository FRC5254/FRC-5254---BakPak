package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.autocommands.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAuto extends CommandGroup {
	
    public TestAuto() {

    	addSequential(new PIDTurn(90));
//    	addSequential(new DriveToDistance(0.75, 50));
//    	addSequential(new PIDTurn(25));
//		addSequential(new DriveToDistanceFast(0.75, 25));

    	
    	//addSequential(new PIDTurn(180));
    	//addSequential(new DriveToDistance(1.0, 15));
    }
}
