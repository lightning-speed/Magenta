package magenta.internal;

public class Parser {
    public static String[][] parse(String in){
        String out[][] = new String[50][2];
        String temp[]= in.split("end");
        for(int i = 0;i<temp.length-1;i++){
            out[i][0] = temp[i].split(":")[0];
            out[i][1] = temp[i].split(":")[1];
        }
        return out;
    }
}
