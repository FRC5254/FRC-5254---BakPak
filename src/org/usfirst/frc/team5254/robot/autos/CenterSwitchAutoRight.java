package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimerWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoDriveToDistance;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.AutoTimedDrive;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetDown;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchAutoRight extends CommandGroup {

	public CenterSwitchAutoRight() {

		addSequential(new ElevatorSetHeight(RobotMap.POP_HEIGHT));
		addParallel(new AutoIntakeOn(true, 1.5));
		addSequential(new AutoTimerWait(1.0));
		addSequential(new AutoDriveToDistance(1.0, 10));
		addSequential(new AutoTimerWait(.5));
		addSequential(new AutoPIDTurn(25));
		addParallel(new AutoIntakeOn(true, 0.5));
		addSequential(new AutoTimerWait(.5));
		addSequential(new AutoDriveToDistance(0.75, 85));
		addSequential(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		addSequential(new AutoTimerWait(.5));
		addSequential(new AutoPIDTurn(-25));
		addParallel(new AutoIntakeOn(true, 0.5));
		addSequential(new AutoTimerWait(.5));
		addSequential(new AutoTimedDrive(0.5, .75));
		addSequential(new AutoIntakeOn(false, 3));
		addSequential(new AutoDriveToDistance(-0.75, 24));
		addSequential(new ElevatorSetDown());
	}
}
