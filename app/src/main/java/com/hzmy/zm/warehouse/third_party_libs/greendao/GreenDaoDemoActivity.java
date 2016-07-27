package com.hzmy.zm.warehouse.third_party_libs.greendao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.third_party_libs.greendao.manager.DBManager;
import com.hzmy.zm.warehouse.third_party_libs.greendao.manager.NoteDataManager;
import com.hzmy.zm.warehouse.third_party_libs.greendao.beans.Note;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：        追梦
 * 邮箱：        1521541979@qq.com
 * 公司：        杭州码友网络科技有限公司
 * 日期：        2016/7/11 10:30
 * 描述：
 */
public class GreenDaoDemoActivity extends AppCompatActivity
{
    @Bind(R.id.editTextNote)
    EditText editTextNote;
    @Bind(R.id.buttonAdd)
    Button buttonAdd;
    @Bind(R.id.buttonSearch)
    Button buttonSearch;
    @Bind(R.id.linearLayout1)
    LinearLayout linearLayout1;
    @Bind(android.R.id.list)
    ListView list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao);
        ButterKnife.bind(this);
        initData();
    }

    private void initData()
    {
        NoteDataManager manager = DBManager.getInstance(this).getNoteDataManager()  ;

        Note entity = new Note();    //创建一个SessionEntity实体对象，并赋值
        entity.setText("A");
        entity.setComment("大家好吗？我来了...");

        //保存
        manager.saveOneData(entity);

        //从数据库中获取数据
        List<Note> listData = manager.loadAllData();
        for (int i = 0; i < listData.size(); i++)
        {
            Note temp = listData.get(i);
            String comment = temp.getComment();
            Log.d("tag", comment);

        }

    }
}
