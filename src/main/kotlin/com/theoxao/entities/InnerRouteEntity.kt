package com.theoxao.entities

import org.springframework.data.mongodb.core.mapping.Document

/**
 * create by theoxao on 2019/5/24
 */
@Document(collection = "inner_route")
class InnerRouteEntity : RouteEntity()
