/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tube;

public class ShootOne extends CommandBase {
  Shooter shooter;
  Tube tube;

  /**
   * Creates a new ShootOne.
   */
  public ShootOne(Shooter shooter, Tube tube) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);

    this.shooter = shooter;
    this.tube = tube;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //tube.up();
    //shooter.engage();
    //shooter.setVelocity(1000 * .75);
    //shooter.engage();

    shooter.setVelocity(500 * .85);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("shooter v", shooter.getVelocity());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.disengage();
    //tube.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
