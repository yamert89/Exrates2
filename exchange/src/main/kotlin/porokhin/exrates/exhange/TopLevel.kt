package porokhin.exrates.exhange

const val WILDCARD1 = "%%%"
const val WILDCARD2 = "$$$"
/**
 * Exchange endpoint url as receiver
 * */
fun String.substitute(firstParam: String, secondParam: String = ""): String =
    replaceFirst(WILDCARD1, firstParam).replaceFirst(WILDCARD2, secondParam)