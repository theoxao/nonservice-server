package com.theoxao.entities;

import com.theoxao.common.BaseRouteData;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * create by theoxao on 2019/5/24
 */
@Document(collection = "inner_route")
public class InnerRouteEntity extends BaseRouteData {
}
