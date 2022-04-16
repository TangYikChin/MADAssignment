package my.com.madassignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class StaffViewModel:ViewModel() {
    private val col = Firebase.firestore.collection("staffs")
    private val staffs = MutableLiveData<List<Staff>>()

    init {
        col.addSnapshotListener{ snap, _->staffs.value = snap?.toObjects()}
    }

    fun get(id: String,name: String): Staff? {
        // TODO
        return null
    }

    fun getAll() = staffs

    fun delete(id: String) {
        // TODO
        col.document(id).delete()
    }

    fun deleteAll() {
        // TODO
        //col.get().addOnSuccessListener { snap->snap.documents.forEach{doc->delete(doc.id)} }
        staffs.value?.forEach { f->delete(f.id) }
    }

    fun set(f: Staff) {
        // TODO
        col.document(f.id).set(f)

    }


}