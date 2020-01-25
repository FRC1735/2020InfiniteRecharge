/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveLine extends SubsystemBase {
  private WPI_TalonSRX leftMotor;
  private WPI_TalonSRX rightMotor;
  private DifferentialDrive differentialDrive;
  private WPI_VictorSPX leftFollower;
  private WPI_VictorSPX rightFollower;
  
  /**
   * Creates a new DriveLine.
   */
  public DriveLine() {
    leftMotor = new WPI_TalonSRX(1);
    rightMotor = new WPI_TalonSRX(2);
    leftFollower = new WPI_VictorSPX(3);
    rightFollower = new WPI_VictorSPX(4);

    leftFollower.follow(leftMotor);
    rightFollower.follow(rightMotor);
    leftFollower.setSafetyEnabled(false);
    rightFollower.setSafetyEnabled(false);

    differentialDrive = new DifferentialDrive(leftMotor, rightMotor);
    differentialDrive.setSafetyEnabled(true);
    differentialDrive.setExpiration(0.1);
    differentialDrive.setMaxOutput(1.0);

  }

  @Override
  public void periodic() {

  }
  
  public WPI_TalonSRX getLeftMotor() {
    return leftMotor;
  }
  
  public WPI_TalonSRX getRightMotor() {
    return rightMotor;
  }

  public void arcadeDrive(final double joystickX, final double joystickY) {
    differentialDrive.arcadeDrive(joystickY, joystickX, true);
  }

  public void stop() {
    differentialDrive.stopMotor();
  }
}
