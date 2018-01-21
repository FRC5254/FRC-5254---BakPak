package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.ElevatorOn;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	public static VictorSP elevator = new VictorSP(RobotMap.ELEVATOR);// TODO figure out if were using a CAN TALON or a VICTOR??

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ElevatorOn());
    }
    
    public void SlideLadder(double Throttle) {
		elevator.set(Throttle);
	}
	public void StopLadder() {
		elevator.set(0.0);
	}
}

