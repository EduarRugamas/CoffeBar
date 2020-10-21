package com.example.coffeqr.Screens

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.coffeqr.R
import com.example.coffeqr.Utils.toast
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.karumi.dexter.Dexter
import com.karumi.dexter.DexterBuilder
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_escan_qr.*


class ActivityEscanQR : AppCompatActivity() {

    private val requestCodeCameraPermission = 100
    private lateinit var cameraSource: CameraSource
    private lateinit var detector: BarcodeDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escan_qr)

        Dexter.withContext(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener{
                override fun onPermissionGranted(permission: PermissionGrantedResponse?) {
                   toast("Permiso aceptado")

                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    toast("Permiso denegado")
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    AlertDialog.Builder(this@ActivityEscanQR)
                        .setTitle("Se necesitan permisos de camara")
                        .setMessage("Se necesitan permisos de camara para escanear codido QR")
                        .setPositiveButton("Ok") { _, _ -> p1?.continuePermissionRequest() }
                        .setNegativeButton("Cancel"){ _, _ -> p1?.cancelPermissionRequest() }
                        .create()
                        .show()
                }


            }).check()

            setUpControl()
            btn_navegacion.visibility = if (QR_result.text == "") View.VISIBLE else View.GONE
            btn_navegacion.setOnClickListener {startActivity(Intent(this,TabMenu::class.java))}



    }
    private fun requestRuntimePermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST)
    }

    private fun setUpControl(){
        detector = BarcodeDetector.Builder(this).build()
        cameraSource = CameraSource.Builder(this, detector).setRequestedPreviewSize(1920, 1080).setRequestedFps(25f).setAutoFocusEnabled(true).build()

        escaneo_qr.holder.addCallback(surfaceCallback)
        detector.setProcessor(processor)
    }
    private val surfaceCallback = object : SurfaceHolder.Callback {
        override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
            if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                try {
                    cameraSource.start(surfaceHolder)
                }catch (exception: Exception){
                    Toast.makeText(applicationContext, "Se necesitan permisos de camara", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }

        override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

        }

        override fun surfaceDestroyed(p0: SurfaceHolder) {
            cameraSource.stop()
        }

    }
    private val processor = object : Detector.Processor<Barcode>{
        override fun release() {}

        override fun receiveDetections(detections: Detector.Detections<Barcode>?) {
            if (detections != null){
                val qrCodes: SparseArray<Barcode> = detections.detectedItems
                val code = qrCodes.valueAt(0)
                QR_result.text = code.displayValue
                Log.d("QR", "$code")
            }

        }


    }

    companion object {
        const val CAMERA_REQUEST = 0
    }
}


