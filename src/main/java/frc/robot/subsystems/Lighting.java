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
 private int LED_COUNT = 70;
 
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

  public void clear() {
    setColor(0, 0, 0);
  }

  public void blue() {
   setColor(0, 0, 255);
  }

  public void yellow() {
    setColor(255, 255, 0);
  }

  public void green() {
    setColor(0, 255, 0);
  }


  // reflectTube(true, true, true, false, false)
  // reflectTube(true, false, false, false, false)
  public void reflectTube(boolean a, boolean b, boolean c, boolean d, boolean e) {
    if (a == true) {
      setColorSection(0, 7, 0, 255, 0);
      setColorSection(63, 7, 0, 255, 0);
    } else {
      setColorSection(0, 7, 0, 0, 0);
      setColorSection(56, 7, 0, 0, 0);
    }
    if (b == true) {
      setColorSection(7, 7, 0, 0, 255);
      setColorSection(49, 7, 0, 0, 255);
    } else {
      setColorSection(7, 7, 0, 0, 0);
      setColorSection(42, 7, 0, 0, 0);
    }
    if (c == true) {
      setColorSection(14, 7, 255, 255, 0);
      setColorSection(35, 7, 255, 255, 0);
    } else {
      setColorSection(14, 7, 0, 0, 0);
      setColorSection(28, 7, 0, 0, 0);
    }
    if (d == true) {
      setColorSection(21, 7, 255, 0, 0);
      setColorSection(21, 7, 255, 0, 0);
    } else {
      setColorSection(21, 7, 0, 0, 0);
      setColorSection(14, 7, 0, 0, 0);
    }
    if (e == true) {
      setColorSection(28, 7, 0, 255, 0);
      setColorSection(7, 7, 0, 255, 0);
    } else {
      setColorSection(28, 7, 0, 0, 0);
      setColorSection(0, 7, 0, 0, 0);
    }
  }
  
  // TODO - modify this to set LEDs on both sides
  // setColorSection(0, 5, 0, 255, 0);
  public void setColorSection(int start, int length, int r, int g, int b) {
    
    for (int i = start; i < start + length ;i++) {
      buffer.setRGB(i, r, g, b);
    }
    led.setData(buffer);
  }
  
  public void setColor(int r, int g, int b) {
    for (int i = 0; i < buffer.getLength(); i++) {
      buffer.setRGB(i, r, g, b);
    }
    led.setData(buffer);
  }

  public void setLEDColor(int position, int r, int g, int b) {

    // 0 -> 69
    // 1 -> 68
    // 2 -> 67
    // reflect left with right
    buffer.setRGB(position, r, g, b);
    buffer.setRGB(getLeftFromRight(position), r, g, b);
    led.setData(buffer);

  }
  
  public int getLeftFromRight(int x) {
    return ( -x + 69);
  }
}
