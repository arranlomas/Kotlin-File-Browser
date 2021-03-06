package com.schiwfty.kotlinfilebrowser

import android.content.Context
import java.io.File


/**
 * Created by arran on 25/06/2017.
 */
fun Long.formatBytesAsSize(): String {
    if (this > 0.1 * 1024.0 * 1024.0 * 1024.0) {
        val f = this.toFloat() / 1024f / 1024f / 1024f
        return String.format("%1$.1f GB", f)
    } else if (this > 0.1 * 1024.0 * 1024.0) {
        val f = this.toFloat() / 1024f / 1024f
        return String.format("%1$.1f MB", f)
    } else {
        val f = this / 1024f
        return String.format("%1$.1f kb", f)
    }
}

fun File.getMimeType(): String {
    return android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension) ?: "*/*"
}

fun File.search(searchTerm: String): List<File> {
    val results = mutableListOf<File>()
    walkTopDown().asIterable().forEach {
        if (it.name.contains(searchTerm)) results.add(it)
    }
    return results
}

fun File.getCleanedName(context: Context): String {
    val partToIgnore = "Android/data/${context.packageName}/files"
    if (this.absolutePath.endsWith(partToIgnore)) return absolutePath.removeSuffix(partToIgnore)
    else return this.absolutePath
}