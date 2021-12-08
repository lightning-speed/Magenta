package magenta.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManager {
    public static String read_file(String fn){
        String out = "";
        try {
            Scanner sc = new Scanner(new File(fn));
            while(sc.hasNextLine())out+=sc.nextLine()+"\n";

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return out;
    }
}
