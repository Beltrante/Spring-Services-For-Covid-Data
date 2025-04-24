package it.covidaggregator.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;

@Builder
@Getter
@Setter
@ToString
public class Response<T>{
    public enum Type{
        SUCCESS,ERROR
    }
    @Builder.Default
    private Type type = Type.SUCCESS;
    private String description;
    @Builder.Default
    private int status = 200;
    private T data;
    @Builder.Default
    private String timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date());
}
