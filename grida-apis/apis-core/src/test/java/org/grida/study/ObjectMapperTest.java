package org.grida.study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ObjectMapper 는")
public class ObjectMapperTest {

    static class PersonWithoutGetter {
        String name;
        int age;

        public PersonWithoutGetter(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    static class PersonWithGetter {
        String name;
        int age;

        public PersonWithGetter(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    @Test
    void getter_가_없으면_직렬화가_되지_않는다() {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        PersonWithoutGetter person = new PersonWithoutGetter("name", 24);

        // when
        boolean result = objectMapper.canSerialize(person.getClass());

        // then
        assertThat(result).isFalse();
    }

    @Test
    void getter_가_있으면_직렬화가_되다() {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        PersonWithGetter person = new PersonWithGetter("name", 24);

        // when
        boolean result = objectMapper.canSerialize(person.getClass());

        // then
        assertThat(result).isTrue();
    }
}
