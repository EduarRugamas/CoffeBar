package com.example.coffeqr.Screens

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.coffeqr.R
import com.example.coffeqr.Utils.toast
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_escan_qr.*


class ActivityEscanQR : AppCompatActivity() {

    private val requestCodeCameraPermission = 1001
    private lateinit var cameraSource: CameraSource
    private lateinit var detector: BarcodeDetector
    private lateinit var txt_result: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escan_qr)

         txt_result = findViewById(R.id.QR_result)

        if (ContextCompat.checkSelfPermission(this@ActivityEscanQR, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            askForCameraPermission()
        } else {
            setupControl()
        }

        if (txt_result.text == ""){
            btn_navegacion.visibility = View.GONE
            toast("Codigo QR no escaneado")
        }


    }

    private fun setupControl() {
        detector = BarcodeDetector.Builder(this@ActivityEscanQR).build()
        cameraSource = CameraSource.Builder(applicationContext, detector).setAutoFocusEnabled(true).build()


        escaneo_qr.holder.addCallback(surfaceCallBack)


        detector.setProcessor(processor)
    }

    private fun askForCameraPermission() {
        ActivityCompat.requestPermissions(this@ActivityEscanQR, arrayOf(android.Manifest.permission.CAMERA), requestCodeCameraPermission)


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCodeCameraPermission && grantResults.isNotEmpty()){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                setupControl()
            }else{
                Toast.makeText(applicationContext, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private val surfaceCallBack = object : SurfaceHolder.Callback{
        override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
            try {
                if (ActivityCompat.checkSelfPermission(
                                this@ActivityEscanQR,
                                android.Manifest.permission.CAMERA
                        ) != PackageManager.PERMISSION_GRANTED
                ) {

                    return
                }
                cameraSource.start(surfaceHolder)
            }catch (exception: Exception){
                Toast.makeText(applicationContext, "something wnet wrong", Toast.LENGTH_SHORT).show()

            }
        }

        override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

        }

        override fun surfaceDestroyed(p0: SurfaceHolder) {
            cameraSource.stop()
        }
    }
    private val processor = object : Detector.Processor<Barcode>{
        override fun release() {

        }

        override fun receiveDetections(detections: Detector.Detections<Barcode>?) {

            if (detections != null) {
                val qrCodes: SparseArray<Barcode> = detections.detectedItems
                val code = qrCodes.valueAt(0)
                txt_result.text = code.displayValue
            }else{
                btn_navegacion.visibility = View.VISIBLE
                btn_navegacion.setOnClickListener {
                    startActivity(Intent(this@ActivityEscanQR, TabMenu::class.java))
                }
            }

        }
    }


}


