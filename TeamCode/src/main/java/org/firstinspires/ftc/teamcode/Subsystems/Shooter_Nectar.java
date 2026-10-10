package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.teamcode.utils.LookupTable;

public class Shooter_Nectar {

    //Example declare a DcMotorEx object as part of this class called 'motorName'
    DcMotorEx Flywheel;
    ServoControllerEx Gate;

    //Declare any other global variables for this class here
    private final LookupTable distanceToVelocity = new LookupTable(ShooterConstant_Nectar.LOOKUP_TABLE);

    public Shooter_Nectar(HardwareMap hardwareMap){
        this.Flywheel = hardwareMap.get(DcMotorEx.class, ShooterConstant_Nectar.MOTOR_NAME);
        this.Gate = hardwareMap.get(ServoControllerEx.class, ShooterConstant_Nectar.SERVO_NAME);

        //This defines the behavior at zero power (brake or coast)
        Flywheel.setZeroPowerBehavior(ShooterConstant_Nectar.ZERO_POWER_BEHAVIOR);

        //This defines the motor direction (forward or reversed)
        Flywheel.setDirection(ShooterConstant_Nectar.MOTOR_DIRECTION);

        /* This defines the motor velocity PIDF gains.  Velocity PIDF values determine control    *
         * around a target velocity (setTargetVelocity) OR how fast the system responds to a      *
         * change in set position (setTargetPosition).                                            */
        Flywheel.setVelocityPIDFCoefficients(
                ShooterConstant_Nectar.VELOCITY_P, //Proportional Gain
                ShooterConstant_Nectar.VELOCITY_I, //Integral Gain
                ShooterConstant_Nectar.VELOCITY_D, //Derivative Gain
                ShooterConstant_Nectar.VELOCITY_F);//Feed Forward Gain

        /* This defines the motor position PID P gain. Position control only needs P gain since   *
         * once the system reaches the target position since once at position you're only         *
         * disturbances in the system                                                             */
        Flywheel.setPositionPIDFCoefficients(
                ShooterConstant_Nectar.POSITION_P);//Proportional Gain

        //motorName.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    /* Standard functions.  All Chelsea Robotics subsystems shall have init() and update() these  *
     * methods defined. Leave empty if not needed!                                                */
    public void init(){
        /* Call this method at the start of your opmode logic once to execute any logic you       *
         * want to be called on initialization. If none, leave empty!                             */
    }

    public void update(){
        //Call this method each time your opmode logic loops (i.e. inside while(opModeIsActive()){}
        //to execute any logic you want to be called periodically. If none, leave empty!

        //setTargetPosition needs to be called once per loop to keep the REV watchdog happy
        //motorName.setTargetPosition(motorSetPosition);
    }

    public void shoot(double distance) {
        double velocity = distanceToVelocity.interpolate(distance);
        this.setMotorVelocity(velocity);
        // TODO - is ball already engaged, or does it need to be dropped,
        // maybe after a short delay to allow the motor to spin up?

    }

    public double getVelocity() {
      return Flywheel.getVelocity();
    }

    public void setMotorVelocity(double angularRate) {
        this.Flywheel.setVelocity(angularRate);
    }
}
