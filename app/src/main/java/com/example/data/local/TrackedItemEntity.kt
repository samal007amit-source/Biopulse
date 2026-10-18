package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracked_items")
data class TrackedItemEntity(
    @PrimaryKey
    val id: String, // Article ID or NCT ID
    val type: String, // "ARTICLE" or "TRIAL"
    val title: String,
    val subtitle: String, // Journal Name or Trial Sponsor
    val secondaryInfo: String, // Impact factor or Phase/Status
    val therapeuticArea: String,
    val molecularTarget: String,
    val userNotes: String = "",
    val userTags: String = "", // Comma-separated tags
    val priority: String = "Normal", // "High", "Normal", "Low"
    val savedAtTimestamp: Long = System.currentTimeMillis()
)
