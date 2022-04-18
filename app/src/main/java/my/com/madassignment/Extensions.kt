package my.com.madassignment

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.errorDialog(text: String) {
    AlertDialog.Builder(context)
        .setTitle("Error")
        .setMessage(text)
        .setPositiveButton("Dismiss", null)
        .show()
}


// Usage: Show a snackbar from fragment
fun Fragment.snackbar(text: String) {
    Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show()
}

// Usage: Hide keyboard from fragment
fun Fragment.hideKeyboard() {
    val imm = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(requireView().windowToken, 0)
}
