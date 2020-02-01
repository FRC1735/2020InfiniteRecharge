/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Lighting extends SubsystemBase {
 private AddressableLED led; 
 private AddressableLEDBuffer buffer; 
 private int LED_COUNT = 240;
 
  /**
   * Creates a new Lighting.
   */
  public Lighting() {
    led = new AddressableLED(9);
    buffer = new AddressableLEDBuffer(LED_COUNT);
    led.setLength(buffer.getLength());

    led.setData(buffer);
    led.start();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void on() {
    led.start();
  }

  public void off() {
    setColor(0, 0, 0);
    led.stop();
  }

  public void blue() {
   setColor(0, 0, 255);
  }

  public void yellow() {
    setColor(255, 255, 0);
  }

  public void setColor(int r, int g, int b) {
    for (int i = 0; i < buffer.getLength(); i++) {
      buffer.setRGB(i, r, g, b);
    }
    led.setData(buffer);
  }
}
