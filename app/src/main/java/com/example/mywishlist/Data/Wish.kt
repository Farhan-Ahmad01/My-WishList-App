package com.example.mywishlist.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Wish-Table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo("wish-title")
    val title: String = "",
    @ColumnInfo("wish-description")
    val description: String = ""
)

object DummyWish{
    val wishList = listOf(
        Wish(title = "A Mac Book", description = "For a smooth experience of working on laptop"),
        Wish(title = "A smart wath", description = "For a smooth experience of working on laptop"),
        Wish(title = "A smart pen", description = "For a smooth experience of working on laptop"),
        Wish(title = "A gaming VR", description = "For a smooth experience of working on laptop"),
        Wish(title = "A Rolls Royace", description = "For a smooth experience of working on laptop")
    )
}
