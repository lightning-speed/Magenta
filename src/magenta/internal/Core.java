package magenta.internal;

import magenta.io.FileManager;
import magenta.util.StringUtil;


public class Core {
    public static String args[];
    public static int current_line;
    public static String lines[];
    public static void setArgs(String ars[]){
        args = new String[ars.length-1];
        for(int i = 1;i<ars.length;i++){
           args[i-1] = ars[i];
        }
    }
    public static void run_file(String file_path){
      String fc = FileManager.read_file(file_path);

        try {
          for (int i = 0; i < 30; i++) {
              String t = "%" + (i + 1);
              fc = fc.replace(t, args[i]);
          }
      }catch (Exception e){}
      fc = fc.replace("\n\n","\n");
      fc = fc.replace("  "," ");
      fc = fc.replace("\n ","\n");
      lines = fc.split("\n");
      for(current_line = 0;current_line<lines.length;current_line++){
          Excutioner.run_instruction(lines[current_line]);
      }
    }
    public static void run_function(String name){
     for(int i = 0;i<lines.length;i++){
         if(StringUtil.equal(lines[i],name+":")){
             current_line = i;
         }
     }
    }
}
