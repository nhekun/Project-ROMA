package com.roma.launcher

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppDrawerViewModel(private val context: Context) : ViewModel() {

    private val _apps = MutableLiveData<List<AppModel>>()
    val apps: LiveData<List<AppModel>> = _apps

    private val _isDrawerOpen = MutableLiveData<Boolean>(false)
    val isDrawerOpen: LiveData<Boolean> = _isDrawerOpen

    init {
        loadApps()
    }

    fun loadApps() {
        val packageManager = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
        val resolveInfos = packageManager.queryIntentActivities(intent, 0)

        val appModels = resolveInfos.map { resolveInfo ->
            AppModel.fromResolveInfo(resolveInfo, packageManager)
        }.sortedBy { it.label.lowercase() }

        _apps.value = appModels
    }

    fun toggleDrawer() {
        _isDrawerOpen.value = !(_isDrawerOpen.value ?: false)
    }

    fun openDrawer() {
        _isDrawerOpen.value = true
    }

    fun closeDrawer() {
        _isDrawerOpen.value = false
    }

    fun launchApp(app: AppModel) {
        val launchIntent = context.packageManager.getLaunchIntentForPackage(app.packageName)
        if (launchIntent != null) {
            context.startActivity(launchIntent)
            closeDrawer()
        }
    }

    class Factory(private val context: Context) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AppDrawerViewModel(context) as T
        }
    }
}