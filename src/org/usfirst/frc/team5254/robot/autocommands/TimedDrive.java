package org.usfirst.frc.team5254.robot.autocommands;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TimedDrive extends Command {

	double Throttle;
	double stopTime;
	public static Timer timer = new Timer();
	

	public TimedDrive(double Throttle, double stopTime) {
		this.Throttle = Throttle;
		this.stopTime = stopTime;
	}

	@Override
	protected void initialize() {
		Robot.Drivetrain.timeDriveInit();
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		Robot.Drivetrain.timeDrive(Throttle);
	}

	@Override
	protected boolean isFinished() {
		if(timer.get() > stopTime) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		Robot.Drivetrain.stop();
		timer.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
