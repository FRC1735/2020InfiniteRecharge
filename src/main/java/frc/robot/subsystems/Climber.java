/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  private WPI_VictorSPX deployer;
  private WPI_VictorSPX wench;

  /**
   * Creates a new Climber.
   */
  public Climber() {
    this.deployer = new WPI_VictorSPX(0); // TODO
    this.wench = new WPI_VictorSPX(0); // TODO

    this.deployer.setNeutralMode(NeutralMode.Brake);
    this.wench.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void climb() {
    wench.set(ControlMode.PercentOutput, 0.5);
  }

  public void deployUp() {
    deployer.set(ControlMode.PercentOutput, 0.5);
  }

  public void deployDown() {
    deployer.set(ControlMode.PercentOutput, -0.5);
  }

  public void stopDeploy() {
    deployer.stopMotor();
  }

  public void stopClimb() {
    wench.stopMotor();
  }

  public void stop() {
    wench.stopMotor();
    deployer.stopMotor();
  }
}
