package com.example.ui.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.BioDatabase
import com.example.data.local.TrackedItemEntity
import com.example.data.model.BioscienceAnalysisResult
import com.example.data.model.BioscienceSkillType
import com.example.data.model.ClinicalTrial
import com.example.data.model.JournalArticle
import com.example.data.model.VeoAspectRatio
import com.example.data.model.VeoUiState
import com.example.data.remote.VeoService
import com.example.data.repository.BioscienceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface AiAnalysisState {
    object Idle : AiAnalysisState
    object Loading : AiAnalysisState
    data class Success(val result: BioscienceAnalysisResult) : AiAnalysisState
    data class Error(val message: String) : AiAnalysisState
}

enum class BioNavigationTab(val label: String) {
    JOURNALS("Journals"),
    TRIALS("Clinical Trials"),
    AI_LAB("Bioscience AI Lab"),
    BIO_VIDEO("Veo Video"),
    WATCHLIST("Watchlist"),
    TRENDS("Intelligence")
}

class BioscienceViewModel(application: Application) : AndroidViewModel(application) {

    private val database = BioDatabase.getDatabase(application)
    private val repository = BioscienceRepository(database.trackedItemDao())
    private val veoService = VeoService(application)

    val trackedItems: StateFlow<List<TrackedItemEntity>> = repository.getTrackedItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Creator Info
    val creatorName = "Amit Samal"
    val creatorEmail = "samal007amit@gmail.com"
    val creatorRole = "Lead Bioscience App Architect & Engineer"

    private val _showCreatorProfile = MutableStateFlow(false)
    val showCreatorProfile: StateFlow<Boolean> = _showCreatorProfile.asStateFlow()

    // Navigation Tab
    private val _currentTab = MutableStateFlow(BioNavigationTab.JOURNALS)
    val currentTab: StateFlow<BioNavigationTab> = _currentTab.asStateFlow()

    // Journal Search & Filter
    private val _journalSearchQuery = MutableStateFlow("")
    val journalSearchQuery: StateFlow<String> = _journalSearchQuery.asStateFlow()

    private val _selectedJournalArea = MutableStateFlow("All Areas")
    val selectedJournalArea: StateFlow<String> = _selectedJournalArea.asStateFlow()

    // Clinical Trials Search & Filter
    private val _trialSearchQuery = MutableStateFlow("")
    val trialSearchQuery: StateFlow<String> = _trialSearchQuery.asStateFlow()

    private val _selectedTrialPhase = MutableStateFlow("All Phases")
    val selectedTrialPhase: StateFlow<String> = _selectedTrialPhase.asStateFlow()

    private val _selectedTrialStatus = MutableStateFlow("All Statuses")
    val selectedTrialStatus: StateFlow<String> = _selectedTrialStatus.asStateFlow()

    // AI Analysis State
    private val _aiState = MutableStateFlow<AiAnalysisState>(AiAnalysisState.Idle)
    val aiState: StateFlow<AiAnalysisState> = _aiState.asStateFlow()

    // Active AI Lab State
    private val _selectedSkillType = MutableStateFlow(BioscienceSkillType.MOA_DECONSTRUCTOR)
    val selectedSkillType: StateFlow<BioscienceSkillType> = _selectedSkillType.asStateFlow()

    private val _labQuery = MutableStateFlow("")
    val labQuery: StateFlow<String> = _labQuery.asStateFlow()

    // Active Selected Item for Details Sheet
    private val _selectedArticle = MutableStateFlow<JournalArticle?>(null)
    val selectedArticle: StateFlow<JournalArticle?> = _selectedArticle.asStateFlow()

    private val _selectedTrial = MutableStateFlow<ClinicalTrial?>(null)
    val selectedTrial: StateFlow<ClinicalTrial?> = _selectedTrial.asStateFlow()

