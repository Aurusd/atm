package com.mpsdevelopment.biopotential.server.service;

public class IdGenerator {
    protected static final int SEQ_NUM_BITS = 9;
    protected static final int NODE_NUM_BITS = 10;
    protected static final int BACK_TIME_BITS = 1;
    protected static final long SEQ_NUM_MAX = 512L;
    protected static volatile int seqNum = 0;
    protected static final int nodeNum = 0;
    protected static volatile long lastTimestamp = 0L;
    protected static long backTimeMoment = 9223372036854775807L;

    public IdGenerator() {
    }

    public static synchronized long nextId() {
        long curTimestamp = System.currentTimeMillis();
        if (curTimestamp < lastTimestamp) {
            backTimeMoment = lastTimestamp;
            lastTimestamp = curTimestamp;
        } else if (curTimestamp > backTimeMoment) {
            backTimeMoment = 9223372036854775807L;
        }

        if (curTimestamp == lastTimestamp) {
            ++seqNum;
            if ((long)seqNum >= 512L) {
                seqNum = 0;

                while(curTimestamp == lastTimestamp) {
                    curTimestamp = System.currentTimeMillis();

                    try {
                        Thread.sleep(1L);
                    } catch (InterruptedException var3) {
                        ;
                    }
                }
            }
        } else {
            seqNum = 0;
        }

        lastTimestamp = curTimestamp;
        return curTimestamp << 20 | 0L | (long)((backTimeMoment != 9223372036854775807L ? 1 : 0) << 9) | (long)seqNum;
    }
}