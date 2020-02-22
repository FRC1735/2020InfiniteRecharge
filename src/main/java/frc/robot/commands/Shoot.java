/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Shoot extends PIDCommand {
  /**
   * Creates a new Shoot.
   */
  public Shoot(Shooter shooter) {
    super(
        // The controller that the command will use
        new PIDController(0.00125 * .45, 0.00033, 0),
        // This should return the measurement
        () -> shooter.getVelocity(),
        // This should return the setpoint (can also be a constant)
        () -> -59000,
        // This uses the output
        output -> {
          SmartDashboard.putNumber("shoot pid output", output);
          double speed = MathUtil.clamp(Math.abs(output), 0, 1);
          shooter.set(speed);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(shooter);

    getController().setTolerance(100);
  }

  @Override
  public void execute() {
    super.execute();
    if (Constants.TUNING_MODE) {
      PIDController controller = getController();
      double currentP = controller.getP();
      double currentI = controller.getI();
      double currentD = controller.getD();

      double sdP = SmartDashboard.getNumber(Constants.SD_SHOOTER_PID_P, 0);
      double sdI = SmartDashboard.getNumber(Constants.SD_SHOOTER_PID_I, 0);
      double sdD = SmartDashboard.getNumber(Constants.SD_SHOOTER_PID_D, 0);

      if (currentP != sdP) {
        controller.setP(sdP);
      }

      if (currentI != sdI) {
        controller.setI(sdI);
      }

      if (currentD != sdD) {
        controller.setD(sdD);
      }
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
