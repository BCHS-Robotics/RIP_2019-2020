package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AutonomousMoveRight")
//@Disabled
public class BasicAutonomous2 extends LinearOpMode {

    /* Declare OpMode members. */
    Hardware robot   = new Hardware();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        robot.frontLeftDrive.setPower(.4);
        robot.frontRightDrive.setPower(.4);
        robot.backLeftDrive.setPower(.4);
        robot.backRightDrive.setPower(.4);

        sleep(400);

        robot.frontRightDrive.setPower(-.4);
        robot.backLeftDrive.setPower(-.4);

        sleep(5000);

        robot.frontLeftDrive.setPower(0);
        robot.frontRightDrive.setPower(0);
        robot.backLeftDrive.setPower(0);
        robot.backRightDrive.setPower(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
}
