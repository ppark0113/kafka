package com.kafka.serializer;

import com.kafka.model.Student;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class StudentSerializer implements Serializer<Student> {
    @Override
    public void configure(Map configs, boolean isKey) {
        // 구성이 필요한 내용
    }

    @Override
    public byte[] serialize(String topic, Student data) {
        try {
            int stringSize;
            byte[] serializedName;
            if (data == null)
                return null;
            else {
                if (data.getName() != null) {
                    serializedName = data.getName().getBytes("UTF-8");
                    stringSize = serializedName.length;
                } else {
                    serializedName = new byte[0];
                    stringSize = 0;
                }
            }

            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + stringSize);
            buffer.putInt(data.getId());
            buffer.putInt(stringSize);
            buffer.put(serializedName);

            return buffer.array();
        } catch (Exception e) {
            throw new SerializationException();
        }
    }

    @Override
    public void close() {
        // close 해줘야 할 내용
    }
}
