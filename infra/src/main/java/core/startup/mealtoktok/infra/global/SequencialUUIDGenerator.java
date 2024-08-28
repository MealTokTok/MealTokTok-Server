package core.startup.mealtoktok.infra.global;

import java.util.UUID;

import org.springframework.stereotype.Component;

import core.startup.mealtoktok.domain.global.IdGenerator;

import com.fasterxml.uuid.Generators;

@Component
public class SequencialUUIDGenerator implements IdGenerator {

    @Override
    public UUID generate() {
        return Generators.timeBasedGenerator().generate();
    }
}
