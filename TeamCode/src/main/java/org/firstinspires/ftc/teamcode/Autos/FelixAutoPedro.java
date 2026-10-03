package org.firstinspires.ftc.teamcode.Autos;
import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.paths.Path;
import org.firstinspires.ftc.teamcode.OpModeStorage;
import com.pedropathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous
public class FelixAutoPedro extends OpMode {
    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();

    private final Pose startPose = p.of(56, 8, 90);
    private final Pose LeftUp = p.of(20.0434, 90.6024, 180);
    private final Pose TopLeft = p.of(61.3706, 99.3435, 134.2113);
    private final Pose CurveTopLeft = p.of(23.7353, 138.2882, 0);
    private final Pose TopRight = p.of(106.9459, 107.2659, 131.6257);
    private final Pose CurveTopRight = p.of(79.9835, 137.9059, 0);
    private final Pose endPose = p.of(58.6753, 8.7118, 63.905);

    private Path StartToLU() {
        return line(startPose, LeftUp).linear(startPose, LeftUp);
    }

    private final Path LeftUPToTopRight() {
        return curve(TopLeft, CurveTopLeft, TopRight).linear(TopLeft, TopRight);
    }

    private final Path TRToEnd() {
        return curve(TopRight, CurveTopRight, endPose).linear(TopRight, endPose);
    }

    private Command autoRoutine() {
        return sequential(
        follow(follower, StartToLU()),
                follow(follower, LeftUPToTopRight()),
                follow(follower, TRToEnd())
        );
    }
    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);

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
}