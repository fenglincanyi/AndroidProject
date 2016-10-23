package com.chinahr.android.common.http;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by geng
 * on 2016/10/18.
 */
public class FileResponseBody  extends ResponseBody {

    private ResponseBody responseBody;
    FileCallBack<FileResponseBody> callBack;
    private BufferedSource bufferedSource;

    public long totalBytesRead = 0L;

    public FileResponseBody(ResponseBody responseBody, FileCallBack callBack) {
        this.responseBody = responseBody;
        this.callBack = callBack;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                callBack.onProgress(totalBytesRead, contentLength());
                return bytesRead;
            }
        };
    }

}
