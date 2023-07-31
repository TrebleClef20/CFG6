package ui;

import domain.Grammar;
import domain.Rule;
import other.BruteParser;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;


public class Tester
{
    public static void main(String[] args)
    {
        // allows printing selected special characters like ε and →
        // snippet from https://stackoverflow.com/questions/28567208/how-can-i-change-the-standard-out-to-utf-8-in-java
        try {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new InternalError("VM does not support mandatory encoding UTF-8");
        }

        String raw_input =  "S -> abcd| aMbcNd\n" +
                            "M-> aMb | Є\n" +
                            "N->cNd|Є\n";

        Grammar grammar = new Grammar(raw_input);

        for (Rule each: grammar.getRule_array())
        {
            System.out.println(each);
        }

        String inp = "aabbccdd";

        BruteParser parse = new BruteParser(inp, grammar);
        parse.bruteParse();

    }
}
