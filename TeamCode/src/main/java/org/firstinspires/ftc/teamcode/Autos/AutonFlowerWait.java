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
public class AutonFlowerWait extends OpMode {
    private Follower follower;
    private ShooterPollen ShooterPollenFly;
    private ShooterPollen GatePollen;
    private IntakeB intakeb;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(8.0743, 58.528, 90);
    private final Pose path1 = poseFactory.of(61.2046, 24.7234, 90);
    private final Pose point2 = poseFactory.of(30.5741, 135.4155, 0);
    private final Pose point2control1 = poseFactory.of(15.9545, 15.2753, 0);

    public Path path1() {return Paths.line(start, path1).constant(path1);}

    public Path path2() {return Paths.curve(path1, point2control1, point2).constant(point2);}

    private Command autoRoutine() {
        return sequential(
                waitMs(3000),
                follow(follower, path1()),
        instant (() -> ShooterPollenFly.setMotorVelocity(1450)), // spinny spin fly fly
                instant (() -> ShooterPollenFly.setHoodPose(0.7)), // puty put uppy up hoody hood
                waitMs(3000),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                instant (() -> intakeb.setMotorPower(1)), // runny run intakey take
                waitMs(2000),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
                waitMs(1500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                waitMs (1800),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
                waitMs(1500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                waitMs (1800),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
                waitMs(1500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                waitMs (1000),
                instant (() -> ShooterPollenFly.setMotorVelocity(0)), // stopy stop flyy fly
                instant (() -> intakeb.setMotorPower(0)),
                waitMs(3000),
                follow(follower, path2()));
                // Add mechanism commands here.
    }

    @Override
    public void init() {
        Scheduler.reset();
        follower = PedroConstants.create(hardwareMap);
        ShooterPollenFly = new ShooterPollen(hardwareMap);
        intakeb = new IntakeB(hardwareMap);
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
