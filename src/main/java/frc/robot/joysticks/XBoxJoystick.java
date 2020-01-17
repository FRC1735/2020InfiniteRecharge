package frc.robot.joysticks;

import edu.wpi.first.wpilibj.Joystick;

public class XBoxJoystick extends AbstractJoystick {
    private static final int AXIS_X = 0;
    // TODO - [D15-29] - define all remaining buttons on this joystick as
    // named variables, like you see above

    public XBoxJoystick(final Joystick joystick, final Role role) {
        super(joystick, role);
    }

    @Override
    public double getX() {
        return joystick.getRawAxis(AXIS_X);
    }

    @Override
    public double getY() {
        // TODO - [D15-29]
        //return joystick.getRawAxis(AXIS_Y);
        return 0;
    }

    @Override
    public double getSwivelStickX() {
        // TODO - [D15-29]
        //return joystick.getRawAxis(SWIVEL_X);
        return 0;
    }

    @Override
    public double getSwivelStickY() {
        // TODO - [D15-29]
        //return joystick.getRawAxis(SWIVEL_Y);
        return 0;
    }

    @Override
    void initializeKeymap() {
        switch (role) {
            case DRIVER_LEFT:
                // TODO - [D15-29]
                break;

            case DRIVER_RIGHT:
            case OPERATOR:
                break;
        }
    }

    @Override
    public boolean isCapableOfSoloTankMode() {
        return true;
    }
}
