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
public class AutonHiveFourtyFive extends OpMode {
    private ShooterPollen ShooterPollenFly;
    private IntakeB intakeb;
    private Follower follower;


    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(31.495, 13.8552, 90);
    private final Pose path1 = poseFactory.of(31.495, 13.8552, 45);
    private final Pose point2 = poseFactory.of(11.654, 14.0217, 180);

//    private ShooterPollen ShooterPollenFly;

    // Autonomous routine
    public Command autoRoutine() {

        return sequential(
<<<<<<< HEAD
                instant (() -> ShooterPollenFly.setMotorVelocity(1200)), // spinny spin flyy fly
                instant (() -> ShooterPollenFly.setHoodPose(0.8)), // puty put uppy up hoody hood
=======
                instant (() -> ShooterPollenFly.setMotorVelocity(1200)), // spinny spin fly fly
                instant (() -> ShooterPollenFly.setHoodPose(0.9)), // puty put uppy up hoody hood
>>>>>>> 2d3e9c7680700d90332c6fb9199391171d96baa5
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
                follow(follower, path2()));



    }


    public Path path1() {
        return Paths.line(start,path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.line(path1, point2).tangent();
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




