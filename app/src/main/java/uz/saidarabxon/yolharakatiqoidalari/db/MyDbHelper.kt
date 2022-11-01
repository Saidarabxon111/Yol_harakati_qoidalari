package uz.saidarabxon.yolharakatiqoidalari.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.saidarabxon.yolharakatiqoidalari.models.Models

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION),
    MyDbINterface {


    companion object {
        const val DB_NAME = "db_name"
        const val VERSION = 1

        const val APP_TABLE = "app_table"
        const val APP_ID = "id"

        const val APP_NAME = "name"
        const val APP_INFO = "info"
        const val APP_IMAGE = "image"
        const val APP_LIKE = "like"
        const val APP_TYPE = "type"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "create table  $APP_TABLE($APP_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,$APP_NAME TEXT NOT NULL, $APP_INFO  TEXT NOT NULL , $APP_IMAGE TEXT NOT NULL , $APP_LIKE text not null, $APP_TYPE TEXT NOT NULL)"

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun addLabel(models: Models) {
        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(APP_NAME, models.name)
        contentValues.put(APP_INFO, models.info)
        contentValues.put(APP_IMAGE, models.img)
        contentValues.put(APP_LIKE, models.like)
        contentValues.put(APP_TYPE, models.type)

        database.insert(APP_TABLE, null, contentValues)
        database.close()
    }

    override fun getAllLabel(): ArrayList<Models> {
        val list = ArrayList<Models>()
        val query = "select * from $APP_TABLE "

        val database = readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Models(
                        id = cursor.getInt(0),
                        name = cursor.getString(1),
                        info = cursor.getString(2),
                        img = cursor.getString(3),
                        like =  cursor.getString(4),
                        type = cursor.getString(5)

                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun editLabel(models: Models) {
val database =writableDatabase
val contentValues =ContentValues()
contentValues.put(APP_ID,models.id)
        contentValues.put(APP_NAME,models.name)
        contentValues.put(APP_INFO,models.info)
        contentValues.put(APP_IMAGE,models.img)
        contentValues.put(APP_LIKE,models.like)
        contentValues.put(APP_TYPE,models.type)

        database.update(APP_TABLE, contentValues, "$APP_ID=?", arrayOf(models.id.toString()))
    }

    override fun deleteLabel(models: Models) {
val database =writableDatabase
database.delete(APP_TABLE,"$APP_ID=?", arrayOf(models.id.toString()))
        database.close()
    }
}