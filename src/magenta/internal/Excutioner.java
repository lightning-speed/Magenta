package magenta.internal;

import magenta.util.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Excutioner {
    public static String current_dir;
    public static String vars[] = new String[300];
    public static int index = 0;
    public static void run_instruction(String ins){
        if(current_dir==null)current_dir = System.getProperty("user.dir");
        if(StringUtil.equalS(ins,"set",3)){
           vars[index] = ins.split(" ")[1];
           vars[index+1] = ins.substring(5+vars[index].length(),ins.length());
           index+=2;
        }
        for(int i = 0;i<index;i+=2){
            ins = ins.replace("("+vars[i]+")",vars[i+1]);
        }
        if(StringUtil.equalS(ins,"del",3)){
            new File(current_dir+"/"+ins.substring(4,ins.length())).delete();
        }
        if(StringUtil.equalS(ins,"pause",5)){
            System.out.println("Press enter to continue");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

     if(StringUtil.equalS(ins,"echo",4)){
         System.out.println(ins.substring(5,ins.length()));
     }
        if(StringUtil.equalS(ins,"mkdir",5)){
            new File(current_dir+"/"+ins.substring(6,ins.length())).mkdir();
        }
        if(StringUtil.equalS(ins,"cd",2)){
            current_dir =  new File(ins.substring(3,ins.length())).getAbsolutePath();
            System.out.println(current_dir);
        }
     if(StringUtil.equalS(ins,"jump",4)){
         Core.run_function(ins.substring(5,ins.length()));
     }

     if(StringUtil.equalS(ins,"exec",4)){
         try {
             System.out.println(ins.substring(5,ins.length()));
             ProcessBuilder prc = new ProcessBuilder(ins.substring(5,ins.length()).split(" "));
             prc.redirectErrorStream(true);
             prc.directory(new File(current_dir));
             Process p = prc.start();
             try (BufferedReader input =
                          new BufferedReader(new
                                  InputStreamReader(p.getInputStream()))) {
                 String line;
                 while ((line = input.readLine()) != null) {
                     System.out.println(line);
                 }
                 while(p.isAlive()){
                     try {
                         Thread.sleep(1);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }
         } catch (IOException e) {
             System.out.println("Executable not found: "+ins.split(" ")[1]);
         }
     }

    }
}
