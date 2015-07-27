package com.hxht.testquickindex;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hxht.testquickindex.adapter.HeroesAdapter;
import com.hxht.testquickindex.domain.Heroes;
import com.hxht.testquickindex.mycustomview.QuickIndexBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends Activity {

    private ListView lv;
    private QuickIndexBar quickindexbar;
    private TextView tvShowletter;
    private List<Heroes> list;
    private static final String[] names = new String[]{
            "宋江",
            "卢俊义",
            "吴用",
            "公孙胜",
            "关胜",
            "林冲",
            "秦明",
            "呼延灼",
            "花荣",
            "柴进",
            "李应",
            "朱仝",
            "鲁智深",
            "武松",
            "董平",
            "张清",
            "杨志",
            "徐宁",
            "索超",
            "戴宗",
            "刘唐",
            "李逵",
            "史进",
            "穆弘",
            "雷横",
            "李俊",
            "阮小二",
            "张横",
            "阮小五",
            "张顺",
            "阮小七",
            "杨雄",
            "石秀",
            "解珍",
            "解宝 ",
            "燕青",
            "朱武",
            "黄信",
            "孙立",
            "宣赞",
            "郝思文",
            "韩滔",
            "彭玘",
            "单廷圭",
            "魏定国",
            "萧让",
            "裴宣",
            "欧鹏",
            "邓飞",
            "燕顺",
            "杨林",
            "凌振",
            "蒋敬",
            "吕方",
            "郭盛",
            "安道全",
            "皇甫端",
            "王英",
            "扈三娘",
            "鲍旭",
            "樊瑞",
            "孔明",
            "孔亮",
            "项充",
            "李衮",
            "金大坚",
            "马麟",
            "童威",
            "童猛",
            "孟康",
            "侯健",
            "陈达",
            "杨春",
            "郑天寿",
            "陶宗旺",
            "宋清",
            "乐和",
            "龚旺",
            "丁得孙",
            "穆春",
            "曹正",
            "宋万",
            "杜迁",
            "薛永",
            "李忠",
            "周通",
            "汤隆",
            "杜兴",
            "邹渊",
            "邹润",
            "朱贵",
            "朱富",
            "施恩",
            "蔡福",
            "蔡庆",
            "李立",
            "李云",
            "焦挺",
            "石勇",
            "孙新",
            "顾大嫂",
            "孙二娘",
            "王定六",
            "郁保四",
            "白胜",
            "时迁",
            "段景住"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initData();
    }

    /**
     * 给我们需要的控件填充数据
     */
    private void initData() {
        //lv.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,names));

        list = new ArrayList<Heroes>();
        list.clear();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            Heroes heroes = new Heroes(name);
            list.add(heroes);
        }

        Collections.sort(list);

        HeroesAdapter adapter = new HeroesAdapter(MainActivity.this,list);
        lv.setAdapter(adapter);

        quickindexbar.setOnUpdateLetterListener(new QuickIndexBar.OnUpdateLetterListener() {
            @Override
            public void updateLetter(String letter) {

                showLetter(letter);
                for (int i = 0; i < list.size(); i++) {
                    if (TextUtils.equals(String.valueOf(list.get(i).getPinyin().charAt(0)), letter)) {
                        lv.setSelection(i);
                        break;
                    }
                }
            }
        });
    }

    private Handler handler = new Handler();
    private void showLetter(String letter) {
        tvShowletter.setText(letter);
        tvShowletter.setVisibility(View.VISIBLE);

        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvShowletter.setVisibility(View.GONE);
            }
        },1000);
    }

    /**
     * 找到我们需要的控件
     */
    private void initViews() {
        lv = (ListView) findViewById(R.id.lv);
        quickindexbar = (QuickIndexBar) findViewById(R.id.quickindexbar);
        tvShowletter = (TextView) findViewById(R.id.tv_showletter);
    }
}
