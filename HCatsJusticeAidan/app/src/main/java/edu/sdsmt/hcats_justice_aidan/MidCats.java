/*
 * Description: Holds the class for the MidCats state. This state sweeps a minimum of 2 cats or 50
 * percent of them.
 */
package edu.sdsmt.hcats_justice_aidan;

public class MidCats extends State {
    /*
     * Description: Constructor for the MidCats class.
     * Parameters: StateMachine sm - main state machine
     *             Game game - main game object
     * Return: MidCats
     */
    public MidCats(StateMachine sm, Game game) {
        // GRADING: SWEEP
        super(sm, game, 2, 50);
    }

    @Override
    public void checkState() {
        if (game.isLost()) {
            stateMachine.setState(StateMachine.StateEnum.End);
        }
        else if (game.getCatsCaught() > 20) {
            stateMachine.setState(StateMachine.StateEnum.Low);
        }
    }

    @Override
    public void endTask() {}

    @Override
    public void startTask() {}

    @Override
    public void sweepRight() {
        super.sweepRight();
        checkState();
    }

    @Override
    public void sweepDown() {
        super.sweepDown();
        checkState();
    }

    @Override
    public void useTreat() {
        // GRADING: TO_TREAT
        stateMachine.setState(StateMachine.StateEnum.TreatActive);
    }
}
