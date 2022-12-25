package com.example.wifiqrcodes

import android.graphics.Bitmap
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.core.graphics.get
import androidx.core.graphics.set

class QRCode {
    companion object {
        fun generateQRCode(text: String): Bitmap {
            val dimension = 400
            val qrCode = QRGEncoder(text, null, QRGContents.Type.TEXT, dimension)
            val bitmap = qrCode.bitmap
            for (i in 0 until dimension) {
                for (j in 0 until dimension) {
                    if (bitmap[i, j] == qrCode.colorBlack) {
                        bitmap[i, j] = qrCode.colorWhite
                    } else {
                        bitmap[i, j] = qrCode.colorBlack
                    }
                }
            }
            return bitmap
        }
    }
}