package org.usfirst.frc.team5254.robot;

import org.usfirst.frc.team5254.robot.commands.*;
import org.usfirst.frc.team5254.robot.triggers.DoubleButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	// Defining controllers
	public Joystick driver = new Joystick(RobotMap.DRIVER_JOYSTICK);
	public Joystick operator = new Joystick(RobotMap.OPERATOR_JOYSTICK);

	public OI() {

		// Defining driver buttons
		Button DriverButtonA = new JoystickButton(driver, 1);
		Button DriverButtonB = new JoystickButton(driver, 2);
		Button DriverButtonX = new JoystickButton(driver, 3);
		Button DriverButtonY = new JoystickButton(driver, 4);
		Button DriverLB = new JoystickButton(driver, 5);
		Button DriverRB = new JoystickButton(driver, 6);
		Button DriverButtonBack = new JoystickButton(driver, 7);
		Button DriverButtonStart = new JoystickButton(driver, 8);
		Button DriverLJC = new JoystickButton(driver, 9);
		Button DriverRJC = new JoystickButton(driver, 10);

		// Defining operator buttons
		Button OperatorButtonA = new JoystickButton(operator, 1);
		Button OperatorButtonB = new JoystickButton(operator, 2);
		Button OperatorButtonX = new JoystickButton(operator, 3);
		Button OperatorButtonY = new JoystickButton(operator, 4);
		Button OperatorButtonLB = new JoystickButton(operator, 5);
		Button OperatorButtonRB = new JoystickButton(operator, 6);
		Button OperatorButtonBack = new JoystickButton(operator, 7);
		Button OperatorButtonStart = new JoystickButton(operator, 8);
		Button OperatorLJC = new JoystickButton(operator, 9);
		Button OperatorRJC = new JoystickButton(operator, 10);
		
		DoubleButton OperatorLBandA = new DoubleButton(operator, 5, 1);
		DoubleButton OperatorLBandB = new DoubleButton(operator, 5, 2);
		DoubleButton OperatorLBandX = new DoubleButton(operator, 5, 3);
		DoubleButton OperatorLBandY = new DoubleButton(operator, 5, 4);
		
		DoubleButton OperatorRBandA = new DoubleButton(operator, 6, 1);
		DoubleButton OperatorRBandB = new DoubleButton(operator, 6, 2);
		DoubleButton OperatorRBandX = new DoubleButton(operator, 6, 3);
		DoubleButton OperatorRBandY = new DoubleButton(operator, 6, 4);
		

		// Driver subcommands
		 DriverButtonA.whenPressed(new ClimberFire());
		// DriverButtonB.whenPressed(command);
		// DriverButtonX.whenPressed(command);
		// DriverButtonY.whenPressed(commnd);
		DriverLB.whenPressed(new DrivetrainShiftUp());
		DriverLB.whenInactive(new DrivetrainShiftDown());
		DriverRB.whenPressed(new DrivetrainShiftUp());
		DriverRB.whenInactive(new DrivetrainShiftDown());
		// DriverButtonStart.whenPressed(command);
		DriverButtonBack.whenPressed(new DrivetrainSlowTurn());
		DriverButtonBack.whenInactive(new DrivetrainDriveWithJoystick());
		// DriverLJC.whenPressed(command);
		DriverRJC.whenPressed(new DrivetrainSlowTurn());
		DriverRJC.whenInactive(new DrivetrainDriveWithJoystick());

		// Operator Subcommands
//		OperatorButtonA.whenPressed(new IntakeOn(true));
//		OperatorButtonB.whenPressed(new IntakeOff());
//		OperatorButtonX.whenPressed(new IntakeOn(false));
//		OperatorButtonY.whenPressed(new ElevatorSetDown());
//		OperatorButtonLB.whenPressed(command); Dont use this button
//		OperatorButtonRB.whenPressed(new ElevatorUnrachet());
//		OperatorButtonRB.whenReleased(new ElevatorRachet());
		OperatorButtonStart.whenPressed(new ElevatorUnrachet());
		OperatorButtonStart.whenReleased(new ElevatorRachet());
//		OperatorLJC.whenPressed(command);
		OperatorRJC.whenPressed(new ElevatorOn(-0.25));

		OperatorLBandA.toggleWhenActive(new IntakeOn(true));
		OperatorLBandB.toggleWhenActive(new IntakeOff());
		OperatorLBandX.toggleWhenActive(new IntakeOn(false));
		OperatorLBandY.toggleWhenActive(new ElevatorSetDown());
		
		OperatorRBandA.toggleWhenActive(new ElevatorSetDown());
		OperatorRBandB.toggleWhenActive(new ElevatorStop());
		OperatorRBandX.toggleWhenActive(new ElevatorSetHeight(15254));
		OperatorRBandY.toggleWhenActive(new ElevatorSetHeight(40000));

	}
}
