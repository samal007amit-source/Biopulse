package com.example.data.model

data class GroundingSource(
    val title: String,
    val url: String
)

data class BioscienceAnalysisResult(
    val title: String,
    val skillType: BioscienceSkillType,
    val timestamp: Long = System.currentTimeMillis(),
    val rawAnalysisText: String,
    val executiveSummary: String = "",
    val keyScientificTakeaways: List<String> = emptyList(),
    val searchQueries: List<String> = emptyList(),
    val searchSources: List<GroundingSource> = emptyList(),
    val isSearchGrounded: Boolean = true,
    val confidenceRating: String = "High (Gemini 3.8 Flash + Google Search)",
    val isAiGenerated: Boolean = true
)
