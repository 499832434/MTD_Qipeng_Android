package com.zibo.qipeng.asphalt.utils;

import android.util.Log;

public class Logger {
	public static int DEBUG_LEVEL = 5;

	private static final int VERBOSE = 5;
	private static final int DEBUG = 4;
	private static final int INFO = 3;
	private static final int WARN = 2;
	private static final int ERROR = 1;

	public static void v(String tag, String msg) {
		if (DEBUG_LEVEL > VERBOSE) {
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (DEBUG_LEVEL > DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (DEBUG_LEVEL > INFO) {
			Log.i(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (DEBUG_LEVEL > WARN) {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String error) {
		if (DEBUG_LEVEL > ERROR) {
			Log.e(tag, error);
		}
	}
	
	public static void e(String tag, Exception e) {
		if (DEBUG_LEVEL > ERROR) {
			if(e == null) return;
			//Log.e(tag, e.getMessage() != null ? e.getMessage() : "", e);
		}
	}

	public static void ee(String tag, String msg) {  //信息太长,分段打印
		//因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
		//  把4*1024的MAX字节打印长度改为2001字符数
		int max_str_length = 2001 - tag.length();
		//大于4000时
		while (msg.length() > max_str_length) {
			Log.e(tag, msg.substring(0, max_str_length));
			msg = msg.substring(max_str_length);
		}
		//剩余部分
		Log.e(tag, msg);
	}
}