    // Dialogs
    private val _editingTrackedItem = MutableStateFlow<TrackedItemEntity?>(null)
    val editingTrackedItem: StateFlow<TrackedItemEntity?> = _editingTrackedItem.asStateFlow()

    // Veo Video Generation State
    private val _veoUiState = MutableStateFlow<VeoUiState>(VeoUiState.Idle)
    val veoUiState: StateFlow<VeoUiState> = _veoUiState.asStateFlow()

    private val _selectedVeoImageUri = MutableStateFlow<Uri?>(null)
    val selectedVeoImageUri: StateFlow<Uri?> = _selectedVeoImageUri.asStateFlow()

    private val _selectedVeoAspectRatio = MutableStateFlow(VeoAspectRatio.LANDSCAPE)
    val selectedVeoAspectRatio: StateFlow<VeoAspectRatio> = _selectedVeoAspectRatio.asStateFlow()

    private val _veoPrompt = MutableStateFlow("Animate cellular uptake and targeted CRISPR-Cas12f nuclear translocation in high-resolution scientific visualization")
    val veoPrompt: StateFlow<String> = _veoPrompt.asStateFlow()

    fun setShowCreatorProfile(show: Boolean) {
        _showCreatorProfile.value = show
    }

    fun setTab(tab: BioNavigationTab) {
        _currentTab.value = tab
    }

    fun setJournalSearch(query: String) {
        _journalSearchQuery.value = query
    }

    fun setJournalArea(area: String) {
        _selectedJournalArea.value = area
    }

    fun setTrialSearch(query: String) {
        _trialSearchQuery.value = query
    }

    fun setTrialPhase(phase: String) {
        _selectedTrialPhase.value = phase
    }

    fun setTrialStatus(status: String) {
        _selectedTrialStatus.value = status
    }

    fun setSkillType(skill: BioscienceSkillType) {
        _selectedSkillType.value = skill
    }

    fun setLabQuery(text: String) {
        _labQuery.value = text
    }

    fun selectArticle(article: JournalArticle?) {
        _selectedArticle.value = article
        _aiState.value = AiAnalysisState.Idle
    }

    fun selectTrial(trial: ClinicalTrial?) {
        _selectedTrial.value = trial
        _aiState.value = AiAnalysisState.Idle
    }

    fun setEditingTrackedItem(item: TrackedItemEntity?) {
        _editingTrackedItem.value = item
    }

    // Veo actions
    fun setSelectedVeoImageUri(uri: Uri?) {
        _selectedVeoImageUri.value = uri
    }

    fun setVeoAspectRatio(ratio: VeoAspectRatio) {
        _selectedVeoAspectRatio.value = ratio
    }

    fun setVeoPrompt(prompt: String) {
        _veoPrompt.value = prompt
    }

    fun generateVeoVideo() {
        val prompt = _veoPrompt.value.ifBlank { "Animate biological mechanism in 3D scientific dynamics" }
        viewModelScope.launch {
            _veoUiState.value = VeoUiState.Generating("Preparing image and launching Veo 3.1 Fast video model...")
            try {
                val result = veoService.generateVideoFromImage(
                    imageUri = _selectedVeoImageUri.value,
                    prompt = prompt,
                    aspectRatio = _selectedVeoAspectRatio.value,
                    onProgress = { step ->
                        _veoUiState.value = VeoUiState.Generating(step)
                    }
                )
                _veoUiState.value = VeoUiState.Success(result)
            } catch (e: Exception) {
                _veoUiState.value = VeoUiState.Error(e.message ?: "Failed to generate video with Veo.")
            }
        }
    }

    fun resetVeoState() {
        _veoUiState.value = VeoUiState.Idle
    }

    // Toggle Tracking
    fun toggleTrackArticle(article: JournalArticle) {
        viewModelScope.launch {
            val isTracked = trackedItems.value.any { it.id == article.id }
            if (isTracked) {
                repository.untrackItem(article.id)
            } else {
                repository.trackArticle(article)
            }
        }
    }

