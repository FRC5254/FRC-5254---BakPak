package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.ElevatorOn;
import org.usfirst.frc.team5254.robot.commands.ElevatorRachet;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Elevator extends PIDSubsystem {
	
	public Elevator() {
		super("Elevator", RobotMap.ELEVATOR_P, RobotMap.ELEVATOR_I, RobotMap.ELEVATOR_D);
		setAbsoluteTolerance(0.0);
		getPIDController().setContinuous(true);
	}

	//Initializing auto Controllers
	public static Victor elevator = new Victor(RobotMap.ELEVATOR);// TODO figure out if were using a CAN TALON or a VICTOR??
	public static Encoder encoder = new Encoder(2, 3, true, Encoder.EncodingType.k4X);
	
	////Initializing Rachet piston
	public static DoubleSolenoid rachetingPiston = new DoubleSolenoid(RobotMap.RACHET_PISTON, RobotMap.UNRACHET_PISTON);
	//Define other variables
	public static Timer timer = new Timer();
	private static int finalTicks;
	private int remainingTicks;
	public double finalSpeed;
	public double remainingDistance;
	public double Speed;
	public double Distance;
    
    //TeleOp Methods
    public void SlideLadder(double Speed) {
		elevator.set(Speed);
    }
    
    public void StopLadder() {
    	elevator.set(0.0);
    }
    public void Ratchet() {
    	rachetingPiston.set(DoubleSolenoid.Value.kForward);
    }
    public void Unrachet() {
    	rachetingPiston.set(DoubleSolenoid.Value.kReverse);
    }
	
	//Auto Methods
	public void initEncoder(boolean direction) {
		
		encoder.reset();
		encoder.setMaxPeriod(0.1);
		encoder.setMinRate(1);
		encoder.setDistancePerPulse(1);
		encoder.setReverseDirection(direction);
		encoder.setSamplesToAverage(7);
		
	}
	
	public void autoTimedRaiseInit(double Speed, double Distance) {
		
		this.Speed = Speed;
		finalTicks = (int) ((Distance / (RobotMap.ELEVATOR_AXIS_DIAMETER * Math.PI)) * RobotMap.ENCODER_TICKS * RobotMap.ELEVATOR_GEAR_RATIO);
		if (Speed > 0) {
			initEncoder(true);
		} else {
			initEncoder(false);
		}
		timer.reset();
		timer.start();
		
	}
	
	public void autoRaiseDistance(double Speed, double Distance) {
		
		remainingTicks = Math.abs(finalTicks) - Math.abs(encoder.get());
		remainingDistance = (Math.abs(remainingTicks) / (RobotMap.ENCODER_TICKS * RobotMap.ELEVATOR_GEAR_RATIO))
				* (RobotMap.ELEVATOR_AXIS_DIAMETER * Math.PI);

		if (Speed > 0) {
			if (remainingDistance < Speed * 15) {
				finalSpeed = remainingDistance / 15;// TODO time these values
			} else {
				finalSpeed = Speed;
			}

			if (timer.get() < 0.25 && remainingDistance > 10.0) {
				finalSpeed = timer.get() * 4.0;
			}

			if (finalSpeed > Speed) {
				finalSpeed = Speed;
			}

			if (finalSpeed < 0.35) {
				finalSpeed = 0.35;
			}
		} else {
			if (remainingDistance < Math.abs(Speed) * 15) {
				finalSpeed = -remainingDistance / 15;
			} else {
				finalSpeed = Speed;

			}

			if (timer.get() < 0.25 && remainingDistance > 10.0) {
				finalSpeed = -timer.get() * 4.0;
			}

			if (finalSpeed < Speed) {
				finalSpeed = Speed;
			}

			if (finalSpeed > -0.35) {
				finalSpeed = -0.35;
			}
		}
		
		elevator.set(-finalSpeed);

		}	
	
	public boolean raiseAutoIsFinished() {
		return remainingTicks < 21;
	}
	
	//Defualt Command
	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorRachet());
	    setDefaultCommand(new ElevatorOn());
	    
	}

	@Override
	protected double returnPIDInput() {
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		
	}
}

