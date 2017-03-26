package com.example.hele.mydictionnary.entity;

import java.util.List;

/**
 * Created by hele on 2017/3/25.
 */

public class Result {
    public String query; //关键字
    public String translation; //有道翻译
    public String phonetic;//中文音标
    public String usPhonetic;//美式发音
    public String ukPhonetic;//英式发音
    public List<String> explains; //基本释义
    public List<Web> webs;

    @Override
    public String toString() {
        return "关键字："+checkIsEmpty(query)+"\n中文发音："+checkIsEmpty(phonetic)+"\n有道词典："+checkIsEmpty(translation)
                +"\n基本释义："+getExplain()+"\n网络释义："+getWeb();
    }

    //检查是否为空 容错处理
    public String checkIsEmpty(String word){
        return word==null?"无":word;
    }

    public String getExplain(){
        StringBuffer stringBuffer=new StringBuffer();
        if (null!=explains&&explains.size()>0){

            for (int i=0;i < explains.size();i++){
                stringBuffer.append(explains.get(i)).append(",");

            }
            return stringBuffer.deleteCharAt(stringBuffer.length()-1).toString();
        }
         return "无";
    }

    public String getWeb(){
        StringBuffer stringBuffer=new StringBuffer();
        if (null!=webs&&webs.size()>0){

            for (int i=0;i < webs.size();i++){
                stringBuffer.append(webs.get(i).toString()).append(";");

            }
            return stringBuffer.deleteCharAt(stringBuffer.length()-1).toString();
        }
        return "无";
    }

}
