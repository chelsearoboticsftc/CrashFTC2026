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
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeB;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterPollen;
import org.firstinspires.ftc.teamcode.pedro.PedroConstants;
@Autonomous
public class AutonGarden extends OpMode {

    private Follower follower;
    private ShooterPollen ShooterPollenFly;
    private ShooterPollen GatePollen;
    private IntakeB intakeb;
    private Intake intake;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose rotatePath = poseFactory.of(81.8717, 131.0942, 270);
    private final Pose rotatePath_2 = poseFactory.of(81.3784, 120.3037, 0);
    private final Pose collectingPathStart = poseFactory.of(81.3784, 120.3037, 0);
    private final Pose collectingPath = poseFactory.of(132.8338, 131.8942, 90);
    private final Pose collectingPathControl1 = poseFactory.of(135.7426, 92.1665, 0);
    private final Pose shootPositioningPathStart = poseFactory.of(132.8338, 131.8942, 90);
    private final Pose shootPositioningPath = poseFactory.of(82.0284, 133.5023, 270);
    private final Pose shootPositioningPathControl1 = poseFactory.of(113.9355, 103.0452, 0);
    private final Pose parkPathStart = poseFactory.of(82.0284, 133.5023, 270);
    private final Pose parkPath = poseFactory.of(131.6383, 35.1099, 180);
    private final Pose parkPathControl1 = poseFactory.of(136.8943, 101.4104, 0);

    public Path rotatePath() {
        return Paths.line(rotatePath, rotatePath_2).linear(rotatePath, rotatePath_2);
    }

    public Path collectingPath() {
        return Paths.curve(collectingPathStart, collectingPathControl1, collectingPath).linear(collectingPathStart, collectingPath);
    }

    public Path shootPositioningPath() {
        return Paths.curve(shootPositioningPathStart, shootPositioningPathControl1, shootPositioningPath).linear(shootPositioningPathStart, shootPositioningPath);
    }

    public Path parkPath() {
        return Paths.curve(parkPathStart, parkPathControl1, parkPath).linear(parkPathStart, parkPath);
    }

    private Command autoRoutine() {
        return sequential(
                instant (() -> ShooterPollenFly.setMotorVelocity(1450)),
                instant (() -> ShooterPollenFly.setHoodPose(0.7)),
                waitMs(3000),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)),
                instant (() -> intakeb.setMotorPower(1)),
                waitMs(2000),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)),
                waitMs(1500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)),
                waitMs (1800),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)),
                waitMs(1500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)),
                waitMs (1800),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)),
                waitMs(1500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)),
                waitMs (1000),
                instant (() -> ShooterPollenFly.setMotorVelocity(0)),
                instant (() -> intakeb.setMotorPower(0)),
                waitMs(3000),
                follow(follower, rotatePath()),
                instant (() -> intake.setMotorPower(1)),
                follow(follower, collectingPath()),
                instant (() -> intake.setMotorPower(0)),
                follow(follower, shootPositioningPath()),
                instant (() -> ShooterPollenFly.setMotorVelocity(1450)),
                instant (() -> ShooterPollenFly.setHoodPose(0.7)),
                waitMs(3000),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)),
                instant (() -> intakeb.setMotorPower(1)),
                waitMs(2000),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)),
                waitMs(1500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)),
                waitMs (1800),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)),
                waitMs(1500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)),
                waitMs (1800),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)),
                waitMs(1500),
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)),
                waitMs (1000),
                instant (() -> ShooterPollenFly.setMotorVelocity(0)),
                instant (() -> intakeb.setMotorPower(0)),
                waitMs(3000),
                follow(follower, parkPath()));
        // Add mechanism commands here.
    }

    @Override
    public void init() {
        Scheduler.reset();
        follower = PedroConstants.create(hardwareMap);
        ShooterPollenFly = new ShooterPollen(hardwareMap);
        intakeb = new IntakeB(hardwareMap);
        intake = new Intake(hardwareMap);
        follower.setPose(rotatePath);//sets the starting point of your Robot
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
