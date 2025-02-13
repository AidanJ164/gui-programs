/*
 * Description: Holds the class for the HighCats state. This state is the starter state of the game.
 * Sweeps move a minimum of 3 cats or 50 percent of them. Treats can also not be used in this state.
 */
package edu.sdsmt.hcats_justice_aidan;

public class HighCats extends State {

    /*
     * Description: Constructor for the HighCats state.
     * Parameters: StateMachine sm - state machine class that handles the states.
     *             Game game - base game object
     * Return: HighCats
     */
    public HighCats(StateMachine sm, Game game) {
        // GRADING: SWEEP
        super(sm, game, 3, 50);
    }

    @Override
    public void checkState() {
        if (game.isLost()) {
            stateMachine.setState(StateMachine.StateEnum.End);
        }
        else if (game.getCatsCaught() > 10) {
            stateMachine.setState(StateMachine.StateEnum.Mid);
        }
    }

    @Override
    public void endTask() {
        game.setTreatBtn(true);
    }

    @Override
    public void startTask() {
        game.setTreatBtn(false);
    }

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
}
