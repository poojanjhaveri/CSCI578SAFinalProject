package edu.usc.trojanow.activities;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.content.Context;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.content.Intent;


public class SensorActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mTemperature;
    private Sensor mLight;
    private Sensor mPressure;
    private Sensor mHumidity;
    private String mInfo;
    private float  fTemperature = 0;
    private float  fLight = 0;
    private float  fPressure = 0;
    private float  fHumidity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        final CheckBox tempCB = (CheckBox) findViewById(R.id.checkBoxSensor1);
        if (mTemperature != null) {

            tempCB.setEnabled(true);

            tempCB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (tempCB.isChecked()) {
                        System.out.println("tempCB: isChecked");
                        startSensor(mTemperature);
                    } else {
                        System.out.println("tempCB: isNotChecked");
                        stopSensor(mTemperature);
                    }

                }

            });
        } else {
            tempCB.setChecked(false);
            tempCB.setEnabled(false);
        }

        final CheckBox lightCB = (CheckBox) findViewById(R.id.checkBoxSensor2);
        if (mLight != null) {

            lightCB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lightCB.isChecked()) {
                        startSensor(mLight);
                    } else {
                        stopSensor(mLight);
                    }
                }

            });
        } else {
            lightCB.setChecked(false);
            lightCB.setEnabled(false);
        }

        final CheckBox pressureCB = (CheckBox) findViewById(R.id.checkBoxSensor3);
        if (mPressure != null) {

            pressureCB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pressureCB.isChecked()) {
                        startSensor(mPressure);
                    } else {
                        stopSensor(mPressure);
                    }
                }

            });
        } else {
            pressureCB.setChecked(false);
            pressureCB.setEnabled(false);
        }

        final CheckBox humidityCB = (CheckBox) findViewById(R.id.checkBoxSensor4);
        if (mHumidity != null) {

            humidityCB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (humidityCB.isChecked()) {
                        startSensor(mHumidity);
                    } else {
                        stopSensor(mHumidity);
                    }
                }

            });
        } else {
            humidityCB.setChecked(false);
            humidityCB.setEnabled(false);
        }

        Button cancelButton = (Button) findViewById(R.id.sensorcancelbutton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button sensorButton = (Button) findViewById(R.id.sensorinfobutton);
        sensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInfo = "";
                if (fTemperature != 0) {
                    mInfo = mInfo.concat(" The temperature is " + fTemperature + " degrees Celsius.");
                }
                if (fLight != 0) {
                    mInfo = mInfo.concat(" The light is " + fLight + " SI lux.");
                }
                if (fPressure != 0) {
                    mInfo = mInfo.concat(" The pressure is " + fPressure + " hPa.");
                }
                if (fHumidity != 0) {
                    mInfo = mInfo.concat(" The pressure is " + fHumidity + "%.");
                }
                System.out.println ("info = " + mInfo);

                Intent intent = new Intent();
                intent.putExtra("SensorInfo", mInfo);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //float millibars_of_pressure = event.values[0];
        int type = event.sensor.getType();
        if (type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
//            System.out.println("SensorActivity: Temperature");
            fTemperature = event.values[0];
        }

        if (type == Sensor.TYPE_LIGHT) {
//            System.out.println("SensorActivity: Light");
            fLight = event.values[0];
        }

        if (type == Sensor.TYPE_PRESSURE) {
//            System.out.println("SensorActivity: Pressure");
            fPressure = event.values[0];
        }

        if (type == Sensor.TYPE_RELATIVE_HUMIDITY) {
//            System.out.println("SensorActivity: Humidity");
            fHumidity = event.values[0];
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        /*
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);

        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mHumidity, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
        */

    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mSensorManager.unregisterListener(this, mTemperature);
        mSensorManager.unregisterListener(this, mLight);
        mSensorManager.unregisterListener(this, mPressure);
        mSensorManager.unregisterListener(this, mHumidity);

    }

    protected void startSensor (Sensor mSensor) {
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void stopSensor (Sensor mSensor) {
        mSensorManager.unregisterListener(this, mSensor);
    }
}
