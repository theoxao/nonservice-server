package com.theoxao.entities

import com.theoxao.common.BaseRouteData
import org.springframework.data.mongodb.core.mapping.Document


/**
 * @author theo
 * @date 2019/5/20
 */
@Document(collection = "route_data")
class RouteEntity : BaseRouteData() {

}