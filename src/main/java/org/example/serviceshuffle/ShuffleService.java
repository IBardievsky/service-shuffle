package org.example.serviceshuffle;

import org.example.serviceshuffle.dto.ShuffleRequest;
import org.example.serviceshuffle.exception.InvalidNumberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/shuffle")
public class ShuffleService {

    @Autowired
    private AsyncLogService asyncLogService;

    @PostMapping
    public ResponseEntity<List<Integer>> shuffle(@RequestBody ShuffleRequest request) {
        int number = request.getNumber();
        if (number < 1 || number > 1000) {
            throw new InvalidNumberException("Number must be between 1 and 1000");
        }

        List<Integer> shuffledArray = IntStream.rangeClosed(1, number)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(shuffledArray);

        // Log the request asynchronously
        asyncLogService.logRequestAsync(new ShuffleRequest(number));

        return ResponseEntity.ok(shuffledArray);
    }

}