package com.example.crc_android.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreModule(private val context : Context) {

    private val Context.dataStore  by preferencesDataStore(name = "dataStore")

    private val token = stringPreferencesKey("token") // string 저장 키값
    //private val intKey = intPreferencesKey("key_name") // int 저장 키값

    // 읽기
    val text : Flow<String> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            preferences[token] ?: ""
        }

    //쓰기
    suspend fun setToken(text : String){
        context.dataStore.edit { preferences ->
            preferences[token] = text
        }
    }

}