package cn.finalteam.mongodao;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/7/8 上午3:59
 */
public class CommonUtils {
    public static String getStackTrace(Throwable e) {
        String stacks = "";
        StringWriter sw = null;
        PrintWriter pw = null;

        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            stacks = sw.toString();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (Throwable t1) {
                t1.printStackTrace();
            }

            try {
                if (sw != null) {
                    sw.close();
                }
            } catch (Throwable t2) {
                t2.printStackTrace();
            }
        }

        return stacks;
    }
}
