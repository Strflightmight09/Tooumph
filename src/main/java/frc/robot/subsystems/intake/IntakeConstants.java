package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class IntakeConstants {

    private static final int MOTOR_ID = 4;
    static final WPI_TalonSRX MOTOR = new WPI_TalonSRX(MOTOR_ID);
    static final double VOLTAGE_COMPENSATION_SATURATION = 12;
    private static final boolean INVERTED_VALUE = false;
    private static final NeutralMode NEUTRAL_MODE = NeutralMode.Coast;

    public enum IntakeState {
        COLLECTION(-6),
        EJECTION(7),
        STOP(0);

        final double voltage;

        IntakeState(double voltage) {
            this.voltage = voltage;
        }
    }

    static {
        MOTOR.configFactoryDefault();

        MOTOR.enableVoltageCompensation(true);
        MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION_SATURATION);

        MOTOR.setInverted(INVERTED_VALUE);

        MOTOR.setNeutralMode(NEUTRAL_MODE);
    }
}
