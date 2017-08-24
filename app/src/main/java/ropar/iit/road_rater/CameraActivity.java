/*
 * Copyright 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ropar.iit.road_rater;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

public class CameraActivity extends Activity implements SensorEventListener{



    @Override
    public void onBackPressed() {
        if(!Camera2VideoFragment.mIsRecordingVideo)
        super.onBackPressed();
    }

    private String accelerometer="video_road";
    private String name_current;
    private SensorManager sensorManager;
    private DatabaseHelper db;
    private long Time2=-1;
    Camera2VideoFragment camera2VideoFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        db=new DatabaseHelper(this);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2VideoFragment.newInstance())
                    .commit();
        }
        camera2VideoFragment=new Camera2VideoFragment();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Camera2VideoFragment camera2VideoFragment=new Camera2VideoFragment();
        Log.d("RETURNING","ON SENSOR CHANGED CALLED"+Camera2VideoFragment.mIsRecordingVideo);
        if ((event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && Camera2VideoFragment.mIsRecordingVideo) ) {
            getAccelerometer(event);
        }

    }
    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        double x = values[0];
        double y = values[1];
        double z = values[2];


       // Camera2VideoFragment camera2VideoFragment=new Camera2VideoFragment();
        long Time= SystemClock.elapsedRealtime()-Camera2VideoFragment.getstarttime();
        if(Time2!=-1)
        {
            if((Time-Time2)<200)
            {
                Log.d("RETURNING","WORKING FINE "+Time+" "+Time2);
                return;
            }
        }
        Log.d("FOLLOWING","NOT WORKING "+Time+" "+Time2);
        Time2=Time;
     //   double accelationSquareRoot = (x * x + y * y + z * z)
       //         / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        name_current=Camera2VideoFragment.final_video_name+"1";
        //Database5Helper db=new DatabaseHelper(this);
         Log.d("ACCELEROMETER",name_current);
     //   Toast.makeText(this,"Accelerometer"+name_current,Toast.LENGTH_LONG).show();
        db.AddDesiredAccelerometerTable(name_current);
        db.insertaccelerometerData(name_current,x,y,z,Time);
      // if(accelationSquareRoot>=1)
      // {
        ;
           //   Toast.makeText(this,"device was given acceleration of"+accelationSquareRoot+"value along x"+x+"value along y"+y+"value along z"+z,Toast.LENGTH_LONG).show();
       //}
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
