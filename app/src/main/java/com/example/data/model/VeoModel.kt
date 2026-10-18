package com.example.data.model

enum class VeoAspectRatio(val apiValue: String, val label: String) {
    LANDSCAPE("16:9", "16:9 (Landscape)"),
    PORTRAIT("9:16", "9:16 (Portrait)")
}

data class VeoVideoResult(
    val prompt: String,
    val aspectRatio: String,
    val videoUri: String?,
    val operationName: String?,
    val isSimulated: Boolean,
    val summary: String,
    val timestamp: Long = System.currentTimeMillis()
)

sealed interface VeoUiState {
    object Idle : VeoUiState
    data class Generating(val stepMessage: String) : VeoUiState
    data class Success(val result: VeoVideoResult) : VeoUiState
    data class Error(val message: String) : VeoUiState
}
