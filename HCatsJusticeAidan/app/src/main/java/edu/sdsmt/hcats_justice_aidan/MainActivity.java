/*
Program: Android: Herding Cats
Name: Aidan Justice
Class: CSC 468 - GUI Programming
Description: This application is a game about herding cats into a collection area. The game begins
with 40 cats spread among a grid. The player can nudge the cats either right or down to get them into
the collection area that is located at the bottom right grid area. The user can also use a treat to
move more cats with sweep. The game is won if all 40 cats are collected or lost if there are no more
moves left.

_X__ 	Pulled the most recent unit tests at submission time, and ensure they still pass
_X__	    All grading tags added if the tier was reached


Tier 1: Model		42
_X__	Unit Tests all pass

Tier 2: Connect Views		16
_X__	Unit Tests all pass

Tier 3a: State Machine/Event Rules *	36
_X__	Framework there
_X__	Unit Tests all pass

Tier 3b: Floating Action		18
_X__	All buttons there
_X__	Icons set and distinguishable
_X__	Opens/closes properly
_X__	Player color updated.

Tier 3c: Layout **	(-50% each line if fails in on orientation)	26
_X__	Custom’s View’s aspect ratio constant
_X__	Relative size of objects in view maintained
_X__	Works in required screen sizes

Tier 3d: Rotation		20
_X__	Required state saved on rotation

Tier 3e: Unit Test		10
_X__	Test there
_X__	Updated all values to support check
_X__	Triggered rotation
_X__	Checked all values after rotation (does NOT require passing since those points are in 3d)

Tier 4: Extensions		30
Extension 1: 5a 5pt Disable Treat Button: When state is in HighCats or if there no treats, the treat button is disabled
Extension 2: 1g 15pt 2+ different background options: Use the square icon floating buttons to change the background color of the application
Extension 3: 5b 5pt floating buttons open/close state saved on rotation: open the floating burst and rotate
Extension 4: 1k 5pt Red border if treat is active: Activate a treat and see if the game area border changes to red.
 */

