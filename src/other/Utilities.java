package other;

import java.io.*;
import java.nio.channels.FileChannel;

public class Utilities
{
    public static void turnOnUTF()
    {
        // allows printing selected special characters like ε and →
        // snippet from https://stackoverflow.com/questions/28567208/how-can-i-change-the-standard-out-to-utf-8-in-java
        try {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new InternalError("VM does not support mandatory encoding UTF-8");
        }
    }


    // https://stackoverflow.com/questions/6100712/simple-way-to-count-character-occurrences-in-a-string
    public static int countChar(String str, char c)
    {
        int count = 0;

        for(int i=0; i < str.length(); i++)
        {    if(str.charAt(i) == c)
            count++;
        }

        return count;
    }


    public static void invalid()
    {
        System.out.println("invalid, quitting");
        System.exit(1);
    }

    //https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
    public static String txtToString(String path) throws IOException
    {
        String results = "";
        File file = new File(path);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            results += st + "\n";

        return results;
    }


    // https://stackoverflow.com/questions/106770/standard-concise-way-to-copy-a-file-in-java
    public static void copyFile(String sourceFile, String destFile) throws IOException
    {
        File sourc = new File(sourceFile);
        File dest = new File(destFile);

        if(!dest.exists()) {
            dest.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourc).getChannel();
            destination = new FileOutputStream(dest).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }
}
