package com.example.mywishlist

sealed class Screen(val rout: String) {
    object HomeView: Screen("home_view")
    object AddScreen: Screen("add_screen")
}