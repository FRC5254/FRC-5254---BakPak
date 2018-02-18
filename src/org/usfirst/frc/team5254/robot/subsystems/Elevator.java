package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.ElevatorRachet;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Elevator extends Subsystem {
	

	//Initializing auto Controllers
	public  static TalonSRX elevator = new TalonSRX(RobotMap.ELEVATOR);
	
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
	public int ticks;
    
	
	public Elevator() {
		
		elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
		elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
	}
	
	public void initEncoder(boolean direction) {
		/* zero the sensor */   
		  elevator.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
		}
	
	public void zeroEncoder() {
		 elevator.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
	}
	
    //TeleOp Method
    public void slideLadder(double Speed) {
		elevator.set(ControlMode.PercentOutput, -Speed);
		System.out.println(elevator.getSelectedSensorPosition(0));
		System.out.println(((elevator.getSelectedSensorPosition(0))  / 256) * (1.273 * Math.PI));
    }
    
    public boolean endSetHeight(){
    	return (elevator.getSelectedSensorPosition(0) > ticks);
    }
    
    public void setToHeight(int ticks) {
    	
    	this.ticks=ticks;
    	
    	if (ticks > Math.abs(elevator.getSelectedSensorPosition(0))){
    		elevator.set(ControlMode.PercentOutput,1);
    	}else{
    		StopLadder();
    	}
    	
    }
    
    public void elevatorDown() {
    	if (elevator.getSelectedSensorPosition(0) > 100) {
    		elevator.set(ControlMode.PercentOutput,-.25);
    		
    	}else {
    		StopLadder();
    	}
    }
    
    public boolean elevatorDownEnd(){
    	return (elevator.getSelectedSensorPosition(0) < 1000);
    }
    
    public void StopLadder() {
    	elevator.set(ControlMode.PercentOutput,0.0);
    }
     
    public void UnratchetInit() { //TODO does this even work
    	timer.reset();
    	timer.start();
    	if (timer.get() >= 0.25) {
    		Unrachet();
    		timer.stop();
    	}
    }
    public void Rachet() {
    	rachetingPiston.set(DoubleSolenoid.Value.kForward);
    }
    public void Unrachet() {
    	rachetingPiston.set(DoubleSolenoid.Value.kReverse);
    }
	
	//Auto Methods

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
		
		remainingTicks = Math.abs(finalTicks) - Math.abs(elevator.getSelectedSensorPosition(10));
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
		
		elevator.set(ControlMode.PercentOutput,  -finalSpeed);

		}	
	
	public boolean raiseAutoIsFinished() {
		return remainingTicks < 21;
	}
	
	//Defualt Command
	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorRachet());
	    // TODO add elevator off here or StopLadder whatever
	}

	
}

