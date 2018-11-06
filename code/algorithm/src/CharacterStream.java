import java.io.FileReader;
import java.io.IOException;

public class CharacterStream {
    public static void main(String[] args) throws IOException
    {
        FileReader sourceStream = null;
        try
        {
            sourceStream = new FileReader("/Users/kokil.jain/code/CodingPractice/code/algorithm/src/test.txt");

            // Reading sourcefile and writing content to
            // target file character by character.
            int temp;
            while ((temp = sourceStream.read()) != -1) {
                System.out.println((char) temp);
            }
        }
        finally
        {
            // Closing stream as no longer in use
            if (sourceStream != null)
                sourceStream.close();
        }
    }
}
