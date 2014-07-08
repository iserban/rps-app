package com.iserban.app.model.game.moves;

import org.junit.Assert;
import org.junit.Test;

public class RockTest {

    @Test
    public void testGetName() {
        Assert.assertEquals(Rock.getInstance().getName(), "Rock");
    }

    @Test
    public void testBeats() {
        Rock rock = Rock.getInstance();
        Paper paper = Paper.getInstance();
        Scissors scissors = Scissors.getInstance();

        Assert.assertFalse(rock.beats(rock));
        Assert.assertFalse(rock.beats(paper));
        Assert.assertTrue(rock.beats(scissors));
    }

    @Test
    public void testTies() {
        Rock rock = Rock.getInstance();
        Paper paper = Paper.getInstance();
        Scissors scissors = Scissors.getInstance();

        Assert.assertTrue(rock.ties(rock));
        Assert.assertFalse(rock.ties(paper));
        Assert.assertFalse(rock.ties(scissors));
    }
}
