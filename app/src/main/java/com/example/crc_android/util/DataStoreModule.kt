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

    private val Context.dataStore by preferencesDataStore(name = "dataStore")

    private val token = stringPreferencesKey("token") // string 저장 키값
    private val email = stringPreferencesKey("email")
    private val password = stringPreferencesKey("password")

    val getToken : Flow<String> = context.dataStore.data
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

    val getEmail : Flow<String> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            preferences[email] ?: ""
        }

    val getPassword : Flow<String> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            preferences[password] ?: ""
        }

    suspend fun setToken(text : String){
        context.dataStore.edit { preferences ->
            preferences[token] = text
        }
    }

    suspend fun setEmail(text : String){
        context.dataStore.edit { preferences ->
            preferences[email] = text
        }
    }

    suspend fun setPassword(text : String){
        context.dataStore.edit { preferences ->
            preferences[password] = text
        }
    }
}