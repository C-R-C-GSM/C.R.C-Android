package com.example.crc_android.util

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.crc_android.data.util.Util.Companion.DEFAULT_TOKEN
import com.example.crc_android.data.util.Util.Companion.PREFERENCES_EMAIL
import com.example.crc_android.data.util.Util.Companion.PREFERENCES_PASSWORD
import com.example.crc_android.data.util.Util.Companion.PREFERENCES_TOKEN
import com.example.crc_android.data.util.Util.Companion.PREFERENCE_NAME
import com.example.crc_android.util.DataStoreModule.PreferencesKeys.dataStoreEmail
import com.example.crc_android.util.DataStoreModule.PreferencesKeys.dataStorePassword
import com.example.crc_android.util.DataStoreModule.PreferencesKeys.dataStoreToken
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject



private val Context.dataStore by preferencesDataStore(PREFERENCE_NAME)

@ActivityRetainedScoped
class DataStoreModule @Inject constructor(@ApplicationContext private val context: Context) {


    private object PreferencesKeys {
        val dataStoreToken = stringPreferencesKey(PREFERENCES_TOKEN)
        val dataStoreEmail = stringPreferencesKey(PREFERENCES_EMAIL)
        val dataStorePassword = stringPreferencesKey(PREFERENCES_PASSWORD)

    }

    //private val intKey = intPreferencesKey("key_name") // int 저장 키값
    private val dataStore: DataStore<Preferences> =
        context.dataStore


    //쓰기
    suspend fun setToken(text: String) {
        context.dataStore.edit { preferences ->
            preferences[dataStoreToken] = text
        }
    }

    suspend fun setEmail(text: String) {
        context.dataStore.edit { preferences ->
            preferences[dataStoreEmail] = text
        }
    }

    suspend fun setPassword(text: String) {
        context.dataStore.edit { preferences ->
            preferences[dataStorePassword] = text
        }
    }

    // 읽기
    val readToken: Flow<Token> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val token = preferences[dataStoreToken] ?: DEFAULT_TOKEN
            Log.d("DataStoreRepository", "readToken  $token")
            Token(token)
        }

    val readEmail: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[dataStoreEmail] ?: ""
        }


    val readPassword: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[dataStorePassword] ?: ""
        }



}


data class Token(
    val token: String,
)