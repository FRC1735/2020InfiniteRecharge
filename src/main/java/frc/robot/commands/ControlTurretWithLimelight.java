/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.LimeLight.LedMode;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ControlTurretWithLimelight extends PIDCommand {
  private LimeLight limeLight;

  /**
   * Creates a new ControlTurretWithLimelight.
   */
  public ControlTurretWithLimelight(Turret turret, LimeLight limeLight) {
    super(
        // The controller that the command will use
        new PIDController(0.055, 0, 0), // TODO replace with real values when they are determined
        // This should return the measurement
        () -> {
          double tx = limeLight.getTx();
          return tx;
        },
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          if (Math.abs(limeLight.getTx()) > 1) {
            double clampedOutput = MathUtil.clamp(output, -.3, .3);
            System.out.println("output: " + output + ", clamped: " + clampedOutput);
            turret.set(clampedOutput);
          }
        });

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(turret, limeLight);

    // Configure additional PID options by calling `getController` here.
    getController().enableContinuousInput(-180, 180);
    getController().setTolerance(2);

    this.limeLight = limeLight;

    if (Constants.TUNING_MODE) {
      //SmartDashboard.putNumber(Constants.SD_TURRET_PID_P, 0);
      //SmartDashboard.putNumber(Constants.SD_TURRET_PID_I, 0);
      //SmartDashboard.putNumber(Constants.SD_TURRET_PID_D, 0);
    }
  }
  
  @Override
  public void initialize() {
    limeLight.ledOn();
  }

  @Override
  public void execute() {
    super.execute();
    if (Constants.TUNING_MODE) {
      getController().setP(SmartDashboard.getNumber(Constants.SD_TURRET_PID_P, 0));
      getController().setI(SmartDashboard.getNumber(Constants.SD_TURRET_PID_I, 0));
      getController().setD(SmartDashboard.getNumber(Constants.SD_TURRET_PID_D, 0));
      System.out.println("P: " + getController().getP());
      System.out.println("I: " + getController().getI());
      System.out.println("D: " + getController().getD());
    }
  }

  @Override
  public void end(boolean interrupted) {
    limeLight.ledOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false; //getController().atSetpoint();
  }
}
