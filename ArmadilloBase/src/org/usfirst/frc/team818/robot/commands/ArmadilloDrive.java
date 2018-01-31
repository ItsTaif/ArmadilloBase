package org.usfirst.frc.team818.robot.commands;

public class ArmadilloDrive extends CommandBase {
	double speed, pLeft, pRight, swerve;

	public ArmadilloDrive() {
		requires(drive);
	}

	protected void initialize() {

		pLeft = 0;
		pRight = 0;
		drive.resetBothEncoders();
		drive.resetGyro();
		drive.enablePID("rotate");
	}

	protected void execute() {

		speed = oi.getRightY();
		drive.setRotatePoint(-oi.getLeftX() * 90);

		pLeft = speed;
		pRight = speed;
		pLeft += drive.getPIDOutputRotate();
		pRight -= drive.getPIDOutputRotate();
		drive.setBoth(pLeft, pRight);
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
