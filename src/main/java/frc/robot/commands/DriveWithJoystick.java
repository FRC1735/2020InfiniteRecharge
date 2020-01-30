/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.joysticks.AbstractJoystick;
import frc.robot.subsystems.DriveLine;

public class DriveWithJoystick extends CommandBase {
  private AbstractJoystick joystick;
  private DriveLine driveLine;

  /**
   * Creates a new DriveWithJoystick.
   */
  public DriveWithJoystick(AbstractJoystick joystick, DriveLine driveLine) {
    addRequirements(driveLine);

    this.joystick = joystick;
    this.driveLine = driveLine;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveLine.tankDrive(joystick.getSwivelStickX(), joystick.getSwivelStickY());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveLine.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
