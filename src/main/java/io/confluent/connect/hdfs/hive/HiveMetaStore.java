/**
 * Copyright 2015 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 **/

package io.confluent.connect.hdfs.hive;

import io.confluent.connect.hdfs.HdfsSinkConnectorConfig;
import io.confluent.connect.hdfs.errors.HiveMetaStoreException;

import org.apache.hadoop.conf.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Deprecated
public class HiveMetaStore extends io.confluent.connect.storage.hive.HiveMetaStore {

  private Configuration conf;
  private HdfsSinkConnectorConfig connectorConfig;
  private Pattern tableNameConverterRegex;
  private String tableNameConverterReplacement;


  public HiveMetaStore(
      Configuration conf,
      HdfsSinkConnectorConfig connectorConfig
  ) throws HiveMetaStoreException {
    super(conf, connectorConfig);
    this.conf = conf;
    this.connectorConfig = connectorConfig;

    String regexConfig = connectorConfig.getString(
            HdfsSinkConnectorConfig.HIVE_TABLE_NAME_REGEX_CONFIG);
    if (regexConfig != null) {
      tableNameConverterRegex = Pattern.compile(regexConfig);
      tableNameConverterReplacement = connectorConfig.getString(
              HdfsSinkConnectorConfig.HIVE_TABLE_NAME_REPLACEMENT_CONFIG);
    }
  }

  public String tableNameConverter(String table) {

    if (table == null) {
      return table;
    }

    if (tableNameConverterRegex != null) {
      Matcher matcher = tableNameConverterRegex.matcher(table);
      if (matcher.matches()) {
        return matcher.replaceFirst(tableNameConverterReplacement);
      }
    }
    return table.replaceAll("[.-]", "_");
  }
}
