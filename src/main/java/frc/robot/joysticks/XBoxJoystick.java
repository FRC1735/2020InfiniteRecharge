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
    private static final int Button_A = 1;
    private static final int Button_B = 2;
    private static final int Button_X = 3;
    private static final int Button_Y = 4;
    private static final int Bumper_L = 5;
    private static final int Bumper_R = 6;
    private static final int Button_Back = 7;
    private static final int Button_Start = 8;
    private static final int L_Stick_Press = 9;
    private static final int R_Stick_Press = 10;

    public XBoxJoystick(final Joystick joystick, final Role role) {
        super(joystick, role);
    }

    @Override
    public double getX() {
        return joystick.getRawAxis(L_AXIS_X);
    }

    @Override
    public double getY() {
        return joystick.getRawAxis(L_AXIS_Y);
    }

    @Override
    public double getSwivelStickX() {
        return joystick.getRawAxis(R_AXIS_X);
    }

    @Override
    public double getSwivelStickY() {
        return joystick.getRawAxis(R_AXIS_Y);
    }

    @Override
    void initializeKeymap() {
        switch (role) {
        case DRIVER_LEFT:
            JoystickButton buttonA = new JoystickButton(joystick, Button_A);
            buttonA.whenPressed(new PrintLogCommand("Hello I am here to destroy your world."));
            
            JoystickButton buttonRStickPress = new JoystickButton(joystick, R_Stick_Press);
            buttonRStickPress.whenPressed(new PrintLogCommand("You are a sad strange little man."));

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
