package com.example

import com.theoxao.common.CommonResult
import com.theoxao.common.ParamWrap

import java.util.function.Consumer


static CommonResult service(ParamWrap paramWrap) {
    def list = paramWrap.servicesHolder.mongoTemplate.findAll(User.class)
    List<UserView> result = new ArrayList<>()
    list.forEach(new Consumer<User>() {
        @Override
        void accept(User user) {
            result.add(UserView.from(user))
        }
    })
    new CommonResult(result)
}

class UserView {
    String id
    String name
    int age

    static UserView from(User user) {
        def view = new UserView()
        view.id = user.id.toHexString()
        view.name = user.name.toString()
        view.age = user.age
        view
    }
}