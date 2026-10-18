package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.VeoAspectRatio
import com.example.data.sample.BioscienceDataStore
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("BioPulse", appName)
  }

  @Test
  fun `verify creator identity is Amit Samal`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    assertEquals("Amit Samal", context.getString(R.string.creator_name))
    assertEquals("samal007amit@gmail.com", context.getString(R.string.creator_email))
    assertEquals("Built by Amit Samal", context.getString(R.string.built_by_label))
  }

  @Test
  fun `verify veo aspect ratios`() {
    assertEquals("16:9", VeoAspectRatio.LANDSCAPE.apiValue)
    assertEquals("9:16", VeoAspectRatio.PORTRAIT.apiValue)
  }

  @Test
  fun `verify bioscience articles loaded`() {
    val articles = BioscienceDataStore.journalArticles
    assertTrue(articles.isNotEmpty())
    assertTrue(articles.any { it.isBreakthrough })
  }

  @Test
  fun `verify clinical trials loaded`() {
    val trials = BioscienceDataStore.clinicalTrials
    assertTrue(trials.isNotEmpty())
    assertTrue(trials.any { it.status == "Recruiting" })
  }
}
