/*
 * Description: Holds the abstract State class that the other states are derived from.
 */
package edu.sdsmt.hcats_justice_aidan;

public abstract class State {
    protected final Game game;
    protected final StateMachine stateMachine;
    private final int min;
    private final int percent;

    /*
     * Description: State constructor.
     * Parameters: StateMachine sm - main state machine
     *             Game game - main game object
     *             int min - minimum amount of cats to move on a sweep
     *             int percent - percentage of cats to move on a sweep
     * Return: State
     */
    public State(StateMachine sm, Game game, int min, int percent) {
        this.stateMachine = sm;
        this.game = game;
        this.min = min;
        this.percent = percent;
    }

    /*
     * Description: Check whether to move to a different state after an action.
     */
    abstract void checkState();

    /*
     * Description: Describes what the state needs to do on exit.
     */
    abstract void endTask();

    /*
     * Description: Resets the game and the state machine back to the starter values.
     */
    void reset() {
        game.reset();
        this.stateMachine.setState(StateMachine.StateEnum.High);
    }

    /*
     * Description: Describes what the state need to do when it is entered.
     */
    abstract void startTask();

    /*
     * Description: Calls the sweep right function of the Game object using the specified min and
     * percent.
     */
    void sweepRight() {
        game.sweepRight(min, percent);
    }

    /*
     * Description: Calls the sweep down function of the Game object using the specified min and
     * percent.
     */
    void sweepDown() {
        game.sweepDown(min, percent);
    }



    /*
     * Description: Describes the action to take when the treat button is clicked.
     */
    void useTreat() {}
}
