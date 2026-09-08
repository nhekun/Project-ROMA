package com.roma.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
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

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        androidx.compose.material3.Text(
            text = timeString,
            fontSize = 72.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = androidx.compose.ui.Modifier.padding(16.dp)
        )
    }
}

@Composable
fun RomanDate(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
    viewModel: ClockViewModel = viewModel()
) {
    val dateString by viewModel.date.observeAsState("")

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        androidx.compose.material3.Text(
            text = dateString,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = androidx.compose.ui.Modifier.padding(8.dp)
        )
    }
}