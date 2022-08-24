package com.javatechie.spring.batch.config;

import com.javatechie.spring.batch.entity.Deportista;
import org.springframework.batch.item.ItemProcessor;

public class DeportistaProcessor implements ItemProcessor<Deportista,Deportista> {

    @Override
    public Deportista process(Deportista deportista) throws Exception {
            return deportista;
    }
}
