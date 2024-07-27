package com.example.jobportal.room

import androidx.room.TypeConverter
import com.example.jobportal.retrofit.model.ContactPreference
import com.example.jobportal.retrofit.model.ContentV3
import com.example.jobportal.retrofit.model.Creative
import com.example.jobportal.retrofit.model.FeeDetails
import com.example.jobportal.retrofit.model.JobTag
import com.example.jobportal.retrofit.model.Location
import com.example.jobportal.retrofit.model.PrimaryDetails
import com.example.jobportal.retrofit.model.Result
import com.example.jobportal.retrofit.model.V3
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Objects

class Convertors {

    @TypeConverter
    fun fromPrimaryDetails(primaryDetails: PrimaryDetails):String{
        return Gson().toJson(primaryDetails)
    }
    @TypeConverter
    fun toPrimaryDetails(value:String):PrimaryDetails{
        val type=object:TypeToken<PrimaryDetails>() {}.type
        return Gson().fromJson(value,type)
    }

    @TypeConverter
    fun fromContactPreference(contactPreference: ContactPreference):String{
        return Gson().toJson(contactPreference)
    }
    @TypeConverter
    fun toContactPreference(value:String):ContactPreference{
        val type=object:TypeToken<ContactPreference>() {}.type
        return Gson().fromJson(value,type)
    }

    @TypeConverter
    fun fromContentV3(contentV3: ContentV3):String{
        return Gson().toJson(contentV3)
    }
    @TypeConverter
    fun toContentV3(value:String):ContentV3{
        val type=object:TypeToken<ContentV3>() {}.type
        return Gson().fromJson(value,type)
    }

    @TypeConverter
    fun fromStringListCreative(creative: List<Creative>):String{
        return Gson().toJson(creative)
    }
    @TypeConverter
    fun toCreative(value:String): List<Creative> {
        val type=object:TypeToken<List<Creative>>() {}.type
        return Gson().fromJson(value,type)
    }

    @TypeConverter
    fun fromFeeDetails(feeDetails: FeeDetails):String{
        return Gson().toJson(feeDetails)
    }
    @TypeConverter
    fun toFeeDetails(value:String): FeeDetails {
        val type=object:TypeToken<FeeDetails>() {}.type
        return Gson().fromJson(value,type)
    }

    @TypeConverter
    fun fromJobTag(jobTag: List<JobTag>):String{
        return Gson().toJson(jobTag)
    }
    @TypeConverter
    fun toJobTag(value:String): List<JobTag> {
        val type=object:TypeToken<List<JobTag>>() {}.type
        return Gson().fromJson(value,type)
    }

    @TypeConverter
    fun fromLocation(location: List<Location>):String{
        return Gson().toJson(location)
    }
    @TypeConverter
    fun toLocation(value:String): List<Location> {
        val type=object:TypeToken<List<Location>>() {}.type
        return Gson().fromJson(value,type)
    }


    @TypeConverter
    fun fromStringList(list: List<String>):String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun toListString(value:String): List<String> {
        val type=object:TypeToken<List<String>>() {}.type
        return Gson().fromJson(value,type)
    }



//    @TypeConverter
//    fun fromResult(result:Result):String{
//        return Gson().toJson(result)
//    }
//    @TypeConverter
//    fun toResult(value:String):Result {
//        val type=object:TypeToken<Result>() {}.type
//        return Gson().fromJson(value,type)
//    }
//    @TypeConverter
//    fun fromStringListResult(result:List<Result>):String{
//        return Gson().toJson(result)
//    }
//    @TypeConverter
//    fun toListResult(value:String):List<Result> {
//        val type=object:TypeToken<List<Result>>() {}.type
//        return Gson().fromJson(value,type)
//    }
//
//
//    @TypeConverter
//    fun fromListString(v: List<V3>):String{
//        return Gson().toJson(v)
//    }
//    @TypeConverter
//    fun toStringList(value:String): List<V3> {
//        val type=object:TypeToken<List<V3>>() {}.type
//        return Gson().fromJson(value,type)
//    }
//
//

}