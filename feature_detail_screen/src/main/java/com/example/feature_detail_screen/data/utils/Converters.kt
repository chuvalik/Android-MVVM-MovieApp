package com.example.feature_detail_screen.data.utils

import android.util.Log
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.feature_core.data.JsonParser
import com.example.feature_detail_screen.data.local.entity.ActorEntity
import com.google.gson.reflect.TypeToken


@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromActorsJson(json: String): List<ActorEntity> {
        Log.d("JsonParserTest", "from actor json")
        return jsonParser.fromJson<ArrayList<ActorEntity>>(
            json,
            object : TypeToken<ArrayList<ActorEntity>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toActorsJson(actors: List<ActorEntity>): String {
        Log.d("JsonParserTest", "to actor json")
        return jsonParser.toJson(
            actors,
            object : TypeToken<ArrayList<ActorEntity>>() {}.type
        ) ?: "[]"
    }
}