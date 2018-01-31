package org.usfirst.frc.team818.robot.commands.timedcommands;

import org.usfirst.frc.team818.robot.commands.CommandBase;
import org.usfirst.frc.team818.robot.utilities.RobotLog;

import edu.wpi.first.wpilibj.Timer;

public class TurnAngle extends CommandBase {

	public double angle;
	private double speed;
	private Timer timer;

	public TurnAngle(double angle, double speedTurn) {
		requires(drive);
		this.angle = angle;
		speed = speedTurn;
		timer = new Timer();
	}
	
	public TurnAngle(double angle) {
		requires(drive);
		this.angle = angle;
		timer = new Timer();
		this.speed = 0.3;

	}

	protected void initialize() {
		RobotLog.putMessage("Running TurnAngle");
		drive.resetGyro();
		drive.setRotatePoint(angle);
		drive.enablePID("rotate");
		timer.start();
	}

	protected void execute() {
		
		speed = drive.getPIDOutputRotate();
		drive.setBoth(speed, -speed);
		
	}

	protected boolean isFinished() {
		
		return drive.rotateOnTarget() || timer.hasPeriodPassed(3);

	}

	protected void end() {
		
		timer.stop();
		drive.setRotatePoint(0);
		drive.disablePID();
		drive.setBoth(0, 0);

	}

	protected void interrupted() {
		
		timer.stop();
		drive.setRotatePoint(0);
		drive.disablePID();
		drive.setBoth(0, 0);
	}
}
