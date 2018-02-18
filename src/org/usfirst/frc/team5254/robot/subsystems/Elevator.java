package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.ElevatorRachet;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
//TODO make the zeroencoder work
public class Elevator extends Subsystem {

	// Initializing auto Controllers
	public static TalonSRX elevator = new TalonSRX(RobotMap.ELEVATOR);
	public static TalonSRX elevatorEncoder = new TalonSRX(RobotMap.ELEVATOR_ENCODER);

	//// Initializing Rachet piston
	public static DoubleSolenoid rachetingPiston = new DoubleSolenoid(RobotMap.RACHET_PISTON, RobotMap.UNRACHET_PISTON);
	
	// Define other variables
	public static Timer timer = new Timer();
	public double finalSpeed;
	public double remainingDistance;
	public double Speed;
	public double Distance;
	public int ticks;

	public void initElevator() {
		
		elevatorEncoder.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
		elevatorEncoder.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
		elevator.follow(elevatorEncoder);
	}

	// TeleOp Method
	public void on(double Speed) {
		elevatorEncoder.set(ControlMode.PercentOutput, Speed);
//		System.out.println(elevator.getSelectedSensorPosition(0));
//		System.out.println(((elevator.getSelectedSensorPosition(0)) / 256) * (1.273 * Math.PI));
	}

	public void setToHeight(int ticks) {
		this.ticks = ticks;
		
			if (ticks > Math.abs(elevatorEncoder.getSelectedSensorPosition(0))) {
				elevatorEncoder.set(ControlMode.PercentOutput, -1);
			} else {
				off();
		}
	}

	public boolean endSetHeight() {
		return (elevatorEncoder.getSelectedSensorPosition(0) > ticks);
	}

	public void elevatorDown() {
		if (elevatorEncoder.getSelectedSensorPosition(0) > 100) {
			elevatorEncoder.set(ControlMode.PercentOutput, -.25);

		} else {
			off();
		}
	}

	public boolean elevatorDownEnd() {
		return false;
	}

	public void off() {
		elevator.set(ControlMode.PercentOutput, 0.0);
	}

	public void rachet() {
		rachetingPiston.set(DoubleSolenoid.Value.kForward);
	}

	public void unrachet() {
		rachetingPiston.set(DoubleSolenoid.Value.kReverse);
	}

	// Auto Methods
	public void initEncoder(boolean direction) { // TODO boolean doesn't do anything
		elevatorEncoder.setSelectedSensorPosition(0, 0, 10);
	}

	public void zeroEncoder() {
		elevatorEncoder.setSelectedSensorPosition(0, 0, 10);
	}

	public void autoTimedRaiseInit(double Speed, int ticks) { // TODO it oaky?

		this.Speed = Speed;
		if (Speed > 0) {
			initEncoder(true);
		} else {
			initEncoder(false);
		}
		timer.reset();
		timer.start();

	}

	// Defualt Command
	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorRachet());
	}

}
