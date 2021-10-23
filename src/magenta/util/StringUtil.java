package magenta.util;

public class StringUtil {
    public static boolean equal(String a,String b){
        if(a.length()!=b.length())return false;
      for(int i = 0;i<a.length();i++){
          if(a.charAt(i)!=b.charAt(i)){
              return false;
          }
      }
      return  true;
    }
    public static boolean equalS(String a,String b,int len){
        for(int i = 0;i<len;i++)
            if(a.charAt(i)!=b.charAt(i))
                return false;
            return true;
    }
}
