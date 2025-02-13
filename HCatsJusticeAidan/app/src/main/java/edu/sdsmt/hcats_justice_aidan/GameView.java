/*
 * Description: Holds the custom view for the game area.
 */
package edu.sdsmt.hcats_justice_aidan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
    final static float SCALE_IN_VIEW = 1.0f;
    private Game game;
    private StateMachine sm;
    private Paint fillPaint;
    private Paint linePaint;
    private Paint collectionPaint;
    private Paint playerPaint;
    private Paint borderPaint;
    private int width = -1;
    private float gridSize = -1;
    private float marginX = -1;
    private float marginY = -1;
    private int numTiles = -1;
    private float tileSize = -1;

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /*
     * Description: Changes the color of the cats.
     * Parameters: int color - color to change the cats to.
     */
    public void changePlayerColor(int color) {
        playerPaint.setColor(color);
        invalidate();
    }

    /*
     * Description: Draws the cats to their tile.
     * Parameters: Canvas canvas - canvas to draw the cats on
     */
    public void drawCats(Canvas canvas) {
        for (int i = 0; i < numTiles; i++) {
            for (int j = 0; j < numTiles; j++) {
                if (!(i == numTiles - 1 && j == numTiles - 1)) {
                    int numCats = game.getCatsAt(i, j);
                    for (int k = 0; k < numCats; k++) {
                        float x = marginX + (tileSize * i) + 15;
                        float y = marginY + (tileSize * j) + 15;
                        canvas.drawCircle(x + ((tileSize - 30) * (float) Math.random()), y + ((tileSize - 30) * (float) Math.random()), 10, playerPaint);
                    }
                }
            }
        }
    }

    /*
     * Description: Draws the grid area for the game area.
     * Parameters: Canvas canvas - canvas to draw on
     */
    public void drawGrid(Canvas canvas) {
        // Draw Background
        canvas.drawRect(marginX, marginY, marginX + gridSize, marginY + gridSize, fillPaint);

        // Draw Collection Background
        canvas.drawRect(marginX + gridSize - tileSize, marginY + gridSize - tileSize, marginX + gridSize, marginY + gridSize, collectionPaint);

        // Draw Inner Grid
        for (int i = 1; i <= numTiles - 1; i++) {
            canvas.drawLine(marginX + (tileSize * i), marginY, marginX + (tileSize * i), marginY + gridSize, linePaint);
            canvas.drawLine(marginX, marginY + (tileSize * i), marginX + gridSize, marginY + (tileSize * i), linePaint);
        }

        // Draw Outer Box
        canvas.drawLine(marginX, marginY, marginX + gridSize, marginY, borderPaint);
        canvas.drawLine(marginX, marginY, marginX, marginY + gridSize, borderPaint);
        canvas.drawLine(marginX + gridSize, marginY, marginX + gridSize, marginY + gridSize, borderPaint);
        canvas.drawLine(marginX, marginY + gridSize, marginX + gridSize, marginY + gridSize, borderPaint);
    }

    public int getPlayerColor() {
        return playerPaint.getColor();
    }

    /*
     * Description: Initializes the paint brushes to use to draw the game area.
     */
    public void init() {
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(0xff000000);
        linePaint.setStrokeWidth(7.5f);
        linePaint.setTextSize(100);

        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setColor(0xffcccccc);

        collectionPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        collectionPaint.setColor(0xff5f0299);

        playerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        playerPaint.setColor(0xff000000);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setColor(0xff000000);
    }

    /*
     * Description: Draws the game area when loaded.
     * Parameters: Canvas canvas - canvas to draw on
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // If dimensions aren't loaded, save them for future use.
        if (width == -1) {
            width = getWidth();
            int height = getHeight();

            int minDim = Math.min(width, height);

            gridSize = (int) (minDim * SCALE_IN_VIEW);
            marginX = (width - gridSize) / 2;
            marginY = (height - gridSize) / 2;
            numTiles = game.getSize();
            tileSize = gridSize / numTiles;
        }

        drawGrid(canvas);
        drawCats(canvas);
    }



    public void setGame(Game game) {
        this.game = game;
    }

    public void setTreatActive(boolean active) {
        if (active) {
            borderPaint.setColor(0xfffc1100);
        }
        else {
            borderPaint.setColor(0xff000000);
        }
    }
}
