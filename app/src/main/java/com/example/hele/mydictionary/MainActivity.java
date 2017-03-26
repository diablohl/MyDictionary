package com.example.hele.mydictionary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hele.mydictionnary.util.AsycHttp;
import com.example.hele.mydictionnary.util.Contants;
import com.example.hele.mydictionnary.util.Parser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.search_voice_btn)
    ImageButton searchVoiceBtn;
    @Bind(R.id.key_word)
    EditText keyWord;
    @Bind(R.id.start_query)
    Button startQuery;
    @Bind(R.id.show_result)
    TextView showResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        startQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入内容
                String input=keyWord.getText().toString().trim();
                //执行网络请求
                AsycHttp.getInstance().requestForGet(Contants.PATH_QUERY,input,monResponseListner);
            }
        });
    }
    AsycHttp.onResponseListner monResponseListner=new AsycHttp.onResponseListner(){


        @Override
        public void onSuccess(String result) {

            //解析
            if (null!=result) {
                showResult.setText(Parser.parserData(result).toString());
            }

        }

        @Override
        public void onFailde(String error) {

            Toast.makeText(MainActivity.this,"数据错误"+error,Toast.LENGTH_SHORT).show();
        }
    };
}
