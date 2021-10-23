package magenta;

import magenta.internal.Core;
import magenta.util.StringUtil;

import java.io.File;

public class Main {
    public static String entry;
    public static void main(String args[]){
        if(args.length>0){
            for(int i = 0;i<args.length;i++) {
                if (new File(args[i]).exists()) {
                    if (args.length > 1) {
                        Core.setArgs(args);
                    }
                }
                if(StringUtil.equal(args[i],"-entry")){
                    entry = args[i+1];
                }
            }
            Core.run_file(args[0]);

        }
        else{
         System.out.println("Magenta [ Version 1.0.0 ]");
         System.out.println("use -help to know commands");
        }
    }
}
