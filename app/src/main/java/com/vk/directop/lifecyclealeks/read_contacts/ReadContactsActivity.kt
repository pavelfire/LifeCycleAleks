package com.vk.directop.lifecyclealeks.read_contacts

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vk.directop.lifecyclealeks.App
import com.vk.directop.lifecyclealeks.R
import com.vk.directop.lifecyclealeks.databinding.ActivityReadContactsBinding
import com.vk.directop.lifecyclealeks.recycler_user.UserActionListener
import com.vk.directop.lifecyclealeks.recycler_user.model.User
import com.vk.directop.lifecyclealeks.recycler_user.model.UsersService

//https://www.youtube.com/watch?v=T6ve7gUrVfc

class ReadContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadContactsBinding
    private lateinit var adapter: ContactsAdapter

    private val usersService: ContactService
        get() = (applicationContext as App).contactService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ContactsAdapter(
            object : ContactActionListener {
                override fun onUserMove(user: User, moveBy: Int) {
                    TODO("Not yet implemented")
                }

                override fun onUserDelete(user: User) {
                    TODO("Not yet implemented")
                }

                override fun onUserDetails(user: User) {
                    TODO("Not yet implemented")
                }
            }
        )

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        usersService.addListener(usersListener)

        findViewById<Button>(R.id.btReadContacts).setOnClickListener {
            //need to take permission in app info for contacts or crash

            val contactList: MutableList<ContactDTO> = ArrayList()
            val contacts = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null,
            )

            if (contacts != null) {
                while (contacts.moveToNext()) {
                    val name =
                        contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    val phone =
                        contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val id =
                        contacts.getString(contacts.getColumnIndex(ContactsContract.Contacts._ID))

                    contactList.add(ContactDTO(id = 0, name = name, phone = phone))

                }

            } else {
                Log.d("Tag", "empty list contacts")
            }
            contacts?.close()

            contactList.add(ContactDTO(id = 0, name = "Pav", phone = "128500"))
            contactList.add(ContactDTO(id = 0, name = "Alena", phone = "4646744"))
            Log.d("TAG", "Contacts: $contactList")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.removeListener(usersListener)
    }

    private val usersListener: ContactListener = {
        adapter.users = it
    }
}