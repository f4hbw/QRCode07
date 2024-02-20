@file:Suppress("DEPRECATION")

package com.f4hbw.qrcode07

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Lancement du scanner de QR Code dès le démarrage de l'activité.
        IntentIntegrator(this).initiateScan()
    }

    // Gestion du résultat du scan
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result.contents != null) {
            // Si un QR Code a été scanné, ouvrir l'URL dans un navigateur
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(result.contents))
            startActivity(browserIntent)
        } else {
            // Gestion de l'erreur ou du cas où aucun QR Code n'a été trouvé
            super.onBackPressed() // Ou afficher un message d'erreur selon vos préférences
        }
    }
}
