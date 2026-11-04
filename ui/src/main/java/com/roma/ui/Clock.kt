package com.roma.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.roma.roman.RomanFormatter

@Composable
fun RomanClock(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
    viewModel: ClockViewModel = viewModel()
) {
    val timeString by viewModel.time.observeAsState("")
    val timeDescription = remember(timeString) { timeString.toSpokenTime() }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        androidx.compose.material3.Text(
            text = timeString,
            style = androidx.compose.material3.MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            modifier = androidx.compose.ui.Modifier.padding(16.dp)
                .semantics { contentDescription = timeDescription }
        )
    }
}

@Composable
fun RomanDate(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
    viewModel: ClockViewModel = viewModel()
) {
    val dateString by viewModel.date.observeAsState("")
    val dateDescription = remember(dateString) { dateString.toSpokenDate() }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        androidx.compose.material3.Text(
            text = dateString,
            style = androidx.compose.material3.MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
            modifier = androidx.compose.ui.Modifier.padding(8.dp)
                .semantics { contentDescription = dateDescription }
        )
    }
}

// Extension functions for accessibility - convert Roman numerals to spoken format using centralized formatter
private fun String.toSpokenTime(): String {
    // Convert "Ⅸ:ⅩⅬⅠ" -> "9:41"
    return this.split(":").map { RomanFormatter.toArabic(it).toString() }.joinToString(":")
}

private fun String.toSpokenDate(): String {
    // Convert "Ⅸ SEPTEMBER MMXXVI" -> "9 September 2026"
    return this.split(" ").map { part ->
        if (part.all { it.isDigit() || "ⅯⅮⅭⅬⅩⅤⅠN".contains(it) }) {
            RomanFormatter.toArabic(part).toString()
        } else {
            part
        }
    }.joinToString(" ")
}