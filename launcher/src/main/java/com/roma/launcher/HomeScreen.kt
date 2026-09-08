package com.roma.launcher

import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.pointerInput
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toBitmap
import androidx.compose.ui.graphics.toPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextOverflow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.roma.ui.ClockViewModel
import com.roma.ui.RomanClock
import com.roma.ui.RomanDate
import com.roma.ui.RomaTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.clickable

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val appDrawerViewModel = viewModel(factory = AppDrawerViewModel.Factory(context))
    val clockViewModel = viewModel(factory = ClockViewModel.Factory())
    
    val isDrawerOpen by appDrawerViewModel.isDrawerOpen.observeAsState(false)
    val apps by appDrawerViewModel.apps.observeAsState(emptyList())

    RomaTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            // Main content
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Clock
                RomanClock(viewModel = clockViewModel)
                
                // Date
                RomanDate(viewModel = clockViewModel)
                
                // Spacer
                androidx.compose.foundation.layout.Spacer(modifier = Modifier.fillMaxWidth())
                
                // Bottom dock - 4 apps
                BottomDock(apps = apps.take(4))
                
                // App Drawer
                if (isDrawerOpen) {
                    AppDrawer(
                        apps = apps,
                        onAppClick = { app ->
                            appDrawerViewModel.launchApp(app)
                        },
                        onClose = { appDrawerViewModel.closeDrawer() }
                    )
                }
            }
            
            // Swipe up gesture to open drawer
            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = { velocity ->
                                if (velocity.y < -500 && !isDrawerOpen) {
                                    appDrawerViewModel.openDrawer()
                                } else if (velocity.y > 500 && isDrawerOpen) {
                                    appDrawerViewModel.closeDrawer()
                                }
                            }
                        )
                    }
            )
        }
    }
}

@Composable
fun BottomDock(apps: List<AppModel>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, bottom = 32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        apps.forEach { app ->
            DockAppIcon(app = app)
        }
    }
}

@Composable
fun DockAppIcon(app: AppModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.size(64.dp)
    ) {
        AppIcon(icon = app.icon, modifier = Modifier.size(48.dp))
        Text(
            text = app.label,
            fontSize = 12.sp,
            color = androidx.compose.ui.graphics.Color.White,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp).fillMaxWidth()
        )
    }
}

@Composable
fun AppIcon(
    icon: android.graphics.drawable.Drawable,
    modifier: Modifier = Modifier
) {
    val bitmap = remember { icon.toBitmap() }
    val imageBitmap = remember(bitmap) { bitmap.asImageBitmap() }
    Image(
        painter = imageBitmap.asPainter(),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}

@Composable
fun AppDrawer(
    apps: List<AppModel>,
    onAppClick: (AppModel) -> Unit,
    onClose: () -> Unit
) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.9f))
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = { velocity ->
                        if (velocity.y > 500) onClose()
                    }
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Header
            Text(
                text = "APPS",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            
            // App grid
            LazyHorizontalGrid(
                rows = GridCells.Fixed(4),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(apps) { app ->
                    AppDrawerItem(app = app, onClick = onAppClick)
                }
            }
        }
    }
}

@Composable
fun AppDrawerItem(app: AppModel, onClick: (AppModel) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .clickable { onClick(app) }
    ) {
        AppIcon(icon = app.icon, modifier = Modifier.size(64.dp))
        Text(
            text = app.label,
            fontSize = 12.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp).fillMaxWidth()
        )
    }
}