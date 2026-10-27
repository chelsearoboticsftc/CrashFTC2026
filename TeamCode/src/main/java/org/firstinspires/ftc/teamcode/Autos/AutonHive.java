package org.firstinspires.ftc.teamcode.Autos;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterPollen;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeB;

import org.firstinspires.ftc.teamcode.OpModeStorage;
import org.firstinspires.ftc.teamcode.pedro.PedroConstants;



@Autonomous
public class AutonHive extends OpMode {
    private ShooterPollen ShooterPollenFly;
    private IntakeB intakeb;
    private Follower follower;


    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(11.0335, 59.9796, 90);
    private final Pose path1Start = poseFactory.of(11.0335, 59.9796, 0);
    private final Pose path1 = poseFactory.of(10.2085, 99.5306, 0);
    private final Pose path1Control1 = poseFactory.of(4.8539, 33.3924, 0);

//    private ShooterPollen ShooterPollenFly;

    // Autonomous routine
    public Command autoRoutine() {

        return sequential(
                instant (() -> ShooterPollenFly.setMotorVelocity(1265)), // spinny spin fly fly
                instant (() -> ShooterPollenFly.setGatePollenPos(0.85)), // puty put uppy up hoody hood
                waitMs(1000),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                instant (() -> intakeb.setMotorPower(1)), // runny run intakey take
                waitMs(500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
                waitMs(500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                waitMs (500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
                waitMs(500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                waitMs (500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
                waitMs(500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                waitMs (500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
                instant (() -> ShooterPollenFly.setMotorVelocity(0)), // stopy stop flyy fly
                follow(follower, path1()));


    };


    public Path path1() {
        return Paths.curve(start, path1Control1, path1).linear(start, path1);
    }


//    private Intake intake;
//    private IntakeB intakeb;

    @Override
    public void init() {
        Scheduler.reset();
        follower = PedroConstants.create(hardwareMap);
//         intake = new Intake(hardwareMap);
//         intakeb = new IntakeB(hardwareMap);
//        ShooterPollenFly = new ShooterPollen(hardwareMap);
        follower.setPose(start);//sets the starting point of your Robot
        follower.update();
        intakeb = new IntakeB(hardwareMap);
        ShooterPollenFly = new ShooterPollen(hardwareMap);
    }
    @Override
    public void start() {
        schedule(autoRoutine());
    }
    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();
    }

    @Override
    public void stop() {
        OpModeStorage.autoEndPose = follower.pose(); //saves your position in that file
    }
    }




