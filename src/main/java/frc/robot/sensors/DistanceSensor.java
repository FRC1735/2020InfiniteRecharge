/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Sharp GP2Y0A41SK0F Analog Distance Sensor 4-30cm
 * 
 * https://www.pololu.com/product/2464
 */
public class DistanceSensor {
    Logger logger = Logger.getGlobal();
    AnalogInput analogInput;

    public DistanceSensor(int port) {
        //logger.info("Initializing distance sensor on port " + port);
        analogInput = new AnalogInput(port);
    }

    public double getDistance() {
        double voltage = Math.max(analogInput.getVoltage(), 0.00001);
        double distance = 12.84 * Math.pow(voltage, -0.9824);

        //logger.info("raw distance: " + distance);

        double result = Math.max(Math.min(distance, 35.0), 4.5);
        //logger.info("getDistance: " + result);

        return result;
    }
}
