package com.xiachufang.connect.fusefs.wal;

import io.confluent.connect.hdfs.storage.HdfsStorage;
import io.confluent.connect.hdfs.wal.WAL;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.errors.ConnectException;

public class FusefsWAL implements WAL {
    public FusefsWAL(String logsDir, TopicPartition topicPart, HdfsStorage storage) {

    }

    @Override
    public void acquireLease() throws ConnectException {

    }

    @Override
    public void append(String s, String s1) throws ConnectException {

    }

    @Override
    public void apply() throws ConnectException {

    }

    @Override
    public void truncate() throws ConnectException {

    }

    @Override
    public void close() throws ConnectException {

    }

    @Override
    public String getLogFile() {
        return null;
    }
}
