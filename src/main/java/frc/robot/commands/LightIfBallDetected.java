/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lighting;
import frc.robot.subsystems.Tube;

public class LightIfBallDetected extends CommandBase {
  Lighting lighting;
  Tube tube;

  /**
   * Creates a new LightIfBallDetected.
   */
  public LightIfBallDetected(Lighting lighting, Tube tube) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(lighting, tube);
    this.lighting = lighting;
    this.tube = tube;
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (tube.getDistanceSensorGroup().isPowerCellDetected(0)) {
      lighting.blue();
    } else {
      lighting.yellow();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
