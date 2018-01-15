package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.CubeMechStopLadder;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeMech extends Subsystem {
	public static VictorSP cubeMechLadder = new VictorSP(RobotMap.CUBE_MECH_LADDER);
	public static Spark cubeMechFlywheels = new Spark(RobotMap.CUBE_MECH_FLYWHEELS);
	public static Solenoid cubeMechArms = new Solenoid(RobotMap.CUBE_MECH_ARMS);
	public static Solenoid cubeMechHinge = new Solenoid(RobotMap.CUBE_MECH_HINGE);
    
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public CubeMech() {
	}
	
	public void ArmsDown() {
		cubeMechHinge.set(true);
	}
	public void ArmsUp() {
		cubeMechHinge.set(false);
	}
	public void Clamp() {
		cubeMechArms.set(true);
	}
	public void Release() {
		cubeMechArms.set(false);
	}
	public void SlideLadder(double Throttle) {
		cubeMechLadder.set(Throttle);
	}
	public void StopLadder() {
		cubeMechLadder.set(0.0);
	}
	public void Intake() {
		cubeMechFlywheels.set(1);
	}
	public void Outake() {
		cubeMechFlywheels.set(-1);
	}
	public void StopFlywheels() {
		cubeMechFlywheels.set(0);
	}

	
    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new CubeMechStopLadder());
    }
}

