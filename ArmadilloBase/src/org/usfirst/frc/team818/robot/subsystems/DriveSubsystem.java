package org.usfirst.frc.team818.robot.subsystems;

import org.usfirst.frc.team818.robot.Constants;
import org.usfirst.frc.team818.robot.commands.DriveCommand;
import org.usfirst.frc.team818.robot.utilities.DoublePIDOutput;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

public class DriveSubsystem extends Subsystem {

	Talon[] leftMotors, rightMotors;
	AnalogGyro driveGyro;
	Encoder leftEncoder, rightEncoder;
	Accelerometer accelerometer;

	private static final double[] STRAIGHT_PID_VALUES = { 0.01, 0.001, 0 };
	private static final double[] STRAIGHT_PID_RANGE = { -1, 1 };
	private static final double[] ROTATE_PID_VALUES = { 0.05, 0, 0.1 };
	private static final double[] ROTATE_PID_RANGE = { -0.4, 0.4 };
	private static final double ROTATE_PID_TOLERANCE = 1;

	private PIDController dynamicBrakingControllerLeft, dynamicBrakingControllerRight, gyroController;
	private DoublePIDOutput pidOutputRotate, pidOutputRight, pidOutputLeft;

	private boolean driveEnabled;

	public DriveSubsystem(int[] leftMotorPorts, int[] rightMotorPorts, int gyroPort, int[] leftEncoderPorts,
			int[] rightEncoderPorts, boolean driveEnabled) {

		this.driveEnabled = driveEnabled;

		if (driveEnabled) {
			leftMotors = new Talon[leftMotorPorts.length];
			rightMotors = new Talon[rightMotorPorts.length];

			leftEncoder = new Encoder(leftEncoderPorts[0], leftEncoderPorts[1]);
			rightEncoder = new Encoder(rightEncoderPorts[0], rightEncoderPorts[1]);

			for (int i = 0; i <leftMotorPorts.length; i++)
				leftMotors[i] = new Talon(leftMotorPorts[i]);

			for (int i = 0; i <rightMotorPorts.length; i++)
				rightMotors[i] = new Talon(rightMotorPorts[i]);

			driveGyro = new AnalogGyro(gyroPort);
			accelerometer = new BuiltInAccelerometer();
		}

		pidOutputRight = new DoublePIDOutput();
		pidOutputLeft = new DoublePIDOutput();
		pidOutputRotate = new DoublePIDOutput();

		dynamicBrakingControllerRight = new PIDController(STRAIGHT_PID_VALUES[0], STRAIGHT_PID_VALUES[1],
				STRAIGHT_PID_VALUES[2], rightEncoder, pidOutputRight);
		dynamicBrakingControllerRight.setOutputRange(STRAIGHT_PID_RANGE[0], STRAIGHT_PID_RANGE[1]);
		dynamicBrakingControllerRight.setInputRange(Double.MAX_VALUE, Double.MIN_VALUE);
		dynamicBrakingControllerRight.setSetpoint(0);
		dynamicBrakingControllerRight.setContinuous(false);

		dynamicBrakingControllerLeft = new PIDController(STRAIGHT_PID_VALUES[0], STRAIGHT_PID_VALUES[1],
				STRAIGHT_PID_VALUES[2], leftEncoder, pidOutputLeft);
		dynamicBrakingControllerLeft.setOutputRange(STRAIGHT_PID_RANGE[0], STRAIGHT_PID_RANGE[1]);
		dynamicBrakingControllerLeft.setInputRange(Double.MAX_VALUE, Double.MIN_VALUE);
		dynamicBrakingControllerLeft.setSetpoint(0);
		dynamicBrakingControllerLeft.setContinuous(false);

		gyroController = new PIDController(ROTATE_PID_VALUES[0], ROTATE_PID_VALUES[1], ROTATE_PID_VALUES[2],
				driveGyro, pidOutputRotate);
		gyroController.setOutputRange(ROTATE_PID_RANGE[0], ROTATE_PID_RANGE[1]);
		gyroController.setInputRange(Double.MAX_VALUE, Double.MIN_VALUE);
		gyroController.setAbsoluteTolerance(ROTATE_PID_TOLERANCE);
		gyroController.setContinuous();
		gyroController.setSetpoint(0);
				
		leftEncoder.setDistancePerPulse((2*Math.PI*Constants.wheelRadius)/360);
		rightEncoder.setDistancePerPulse((2*Math.PI*Constants.wheelRadius)/360);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveCommand());
	}

	public void setLeft(double speed) {
		if (driveEnabled) {
			for (int i = 0; i < leftMotors.length; i++)
				leftMotors[i].set(-speed);
		}
	}

	public void setRight(double speed) {
		if (driveEnabled) {
			for (int i = 0; i < rightMotors.length; i++)
				rightMotors[i].set(speed);
		}
	}

	public void setBoth(double speedLeft, double speedRight) {
		if (driveEnabled) {
			setLeft(speedLeft);
			setRight(speedRight);
		}
	}

	public void setBoth(double speed) {
		if (driveEnabled) {
			setLeft(speed);
			setRight(speed);
		}
	}

	public void resetGyro() {
		if (driveEnabled) {
			driveGyro.reset();
		}
	}

	public double getAngle() {
		if (driveEnabled) {
			return driveGyro.getAngle() % 360;
		} else
			return 0;
	}

	public int getLeftRotation() {
		if (driveEnabled) {
			return leftEncoder.get();
		} else
			return 0;
	}

	public int getRightRotation() {
		if (driveEnabled) {
			return rightEncoder.get();
		} else
			return 0;
	}

	public void resetBothEncoders() {
		if (driveEnabled) {
			rightEncoder.reset();
			leftEncoder.reset();
		}
	}

	public boolean getLeftDirection() {
		if (driveEnabled) {
			return leftEncoder.getDirection();
		} else
			return false;

	}

	public boolean getRightDirection() {
		if (driveEnabled) {
			return rightEncoder.getDirection();
		} else
			return false;

	}

	public void enablePID(String pidType) {
		if (driveEnabled) {
			if (pidType.equals("straight")) {
				if (gyroController.isEnabled())
					gyroController.disable();
				if (!dynamicBrakingControllerRight.isEnabled())
					dynamicBrakingControllerRight.enable();
				if (!dynamicBrakingControllerLeft.isEnabled())
					dynamicBrakingControllerLeft.enable();
			} else if (pidType.equals("rotate")) {
				if (dynamicBrakingControllerRight.isEnabled())
					dynamicBrakingControllerRight.disable();
				if (dynamicBrakingControllerLeft.isEnabled())
					dynamicBrakingControllerLeft.disable();
				if (!gyroController.isEnabled())
					gyroController.enable();
			}
		}
	}

	public void disablePID() {
		if (driveEnabled) {
			if (gyroController.isEnabled())
				gyroController.disable();
			if (dynamicBrakingControllerRight.isEnabled())
				dynamicBrakingControllerRight.disable();
			if (dynamicBrakingControllerLeft.isEnabled())
				dynamicBrakingControllerLeft.disable();
		}
	}

	public void setRotatePoint(double angle) {
		if (driveEnabled)
			gyroController.setSetpoint(angle);
	}

	public boolean rotateOnTarget() {
		return (driveEnabled) ? gyroController.onTarget() : true;
	}

	public double getPIDOutputRotate() {
		return (driveEnabled) ? pidOutputRotate.get() : 0;
	}

	public double getPIDOutputLeft() {
		return (driveEnabled) ? pidOutputLeft.get() : 0;
	}

	public double getPIDOutputRight() {
		return (driveEnabled) ? pidOutputRight.get() : 0;
	}

	public void setRotatePID(double p, double i, double d) {
		gyroController.setPID(p, i, d);
	}

}