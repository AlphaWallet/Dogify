package cn.seabornlee.dogify.database

import cn.seabornlee.dogify.db.DogifyDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope


internal actual fun Scope.createDriver(databaseName: String): SqlDriver =
    AndroidSqliteDriver(DogifyDatabase.Schema, androidContext(), databaseName)