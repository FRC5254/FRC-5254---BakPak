package org.usfirst.frc.team5254.robot.autocommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ExecuteAfterWait extends CommandGroup {

    public ExecuteAfterWait(double time, Command command) {
        
    	addSequential(new WaitCommand(time));
    	addSequential(command);
    }
}
