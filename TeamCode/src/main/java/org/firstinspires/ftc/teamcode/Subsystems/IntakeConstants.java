package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class IntakeConstants {
    //Add subsystem constants here.  Use this to avoid magic numbers
    public static final int INTAKE_THRESHOLD = 5;
    public static final DcMotor.ZeroPowerBehavior INTAKE_ZERO_POWER_BEHAVIOR = DcMotor.ZeroPowerBehavior.BRAKE;
    public static final DcMotorSimple.Direction INTAKE_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static final double INTAKE_VELOCITY_P = 1.0;
    public static final double INTAKE_VELOCITY_I = 0.0;
    public static final double INTAKE_VELOCITY_D = 0.0;
    public static final double INTAKE_VELOCITY_F = 14.5;
    public static final int INTAKE_POSITION_TOLERANCE = 5;
    public static final double INTAKE_VELOCITY_TICKS_PER_S = 2200;
    public static final int INTAKE_A_POSITION = 500;
    public static final int INTAKE_B_POSITION = 1000;
    public static double INTAKE_POSITION_P = 5.0;
}
