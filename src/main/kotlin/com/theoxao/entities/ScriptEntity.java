package com.theoxao.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

/**
 * create by theoxao on 2019/5/24
 */

@Document(collection = "script_data")
public class ScriptEntity {

    private ObjectId id;
    private String originScript;
    private String parsedScript;
    private ArrayList<String> methods;
    /**
     * 0 single method script
     * 1 multi method script
     */
    private Integer type;
}
