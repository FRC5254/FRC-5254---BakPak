package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.autocommands.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAuto extends CommandGroup {
	
    public TestAuto() {

    	//addSequential(new PIDTurn(50));
    	//addSequential(new DriveToDistanceFast(1.0, 100));
    	addSequential(new PIDTurn(-60));
		//addSequential(new DriveToDistanceFast(1.0, 25));

    	
    	//addSequential(new PIDTurn(180));
    	//addSequential(new DriveToDistance(1.0, 15));
    }
}
