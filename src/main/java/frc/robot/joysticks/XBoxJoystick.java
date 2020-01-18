package frc.robot.joysticks;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.PrintLogCommand;

public class XBoxJoystick extends AbstractJoystick {
    private static final int L_AXIS_X = 0;
    private static final int L_AXIS_Y = 1;
    private static final int Trigger_L = 2;
    private static final int Trigger_R = 3;
    private static final int R_AXIS_X = 4;
    private static final int R_AXIS_Y = 5;
    private static final int Button_A = 0;
    private static final int Button_B = 1;
    private static final int Button_X = 2;
    private static final int Button_Y = 3;
    private static final int Bumper_L = 4;
    private static final int Bumper_R = 5;
    private static final int Button_Back = 6;
    private static final int Button_Start = 7;
    private static final int L_Stick_Press = 8;
    private static final int R_Stick_Press = 9;
    // TODO - [D15-29] - define all remaining buttons on this joystick as
    // named variables, like you see above

    public XBoxJoystick(final Joystick joystick, final Role role) {
        super(joystick, role);
    }

    @Override
    public double getX() {
        return joystick.getRawAxis(L_AXIS_X);
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
            JoystickButton buttonA = new JoystickButton(joystick, Button_A);
            buttonA.whenPressed(new PrintLogCommand("Hello I am here to destroy your world."));
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
