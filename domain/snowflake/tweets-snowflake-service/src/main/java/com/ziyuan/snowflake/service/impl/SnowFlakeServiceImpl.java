package com.ziyuan.snowflake.service.impl;

import com.ziyuan.snowflake.service.SnowFlakeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@Slf4j
public class SnowFlakeServiceImpl implements SnowFlakeService {
    public final static String SID_MAX = "zzzzzzzz";
    public final static String SID_MIN = "00000000";
    private final static long START_STAMP = 1609480800000L; // 2021-01-01 00:00:00
    @Value("${snowflake.sequenceBit}")
    private long SEQUENCE_BIT; // bits of sequence

    // sum of bits of each part must <= 23 bits,
    // positive long has 63 bits, the timestamp diff is 40 bits, so the sum of bits of each part must <= 23 bits
    private final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    private final long MACHINE_LEFT = SEQUENCE_BIT;
    @Value("${snowflake.machineBit}")
    private long MACHINE_BIT;   // bits of machine
    private final long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    @Value("${snowflake.datacenterBit}")
    private long DATACENTER_BIT;// bits of datacenter
    private final long TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;
    // id depends on bit
    @Value("${snowflake.datacenterId}")
    private long datacenterId;  // datacenter id
    @Value("${snowflake.machineId}")
    private long machineId;     // machine id
    private long sequence = 0L; // init seq number
    private long lastTimestamp = -1L;// last timestamp

    /**
     * get a distributed unique increasing id
     *
     * @return next snowflake id has length of 8
     */
    @Override
    public synchronized String sid() {
        long currentTimestamp = getNewTimestamp();
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currentTimestamp == lastTimestamp) {
            // seq += 1 in the same timestamp
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // seq reach the max value in the same millis, so we borrow from the next millis
            if (sequence == 0L) {
                currentTimestamp = getNextMill();
            }
        } else {
            //reset sequence for new millisecond
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        long sid = (currentTimestamp - START_STAMP) << TIMESTAMP_LEFT // time stamp
                | datacenterId << DATACENTER_LEFT       // datacenter id
                | machineId << MACHINE_LEFT             // machine id
                | sequence;                             // sequence

        String sidStr = new BigInteger(String.valueOf(sid)).toString(36);
        while (sidStr.length() < 8) {
            sidStr = "0" + sidStr;
        }

        if (sidStr.length() > 8) {
            log.error("sidStr length is greater than 8");
            return null;
        }
        return sidStr + ":" + MACHINE_BIT;
    }

    private long getNextMill() {
        long mill = getNewTimestamp();
        while (mill <= lastTimestamp) {
            mill = getNewTimestamp();
        }
        return mill;
    }

    private long getNewTimestamp() {
        return System.currentTimeMillis();
    }
}