package com.anyex.apps.bean;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class DecimalSerialize extends JsonSerializer<BigDecimal>
{
    public int scale = -1;
    
    public DecimalSerialize()
    {
    }
    
    public DecimalSerialize(int scale)
    {
        this.scale = scale;
    }
    
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException
    {
        String format = "";
        // 是否为空
        if (null != value && scale > -1)
        {
            format = value.setScale(scale, BigDecimal.ROUND_DOWN).toPlainString();
        }
        else
        {
            format = value.toPlainString();
        }
        gen.writeString(format);
    }
}