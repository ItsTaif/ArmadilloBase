package org.usfirst.frc.team818.robot.commands.timedcommands;

import org.usfirst.frc.team818.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

public class ExampleTimedCommand extends CommandBase {

	public double time;
	private Timer timer;

	public ExampleTimedCommand(double time) {
		requires(example);
		this.time = time;
		timer = new Timer();
	}

	protected void initialize() {
		timer.start();
	}

	protected void execute() {
			
	}

	protected boolean isFinished() {
		
		return timer.hasPeriodPassed(time);

	}

	protected void end() {
		timer.stop();
	}

	protected void interrupted() {
		timer.stop();
	}
}
