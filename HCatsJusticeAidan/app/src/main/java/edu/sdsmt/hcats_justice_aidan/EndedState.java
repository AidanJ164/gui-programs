/*
* Description: This file holds the class for the EndedState. It describes what happens when the game
* is over.
 */

package edu.sdsmt.hcats_justice_aidan;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class EndedState extends State {

    private final MainActivity mainAct;

    /*
     * Description: Constructor for the EndedState.
     * Parameters: StateMachine sm - parent state machine
     *             Game game - herding cats game
     *             MainActivity mainAct - Main activity of the application
     * Return: EndedState
     */
    public EndedState(StateMachine sm, Game game, MainActivity mainAct) {
        super(sm, game, 0, 0);
        this.mainAct = mainAct;
    }

    @Override
    public void checkState() {}

    @Override
    public void endTask() {
        game.reset();
        mainAct.updateInfo();
    }

    @Override
    public void startTask() {
        // Create a dialog to display whether user won or lost.
        // GRADING: DIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(mainAct);
        if (game.isLost()) {
            builder.setTitle("You lost.");
            builder.setMessage("You ran out of moves.");
        }
        else {
            builder.setTitle("You Win!");
            builder.setMessage("You collected all of the cats!");
        }
        // Create button to reset the game.
        builder.setPositiveButton("Play Again", new PlayAgainBtn());
        builder.create().show();
    }

    /* Play Again button on click listener */
    class PlayAgainBtn implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // GRADING: RESET
            stateMachine.setState(StateMachine.StateEnum.High);
        }
    }
}
