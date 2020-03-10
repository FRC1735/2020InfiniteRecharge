/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.Random;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Lighting;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ReflectTube extends InstantCommand {
  private Lighting lighting;
  public ReflectTube(final Lighting lighting) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(lighting);
    this.lighting = lighting;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //lighting.off();
    lighting.reflectTube(true, true, false, false, false);
    //Random random = new Random();
    //lighting.setLEDColor(random.nextInt(35), 0, 255, 0);
  }
}
