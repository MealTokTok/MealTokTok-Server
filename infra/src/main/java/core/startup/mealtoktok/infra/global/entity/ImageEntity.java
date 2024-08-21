package core.startup.mealtoktok.infra.global.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    public static ImageEntity from(Image image) {

        if (image == null) {
            return null;
        }

        return new ImageEntity(image.getId() != null ? image.getId() : null, image.getImageUrl());
    }

    public Image toImage() {
        return new Image(id, imageUrl);
    }
}
