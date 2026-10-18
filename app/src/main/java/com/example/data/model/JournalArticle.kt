package com.example.data.model

data class JournalArticle(
    val id: String,
    val title: String,
    val journal: String,
    val impactFactor: Double,
    val authors: String,
    val publishDate: String,
    val therapeuticArea: String,
    val molecularTarget: String,
    val modality: String, // e.g. CRISPR/Cas, mRNA, BiTE, Small Molecule, Protac
    val abstractText: String,
    val clinicalStage: String, // e.g. Preclinical In-Vivo, Phase 1 IND, Discovery
    val keyTakeaways: List<String>,
    val doi: String,
    val isBreakthrough: Boolean = false
)
