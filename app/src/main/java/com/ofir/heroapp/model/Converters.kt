package com.ofir.heroapp.model

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromArray(heroAttributes: ArrayList<String>):String{
        var heroAttributesAsString = ""
        for(heroAttribute in heroAttributes) heroAttributesAsString += ("$heroAttribute,");
        return heroAttributesAsString
    }

    @TypeConverter
    fun toArray(heroAttributesAsString: String):ArrayList<String>{
        val heroAttributes: ArrayList<String> = ArrayList()
        for(s in heroAttributesAsString.split(","))
            heroAttributes.add(s)
        return heroAttributes
    }

}