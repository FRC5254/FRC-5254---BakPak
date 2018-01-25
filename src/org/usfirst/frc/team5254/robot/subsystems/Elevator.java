package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.ElevatorOn;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class Elevator extends Subsystem {
	
	//Initializing Motor Controllers
	public static WPI_TalonSRX elevator = new WPI_TalonSRX(RobotMap.ELEVATOR);// TODO figure out if were using a CAN TALON or a VICTOR??
	
	//Define other variables
	public static Timer timer = new Timer();
	public double finalThrottle;
	public double remainingTime;
	public double Throttle;
    
    //TeleOp Methods
    public void SlideLadder(double Throttle) {
		elevator.set(Throttle);
	}
	public void StopLadder() {
		elevator.set(0.0);
	}
	
	//Auto Methods
	public void autoTimedRaiseInit(double Throttle, double Time) {
		
		this.Throttle = Throttle;
		timer.reset();
		timer.start();
		
	}
	
	public void autoTimedRaise(double Throttle, double Time) {
		
		if(Time - timer.get() >= 0) {
			remainingTime = Time - timer.get();
		} else {
			remainingTime = 0;
		}
		if (Throttle > 0) {
			if (remainingTime < Throttle * 15) {
				finalThrottle = remainingTime / 15;// TODO time these values
			} else {
				finalThrottle = Throttle;
			}

			if (timer.get() < 0.25 && remainingTime > 10.0) {
				finalThrottle = timer.get() * 4.0;
			}

			if (finalThrottle > Throttle) {
				finalThrottle = Throttle;
			}

			if (finalThrottle < 0.35) {
				finalThrottle = 0.35;
			}
		} else {
			if (remainingTime < Math.abs(Throttle) * 15) {
				finalThrottle = -remainingTime / 15;
			} else {
				finalThrottle = Throttle;

			}

			if (timer.get() < 0.25 && remainingTime > 10.0) {
				finalThrottle = -timer.get() * 4.0;
			}

			if (finalThrottle < Throttle) {
				finalThrottle = Throttle;
			}

			if (finalThrottle > -0.35) {
				finalThrottle = -0.35;
			}
		}
	}
	//Defualt Command
	public void initDefaultCommand() {
		 
	    setDefaultCommand(new ElevatorOn());
	    
	}
}

