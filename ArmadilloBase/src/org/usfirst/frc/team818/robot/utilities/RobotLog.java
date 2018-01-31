package org.usfirst.frc.team818.robot.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Matthew P.
 * Team 818 The Steel Armadillos 
 * 
 * This class is used to display messages on the SmartDashboard for quick debugging
 * and has practical applications in testing. Integers, booleans, and other data
 * can be written to the SmartDashboard to identify whether or not the program is
 * behaving in the intended manner.
 * 
 */

public abstract class RobotLog {

	private static String[] log;
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("[HH:mm:ss]\t");
	private static Date date;
	
	public static void init(){
		
		 log = new String[64];
		for(int i = 0; i < 64; i++)
			log[i] = "";
		
		updateLog();
		
	}
	
	public static void putMessage(String message){
		
		for(int i = 63; i > 0; i--)
			log[i] = log[i-1];
		
		date = new Date();
		log[0] = timeFormat.format(date) + message;
		
		updateLog();
		
	}
	
	private static void updateLog(){
		
		String logMessage = "";
		for(int i = 0; i < 64; i++)
			logMessage += "\n" + log[i];
		logMessage = logMessage.replaceFirst("\n", "");
		
		try{
			SmartDashboard.putString("RobotLog", logMessage);
		} catch (IllegalArgumentException iae){
			System.out.println(iae.getMessage());
		}
		
	}
	
}
