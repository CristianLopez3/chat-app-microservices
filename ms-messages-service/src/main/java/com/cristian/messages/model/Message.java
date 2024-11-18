package com.cristian.messages.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "messages")
@Builder
public class Message {

    @Id
    private String id;
    private String message;
    private String sender;
    private String receiver;

}
