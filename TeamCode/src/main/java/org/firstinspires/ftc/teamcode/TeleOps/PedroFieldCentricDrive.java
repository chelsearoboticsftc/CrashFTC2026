package org.firstinspires.ftc.teamcode.TeleOps;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import static com.pedropathing.ivy.commands.Commands.waitMs;
import org.firstinspires.ftc.teamcode.OpModeStorage;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterConstantNectar;
import org.firstinspires.ftc.teamcode.pedro.PedroConstants;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterNectar;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterPollen;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeB;

@TeleOp(name = "PFC Drive")
public class PedroFieldCentricDrive extends OpMode {

    private Intake intake;
    private IntakeB intakeb;
    private ShooterNectar Flywheel;
    private ShooterNectar ShooterNectarFly;
    private ShooterNectar GateNectar;
    private ShooterPollen ShooterPollenFly;
    private ShooterPollen Hood;
    private boolean intakedB;

    double GatePollenPos;
    double HoodPos;
    private Follower follower;


    @Override
    public void start() {
        follower.setPose(OpModeStorage.autoEndPose);
        follower.update();
        GatePollenPos = 0;
        intakedB = true;
    }

    @Override
    public void init() {
        follower = PedroConstants.create(hardwareMap);
        intake = new Intake(hardwareMap);
        intakeb = new IntakeB(hardwareMap);
        ShooterPollenFly = new ShooterPollen(hardwareMap);
    }

    @Override
    public void loop() {
        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                gamepad1.right_stick_x,
                follower.pose().heading()
        );
        follower.manual(powers);
        follower.update();
        // relocalise button
        if (gamepad1.startWasPressed()) {
            Pose cornerPose = new Pose(0, 0, Math.toRadians(90));
            // On the fly Pose creation, Only accepts radians for heading
            follower.setPose(cornerPose); // overrides our pose
        }

        Pose robotPose = follower.pose(); // returns a Pose object
        telemetry.addData("Robot X", robotPose.x());
        telemetry.addData("Robot Y", robotPose.y());
        telemetry.addData("Robot Heading", Math.toDegrees(robotPose.heading()));
        telemetry.addData("Servo Position", ShooterPollenFly.getGatePollenPos());
        telemetry.addData("Shooter Vel", ShooterPollenFly.getVelocity());
        telemetry.addData("Hood Pos", ShooterPollenFly.getHoodPose());
        telemetry.update();

        // Intake stuff


        if (gamepad2.aWasPressed()) {
            intake.setMotorPower(1);
            intakeb.setMotorPower(1);
        }

        if (gamepad2.aWasReleased()) {
            intake.setMotorPower(0);
            intakeb.setMotorPower(0);
        }

       // takey outy stuff

        if (gamepad2.bWasPressed()) {
            intakeb.setMotorPower(-1);
            intake.setMotorPower(-1);
        }


        if (gamepad2.bWasReleased()) {
            intakeb.setMotorPower(0);
            intake.setMotorPower(0);
        }

        // Shooter stuff

        if (gamepad2.backWasPressed()) {
            ShooterPollenFly.setMotorVelocity(0);
        }


        if (gamepad2.dpad_down) {
          ShooterPollenFly.setHoodPose(1);
        }
        if (gamepad2.dpad_up) {
            ShooterPollenFly.setHoodPose(0.85);
        }


        if (gamepad2.rightTriggerWasPressed()) {
            ShooterPollenFly.setGatePollenPos(0.5);
        }

        if (gamepad2.rightTriggerWasReleased()) {
            ShooterPollenFly.setGatePollenPos(0.8);
        }

        if (gamepad2.leftTriggerWasPressed()) {
            ShooterPollenFly.setMotorVelocity(1400);
        }

        if (gamepad2.leftTriggerWasReleased()) {
            ShooterPollenFly.setMotorVelocity(800);
        }


        // Intake B toggle


        if (gamepad2.xWasPressed()) {

            intakeb.setMotorPower(1);
//            if (intakedB = false) {
//                intakeb.setMotorPower(0);
//            }
//
//            if (intakedB = true) {
//                intakeb.setMotorPower(1);
//            }

            }
        if (gamepad2.xWasReleased()) {

            intakeb.setMotorPower(0);
//            if (intakedB = false) {
//                intakedB = true;
//            }
//            if (intakedB = true) {
//                intakedB = false;
//            }

        }

        }
    }
