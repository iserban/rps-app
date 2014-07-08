package com.iserban.app.model.game.moves;

import org.junit.Assert;
import org.junit.Test;

public class PaperTest {

    @Test
    public void testGetName() {
        Assert.assertEquals(Paper.getInstance().getName(), "Paper");
    }

    @Test
    public void testBeats() {
        Rock rock = Rock.getInstance();
        Paper paper = Paper.getInstance();
        Scissors scissors = Scissors.getInstance();

        Assert.assertFalse(paper.beats(paper));
        Assert.assertFalse(paper.beats(scissors));
        Assert.assertTrue(paper.beats(rock));
    }

    @Test
    public void testTies() {
        Rock rock = Rock.getInstance();
        Paper paper = Paper.getInstance();
        Scissors scissors = Scissors.getInstance();

        Assert.assertTrue(paper.ties(paper));
        Assert.assertFalse(paper.ties(scissors));
        Assert.assertFalse(paper.ties(rock));
    }
}
