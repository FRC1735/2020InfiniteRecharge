package frc.robot.joysticks;

import edu.wpi.first.wpilibj.Joystick;

public abstract class AbstractJoystick {
    protected Joystick joystick;
    protected Role role;

    public AbstractJoystick(final Joystick joystick, final Role role) {
        this.joystick = joystick;
        this.role = role;
        initializeKeymap();
    }

    public boolean getRawButton(int buttonId) {
        return joystick.getRawButton(buttonId);
    }

    abstract void initializeKeymap();

    public abstract double getX();

    public abstract double getY();

    public abstract double getSwivelStickX();

    public abstract double getSwivelStickY();

    public abstract boolean isCapableOfSoloTankMode();
}
