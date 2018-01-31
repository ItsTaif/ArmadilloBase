package org.usfirst.frc.team818.robot.commands.timedcommands;

import org.usfirst.frc.team818.robot.Constants;
import org.usfirst.frc.team818.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

public class Drive4Distance extends CommandBase {

	double distance, speed, pLeft, pRight;
	int leftTicks, rightTicks;
	Timer timer;

	public Drive4Distance(double speed, double distance) {
		requires(drive);
		timer = new Timer();
		this.distance = distance;
		this.speed = speed;
		
		if (this.distance < 0) {
			this.speed = -speed;
		}
	}

	public Drive4Distance(double distance) {
		requires(drive);
		timer = new Timer();
		this.distance = distance;
		this.speed = 0.5;
		
		if (this.distance < 0) {
			this.speed = -speed;
		}
	}
	
	protected void initialize() {
		drive.setBoth(0);
		drive.resetBothEncoders();
		drive.resetGyro();
		timer.start();
		
		pLeft = 0;
		pRight = 0;

		drive.enablePID("rotate");
		drive.setRotatePoint(0);
	}

	protected void execute() {
	
		leftTicks = Math.abs(drive.getLeftRotation());
		rightTicks = Math.abs(drive.getRightRotation());

		pLeft = speed;
		pRight = speed;
		pRight -= drive.getPIDOutputRotate();
		drive.setBoth(pLeft, pRight);
	}

	protected boolean isFinished() {
		if (timer.hasPeriodPassed(5)
				|| (Math.abs(Constants.cycleDistance * ((leftTicks + rightTicks / 2) / 360)) >= Math.abs(distance))) {
			return true;
		} else
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
