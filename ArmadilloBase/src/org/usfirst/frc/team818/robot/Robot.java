
package org.usfirst.frc.team818.robot;

import org.usfirst.frc.team818.robot.commands.CommandBase;
import org.usfirst.frc.team818.robot.utilities.RobotLog;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Robot extends IterativeRobot {

	Command autonomous;

	public void robotInit() {
		CommandBase.init();
		RobotLog.init();
	}

	public void autonomousInit() {

		autonomous = AutonomousSelector.getSelectedAutonomous();
		if (autonomous != null)
			autonomous.start();
		try {
			NetworkTable.getTable("Root").putNumber("allianceStation",
					DriverStation.getInstance().getAlliance().ordinal());
			NetworkTable.getTable("Root").putBoolean("autonStarted", true);
		} catch (Exception e) {

		}

	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (autonomous != null)
			autonomous.cancel();
	}

	public void teleopPeriodic() {
		/**
		 * This "Scheduler" object keeps track of which Commands need to be run by the
		 * various subsystems on the robot. By telling the "Scheduler" to run
		 * periodically during teleop, it is telling the robot to constantly execute all
		 * necessary commands
		 */
		Scheduler.getInstance().run();
	}

	public void disabledInit() {
		CommandBase.disable();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
