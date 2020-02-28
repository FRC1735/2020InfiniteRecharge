/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.joysticks.AbstractJoystick;
import frc.robot.subsystems.Turret;

public class ControlTurretWithJoystick extends CommandBase {
  private Turret turret;
  private AbstractJoystick joystick;
  public ControlTurretWithJoystick(Turret turret, AbstractJoystick joystick) {
    addRequirements(turret);
    this.turret = turret;
    this.joystick = joystick;
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double v = joystick.getX();
    SmartDashboard.putNumber("attack x", v);
    /*
    double encoderValue = turret.getEncoderValue();

    if (encoderValue > 7661 && encoderValue < 12695) {
      */
      if (Math.abs(v) > 0.1) {
        turret.set(v);
        return;
      } else {
        turret.stop();
      }
    /*}

    turret.stop();*/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turret.stop();
    }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
