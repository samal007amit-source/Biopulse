package com.example.data.repository

import com.example.data.local.TrackedItemDao
import com.example.data.local.TrackedItemEntity
import com.example.data.model.BioscienceAnalysisResult
import com.example.data.model.BioscienceSkillType
import com.example.data.model.ClinicalTrial
import com.example.data.model.JournalArticle
import com.example.data.remote.GeminiService
import com.example.data.sample.BioscienceDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BioscienceRepository(
    private val trackedItemDao: TrackedItemDao,
    private val geminiService: GeminiService = GeminiService()
) {
    fun getArticles(): List<JournalArticle> = BioscienceDataStore.journalArticles

    fun getClinicalTrials(): List<ClinicalTrial> = BioscienceDataStore.clinicalTrials

    fun getTrackedItems(): Flow<List<TrackedItemEntity>> = trackedItemDao.getAllTrackedItems()

    fun isItemTracked(id: String): Flow<Boolean> = trackedItemDao.isItemTracked(id)

    suspend fun trackArticle(article: JournalArticle, notes: String = "", priority: String = "Normal") {
        val entity = TrackedItemEntity(
            id = article.id,
            type = "ARTICLE",
            title = article.title,
            subtitle = article.journal,
            secondaryInfo = "IF ${article.impactFactor} • ${article.clinicalStage}",
            therapeuticArea = article.therapeuticArea,
            molecularTarget = article.molecularTarget,
            userNotes = notes,
            userTags = "${article.modality}, ${article.therapeuticArea}",
            priority = priority
        )
        trackedItemDao.insertOrUpdate(entity)
    }

    suspend fun trackTrial(trial: ClinicalTrial, notes: String = "", priority: String = "Normal") {
        val entity = TrackedItemEntity(
            id = trial.nctId,
            type = "TRIAL",
            title = trial.title,
            subtitle = trial.sponsor,
            secondaryInfo = "${trial.phase} • ${trial.status}",
            therapeuticArea = trial.therapeuticArea,
            molecularTarget = trial.biomarkers.firstOrNull() ?: trial.condition,
            userNotes = notes,
            userTags = "${trial.phase}, ${trial.therapeuticArea}",
            priority = priority
        )
        trackedItemDao.insertOrUpdate(entity)
    }

    suspend fun updateTrackedItemNotes(id: String, notes: String, priority: String) {
        val existing = trackedItemDao.getTrackedItemById(id)
        if (existing != null) {
            trackedItemDao.update(existing.copy(userNotes = notes, priority = priority))
        }
    }

    suspend fun untrackItem(id: String) {
        trackedItemDao.deleteById(id)
    }

    suspend fun analyzeArticle(
        article: JournalArticle,
        skillType: BioscienceSkillType,
        userQuery: String? = null
    ): BioscienceAnalysisResult {
        val context = buildString {
            appendLine("Title: ${article.title}")
            appendLine("Journal: ${article.journal} (Impact Factor: ${article.impactFactor})")
            appendLine("Authors: ${article.authors}")
            appendLine("Date: ${article.publishDate}")
            appendLine("Therapeutic Area: ${article.therapeuticArea}")
            appendLine("Molecular Target: ${article.molecularTarget}")
            appendLine("Modality: ${article.modality}")
            appendLine("Clinical Stage: ${article.clinicalStage}")
            appendLine("Abstract: ${article.abstractText}")
            appendLine("Key Highlights: ${article.keyTakeaways.joinToString("; ")}")
        }

        return geminiService.runBioscienceAnalysis(
            itemTitle = article.title,
            itemContext = context,
            skillType = skillType,
            userQuery = userQuery
        )
    }

    suspend fun analyzeClinicalTrial(
        trial: ClinicalTrial,
        skillType: BioscienceSkillType,
        userQuery: String? = null
    ): BioscienceAnalysisResult {
        val context = buildString {
            appendLine("NCT ID: ${trial.nctId}")
            appendLine("Official Title: ${trial.title}")
            appendLine("Phase: ${trial.phase}")
            appendLine("Recruitment Status: ${trial.status}")
            appendLine("Sponsor: ${trial.sponsor}")
            appendLine("Indication / Condition: ${trial.condition}")
            appendLine("Intervention: ${trial.intervention}")
            appendLine("Primary Endpoint: ${trial.primaryEndpoint}")
            appendLine("Target Biomarkers: ${trial.biomarkers.joinToString(", ")}")
            appendLine("Study Design: ${trial.studyDesign}")
            appendLine("Enrollment: ${trial.enrollmentCount} subjects")
            appendLine("Summary: ${trial.detailedSummary}")
        }

        return geminiService.runBioscienceAnalysis(
            itemTitle = "${trial.nctId}: ${trial.condition}",
            itemContext = context,
            skillType = skillType,
            userQuery = userQuery
        )
    }

    suspend fun runCustomBioscienceQuery(
        promptQuery: String,
        skillType: BioscienceSkillType
    ): BioscienceAnalysisResult {
        return geminiService.runBioscienceAnalysis(
            itemTitle = "Bioscience Investigation Query",
            itemContext = "General bioscience literature, biochemical databases, and clinical registries.",
            skillType = skillType,
            userQuery = promptQuery
        )
    }
}
