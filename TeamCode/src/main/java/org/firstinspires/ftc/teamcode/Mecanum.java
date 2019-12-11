package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Mecanum", group="Iterative OpMode")
//@Disabled
public class Mecanum extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    private CRServo slide = null;
    private Servo claw = null;
    private Servo clipper = null;
    private DcMotor shoulder = null;


    double drive;
    double strafe;
    double turn;

    double FLPower;
    double FRPower;
    double BLPower;
    double BRPower;

    @Override
    public void init() {

        frontLeft = hardwareMap.get(DcMotor.class, "FLmotor");
        frontRight = hardwareMap.get(DcMotor.class, "FRmotor");
        backLeft = hardwareMap.get(DcMotor.class, "BLmotor");
        backRight = hardwareMap.get(DcMotor.class, "BRmotor");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        slide = hardwareMap.get(CRServo.class, "slide");
        claw = hardwareMap.get(Servo.class, "claw");
        clipper = hardwareMap.get(Servo.class, "clipper");

        shoulder = hardwareMap.get(DcMotor.class, "shoulder");
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        drive = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        turn = gamepad1.right_stick_x;

        FLPower = Range.clip(drive + turn - strafe, -1.0, 1.0);
        FRPower = Range.clip(drive - turn + strafe, -1.0, 1.0);
        BLPower = Range.clip(drive + turn + strafe, -1.0, 1.0);
        BRPower = Range.clip(drive - turn - strafe, -1.0, 1.0);

        frontLeft.setPower(FLPower);
        frontRight.setPower(FRPower);
        backLeft.setPower(BLPower);
        backRight.setPower(BRPower);

        if(gamepad2.left_bumper)
            claw.setPosition(.3);
        else if(gamepad2.right_bumper)
            claw.setPosition(.75);

        if(gamepad2.x)
            clipper.setPosition(0);
        else if(gamepad2.y)
            clipper.setPosition(.8);

        slide.setPower(gamepad2.left_trigger - gamepad2.right_trigger);

        shoulder.setPower(.8*-gamepad2.left_stick_y);

        telemetry.addData("Runtime", runtime.toString());
    }

    @Override
    public void stop(){
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        shoulder.setPower(0);
    }
}
