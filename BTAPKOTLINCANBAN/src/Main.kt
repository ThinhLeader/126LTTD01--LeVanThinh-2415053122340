data class Student(
    var id: String,
    var name: String,
    var age: Int,
    var major: String,
    var gpa: Double
)

// Khởi tạo 5 sinh viên mẫu khác nhau
val students = mutableListOf(
    Student("2415053122340", "Le Van Thinh", 20, "Cong nghe so", 8.2),
    Student("2415053122341", "Hoang Van Quoc Nhat", 20, "Cong nghe so", 7.8),
    Student("2415053122342", "Pham Thanh Tien", 20, "Cong nghe so", 9.1),
    Student("2415053122343", "Thai Truong Giang", 19, "Ngon ngu Anh", 4.5),
    Student("2415053122344", "Tran Van Hoang", 21, "Kinh te", 8.8)
)

fun printHeader() {
    println(String.format("%-15s %-25s %-10s %-20s %s", "Student ID", "Full Name", "Age", "Major", "GPA"))
    println("-".repeat(75))
}

fun printStudentList(list: List<Student>) {
    if (list.isEmpty()) {
        println("Danh sách trống.")
        return
    }
    printHeader()
    list.forEach {
        println(String.format("%-15s %-25s %-10s %-20s %.2f", it.id, it.name, it.age, it.major, it.gpa))
    }
}

// 1. Add student
fun addStudent() {
    print("Enter ID: "); val id = readln()
    print("Enter Full Name: "); val name = readln()
    print("Enter Age: "); val age = readln().toIntOrNull() ?: 0
    print("Enter Major: "); val major = readln()
    print("Enter GPA: "); val gpa = readln().toDoubleOrNull() ?: 0.0
    students.add(Student(id, name, age, major, gpa))
    println("Student added successfully!")
}

// 3. Search student (Gộp chung tìm theo ID và tìm một phần tên - Req 8)
fun searchStudent(keyword: String) {
    val result = students.filter {
        it.id == keyword || it.name.contains(keyword, ignoreCase = true)
    }
    printStudentList(result)
}

// 6. Remove student
fun removeStudent(id: String) {
    val removed = students.removeIf { it.id == id }
    if (removed) println("Student removed.") else println("Student not found.")
}

// Req 1 & 2: Gộp chung hàm đếm theo khoảng GPA
fun countByGpaRange(min: Double, max: Double) = students.count { it.gpa in min..max }

// Req 4 & 10: Lấy danh sách Top GPA
fun displayTopGPA(topN: Int) {
    val topList = students.sortedByDescending { it.gpa }.take(topN)
    printStudentList(topList)
}

// Req 9, 11, 12: Hàm sắp xếp dùng chung
fun sortStudents(criteria: Int) {
    val sortedList = when (criteria) {
        1 -> students.sortedByDescending { it.gpa } // Req 9
        2 -> students.sortedBy { it.age }           // Req 11
        3 -> students.sortedBy { it.name }          // Req 12
        else -> students
    }
    printStudentList(sortedList)
    println("Sorted successfully.")
}

fun advancedMenu() {
    var choice: Int
    do {
        println("\n--- ADVANCED REQUIREMENTS ---")
        println("1. Đếm số sinh viên có GPA ≥ 8.0.")
        println("2. Đếm số sinh viên có GPA < 5.0.")
        println("3. Tính GPA trung bình của sinh viên ngành được giao.")
        println("4. Tìm sinh viên có GPA cao nhất.")
        println("5. Tìm sinh viên lớn tuổi nhất.")
        println("6. Tìm sinh viên có GPA nằm trong khoảng 7.0 → 8.5.")
        println("7. Tìm tất cả sinh viên thuộc một ngành.")
        println("8. Tìm sinh viên theo một phần tên.")
        println("9. Sắp xếp sinh viên theo GPA giảm dần.")
        println("10. Hiển thị 3 sinh viên có GPA cao nhất.")
        println("11. Sắp xếp sinh viên theo tuổi.")
        println("12. Sắp xếp sinh viên theo tên.")
        println("0. Back to Main Menu")
        print("Choice: ")

        choice = readln().toIntOrNull() ?: -1

        when (choice) {
            1 -> println("Đếm (GPA >= 8.0): ${countByGpaRange(8.0, 10.0)}")
            2 -> println("Đếm (GPA < 5.0): ${countByGpaRange(0.0, 4.99)}")
            3 -> {
                print("Nhập ngành học: "); val m = readln()
                val filtered = students.filter { it.major.equals(m, ignoreCase = true) }
                if (filtered.isNotEmpty()) println("GPA trung bình: ${filtered.map { it.gpa }.average()}")
                else println("Không tìm thấy sinh viên.")
            }
            4 -> { println("GPA cao nhất:"); displayTopGPA(1) }
            5 -> {
                println("Sinh viên già nhất:")
                students.maxByOrNull { it.age }?.let { printStudentList(listOf(it)) }
            }
            6 -> printStudentList(students.filter { it.gpa in 7.0..8.5 })
            7 -> {
                print("Nhập ngành học: "); val m = readln()
                printStudentList(students.filter { it.major.equals(m, ignoreCase = true) })
            }
            8 -> { print("Nhập chữ cái: "); searchStudent(readln()) }
            9 -> sortStudents(1)
            10 -> { println("Top 3 GPA:"); displayTopGPA(3) }
            11 -> sortStudents(2)
            12 -> sortStudents(3)
        }
    } while (choice != 0)
}

fun main() {
    var choice: Int
    do {
        println("\n========== STUDENT MANAGEMENT ==========")
        println("1. Add student")
        println("2. Display all students")
        println("3. Search student")
        println("4. Calculate average GPA")
        println("5. Find student with highest GPA")
        println("6. Remove student")
        println("7. Advanced Requirements (1-12)")
        println("0. Exit")
        print("Choose: ")

        choice = readln().toIntOrNull() ?: -1

        when (choice) {
            1 -> addStudent()
            2 -> printStudentList(students)
            3 -> { print("Nhập ID sinh viên cần tìm: "); searchStudent(readln()) }
            4 -> {
                val avg = if (students.isNotEmpty()) students.map { it.gpa }.average() else 0.0
                println("Trung bình GPA tất cả sinh viên: $avg")
            }
            5 -> displayTopGPA(1)
            6 -> { print("Nhập ID sinh viên muốn xóa: "); removeStudent(readln()) }
            7 -> advancedMenu()
            0 -> println("Đnag thoát...")
            else -> if (choice != 0) println("Lựa chọn không hợp lệ!")
        }
    } while (choice != 0)
}