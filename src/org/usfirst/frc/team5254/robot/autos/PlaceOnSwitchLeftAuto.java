package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.Robot;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoDriveToDistance;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimedDrive;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimedElevatorRaise;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOff;
import org.usfirst.frc.team5254.robot.commands.ElevatorRachet;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceOnSwitchLeftAuto extends CommandGroup {

	public PlaceOnSwitchLeftAuto() {

		addSequential(new AutoTimedElevatorRaise(1)); // could change to be encoder
		addSequential(new AutoDriveToDistance(1.0, 10));
		addSequential(new AutoTimerWait(.5));
		addSequential(new AutoPIDTurn(-35));
		addSequential(new AutoTimerWait(.5));
		addSequential(new AutoDriveToDistance(0.75, 95));
		addSequential(new AutoPIDTurn(35));
		addSequential(new AutoTimerWait(.5));
		addSequential(new AutoTimedDrive(0.5, .75));
		addSequential(new AutoIntakeOn(false));
		addSequential(new AutoTimerWait(2));
		addSequential(new AutoIntakeOff());

	}
}
