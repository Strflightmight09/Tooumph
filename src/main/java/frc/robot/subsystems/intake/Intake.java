package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Conversions;

public class Intake extends SubsystemBase {
    private static final Intake INSTANCE = new Intake();
    private final WPI_TalonSRX
            collectionMotor = IntakeConstants.COLLECTION_MOTOR,
            angleMotor = IntakeConstants.ANGLE_MOTOR;
    private final CANcoder encoder = IntakeConstants.ENCODER;
    private TrapezoidProfile angleMotorProfile = null;
    private double lastAngleMotorProfileGenerationTime;

    public static Intake getInstance() {
        return INSTANCE;
    }

    private void setCollectionMotorVoltage(double voltage) {
        collectionMotor.set(Conversions.voltageToCompensatedPower(voltage, IntakeConstants.VOLTAGE_COMPENSATION_SATURATION));
    }

    private double calculateAngleMotorVoltage(TrapezoidProfile.State targetState) {
        final double pidOutput = IntakeConstants.PROFILED_PID_CONTROLLER.calculate(
                getAngleMotorPosition().getDegrees(),
                targetState.position
        );
        final double feedforward = IntakeConstants.FEEDFORWARD.calculate(
                Units.degreesToRadians(targetState.position),
                targetState.velocity
        );
        return pidOutput + feedforward;
    }

    private Rotation2d getAngleMotorPosition() {
        final double angleMotorPositionDegrees = Conversions.revolutionsToDegrees(encoder.getPosition().getValue());
        return Rotation2d.fromDegrees(angleMotorPositionDegrees);
    }

    private double getAngleMotorVelocityRotationsPerSecond() {
        return encoder.getVelocity().getValue();
    }

    private double getAngleMotorProfileTime() {
        return Timer.getFPGATimestamp() - lastAngleMotorProfileGenerationTime;
    }

    void setCollectionMotorState(double voltage) {
        setCollectionMotorVoltage(voltage);
    }

    void stopCollectionMotor() {
        collectionMotor.stopMotor();
    }

    void stopAngleMotor() {
        angleMotor.stopMotor();
    }

    void generateAngleMotorProfile(Rotation2d targetAngle) {
        angleMotorProfile = new TrapezoidProfile(
                IntakeConstants.CONSTRAINTS,
                new TrapezoidProfile.State(targetAngle.getDegrees(), 0),
                new TrapezoidProfile.State(getAngleMotorPosition().getDegrees(), getAngleMotorVelocityRotationsPerSecond())
        );

        lastAngleMotorProfileGenerationTime = Timer.getFPGATimestamp();
    }

    void setTargetAngleFromProfile() {
        if (angleMotorProfile == null) {
            angleMotor.stopMotor();
            return;
        }

        TrapezoidProfile.State targetState = angleMotorProfile.calculate(getAngleMotorProfileTime());
        angleMotor.setVoltage(calculateAngleMotorVoltage(targetState));
    }
}
