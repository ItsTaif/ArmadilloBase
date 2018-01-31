package org.usfirst.frc.team818.robot.commands;

public class ArcadeDrive extends CommandBase {

	double speedX, speedY, speedLeft, speedRight;

	public ArcadeDrive() {
		requires(drive);
	}

	protected void initialize() {
		drive.setBoth(0);

	}

	protected void execute() {

		speedY = oi.getGamepadLeftY();
		speedX = oi.getGamepadLeftX();

		if (speedY < 0) {
			speedLeft = speedY + speedX;
			speedRight = speedY - speedX;
		} else {
			speedLeft = speedY - speedX;
			speedRight = speedY + speedX;
		}

		drive.setBoth(speedLeft*0.7, speedRight*0.7);
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
