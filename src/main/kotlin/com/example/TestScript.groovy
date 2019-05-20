package com.example

import com.example.service.FooService
import com.theoxao.common.CommonResult
import com.theoxao.common.ParamWrap

static CommonResult service(ParamWrap paramWrap) {
    def id = paramWrap.call.parameters.get("id")
    def fooService = (FooService) paramWrap.servicesHolder.getService("fooService")
    def duplicate = fooService.duplicate(id)
    new CommonResult(duplicate)
}
