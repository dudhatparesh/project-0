package com.brighterbrain.project0.data

import com.brighterbrain.project0.data.model.Item
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseHelper @Inject constructor(private var firebaseDatabase: FirebaseDatabase){
    fun saveItem(item: Item){
        firebaseDatabase.getReference("project-0")
                .child("items")
                .child(item.id.toString())
                .setValue(item)
    }
}