package com.example.hele.mydictionnary.util;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by hele on 2017/3/25.
 */

public class AsycHttp {
    public static final String TAG=AsycHttp.class.getSimpleName();
    private static AsycHttp mAsycHttp;
    private AsycHttp(){

    }

    public static AsycHttp getInstance(){
        if (mAsycHttp==null){
            synchronized (AsycHttp.class){
                if (null==mAsycHttp){
                    mAsycHttp=new AsycHttp();
                }
            }
        }
        return mAsycHttp;
    }



    public void requestForGet(String path,String input,onResponseListner monResponseListner){
        //执行网络请求
        if(null!=input||"".equals(input)){
            StringBuffer buf=new StringBuffer();
            buf.append(path).append(input);

            HttpGet request=new HttpGet(buf.toString());
            new DictAsycTask(monResponseListner).execute(request);

        }
    }

    class DictAsycTask extends AsyncTask<HttpUriRequest,Void,String> {

        onResponseListner mResponseListner;
        public DictAsycTask(onResponseListner monResponseListner){

            mResponseListner=monResponseListner;
        }
        int resultCode;
        @Override
        protected String doInBackground(HttpUriRequest... params) {
            HttpClient client=new DefaultHttpClient();
            try {
                HttpResponse response=client.execute(params[0]);
                resultCode=response.getStatusLine().getStatusCode();
                if (resultCode== HttpStatus.SC_OK){
                    HttpEntity entity=response.getEntity();
                    return EntityUtils.toString(entity);
                }else {
                    params[0].abort();
                }
            } catch (ClientProtocolException e) {
                Log.e(TAG,"ClientProtocolException");
            }catch (IOException e) {
                Log.e(TAG,"IOException");
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (null!=s){
               // Log.e(TAG,"结果："+s);
                mResponseListner.onSuccess(s);
            }else {
                //Log.e(TAG,"失败："+resultCode);
                mResponseListner.onFailde("error"+resultCode);

            }
        }
    }
    //回调借口
    public  interface onResponseListner{
        void onSuccess(String result);
        void onFailde(String error);
    }

}
