package com.example.myapplication.utils

fun Double.toAcademyRanking() : String = when {
    this >= 3.6 -> "Xuất sắc"
    this >= 3.2 -> "Giỏi"
    this >= 2.5 -> "Khá"
    else -> "Trung bình"
}