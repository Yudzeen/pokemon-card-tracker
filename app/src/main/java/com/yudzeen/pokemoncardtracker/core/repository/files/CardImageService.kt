package com.yudzeen.pokemoncardtracker.core.repository.files

import android.net.Uri
import java.util.UUID

interface CardImageService {
    fun saveCardImage(sourceUri: Uri, cardId: UUID): Uri
}