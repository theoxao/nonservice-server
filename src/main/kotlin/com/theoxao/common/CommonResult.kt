package com.theoxao.common


/**
 * @author theo
 * @date 2019/5/20
 */
class CommonResult(var data: Any?) {

    var code = 0
    var msg: String? = null

    init {
        this.code = 200
        this.msg = null
    }
}