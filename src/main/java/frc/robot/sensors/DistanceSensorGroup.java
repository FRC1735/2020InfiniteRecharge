/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DistanceSensorGroup {
    Logger logger = Logger.getGlobal();

    List<DistanceSensor> sensors;

    double MIN_DETECTION_THRESHOLD_CM = 3.5;
    double MAX_DETECTION_THRESTHOLD_CM = 11;

    public DistanceSensorGroup(int portA, int portB, int portC, int portD, int portE) {
        sensors = new ArrayList();
        sensors.add(new DistanceSensor(portA));
        sensors.add(new DistanceSensor(portB));
        /*sensors.add(new DistanceSensor(portC));
        sensors.add(new DistanceSensor(portD));
        sensors.add(new DistanceSensor(portE));*/
    }

    public List<Double> getDistances() {
        List<Double> distances = new ArrayList<>();

        for (int i = 0; i < sensors.size(); i++) {
            distances.add(sensors.get(i).getDistance());
        }

        return distances;
    }

    public boolean isPowerCellDetected(int position) {
        if (position < 0 || position >= sensors.size()) {
            logger.warning("Tried to access an invalid sensor position");
            return false;
        }

        double distance = sensors.get(position).getDistance();
        //SmartDashboard.putNumber("Distance Sensor " + position, distance);

        boolean detected = (distance > MIN_DETECTION_THRESHOLD_CM && distance < MAX_DETECTION_THRESTHOLD_CM);

        if (detected) {
            logger.info("Power Cell detected at position " + position);
        } else {
            logger.info("Power Cell not detected at position " + position);
        }

        return detected;
    }
}
