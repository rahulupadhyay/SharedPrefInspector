package com.github.dhaval2404.sharedprefinspector.data.repository

import android.content.Context
import com.github.dhaval2404.sharedprefinspector.data.SharedPrefDatabase
import com.github.dhaval2404.sharedprefinspector.data.entity.SharedPref
import com.github.dhaval2404.sharedprefinspector.util.NotificationHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedPrefRepository(context: Context) {

    private val mSharePrefDAO = SharedPrefDatabase.getInstance(context).sharePrefDAO()
    private val mNotificationHelper = NotificationHelper(context)

    fun insert(sharedPref: SharedPref) {
        CoroutineScope(Dispatchers.Main).launch {
            sharedPref.id = mSharePrefDAO.insert(sharedPref)
            mNotificationHelper.show(sharedPref)
        }
    }

    suspend fun getAll(sharedPref: SharedPref) {
        mSharePrefDAO.getAll()
    }

    suspend fun clear() {
        CoroutineScope(Dispatchers.Main).launch {
            mSharePrefDAO.clear()
        }
    }

}