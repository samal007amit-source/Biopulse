package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.BioscienceSkillType
import com.example.data.model.ClinicalTrial
import com.example.data.sample.BioscienceDataStore
import com.example.ui.theme.BioAmberAlert
import com.example.ui.theme.BioCyanAccent
import com.example.ui.theme.BioGreenSuccess
import com.example.ui.viewmodel.BioscienceViewModel

@Composable
fun ClinicalTrialsScreen(
    viewModel: BioscienceViewModel,
    modifier: Modifier = Modifier
) {
    val searchQuery by viewModel.trialSearchQuery.collectAsState()
    val selectedPhase by viewModel.selectedTrialPhase.collectAsState()
    val selectedStatus by viewModel.selectedTrialStatus.collectAsState()
    val trackedItems by viewModel.trackedItems.collectAsState()
    val trials = viewModel.getFilteredTrials()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("trials_list_view"),
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {
        // Hero Header
        item {
            TrialsHeroSection(
                trialCount = trials.size,
                recruitingCount = trials.count { it.status.contains("Recruiting", ignoreCase = true) }
            )
        }

        // Search Bar
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.setTrialSearch(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .testTag("trial_search_input"),
                placeholder = { Text("Search NCT ID, biomarker, sponsor, drug...") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.primary)
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(
                            onClick = { viewModel.setTrialSearch("") },
                            modifier = Modifier.testTag("clear_trial_search")
                        ) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear search")
                        }
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                ),
                singleLine = true
            )
        }

        // Phase Filter Chips
        item {
            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(BioscienceDataStore.trialPhases) { phase ->
                        val isSelected = phase == selectedPhase
                        FilterChip(
                            selected = isSelected,
                            onClick = { viewModel.setTrialPhase(phase) },
                            label = { Text(phase, fontSize = 12.sp) },
                            modifier = Modifier.testTag("filter_phase_$phase"),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(BioscienceDataStore.recruitmentStatuses) { status ->
                        val isSelected = status == selectedStatus
                        FilterChip(
                            selected = isSelected,
                            onClick = { viewModel.setTrialStatus(status) },
                            label = { Text(status, fontSize = 12.sp) },
                            modifier = Modifier.testTag("filter_status_$status"),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        )
                    }
                }
            }
        }

        // Trials List
        if (trials.isEmpty()) {
            item {
                EmptySearchState(
                    title = "No Clinical Trials Matching Criteria",
                    subtitle = "Broaden your search terms or reset the phase/status filters."
                )
            }
        } else {
            items(trials, key = { it.nctId }) { trial ->
                val isTracked = trackedItems.any { it.id == trial.nctId }
                ClinicalTrialCard(
                    trial = trial,
                    isTracked = isTracked,
                    onToggleTrack = { viewModel.toggleTrackTrial(trial) },
                    onViewDetails = { viewModel.selectTrial(trial) },
                    onAiAnalyze = {
                        viewModel.selectTrial(trial)
                        viewModel.runTrialAnalysis(trial, BioscienceSkillType.TRIAL_ENDPOINT_EVALUATOR)
                    }
                )
            }
        }
    }
}

@Composable
fun TrialsHeroSection(trialCount: Int, recruitingCount: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("trials_hero_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.LocalHospital,
                        contentDescription = "Trial Icon",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Clinical Trial Registry Intelligence",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Built by Amit Samal • Real-time trial protocols & endpoints",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                QuickStatBadge(label = "Trials Tracked", value = "$trialCount active", color = MaterialTheme.colorScheme.primary)
                QuickStatBadge(label = "Actively Recruiting", value = "$recruitingCount studies", color = BioGreenSuccess)
                QuickStatBadge(label = "AI Scrutiny", value = "Endpoints & MoA", color = MaterialTheme.colorScheme.tertiary)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ClinicalTrialCard(
    trial: ClinicalTrial,
    isTracked: Boolean,
    onToggleTrack: () -> Unit,
    onViewDetails: () -> Unit,
    onAiAnalyze: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    val statusColor = when {
        trial.status.contains("Recruiting", ignoreCase = true) -> BioGreenSuccess
        trial.status.contains("Active", ignoreCase = true) -> BioAmberAlert
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("trial_card_${trial.nctId}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: NCT ID, Status badge, Track button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = trial.nctId,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Text(
                            text = trial.phase,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    // Status Indicator Dot
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(statusColor.copy(alpha = 0.12f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 3.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(statusColor)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = trial.status,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = statusColor
                        )
                    }
                }

                IconButton(
                    onClick = onToggleTrack,
                    modifier = Modifier.testTag("track_trial_button_${trial.nctId}")
                ) {
                    Icon(
                        imageVector = if (isTracked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = if (isTracked) "Untrack trial" else "Track trial",
                        tint = if (isTracked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Indication & Title
            Text(
                text = trial.condition,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = trial.title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Sponsor & Timeline
            Text(
                text = "Sponsor: ${trial.sponsor} • Enrollment: ${trial.enrollmentCount}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Biomarkers Chips
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                TagChip(label = "Intervention: ${trial.intervention.take(30)}...", color = MaterialTheme.colorScheme.primary)
                trial.biomarkers.forEach { biomarker ->
                    TagChip(label = biomarker, color = BioCyanAccent)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Primary Endpoint Preview
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Text(
                    text = "Primary Endpoint:",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = trial.primaryEndpoint,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Expand Toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded }
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isExpanded) "Hide Protocol Details" else "View Full Protocol & Design",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Study Design: ${trial.studyDesign}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Timeline: ${trial.startDate} – Target Completion: ${trial.completionDate}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = trial.detailedSummary,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onAiAnalyze,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("ai_trial_analyze_button_${trial.nctId}"),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        Icons.Default.Analytics,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("AI Endpoint Scrutiny", fontSize = 12.sp, color = MaterialTheme.colorScheme.onPrimary)
                }

                OutlinedButton(
                    onClick = onViewDetails,
                    modifier = Modifier.testTag("trial_details_button_${trial.nctId}"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Protocol", fontSize = 12.sp)
                }
            }
        }
    }
}
