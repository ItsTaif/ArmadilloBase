package org.usfirst.frc.team818.robot.autonomi;

import org.usfirst.frc.team818.robot.commands.CommandBase;

public class DoNothing extends CommandBase {

    public DoNothing() {
    	requires(drive);
    }

    protected void initialize() {
    	drive.setBoth(0);
    }

    protected void execute() {
    	drive.setBoth(0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	drive.setBoth(0);
    }

    protected void interrupted() {
    	drive.setBoth(0);
    }
}
