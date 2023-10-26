package com.recepgemalmaz.pazarama_bootcamp_ders_4_2_sensorsample


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.recepgemalmaz.pazarama_bootcamp_ders_4_2_sensorsample.ui.theme.Pazarama_Bootcamp_Ders_4_2_SensorSampleTheme

class MainActivity : ComponentActivity() {

    private lateinit var mng:SensorManager

    inner class SensorObserver : SensorEventListener
    {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event!!.sensor.type == Sensor.TYPE_LIGHT ) {
                var i = event!!.values[0]
            }

            if (event!!.sensor.type == Sensor.TYPE_MAGNETIC_FIELD ) {
                yat = event!!.values[0]
                dus = event!!.values[1]
                der = event!!.values[2]
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }
    }

    private lateinit var observer:SensorObserver
    private lateinit var isikSensoru:Sensor
    private lateinit var emSensoru:Sensor
    private var light:Float? = null
    private var yat:Float? = null
    private var dus:Float? = null
    private var der:Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mng = this.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        isikSensoru = mng.getDefaultSensor(Sensor.TYPE_LIGHT)!!

        emSensoru = mng.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)!!

        observer = SensorObserver()

        /*
        var lst = mng.getSensorList(Sensor.TYPE_ALL)

        for (s in lst)
        {
            Log.e("x",  s.name)
        }
        */

        setContent {

            var val1 = remember { mutableStateOf(light)  }

            var yatay = remember { mutableStateOf(yat)  }
            var dusey = remember { mutableStateOf(dus)  }
            var derinlik = remember { mutableStateOf(der)  }

            Pazarama_Bootcamp_Ders_4_2_SensorSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main(isik = val1.value, x = yatay.value, y = dusey.value, z = derinlik.value)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // mng.registerListener(observer, isikSensoru, SensorManager.SENSOR_STATUS_ACCURACY_LOW,  SensorManager.SENSOR_DELAY_GAME)
        mng.registerListener(observer, emSensoru, SensorManager.SENSOR_STATUS_ACCURACY_LOW,  SensorManager.SENSOR_DELAY_GAME)

    }

    override fun onStop() {
        super.onStop()
        mng.unregisterListener(observer)
    }
}

@Composable
fun Main(isik:Float?, x:Float?, y:Float?, z:Float?) {
    Column()
    {
        Text(text = "${isik} lux")
        Spacer(Modifier.fillMaxWidth().height(10.dp).background(Color.Blue))
        Text(text = "${x} uT")
        Spacer(Modifier.fillMaxWidth().height(10.dp).background(Color.Blue))
        Text(text = "${y} uT")
        Spacer(Modifier.fillMaxWidth().height(10.dp).background(Color.Blue))
        Text(text = "${z} uT")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Pazarama_Bootcamp_Ders_4_2_SensorSampleTheme {

    }
}