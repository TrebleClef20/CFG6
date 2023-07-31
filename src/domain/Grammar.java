package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static other.Utilities.countChar;
import static other.Utilities.invalid;

public class Grammar
{
    private static Set<String> variable_set = new HashSet();
    private static Set<String> terminal_set = new HashSet();
    private static String start_symbol;
    private static Rule[] rule_array;

    public Grammar(String input)
    {
        process(input);
    }

    public static void process(String input)
    {
        input = input.replaceAll(" ", "");
        input = input.replaceAll("\\?", "Є");
        input = input.replaceAll("->", "→");

        int arrow_count = countChar(input, '→');
        int pipe_count = countChar(input, '|');

        rule_array = new Rule[arrow_count + pipe_count];

        String[] first_split = input.split("\n");
        if (first_split.length != arrow_count)
            invalid();

        int j = 0;
        for (String element : first_split)
        {
            String[] second_split = element.split("[→|]");

            if (second_split.length < 2)
                invalid();
            if (second_split[0].length() > 1)
                invalid();
            if (!isFirstUpper(second_split[0]))
                invalid();

            String current_variable = second_split[0];
            variable_set.add(current_variable);

            int len = second_split.length;
            for (int i = 1; i < len; i++)
            {
                String curr_raw = second_split[i];
                if (curr_raw.equals(""))
                    invalid();
                Rule curr_rule = new Rule(current_variable, curr_raw);

                rule_array[j] = curr_rule;
                j++;

                for (int k = 0; k < curr_raw.length(); k++)
                {
                    char curr_letter = curr_raw.charAt(k);
                    if(!Character.isUpperCase(curr_letter))
                        terminal_set.add(curr_letter + "");
                }
            }
        }

        if (variable_set.contains("S"))
            start_symbol = "S";
        else
            start_symbol = rule_array[0].getVariable();
    }

    public static boolean isFirstUpper(String str)
    {
        return Character.isUpperCase(str.charAt(0));
    }

    public Rule[] getRule_array()
    {
        return rule_array;
    }

    public String getStart_symbol()
    {
        return start_symbol;
    }

    @Override
    public String toString()
    {
        String results = "";

        int len = rule_array.length;
        for (int i = 0; i < len; i++)
        {
            results += (i+1) + ".\t" + rule_array[i] + "\n";
        }
        return results;
    }
}