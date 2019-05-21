package com.theoxao.common;

import com.theoxao.service.ServicesHolder;
import com.theoxao.wrap.ApplicationCallWrap;
import com.theoxao.wrap.ContinuationWrap;
import io.ktor.application.ApplicationCall;
import kotlin.Unit;

/**
 * @author theo
 * @date 2019/5/21
 */
public class ParamWrap {
    public final ServicesHolder servicesHolder;
    public final ApplicationCall call;
    public final ApplicationCallWrap callWrap;

    public ParamWrap(ServicesHolder servicesHolder, ApplicationCall call) {
        this.servicesHolder = servicesHolder;
        this.call = call;
        this.callWrap = new ApplicationCallWrap(call);
    }

    public void respond(Object any) {
        callWrap.respond(any, new ContinuationWrap<Unit>() {
        });
    }
}
