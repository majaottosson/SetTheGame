package Test;

import SetLogic.Rules;
import SetLogic.Cards;
import SetLogic.Deck;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


public class TestSet {

    @Test
    void testDeck() {
        Deck deck = new Deck();
        Cards testcards1 = new Cards("green", 2, 'S', "empty");
        Cards testcards2 = new Cards("blue", 1, '<', "full");
        Cards testcards3 = new Cards("red", 3, 'O', "striped");
        
        assertTrue(deck.deck.contains(testcards1));
        assertTrue(deck.deck.contains(testcards2));
        assertTrue(deck.deck.contains(testcards3));
        
    }

    @Test
    void testFillDeck() {
        Deck deck = new Deck();
        
        List<Cards> cards = deck.getDeck();
        cards.remove(0);
        cards.remove(0);
        deck.filldeck();
        
        assertEquals(79, cards.size());
    }

    @Test
    void testColorRuleSameColor() {
        Cards testcards1 = new Cards("green", 1, 'O', "empty");
        Cards testcards2 = new Cards("green", 1, '<', "full");
        Cards testcards3 = new Cards("green", 3, 'O', "striped");
        
        Cards[] c = {testcards1, testcards2, testcards3};
        Rules rules = new Rules();
        
        assertEquals(1, rules.ColorRule(c), "");
        
    }

    @Test
    void testColorRuleDifferentColor() {
        Cards testcards1 = new Cards("green", 2, 'S', "empty");
        Cards testcards2 = new Cards("blue", 1, '<', "full");
        Cards testcards3 = new Cards("red", 3, 'O', "striped");
        
        Cards[] c = {testcards1, testcards2, testcards3};
        Rules rules = new Rules();
        
        assertEquals(0, rules.ColorRule(c), "");
    }

    @Test
    void testColorRuleTwoSameColor() {
        Cards testcards1 = new Cards("green", 2, 'S', "empty");
        Cards testcards2 = new Cards("green", 1, '<', "full");
        Cards testcards3 = new Cards("red", 3, 'O', "striped");
        
        Cards[] c = {testcards1, testcards2, testcards3};
        Rules rules = new Rules();
        
        assertEquals(-1, rules.ColorRule(c), "");
        
    }

    @Test
    void testFilledRuleSameFilling() {
        Cards testcards1 = new Cards("green", 1, 'O', "empty");
        Cards testcards2 = new Cards("green", 1, '<', "empty");
        Cards testcards3 = new Cards("green", 3, 'O', "empty");
        
        Cards[] c = {testcards1, testcards2, testcards3};
        Rules rules = new Rules();
        
        assertEquals(1, rules.FilledRule(c), "");
    }

    @Test
    void testFilledRuleDifferentFilling() {
        Cards testcards1 = new Cards("green", 1, 'O', "empty");
        Cards testcards2 = new Cards("green", 1, '<', "full");
        Cards testcards3 = new Cards("green", 3, 'O', "striped");
        
        Cards[] c = {testcards1, testcards2, testcards3};
        Rules rules = new Rules();
        
        assertEquals(0, rules.FilledRule(c), "");
    }

    @Test
    void testFillingRuleTwoSameFilling() {
        Cards testcards1 = new Cards("green", 1, 'O', "full");
        Cards testcards2 = new Cards("green", 1, '<', "full");
        Cards testcards3 = new Cards("green", 3, 'O', "striped");
        
        Cards[] c = {testcards1, testcards2, testcards3};
        Rules rules = new Rules();
        
        assertEquals(-1, rules.FilledRule(c), "");
    }

    @Test
    void testNumberRuleSameNumber() {
        Cards testcards1 = new Cards("green", 1, 'O', "empty");
        Cards testcards2 = new Cards("green", 1, '<', "full");
        Cards testcards3 = new Cards("green", 1, 'O', "striped");
        
        Cards[] c = {testcards1, testcards2, testcards3};
        Rules rules = new Rules();
        
        assertEquals(1, rules.NumberRule(c), "");
    }

    @Test
    void testNumberRuleDifferentNumber() {
        Cards testcards1 = new Cards("green", 1, 'O', "empty");
        Cards testcards2 = new Cards("green", 2, '<', "full");
        Cards testcards3 = new Cards("green", 3, 'O', "striped");
        
        Cards[] c = {testcards1, testcards2, testcards3};
        Rules rules = new Rules();
        
        assertEquals(0, rules.NumberRule(c), "");
    }

    @Test
    void testNumberRuleTwoSameNumber() {
        Cards testcards1 = new Cards("green", 1, 'O', "empty");
        Cards testcards2 = new Cards("green", 3, '<', "full");
        Cards testcards3 = new Cards("green", 3, 'O', "striped");
        
        Cards[] c = {testcards1, testcards2, testcards3};
        Rules rules = new Rules();
        
        assertEquals(-1, rules.NumberRule(c), "");
    }

    @Test 
    void testFigureRuleSameFigure() {
        Cards testcards1 = new Cards("green", 1, 'O', "empty");
        Cards testcards2 = new Cards("green", 3, 'O', "full");
        Cards testcards3 = new Cards("green", 3, 'O', "striped");
        
        Cards[] c = {testcards1, testcards2, testcards3};
        Rules rules = new Rules();
        
        assertEquals(1, rules.FigureRule(c), "");
    }

    @Test
    void testFigureRuleDifferentFigure() {
        Cards testcards1 = new Cards("green", 1, 'O', "empty");
        Cards testcards2 = new Cards("green", 3, 'S', "full");
        Cards testcards3 = new Cards("green", 3, '<', "striped");
        
        Cards[] c = {testcards1, testcards2, testcards3};
        Rules rules = new Rules();
        
        assertEquals(0, rules.FigureRule(c), "");
    }

    @Test
    void testFigureRuleTwoSameFigure() {
        Cards testcards1 = new Cards("green", 1, 'O', "empty");
        Cards testcards2 = new Cards("green", 3, 'S', "full");
        Cards testcards3 = new Cards("green", 3, 'S', "striped");
        
        Cards[] c = {testcards1, testcards2, testcards3};
        Rules rules = new Rules();
        
        assertEquals(-1, rules.FigureRule(c), "");
    }

    @Test
    void testAllRulesIsSet() {
        Cards testcards1 = new Cards("green", 1, 'O', "empty");
        Cards testcards2 = new Cards("green", 2, '<', "full");
        Cards testcards3 = new Cards("green", 3, 'S', "striped");
        
        Rules rules = new Rules();
        rules.AllRules(testcards1, testcards2, testcards3);
        
        assertTrue(rules.getisSet());
    }

    @Test
    void testAllRulesIsNotSet() {
        Cards testcards1 = new Cards("green", 1, 'O', "empty");
        Cards testcards2 = new Cards("green", 2, '<', "full");
        Cards testcards3 = new Cards("blue", 3, 'S', "striped");
        
        Rules rules = new Rules();
        rules.AllRules(testcards1, testcards2, testcards3);
        
        assertFalse(rules.getisSet());
    }


}
