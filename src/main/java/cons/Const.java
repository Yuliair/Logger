package cons;

/**
 * Created by Юлия on 23.04.2016.
 */
public class Const {
    public static String folderFileIn = "";
    public static String folderFileOut = "";
    public static String fileOut = "";
    public static final String cvsSplitBy = ",";
    public static final int secInDay = 86400;//60*60*24;
    public static final int millSinS = 1000;
    public static final int threadsCount = 10;
    public static Boolean working = true;

    public static void setfolderFileIn(String string) {
        folderFileIn = string;
    }

    public static void setfolderFileOut(String string) {
        folderFileOut = string;
    }

    public static void setFileOut(String string) {
        fileOut = string;
    }

    public static long dayOf(long time) {
        return time / Const.secInDay;
    }


}
