package org.usfirst.frc.team818.robot;

import org.usfirst.frc.team818.robot.autonomi.DoNothing;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Matthew P. Team 818 The Steel Armadillos
 * 
 * This class allows the driver to actively select autonomous modes to run
 * during the competition. The SmartDashboard can be modified to include a
 * variety of autonomi. For the code to run properly, the items indexed in the
 * String array "autonomi" must match their numbers on the SmartDashboard.
 * 
 * To modify the SmartDashboard, it is necessary to create a new SmartDashboard
 * project in LabView. There are other options for customizing the
 * SmartDashboard, but the simplest method is using LabView.
 *
 */

public class AutonomousSelector {

	public static final String[] autonomi = { "DoNothing", "ExampleAuton" };

	public static Command getSelectedAutonomous() {

		int autoIndex;
		Command autonomous = new DoNothing();

		try {

			autoIndex = Integer.parseInt(SmartDashboard.getString("Autonomous", "DoNothing"));
		} catch (Exception exc) {
			
			autoIndex = 0;
		}

		try {
			
			autonomous = (CommandGroup) Class.forName("org.usfirst.frc.team818.robot.autonomi." + autonomi[autoIndex])
					.newInstance();

		} catch (Exception e) {
			
			autonomous = new DoNothing();
		}

		return autonomous;

	}

}
