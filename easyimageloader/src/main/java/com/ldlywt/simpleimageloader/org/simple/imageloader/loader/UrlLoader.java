/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 bboyfeiyu@gmail.com ( Mr.Simple )
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.ldlywt.simpleimageloader.org.simple.imageloader.loader;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ldlywt.simpleimageloader.jakewharton.disklrucache.IOUtil;
import com.ldlywt.simpleimageloader.org.simple.imageloader.request.BitmapRequest;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author mrsimple
 */
public class UrlLoader extends AbsLoader {
    @Override
    protected Bitmap onLoadImage(BitmapRequest result) {
        try {
            String imageUri = result.imageUri;
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(imageUri).build();
            Response response = okHttpClient.newCall(request).execute();
            InputStream inputStream = response.body().byteStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public Bitmap onLoadImage(BitmapRequest request) {
//        final String imageUrl = request.imageUri;
//        FileOutputStream fos = null;
//        InputStream is = null;
//        Bitmap bitmap = null ;
//         HttpURLConnection conn = null ;
//        try {
//            URL url = new URL(imageUrl);
//            conn = (HttpURLConnection) url.openConnection();
//            is = new BufferedInputStream(conn.getInputStream());
//            bitmap =  BitmapFactory.decodeStream(is, null, null);
//        } catch (Exception e) {
//        	e.printStackTrace();
//        } finally {
//            IOUtil.closeQuietly(is);
//            IOUtil.closeQuietly(fos);
//            if ( conn != null ) {
//                // 关闭流
//                conn.disconnect();
//			}
//        }
//        return bitmap;
//    }



}
