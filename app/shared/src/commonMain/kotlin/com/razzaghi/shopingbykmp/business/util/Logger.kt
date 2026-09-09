package business.util

class Logger(
    private val tag:String,
    private val isDebug:Boolean=true,
) {
    fun log(msg:String){
        if (!isDebug){
            printLogD(tag,msg)
        }else{
            printLogD(tag,msg)
        }
    }

    companion object Factory{
        fun buildDebug(tag:String): Logger {
            return Logger(
                tag = tag,
                isDebug = true
            )
        }
        fun buildRelease(tag:String): Logger {
            return Logger(
                tag = tag,
                isDebug = false
            )
        }
    }

}

fun printLogD(tag: String?,msg: String?){
    println("$tag: $msg")
}