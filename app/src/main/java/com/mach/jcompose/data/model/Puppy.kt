package com.mach.jcompose.data.model

import androidx.annotation.DrawableRes

enum class DogSize(val size: String) {
    LARGE("Large (61-100lbs"),
    MEDIUM("Medium (26-60lbs)"),
    SMALL("Small (25lbs or less)")
}

enum class DogSex(val what: String) {
    GOOD_BOY("Good Boy"),
    GOOD_GIRL("Good Girl")
}

data class Puppy(
    val id: Int,
    val name: String,
    val age: String,
    @DrawableRes val picture: Int,
    val description: String,
    val sex: DogSex,
    val size: DogSize
)
