/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  private WPI_VictorSPX main;
  private WPI_VictorSPX follower;

  /**
   * Creates a new Climber.
   */
  public Climber() {
    this.main = new WPI_VictorSPX(0); // TODO
    this.follower = new WPI_VictorSPX(0); // TODO

    follower.follow(main);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void engage() {
    main.set(ControlMode.PercentOutput, 0.5);
  }

  public void disengage() {
    main.stopMotor();
  }
}
