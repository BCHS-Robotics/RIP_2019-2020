package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;


@Autonomous(name = "TestServoAutonomous")
public class TestServoAutonomous extends LinearOpMode {

    private CRServo slide = null;

     @Override
     public void runOpMode() {

         slide = hardwareMap.get(CRServo.class, "slide");
         slide.setPower(0);

         waitForStart();

         slide.setPower(1);
         sleep(6000);
         slide.setPower(0);

         stop();
     }

}
