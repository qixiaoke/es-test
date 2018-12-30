package com.common;

import com.domain.ServerResult;

public class ResultBuilder<T> {

    public static ServerResult build(Object result) {
        ServerResult serverResult = new ServerResult();
        serverResult.setCode(200);
        serverResult.setMessage("SUCCESS");
        serverResult.setObject(result);

        return serverResult;
    }
}
