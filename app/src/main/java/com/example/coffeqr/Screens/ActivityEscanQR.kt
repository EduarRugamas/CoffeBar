package com.example.coffeqr.Screens

import android.app.Activity
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
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_escan_qr.*


class ActivityEscanQR : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escan_qr)


        btn_escaneo.setOnClickListener {
            val scanner = IntentIntegrator(this)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result !=  null){
                if(result.contents == null) {
                    Toast.makeText(this, "No se pudo Escanear", Toast.LENGTH_LONG).show()
                } else {
                    btn_navegacion.visibility = View.VISIBLE
                    //Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                    QR_result.text = result.contents
                    btn_navegacion.setOnClickListener {
                        startActivity(Intent(this, TabMenu::class.java))
                    }
                }

            }else{
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}


