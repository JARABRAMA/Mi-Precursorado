package com.jarabrama.miprecursorado.ui.filter

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.jarabrama.miprecursorado.R
import com.jarabrama.miprecursorado.ui.components.filter.TotalHoursCard
import org.junit.Rule
import org.junit.Test
import java.time.Duration

class TotalHoursCardTest {

  @get:Rule
  val composeTestRule = createComposeRule()


  @Test
  fun when_duration_show_total_duration() {
    lateinit var horasText: String
    lateinit var minutesText: String
    composeTestRule.setContent {
      horasText = stringResource(R.string.horas, 7)
      minutesText = stringResource(R.string.minutos, 3)
      TotalHoursCard(
        duration = Duration.ofMinutes(423L)
      )
    }

    composeTestRule.onNodeWithText(horasText).assertExists()
    composeTestRule.onNodeWithText(minutesText).assertExists()
  }

  @Test
  fun when_duration_is_cero_then_show_message() {
    lateinit var textNotTimeJet: String;

    composeTestRule.setContent {
      textNotTimeJet = stringResource(R.string.no_horas_aun)
      TotalHoursCard(Duration.ofMinutes(0))
    }

    composeTestRule.onNodeWithText(textNotTimeJet).assertExists()
  }

  @Test
  fun when_duration_is_zero_does_not_show_minutes_and_hours() {
    lateinit var horasText: String
    lateinit var minutesText: String
    composeTestRule.setContent {
      horasText = stringResource(R.string.horas, 0)
      minutesText = stringResource(R.string.minutos, 0)
      TotalHoursCard(
        duration = Duration.ofMinutes(0)
      )
    }

    composeTestRule.onNodeWithText(horasText).assertDoesNotExist()
    composeTestRule.onNodeWithText(minutesText).assertDoesNotExist()
  }
}