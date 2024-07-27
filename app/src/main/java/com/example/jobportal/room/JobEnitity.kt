package com.example.jobportal.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.jobportal.retrofit.model.ContactPreference
import com.example.jobportal.retrofit.model.ContentV3
import com.example.jobportal.retrofit.model.Creative
import com.example.jobportal.retrofit.model.FeeDetails
import com.example.jobportal.retrofit.model.JobTag
import com.example.jobportal.retrofit.model.Location
import com.example.jobportal.retrofit.model.PrimaryDetails
import com.example.jobportal.retrofit.model.Result

@Entity(tableName = "job_table")
@TypeConverters(Convertors::class)
data class JobEnitity(
    val amount: String?,
    val city_location: Int?,
    val company_name: String?,
    val content: String?,
    val created_on: String?,
    val creatives: String?,
    val experience: Int?,
    val fees_charged: Int?,
    @PrimaryKey
    val id: Int?,
    val job_category: String?,
    val job_hours: String?,
    val job_role: String?,
    val job_role_id: Int?,
    val job_type: Int?,
    val locality: Int,
    val locations: List<Location>?,
    val primary_details: PrimaryDetails?,
    val qualification: Int,
    val salary_max: Int?,
    val salary_min: Int?,
    val screening_retry: String?,
    val status: Int?,
    val title: String?,
    val type: Int?,
    val views: Int?,
    val whatsapp_no: String?
)

fun Result.toJobEntity():JobEnitity{
    return JobEnitity(
        amount=this.amount,
        city_location = this.city_location,
        company_name = this.company_name,
        content=this.content,
        created_on = this.created_on,
        creatives = this.creatives?.get(0)?.file,
        experience = this.experience,
        fees_charged = this.fees_charged,
        id=this.id,
        job_category = this.job_category,
        job_hours = this.job_hours,
        job_role = this.job_role,
        job_role_id=this.job_role_id,
        job_type = this.job_type,
        locality = this.locality,
        locations = this.locations,
        qualification = this.qualification,
        primary_details = this.primary_details,
        salary_max = this.salary_max,
        salary_min = this.salary_min,
        screening_retry = this.screening_retry,
        status = this.status,
        title = this.title,
        type = this.type,
        views = this.views,
        whatsapp_no = this.whatsapp_no
    )
}
fun JobEnitity.toResult():Result{
    return Result(
        amount=this.amount,
        city_location = this.city_location,
        company_name = this.company_name,
        content=this.content,
        created_on = this.created_on,
        creatives = listOf(Creative(this.creatives)),
        experience = this.experience,
        fees_charged = this.fees_charged,
        id=this.id,
        job_category = this.job_category,
        job_hours = this.job_hours,
        job_role = this.job_role,
        job_role_id=this.job_role_id,
        job_type = this.job_type,
        locality = this.locality,
        locations=this.locations,
        primary_details = this.primary_details,
        qualification = this.qualification,
        salary_max = this.salary_max,
        salary_min = this.salary_min,
        screening_retry = this.screening_retry,
        status = this.status,
        title = this.title,
        type = this.type,
        views = this.views,
        whatsapp_no = this.whatsapp_no
    )
}