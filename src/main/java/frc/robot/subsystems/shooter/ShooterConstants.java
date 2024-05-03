package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ShooterConstants {
    private static final int MOTOR_ID = 6;
    static final WPI_TalonSRX MOTOR = new WPI_TalonSRX(MOTOR_ID);

    private static final double VOLTAGE_COMPENSATION_SATURATION = 12;
    private static final boolean INVERTED_VALUE = false;

    private static final NeutralMode NEUTRAL_MODE = NeutralMode.Brake;

    static {
        MOTOR.configFactoryDefault();

        MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION_SATURATION);
        MOTOR.enableVoltageCompensation(true);

        MOTOR.setInverted(INVERTED_VALUE);

        MOTOR.setNeutralMode(NEUTRAL_MODE);
    }

    public enum ShooterState {
        STOPPED(0),
        SHOOTING(12);
        final double voltage;
        ShooterState(double voltage) {
            this.voltage = voltage;
        }
    }
}
