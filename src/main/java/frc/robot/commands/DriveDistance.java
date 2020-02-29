/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.DriveLine;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveDistance extends PIDCommand {
  private DriveLine driveLine;

  /**
   * Creates a new DriveDistance.
   */
  public DriveDistance(DriveLine driveLine, double distanceInches) {
    super(
        // The controller that the command will use
        new PIDController(0.00125 * .45, 0.00033, 0),
        // This should return the measurement
        () -> driveLine.getDistanceTraveled(),
        () -> {
          double target = distanceInches * DriveLine.ENCODER_TICK_PER_INCH;
          //System.out.println("target: " + target);
          return target;
        },
        // This uses the output
        output -> {
          // Use the output here
          //System.out.println("output: " + output);
          double clampedOutput = MathUtil.clamp(output, -.3, .3);
          // driveLine.arcadeDrive(clampedOutput, clampedOutput);
          driveLine.set(ControlMode.PercentOutput, clampedOutput, -clampedOutput);
        });
    
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(driveLine);
    this.driveLine = driveLine;

    driveLine.getLeftMotor().setNeutralMode(NeutralMode.Brake);
    driveLine.getRightMotor().setNeutralMode(NeutralMode.Brake);

    getController().setTolerance(25);
  }

  @Override
  public void initialize() {
    // TODO Auto-generated method stub
    super.initialize();
    driveLine.resetEncoders();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
