package me.seungpang.advenced.trace.logtrace;

import me.seungpang.advenced.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