    fun toggleTrackTrial(trial: ClinicalTrial) {
        viewModelScope.launch {
            val isTracked = trackedItems.value.any { it.id == trial.nctId }
            if (isTracked) {
                repository.untrackItem(trial.nctId)
            } else {
                repository.trackTrial(trial)
            }
        }
    }

    fun updateNotes(id: String, notes: String, priority: String) {
        viewModelScope.launch {
            repository.updateTrackedItemNotes(id, notes, priority)
            _editingTrackedItem.value = null
        }
    }

    fun deleteTrackedItem(id: String) {
        viewModelScope.launch {
            repository.untrackItem(id)
        }
    }

    // Run AI Bioscience Analysis with Google Search Grounding
    fun runArticleAnalysis(article: JournalArticle, skillType: BioscienceSkillType) {
        viewModelScope.launch {
            _aiState.value = AiAnalysisState.Loading
            try {
                val result = repository.analyzeArticle(article, skillType)
                _aiState.value = AiAnalysisState.Success(result)
            } catch (e: Exception) {
                _aiState.value = AiAnalysisState.Error(e.message ?: "Failed to generate bioscience analysis.")
            }
        }
    }

    fun runTrialAnalysis(trial: ClinicalTrial, skillType: BioscienceSkillType) {
        viewModelScope.launch {
            _aiState.value = AiAnalysisState.Loading
            try {
                val result = repository.analyzeClinicalTrial(trial, skillType)
                _aiState.value = AiAnalysisState.Success(result)
            } catch (e: Exception) {
                _aiState.value = AiAnalysisState.Error(e.message ?: "Failed to generate trial intelligence.")
            }
        }
    }

    fun runLabAnalysis(customPrompt: String) {
        if (customPrompt.isBlank()) return
        viewModelScope.launch {
            _aiState.value = AiAnalysisState.Loading
            try {
                val result = repository.runCustomBioscienceQuery(customPrompt, _selectedSkillType.value)
                _aiState.value = AiAnalysisState.Success(result)
            } catch (e: Exception) {
                _aiState.value = AiAnalysisState.Error(e.message ?: "Failed to complete investigation.")
            }
        }
    }

    fun clearAiState() {
        _aiState.value = AiAnalysisState.Idle
    }

    fun getFilteredArticles(): List<JournalArticle> {
        val q = _journalSearchQuery.value.trim().lowercase()
        val area = _selectedJournalArea.value
        return repository.getArticles().filter { article ->
            val matchesArea = area == "All Areas" || article.therapeuticArea.contains(area, ignoreCase = true)
            val matchesQuery = q.isEmpty() ||
                    article.title.lowercase().contains(q) ||
                    article.journal.lowercase().contains(q) ||
                    article.molecularTarget.lowercase().contains(q) ||
                    article.modality.lowercase().contains(q) ||
                    article.abstractText.lowercase().contains(q)
            matchesArea && matchesQuery
        }
    }

    fun getFilteredTrials(): List<ClinicalTrial> {
        val q = _trialSearchQuery.value.trim().lowercase()
        val phase = _selectedTrialPhase.value
        val status = _selectedTrialStatus.value

        return repository.getClinicalTrials().filter { trial ->
            val matchesPhase = phase == "All Phases" || trial.phase.equals(phase, ignoreCase = true)
            val matchesStatus = status == "All Statuses" || trial.status.contains(status, ignoreCase = true)
            val matchesQuery = q.isEmpty() ||
                    trial.title.lowercase().contains(q) ||
                    trial.nctId.lowercase().contains(q) ||
                    trial.sponsor.lowercase().contains(q) ||
                    trial.condition.lowercase().contains(q) ||
                    trial.intervention.lowercase().contains(q) ||
                    trial.biomarkers.any { it.lowercase().contains(q) }
            matchesPhase && matchesStatus && matchesQuery
        }
    }
}
