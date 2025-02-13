/*
 * Description: Holds the class for the LowCats state. This state moves a minimum of 1 cat or 25
 * percent of them.
 */
package edu.sdsmt.hcats_justice_aidan;

public class LowCats extends State {
    /*
     * Description: Constructor for the LowCats class.
     * Parameters: StateMachine sm - main state machine
     *             Game game - main game object
     * Return: LowCats
     */
    public LowCats(StateMachine sm, Game game) {
        // GRADING: SWEEP
        super(sm, game, 1, 25);
    }

    @Override
    public void checkState() {
        if (game.isLost() || game.isWon()) {
            stateMachine.setState(StateMachine.StateEnum.End);
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
        stateMachine.setState(StateMachine.StateEnum.TreatActive);
    }
}
