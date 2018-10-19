package org.usfirst.frc.team5254.robot;

import org.usfirst.frc.team5254.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	// Defining controllers
	public Joystick driver = new Joystick(RobotMap.DRIVER_JOYSTICK);
	public Joystick operator = new Joystick(RobotMap.OPERATOR_JOYSTICK);


	public OI() {
		
		// Defining driver buttons
//		Button DriverButtonA = new JoystickButton(driver, 1);
		Button DrivetrainSlowDrive = new JoystickButton(driver, Robot.dp.slowDrive);
//		Button DriverButtonX = new JoystickButton(driver, 3);
//		Button DriverButtonY = new JoystickButton(driver, 4);
		Button DrivetrainShiftDown = new JoystickButton(driver, Robot.dp.shiftDownButton);
		Button DrivetrainShiftUp = new JoystickButton(driver, Robot.dp.shiftUpButton);
		Button DrivetrainSlowTurn = new JoystickButton(driver, Robot.dp.slowTurnButton);
//		Button DriverButtonStart = new JoystickButton(driver, 8);
//		Button DriverLJC = new JoystickButton(driver, 9);
//		Button DriverRJC = new JoystickButton(driver, 10);

		// Defining operator buttons
		Button ElevatorDown = new JoystickButton(operator, Robot.op.downButton);
		Button ElevatorOwnedScaleHeight = new JoystickButton(operator, Robot.op.ownedScaleHeightButton);
		Button ElevatorSwitchHeight = new JoystickButton(operator, Robot.op.switchHeightButton);
		Button ElevatorUnownedScaleHeight = new JoystickButton(operator, Robot.op.unownedScaleHeightButton);
		Button Outtake = new JoystickButton(operator, Robot.op.outtakeButton);
		Button Intake = new JoystickButton(operator, Robot.op.intakeButton);
//		Button OperatorButtonBack = new JoystickButton(operator, 7);
		Button SlowOuttake = new JoystickButton(operator, Robot.op.slowOuttakeButton);
//		Button OperatorLJC = new JoystickButton(operator, 9);
//		Button OperatorRJC = new JoystickButton(operator, 10);

		
		// Driver subcommands
		DrivetrainSlowDrive.whenActive(new DrivetrainSlowDrive());
		DrivetrainSlowDrive.whenInactive(new DrivetrainDriveWithJoystick());
		DrivetrainShiftUp.whenPressed(new DrivetrainShiftUp());// TODO test (this is new)
		DrivetrainShiftDown.whenPressed(new DrivetrainShiftDown());
		DrivetrainSlowTurn.whenActive(new DrivetrainSlowTurn());
		DrivetrainSlowTurn.whenInactive(new DrivetrainDriveWithJoystick());

		// Operator Subcommands
		ElevatorDown.whenPressed(new ElevatorDown(RobotMap.ELE_DOWN_SPEED));
		ElevatorOwnedScaleHeight.whenPressed(new ElevatorSetHeight(RobotMap.OWNED_SCALE_HEIGHT));
		ElevatorSwitchHeight.whenPressed(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		ElevatorUnownedScaleHeight.whenPressed(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
		Intake.whenPressed(new IntakeSetSpeed(-0.75));
		Intake.whenReleased(new IntakeSetSpeed(0));
		Outtake.whenPressed(new IntakeSetSpeed(0.75));
		Outtake.whenReleased(new IntakeSetSpeed(0));
		SlowOuttake.whenPressed(new IntakeSetSpeed(0.5));
		SlowOuttake.whenReleased(new IntakeSetSpeed(0));
	}
}
