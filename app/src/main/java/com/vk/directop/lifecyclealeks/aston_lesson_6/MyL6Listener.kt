package com.vk.directop.lifecyclealeks.aston_lesson_6

interface MyL6Listener : FirstListener, SecondListener, ThirdListener{
}

interface FirstListener{
    fun firstOnClick()
}

interface SecondListener{
    fun secondOnClick()
}

interface ThirdListener{
    fun thirdOnClick()
}