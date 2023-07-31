package domain;

import java.util.Arrays;
import java.util.List;

import static other.Utilities.invalid;

public class Rule
{
    private String variable;
    private String raw_vars_terms;

    public Rule(String variable, String raw)
    {
        if (raw.matches(".*[^a-zA-ZЄ].*"))
            invalid();

        this.variable = variable;
        this.raw_vars_terms = raw;

    }

    @Override
    public String toString()
    {
        return variable + " → " + raw_vars_terms;
    }

    public String getVariable()
    {
        return variable;
    }

    public String getRaw_vars_terms()
    {
        return raw_vars_terms;
    }
}
