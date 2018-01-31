package org.usfirst.frc.team818.robot;

import org.usfirst.frc.team818.robot.commands.ArcadeDrive;
import org.usfirst.frc.team818.robot.commands.ArmadilloDrive;
import org.usfirst.frc.team818.robot.commands.DynamicBraking;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class OI {

	private Joystick leftStick, rightStick, gamepad;
	private JoystickButton exampleButtonLeft, exampleButtonRight;
	private Trigger exampleTrigger;

	public OI() {

		// Instantiating Joysticks
		leftStick = new Joystick(0);
		rightStick = new Joystick(1);
		gamepad = new Joystick(2);

		// Instantiating buttons
		exampleButtonLeft = new JoystickButton(leftStick, 1);
		exampleButtonRight = new JoystickButton(rightStick, 1);

		// Instantiating Triggers
		exampleTrigger = new Trigger() {
			public boolean get() {
				return rightStick.getRawButton(3) && leftStick.getRawButton(3);
			}
		};
		
		// Binding buttons to commands
		exampleButtonLeft.whenPressed(new DynamicBraking());
		exampleButtonRight.whenPressed(new ArmadilloDrive());
		exampleTrigger.whenActive(new ArcadeDrive());
	}

	// Joystick Axes
	public double getLeftY() {
		return (Math.abs(leftStick.getY()) > 0.1) ? -leftStick.getY() : 0;
	}

	public double getRightY() {
		return (Math.abs(rightStick.getY()) > 0.1) ? -rightStick.getY() : 0;
	}

	public double getLeftX() {
		return (Math.abs(leftStick.getX()) > 0.1) ? -leftStick.getX() : 0;
	}

	public double getRightX() {
		return (Math.abs(rightStick.getX()) > 0.1) ? -rightStick.getX() : 0;
	}

	public double getGamepadLeftY() {
		return (Math.abs(gamepad.getRawAxis(1)) > 0.1) ? -gamepad.getRawAxis(1) : 0;
	}

	public double getGamepadRightY() {
		return (Math.abs(gamepad.getRawAxis(3)) > 0.1) ? -gamepad.getRawAxis(3) : 0;
	}

	public double getGamepadLeftX() {
		return (Math.abs(gamepad.getRawAxis(2)) > 0.1) ? -gamepad.getRawAxis(0) : 0;
	}

	public double getGamepadRightX() {
		return (Math.abs(gamepad.getRawAxis(4)) > 0.1) ? -gamepad.getRawAxis(2) : 0;
	}
}