package edu.sdsmt.hcats_justice_aidan;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout mainView;
    Game game;
    private GameView gameView;
    StateMachine stateMachine;
    TextView moves;
    TextView treats;
    private TextView caught;
    private Button treatBtn;
    private FloatingActionButton floatRed;
    private FloatingActionButton floatBlue;
    private FloatingActionButton floatBlack;
    private FloatingActionButton floatBackgroundWhite;
    private FloatingActionButton floatBackgroundPurple;
    private boolean isFabOpen = false;
    private boolean isBackgroundFabOpen = false;
    private int backgroundColor = 0xffffffff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mainView = findViewById(R.id.main);

        game = new Game();
        gameView = findViewById(R.id.gameView);
        gameView.setGame(game);
        stateMachine = new StateMachine(game, this, gameView);

        // Information Area
        moves = findViewById(R.id.moves);
        treats = findViewById(R.id.treats);
        caught = findViewById(R.id.caught);

        // Buttons
        Button resetBtn = findViewById(R.id.resetBtn);
        Button rightBtn = findViewById(R.id.rightBtn);
        Button downBtn = findViewById(R.id.downBtn);
        treatBtn = findViewById(R.id.treatBtn);
        floatRed = findViewById(R.id.floatColorRed);
        floatBlue = findViewById(R.id.floatColorBlue);
        floatBlack = findViewById(R.id.floatColorBlack);
        floatBackgroundWhite = findViewById(R.id.backgroundWhite);
        floatBackgroundPurple = findViewById(R.id.backgroundPurple);

        // Set Button Listeners
        resetBtn.setOnClickListener(new ResetBtnClick());
        rightBtn.setOnClickListener(new RightBtnClick());
        downBtn.setOnClickListener(new DownBtnClick());
        treatBtn.setOnClickListener(new TreatBtnClick());

        updateInfo();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("moves", game.getMoves());
        savedInstanceState.putInt("treats", game.getTreats());
        savedInstanceState.putString("state", stateMachine.getState().toString());
        savedInstanceState.putInt("color", gameView.getPlayerColor());
        savedInstanceState.putInt("backgroundColor", backgroundColor);
        savedInstanceState.putBoolean("fabOpen", isFabOpen);
        savedInstanceState.putBoolean("backgroundFabOpen", isBackgroundFabOpen);

        // Save cats in each grid space
        int size = game.getSize();
        int[] cats = new int[size * size];
        for (int i = 0; i < size * size; i++) {
             cats[i] = game.getCatsAt(i / size, i % size);
        }
        savedInstanceState.putIntArray("catGrid", cats);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Recreate game and statemachine with saved values
        int moves = savedInstanceState.getInt("moves");
        int treats = savedInstanceState.getInt("treats");
        int[] cats = savedInstanceState.getIntArray("catGrid");

        game = new Game(moves, treats, cats);
        stateMachine = new StateMachine(game, this, gameView, savedInstanceState.getString("state"));

        setBackgroundColor(savedInstanceState.getInt("backgroundColor"));

        // Restore floating buttons state
        if (savedInstanceState.getBoolean("fabOpen")) {
            showFabMenu();
        }
        if (savedInstanceState.getBoolean("backgroundFabOpen")) {
            showBackgroundMenu();
        }

        // Restore the GameView
        gameView.setGame(game);
        gameView.changePlayerColor(savedInstanceState.getInt("color"));
        updateInfo();
    }

    /*
     * Description: Close the fab menu for changing background color.
     */
    public void closeBackgroundMenu() {
        isBackgroundFabOpen = false;

        floatBackgroundPurple.animate().translationX(DpToPixels(55));
        floatBackgroundWhite.animate().translationX(DpToPixels(110));

        floatBackgroundWhite.animate().alpha(0);
        floatBackgroundPurple.animate().alpha(0);
    }

    /*
    * Description: Close the fab menu for changing player color.
     */
    public void closeFabMenu() {
        isFabOpen = false;

        floatBlack.animate().translationX(DpToPixels(165));
        floatRed.animate().translationX(DpToPixels(110));
        floatBlue.animate().translationX(DpToPixels(55));

        floatRed.animate().alpha(0);
        floatBlack.animate().alpha(0);
        floatBlue.animate().alpha(0);
    }

    /*
     * Author: Dr. Lisa Rebenitsch
     * Description: Converts dp to px. This is used for moving the floating action buttons on burst.
     * Params: float dp - dp to convert
     * Return: float - dp converted to pixels.
     */
    public float DpToPixels(float dp) {
        float pxPerDp = (float) getResources()
                .getDisplayMetrics()
                .densityDpi;

        pxPerDp = pxPerDp
                / DisplayMetrics.DENSITY_DEFAULT;

        return dp * pxPerDp;
    }

    public Game getGame() {
        return game;
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }

    /*
     * Description: Handles when the main floating button for changing background color is clicked.
     * Parameters: View view
     */
    public void onBackgroundBurst(View view) {
        if (!isBackgroundFabOpen) {
            showBackgroundMenu();
        }
        else {
            closeBackgroundMenu();
        }
    }

    public void onBackgroundWhite(View view) {
        setBackgroundColor(0xffffffff);
    }

    public void onBackgroundPurple(View view) {
        setBackgroundColor(0xffb8a6ff);
    }

    public void onBlack(View view) {
        gameView.changePlayerColor(0xff000000);
    }

    public void onBlue(View view) {
        gameView.changePlayerColor(0xff4f6cff);
    }

    /*
     * Description: Handles when the main floating button for changing player color is clicked.
     * Parameters: View view
     */
    public void onBurst(View view) {
        if (!isFabOpen) {
            showFabMenu();
        }
        else {
            closeFabMenu();
        }
    }
    public void onRed(View view) {
        gameView.changePlayerColor(0xfff54040);
    }

    public void setBackgroundColor(int color) {
        backgroundColor = color;
        mainView.setBackgroundColor(color);
    }

    /*
     * Description: Opens the fab menu for changing background color.
     */
    public void showBackgroundMenu() {
        isBackgroundFabOpen = true;

        floatBackgroundPurple.animate().translationX(-DpToPixels(55));
        floatBackgroundWhite.animate().translationX(-DpToPixels(110));

        floatBackgroundWhite.animate().alpha(1.0f);
        floatBackgroundPurple.animate().alpha(1.0f);

    }

    /*
     * Description: Opens the fab menu for changing player color.
     */
    public void showFabMenu() {
        isFabOpen = true;

        floatBlack.animate().translationX(-DpToPixels(165));
        floatRed.animate().translationX(-DpToPixels(110));
        floatBlue.animate().translationX(-DpToPixels(55));

        floatRed.animate().alpha(1.0f);
        floatBlack.animate().alpha(1.0f);
        floatBlue.animate().alpha(1.0f);
    }

    /*
     * Description: Updates the main text of the game, enables/disables the treat button, and updates
     * the game grid.
     */
    public void updateInfo() {
        moves.setText(String.format(Locale.ENGLISH, "%d", game.getMoves()));
        treats.setText(String.format(Locale.ENGLISH, "%d", game.getTreats()));
        caught.setText(String.format(Locale.ENGLISH, "%d", game.getCatsCaught()));
        treatBtn.setEnabled(game.getTreatBtn());
        gameView.invalidate();
    }

    /* Main Button Handlers */
    class DownBtnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            stateMachine.sweepDown();
            updateInfo();
        }
    }

    class RightBtnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            stateMachine.sweepRight();
            updateInfo();
        }
    }

    class ResetBtnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            stateMachine.reset();
            updateInfo();
        }
    }

    class TreatBtnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (game.getTreatBtn()) {
                stateMachine.useTreat();
                updateInfo();
            }
        }
    }
}
