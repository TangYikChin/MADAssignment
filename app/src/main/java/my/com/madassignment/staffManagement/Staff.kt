package my.com.madassignment.staffManagement

import com.google.firebase.firestore.DocumentId


data class Staff (
    @DocumentId
    var id: String="",
    var name : String = "",
    var age: Int = 0,
    var gender: String="",
    var address: String="",
    var postcode: Int = 0,
    var state: String = "",
    var phone: Int = 0,

    )
