package com.nirbhay.nirbhay_24x7.shakerModule

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import com.nirbhay.nirbhay_24x7.utilities.showMsg
import kotlin.math.abs
import kotlin.math.sqrt

class ShakerService : Service(), SensorEventListener {

    private var acceleration: Float = 0f
    private var shakeThreshold: Float = 15f
    private val timeDiff: Long = 60000
    private var lastShakeTime: Long = 0
    private var curShakeTime: Long = 0
    private var shakeCount: Int = 0
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        registerSensor()
    }

    private fun registerSensor() {
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometerSensor: Sensor
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            sensorManager.registerListener(
                this,
                accelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            curShakeTime = System.currentTimeMillis()
            if (abs(lastShakeTime - curShakeTime) > timeDiff) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                acceleration = sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH

                if (acceleration > shakeThreshold) {
                    lastShakeTime = curShakeTime
                    shakeCount++
                    if(shakeCount == 3) {
                        this.showMsg("Shake Detected")
                        shakeCount = 0
                    }
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}