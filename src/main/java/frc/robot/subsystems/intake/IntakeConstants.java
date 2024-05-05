package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.robot.utilities.Conversions;

public class IntakeConstants {

    private static final int
            COLLECTION_MOTOR_ID = 4,
            ANGLE_MOTOR_ID = 5,
            ENCODER_ID = 10;
    static final WPI_TalonSRX
            COLLECTION_MOTOR = new WPI_TalonSRX(COLLECTION_MOTOR_ID),
            ANGLE_MOTOR = new WPI_TalonSRX(ANGLE_MOTOR_ID);
    static final TalonSRX ENCODER = new TalonSRX(ENCODER_ID);
    private static final double VOLTAGE_COMPENSATION_SATURATION = 12;
    private static final boolean
            COLLECTION_MOTOR_INVERTED_VALUE = false,
            ANGLE_MOTOR_INVERTED_VALUE = false;
    private static final NeutralMode
            COLLECTION_MOTOR_NEUTRAL_MODE = NeutralMode.Coast,
            ANGLE_MOTOR_NEUTRAL_MODE = NeutralMode.Brake;

    private static final double
            MAX_ANGLE_MOTOR_VELOCITY = 3,
            MAX_ANGLE_MOTOR_ACCELERATION = 3;

    static final TrapezoidProfile.Constraints CONSTRAINTS = new TrapezoidProfile.Constraints(
            MAX_ANGLE_MOTOR_VELOCITY,
            MAX_ANGLE_MOTOR_ACCELERATION
    );
    private static final boolean ENCODER_PHASE = true;
    private static final double ENCODER_OFFSET = 0;

    private static final double
            P = 1,
            I = 0,
            D = 0,
            KS = 0,
            KV = 0,
            KA = 0;
    static final ProfiledPIDController PROFILED_PID_CONTROLLER = new ProfiledPIDController(P, I, D, CONSTRAINTS);
    static final ArmFeedforward FEEDFORWARD = new ArmFeedforward(KS, KV, KA);

    static {
        configureCollectionMotor();
        configureAngleMotor();
        configureEncoder();
    }

    private static void configureCollectionMotor() {
        COLLECTION_MOTOR.configFactoryDefault();

        COLLECTION_MOTOR.enableVoltageCompensation(true);
        COLLECTION_MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION_SATURATION);

        COLLECTION_MOTOR.setInverted(COLLECTION_MOTOR_INVERTED_VALUE);

        COLLECTION_MOTOR.setNeutralMode(COLLECTION_MOTOR_NEUTRAL_MODE);
    }

    private static void configureAngleMotor() {
        ANGLE_MOTOR.configFactoryDefault();

        ANGLE_MOTOR.enableVoltageCompensation(true);
        ANGLE_MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION_SATURATION);

        ANGLE_MOTOR.setInverted(ANGLE_MOTOR_INVERTED_VALUE);

        ANGLE_MOTOR.setNeutralMode(ANGLE_MOTOR_NEUTRAL_MODE);
    }

    private static void configureEncoder() {
        ENCODER.configFactoryDefault();
        ENCODER.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
        ENCODER.setSensorPhase(ENCODER_PHASE);
        ENCODER.setSelectedSensorPosition(Conversions.offsetRead(ENCODER.getSelectedSensorPosition(), ENCODER_OFFSET));
    }

    public enum IntakeState {
        COLLECTION(-6, Rotation2d.fromDegrees(0)),
        EJECTION(7, Rotation2d.fromDegrees(0)),
        STOP(0, Rotation2d.fromDegrees(0));

        final double voltage, targetPosition;

        IntakeState(double voltage, Rotation2d targetPosition) {
            this.voltage = voltage;
            this.targetPosition = targetPosition.getDegrees();
        }
    }
}
