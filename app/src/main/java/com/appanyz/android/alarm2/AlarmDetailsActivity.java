package com.appanyz.android.alarm2;

import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class AlarmDetailsActivity extends AppCompatActivity {

    private AlarmModel alarmDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alarm_details);

        alarmDetails = new AlarmModel();

        final LinearLayout ringToneContainer = (LinearLayout)findViewById(R.id.alarm_ringtone_container);
        ringToneContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                break;
            }
            case R.id.action_save: {
                updateModelFromLayout();

                AlarmDBHelper dbHelper = new AlarmDBHelper(this);
                if (alarmDetails.id < 0) {
                    dbHelper.createAlarm(alarmDetails);
                } else {
                    dbHelper.updateAlarm(alarmDetails);
                }

                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1: {
                    alarmDetails.alarmTone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

                    TextView txtToneSelection = (TextView) findViewById(R.id.alarm_label_tone_selection);
                    txtToneSelection.setText(RingtoneManager.getRingtone(this, alarmDetails.alarmTone).getTitle(this));
                    break;
                }
                default: {
                    break;
                }
            }
        }

    }

    private void updateModelFromLayout(){

        TimePicker timePicker = (TimePicker) findViewById(R.id.alarm_details_time_picker);
        alarmDetails.timeMinute = timePicker.getCurrentMinute();
        alarmDetails.timeHour = timePicker.getCurrentHour();

        CheckBox checkMonday = (CheckBox)findViewById(R.id.alarm_details_repeat_monday);
        alarmDetails.setRepeatingDay(AlarmModel.MONDAY, checkMonday.isChecked());

        CheckBox checkTuesday = (CheckBox) findViewById(R.id.alarm_details_repeat_tuesday);
        alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, checkTuesday.isChecked());

        CheckBox checkWednesday = (CheckBox) findViewById(R.id.alarm_details_repeat_wednesday);
        alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, checkWednesday.isChecked());

        CheckBox checkThursday = (CheckBox) findViewById(R.id.alarm_details_repeat_thursday);
        alarmDetails.setRepeatingDay(AlarmModel.THURSDAY, checkThursday.isChecked());

        CheckBox checkFriday = (CheckBox) findViewById(R.id.alarm_details_repeat_friday);
        alarmDetails.setRepeatingDay(AlarmModel.FRIDAY, checkFriday.isChecked());

        CheckBox checkSaturday = (CheckBox) findViewById(R.id.alarm_details_repeat_saturday);
        alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, checkSaturday.isChecked());

        CheckBox checkSunday = (CheckBox) findViewById(R.id.alarm_details_repeat_sunday);
        alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, checkSunday.isChecked());

        alarmDetails.isEnabled = true;
    }

}
