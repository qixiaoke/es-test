package com.common;

import com.domain.ServerResult;

public class ResultBuilder {

    public static <T>ServerResult build(T result) {
        ServerResult serverResult = new ServerResult(200, "SUCCESS", result);

        return serverResult;
    }
}
