package org.example.serviceshuffle.dto;

import java.util.List;

public class ShuffleResponse {

    private List<Integer> shuffled;

    public ShuffleResponse() {
    }

    public ShuffleResponse(List<Integer> shuffled) {
        this.shuffled = shuffled;
    }

    public List<Integer> getShuffled() {
        return shuffled;
    }

    public void setShuffled(List<Integer> shuffled) {
        this.shuffled = shuffled;
    }
}