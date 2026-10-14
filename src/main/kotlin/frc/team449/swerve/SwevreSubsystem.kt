package frc.team449.swerve

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.littletonrobotics.junction.Logger

class SwevreSubsystem(private val io: SwevreIO): SubsystemBase() {
    private val inputs: ExampleIOInputsAutoLogged = ExampleIOInputsAutoLogged()

    override fun periodic() {
        io.updateInputs(inputs)
        Logger.processInputs("Swerve", inputs)
    }

    fun setTurnVoltage(voltage: Double): Command =
        runOnce { // or run
            io.updateAppliedVoltageTurn(voltage)
        }

    fun stopAllMotor(): Command =
        runOnce { // or run
            io.updateAppliedVoltageDrive(0.0)
            io.updateAppliedVoltageTurn(0.0)
        }

}
