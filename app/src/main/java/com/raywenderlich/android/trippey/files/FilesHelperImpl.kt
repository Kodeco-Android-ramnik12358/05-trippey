package com.raywenderlich.android.trippey.files

import java.io.File
import java.io.FileOutputStream

class FilesHelperImpl(private val directory: File) : FilesHelper {
    override fun saveData(fileName: String, data: String) {
        val file = buildFile(fileName)
        val fileOutputStream = buildOutputStream(file)

        try {
            fileOutputStream.use {
                it.write(data.toByteArray())
            }
        } catch (error: Throwable) {
            error.printStackTrace()
        }
    }

    override fun getData(): List<File> {
        return directory.listFiles()?.toList() ?: emptyList()
    }

    override fun deleteData(fileName: String) {
        val file = buildFile(fileName)

        if(!file.exists()) {
            return
        }

        file.delete()
    }

    private fun buildFile(fileName: String): File {
        return File(directory, fileName)
    }

    private fun buildOutputStream(file: File): FileOutputStream {
        return FileOutputStream(file)
    }
}