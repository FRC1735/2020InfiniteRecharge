/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveLine extends SubsystemBase {
  private WPI_TalonSRX leftMotor;
  private WPI_TalonSRX rightMotor;
  private DifferentialDrive differentialDrive;
  private WPI_VictorSPX leftFollower;
  private WPI_VictorSPX rightFollower;
  public final AHRS gyro;

  public static double ENCODER_TICK_PER_INCH = (4070 / (3.1415926 * 6));
  private int LEFT_ENCODER_OFFSET = 0;

  Logger logger = Logger.getGlobal();

  /**
   * Creates a new DriveLine.
   */
  public DriveLine() {
    logger.info("Initializing DriveLine");
    leftMotor = new WPI_TalonSRX(7);
    rightMotor = new WPI_TalonSRX(6);
    leftFollower = new WPI_VictorSPX(3);
    rightFollower = new WPI_VictorSPX(2);
    gyro = new AHRS(I2C.Port.kMXP);

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

    SmartDashboard.putNumber("gryo.yaw", getYaw());
    SmartDashboard.putNumber("leftEncoder", leftMotor.getSelectedSensorPosition());
    SmartDashboard.putNumber("rightEncoder", rightMotor.getSelectedSensorPosition());
  }

  public WPI_TalonSRX getLeftMotor() {
    return leftMotor;
  }

  public WPI_TalonSRX getRightMotor() {
    return rightMotor;
  }

  public void arcadeDrive(final double joystickX, final double joystickY) {
    logger.info("x: " + joystickX + " y: " + joystickY);
    differentialDrive.arcadeDrive(-joystickY, joystickX, true);
  }

  public void tankDrive(final double joystickAY, final double joystickBY) {
    differentialDrive.tankDrive(joystickAY, joystickBY);
  }

  public void set(ControlMode mode, double leftValue, double rightValue) {
    leftMotor.set(mode, leftValue);
    rightMotor.set(mode, rightValue);
  }

  public double getYaw() {
    return Math.IEEEremainder(gyro.getAngle(), 360);
  }

  public void zeroYaw() {
    gyro.zeroYaw();
  }

  public void resetEncoders() {
    leftMotor.setSelectedSensorPosition(0);
    rightMotor.setSelectedSensorPosition(0);
  }

  public double getDistanceTraveled() {
    double encoderLeftValue = leftMotor.getSelectedSensorPosition() - LEFT_ENCODER_OFFSET;
    double encoderRightValue = rightMotor.getSelectedSensorPosition();
    return (Math.abs(encoderLeftValue) + Math.abs(encoderRightValue)) / 2;
  }
  public void stop() {
    differentialDrive.stopMotor();
  }
}