/*
 * Description: Holds the StateMachine class. This class is the controller of the different states
 * the game could be in.
 */
package edu.sdsmt.hcats_justice_aidan;

public class StateMachine {
    public enum StateEnum {High, Mid, TreatActive, Low, End}

    private StateEnum state = StateEnum.High;
    private final State[] stateArray;

    /*
     * Description: StateMachine constructor.
     * Parameters: Game game - main game object
     *             MainActivity mainAct - main activity of the application
     * Return: StateMachine
     */
    public StateMachine(Game game, MainActivity mainAct, GameView gameView) {
        stateArray = new State[] {
                new HighCats(this, game),
                new MidCats(this, game),
                new TreatActive(this, game, gameView),
                new LowCats(this, game),
                new EndedState(this, game, mainAct)
        };
    }

    /*
     * Description: Another StateMachine constructor that takes in a state to start at.
     * Parameters: Game game - main game object
     *             MainActivity mainAct - main activity of the application
     *             State state - state to start in.
     * Return: StateMachine
     */
    public StateMachine(Game game, MainActivity mainAct, GameView gameView, String state) {
        this(game, mainAct, gameView);
        setState(StateEnum.valueOf(state));
    }

    public String getCurrentStateName() {
        switch (state) {
            case High:
                return HighCats.class.getName();
            case Mid:
                return MidCats.class.getName();
            case Low:
                return LowCats.class.getName();
            case TreatActive:
                return TreatActive.class.getName();
            case End:
                return EndedState.class.getName();
        }
        return "";
    }

    public StateEnum getState() {
        return this.state;
    }

    /*
     * Description: Calls the reset function of the current state.
     */
    public void reset() {
        this.stateArray[this.state.ordinal()].reset();
    }

    /*
     * Description: Sets the state of the game. It first calls the exit activity of the state it's
     * leaving, changes the state, and then calls the entry activity of the state it's going to.
     */
    public void setState(StateEnum state) {
        stateArray[this.state.ordinal()].endTask();
        this.state = state;
        stateArray[this.state.ordinal()].startTask();
    }

    /*
     * Description: Calls the sweep down of the current state.
     */
    public void sweepDown() {
        this.stateArray[this.state.ordinal()].sweepDown();
    }

    /*
     * Description: Calls the sweep right of the current state.
     */
    public void sweepRight() {
        this.stateArray[this.state.ordinal()].sweepRight();
    }

    /*
     * Description: Calls the useTreat function of the current state. This might not do anything
     * unless the current state is in Mid or Low cats.
     */
    public void useTreat() {
        this.stateArray[this.state.ordinal()].useTreat();
    }
}
