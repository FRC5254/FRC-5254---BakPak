package org.usfirst.frc.team5254.robot;

import org.usfirst.frc.team5254.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	// Defining controllers
	public Joystick driverJoystick = new Joystick(RobotMap.DRIVER_JOYSTICK);
	public Joystick operatorJoystick = new Joystick(RobotMap.OPERATOR_JOYSTICK);
	
	public OI() {
		
		// Defining driver buttons
		Button DriverButtonLB = new JoystickButton(driverJoystick, 4);
		Button DriverButtonRB = new JoystickButton(driverJoystick, 5);
		Button DriverButtonStart = new JoystickButton(driverJoystick, 7);
		Button DriverButtonRJC = new JoystickButton(driverJoystick, 9);
		
		// Defining operator buttons
		Button OperatorButtonA = new JoystickButton(operatorJoystick, 0);
		Button OperatorButtonX = new JoystickButton(operatorJoystick, 2);
		Button OperatorButtonB = new JoystickButton(operatorJoystick, 1);
		Button OperatorButtonRB = new JoystickButton(operatorJoystick, 5);
		Button OperatorButtonBack = new JoystickButton(operatorJoystick, 6);
		Button OperatorButtonStart = new JoystickButton(operatorJoystick, 7);
		
		// Driver subcommands
		DriverButtonRB.whenPressed(new DrivetrainShiftUp());
		DriverButtonRB.whenInactive(new DrivetrainShiftDown());
		DriverButtonLB.whenPressed(new DrivetrainShiftUp());
		DriverButtonLB.whenInactive(new DrivetrainShiftDown());
		DriverButtonStart.whenPressed(new DrivetrainSlowTurn());
		DriverButtonStart.whenInactive(new DrivetrainDriveWithJoystick());
		DriverButtonRJC.whenPressed(new DrivetrainSlowTurn());
		DriverButtonRJC.whenInactive(new DrivetrainDriveWithJoystick());
		
		// Operator Subcommands
		OperatorButtonBack.whenPressed(new CubeMechClamp());
		OperatorButtonStart.whenPressed(new CubeMechRelease());
		OperatorButtonA.whenPressed(new CubeMechIntake());
		OperatorButtonX.whenPressed(new CubeMechOutake());
		OperatorButtonB.whenPressed(new CubeMechStopFlywheels());
		OperatorButtonRB.whenPressed(new CubeMechPopOut());
		OperatorButtonRB.whenInactive(new CubeMechPopIn());
	}
}
