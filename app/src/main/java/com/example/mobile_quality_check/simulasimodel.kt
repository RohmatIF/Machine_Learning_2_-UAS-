package com.example.mobile_quality_check

import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class simulasimodel : AppCompatActivity() {
    private lateinit var interpreter: Interpreter
    private val mModelPath = "Mobile_Price.tflite"

    private lateinit var resultText: TextView
    private lateinit var battery_power: EditText
    private lateinit var bluetooth: EditText
    private lateinit var clock_speed: EditText
    private lateinit var dual_sim: EditText
    private lateinit var fc: EditText
    private lateinit var four_g: EditText
    private lateinit var int_memory: EditText
    private lateinit var m_dep: EditText
    private lateinit var mobile_wt: EditText
    private lateinit var n_cores: EditText

    private lateinit var pc: EditText
    private lateinit var px_height: EditText
    private lateinit var px_width: EditText
    private lateinit var ram: EditText
    private lateinit var sc_h: EditText
    private lateinit var sc_w: EditText
    private lateinit var talk_time: EditText
    private lateinit var three_g: EditText
    private lateinit var touch_screen: EditText
    private lateinit var wifi: EditText

    private lateinit var checkButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulasimodel)

        resultText = findViewById(R.id.txtResult)
        battery_power = findViewById(R.id.batterypower)
        bluetooth   = findViewById(R.id.bluetooth)

        clock_speed = findViewById(R.id.clock_speed)
        dual_sim = findViewById(R.id.dual_sim)
        fc = findViewById(R.id.fc)

        four_g = findViewById(R.id.four_g)
        int_memory = findViewById(R.id.int_memory)
        m_dep = findViewById(R.id.m_dep)
        mobile_wt = findViewById(R.id.mobile_wt)
        n_cores = findViewById(R.id.n_cores)

        pc = findViewById(R.id.pc)
        px_height = findViewById(R.id.px_height)
        px_width = findViewById(R.id.px_width)
        ram = findViewById(R.id.ram)
        sc_h = findViewById(R.id.sc_h)

        sc_w = findViewById(R.id.sc_w)
        talk_time = findViewById(R.id.talk_time)
        three_g = findViewById(R.id.three_g)
        touch_screen = findViewById(R.id.touch_screen)
        wifi = findViewById(R.id.wifi)

        checkButton = findViewById(R.id.btnpredict)



        checkButton.setOnClickListener {
            var result = doInference(
                battery_power.text.toString(),
                bluetooth.text.toString(),
                clock_speed.text.toString(),
                dual_sim.text.toString(),
                fc.text.toString(),
                four_g.text.toString(),
                int_memory.text.toString(),
                m_dep.text.toString(),
                mobile_wt.text.toString(),
                n_cores.text.toString(),
                pc.text.toString(),
                px_height.text.toString(),
                px_width.text.toString(),
                ram.text.toString(),
                sc_h.text.toString(),
                sc_w.text.toString(),
                talk_time.text.toString(),
                three_g.text.toString(),
                touch_screen.text.toString(),
                wifi.text.toString()
            )

            runOnUiThread {
                if (result.toInt() == 0) {
                    resultText.text = "Low Price"
                } else if (result.toInt() == 1 ) {
                    resultText.text = "Medium Price"
                }
                else if (result.toInt() ==2 ) {
                    resultText.text = "High Price"
                }
                else  {
                    resultText.text = "Very High Price"
                }
            }

        }
        initInterpreter()

    }

    private fun initInterpreter() {
        val options = Interpreter.Options()
        options.setNumThreads(4)
        options.setUseNNAPI(true)
        interpreter = Interpreter(loadModelFile(assets, mModelPath), options)
    }

    private fun doInference(
        input1: String,
        input2: String,
        input3: String,
        input4: String,
        input5: String,
        input6: String,
        input7: String,
        input8: String,
        input9: String,
        input10: String,
        input11: String,
        input12: String,
        input13: String,
        input14: String,
        input15: String,
        input16: String,
        input17: String,
        input18: String,
        input19: String,
        input20: String


    ): Int {
        val inputVal = FloatArray(20)
        var std = FloatArray(20)
        std[0]=439.418206.toFloat()
        std[1]=0.500100.toFloat()
        std[2]=0.816004.toFloat()
        std[3]= 0.500035.toFloat()
        std[4]=4.341444.toFloat()
        std[5]= 0.499662.toFloat()
        std[6]=18.145715.toFloat()
        std[7]= 0.288416.toFloat()
        std[8]=35.399655.toFloat()
        std[9]= 2.287837.toFloat()
        std[10]=6.064315.toFloat()
        std[11]=443.780811.toFloat()
        std[12]= 432.199447.toFloat()
        std[13]= 1084.732044.toFloat()
        std[14]= 4.213245.toFloat()
        std[15]= 4.356398.toFloat()
        std[16]= 5.463955.toFloat()
        std[17]= 0.426273.toFloat()
        std[18]=0.500116.toFloat()
        std[19]=0.500076.toFloat()

        inputVal[0] = ((input1.toFloat()- 1238.51850 ) /   439.418206).toFloat()

        inputVal[1] = ((input2.toFloat() -  0.49500) / 0.500100.toFloat()).toFloat()

        inputVal[2] = ((input3.toFloat()- 1.52225)/ 0.816004.toFloat()).toFloat()

        inputVal[3] = ((input4.toFloat()- 0.50950)/  0.500035.toFloat()).toFloat()

        inputVal[4] = ((input5.toFloat()-  4.30950)/ std[4]).toFloat()

        inputVal[5] = ((input6.toFloat()-  0.52150)/std[5]).toFloat()

        inputVal[6] = ((input7.toFloat()- 32.04650)/std[6]).toFloat()

        inputVal[7] = ((input8.toFloat()- 0.50175)/std[7]).toFloat()

        inputVal[8] = ((input9.toFloat()-  140.24900 ) /  std[8]).toFloat()

        inputVal[9] = ((input10.toFloat() - 4.52050) /std[9]   ).toFloat()

        inputVal[10] = ((input11.toFloat()- 9.91650 ) /    std[10]).toFloat()

        inputVal[11] = ((input12.toFloat() -  645.10800) / std[11]).toFloat()

        inputVal[12] = ((input13.toFloat()-  1251.51550)/ std[12]).toFloat()

        inputVal[13] = ((input14.toFloat()- 2124.21300)/ std[13]).toFloat()

        inputVal[14] = ((input15.toFloat()- 12.30650)/ std[14]).toFloat()

        inputVal[15] = ((input16.toFloat()-      5.76700)/std[15]).toFloat()

        inputVal[16] = ((input17.toFloat()-  11.01100)/std[16]).toFloat()

        inputVal[17] = ((input18.toFloat()-    0.76150)/std[17]).toFloat()

        inputVal[18] = ((input19.toFloat()- 0.50300 ) /  std[18]).toFloat()

        inputVal[19] = ((input20.toFloat() -  0.50700) /std[19]   ).toFloat()

        val output = Array(1) {
            FloatArray(4)
        }
        Log.e("result", (output[0].toList() + "").toString())
        interpreter.run(inputVal, output)
        return output[0].indexOfFirst { it == output[0].maxOrNull() }
    }

    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }
}
