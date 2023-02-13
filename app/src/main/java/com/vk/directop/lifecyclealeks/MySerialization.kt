package com.vk.directop.lifecyclealeks

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
/*
class MySerialization {

    val serializablePerson = Person(
        name = "John",
        surname = "Smith",
        age = 21,
    )
    val directory = ""
    val filePath = "${directory}/serialized.bin"
    val file = File(filePath).apply { createNewFile() }

    // write Person
    val fileOutputStream = FileOutputStream(file)
    //ObjectOutputStream
    ObjectOutputStream(fileOutputStream).use{
        it.writeObject(serializablePerson)
        it.flush()
    }

    // read Person
    val fileInputStream = FileInputStream(file)
    ObjectInputStream(fileInputStream).use{
        print(it.readObject() as Person)
    }
}
*/
