package com.android.sportnmedc.network

import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException


class EmptyStringTypeAdapter : TypeAdapter<String>() {
    @Throws(IOException::class)
    override fun write(jsonWriter: JsonWriter, String: String?) {
        if (String == null) {
            jsonWriter.nullValue()
            return
        }
        jsonWriter.value(String)
    }

    @Throws(IOException::class)
    override fun read(jsonReader: JsonReader): String? {
        if (jsonReader.peek() === JsonToken.NULL) {
            jsonReader.nextNull()
            return " "
        }

        try {
            val value = jsonReader.nextString()
            return if ("" == value) {
                " "
            } else {
                value
            }
        } catch (e: Exception) {
            throw JsonSyntaxException(e)
        }
    }
}