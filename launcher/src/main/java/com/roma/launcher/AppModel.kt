package com.roma.launcher

import android.content.pm.ApplicationInfo
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable

data class AppModel(
    val packageName: String,
    val label: String,
    val icon: Drawable,
    val resolveInfo: ResolveInfo
) {
    companion object {
        fun fromResolveInfo(resolveInfo: ResolveInfo, packageManager: android.content.pm.PackageManager): AppModel {
            return AppModel(
                packageName = resolveInfo.activityInfo.packageName,
                label = resolveInfo.loadLabel(packageManager).toString(),
                icon = resolveInfo.loadIcon(packageManager),
                resolveInfo = resolveInfo
            )
        }
    }
}