package com.delminiusdevs.toppop.util

fun secondsFormatter(seconds: Int): String {
    return "${(seconds / 60).toString().padStart(2, '0')}:${
        (seconds % 60).toString().padStart(2, '0')
    }"
}