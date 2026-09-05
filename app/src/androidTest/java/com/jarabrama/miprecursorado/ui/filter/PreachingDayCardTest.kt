package com.jarabrama.miprecursorado.ui.filter

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.jarabrama.miprecursorado.ui.components.filter.PreachingDayCard
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.Duration
import java.time.LocalDate

class PreachingDayCardTest {

  @get:Rule
  val composeTestRule = createComposeRule()
  lateinit var date: LocalDate;

  @Before
  fun setUp() {
    date = LocalDate.of(2026, 8, 20)
  }

  @Test
  fun when_date_then_show_day_of_week_and_day_of_month() {
    composeTestRule.setContent {
      PreachingDayCard(
        date = date,
        duration = Duration.ofHours(2L)
      )
    }

    composeTestRule.onNodeWithText("Jueves").assertExists()
    composeTestRule.onNodeWithText("20").assertExists()
  }

  @Test
  fun when_duration_show_hours_and_minutes_of_duration() {
    val duration = Duration.ofMinutes(95)
    lateinit var horasText: String
    lateinit var minText: String

    composeTestRule.setContent {
      horasText = stringResource(com.jarabrama.miprecursorado.R.string.horas, 1)
      minText = stringResource(com.jarabrama.miprecursorado.R.string.minutos, 35)
      PreachingDayCard(date = date, duration = duration)
    }

    composeTestRule.onNodeWithText(horasText).assertExists()
    composeTestRule.onNodeWithText(minText).assertExists()
  }

}