package org.usfirst.frc.team5254.robot.autos;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorDownWait;
import org.usfirst.frc.team5254.robot.autocommands.AutoElevatorSetDown;
import org.usfirst.frc.team5254.robot.autocommands.AutoIntakeOn;
import org.usfirst.frc.team5254.robot.autocommands.AutoPIDTurn;
import org.usfirst.frc.team5254.robot.autocommands.pathing.Paths;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath;
import org.usfirst.frc.team5254.robot.autocommands.pathing.RunPath2;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftBackSideSwitchLeft extends CommandGroup {

    public LeftBackSideSwitchLeft() {
    	addParallel(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT + 13000));
    	addParallel(new AutoIntakeOn(true, RobotMap.AUTO_INTAKE, 1.25));
        addSequential(new RunPath(Paths.straightLength(90), x -> {
			if (x < 0.20) return 0.7;
			if (x < 0.80) return 0.7;//0.75
			else return 0.5;
		}, 0.75));
        addParallel(new AutoElevatorDownWait(1.7));
        addSequential(new RunPath2(Paths.FROM_LEFT.BACK_SWITCH_FIRST_PLACE, 0.5, 0, 0.118));// Path, speed, high gear, outtake
        addSequential(new AutoPIDTurn(90));
//        addSequential(new AutoIntakeOn(false, RobotMap.AUTO_SWITCH_OUTAKE, 2));
//        addSequential(new RunPath(Paths.straightLength(24),0.5, 0));
        addSequential(new AutoElevatorSetDown());
    }
}
