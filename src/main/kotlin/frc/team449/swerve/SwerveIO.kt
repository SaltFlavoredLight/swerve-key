package frc.team449.swerve

import edu.wpi.first.math.geometry.Rotation2d
import org.littletonrobotics.junction.AutoLog

// So what do you need an updateInputs(inputs: ExampleIOInputs) function for?
// This is where you're actually going to change the values of what's in your IOInputs class so that when you look at AdvantageScope
// it's not just all 0. This function is going to get called periodically in your Subsystem class, which we'll get to a bit later.



interface SwerveIO {
    @AutoLog
    open class ExampleIOInputs {
        @JvmField var velocityTurn: Double = 0.0
        @JvmField var appliedVoltageTurn: Double = 0.0
        @JvmField var usedVoltageTurn: Double = 0.0
        @JvmField var angleTurn: Rotation2d = Rotation2d()

        @JvmField var velocityDrive: Double = 0.0
        @JvmField var appliedVoltageDrive: Double = 0.0
        @JvmField var usedVoltageDrive: Double = 0.0
    }

    fun updateVelocityTurn(velocity: Double)
    fun updateAppliedVoltageTurn(voltage: Double)
    fun updateAngleTurn(angle: Rotation2d)
    fun updateVelocityDrive(velocity: Double)
    fun updateAppliedVoltageDrive(voltage: Double)

    fun updateInputs(inputs: ExampleIOInputs)


    fun getPositionTurn()
    fun getVoltageTurn()
    fun getAngleVelocityTurn()
}



