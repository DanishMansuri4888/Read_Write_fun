import com.google.firebase.database.FirebaseDatabase

fun writeDataToFirebase(node: String, data: Any) {
    val database = FirebaseDatabase.getInstance().getReference(node)
    database.setValue(data).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            println("Data written successfully")
        } else {
            println("Error writing data: ${task.exception?.message}")
        }
    }
}


fun readDataFromFirebase(node: String, callback: (data: Any?) -> Unit) {
    val database = FirebaseDatabase.getInstance().getReference(node)
    database.get().addOnCompleteListener { task ->
        if (task.isSuccessful) {
            callback(task.result?.value)
        } else {
            println("Error reading data: ${task.exception?.message}")
        }
    }
}

fun main() {

    writeDataToFirebase("user1", mapOf("name" to "Umer", "age" to 30))


    readDataFromFirebase("user1") { data ->
        println("Retrieved data: $data")
    }
}

