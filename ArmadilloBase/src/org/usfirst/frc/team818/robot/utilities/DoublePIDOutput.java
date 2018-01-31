package org.usfirst.frc.team818.robot.utilities;

import edu.wpi.first.wpilibj.PIDOutput;


public class DoublePIDOutput implements PIDOutput {
	
	private double output;
	
	public DoublePIDOutput(double baseOutput) {
		super();
		output = baseOutput;
	}
	
	public DoublePIDOutput() {
		this(0);
	}
	
	public double get() {
		return output;
	}
	
	public void pidWrite(double output) {
		this.output = output;
	}
	
}
