package org.usfirst.frc.team818.robot.utilities;

public class MathUtil {

	public static double limitMin(double input, double minimum){
		if(input < minimum)
			return minimum;
		else
			return input;
	}
	
	public static double limitMax (double input, double maximum){
		if(input > maximum)
			return maximum;
		else
			return input;
	}
	
	public static double setLimits(double input, double minimum, double maximum){
		if(input < minimum)
			return minimum;
		else if(input > maximum)
			return maximum;
		else
			return input;
	}
	
	public static float limitMin(float input, float minimum){
		if(input < minimum)
			return minimum;
		else
			return input;
	}
	
	public static float limitMax (float input, float maximum){
		if(input > maximum)
			return maximum;
		else
			return input;
	}
	
	public static float setLimits(float input, float minimum, float maximum){
		if(input < minimum)
			return minimum;
		else if(input > maximum)
			return maximum;
		else
			return input;
	}
	
}
