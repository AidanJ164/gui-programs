package edu.sdsmt.hcats_justice_aidan;

import android.content.pm.ActivityInfo;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicReference;

@RunWith(AndroidJUnit4.class)
public class Tier3e {
    @Rule
    public final ActivityScenarioRule<MainActivity> act = new ActivityScenarioRule<>(MainActivity.class);

    private Game g;
    private StateMachine sm;
    private MainActivity mainAct;

    private void init(){
        AtomicReference<Game> gameAtom = new AtomicReference<>();
        AtomicReference<StateMachine> smAtom = new AtomicReference<>();
        AtomicReference<Button> treatBtnAtom = new AtomicReference<>();
        AtomicReference<Button> resetBtnAtom = new AtomicReference<>();
        AtomicReference<Button> downBtnAtom = new AtomicReference<>();
        AtomicReference<Button> rightBtnAtom = new AtomicReference<>();
        AtomicReference<TextView> treatAtom = new AtomicReference<>();
        AtomicReference<TextView> moveAtom = new AtomicReference<>();
        AtomicReference<TextView> caughtAtom = new AtomicReference<>();
        act.getScenario().onActivity(act -> {
            mainAct = act;
            gameAtom.set(act.getGame());
            smAtom.set(act.getStateMachine());

            treatBtnAtom.set(act.findViewById(R.id.treatBtn));
            resetBtnAtom.set(act.findViewById(R.id.resetBtn));
            downBtnAtom.set(act.findViewById(R.id.downBtn));
            rightBtnAtom.set(act.findViewById(R.id.rightBtn));

            treatAtom.set(act.findViewById(R.id.treats));
            moveAtom.set(act.findViewById(R.id.moves));
            caughtAtom.set(act.findViewById(R.id.caught));
        });

        g = gameAtom.get();
        sm = smAtom.get();
    }

    @Test
    public void checkRotation() {
        init();

        Espresso.onView(ViewMatchers.withId(R.id.rightBtn)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.rightBtn)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.downBtn)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.treatBtn)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.rightBtn)).perform(ViewActions.click());

        // Rotate Device
        mainAct.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();

        // Check Values after Rotation
        // Check state
        Assert.assertEquals(MidCats.class.getName(), sm.getCurrentStateName());

        // Row 1
        Assert.assertEquals(0, g.getCatsAt(0,0));
        Assert.assertEquals(0, g.getCatsAt(1,0));
        Assert.assertEquals(7, g.getCatsAt(2,0));
        // Row 2
        Assert.assertEquals(0, g.getCatsAt(0, 1));
        Assert.assertEquals(0, g.getCatsAt(1, 1));
        Assert.assertEquals(15, g.getCatsAt(2, 1));
        // Row 3
        Assert.assertEquals(0, g.getCatsAt(0, 2));
        Assert.assertEquals(2, g.getCatsAt(1, 2));

        // Check Info Values
        Assert.assertEquals(10, g.getMoves());
        Assert.assertEquals(16, g.getCatsCaught());
        Assert.assertEquals(2, g.getTreats());
    }
}
