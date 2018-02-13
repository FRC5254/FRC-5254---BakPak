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
		Button DriverButtonLB = new JoystickButton(driverJoystick, 5);
		Button DriverButtonRB = new JoystickButton(driverJoystick, 6);
		Button DriverButtonStart = new JoystickButton(driverJoystick, 8);
		Button DriverButtonRJC = new JoystickButton(driverJoystick, 10);
		Button DriverButtonA = new JoystickButton(driverJoystick, 1);
		Button DriverButtonX = new JoystickButton(driverJoystick, 3);
		Button DriverButtonB = new JoystickButton(driverJoystick, 2);
		
		// Defining operator buttons
		Button OperatorButtonA = new JoystickButton(operatorJoystick, 1);
		Button OperatorButtonX = new JoystickButton(operatorJoystick, 3);
		Button OperatorButtonB = new JoystickButton(operatorJoystick, 2);
		Button OperatorButtonRB = new JoystickButton(operatorJoystick, 6);
		Button OperatorButtonBack = new JoystickButton(operatorJoystick, 7);
		Button OperatorButtonStart = new JoystickButton(operatorJoystick, 8);
		
		// Driver subcommands
		DriverButtonRB.whenPressed(new DrivetrainShiftUp());
		DriverButtonRB.whenInactive(new DrivetrainShiftDown());
		DriverButtonLB.whenPressed(new DrivetrainShiftUp());
		DriverButtonLB.whenInactive(new DrivetrainShiftDown());
		DriverButtonStart.whenPressed(new DrivetrainSlowTurn());
		DriverButtonStart.whenInactive(new DrivetrainDriveWithJoystick());
		DriverButtonRJC.whenPressed(new DrivetrainSlowTurn());
		DriverButtonRJC.whenInactive(new DrivetrainDriveWithJoystick());
//		DriverButtonA.whenPressed(new CubeMechIntake());
//		DriverButtonX.whenPressed(new CubeMechOutake());
//		DriverButtonB.whenPressed(new CubeMechStopFlywheels());
		
		// Operator Subcommands
		OperatorButtonX.whenPressed(new CubeMechIntake());
		OperatorButtonA.whenPressed(new CubeMechOutake());
		OperatorButtonB.whenPressed(new CubeMechStopFlywheels());
		OperatorButtonBack.whenPressed(new ElevatorStop());
		OperatorButtonBack.whenReleased(new ElevatorOn());

	}
}
