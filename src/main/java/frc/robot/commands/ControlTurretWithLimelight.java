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
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ControlTurretWithLimelight extends PIDCommand {
  /**
   * Creates a new ControlTurretWithLimelight.
   */
  public ControlTurretWithLimelight(Turret turret, LimeLight limeLight, final DoubleSupplier p, final DoubleSupplier i,
      final DoubleSupplier d) {
    super(
        // The controller that the command will use
        new PIDController(SmartDashboard.getNumber("Turret P", 0), 
            SmartDashboard.getNumber("Turret I", 0), SmartDashboard.getNumber("Turret D", 0)),
        // This should return the measurement
        () -> {
          double tx = limeLight.getTx();
          System.out.println("tx: " + tx);
          return tx;
        }
          ,
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          //if (limeLight.getTv() > 0) {
          if (Math.abs(limeLight.getTx()) > 1) {
            double clampedOutput = MathUtil.clamp(output, -.3, .3);
            System.out.println("output: " + output + ", clamped: " + clampedOutput);
            turret.set(clampedOutput);
          }
          //} else {
          //  turret.stop();
          //}
          // Use the output here
        });

    System.out.println("P: " + p.getAsDouble());
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(turret, limeLight);

    // Configure additional PID options by calling `getController` here.
    getController().enableContinuousInput(-180, 180);
    getController().setTolerance(2);
  }

  @Override
  public void execute() {
    super.execute();
    getController().setP(SmartDashboard.getNumber("Turret P", 0));
    getController().setI(SmartDashboard.getNumber("Turret I", 0));
    getController().setD(SmartDashboard.getNumber("Turret D", 0));
    System.out.println("P: " + getController().getP());
    System.out.println("I: " + getController().getI());
    System.out.println("D: " + getController().getD());

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //System.out.println("P: " + getController().getP());
    return false; //getController().atSetpoint();
  }
}
