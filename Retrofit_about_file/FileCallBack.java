package com.chinahr.android.common.http;

import android.os.Environment;

import com.chinahr.android.m.util.LogUtil;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 用于File 下载的CallBack
 *
 * Created by geng
 * on 2016/10/19.
 */
public abstract class FileCallBack<T extends ResponseBody> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() >= 200 && response.code() < 300) {
            saveFile(response);
            return;
        }
        onFailure(call, null);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFail(call, t);
    }

    public abstract void onSuccess(File apkFile);

    public abstract void onProgress(long progress, long total);

    public abstract void onFail(Call<T> call, Throwable t);

    public File saveFile(Response<T> response) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Download";

        InputStream is = null;
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, "chinahr.apk");
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();

            onSuccess(file);

            return file;
        } catch (IOException e) {
            LogUtil.e(e.getMessage());
            CrashReport.postCatchedException(e);
            return null;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }

            } catch (IOException e) {
                LogUtil.e(e.getMessage());
                CrashReport.postCatchedException(e);
            }
        }
    }
}
