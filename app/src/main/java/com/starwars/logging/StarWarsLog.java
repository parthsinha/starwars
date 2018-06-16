package com.starwars.logging;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class StarWarsLog {
    private static final Object lockObject = new Object();
    private static final LinkedList<LogItem> items = new LinkedList<>();

    public static void v(String tag, String msg) {
        Log.v(tag, msg);
        memoryLog("v", tag, msg);
    }

    public static void v(String tag, String msg, Throwable tr) {
        Log.v(tag, msg + '\n' + getStackTraceString(tr));
        memoryLog("v", tag, msg + '\n' + getStackTraceString(tr));
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
        memoryLog("d", tag, msg);
    }

    public static void d(String tag, String msg, Throwable tr) {
        Log.d(tag, msg + '\n' + getStackTraceString(tr));
        memoryLog("d", tag, msg + '\n' + getStackTraceString(tr));
    }

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
        memoryLog("i", tag, msg);
    }

    public static void i(String tag, String msg, Throwable tr) {
        Log.i(tag, msg + '\n' + getStackTraceString(tr));
        memoryLog("i", tag, msg + '\n' + getStackTraceString(tr));
    }

    public static void w(String tag, String msg) {
        Log.w(tag, msg);
        memoryLog("w", tag, msg);
    }

    public static void w(String tag, String msg, Object... varArgs) {
        Log.w(tag, String.format(msg, varArgs));
        memoryLog("w", tag, msg);
    }

    public static void w(String tag, String msg, Throwable tr) {
        Log.w(tag, msg + '\n' + getStackTraceString(tr));
        memoryLog("w", tag, msg + '\n' + getStackTraceString(tr));
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
        memoryLog("e", tag, msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        Log.e(tag, msg + '\n' + getStackTraceString(tr));
        memoryLog("e", tag, msg + '\n' + getStackTraceString(tr));
    }

    public static void wtf(String tag, String msg) {
        Log.wtf(tag, msg);
        memoryLog("wtf", tag, msg);
    }

    public static void wtf(String tag, Throwable tr) {
        Log.wtf(tag, '\n' + getStackTraceString(tr));
        memoryLog("wtf", tag, '\n' + getStackTraceString(tr));
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        Log.wtf(tag, msg + '\n' + getStackTraceString(tr));
        memoryLog("wtf", tag, msg + '\n' + getStackTraceString(tr));
    }

    private static void memoryLog(String severity, String tag, String message) {

    }

    public static String getStackTraceString(Throwable tr) {
        //this is a terrible kludge to working around a hack in the android sdk
        //the default android getStackTraceString in the Log class filters out unknown host exceptions.
        //this is to apparently reduce spam, but totally useless when you actually would like
        //to see the unknown host exception!

        if (tr == null) {
            return "";
        }

        Throwable t = tr;
        while (t != null) {
            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

    public static String getAllLogsString() {
        StringBuilder sb = new StringBuilder();
        synchronized (lockObject) {
            for (LogItem s : items) {
                sb.append(s.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public static class LogItem {
        private static DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        private String severity;
        private String tag;
        private String msg;
        private long time;

        public LogItem(String severity, String tag, String msg, long time) {
            this.severity = severity;
            this.tag = tag;
            this.msg = msg;
            this.time = time;
        }

        public String getSeverity() {
            return severity;
        }

        public void setSeverity(String severity) {
            this.severity = severity;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String toString() {
            String dateFormatted = formatter.format(time);

            return String.format("%s (%s) [%s]: %s", dateFormatted, severity, tag, msg);
        }
    }
}
