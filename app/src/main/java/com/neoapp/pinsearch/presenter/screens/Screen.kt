package com.neoapp.pinsearch.presenter.screens

sealed class Screen(val route : String , val name : String){

    object Home : Screen("home" , "Home")
    object Pin : Screen("pin" , "Pin")
    object Post : Screen("post" , "Post")
    object History : Screen("history" , "History")
}
