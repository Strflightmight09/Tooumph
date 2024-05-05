package frc.robot.subsystems.tank;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class TankConstants {
    private static final int
            LEFT_MASTER_MOTOR_ID = 0,
            LEFT_FOLLOWER_MOTOR_ID = 1,
            RIGHT_MASTER_MOTOR_ID = 2,
            RIGHT_FOLLOWER_MOTOR_ID = 3;
    static final WPI_TalonSRX
            LEFT_MASTER_MOTOR = new WPI_TalonSRX(LEFT_MASTER_MOTOR_ID),
            LEFT_FOLLOWER_MOTOR = new WPI_TalonSRX(LEFT_FOLLOWER_MOTOR_ID),
            RIGHT_MASTER_MOTOR = new WPI_TalonSRX(RIGHT_MASTER_MOTOR_ID),
            RIGHT_FOLLOWER_MOTOR = new WPI_TalonSRX(RIGHT_FOLLOWER_MOTOR_ID);
    static final DifferentialDrive DIFFERENTIAL_DRIVE = new DifferentialDrive(LEFT_MASTER_MOTOR, RIGHT_MASTER_MOTOR);
    private static final double VOLTAGE_COMPENSATION_SATURATION = 12;
    private static final boolean
            LEFT_MASTER_INVERTED_VALUE = false,
            LEFT_FOLLOWER_INVERTED_VALUE = false,
            RIGHT_MASTER_INVERTED_VALUE = true,
            RIGHT_FOLLOWER_INVERTED_VALUE = true;
    private static final NeutralMode NEUTRAL_MODE = NeutralMode.Brake;

    static {
        configureLeftMotors();
        configureRightMotors();
    }

    private static void configureLeftMotors() {
        LEFT_MASTER_MOTOR.configFactoryDefault();
        LEFT_FOLLOWER_MOTOR.configFactoryDefault();

        LEFT_MASTER_MOTOR.enableVoltageCompensation(true);
        LEFT_FOLLOWER_MOTOR.enableVoltageCompensation(true);
        LEFT_MASTER_MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION_SATURATION);
        LEFT_FOLLOWER_MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION_SATURATION);

        LEFT_MASTER_MOTOR.setInverted(LEFT_MASTER_INVERTED_VALUE);
        LEFT_FOLLOWER_MOTOR.setInverted(LEFT_FOLLOWER_INVERTED_VALUE);
        LEFT_MASTER_MOTOR.setNeutralMode(NEUTRAL_MODE);
        LEFT_FOLLOWER_MOTOR.setNeutralMode(NEUTRAL_MODE);

        LEFT_FOLLOWER_MOTOR.follow(LEFT_MASTER_MOTOR, FollowerType.PercentOutput);
    }

    public static void configureRightMotors() {
        RIGHT_MASTER_MOTOR.configFactoryDefault();
        RIGHT_FOLLOWER_MOTOR.configFactoryDefault();

        RIGHT_MASTER_MOTOR.enableVoltageCompensation(true);
        RIGHT_FOLLOWER_MOTOR.enableVoltageCompensation(true);
        RIGHT_MASTER_MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION_SATURATION);
        RIGHT_FOLLOWER_MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION_SATURATION);

        RIGHT_MASTER_MOTOR.setInverted(RIGHT_MASTER_INVERTED_VALUE);
        RIGHT_FOLLOWER_MOTOR.setInverted(RIGHT_FOLLOWER_INVERTED_VALUE);
        RIGHT_MASTER_MOTOR.setNeutralMode(NEUTRAL_MODE);
        RIGHT_FOLLOWER_MOTOR.setNeutralMode(NEUTRAL_MODE);

        RIGHT_FOLLOWER_MOTOR.follow(RIGHT_MASTER_MOTOR, FollowerType.PercentOutput);
    }
}