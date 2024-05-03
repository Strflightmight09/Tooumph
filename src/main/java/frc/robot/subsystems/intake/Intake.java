package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Conversions;

public class Intake extends SubsystemBase {
    private static final Intake INSTANCE = new Intake();
    private final WPI_TalonSRX motor = IntakeConstants.MOTOR;

    public static Intake getInstance() {
        return INSTANCE;
    }

    public void setMotorState(IntakeConstants.IntakeState targetState) {
        setMotorVoltage(targetState.voltage);
    }

    public void stop() {
        motor.stopMotor();
    }

    private void setMotorVoltage(double voltage) {
        motor.set(Conversions.voltageToCompensatedPower(voltage, IntakeConstants.VOLTAGE_COMPENSATION_SATURATION));
    }
}
