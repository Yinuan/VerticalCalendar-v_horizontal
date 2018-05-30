package com.muzi.verticalcalendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.muzi.library.CalendarView;
import com.muzi.library.bean.DayBean;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private Button btnClear, btnUnable;
    private EditText editText;
    private Calendar activeStart, activeEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnUnable = (Button) findViewById(R.id.btnUnable);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.resetState();
            }
        });
        btnUnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setUnableDays(Integer.parseInt(editText.getText().toString().trim()));
            }
        });
        calendarView.resetState();

        calendarView.setOnCalendarChange(new CalendarView.OnCalendarChange() {
            @Override
            public void onStart(DayBean dayBean) {
                Log.d("MainActivity", "dayBean:" + dayBean);
            }

            @Override
            public void onEnd(DayBean dayBean) {
                Log.d("MainActivity", "dayBean:" + dayBean);
            }

            @Override
            public void onDays(int day) {
                super.onDays(day);
                Log.d("MainActivity", "day:" + day);
                if (day>30){
                    Toast.makeText(MainActivity.this, "选择天数不能大于三十天", Toast.LENGTH_SHORT).show();
                }
            }
        });

        activeStart = Calendar.getInstance();

        activeEnd = Calendar.getInstance();


       // activeStart.add(Calendar.DAY_OF_MONTH, 3);
        //activeEnd.add(Calendar.DAY_OF_MONTH, 30);
        activeStart.set(2017,07,01);
        activeEnd.set(2018,05,30);


//        calendarView.setOnIntercept(new OnIntercept() {
//            @Override
//            public void onIntercept(Calendar today, List<MonthBean> monthList, CalendarAdapter adapter) {
//                for (MonthBean monthBean : monthList) {
//                    for (DayBean dayBean : monthBean.getDayList()) {
//                        if (dayBean.getCalendar().before(activeStart) ||
//                                dayBean.getCalendar().after(activeEnd)) {
//                            dayBean.setSelectState(SelectState.UNABLE);
//                        }
//                    }
//                }
//                adapter.notifyDataSetChanged();
//            }
//        });

        /**
         * 设置区间可用
         * 与上面的方法相同
         */
       // calendarView.setEnableRange(activeStart, activeEnd);
    }
}
