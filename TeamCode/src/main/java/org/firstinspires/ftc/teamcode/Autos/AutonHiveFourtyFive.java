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





    private final Pose start = poseFactory.of(35.6544, 10.6609, 45);
    private final Pose point1 = poseFactory.of(47.9999, 118.0732, 360);
    private final Pose point1Control1 = poseFactory.of(58.4065, 121.3505, 0);


//    private ShooterPollen ShooterPollenFly;

    // Autonomous routine
    public Command autoRoutine() {

        return sequential(
                instant (() -> ShooterPollenFly.setMotorVelocity(1350)), // spinny spin fly flyy
                instant (() -> ShooterPollenFly.setHoodPose(0.82)), // puty put uppy up hoody hood
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // gatey gate
                waitMs(2000),//stop stopy wait waity
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                instant (() -> intakeb.setMotorPower(1)), // runny run intakey take
                waitMs(800),//stop stopy wait waity
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
                waitMs(300),//stop stopy wait waity
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                waitMs (1800),//stop stopy wait waity
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
                waitMs(300),//stop stopy wait waity
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                waitMs (1800),//stop stopy wait waity
                instant (() -> ShooterPollenFly.setGatePollenPos(0.5)), // openy open gatey gate
                waitMs(400),//stop stopy wait waity
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                waitMs (3000),//stop stopy wait waity
                instant (() -> ShooterPollenFly.setMotorVelocity(0)), // stopy stop flyy fly
                instant (() -> intakeb.setMotorPower(0)),
                waitMs(3000),//stop stopy wait waity
                instant (() -> ShooterPollenFly.setGatePollenPos(0.8)), // closey close gatey gate
                follow(follower, path1()));

    }


    public Path path1() {
        return Paths.curve(start, point1Control1, point1).linear(start, point1);
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




