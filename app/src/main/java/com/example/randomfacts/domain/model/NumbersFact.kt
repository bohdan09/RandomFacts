package com.example.randomfacts.domain.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.randomfacts.data.room.AppDB

@Entity(tableName = AppDB.TABLE_NAME)
data class NumbersFact(
    val number: Int,
    val text: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(number)
        parcel.writeString(text)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NumbersFact> {
        override fun createFromParcel(parcel: Parcel): NumbersFact {
            return NumbersFact(parcel)
        }

        override fun newArray(size: Int): Array<NumbersFact?> {
            return arrayOfNulls(size)
        }
    }
}