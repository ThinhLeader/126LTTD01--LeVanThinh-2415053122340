package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Student
import com.example.myapplication.utils.toAcademyRanking
import com.example.myapplication.utils.toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //Khởi tạo danh sách sinh viên
    private val studentsList = mutableListOf(
        Student("2415053122340",
            "Lê Văn Thịnh",
            "CNTT",
            "thinh@gmail.com",
            3.4,
            "0993945851"),
        Student("2",
            "Hồ Văn Hoàng",
            "Luat",
            "hoang@gmail.com",
            3.2,
            "09123839348"),
        Student("3",
            "Lê Văn Thường",
            "CNTT",
            "thuong@gmail.com",
            3.8,
            "0987948471")
    )
    private var student: Student? = null

    //Hàm hiển thị thông tin
    private fun bindingStudent(student: Student){
        with(binding){
            tvName.text = "Tên: ${student.name}"
            tvStudentId.text = "MSV: ${student.id}"
            tvClass.text = "Lớp: ${student.className}"
            tvEmail.text = "Email: ${student.email}"
            tvGpa.text = "GPA: ${student.gpa}"
            tvRanking.text = "Đánh giá: ${student.gpa.toAcademyRanking()}"
            tvPhone.text = "SĐT: ${student.phone}"
            edtGpa.setText(student.gpa.toString())
            btnUpdateGpa.isEnabled = true
            btnCall.isEnabled = true
            btnDel.isEnabled = true
        }
    }

    //Hàm xóa thông tin trên màn hình
    private fun clearStudentProfile(){
        with(binding){
            tvTitle.text = "Danh sách rỗng"
            tvName.text = ""
            tvStudentId.text = ""
            tvClass.text = ""
            tvEmail.text = ""
            tvGpa.text = ""
            tvRanking.text = ""
            tvPhone.text = ""
            tilGpa.error = null
            edtGpa.setText("")
            btnUpdateGpa.isEnabled = false
            btnCall.isEnabled = false
            btnDel.isEnabled = false
        }
    }

    //Hàm xóa sinh viên
    private fun deleteStudent(){
        studentsList.remove(student)
        student = studentsList.firstOrNull()

        student?.let {
            bindingStudent(it)
        } ?: clearStudentProfile()
    }

    //Hàm hiển thị dialog
    private fun showConfirmDialog(onConfirm: () -> Unit){
        AlertDialog.Builder(this)
            .setTitle("Xác nhận")
            .setMessage("Bạn có chắc chắn muốn xóa?")
            .setPositiveButton("Xác nhận",{
                dialog,which->
                onConfirm()
            })
            .setNegativeButton("Hủy bỏ", {dialog,which-> })
            .show()
    }

    //Hàm xây dựng trimmed text
    fun EditText.trimmedText(): String = text?.toString()?.trim().orEmpty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        student = studentsList.firstOrNull()
        student?.let { bindingStudent(it) }

        //Cập nhật
        binding.btnUpdateGpa.setOnClickListener {
            val inputStr = binding.edtGpa.trimmedText()
            val newGpa = inputStr.toDoubleOrNull()

            if(newGpa == null || newGpa !in 0.0..4.0) {
                binding.tilGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm nhập vào không hợp lệ!")
                return@setOnClickListener
            }

            binding.tilGpa.error = null
            student?.gpa = newGpa
            student?.let { bindingStudent(it) }

            toast("Cập nhật điểm thành công!")
        }

        //Gọi điện
        binding.btnCall.setOnClickListener {
            student?.let {
                val intent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:${it.phone}")
                )
                startActivity(intent)
            }
        }

        //Xóa hồ sơ
        binding.btnDel.setOnClickListener{
            showConfirmDialog{
                deleteStudent()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}