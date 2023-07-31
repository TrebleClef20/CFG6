package other;

import domain.Rule;

public class Move implements Comparable<Move>
{
    private int ruleNum;
    private Rule rule;
    private int score;
    private String before;
    private String after;
    private String target;

    public Move(int ruleNum, Rule rule, String before)
    {
        this.ruleNum = ruleNum;
        this.rule = rule;
        this.before = before;
        this.after = calcAfter();
    }

    public String calcAfter()
    {
        return before.replaceAll(rule.getVariable(), rule.getRaw_vars_terms());
    }

//    public int calcScore()
//    {
//        int score = 0;
//        String[] terminalsArray = calcTerminalsArray(after);
//
//        int len = before.length();
//        for (int i = 0; i < len; i++){
//            char c = before.charAt(i);
//            //Process char
//
//        }
//    }

    @Override
    public int compareTo(Move move)
    {
        return (this.score - move.score);
    }

    public String[] calcTerminalsArray(String raw_vars_terms)
    {
        return raw_vars_terms.replaceAll("[A-Z]", " ").trim().split("\s+");
    }

    public Rule getRule()
    {
        return rule;
    }

    public String getAfter()
    {
        return after;
    }

    @Override
    public String toString()
    {
        return rule.getVariable() + " → " + after + " (" + ruleNum + ")";
    }
}
