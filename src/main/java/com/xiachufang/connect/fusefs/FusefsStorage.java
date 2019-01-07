package com.xiachufang.connect.fusefs;

import com.xiachufang.connect.fusefs.wal.FusefsWAL;
import io.confluent.connect.hdfs.HdfsSinkConnectorConfig;
import io.confluent.connect.hdfs.storage.HdfsStorage;
import io.confluent.connect.hdfs.wal.WAL;
import org.apache.kafka.common.TopicPartition;

import java.io.IOException;

public class FusefsStorage extends HdfsStorage {
    public FusefsStorage(HdfsSinkConnectorConfig conf, String url) throws IOException {
        super(conf, url);
    }

    public WAL wal(String topicsDir, TopicPartition topicPart) {
        return new FusefsWAL(topicsDir, topicPart, this);
    }
}
