package frc.robot.joysticks;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class JoystickFactory {
    public AbstractJoystick get(final Joystick joystick, final Role role) {
        if (DriverStation.getInstance().getJoystickIsXbox(joystick.getPort())) {
            return new XBoxJoystick(joystick, role);
        } else {
            // TODO - define other joysticks
            return null;
        }
    }
}
