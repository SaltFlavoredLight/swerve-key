package frc.team449

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.PrintCommand
import edu.wpi.first.wpilibj2.command.button.CommandXboxController
import frc.team449.swerve.SwerveIOHardware
import frc.team449.swerve.SwerveIOSim
import frc.team449.swerve.SwerveSubsystem
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser

object RobotContainer {
    // driver controller
    val driveController: CommandXboxController = CommandXboxController(0)
    val operatorController: CommandXboxController = CommandXboxController(1)
    var autonomousCommand: Command = PrintCommand("If you see this, you probably didn't run an auto.")

    val bindings = Bindings(this)

    val exampleSubsystem: SwerveSubsystem =
        SwerveSubsystem(
            when (Constants.CURRENT_MODE) {
                Constants.Mode.REAL -> SwerveIOHardware(1, 2)
                Constants.Mode.SIM -> SwerveIOSim()
                else -> SwerveIOSim()
                /*
                the else here is for the replay case, but since we're
                not using it we'll just put Sim in there as a placeholder
                to make the compiler happy
                */
            }
        )

    val autoChooser = LoggedDashboardChooser<Command>("Auto Routines")
}
