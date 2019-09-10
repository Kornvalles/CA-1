/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Joke;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author benjaminbajrami
 */

public class JokeDTO {
    
    private String joke;
    private String reference;
    private double rating;

    public JokeDTO() {
    }

    public JokeDTO(Joke joke) {
        this.joke = joke.getJoke();
        this.reference = joke.getReference();
        this.rating = joke.getRating();
    }

    
}
