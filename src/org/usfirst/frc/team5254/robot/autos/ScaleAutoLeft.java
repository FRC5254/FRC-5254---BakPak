package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.autocommands.*;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetDown;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;
import org.usfirst.frc.team5254.robot.commands.IntakeOn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleAutoLeft extends CommandGroup {

    public ScaleAutoLeft() {
		addSequential(new ElevatorSetHeight(2000));
		addSequential(new AutoTimerWait(.5));
    	addSequential(new AutoDriveToDistance(1 , 210));
		addSequential(new AutoTimerWait(.5));
     	addSequential(new AutoPIDTurn(30));
     	addSequential(new AutoTimerWait(.5));
     	addSequential(new ElevatorSetHeight(40000));
     	addSequential(new AutoTimerWait(.5));
     	addSequential(new AutoDriveToDistance(0.75, 65));
     	addSequential(new AutoIntakeOn(false, 1));
     	addSequential(new AutoDriveToDistance(-0.5, 65));
//     	addSequential(new ElevatorSetDown());
    }
}
