package ui;

import domain.Grammar;
import domain.Rule;
import other.BruteParser;
import other.Move;
import other.Utilities;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class CFG
{
    public static void main(String[] args) throws IOException
    {
        Utilities.turnOnUTF();

        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Context free grammar");

        while (true)
        {
            System.out.print( "\nOptions:\n" +
                                "1: Read Me instructions\n" +
                                "2: Edit grammar txt file\n" +
                                "3: Test input string\n" +
                                "4: Exit\n" +
                                "5: Create default txt file" +
                                "\n" +
                                "Select[1-5]: ");

            int selection = 0;
            try{
                selection = Integer.parseInt(scan.nextLine());
            } catch (Exception e){ }

            switch (selection)
            {
                case 0:
                    System.out.println("\nInvalid selection");
                    break;
                case 1:
                    System.out.println( "Instructions:\n" +
                                        "\tFirst, input necessary Grammar rules in the txt file. It\n" +
                                        "contains a set of rules by default as sample. Show below are\n" +
                                        "special characters you may use. Notepad will open automatically.\n" +
                                        "\n" +
                                        "\tType “?”   interpreted as epsilon\n" +
                                        "\tType “->”  interpreted as arrow right\n" +
                                        "\tType “|”   interpreted as pipe (or)\n" +
                                        "\n" +
                                        "Save and close the txt file (notepad) to proceed to the program.\n" +
                                        "Next, select option 3 to test a string for grammar check.\n");
                    break;
                case 2:
                    System.out.println("\nOpening txt file...");
                    ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "src/ui/rules.txt");
                    pb.start();
                    System.out.println("Closing txt file...\n");
                    break;
                case 3:
                    String raw_input = Utilities.txtToString("src/ui/rules.txt");
                    Grammar grammar = new Grammar(raw_input);

                    System.out.println("\nGrammar:");
                    System.out.println(grammar);

                    System.out.print("Input target: ");
                    String user_input = scan.nextLine();

                    BruteParser parse = new BruteParser(user_input, grammar);
                    ArrayList<Move> returnedMoves = parse.bruteParse();

                    if (returnedMoves == null)
                    {
                        System.out.println("Target Rejected");
                        break;
                    }

                    for (Move move : returnedMoves)
                    {
                        System.out.println(move);
                    }
                    Move lastMove = returnedMoves.get(returnedMoves.size() - 1);
                    String finalAns = lastMove.getAfter().replaceAll("Є", "");
                    System.out.println("  → " + finalAns + " (Target Accepted)");

                    break;
                case 4:
                    System.exit(0);
                    break;
                case 5:
                    Utilities.copyFile("src/other/rules.txt", "src/ui/rules.txt");
                    System.out.println("\nrules.txt file overwritten with default sample rules");
                    break;
            }


        }

    }
}
