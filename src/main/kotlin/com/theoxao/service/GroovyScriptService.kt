package com.theoxao.service

import com.theoxao.common.GroovyShellHolder
import groovy.lang.GroovyShell
import groovy.lang.Script
import org.springframework.stereotype.Service

/**
 * create by theoxao on 2019/5/19
 */
@Service
class GroovyScriptService {
    private val shell = GroovyShellHolder.shell

    fun parse(scriptAsString: String, invoke: Script.() -> Any): Any {
        val script = shell.parse(scriptAsString)
        return invoke(script)
    }

    fun mapParameterScript(scriptAsString: String, methodName: String, param: Map<Any, Any>): Any {
        val script = shell.parse(scriptAsString)
        return script.invokeMethod(methodName, param)
    }

    fun preParse(rawScript: String): String {
        //TODO do something
        return rawScript
    }
}