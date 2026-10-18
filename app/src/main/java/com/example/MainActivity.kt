package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.screens.ArticleDetailBottomSheet
import com.example.ui.screens.BioVideoScreen
import com.example.ui.screens.ClinicalTrialsScreen
import com.example.ui.screens.CreatorProfileDialog
import com.example.ui.screens.EditNoteDialog
import com.example.ui.screens.JournalsScreen
import com.example.ui.screens.ScienceLabScreen
import com.example.ui.screens.TrendsScreen
import com.example.ui.screens.TrialDetailBottomSheet
import com.example.ui.screens.WatchlistScreen
import com.example.ui.theme.BioCyanAccent
import com.example.ui.theme.BioPurpleBiotech
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.BioNavigationTab
import com.example.ui.viewmodel.BioscienceViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: BioscienceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                BioPulseApp(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BioPulseApp(viewModel: BioscienceViewModel) {
    val currentTab by viewModel.currentTab.collectAsState()
    val trackedItems by viewModel.trackedItems.collectAsState()
    val selectedArticle by viewModel.selectedArticle.collectAsState()
    val selectedTrial by viewModel.selectedTrial.collectAsState()
    val editingTrackedItem by viewModel.editingTrackedItem.collectAsState()
    val showCreatorProfile by viewModel.showCreatorProfile.collectAsState()

    // Handle back button: return to Journals if in other tabs
    BackHandler(enabled = currentTab != BioNavigationTab.JOURNALS && selectedArticle == null && selectedTrial == null) {
        viewModel.setTab(BioNavigationTab.JOURNALS)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Science,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "BioPulse",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = BioCyanAccent.copy(alpha = 0.15f)
                                ) {
                                    Text(
                                        text = "Gemini 3.8",
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = BioCyanAccent
                                    )
                                }
                            }
                            Text(
                                text = "Search Grounded • Veo Video",
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 9.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                actions = {
                    // Prominently display "Built by Amit Samal" in Top Bar
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.9f),
                        modifier = Modifier
                            .clickable { viewModel.setShowCreatorProfile(true) }
                            .padding(end = 12.dp)
                            .testTag("built_by_creator_button")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(18.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "AS",
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Built by Amit Samal",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.testTag("app_top_bar")
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .testTag("bottom_navigation_bar")
            ) {
                // Tab 1: Journals
                NavigationBarItem(
                    selected = currentTab == BioNavigationTab.JOURNALS,
                    onClick = { viewModel.setTab(BioNavigationTab.JOURNALS) },
                    icon = { Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = "Journals") },
                    label = { Text("Journals", fontSize = 9.sp) },
                    modifier = Modifier.testTag("nav_item_journals"),
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )

                // Tab 2: Clinical Trials
                NavigationBarItem(
                    selected = currentTab == BioNavigationTab.TRIALS,
                    onClick = { viewModel.setTab(BioNavigationTab.TRIALS) },
                    icon = { Icon(Icons.Default.LocalHospital, contentDescription = "Trials") },
                    label = { Text("Trials", fontSize = 9.sp) },
                    modifier = Modifier.testTag("nav_item_trials"),
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )

                // Tab 3: Bioscience AI Lab (Google Search Grounded)
                NavigationBarItem(
                    selected = currentTab == BioNavigationTab.AI_LAB,
                    onClick = { viewModel.setTab(BioNavigationTab.AI_LAB) },
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(BioCyanAccent.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.AutoAwesome,
                                contentDescription = "AI Lab",
                                tint = BioCyanAccent,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    },
                    label = { Text("AI Lab", fontSize = 9.sp, fontWeight = FontWeight.Bold) },
                    modifier = Modifier.testTag("nav_item_ai_lab"),
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )

                // Tab 4: BioVideo Studio (Veo 3.1 Fast)
                NavigationBarItem(
                    selected = currentTab == BioNavigationTab.BIO_VIDEO,
                    onClick = { viewModel.setTab(BioNavigationTab.BIO_VIDEO) },
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(BioPurpleBiotech.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Movie,
                                contentDescription = "Veo Video",
                                tint = BioPurpleBiotech,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    },
                    label = { Text("Veo Video", fontSize = 9.sp, fontWeight = FontWeight.Bold) },
                    modifier = Modifier.testTag("nav_item_veo_video"),
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )

                // Tab 5: Watchlist
                NavigationBarItem(
                    selected = currentTab == BioNavigationTab.WATCHLIST,
                    onClick = { viewModel.setTab(BioNavigationTab.WATCHLIST) },
                    icon = {
                        if (trackedItems.isNotEmpty()) {
                            BadgedBox(
                                badge = {
                                    Badge(
                                        containerColor = MaterialTheme.colorScheme.primary,
                                        contentColor = MaterialTheme.colorScheme.onPrimary
                                    ) {
                                        Text("${trackedItems.size}", fontSize = 8.sp)
                                    }
                                }
                            ) {
                                Icon(Icons.Default.Bookmark, contentDescription = "Watchlist")
                            }
                        } else {
                            Icon(Icons.Default.Bookmark, contentDescription = "Watchlist")
                        }
                    },
                    label = { Text("Watchlist", fontSize = 9.sp) },
                    modifier = Modifier.testTag("nav_item_watchlist"),
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )

                // Tab 6: Trends
                NavigationBarItem(
                    selected = currentTab == BioNavigationTab.TRENDS,
                    onClick = { viewModel.setTab(BioNavigationTab.TRENDS) },
                    icon = { Icon(Icons.Default.Insights, contentDescription = "Trends") },
                    label = { Text("Trends", fontSize = 9.sp) },
                    modifier = Modifier.testTag("nav_item_trends"),
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(targetState = currentTab, label = "tab_crossfade") { tab ->
                when (tab) {
                    BioNavigationTab.JOURNALS -> JournalsScreen(viewModel = viewModel)
                    BioNavigationTab.TRIALS -> ClinicalTrialsScreen(viewModel = viewModel)
                    BioNavigationTab.AI_LAB -> ScienceLabScreen(viewModel = viewModel)
                    BioNavigationTab.BIO_VIDEO -> BioVideoScreen(viewModel = viewModel)
                    BioNavigationTab.WATCHLIST -> WatchlistScreen(viewModel = viewModel)
                    BioNavigationTab.TRENDS -> TrendsScreen(viewModel = viewModel)
                }
            }
        }
    }

    // Article Detail Modal
    selectedArticle?.let { article ->
        ArticleDetailBottomSheet(
            article = article,
            viewModel = viewModel,
            onDismiss = {
                viewModel.selectArticle(null)
            }
        )
    }

    // Clinical Trial Detail Modal
    selectedTrial?.let { trial ->
        TrialDetailBottomSheet(
            trial = trial,
            viewModel = viewModel,
            onDismiss = {
                viewModel.selectTrial(null)
            }
        )
    }

    // Edit Notes Dialog
    editingTrackedItem?.let { item ->
        EditNoteDialog(
            item = item,
            onSave = { notes, priority ->
                viewModel.updateNotes(item.id, notes, priority)
            },
            onDismiss = {
                viewModel.setEditingTrackedItem(null)
            }
        )
    }

    // Creator Profile Dialog
    if (showCreatorProfile) {
        CreatorProfileDialog(
            onDismiss = {
                viewModel.setShowCreatorProfile(false)
            }
        )
    }
}
