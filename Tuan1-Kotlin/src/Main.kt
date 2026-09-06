fun main() {
    println("Tên: Lê Văn Thịnh\nMsv: 2415053122340\nLHP: 126LTTD01")

    print("Nhập điểm môn Toán: ")
    val d1 = readln().toDouble()
    print("Nhập điểm môn Lập trình: ")
    val d2 = readln().toDouble()
    print("Nhập điểm môn Cơ sở dữ liệu: ")
    val d3 = readln().toDouble()
    val tong = d1 + d2 + d3
    val diemmax = maxOf(d1, d2, d3)
    val trungbinh = tong / 3
    println("-------------------------")
    println("Tổng điểm: $tong")
    println("Điểm cao nhất: $diemmax")
    println("Điểm trung bình: $trungbinh")

    if (trungbinh >= 5) {
        println("=> Sinh viên có GPA đạt")
    } else {
        println("=> Sinh viên không đạt")
    }
}