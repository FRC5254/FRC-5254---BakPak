package org.usfirst.frc.team5254.robot.autocommands;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TimedDrive extends Command {

	double Throttle;
	double Distance;

	public TimedDrive(double Throttle, double Distance) {
		// TODO Requires DT?

		this.Throttle = Throttle;
		this.Distance = Distance;
	}

	@Override
	protected void initialize() {
		Robot.Drivetrain.autoDistanceDriveInit(Throttle, Distance);
	}

	@Override
	protected void execute() {
		Robot.Drivetrain.autoDriveToDistance();
	}

	@Override
	protected boolean isFinished() {

		return Robot.Drivetrain.driveAutoIsFinished();
	}

	@Override
	protected void end() {
		Robot.Drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
