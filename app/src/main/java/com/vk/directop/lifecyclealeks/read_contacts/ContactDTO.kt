package com.vk.directop.lifecyclealeks.read_contacts

import android.graphics.Bitmap

data class ContactDTO(
    val id : Long,
    val name: String = "",
    val phone: String = "",
    val image : Bitmap? = null

    )
