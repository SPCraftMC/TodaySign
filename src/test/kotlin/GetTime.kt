import java.text.SimpleDateFormat
import java.util.*

fun xmain() {
    val sdf = SimpleDateFormat("HH")
    val currentDate = sdf.format(Date())
    println(currentDate)
    if (currentDate == "00") println("now 0a.m.")
}