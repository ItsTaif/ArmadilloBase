package org.usfirst.frc.team818.robot.subsystems;

import org.usfirst.frc.team818.robot.commands.ExampleCommand;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ExampleSubsystem extends Subsystem {

	private Talon exampleMotor;
	private boolean exampleEnabled;
	
	public ExampleSubsystem(int exampleMotorPort, boolean exampleEnabled) {
		
		this.exampleEnabled = exampleEnabled;
		
		if (exampleEnabled) {
			exampleMotor = new Talon(exampleMotorPort);
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ExampleCommand());
	}

	public void setSpeed(double speed) {
		if (exampleEnabled) {
			exampleMotor.set(speed);
		}
	}
	
}
