package com.example.hele.mydictionnary.entity;

import java.util.List;

/**
 * Created by hele on 2017/3/25.
 */

public class Web {
    public String key;
    public List<String> values;

    @Override
    public String toString() {
        return key+":"+getAllWebValues();
    }

    public String getAllWebValues(){
        StringBuffer stringBuffer=new StringBuffer();
        if (null!=values&&values.size()>0){

            for (int i=0;i < values.size();i++){
                stringBuffer.append(values.get(i)).append(",");

            }
            return stringBuffer.deleteCharAt(stringBuffer.length()-1).toString();
        }
        return "无";
    }
}
