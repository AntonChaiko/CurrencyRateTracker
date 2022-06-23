package com.example.currencyratetracker.data.solid

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaDrm
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class Solid @Inject constructor(
    private val auth: FirebaseAuth,
    @Named("LogInFile")
    private val logger: Logger,
    private val authenticator: Authenticator
) {

    suspend fun loginUser() {
        try {
            authenticator.authenticate("asda", "Asdasd")
        } catch (e: Exception) {

            logger.log(e.localizedMessage ?: "Unknown error!")

            Logger.LogInCrashlytics(
                Crashlytics()
            ).log(e.localizedMessage ?: "Unknown error!")
        }
    }
}

class FirebaseAuth {
    fun authenticateUser(password: String, username: String): Boolean = true
}

interface Logger {

    fun log(logMessage: String)

    class LogInFile : Logger {
        override fun log(logMessage: String) {
            val file = File("error.txt")
            file.appendText(logMessage)
        }
    }

    class LogInCrashlytics(
        private val crashlytics: Crashlytics,
    ) : Logger {
        override fun log(logMessage: String) {
            crashlytics.sendError(logMessage)
        }
    }
}


class Crashlytics {
    fun sendError(errorMessage: String): Boolean = true
}


interface Authenticator {

    suspend fun authenticate(name: String, password: String)

    class AuthByFirebase(private val firebaseAuth: FirebaseAuth) : Authenticator {
        override suspend fun authenticate(name: String, password: String) {
            firebaseAuth.authenticateUser(password, name)
        }

    }

    class AuthAtOwnSource() : Authenticator {
        override suspend fun authenticate(name: String, password: String) {

        }

    }

}

interface Save<T> {
    fun save(data: T)
}

interface Read<T> {
    fun read(): T
}


interface Storage<T> {

    interface Read<T> : com.example.currencyratetracker.data.solid.Read<T>
    interface Save<T> : com.example.currencyratetracker.data.solid.Save<T>
}

interface StringStorage {
    interface Save : Storage.Save<String>
    interface Read : Storage.Read<String>

    class Base(context: Context) : Save, Read {


        private val key = "key"
        private val sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)

        override fun save(data: String) {

            sharedPreferences.edit().putString(key, data).apply()
        }

        override fun read(): String {
            return sharedPreferences.getString(key, "") ?: ""
        }

    }


}


