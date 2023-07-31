package other;

import domain.Grammar;
import domain.Rule;

import java.util.ArrayList;
import java.util.Collections;

public class BruteParser
{
    private String orig_target;
    private Grammar grammar;
    private Rule[] rules_array;
    private ArrayList<Move> winningMoves = new ArrayList<Move>();
    private ArrayList<Integer> levelsAccepted = new ArrayList<Integer>();
    private boolean found = false;

    public BruteParser(String target, Grammar grammar)
    {
        this.orig_target = target;
        this.grammar = grammar;
        this.rules_array = grammar.getRule_array();
    }

    public ArrayList<Move> bruteParse()
    {
        String start_var = grammar.getStart_symbol();

        if (recursiveParse(start_var, start_var, 0))
        {
            Collections.reverse(winningMoves);

            return winningMoves;
        }
        return null;
    }

    public ArrayList<Move> Possibilities(String variable, String curr_state)
    {
        ArrayList<Move> possibles = new ArrayList<Move>();

        int len = rules_array.length;
        for (int i = 0; i < len; i++)
        {
            if (rules_array[i].getVariable().equals(variable))
            {
                Move curr_move = new Move(i + 1, rules_array[i], curr_state);
                possibles.add(curr_move);
            }
        }

        return possibles;
    }

    public boolean recursiveParse(String variable, String curr_state, Integer level)
    {

        String[] terminals = calcTerminalsArray(curr_state);

        // count terminals
        int terminals_count = 0;
        for (int i = 0; i < terminals.length; i++)
        {
            terminals_count += terminals[i].length();
        }

        if (terminals_count > orig_target.length())
            return false;

        if (terminals.length == 1 && !terminals[0].equals(""))
        {
            return terminals[0].equals(orig_target);
        }

        ArrayList<Move> possible_Moves = Possibilities(variable, curr_state);
        for (Move curr_move : possible_Moves)
        {
            String next_state = curr_move.getAfter().replaceAll("Є", "");
            String[] variables = calcVariablesArray(next_state);
            for (String var: variables)
            {
                if (recursiveParse(var, next_state, level+1))
                {
                    winningMoves.add(curr_move);
                    return true;
                }
            }
        }
        return false;
    }

    public String[] calcTerminalsArray(String raw_vars_terms)
    {
        return raw_vars_terms.replaceAll("[A-Z]", " ").trim().split("\s+");
    }

    public String[] calcVariablesArray(String raw_vars_terms)
    {
        return raw_vars_terms.replaceAll("[a-zЄ]", " ").trim().split("\s+");
    }
}
