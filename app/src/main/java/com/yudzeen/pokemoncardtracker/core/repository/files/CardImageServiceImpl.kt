package com.yudzeen.pokemoncardtracker.core.repository.files

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.util.UUID
import javax.inject.Inject

class CardImageServiceImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
): CardImageService {

    companion object {
        private const val IMAGE_PREFIX = "IMG_"
        private const val IMAGE_SUFFIX = ".webp"
    }

    override fun saveCardImage(sourceUri: Uri, cardId: UUID): Uri {
        val destinationFile = getCardImageFile(cardId)
        val bitmap = ImageDecoder.createSource(context.contentResolver, sourceUri)
            .let { ImageDecoder.decodeBitmap(it) }

        destinationFile.outputStream().use { fos ->
            bitmap.compress(Bitmap.CompressFormat.WEBP_LOSSLESS, 100, fos)
            fos.flush()
        }

        bitmap.recycle()

        return destinationFile.toUri()
    }

    override fun deleteCardImage(cardId: UUID): Boolean {
        val imageFile = getCardImageFile(cardId)
        return imageFile.delete()
    }

    private fun getCardImageFile(cardId: UUID): File {
        val fileName = IMAGE_PREFIX + cardId.toString() + IMAGE_SUFFIX
        return File(context.filesDir, fileName)
    }
}