package com.iserban.app.model.game.moves;

import org.junit.Assert;
import org.junit.Test;

public class ScissorsTest {

    @Test
    public void testGetName() {
        Assert.assertEquals(Scissors.getInstance().getName(), "Scissors");
    }

    @Test
    public void testBeats() {
        Rock rock = Rock.getInstance();
        Paper paper = Paper.getInstance();
        Scissors scissors = Scissors.getInstance();

        Assert.assertFalse(scissors.beats(scissors));
        Assert.assertFalse(scissors.beats(rock));
        Assert.assertTrue(scissors.beats(paper));
    }

    @Test
    public void testTies() {
        Rock rock = Rock.getInstance();
        Paper paper = Paper.getInstance();
        Scissors scissors = Scissors.getInstance();

        Assert.assertTrue(scissors.ties(scissors));
        Assert.assertFalse(scissors.ties(rock));
        Assert.assertFalse(scissors.ties(paper));
    }
}
