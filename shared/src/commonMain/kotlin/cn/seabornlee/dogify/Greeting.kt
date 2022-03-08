package cn.seabornlee.dogify

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}