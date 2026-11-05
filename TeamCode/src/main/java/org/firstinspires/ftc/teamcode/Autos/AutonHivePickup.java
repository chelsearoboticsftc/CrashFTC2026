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

import org.firstinspires.ftc.teamcode.OpModeStorage;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeB;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterPollen;
import org.firstinspires.ftc.teamcode.pedro.PedroConstants;

@Autonomous
public class AutonHivePickup extends OpMode {
    private Follower follower;
    private IntakeB intakeb;
    private ShooterPollen ShooterPollenFly;
    private ShooterPollen GatePollen;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(56, 8, 90);
    private final Pose path1 = poseFactory.of(52.3134, 7.8084, 90);
    private final Pose point2 = poseFactory.of(9.4764, 8.4932, 180);
    private final Pose point3 = poseFactory.of(9.3279, 94.806, 180);
    private final Pose point3Control1 = poseFactory.of(53.6694, 59.5792, 0);

    public Path path1() {
        return Paths.line(start, path1).constant(path1);
    }

    public Path point2() {
        return Paths.line(path1, point2).constant(point2);
    }

    public Path point3() {
        return Paths.curve(point2, point3Control1, point3).constant(point3);
    }


    private Command autoRoutine() {
        return sequential(
                instant (() -> ShooterPollenFly.setMotorVelocity(1200)), // spinny spin fly fly
                instant (() -> ShooterPollenFly.setHoodPose(0.8)), // puty put uppy up hoody hood
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // gatey gate
                waitMs(2000),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                instant (() -> intakeb.setMotorPower(1)), // runny run intakey take
                waitMs(800),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
                waitMs(300),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                waitMs (1800),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
                waitMs(200),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                waitMs (1800),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
                waitMs(200),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                waitMs (3000),
                instant (() -> ShooterPollenFly.setMotorVelocity(0)), // stopy stop flyy fly
                instant (() -> intakeb.setMotorPower(0)),
                follow(follower, path1()),
                instant (() -> intakeb.setMotorPower(1)),
                follow(follower, point2()),
                instant (() -> intakeb.setMotorPower(0)),
                follow(follower, point3()));


                // Add mechanism commands here.
    }

    @Override
    public void init() {
        Scheduler.reset();
        follower = PedroConstants.create(hardwareMap);
        follower.setPose(start);//sets the starting point of your Robot
        follower.update();
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
