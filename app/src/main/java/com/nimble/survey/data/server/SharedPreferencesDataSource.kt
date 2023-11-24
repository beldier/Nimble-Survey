package com.nimble.survey.data.server

import android.content.Context
import android.content.SharedPreferences
import com.nimble.data.datasource.SensitiveInformationDataSource
import com.nimble.survey.R
import org.koin.core.annotation.Factory

@Factory
class SharedPreferencesDataSource(context: Context):SensitiveInformationDataSource {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    companion object {
        const val USER_TOKEN = "user_token"
    }

    override fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    override fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}