package com.example.gbt_4.info

import java.util.*

data class UserDto(var id: Long, var userName:String, var gender:String, var birthYear: String,
    var smokingYear: Long, var comment: String, var price: Long, var averageSmoking: Long,
    var profileImg: String, var popupImg: String
)
