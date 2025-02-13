/*
 * Description: Holds the class for the TreatActive state. When the game is in this state, sweeps
 * move a minimum of 5 cats or 75 percent of the cats. Cannot activate another treat while in this
 * state.
 */
package edu.sdsmt.hcats_justice_aidan;

public class TreatActive extends State {
    private GameView gameView;
    /*
     * Description: TreatActive Constructor.
     * Parameters: StateMachine sm - main state machine
     *             Game game - main game object
     * Return: TreatActive
     */
    public TreatActive(StateMachine sm, Game game, GameView gameView) {
        // GRADING: SWEEP
        super(sm, game, 5, 75);
        this.gameView = gameView;
    }

    @Override
    public void checkState() {
        if (game.isLost() || game.isWon()) {
            stateMachine.setState(StateMachine.StateEnum.End);
        }
        // GRADING: TO_NO_TREAT
        else if (game.getCatsCaught() > 20) {
            stateMachine.setState(StateMachine.StateEnum.Low);

        }
        // GRADING: TO_NO_TREAT
        else if (game.getCatsCaught() > 10) {
            stateMachine.setState(StateMachine.StateEnum.Mid);
        }
    }

    @Override
    public void endTask() {
        game.setTreatBtn(true);
        gameView.setTreatActive(false);
    }

    @Override
    public void startTask() {
        game.setTreatBtn(false);
        game.useTreat();
        gameView.setTreatActive(true);
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
