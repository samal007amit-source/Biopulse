package com.example.data.model

data class ClinicalTrial(
    val nctId: String,
    val title: String,
    val phase: String, // "Phase 1", "Phase 1/2", "Phase 2", "Phase 3", "Phase 4"
    val status: String, // "Recruiting", "Active, not recruiting", "Completed", "Enrolling by invitation"
    val sponsor: String,
    val condition: String,
    val intervention: String,
    val primaryEndpoint: String,
    val enrollmentCount: Int,
    val startDate: String,
    val completionDate: String,
    val therapeuticArea: String,
    val biomarkers: List<String>,
    val studyDesign: String,
    val detailedSummary: String
)
