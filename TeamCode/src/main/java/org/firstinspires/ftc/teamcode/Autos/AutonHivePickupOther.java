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
public class AutonHivePickupOther extends OpMode {
//    private ShooterPollen ShooterPollenFly;
//    private IntakeB intakeb;
    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();




    private final Pose start = poseFactory.of(57.7855, 7.8884, 270);
    private final Pose path1 = poseFactory.of(57.7855, 20.0422, 180);
    private final Pose point2Start = poseFactory.of(57.7855, 20.0422, 180);
    private final Pose point2 = poseFactory.of(17.3102, 10.2511, 180);
    private final Pose point2Control1 = poseFactory.of(46.7575, 10.7687, 0);
    private final Pose point3Start = poseFactory.of(17.3102, 10.2511, 180);
    private final Pose point3 = poseFactory.of(11.1136, 8.1489, 180);
    private final Pose point4Start = poseFactory.of(11.1136, 8.1489, 180);
    private final Pose point4 = poseFactory.of(59.4483, 129.2195, 90);
    private final Pose point4Control1 = poseFactory.of(28.233, 112.43, 0);
    private final Pose point5Start = poseFactory.of(59.4483, 129.2195, 90);
    private final Pose point5 = poseFactory.of(17.0278, 118.9818, 180);

//    private ShooterPollen ShooterPollenFly;

    // Autonomous routine
    public Command autoRoutine() {

        return sequential(
//                instant (() -> ShooterPollenFly.setMotorVelocity(1475)), // spinny spin flyy fly
//                // instant (() -> ShooterPollenFly.setHoodPose(0.8)), // puty put uppy up hoody hood
//                waitMs(1000),
//                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
//                instant (() -> intakeb.setMotorPower(1)), // runny run intakey take
//                waitMs(2000),
//                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
//                waitMs(5000),
//                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
//                waitMs (1800),
//                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
//                waitMs(1500),
//                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
//                waitMs (1800),
//                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
//                waitMs(1500),
//                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
////                waitMs (1000),
//                instant (() -> ShooterPollenFly.setMotorVelocity(0)), // stopy stop flyy fly
//                instant (() -> intakeb.setMotorPower(0)),
//                waitMs(3000),
                follow(follower, path1()),
                follow(follower, path2()),
                follow(follower, path3()),
                follow(follower, path4()),
                follow(follower, path5()));


    }


    public Path path1() {
        return Paths.line(start, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.curve(point2Start, point2Control1, point2).linear(point2Start, point2);
    }

    public Path path3() {
        return Paths.line(point3Start, point3).linear(point3Start, point3);
    }

    public Path path4() {
        return Paths.curve(point4Start, point4Control1, point4).linear(point4Start, point4);
    }

    public Path path5() {
        return Paths.line(point5Start, point5).linear(point5Start, point5);
    }





//    private Intake intake;
//    private IntakeB intakeb;

@Override
public void init() {
    Scheduler.reset();
    follower = PedroConstants.create(hardwareMap);
    follower.setPose(start);//sets the starting point of your Robot
    follower.update();
//    intakeb = new IntakeB(hardwareMap);
//    ShooterPollenFly = new ShooterPollen(hardwareMap);
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
