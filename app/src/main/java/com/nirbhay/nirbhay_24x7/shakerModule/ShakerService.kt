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

class ShakerService : Service(), SensorEventListener {
    private var isItFirstTime = true
    private var currentX: Float = 0f
    private var currentY: Float = 0f
    private var currentZ: Float = 0f
    private var lastX: Float = 0f
    private var lastY: Float = 0f
    private var lastZ: Float = 0f
    private var shakeThreshold: Float = 5f
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
            currentX = event.values[0]
            currentY = event.values[1]
            currentZ = event.values[2]
            if (!isItFirstTime) {
                val diffX = abs(lastX - currentX)
                val diffY = abs(lastY - currentY)
                val diffZ = abs(lastZ - currentZ)

                if ((diffX > shakeThreshold && diffY > shakeThreshold)
                    || (diffX > shakeThreshold && diffZ > shakeThreshold)
                    || (diffY > shakeThreshold && diffZ > shakeThreshold)
                )
                    this.showMsg("Shake detected")
            }
            lastX = currentX
            lastY = currentY
            lastZ = currentZ
            isItFirstTime = false
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}