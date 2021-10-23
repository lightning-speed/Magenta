package magenta.internal;

import magenta.Main;
import magenta.io.FileManager;
import magenta.util.StringUtil;

import java.lang.reflect.Executable;

public class Core {
    public static String args[];
    public static String functions[][];
    public static void setArgs(String ars[]){
        args = new String[ars.length-1];
        for(int i = 1;i<ars.length;i++){
           args[i-1] = ars[i];
        }
    }
    public static void run_file(String file_path){
      String fc = FileManager.read_file(file_path);
      fc = fc.split("main")[1];
      try {
          for (int i = 0; i < 30; i++) {
              String t = "%" + (i + 1);
              fc = fc.replace(t, args[i]);
          }
      }catch (Exception e){}
      fc = fc.replace("\n\n","\n");
      fc = fc.replace("  "," ");
      fc = fc.replace("\n ","\n");
        functions  = Parser.parse(fc);
        if(Main.entry==null)
        run_function("main");
        else
            run_function(Main.entry);
    }
    public static void run_function(String name){
     int loc = 0;
     try {

         for (int i = 0; i < functions.length; i++) {
             if (StringUtil.equal(name, functions[i][0].replace("\n",""))) {
                 loc = i;
                 break;
             }
         }
     }catch (Exception e){}
     String ins[] = functions[loc][1].split("\n");
     for(int i = 0;i<ins.length;i++)
         try {
             Excutioner.run_instruction(ins[i]);

         }catch (Exception e){}
    }
}
