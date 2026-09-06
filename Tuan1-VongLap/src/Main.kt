fun main() {
    println("Tên: Lê Văn Thịnh\nMsv: 2415053122340\nLHP: 126LTTD01")

    while (true) {
        println("\n------------- MENU -------------")
        println("1. Bài 1 (In từ 1 đến 10)")
        println("2. Bài 2 (Tính tổng 1 đến 100)")
        println("3. Bài 3 (In số chẵn từ 1 đến 20)")
        println("0. Thoát")
        print("Chọn bài muốn làm: ")

        val choice = readln().toIntOrNull() ?: -1

        when (choice) {
            1 -> {
                println("=> Bài 1:")
                val n = 10
                for (i in 1..n) {
                    print("$i ")
                }
                println()
            }
            2 -> {
                println("=> Bài 2:")
                val n = 100
                var tong = 0
                for (i in 1..n) {
                    tong += i
                }
                println("Tổng là: $tong")
            }
            3 -> {
                println("=> Bài 3:")
                val n = 20
                for (i in 2..n step 2) {
                    print("$i ")
                }
                println()
            }
            0 -> {
                println("=>thoát.")
                break
            }
            else -> {
                println("=> Vui lòng nhập lại!")
            }
        }
    }
}