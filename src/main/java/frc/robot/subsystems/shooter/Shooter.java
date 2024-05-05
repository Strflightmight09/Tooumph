package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private static final Shooter INSTANCE = new Shooter();
    private final WPI_TalonSRX motor = ShooterConstants.MOTOR;

    private Shooter() {
    }

    public static Shooter getInstance() {
        return INSTANCE;
    }

    void setMotorState(ShooterConstants.ShooterState state) {
        motor.setVoltage(state.voltage);
    }
}
