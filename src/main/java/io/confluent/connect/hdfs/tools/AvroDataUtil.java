/**
 * Copyright 2015 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package io.confluent.connect.hdfs.tools;

import org.apache.avro.LogicalType;
import org.apache.avro.Schema;
import org.apache.avro.LogicalTypes;

import java.util.Arrays;
import java.util.HashMap;


public class AvroDataUtil {
  private static HashMap<Schema, Schema> logicalTypeAvroSchemaCache = new HashMap<>();

  public static Schema addLogicalTypeTo(Schema avroSchema) {

    if (logicalTypeAvroSchemaCache.get(avroSchema) != null) {
      return logicalTypeAvroSchemaCache.get(avroSchema);
    }

    // add logicalType to schema
    addLogicalTypeIfRequired(avroSchema);

    logicalTypeAvroSchemaCache.put(avroSchema, avroSchema);

    return avroSchema;
  }

  private static void addLogicalTypeIfRequired(Schema avroSchema) {
    switch (avroSchema.getType()) {
      case RECORD: {
        for (Schema.Field field : avroSchema.getFields()) {
          addLogicalTypeIfRequired(field.schema());
        }
        break;
      }
      case ARRAY: {
        addLogicalTypeIfRequired(avroSchema.getElementType());
        break;
      }
      case MAP: {
        addLogicalTypeIfRequired(avroSchema.getValueType());
        break;
      }
      case UNION: {
        for (Schema schema : avroSchema.getTypes()) {
          addLogicalTypeIfRequired(schema);
        }
        break;
      }
      default: {
        String logicalTypeProp = avroSchema.getProp("logicalType");
        if (logicalTypeProp == null) {
          return;
        }

        // ignore timestamp/date logical type
        // make spark v2.0.1 & hive v1.1.0 happy
        String[] ignoredTypes = new String[] {"timestamp-millis", "timestamp-micros", "date"};

        if (Arrays.asList(ignoredTypes).contains(logicalTypeProp)) {
          return;
        }

        LogicalType logicalType = LogicalTypes.fromSchema(avroSchema);
        logicalType.addToSchema(avroSchema);
      }
    }
  }
}
