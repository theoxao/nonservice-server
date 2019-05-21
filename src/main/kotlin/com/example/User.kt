package com.example

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


/**
 * @author theo
 * @date 2019/5/21
 */
@Document(collection = "col_user")
class User {
    @Id
    var id = ObjectId()
    var name: String? = null
    var age: Int = 0
}