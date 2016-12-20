package com.tac.iparttimejob.UI.Utils;

import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by AiProgram on 2016/12/19.
 */

public class TextUitls {
    /**
     * 实现文本复制功能
     * @param content
     */
    public static void copy(String content, Context context) {
// 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }
}
