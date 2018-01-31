package org.usfirst.frc.team818.robot.commands;

public class DynamicBraking extends CommandBase {

	public DynamicBraking() {
		requires(drive);
	}

	protected void initialize() {
		
		drive.setBoth(0);
		drive.resetBothEncoders();
		drive.enablePID("straight");
	}

	protected void execute() {
		drive.setBoth(drive.getPIDOutputLeft(), drive.getPIDOutputRight());		
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		drive.setBoth(0);
		drive.disablePID();
	}

	protected void interrupted() {
		drive.setBoth(0);
		drive.disablePID();
	}
}
