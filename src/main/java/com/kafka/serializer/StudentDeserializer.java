package com.kafka.serializer;

import com.kafka.model.Student;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class StudentDeserializer implements Deserializer<Student> {

    @Override
    public void configure(Map configs, boolean isKey) {}

    @Override
    public Student deserialize(String topic, byte[] data) {
        int id; // 필드를 미리 정의해둬야 한다.
        int nameSize;
        String name;

        try {
            if (data == null)
                return null;
            if (data.length < 0)
                throw new SerializationException("...");

            ByteBuffer buffer = ByteBuffer.wrap(data);
            id = buffer.getInt();
            nameSize = buffer.getInt();
            byte[] nameBytes = new byte[nameSize];
            buffer.get(nameBytes);
            name = new String(nameBytes, "UTF-8");

            return new Student(id, name);
        } catch (Exception e) {
            throw new SerializationException("...");
        }
    }

    @Override
    public void close() {
    }
}