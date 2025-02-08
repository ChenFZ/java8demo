/**
 * Copyright (C), 2015-2022
 * Description:
 * <author>          <time>          <version>          <desc>
 * chenfz           2022/3/30 17:07           1.0              描述
 */
package com.chenfz.orcpractice;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.OrcFile;
import org.apache.orc.Reader;
import org.apache.orc.RecordReader;
import org.apache.orc.TypeDescription;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrcFileReader {
    private static final int BATCH_SIZE = 2048;

    public static void main(String[] args) throws IOException {
        List<Map<String, Object>> rows = read(new Configuration(), "orders.orc");
        for (Map<String, Object> row : rows) {
            System.out.println(row);
        }
    }

    public static List<Map<String, Object>> read(Configuration configuration, String path)
            throws IOException {
        // Create a list to collect rows
        List<Map<String, Object>> rows = new LinkedList<>();

        // Create an ORC reader using the Hadoop fileSystem and path
        try (Reader reader = OrcFile.createReader(new Path(path), OrcFile.readerOptions(configuration))) {
            // Extract the schema
            TypeDescription schema = reader.getSchema();

            try (RecordReader records = reader.rows(reader.options())) {
                // Read rows in batch for better performance.
                VectorizedRowBatch batch = reader.getSchema().createRowBatch(BATCH_SIZE);
                LongColumnVector orderIdColumnVector = (LongColumnVector) batch.cols[0];
                BytesColumnVector itemNameColumnVector = (BytesColumnVector) batch.cols[1];
                DoubleColumnVector priceColumnVector = (DoubleColumnVector) batch.cols[2];

                while (records.nextBatch(batch)) {
                    for (int rowNum = 0; rowNum < batch.size; rowNum++) {
                        // Read rows from the batch
                        Map<String, Object> map = new HashMap<>();
                        map.put("order_id", orderIdColumnVector.vector[rowNum]);
                        map.put("item_name", itemNameColumnVector.toString(rowNum));
                        map.put("price", priceColumnVector.vector[rowNum]);
                        rows.add(map);
                    }
                }
            }
        }
        return rows;
    }
}
