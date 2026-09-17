package com.example.myapplication.model

data class Student(
    val id: String,
    val name: String,
    val className: String,
    val email : String,
    var gpa: Double,
    val phone: String
)